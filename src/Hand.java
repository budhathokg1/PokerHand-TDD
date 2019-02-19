import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    private int combination;
    private int highCard;

    public Hand(String[] input) {
        cards = new ArrayList<>(5);
        for (int i = 0; i < input.length; i++)
            cards.add(new Card(input[i]));
        sort(cards);
    }

    private void sort(ArrayList<Card> cards) {
        for (int i = 0; i < this.cards.size(); i++) {
            int indexOfMin = i;
            int min = getValueAt(i);
            for (int j = i+1; j < this.cards.size(); j++) {
                if(getValueAt(j) < min) {
                    min = getValueAt(j);
                    indexOfMin = j;
                }

            }
            Card temp = getCard(i);
            this.cards.set(i, getCard(indexOfMin));
            this.cards.set(indexOfMin, temp);
        }
    }


    public char getSuitAt(int card) {

        return getCard(card).getSuit();
    }

    public int getValueAt(int card) {
        return getCard(card).getValue();
    }

    public Card getCard(int card) {
        return cards.get(card);
    }


    public int getCombination() {
        return combination;
    }

    public int getHighCard() {
        return highCard;
    }

    public void setCombination(int combination) {
        this.combination = combination;
    }

    public void setHighCard(int highCard) {
        this.highCard = highCard;
    }
}
