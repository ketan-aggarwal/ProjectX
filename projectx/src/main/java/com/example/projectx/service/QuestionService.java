package com.example.projectx.service;

import com.example.projectx.entity.Question;
import com.example.projectx.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    // Get all questions
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // Get a question by ID
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    // Create a new question
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    // Get questions by asker (User)
    public List<Question> getQuestionsByAsker(Long askerId) {
        return questionRepository.findByAskerId(askerId);
    }

    // You can add more methods as needed (e.g., for updates, deletes, etc.)
}
