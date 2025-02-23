package com.example.projectx.service;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleTokenVerifier {

    private static final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
            new NetHttpTransport(),
            new GsonFactory()
    ).build();

    // GoogleTokenVerifier.java
    public GoogleIdToken.Payload verify(String idToken) throws Exception {
        try {
            GoogleIdToken idTokenObj = verifier.verify(idToken);
            if (idTokenObj == null) {
                System.out.println("NULL TOKEN - verification failed");
                throw new RuntimeException("Invalid token");
            }
            GoogleIdToken.Payload payload = idTokenObj.getPayload();
            System.out.println("Verified user: " + payload.getEmail());
            return payload;
        } catch (Exception e) {
            System.out.println("Token verification error: " + e.getMessage());
            throw e;
        }
    }
}