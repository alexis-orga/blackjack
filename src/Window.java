import java.awt.*;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window extends Frame {
    private GameLogic game;
    private JLabel lblAccount, lblBet, lblPlayerHand, lblDealerHand, lblStatus;
    private JButton btnHit, btnStand, btnNewGame, btnBet5, btnBet10, btnBet20, btnBet50;
    private JPanel panelPlayerHand, panelDealerHand;



    public Window() {
        game = new GameLogic();

        setTitle("Blackjack by me");
        setSize(400, 400);
        setLayout(new GridLayout(6, 1));

        lblAccount = new JLabel("Account: $", SwingConstants.CENTER);
        lblBet = new JLabel("Bet: $", SwingConstants.CENTER);
        lblPlayerHand = new JLabel("Your hand", SwingConstants.CENTER);
        lblDealerHand = new JLabel("Dealer hand", SwingConstants.CENTER);
        lblStatus = new JLabel("", SwingConstants.CENTER);

        btnBet5 = new JButton("Bet 5$");
        btnBet10 = new JButton("Bet 10$");
        btnBet20 = new JButton("Bet 20$");
        btnBet50 = new JButton("Bet 50$");
        btnHit = new JButton("Hit");
        btnStand = new JButton("Stand");
        btnNewGame = new JButton("New Game");

        lblAccount.setFont(new Font("ARIAL", Font.ITALIC, 16));
        lblBet.setFont(new Font("ARIAL", Font.ITALIC, 16));
        lblPlayerHand.setFont(new Font("Arial", Font.BOLD, 16));
        lblDealerHand.setFont(new Font("Arial", Font.BOLD, 16));
        lblStatus.setFont(new Font("Arial", Font.BOLD, 18));
        setBackground(Color.GREEN);
        panelDealerHand = new JPanel();
        panelDealerHand.setLayout(new FlowLayout());
        panelDealerHand.setBackground(Color.GREEN);

        panelPlayerHand = new JPanel();
        panelPlayerHand.setLayout(new FlowLayout());
        panelPlayerHand.setBackground(Color.GREEN);

        add(panelDealerHand);
        add(panelPlayerHand);

        add(lblAccount);
        add(lblBet);
        add(lblDealerHand);
        add(lblPlayerHand);
        add(lblStatus);


        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(2, 4, 10, 10));
        btnPanel.setBackground(Color.GREEN);

        btnPanel.add(btnBet5);
        btnPanel.add(btnBet10);
        btnPanel.add(btnBet20);
        btnPanel.add(btnBet50);
        btnPanel.add(btnHit);
        btnPanel.add(btnStand);
        btnPanel.add(btnNewGame);
        add(btnPanel);

        btnBet5.addActionListener(e -> placeBet(5));
        btnBet10.addActionListener(e -> placeBet(10));
        btnBet20.addActionListener(e -> placeBet(20));
        btnBet50.addActionListener(e -> placeBet(50));
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

        panelDealerHand.removeAll();
        panelPlayerHand.removeAll();

        for (Integer card : game.getDealerHand().getCards()) {
            panelDealerHand.add(new JLabel(loadCardImage(card)));
        }
        for (Integer card : game.getPlayerHand().getCards()) {
            panelPlayerHand.add(new JLabel(loadCardImage(card)));
        }

        panelDealerHand.revalidate();
        panelDealerHand.repaint();
        panelPlayerHand.revalidate();
        panelPlayerHand.repaint();
    }

    private ImageIcon loadCardImage(int value) {
        String imagePath = "assets/" + value + ".png";
        return new ImageIcon(imagePath);
    }
}
