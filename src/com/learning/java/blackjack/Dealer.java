package com.learning.java.blackjack;

import com.learning.java.blackjack.cards.Ace;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dealer {
    private int numberOfPlayers;
    private ArrayList<Card> hand;
    Deck deck;
    private List<Player> playerList;

    private List<Player> satisfied;
    private int handValue;

    private GameGui g;
    Dealer(int numberOfPlayers, Deck deck, List<Player> satisfied, GameGui gameGui){
        this.numberOfPlayers = numberOfPlayers;
        this.deck = deck;
        this.satisfied = satisfied;
        this.g = gameGui;

        hand = new ArrayList<Card>();
        this.playerList = new ArrayList<>();
        this.deal();
    }

    public void deal(){
        int i;
        int j;
        for(i = 0; i < numberOfPlayers; i++){
            this.playerList.add(new Player(i + 1, deck));
        }
        Random rand = new Random();
        i = 0;


        for(i = 0; i < numberOfPlayers; i++){
            Player currentPlayer = this.playerList.get(i);
            j = 0;
            while (j < 2) {
                int draw_card = rand.nextInt(deck.cards.size());
//                int draw_card;
//                if(j == 0){
//                    draw_card = deck.cards.size() - 1;
//                } else {
//                    draw_card = deck.cards.size() - 2;
//                }
                List<Card> suite = deck.cards.get(draw_card);
                if (suite.size() == 0) {
                    deck.removeSuite();
                    deck.cards.remove(suite);
                    continue;
                }
                currentPlayer.getHand().add(suite.get(suite.size() - 1));
                suite.remove(suite.size() - 1);
                deck.removeCard();
                j++;
            }
        }
        this.deck.removeEmptySuites();

        i = 0;
        j = 0;

        while (j < 2) {
            int draw_card = rand.nextInt(deck.cards.size());
            List<Card> suite = deck.cards.get(draw_card);
            if (suite.size() == 0) {
                deck.removeSuite();
                deck.cards.remove(suite);
                continue;
            }
            this.hand.add(suite.get(suite.size() - 1));
            suite.remove(suite.size() - 1);
            deck.removeCard();
            j++;
        }

        i = 0;
        j = 0;
        this.calculateDealerHandValue();
        for(i = 0; i < this.playerList.size(); i++){
            playerList.get(i).calculatePlayerHandValue();
            if(playerList.get(i).getHandValue() == 21){
                playerList.get(i).setWin();
            }
        }
        i = 0;
        while(i < this.playerList.size()){
            if(playerList.get(i).getState() == "win"){
                satisfied.add(playerList.get(i));
                playerList.remove(i);
            } else {
                i++;
            }
        }
//        this.deck.showDeck();
//        this.showAllHands();

    }

    public void showAllHands(){
        for(int i = 0; i < playerList.size(); i++){
            System.out.println("PLAYER " +  playerList.get(i).getNumber() + ": "  + playerList.get(i).getHand() + "\n VALUE: " + playerList.get(i).getHandValue());
        }

        System.out.println("DEALER: " + this.hand + "\n VALUE: " + this.handValue);
    }
    public ArrayList<Card> getHand(){
        return this.hand;
    }
    public void calculateDealerHandValue(){
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

    public int getDealerScore(){
        return this.handValue;
    }

    public List<Player> getPlayerList(){
        return this.playerList;
    }

    public void draw(){
        int i = 0;
        int j = 0;

        Random rand = new Random();
        boolean drew = false;

        while(!drew){
            int draw_card = rand.nextInt(deck.cards.size()); //deck.cards is the array that contains the all the subarray of cards
            if(draw_card == 0){
                break;
            }
            List<Card> suite = deck.cards.get(draw_card); // get the suite associated with the deck
            if (suite.size() == 0) {
                deck.removeSuite();
                deck.cards.remove(suite);
                continue;
            }
            this.getHand().add(suite.get(suite.size() - 1));
            this.calculateDealerHandValue();

            suite.remove(suite.size() - 1);
            deck.removeCard();
            drew = true;
        }
    }
}
