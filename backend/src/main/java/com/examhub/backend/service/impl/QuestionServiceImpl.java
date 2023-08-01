package com.examhub.backend.service.impl;

import com.examhub.backend.model.Question;
import com.examhub.backend.model.Quiz;
import com.examhub.backend.repository.QuestionRepository;
import com.examhub.backend.repository.QuizRepository;
import com.examhub.backend.service.QuestionService;
import com.examhub.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Question addQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long id) {
        if(this.questionRepository.findById(id).isPresent()){
            return this.questionRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Long id) {
        Question question = new Question();
        question.setQuesId(id);
        this.questionRepository.delete(question);
    }

    @Override
    public Question get(Long questionId) {
        return this.questionRepository.getOne(questionId);
    }


}
