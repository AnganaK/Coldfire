package com.incredible.computerstudies.models;

import java.util.ArrayList;

/**
 * Created by incredible on 7/7/16.
 */
public class Questions {
/*
    {
        "id": 1,
        "question": "what is ur name??",
        "question_type": "objective",
        "chapter_no": 1,
        "options": "[jigish, vaibhavi, dakshal, dhruval]",
        "subject": "computer",
        "correct_answer": 3,
        "difficulty_level": 0,
        "solution": "its my name"
    }
*/

    int id;
    String questions;
    String question_type;
    int chapter_no;
    ArrayList<String> options;
    String subject;
    int correct_answer;
    int difficulty_level;
    String solution;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public int getChapter_no() {
        return chapter_no;
    }

    public void setChapter_no(int chapter_no) {
        this.chapter_no = chapter_no;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(int correct_answer) {
        this.correct_answer = correct_answer;
    }

    public int getDifficulty_level() {
        return difficulty_level;
    }

    public void setDifficulty_level(int difficulty_level) {
        this.difficulty_level = difficulty_level;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
