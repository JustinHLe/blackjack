package com.learning.java.blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EndGUI implements ActionListener {
    List<Player> players;
    Dealer d;
    private JFrame frame;

    private JPanel Top;
    private JPanel Bottom;

    private JButton restartBtn;

    private Game g;
    EndGUI(List<Player> satisfied, Dealer d, Game g){
        players = satisfied;
        this.d = d;
        this.g = g;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 700);
        frame.setLayout(new GridLayout(2, 1));

        Top = new JPanel();
        Bottom = new JPanel();

        setTop(Top);
        setBottom(Bottom);

        frame.add(Top);
        frame.add(Bottom);
        frame.setVisible(true);
    }

    public void setTop(JPanel top){
        top.setLayout(new BorderLayout());
        JPanel header = new JPanel();
        JPanel footer = new JPanel();

        JLabel dealer = new JLabel("Dealer");
        dealer.setFont(new Font("Arial", Font.PLAIN, 32));
        header.add(dealer);

        JPanel dealerRow = new JPanel();
        dealerRow.setLayout(new BoxLayout(dealerRow, BoxLayout.X_AXIS));

        dealerRow.add(Box.createHorizontalGlue());
        for(int i = 0; i < d.getHand().size(); i++){
            JLabel faceDownCard = new JLabel();
            faceDownCard.setIcon(new ImageIcon(new ImageIcon("C://Users/Justin/Java/blackjack/src/com/learning/java/blackjack/images/"+ g.getDealer().getHand().get(i).getName() + ".png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            dealerRow.add(faceDownCard);
            dealerRow.add(Box.createRigidArea(new Dimension(10, 0)));
        }
        dealerRow.add(Box.createHorizontalGlue());

        footer.setLayout(new BorderLayout());

        JLabel dealerScoreLabel = new JLabel("Dealer Score: " + d.getDealerScore());
        dealerScoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        dealerScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        footer.add(dealerScoreLabel, BorderLayout.CENTER);
        if(d.getDealerScore() > 21){
            JLabel dealerMessage = new JLabel("Dealer bust everyone wins!");
            dealerMessage.setFont(new Font("Arial", Font.PLAIN, 18));
            dealerMessage.setHorizontalAlignment(JLabel.CENTER);
            footer.add(dealerMessage, BorderLayout.SOUTH);
        }

        top.add(header, BorderLayout.NORTH);
        top.add(dealerRow, BorderLayout.CENTER);
        top.add(footer, BorderLayout.SOUTH);

    }
    public void setBottom(JPanel bottom){
        bottom.setLayout(new BorderLayout());

        JPanel btnContainer = new JPanel();
        restartBtn = new JButton("Restart");
        restartBtn.addActionListener(this);
        btnContainer.add(restartBtn);

        JPanel stats = new JPanel();
        stats.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
        for(int i = 0; i < players.size(); i++){
            JPanel playerContainer = new JPanel();
            playerContainer.setLayout(new BoxLayout(playerContainer, BoxLayout.Y_AXIS));

            JLabel player = new JLabel("Player: " + String.valueOf(players.get(i).getNumber()));
            player.setFont(new Font("Arial", Font.PLAIN, 22));
            JLabel score = new JLabel("Score: " + String.valueOf(players.get(i).getHandValue()));
            score.setFont(new Font("Arial", Font.PLAIN, 22));

            JLabel status = new JLabel();
            if(d.getDealerScore() > 21){
                status = new JLabel("Status: win");
                status.setFont(new Font("Arial", Font.PLAIN, 22));
            } else {
                if(players.get(i).getState() == "stay" && players.get(i).getHandValue() > d.getDealerScore()){
                    status = new JLabel("Status: win");
                    status.setFont(new Font("Arial", Font.PLAIN, 22));
                }
                else if(players.get(i).getState() == "stay" && players.get(i).getHandValue() <= d.getDealerScore()){
                    status = new JLabel("Status: lost");
                    status.setFont(new Font("Arial", Font.PLAIN, 22));
                }
                else if(players.get(i).getState() != "stay"){
                    status = new JLabel("Status: " + players.get(i).getState());
                    status.setFont(new Font("Arial", Font.PLAIN, 22));
                }
            }

            playerContainer.add(player);
            playerContainer.add(score);
            playerContainer.add(status);
            stats.add(playerContainer);
        }

        Bottom.add(stats, BorderLayout.CENTER);
        Bottom.add(btnContainer, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.restartBtn){
            frame.dispose();
            new LoginGUI();
        }

    }
}
