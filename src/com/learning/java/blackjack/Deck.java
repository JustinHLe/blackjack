package com.learning.java.blackjack;


import com.learning.java.blackjack.cards.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Deck {

    private int numberOfCards;
    private int numberOfSuites;

    List<List<Card>> cards;
    List<Card> aces;

    List<Card> queens;
    List<Card> kings;
    List<Card> jacks;
    List<Card> ten;

    List<Card> two;
    List<Card> three;
    List<Card> four;
    List<Card> five;
    List<Card> six;
    List<Card> seven;
    List<Card> eight;
    List<Card> nine;

    public Deck(){
        this.numberOfCards = 52;
        this.numberOfSuites = 13;
        cards = new ArrayList<List<Card>>();
        aces = new ArrayList<>(Arrays.asList(new Ace(), new Ace(), new Ace(), new Ace()));

        queens = new ArrayList<>(Arrays.asList(new Queen(), new Queen(), new Queen(), new Queen()));
        kings = new ArrayList<>(Arrays.asList(new King(), new King(), new King(), new King()));
        jacks = new ArrayList<>(Arrays.asList(new Jack(), new Jack(), new Jack(), new Jack()));
        ten = new ArrayList<>(Arrays.asList(new Ten(), new Ten(), new Ten(), new Ten()));

        two = new ArrayList<>(Arrays.asList(new Two(), new Two(), new Two(), new Two()));
        three = new ArrayList<>(Arrays.asList(new Three(), new Three(), new Three(), new Three()));
        four = new ArrayList<>(Arrays.asList(new Four(), new Four(), new Four(), new Four()));
        five = new ArrayList<>(Arrays.asList(new Five(), new Five(), new Five(), new Five()));
        six = new ArrayList<>(Arrays.asList(new Six(), new Six(), new Six(), new Six()));
        seven = new ArrayList<>(Arrays.asList(new Seven(), new Seven(), new Seven(), new Seven()));
        eight = new ArrayList<>(Arrays.asList(new Eight(), new Eight(), new Eight(), new Eight()));
        nine = new ArrayList<>(Arrays.asList(new Nine(), new Nine(), new Nine(), new Nine()));

        cards.add(two);
        cards.add(three);
        cards.add(four);
        cards.add(five);
        cards.add(six);
        cards.add(seven);
        cards.add(eight);
        cards.add(nine);

        cards.add(queens);
        cards.add(kings);
        cards.add(jacks);
        cards.add(ten);

        cards.add(aces);


    }
    public int getNumberOfCards() {
        return this.numberOfCards;
    }

    public int getNumberOfSuites(){
        return this.numberOfSuites;
    }

    public void removeEmptySuites(){
        for(int i = 0; i < this.cards.size(); i++){
            if(this.cards.get(i).size() == 0){
                this.cards.remove(i);
                this.numberOfSuites--;
            }
        }
    }
    public void removeSuite(){
        this.numberOfSuites--;
    }
    public void removeCard(){
        this.numberOfCards--;
    }
    public void showDeck(){
        for(int i = 0; i < this.cards.size(); i++){
            System.out.println("SUITE: " + cards.get(i));
        }
        System.out.println("Number of cards: " + this.numberOfCards);
        System.out.println("Number of Suites: " + this.numberOfSuites);
    }

}
