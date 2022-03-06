package com.user.progress.service.impl;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TestService {

    private static final String FILE_PATH = "C:\\Development\\DevOps\\kafka.txt";

    public static void main(String[] args) {

        List<WordCount> wordCountList = new ArrayList<>();
        List<String> words = new ArrayList<>();
        words.add("Hi");
        words.add("Hi");
        words.add("welcome");
        words.add("welcome");
        words.add("welcome");
        words.add("welcome");
        words.add("John");

        words.stream().forEach(word -> {
            WordCount wordCount = new WordCount();
            wordCount.setWord(word);
            wordCount.setCount(words.stream().filter(w -> w.equals(word)).collect(Collectors.toList()).size());
            wordCountList.add(wordCount);
        });
        wordCountList.stream().distinct().sorted(Comparator.comparing(WordCount::getCount).reversed()).collect(Collectors.toList()).forEach(wordCount -> System.out.println(wordCount.getWord() + "->" + wordCount.getCount()));
    }

    @Data
    static class WordCount {
        String word;
        int count;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof WordCount)) return false;
            WordCount wordCount = (WordCount) o;
            return count == wordCount.count && word.equals(wordCount.word);
        }

        @Override
        public int hashCode() {
            return Objects.hash(word, count);
        }

        public WordCount() {
        }

        public WordCount(String word, int count) {
            this.word = word;
            this.count = count;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
