package com.learning.java.blackjack;

public class Card {
    private String name;
    private int value;

    public Card(String name, int value){
        this.name = name;
        this.value = value;
    }
    public Card(String name){
        this.name = name;
    }

    public int getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public void setValue(int value){
        this.value = value;
    }
}
