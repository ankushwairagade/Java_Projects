package BlackJack;

import java.util.Scanner;

public class main {

    public static void main(String[] args) throws InterruptedException {

        gameplay();

    }

    static void gameplay() throws InterruptedException {

        Deck playingDeck = new Deck();
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        // initalized the playingDeck
        playingDeck.creatingDecks();
        playingDeck.randomShuffle();

        Scanner sc = new Scanner(System.in);

        // give somemoney
        double playerMoney=0;
        System.out.println("Welcome to Black Jack! ");

        System.out.println("Enter total money / chips");

        playerMoney=sc.nextDouble();
        // game loop

        while(playerMoney >0)
        {
            // game play on
            // take input from user for money

            System.out.println("You have "+playerMoney+" ₹ , how much would you like to bet ?");
            double playerBet=sc.nextDouble();

            if(playerBet > playerMoney){
                System.out.println("you cannot bet more than you have.");
                break;
            }
            // start dealing  player get two cards and dealer gets two card

            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            System.out.println("just wait !");
            System.out.println("Deck is Initialization  ...");
            Thread.sleep(4000);
            System.out.println("Deck is Shuffling ...");
            Thread.sleep(2000);
            boolean endRound=false;

            while(true)
            {
                System.out.println("Your hand :");
                System.out.println(playerDeck);
                Thread.sleep(1000);
                System.out.println("your hand is valued at : "+playerDeck.cardValue());

                // only show first card
                System.out.println("Dealer hand :");
                Thread.sleep(1000);
                System.out.println(dealerDeck.getCard(0).toString()+" and {Hidden}");


                // then ask to hit or stay
                System.out.println("Would you like to (1)Hit or  (2)Stand / Stay");
                int response = sc.nextInt();

                if(response==1)
                { // Hit case
                    playerDeck.draw(playingDeck);
                    System.out.println(" you draw a : "+playerDeck.getCard(playerDeck.deckSize()-1).toString());

                    // bust if >21
                    int i = playerDeck.cardValue();
                    if(i >= 21)
                    {
                        System.out.println("Bust. currently valued at : "+playerDeck.cardValue() );
                        playerMoney -=playerBet;
                        endRound=true;
                        break;
                    }
                }

                if(response==2)
                {
                    // stand or stay
                    break;

                }

            }

            //outer loop
            // reveal the dealer cards

            System.out.println("Dealer cards: "+dealerDeck);
            //check and compared card valuesss

            // Dealer Draws at 15, stand at 17

            while((dealerDeck.cardValue() <17) && (endRound==false))
            {
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer Draws : "+dealerDeck.getCard(dealerDeck.deckSize()-1).toString());

            }
            // Display total valued for Dealer
            System.out.println("Dealer's Hand is valued at: "+dealerDeck.cardValue());
            // determine if dealer busted

            if(dealerDeck.cardValue() >21 && endRound==false){
                System.out.println("Dealer busts! You Win");
                playerMoney+=playerBet;
                endRound=true;
            }


            // determine if push
            if(playerDeck.cardValue() == dealerDeck.cardValue() && endRound==false)
            {
                System.out.println("Push");
                endRound=true;
            }

            if(playerDeck.cardValue() > dealerDeck.cardValue() && endRound==false)
            {
                System.out.println("You Win the Hand!");
                playerMoney+=playerBet;
                endRound=true;
            }

            if(dealerDeck.cardValue() > playerDeck.cardValue() && endRound==false)
            {
                System.out.println("Dealer Beats you !");
                playerMoney -=playerBet;
                endRound=true;
            }

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);

            System.out.println("End of Hand ");

        }
        System.out.println("Game Over! you are out of money");

    }


}
