package com.dsaws.common.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Translation implements Serializable {

    private int id;

    private String word;

    private String translation;

    private int likes;

    private String source;

    private Date date;

    private int wordId;

    private String fuzzyWord;

    public Translation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getFuzzyWord() {
        return fuzzyWord;
    }

    public void setFuzzyWord(String fuzzyWord) {
        this.fuzzyWord = fuzzyWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Translation that = (Translation) o;
        return id == that.id && wordId == that.wordId && Objects.equals(word, that.word) && Objects.equals(translation, that.translation) && Objects.equals(likes, that.likes) && Objects.equals(source, that.source) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, translation, likes, source, date, wordId);
    }
}
