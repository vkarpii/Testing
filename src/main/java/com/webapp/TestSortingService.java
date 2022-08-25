package com.webapp;

import com.webapp.entity.Answer;
import com.webapp.entity.Question;
import com.webapp.entity.Test;
import com.webapp.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class TestSortingService {
    private TestSortingService(){}
    public static Test sorting(Test test){
        ArrayList<Question> questions = test.getQuestions();
        switch (test.getComplexity()){
            case "Medium":
                Collections.shuffle(questions);
                break;
            case "Hard":
                Collections.shuffle(questions);
                for (int i = 0; i != questions.size();i++){
                    ArrayList<Answer> answers = questions.get(i).getAnswers();
                    Collections.shuffle(answers);
                    questions.get(i).setAnswers(answers);
                }
                break;
            default:
        }
        test.setQuestions(questions);
        return test;
    }
}
