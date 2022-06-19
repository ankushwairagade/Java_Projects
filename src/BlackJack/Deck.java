package BlackJack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    ArrayList<Cards> cards;

    public Deck() {
        this.cards= new ArrayList<>();
    }

    void creatingDecks() // works
    {
        for(Suit s:Suit.values())
        {
            for(Number n : Number.values())
            {
                cards.add(new Cards(n, s));
            }
        }
    }

    void randomShuffle() // works
    {
        ArrayList<Cards> temp = new ArrayList<>();
        Random random = new Random();
        int randomIndex=0;
        int originalSize= this.cards.size();

        for (int i = 0; i < originalSize; i++) {
            randomIndex = random.nextInt((this.cards.size()-1)+1);
            temp.add(this.cards.get(randomIndex));
            this.cards.remove(randomIndex);
        }

        this.cards=temp;
    }

    Cards getCard(int i)
    {
        return this.cards.get(i);
    }

    void removeCard(int i){
        this.cards.remove(i);
    }

    void addCard(Cards addCard)
    {
        this.cards.add(addCard);
    }

    void draw(Deck dCard)
    { // always begining card are draw from deck
        this.cards.add(dCard.getCard(0));
        dCard.removeCard(0);
    }

    int deckSize() {return this.cards.size();}

    void moveAllToDeck(Deck moveTo)
    {
        int thisDeckSize= this.cards.size();

        //put cards into moveto Deck
        for(int i=0;i<thisDeckSize;i++)
        {
            moveTo.addCard(this.getCard(i));
        }

        // remove cards
        for (int i=0;i<thisDeckSize;i++) {
            this.removeCard(0);
        }
    }

    // return total Value at cards in desk
    public int cardValue() {
        int totalValue = 0;
        int aces = 0;

        for (Cards aCard : this.cards) {   //  Ace , Two , Three, Four, Five ,Six , Seven, Eight, Nine,Ten,Jack,Queen ,King
            switch (aCard.getNumber()) {

                case TWO: totalValue += 2;   break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4;  break;
                case FIVE: totalValue += 5;  break;
                case SIX: totalValue += 6;   break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9;  break;
                case TEN: totalValue += 10;  break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10;break;
                case KING: totalValue += 10; break;
                case ACE:   totalValue += 1;  break;

            }

            for (int i = 0; i < aces; i++) {

                if (totalValue > 10) {
                    totalValue += 1;
                } else {
                    totalValue += 11;
                }
            }
        }
        return totalValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Cards c : cards) {

            sb.append(" "+c+"\n");
        }
        return sb.toString();
    }
}
