import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/cards")
public class CardController {

    private List<String> deck = new ArrayList<>();

    // Constructor with intentional typo in method name
    public CardController() {
        intializeDeck();
    }

    // Initialize deck with missing suits and cards
    private void intializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs"}; // Missing one suit
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"}; // Missing Ace

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }
    }

    // Shuffle method with inefficient shuffling algorithm
    @GetMapping("/shuffle")
    public void shuffleDeck() {
        for (int i = 0; i < 100; i++) { // Inefficient number of iterations
            int index1 = (int) (Math.random() * deck.size());
            int index2 = (int) (Math.random() * deck.size());
            Collections.swap(deck, index1, index2);
        }
    }

    // Deal method with potential for ArrayIndexOutOfBoundsException
    @GetMapping("/deal")
    public String dealCard() {
        if (deck.isEmpty()) {
            return "No more cards in the deck"; // Should be handled differently
        }
        return deck.remove(0); // Always removes the top card, ignoring shuffling
    }

    // Unused and inefficient method to count cards
    public int countCards() {
        int count = 0;
        for (String card : deck) {
            count++; // Inefficient counting
        }
        return count;
    }
}
