import java.awt.*;
import java.util.List;

public class Window extends Frame {
    private GameLogic game;
    private Label lblAccount, lblBet, lblPlayerHand, lblDealerHand, lblStatus;
    private Button btnHit, btnStand, btnNewGame, btnBet10;

    public Window() {
        game = new GameLogic();

        setTitle("Blackjack by me");
        setSize(400, 400);
        setLayout(new GridLayout(6, 1));

        lblAccount = new Label("Account: $");
        lblBet = new Label("Bet: $");
        lblPlayerHand = new Label("Your hand");
        lblDealerHand = new Label("Dealer hand");
        lblStatus = new Label("");

        btnBet10 = new Button("Bet 10$");
        btnHit = new Button("Hit");
        btnStand = new Button("Stand");
        btnNewGame = new Button("New Game");

        add(lblAccount);
        add(lblBet);
        add(lblDealerHand);
        add(lblPlayerHand);
        add(lblStatus);

        Panel btnPanel = new Panel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(btnBet10);
        btnPanel.add(btnHit);
        btnPanel.add(btnStand);
        btnPanel.add(btnNewGame);
        add(btnPanel);

        btnBet10.addActionListener(e -> placeBet(10));
        btnHit.addActionListener(e -> hitCard());
        btnStand.addActionListener(e -> dealerTurn());
        btnNewGame.addActionListener(e -> startNewGame());

        setVisible(true);
    }

    private void startNewGame() {
        game.startNewGame();
        lblStatus.setText("");
        updateUI();
    }

    private void placeBet(int amount) {
        if (game.getDealerHand() == null) {
            System.out.println("GameLogic not initialized! Restarting...");
            game.startNewGame();
        }

        game.placeBet(amount);
        updateUI();
    }

    private void hitCard() {
        game.playerHit();
        if (game.isPlayerBusted()) {
            lblStatus.setText("Busted !");
        }
        updateUI();
    }

    private void dealerTurn() {
        game.dealerTurn();
        lblStatus.setText(game.determineWinner());
        updateUI();
    }

    private void updateUI() {
        if (game.getDealerHand() == null || game.getPlayerHand() == null) {
            System.out.println("Error: Hands not initialized! Skipping update.");
            return;
        }

        lblAccount.setText("Account: $" + game.getAccount());
        lblBet.setText("Bet: $" + game.getBet());
        lblDealerHand.setText("Dealer hand" + formatHand(game.getDealerHand().getCards()) + " " + game.getDealerHand().getTotal());
        lblPlayerHand.setText("Your hand" + formatHand(game.getPlayerHand().getCards()) + " " + game.getPlayerHand().getTotal());
    }

    private String formatHand(List<Integer> cards) {
        return cards.toString();
    }
}
