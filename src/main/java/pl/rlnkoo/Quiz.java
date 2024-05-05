package pl.rlnkoo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Quiz {

    File quizJson;

    public Quiz(String fileName) {
        String file = Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName)).getFile();
        quizJson = new File(file);
    }

    public void displayQuestions() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Question> questions = mapper.readValue(quizJson, new TypeReference<>() {});

        questions.stream()
                .map(Question::getQuestion)
                .forEach(System.out::println);
    }

    public void play() throws IOException {
        Scanner scanner = new Scanner(System.in);

        int points = 0;

        ObjectMapper mapper = new ObjectMapper();
        List<Question> questions = mapper.readValue(quizJson, new TypeReference<>() {});

        for (Question question : questions) {
            System.out.println(question.getQuestion());
            System.out.println("A: " + question.getA());
            System.out.println("B: " + question.getB());
            System.out.println("C: " + question.getC());
            System.out.println("D: " + question.getD());
            System.out.println();
            System.out.println("Provide your answer (a, b, c, d): ");

            String answer = scanner.nextLine();

            if (question.getCorrectAnswer().equals(answer)) {
                System.out.println("Correct answer!");
                System.out.println();
                points ++;
            } else {
                System.out.println("Wrong answer. Correct answer is: " + question.getCorrectAnswer());
                System.out.println();
            }
        }
        scanner.close();
        System.out.println("Quiz ended. You scored: " + points + "/20 points!");
    }
}
