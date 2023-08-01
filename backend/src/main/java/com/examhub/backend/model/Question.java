package com.examhub.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quesId;
    @Column(length = 5000)
    private String question;
    private String image;
    @Transient
    private String givenAnswer;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String answer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
}
