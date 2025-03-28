import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Integer> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (int suits = 0; suits < 4; suits++){
            for (int value = 1; value <= 13; value++) {
                cards.add(value);
            }
        }
        Collections.shuffle(cards);
    }

    public int drawCard() {
        if (cards.isEmpty()) {
            System.out.println("All cards have been drawn");
            refillDeck();
        } 
        return cards.remove(0);
    }

    private void refillDeck() {
        cards.clear();
        for (int suits = 0; suits < 4; suits++) {
            for (int value = 1; value <= 13; value++) {
                cards.add(value);
            
            }
        }
        Collections.shuffle(cards);
    }
}
