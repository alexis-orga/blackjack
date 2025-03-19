import java.util.Scanner;

public class Main {
    @SuppressWarnings("resource")
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        boolean drawCard = true;
        int account = 100;

        while (playAgain) {
            if (account <= 0) {
                System.out.println("You dont have any money left !");
                break;
            }

            Deck deck = new Deck();
            Hand playerHand = new Hand();
            Hand dealerHand = new Hand();

            playerHand.addCard(deck.drawCard());
            dealerHand.addCard(deck.drawCard());
            playerHand.addCard(deck.drawCard());
            dealerHand.addCard(deck.drawCard());

            System.out.println("Your account balance: $" + account);
            System.out.println("How much do you want to play ?");
            int bet = scanner.nextInt();
            scanner.nextLine();
            if (bet > account || bet <= 0 ) {
                System.out.println("Invalid bet amount, try again.");
                continue;
            }
            account -= bet;


            System.out.println("Your hand: " + playerHand + "Total: " + playerHand.getTotal());
            System.out.println("Dealer hand: [?] " + dealerHand.getCards().get(1));
            
            if (playerHand.getTotal() == 21) {
                System.out.println("Blackjack ! You win !");
                account = bet*2;
            }

            while (drawCard) {
                System.out.println("Do you want to draw a card? (yes/no)");
                String choice = scanner.nextLine().toLowerCase();
                if (choice.equals("yes")) {
                    playerHand.addCard(deck.drawCard());
                    System.out.println(playerHand + " " + playerHand.getTotal());
                    if (playerHand.getTotal() > 21) {
                        System.out.println("Busted, you are above 21 ! You lose :(");
                        break;
                    } 
                } else {
                    break;
                }
            }

            if (playerHand.getTotal() <= 21) {
                System.out.println("Dealer has the hand...");
                while (dealerHand.getTotal() < 17) {
                    dealerHand.addCard(deck.drawCard());
                    System.out.println("Dealer hand: " + dealerHand + " Total: " + dealerHand.getTotal());
                }
                if (dealerHand.getTotal() > 21 || playerHand.getTotal() > dealerHand.getTotal()) {
                    System.out.println("You won");
                    account += bet * 2;
                } else if (dealerHand.getTotal() == playerHand.getTotal()) {
                    System.out.println("Draw");
                    account += bet;
                } else {
                    System.out.println("Dealer won");
                }
            }

            System.out.println("New balance is: $" + account);
            System.out.println("Do you want to play again? (yes/no)");
            playAgain = scanner.nextLine().equalsIgnoreCase("yes");
        }
    }
}    