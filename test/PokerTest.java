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

        assertEquals(3, sut.getValue(0));
        assertEquals('D', sut.getSuit(0));
        assertEquals(4, sut.getValue(1));
        assertEquals('D', sut.getSuit(1));
        assertEquals(5, sut.getValue(2));
        assertEquals('D', sut.getSuit(2));
        assertEquals(8, sut.getValue(3));
        assertEquals('C', sut.getSuit(3));
        assertEquals(8, sut.getValue(4));
        assertEquals('D', sut.getSuit(4));


    }

    @Test
    public void givenPokerHandCallHasHighCard() {
        String hand = "Black: 2H 5D 6S JC KD White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.inspectHand();
        assertEquals(1, sut.player1.getCombination());
        assertEquals(13, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasPair() {
        String hand = "Black: 2H 4D 6S KC KD White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.inspectHand();
        assertEquals(2, sut.player1.getCombination());
        assertEquals(13, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasTwoPair() {
        String hand = "Black: 6H 5D 6S KC KD White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.inspectHand();
        assertEquals(3, sut.player1.getCombination());
        assertEquals(13, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasThreeOfAKind() {
        String hand = "Black: 6H KH 5C KC KD White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.inspectHand();
        assertEquals(4, sut.player1.getCombination());
        assertEquals(13, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasStraight() {
        String hand = "Black: KS TS JD QC AS White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.inspectHand();
        assertEquals(5, sut.player1.getCombination());
        assertEquals(14, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasFlush() {
        String hand = "Black: 2D 3D 4D 5D AD White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.inspectHand();
        assertEquals(6, sut.player1.getCombination());
        assertEquals(14, sut.player1.getHighCard());
    }


    @Test
    public void givenPokerHandCallHasFullHouse() {
        String hand = "Black: 2D 2S 2C 8H 8D White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.inspectHand();
        assertEquals(7, sut.player1.getCombination());
        assertEquals(2, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasFourOfAKind() {
        String hand = "Black: 2D 2S 2C 2H AD White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.inspectHand();
        assertEquals(8, sut.player1.getCombination());
        assertEquals(14, sut.player1.getHighCard());
    }

    @Test
    public void givenPokerHandCallHasStraightFlush() {
        String hand = "Black: JH QH TH KH AH White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.inspectHand();
        assertEquals(9, sut.player1.getCombination());
        assertEquals(14, sut.player1.getHighCard());
    }


    @Test
    public void givenPokerHandWhenCalledScoreThenReturnWhoWins() {
        String hand = "Black: JH QH TH KH AH White: 2C 3H 4S 8C AH";
        PokerHands sut = new PokerHands(hand);
        sut.inspectHand();
        assertEquals(9, sut.player1.getCombination());
        assertEquals(14, sut.player1.getHighCard());
    }
}