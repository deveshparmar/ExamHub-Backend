package com.examhub.backend.controller;

import com.examhub.backend.model.Question;
import com.examhub.backend.model.Quiz;
import com.examhub.backend.service.QuestionService;
import com.examhub.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }


    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("quizId") Long quizId) {
        Quiz quiz = this.quizService.getQuiz(quizId);
        Set<Question> questionSet = quiz.getQuestions();
        List<Question> list = new ArrayList<>(questionSet);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
        }
        list.forEach((q)->{
            q.setAnswer("");
        });
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{quesId}")
    public ResponseEntity<Question> getQuestion(@PathVariable("quesId")Long quesId){
        return ResponseEntity.ok(this.questionService.getQuestion(quesId));
    }

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?>getQuestionsOfQuizAdmin(@PathVariable("qid")Long qid){
        Quiz quiz = new Quiz();
        quiz.setQId(qid);
        Set<Question>questions = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questions);
    }

    @DeleteMapping("/{quesId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("quesId")Long quesId){
        this.questionService.deleteQuestion(quesId);
        return ResponseEntity.ok("Question Deleted");
    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question>list){
        double marksGot= 0;
        int correctAnswer = 0;
        int attempted = 0;
        double percentage;
        for(Question q : list) {
            Question question = this.questionService.get(q.getQuesId());
            if (question.getAnswer().trim().equals(q.getGivenAnswer().trim())) {
                correctAnswer++;
                double singleMarks = Double.parseDouble(list.get(0).getQuiz().getMaxMarks()) / list.size();
                marksGot += singleMarks;
            }

            if (q.getGivenAnswer()!=null) {
                attempted++;
            }
        }
        percentage = (double) correctAnswer / list.size() *100;
            Map<String,Object>of = Map.of("marksGot",marksGot,"correctAnswers",correctAnswer,"attempted",attempted, "percentage",percentage);

        return  ResponseEntity.ok(of);
    }
}
