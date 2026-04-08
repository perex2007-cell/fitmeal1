package com.fitmeal.service;

import com.fitmeal.model.Feedback;
import java.util.ArrayList;
import java.util.List;

public class FeedbackService {
    private static FeedbackService instance;
    private List<Feedback> feedbacks = new ArrayList<>();

    private FeedbackService() {}

    public static FeedbackService getInstance() {
        if (instance == null) {
            instance = new FeedbackService();
        }
        return instance;
    }

    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    public List<Feedback> getFeedbacksByType(String type) {
        return feedbacks.stream()
                .filter(f -> f.getType().equals(type))
                .toList();
    }
}
