package com.example.projectx.controller;

import com.example.projectx.entity.User;
import com.example.projectx.jwt.JwtUtil;
import com.example.projectx.service.GoogleTokenVerifier;
import com.example.projectx.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RestController
@RequestMapping("/api/oauth")
public class OAuthController {

    private final GoogleTokenVerifier googleTokenVerifier;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public OAuthController(GoogleTokenVerifier googleTokenVerifier,
                           UserService userService,
                           JwtUtil jwtUtil) {
        this.googleTokenVerifier = googleTokenVerifier;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/google")
    public ResponseEntity<?> handleGoogleLogin(@RequestBody Map<String, String> payload,
                                               HttpServletResponse response) {
        try {

            String googleToken = payload.get("token");

            // Write the token to a separate file (appending to it)
            try (FileWriter fw = new FileWriter("google_token_log.txt", true);
                 PrintWriter pw = new PrintWriter(fw)) {
                pw.println("======== Received Google Token from Frontend ========");
                pw.println(googleToken);
                pw.println("=====================================================");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            GoogleIdToken.Payload tokenPayload = googleTokenVerifier.verify(payload.get("token"));

            String email = tokenPayload.getEmail();
            String name = (String) tokenPayload.get("name");

            // Check/Create user
            User user = userService.findOrCreateUser(email, name);

            String jwt = jwtUtil.generateToken(email);

            response.addHeader(HttpHeaders.SET_COOKIE, createCookie(jwt).toString());
            return ResponseEntity.ok()
                    .body(Map.of(
                            "email", email,
                            "name", name,
                            "jwt", jwt
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Authentication failed"));
        }
    }

    private ResponseCookie createCookie(String token) {
        return ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(false) // Keep false for local development
                .path("/")
                .maxAge(86400)
                .sameSite("None") // Changed to None
                .domain("localhost") // Explicit domain
                .build();
    }
}