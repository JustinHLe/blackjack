package com.learning.java.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private ArrayList<Card> hand;
    int number;
    Deck deck;
    private int handValue;

    private String state;
    Player(int number, Deck d){
        hand = new ArrayList<Card>();
        this.number = number;
        this.deck = d;
    }

    public ArrayList<Card> getHand(){
        return this.hand;
    }

    public int getHandValue(){
        return this.handValue;
    }

    public void calculatePlayerHandValue(){
        int sum = 0;
        for(int i = 0; i < this.hand.size(); i++){
            Card currentCard = this.hand.get(i);
            if(currentCard.getName().equals("Ace")){
                if(sum + 11 > 21){
                    currentCard.setValue(1);
                } else {
                    currentCard.setValue(11);
                }
                sum += currentCard.getValue();
            } else {
                sum += currentCard.getValue();
            }
        }
        this.handValue = sum;
    }

    public int getNumber(){
        return this.number;
    }

    public void draw(Player currentPlayer){
        int i = 0;
        int j = 0;

        Random rand = new Random();
        boolean drew = false;

        while(!drew){
            int draw_card = rand.nextInt(deck.cards.size() - 1); //deck.cards is the array that contains the all the subarray of cards
            if(draw_card == 0){
                continue;
            }
            List<Card> suite = deck.cards.get(draw_card); // get the suite associated with the deck
            if (suite.size() == 0) {
                deck.removeSuite();
                deck.cards.remove(suite);
                continue;
            }
            currentPlayer.getHand().add(suite.get(suite.size() - 1));
            currentPlayer.calculatePlayerHandValue();
            suite.remove(suite.size() - 1);
            deck.removeCard();
            drew = true;
        }
    }

    public void setLost(){
        this.state = "lost";
    }

    public void setWin(){
        this.state = "win";
    }

    public void setStay(){
        this.state = "stay";
    }

    public String getState(){
        return this.state;
    }


}
