package BlackJack;

public class Cards {
    Number number;
    Suit suit;

    public Cards(Number number, Suit suit) {
        this.number = number;
        this.suit = suit;
    }


    public Number getNumber() {
        return number;
    }


    public Suit getSuit() {
        return suit;
    }


    @Override
    public String toString() {

        return number+"  "+suit;
    }
}
