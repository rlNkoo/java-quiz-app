package pl.rlnkoo;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Quiz quiz = new Quiz("quiz.json");
        quiz.play();
    }
}