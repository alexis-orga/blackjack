public class GameLogic {
    private Deck deck;
    private Hand playerHand, dealerHand;
    private int account = 100;
    private int bet = 0;

    public GameLogic() {
        startNewGame();
    }

    public void startNewGame() {
        Deck deck = new Deck();
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();
        bet = 0;

        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
    }

    public void placeBet(int amount) {
        if (account >= amount) {
            bet += amount;
            account -= amount;
        }
    }

    public void playerHit() {
        playerHand.addCard(deck.drawCard());
    }

    public boolean isPlayerBusted() {
        return playerHand.getTotal() > 21;
    }

    public void dealerTurn() {
        while (dealerHand.getTotal() < 17) {
            dealerHand.addCard(deck.drawCard());
        }
    }

    public String determineWinner() {
        if (dealerHand.getTotal() > 21 || playerHand.getTotal() > dealerHand.getTotal()) {
            account += bet * 2;
            return "You win!";
        } else if (dealerHand.getTotal() == playerHand.getTotal()) {
            account += bet;
            return "Draw!";
        } else {
            return "Dealer win !";
        }
    }

    public int getAccount() {
        return account;
    } 
    public int getBet() {
        return bet;
    }
    public Hand getPlayerHand() {
        return playerHand;
    }
    public Hand getDealerHand() {
        return dealerHand;
    }
}