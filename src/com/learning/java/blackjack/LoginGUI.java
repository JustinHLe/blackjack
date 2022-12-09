package com.learning.java.blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class LoginGUI implements ActionListener {

    private JLabel game_limit_txt;
    private JFrame frame;
    private JTextField player_input;
    private JPanel homePanel;
    private JPanel gamePanel;
    LoginGUI(){
        //initialize variables and set layout
        frame = new JFrame();
        homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        homePanel.setPreferredSize(new Dimension(400, 200));

        homePanel.add(Box.createVerticalGlue());
        //HomePanel InfoText
        game_limit_txt = new JLabel("Enter number of players (Max: 6)");
        game_limit_txt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        game_limit_txt.setAlignmentY(JLabel.CENTER_ALIGNMENT);

        homePanel.add(game_limit_txt);
        homePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        //create input
        player_input = new JTextField();
        player_input.setMaximumSize(new Dimension(300, 20));
        homePanel.add(player_input);
        homePanel.add(Box.createRigidArea(new Dimension(0, 10)));


        //create button
        JButton enter_button = new JButton("Enter");
        enter_button.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        enter_button.addActionListener(this);
        homePanel.add(enter_button);

        homePanel.add(Box.createVerticalGlue());

        //add panel to display
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Blackjack Login");
        frame.add(homePanel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        //Get Text from input and validate
        String inputData = player_input.getText();
        try{
            int numberOfPlayers = parseInt(inputData);
            if(numberOfPlayers <= 0){
                throw new NumberFormatException("invalid number");
            }
            else if (numberOfPlayers > 6){
                throw new NumberFormatException("exceeds game limit");
            } else {
                frame.dispose();
                new GameGui(numberOfPlayers);
            }
        } catch(NumberFormatException err){
            game_limit_txt.setText("Invalid user input please input a NUMBER from 1 - 6.");
        }
    }



    public static void main(String[] args){
        new LoginGUI();
    }
}
