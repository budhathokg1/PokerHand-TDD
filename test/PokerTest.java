import org.junit.*;

import static org.junit.Assert.assertEquals;

public class PokerTest {

    @Test
    public void givenACardInput() throws Exception {
        String hand = "TD";
        Card sut = new Card(hand);
        assertEquals(10, sut.getValue());
        assertEquals('D', sut.getSuit());
    }

    @Test
    public void givenAHand() throws Exception {
        String [] hand = {"8C", "3D", "4D", "5D", "8D"};
        Hand sut = new Hand(hand);

        assertEquals(3, sut.getValueAt(0));
        assertEquals('D', sut.getSuitAt(0));
        assertEquals(4, sut.getValueAt(1));
        assertEquals('D', sut.getSuitAt(1));
        assertEquals(5, sut.getValueAt(2));
        assertEquals('D', sut.getSuitAt(2));
        assertEquals(8, sut.getValueAt(3));
        assertEquals('C', sut.getSuitAt(3));
        assertEquals(8, sut.getValueAt(4));
        assertEquals('D', sut.getSuitAt(4));


    }

    @Test
    public void givenPokerHandCallHasHighCard() {
        String hand = "Black: 2H 5D 6S JC KD White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals(1, sut.player1.getCombination());
        assertEquals(13, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasPair() {
        String hand = "Black: 2H 4D 6S KC KD White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals(2, sut.player1.getCombination());
        assertEquals(6, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasTwoPair() {
        String hand = "Black: 5H 5D 6S KC KD White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals(3, sut.player1.getCombination());
        assertEquals(6, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasThreeOfAKind() {
        String hand = "Black: 6H KH 5C KC KD White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals(4, sut.player1.getCombination());
        assertEquals(13, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasStraight() {
        String hand = "Black: KS TS JD QC AS White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals(5, sut.player1.getCombination());
        assertEquals(14, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasFlush() {
        String hand = "Black: 2D 3D 4D 5D AD White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals(6, sut.player1.getCombination());
        assertEquals(14, sut.player1.getHighCard());
    }


    @Test
    public void givenPokerHandCallHasFullHouse() {
        String hand = "Black: 2D 2S 2C 8H 8D White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals(7, sut.player1.getCombination());
        assertEquals(2, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasFourOfAKind() {
        String hand = "Black: 2D 2S 2C 2H AD White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals(8, sut.player1.getCombination());
        assertEquals(2, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasStraightFlush() {
        String hand = "Black: JH QH TH KH AH White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals(9, sut.player1.getCombination());
        assertEquals(14, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandWhenCalledScoreThenReturnWinner() {
        String hand = "Black: JH QH TH KH AH White: 2H 3H 4H 5H 6H";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals("Black wins", sut.getWinner());
    }

    @Test
    public void givenPokerHandWhenCalledSettleTieThenReturnTie() {
        String hand = "Black: JH QH TH KH AH White: JH QH TH KH AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals("Tie", sut.getWinner());
    }

    @Test
    public void givenPokerHandWhenCalledWinnerCombinationThenReturnWinnerCombination() {
        String hand = "Black: JH QH TH KH AH White: JH 2H TH KH AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals(9, sut.getWinnerCombination());
    }


    @Test
    public void givenStraightFlushPokerHandWhenCalledWinnerCombinationNameThenReturnStraightFlush() {
        String hand = "Black: JH QH TH KH AH White: JH 2H TH KH AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals("straight flush", sut.getWinnerCombinationName());

    }

    @Test
    public void givenFlushPokerHandWhenCalledWinnerCombinationNameThenReturnFlush() {
        String hand = "Black: 2S 2H TH KH AH White: JH 2H TH KH AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals("flush", sut.getWinnerCombinationName());

    }

    @Test
    public void givenPairPokerHandWhenCalledWinnerCombinationNameThenReturnPair() {
        String hand = "Black: 2S 2H TH KH AH White: JH 2D TH KH AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals("pair", sut.getWinnerCombinationName());

    }

    @Test
    public void givenTwoPairPokerHandWhenCalledWinnerCombinationNameThenReturnTwoPair() {
        String hand = "Black: 2S 2H TH KH KH White: JH 2D TH KH AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals("two pair", sut.getWinnerCombinationName());

    }

    @Test
    public void givenFullHousePokerHandWhenCalledWinnerCombinationNameThenReturnFullHouse() {
        String hand = "Black: 2S 2H 2D KH KH White: JH 2D TH KH AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals("full house", sut.getWinnerCombinationName());

    }

    @Test
    public void givenThreeOfAKindPokerHandWhenCalledWinnerCombinationNameThenReturnThreeOfAKind() {
        String hand = "Black: 2S 2H 2D KH QH White: JH 2D TH KH AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals("three of a kind", sut.getWinnerCombinationName());

    }


    @Test
    public void givenFourOfAKindPokerHandWhenCalledWinnerCombinationNameThenReturnFourOfAKind() {
        String hand = "Black: 2S 2D 2H 2C QH White: JH 2D TH KH AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals("four of a kind", sut.getWinnerCombinationName());

    }

    @Test
    public void givenStraightPokerHandWhenCalledWinnerCombinationNameThenReturnStraight() {
        String hand = "Black: JH QC TH KH AH White: JH 2H TD KH AH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals("straight", sut.getWinnerCombinationName());

    }

    @Test
    public void givenHighCardPokerHandWhenCalledWinnerCombinationNameThenReturnHighCard() {
        String hand = "Black: JH QC 9H KH AH White: JH 2H TD KH 5H";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals("high card", sut.getWinnerCombinationName());

    }


    @Test
    public void givenPokerHandsCallGetWinnerHighCardThenReturnWinnerHighCard() {
        String hand = "Black: 3H 4D 5S 9C KD White: 3C 4H 5S 9C KH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        assertEquals("", sut.getWinnerHighCard());
    }

    @Test
    public void givenPokerHandCallWinnerFunctionThenDisplayResult() {
        String hand = "Black: 3H 4D 5S 9C KD White: 3C 4H 5S 9C KH";
        PokerHands sut = new PokerHands(hand);
        sut.evaluateHand();
        System.out.println(sut.getWinner()+ "  " + sut.getWinnerCombinationName() + " " + sut.getWinnerHighCard());
    }


}