package com.learning.java.blackjack;

import java.util.*;

public class Game {
    int numberOfPlayers;
    private Dealer d;

    private Player currentPlayer;

    private GameGui gameGui;
    Queue<Player> q;

    List<Player> satisifed;


    public Game(int numberOfPlayers, GameGui gameGui){
        this.numberOfPlayers = numberOfPlayers;
        this.gameGui = gameGui;
        q = new LinkedList<>();
        Deck deck = new Deck();
        satisifed = new ArrayList<>();
        d = new Dealer(numberOfPlayers, deck, satisifed, gameGui);

        this.start(d);
    }

    void start(Dealer d){
        List<Player> playerList = d.getPlayerList();
        /*
            q methods:
            offer() add
            poll() remove
            peek() examine head of queue
         */
        for(int i = 0; i < playerList.size(); i++){
            q.offer(playerList.get(i));
        }
        mount();
    }

    void mount(){
        if(!q.isEmpty()){
            Player current = q.peek();
            gameGui.setCurrentPlayer(current);
        }
        if(satisifed.size() == numberOfPlayers){
            while(d.getDealerScore() < 17){
                d.draw();
            }
            gameGui.dealerScoreLabel.setText("Dealer Score: " + d.getDealerScore());
            gameGui.showScore(satisifed, d, this);
        }
    }

    public void unmount(){
        Player current = q.peek();
        satisifed.add(current);
        q.poll();
        mount();
    }

    public void kick(){
        Player current = q.peek();
        satisifed.add(current);
        q.poll();
        mount();
    }

    public Dealer getDealer(){
        return this.d;
    }
    public int getDealerScore(){
        return d.getDealerScore();
    }

    public List<Player> getSatisifed(){
        return satisifed;
    }
}
