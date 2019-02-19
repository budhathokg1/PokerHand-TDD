public class PokerHands {
    public Hand player1, player2;
    private int numberOfCards = 5;
    private int winnerCombination;

    public PokerHands(String input) {
        String blackPlayer = input.substring(input.indexOf(":") + 1, input.indexOf("White"));
        String whitePlayer = input.substring(input.lastIndexOf(":") + 1);
        String [] cardsOfBlackPlayer = blackPlayer.trim().split(" ");
        String [] cardsOfWhitePlayer = whitePlayer.trim().split(" ");
        player1 = new Hand(cardsOfBlackPlayer);
        player2  = new Hand(cardsOfWhitePlayer);
    }

    public void evaluateHand() {
        decideCombination(player1);
        decideCombination(player2);
    }

    private void decideCombination(Hand player) {

        if(hasStraightFlush(player)) {
            player.setCombination(9);
            player.setHighCard(player.getValueAt(4));
        }
        else if(hasFourOfAKind(player)) {
            player.setCombination(8);
            player.setHighCard(player.getValueAt(1));
        }
        else if(hasFullHouse(player)) {
            player.setCombination(7);
            player.setHighCard(player.getValueAt(2));
        }
        else if(hasFlush(player)) {
            player.setCombination(6);
            player.setHighCard(player.getValueAt(4));
        }
        else if(hasStraight(player)) {
            player.setCombination(5);
            player.setHighCard(player.getValueAt(4));
        }
        else if(hasThreeOfAKind(player)){
            player.setCombination(4);
            player.setHighCard(player.getValueAt(2));
        }
        else if(hasTwoPair(player)){
            player.setCombination(3);
            if(isPair(player.getCard(0), player.getCard(1)) && isPair(player.getCard(2), player.getCard(3)))
                player.setHighCard(player.getValueAt(4));
            else if(isPair(player.getCard(1), player.getCard(2)) && isPair(player.getCard(3), player.getCard(4)))
                player.setHighCard(player.getValueAt(0));
            else
                player.setHighCard(player.getValueAt(2));

        }
        else if(hasPair(player)) {
           player.setCombination(2);
           if(isPair(player.getCard(3), player.getCard(4)))
                player.setHighCard(player.getValueAt(2));
           else
               player.setHighCard(player.getValueAt(4));
        }
        else {
            player.setCombination(1);
            player.setHighCard(player.getValueAt(4));
        }

    }

    private boolean hasStraightFlush(Hand player) {
        return (hasStraight(player) && hasFlush(player));
    }

    private boolean hasFullHouse(Hand player) {
        return ((isThreeOfAKind(player.getCard(0), player.getCard(1), player.getCard(2)) && isPair(player.getCard(3),player.getCard(4))) ||
                (isPair(player.getCard(0), player.getCard(1)) && isThreeOfAKind(player.getCard(2), player.getCard(3), player.getCard(4))));
    }


    private boolean hasFourOfAKind(Hand player) {
        for (int i = 0; i < numberOfCards -3 ; i++) {
            if(isFourOfAKind(player.getCard(i), player.getCard(i+1), player.getCard(i+2), player.getCard(i+3)))
                return true;

        }
        return false;
    }

    private boolean isFourOfAKind(Card card1, Card card2, Card card3, Card card4) {
        return card1.getValue() == card2.getValue() && card2.getValue() == card3.getValue() && card3.getValue() == card4.getValue();
    }

    private boolean hasFlush(Hand player) {
        for (int i = 0; i < numberOfCards - 1; i++) {
            if(player.getSuitAt(i) != player.getSuitAt(i+1))
                return false;
        }
        return true;
    }

    private boolean hasStraight(Hand player) {
        for (int i = 0; i < numberOfCards - 1; i++) {
            if(Math.abs(player.getValueAt(i) - player.getValueAt(i+1)) != 1){
                return false;
            }

        }
        return true;
    }

    private boolean hasThreeOfAKind(Hand player) {
        for (int i = 0; i < numberOfCards -2 ; i++) {
            if(isThreeOfAKind(player.getCard(i), player.getCard(i+1), player.getCard(i+2)))
                return true;

        }
        return false;
    }

    private boolean isThreeOfAKind(Card card1, Card card2, Card card3) {
        return card1.getValue() == card2.getValue() && card2.getValue() == card3.getValue();
    }

    private boolean hasTwoPair(Hand player) {
        for (int i = 0; i < numberOfCards - 1; i++) {
            if(isPair(player.getCard(i), player.getCard(i+1))) {
                for (int j = i + 2; j < numberOfCards - 1; j++) {
                    if (isPair(player.getCard(j), player.getCard(j + 1)))
                        return true;

                }
            }
        }
        return false;
    }

    private boolean hasPair(Hand player) {
        for (int i = 0; i < numberOfCards - 1; i++) {
            if(isPair(player.getCard(i), player.getCard(i+1)))
                return true;
        }
        return false;
    }

    private boolean isPair(Card card1, Card card2) {
        return card1.getValue() == card2.getValue();
    }

    public String getWinner() {
        if(player1.getCombination() == player2.getCombination()) {
            if(player1.getHighCard() == player2.getHighCard())
                return settleTie();
            else
                return player1.getHighCard() > player2.getHighCard() ? "Black wins" : "White wins";
        }
        else
            return player1.getCombination() > player2.getCombination() ? "Black wins" : "White wins";
    }

    private String settleTie() {
        for (int i = 0; i < numberOfCards; i++) {
            if (player1.getValueAt(i) != player2.getValueAt(i))
                return player1.getValueAt(i) > player2.getValueAt(i) ? "Black wins" : "White wins";
        }
        return "Tie";
    }


    public int getWinnerCombination() {
        if(player1.getCombination() == player2.getCombination()) {
            return 1;
        }
        else
            return player1.getCombination() > player2.getCombination() ? player1.getCombination() : player2.getCombination();

    }

    public String getWinnerCombinationName() {
        String combinationName = "";
        if (getWinner() != "Tie") {
            switch (getWinnerCombination()) {
                case 1:
                    combinationName = "high card";
                    break;
                case 2:
                    combinationName = "pair";
                    break;
                case 3:
                    combinationName = "two pair";
                    break;
                case 4:
                    combinationName = "three of a kind";
                    break;
                case 5:
                    combinationName = "straight";
                    break;
                case 6:
                    combinationName = "flush";
                    break;
                case 7:
                    combinationName = "full house";
                    break;
                case 8:
                    combinationName = "four of a kind";
                    break;
                case 9:
                    combinationName = "straight flush";
                    break;
            }
            return combinationName;

        }
        return combinationName;

    }

}
