package com.udacity.gradle.jokes;

import java.util.Random;

public class Joker {

    static String[] jokes = {
            "Why is Santa so jolly?\n" +
                    "Because he knows where all the naughty girls live.",
            "What is the difference between a snowman and a snow-woman?\n" +
                    "Snowballs.",
            "What happens when you cross a snowman and a vampire?\n" +
                    "You get a frostbite.\n",
            "The 21st century: Deleting history is often more important than making it."
    };

    public static String getJoke() {
        Random r = new Random();
        return jokes[r.nextInt(jokes.length)];
    }

    public static String[] getAllJokes() {
        return jokes;
    }
}