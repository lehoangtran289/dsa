package com.codeforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Template {
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    static String readln() throws IOException {
        return input.readLine();
    }

    public static void main(String[] args) throws IOException {
        String input = Template.readln();

        solution(input);
    }

    public static void solution(String input) {

    }
}
