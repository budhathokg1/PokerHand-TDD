public class Card {
    private int value;
    private char suit;

    public Card(String input) {
        suit = input.charAt(1);
        char cardCharVAlue = input.charAt(0);
        if(Character.isDigit(cardCharVAlue))
            value = Character.getNumericValue(cardCharVAlue);
        else{
            switch (cardCharVAlue){
                case 'T':
                    value = 10;
                    break;
                case 'J':
                    value = 11;
                    break;
                case 'Q':
                    value = 12;
                    break;
                case 'K':
                    value = 13;
                    break;
                case 'A':
                    value = 14;
                    break;
            }
        }
    }

    public int getValue() {

        return value;
    }

    public char getSuit() {

        return suit;
    }
}

