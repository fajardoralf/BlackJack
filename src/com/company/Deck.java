package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;

    public Deck(){
        this.cards = new ArrayList<Card>();
    }

    public void createFullDeck(){
        //Generate cards

        for(Suit cardSuit: Suit.values()){
            for(Value cardValue : Value.values()){
                //Add a new card to the mix
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }

    public void shuffle(){
        ArrayList<Card> deck = new ArrayList<Card>();
        Random random = new Random();

        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for(int i = 0; i < originalSize; i++){
            //Generate random  rand.nextInt((max - min) + 1) + min;
            randomCardIndex = random.nextInt((this.cards.size()-1)+ 1);
            deck.add(this.cards.get(randomCardIndex));

            //Remove card from deck
            this.cards.remove(randomCardIndex);
        }

        this.cards = deck;
    }


    public String toString(){
        String cardListOutput = "";


        for(Card cardToOutput: this.cards){
            cardListOutput +=  "\n" + cardToOutput.toString();

        }
        return cardListOutput;
    }

    public void removeCard(int i){
        this.cards.remove(i);
    }

    public Card getCard(int i){
        return this.cards.get(i);
    }

    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    //Draws from the deck
    public void drawDeck(Deck comingFrom){
        this.cards.add(comingFrom.getCard(1));
        comingFrom.removeCard(1);
    }

    public int deckSize(){
        return this.cards.size();
    }

    public void moveAllToDeck(Deck moveTo){
        int thisDeckSize = this.cards.size();

        for(int i = 0; i < thisDeckSize; i++){
            moveTo.addCard(this.getCard(i));
        }

        for(int i = 0; i < thisDeckSize; i++){
            this.removeCard(0);

        }
    }

    //Return total value of cards in deck
    public int cardsValue(){
        int totalValue = 0;
        int aceValue = 0;
        int aceCount = 4;

        for(Card cards : this.cards) {
            switch (cards.getValue()) {
                case TWO:
                    totalValue += 2;
                    break;
                case THREE:
                    totalValue += 3;
                    break;
                case FOUR:
                    totalValue += 4;
                    break;
                case FIVE:
                    totalValue += 5;
                    break;
                case SIX:
                    totalValue += 6;
                    break;
                case SEVEN:
                    totalValue += 7;
                    break;
                case EIGHT:
                    totalValue += 8;
                    break;
                case NINE:
                    totalValue += 9;
                    break;
                case TEN:
                    totalValue += 10;
                    break;
                case JACK:
                    totalValue += 10;
                    break;
                case QUEEN:
                    totalValue += 10;
                    break;
                case KING:
                    totalValue += 10;
                    break;
                case ACE:
                    totalValue += 11;
                    aceValue += 11;
                    break;
            }

            if(totalValue > 21 || aceCount > 0){
                for(Card aces : this.cards){
                    if(totalValue <= 21){
                        break;
                    }
                    if(aces.getValue() == Value.ACE){
                        aceCount--;
                        totalValue -= 10;
                    }
                }
            }

        }
              return totalValue;
    }

}
