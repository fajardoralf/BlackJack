package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to BlackJack!");

        //Create our playing deck;

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();


        double playerMoney = 100.00d;

        Scanner userInput = new Scanner(System.in);


        //Game Loop
        while (playerMoney > 0) {
            //Plays until playermoney is 0
            System.out.println("You have " + playerMoney + ". How much would you like to bet?");

            double playerBet = userInput.nextDouble();


            while (playerBet > playerMoney) {
                System.out.println("You dont have enough money. " + "It has to be less than or equal to " + playerMoney + ".");
                System.out.println("You have " + playerMoney + ". How much would you like to bet?");
                playerBet = userInput.nextDouble();
            }

            boolean endRound = false;

            //Start dealing
            //Player gets two cards

            playerDeck.drawDeck(playingDeck);
            playerDeck.drawDeck(playingDeck);

            //Dealer gets two cards
            dealerDeck.drawDeck(playingDeck);
            dealerDeck.drawDeck(playingDeck);

            while (true) {
                System.out.println("Your hand: ");
                System.out.print(playerDeck.toString());
                System.out.println("\n" + "Your hand is: " + playerDeck.cardsValue());

                //Display Dealer Hand
                System.out.println("Dealer hand: \n" + dealerDeck.getCard(0).toString() +"\n"+ "[Hidden]");

                //What does the player want to do?
                System.out.println("Would you like to Hit(1) or Stand(2)");
                int response = userInput.nextInt();
                if (response == 1) {
                    playerDeck.drawDeck(playingDeck);
                    System.out.println("You drew a " + playerDeck.getCard(playerDeck.deckSize() - 1).toString());
                }
                if (response == 2) {
                    break;
                }
                //Breaks out of the first while loop
                if (playerDeck.cardsValue() > 21) {
                    System.out.println("Bust. You hit over 21 " + "\n" + playerDeck.cardsValue());
                    playerMoney -= playerBet;
                    break;
                }
                //If player has over 21 break out of the loop
                if (playerDeck.cardsValue() == 21) {
                    System.out.println("You have 21");
                    break;
                }
            }

            //Reveal Dealer Cards
            System.out.println("Dealer Cards: " + dealerDeck.toString());
            //See if dealer has more points than player
            if ((dealerDeck.cardsValue() > playerDeck.cardsValue()) && !endRound) {
                System.out.println("Dealer wins");
                playerMoney -= playerBet;
                endRound = true;
            }
            //Checks if players cards are over 21
            if (playerDeck.cardsValue() > 21) {
                dealerDeck.toString();
                System.out.println("Dealer wins" + "You lost " + playerBet + ".");
                endRound = true;
            }

            //Dealer draws at 16. stand by 17
            while ((dealerDeck.cardsValue() < 17) && !endRound) {
                dealerDeck.drawDeck(playingDeck);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());
            }

            //Display total value for dealer
            System.out.println("Dealers hand is valued at: " + dealerDeck.cardsValue());
            if ((dealerDeck.cardsValue() > 21) && !endRound) {
                System.out.println("Dealer busts! " + "You win!\n" + "You won " + playerBet + ".");
                playerMoney += playerBet;
                endRound = true;
            }
            //Determine if draw
            if ((playerDeck.cardsValue() == dealerDeck.cardsValue()) && !endRound) {
                System.out.println("Push");
                endRound = true;
            }
            //If you have better cards than dealer
            if ((playerDeck.cardsValue() > dealerDeck.cardsValue()) && !endRound) {
                System.out.println("You win the hand" + "You won " + playerBet + ".");
                playerMoney += playerBet;
                endRound = true;
            } else if (endRound == false) {
                System.out.println("You lose the hand. " + "You lost " + playerBet + ".");
                playerMoney -= playerBet;
                endRound = true;

            }
            if (playerMoney <= 0.00d) {
                System.out.println("You dont have any more money. The game is over");
            }


            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);

            System.out.println("End of hand");
            System.out.println("--NEW ROUND--");
        }

        System.out.println("Game over!");

    }
}
