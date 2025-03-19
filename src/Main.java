import java.util.Scanner;

public class Main {
    @SuppressWarnings("resource")
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        boolean drawCard = true;

        while (playAgain) {
            Deck deck = new Deck();
            Hand playerHand = new Hand();
            Hand dealerHand = new Hand();

            playerHand.addCard(deck.drawCard());
            dealerHand.addCard(deck.drawCard());
            playerHand.addCard(deck.drawCard());
            dealerHand.addCard(deck.drawCard());

            System.out.println("Your hand: " + playerHand + "Total: " + playerHand.getTotal());
            System.out.println("Dealer hand: [?] " + dealerHand.getCards().get(1));
            if (playerHand.getTotal() == 21) {
                System.out.println("Blackjack ! You win !");
            }
            if (dealerHand.getTotal() == 21) {
                System.out.println("Dealer got blackjack, you lost !");
            }
            while (drawCard) {
                System.out.println("Do you want to draw a card? (yes/no)");
                String choice = scanner.nextLine().toLowerCase();
                if (choice.equals("yes")) {
                    playerHand.addCard(deck.drawCard());
                    System.out.println(playerHand);
                    System.out.println(playerHand.getTotal());
                    if (playerHand.getTotal() > 21) {
                        System.out.println("Busted, you are above 21 ! You lose :(");
                        return;
                    } else if (playerHand.getTotal() == 21) {
                        System.out.println("You hit 21, cant draw again");
                        break;
                    }
                } else {
                    break;
                }
            }
            System.out.println("Dealer has the hand");
            while (dealerHand.getTotal() < 17) {
                dealerHand.addCard(deck.drawCard());
                System.out.println(dealerHand + " " + dealerHand.getTotal());
            }

            System.out.println("Dealer hand: " + dealerHand + " Total: " + dealerHand.getTotal());

            if (dealerHand.getTotal() > 21 || playerHand.getTotal() > dealerHand.getTotal()) {
                System.out.println("You won !");
            } else if (dealerHand.getTotal() == playerHand.getTotal()) {
                System.out.println("Draw !");
            } else {
                System.out.println("Dealer won !");
            }

            System.out.println("Do you want to play again ? (yes/no)");
            String playAgainResponse = scanner.nextLine().toLowerCase();
            playAgain = playAgainResponse.equals("yes");
        }
    }
}