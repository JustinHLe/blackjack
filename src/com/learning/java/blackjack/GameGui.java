package com.learning.java.blackjack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Integer.parseInt;

public class GameGui implements ActionListener {

    private int numberOfPlayers;
    private JFrame frame;

    private JPanel NorthPanel;
    private JPanel SouthPanel;
    private JPanel EastPanel;
    private JPanel WestPanel;
    private JPanel CenterPanel;

    private int dealerScore;

    JLabel currentPlayerLabel;

    JLabel dealerScoreLabel;

    JLabel playerScoreLabel;

    JButton hitBtn;

    JButton stayBtn;

    JButton restartBtn;

    Player currentPLayer;

    Game g;

    JPanel dealerRow;
    JPanel playerRow;

    JPanel btnPanel;
    public GameGui(int numberOfPlayers) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1800, 700);
        NorthPanel = new JPanel();
        SouthPanel = new JPanel();
        EastPanel = new JPanel();
        WestPanel = new JPanel();
        CenterPanel = new JPanel();
        addTitle();
        addFooter();
        addInfoPanel();

        WestPanel.setPreferredSize(new Dimension(EastPanel.getPreferredSize().width, 700));


        frame.add(NorthPanel, BorderLayout.NORTH);
        frame.add(SouthPanel, BorderLayout.SOUTH);
        frame.add(EastPanel, BorderLayout.EAST);
        frame.add(WestPanel, BorderLayout.WEST);

        frame.setVisible(true);
        this.numberOfPlayers = numberOfPlayers;
        start();
        frame.add(CenterPanel, BorderLayout.CENTER);
    }

    public void addTitle(){
        JLabel blackjackTitle = new JLabel();
        blackjackTitle.setText("Blackjack");
        blackjackTitle.setFont(new Font("Arial", Font.PLAIN, 32));

        NorthPanel.add(blackjackTitle);
    }

    public void addFooter(){
        btnPanel = new JPanel();

        hitBtn = new JButton("Hit");
        stayBtn = new JButton("Stay");
        restartBtn = new JButton("Restart");

        hitBtn.addActionListener(this);
        stayBtn.addActionListener(this);
        restartBtn.addActionListener(this);

        btnPanel.add(hitBtn);
        btnPanel.add(stayBtn);
        btnPanel.add(restartBtn);

        SouthPanel.setPreferredSize(new Dimension(2000, 50));
        SouthPanel.add(btnPanel);
    }

    public void addInfoPanel(){
        EastPanel.setLayout(new BoxLayout(EastPanel, BoxLayout.Y_AXIS));
        EastPanel.setBorder(new EmptyBorder(0, 0, 0, 80));

        WestPanel.setLayout(new BoxLayout(WestPanel, BoxLayout.Y_AXIS));
        WestPanel.setBorder(new EmptyBorder(0, 80, 0, 0));

        dealerScoreLabel = new JLabel("Dealer Score: ");
        currentPlayerLabel = new JLabel("Player: ");
        playerScoreLabel = new JLabel("Player Score:");

        dealerScoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        currentPlayerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        playerScoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        EastPanel.add(Box.createVerticalGlue());
        EastPanel.add(dealerScoreLabel);
        EastPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        EastPanel.add(currentPlayerLabel);
        EastPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        EastPanel.add(playerScoreLabel);
        EastPanel.add(Box.createVerticalGlue());

        WestPanel.add(Box.createVerticalGlue());
        WestPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        WestPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        WestPanel.add(Box.createVerticalGlue());
    }

    public void setCenterPanel(){
        CenterPanel.setLayout(new GridLayout(2, 1));
        dealerRow = new JPanel();
        dealerRow.setLayout(new BoxLayout(dealerRow, BoxLayout.X_AXIS));

        dealerRow.add(Box.createHorizontalGlue());
        JLabel faceDownCard = new JLabel();
        faceDownCard.setIcon(new ImageIcon(new ImageIcon("C://Users/Justin/Java/blackjack/src/com/learning/java/blackjack/images/face-down-card.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        dealerRow.add(faceDownCard);
        dealerRow.add(Box.createRigidArea(new Dimension(10, 0)));
        JLabel openCard = new JLabel();
        openCard.setIcon(new ImageIcon(new ImageIcon("C://Users/Justin/Java/blackjack/src/com/learning/java/blackjack/images/"+ g.getDealer().getHand().get(0).getName() + ".png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        dealerRow.add(openCard);
        dealerRow.add(Box.createHorizontalGlue());


        CenterPanel.add(dealerRow);
    }

    public void start(){
        g = new Game(numberOfPlayers, this);
        setCenterPanel();
        setPlayerCards(currentPLayer);
        dealerScore = g.getDealerScore();
        dealerScoreLabel.setText("Dealer Score: XX");
        List<Player> winners = g.getSatisifed();
        if(winners.size() > 0){
            for(int i = 0; i < winners.size(); i++){
                setWinner(winners.get(i));
            }
        }
    }

    public void setCurrentPlayer(Player p){
        currentPlayerLabel.setText("Player: " + p.getNumber());
        playerScoreLabel.setText("Player Score: " + p.getHandValue());
        this.currentPLayer = p;
    }

    public void setPlayerCards(Player currentPLayer){
        Component[] c = CenterPanel.getComponents();
        if(c.length == 2){
            CenterPanel.remove(c[1]);
        }
        playerRow = new JPanel();
        playerRow.setLayout(new BoxLayout(playerRow, BoxLayout.X_AXIS));
        JLabel cardOne = new JLabel();
        cardOne.setIcon(new ImageIcon(new ImageIcon("C://Users/Justin/Java/blackjack/src/com/learning/java/blackjack/images/" + currentPLayer.getHand().get(0).getName() + ".png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        playerRow.add(cardOne);
        playerRow.add(Box.createRigidArea(new Dimension(10, 0)));
        JLabel cardTwo = new JLabel();
        cardTwo.setIcon(new ImageIcon(new ImageIcon("C://Users/Justin/Java/blackjack/src/com/learning/java/blackjack/images/"+ currentPLayer.getHand().get(1).getName() + ".png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        playerRow.add(cardTwo);
        CenterPanel.revalidate();
        CenterPanel.repaint();
        CenterPanel.add(playerRow);
    }

    public void addPlayerCard(Player currentPLayer){
        JLabel newCard = new JLabel();
        newCard.setIcon(new ImageIcon(new ImageIcon("C://Users/Justin/Java/blackjack/src/com/learning/java/blackjack/images/" + currentPLayer.getHand().get(currentPLayer.getHand().size() - 1).getName() + ".png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        playerRow.add(Box.createRigidArea(new Dimension(10, 0)));
        playerRow.add(newCard);
        playerRow.add(Box.createRigidArea(new Dimension(10, 0)));
    }

//    public void removeCards(){
//        Component[] c = CenterPanel.getComponents();
//        if(c.length == 2){
//            CenterPanel.remove(c[1]);
//        }
//        CenterPanel.repaint();
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.hitBtn){
            this.currentPLayer.draw(this.currentPLayer);
            addPlayerCard(currentPLayer);
            playerScoreLabel.setText("Player Score: " + this.currentPLayer.getHandValue());
            if(checkForWinner()){
                return;
            }
            if(this.currentPLayer.getHandValue() > 21){
                JLabel gameMessage = new JLabel("Player " + this.currentPLayer.number + " lost");
                EastPanel.add(gameMessage);
                currentPLayer.setLost();
                SouthPanel.remove(btnPanel);
                SouthPanel.repaint();
                JLabel temp = new JLabel(" ");
                SouthPanel.add(temp);
                SouthPanel.setPreferredSize(new Dimension(2000, 50));
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        g.kick();
                        setPlayerCards(currentPLayer);
                        SouthPanel.remove(temp);
                        SouthPanel.repaint();
                        SouthPanel.add(btnPanel);
                    }
                },2000);
            }
        }
        else if (e.getSource() == this.stayBtn){
            if(checkForWinner()){
                return;
            }
            currentPLayer.setStay();
            g.unmount();
            setPlayerCards(currentPLayer);
        } else {
            //restarting
            System.out.println("restarting");
            frame.dispose();
            new LoginGUI();
        }
    }

    public void showScore(List<Player> satisfied, Dealer d, Game g){
        frame.dispose();
        new EndGUI(satisfied, d, g);
    }

    public Boolean checkForWinner(){
        if(this.currentPLayer.getHandValue() == 21){
            JLabel gameMessage = new JLabel("Player " + this.currentPLayer.number + " won");
            EastPanel.add(gameMessage);
            currentPLayer.setWin();
            SouthPanel.remove(btnPanel);
            SouthPanel.repaint();
            JLabel temp = new JLabel(" ");
            SouthPanel.add(temp);
            SouthPanel.setPreferredSize(new Dimension(2000, 50));
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    g.kick();
                    setPlayerCards(currentPLayer);
                    SouthPanel.remove(temp);
                    SouthPanel.repaint();
                    SouthPanel.add(btnPanel);
                }
            },2000);
            return true;
        }
        return false;
    }

    public void setWinner(Player player){
        JLabel gameMessage = new JLabel("Player " + player.number + " won");
        EastPanel.add(gameMessage);
    }
}
