import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Integer> cards = new ArrayList<>();

        public void addCard(int card) {
            cards.add(card);
        }
    
        public List<Integer> getCards() {
            return cards;
        }
    
        public int getTotal() {
            int total = 0;
            int aceCount = 0;
            for (int card : cards) {
                if (card > 10) {
                    total += 10;
                } else if (card == 1) {
                total += 11;
                aceCount ++;
            } else {
                total += card;
            }
        }
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount --;
        }
        return total;
    }
    public String toString() {
        return cards.toString();
    }
}
