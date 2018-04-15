import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class TestRussianBank extends MyTestCase {
    public TestRussianBank() {
    }

    public static void main(String [] args) {
        new TestRussianBank().availableCardsStart();
        new TestRussianBank().moveCardFromReserve();
        new TestRussianBank().moveCardFromTableau();
    }

    public void availableCardsStart() {
        Board bd = new Board();
        bd.firstPlayer = new Player(quotes.RedQuote.getInstance());
        bd.secondPlayer = new Player(quotes.BlueQuote.getInstance());
        super.assertEqual(Deck.isShuffled(bd.firstPlayer.fullDeck), false);
        super.assertEqual(Deck.isShuffled(bd.secondPlayer.fullDeck), false);
        bd.firstPlayer.shuffleDeck();
        bd.secondPlayer.shuffleDeck();
        super.assertEqual(Deck.isShuffled(bd.firstPlayer.fullDeck), true);
        super.assertEqual(Deck.isShuffled(bd.secondPlayer.fullDeck), true);
        bd.firstPlayer.createHandReserve();
        bd.secondPlayer.createHandReserve();
        super.assertEqual(bd.firstPlayer.hand.cards.size(), 39L);
        super.assertEqual(bd.secondPlayer.hand.cards.size(), 39L);
        bd.fillInitialTableau();
        super.assertEqual(bd.firstPlayer.hand.cards.size(), 35L);
        super.assertEqual(bd.secondPlayer.hand.cards.size(), 35L);
        bd.fillInitialFoundations();
        bd.setFirstTurn();

        long toVar_4 = 8L;

        for (Long i = 1L; i <= toVar_4; i++) {
            super.assertEqual(((Object) ((Deck) Utils.get(bd.tableau, i)).getTopCard().status),
                quotes.Face_UpQuote.getInstance());
            super.assertEqual(((Deck) Utils.get(bd.tableau, i)).cards.size(), 1L);
            super.assertEqual(Deck.isEmpty(
                    ((Deck) Utils.get(bd.foundations, i)).cards), true);
        }

        super.assertEqual(((Object) bd.firstPlayer.reserve.getTopCard().status),
            quotes.Face_UpQuote.getInstance());
        super.assertEqual(((Object) bd.secondPlayer.reserve.getTopCard().status),
            quotes.Face_UpQuote.getInstance());
        super.assertEqual(Deck.isEmpty(bd.firstPlayer.waste.cards), true);
        super.assertEqual(Deck.isEmpty(bd.secondPlayer.waste.cards), true);

        long toVar_5 = bd.secondPlayer.reserve.cards.size();

        for (Long i = 2L; i <= toVar_5; i++) {
            super.assertEqual(((Object) ((Card) Utils.get(
                    bd.firstPlayer.reserve.cards, i)).status),
                quotes.Face_DownQuote.getInstance());
            super.assertEqual(((Object) ((Card) Utils.get(
                    bd.secondPlayer.reserve.cards, i)).status),
                quotes.Face_DownQuote.getInstance());
        }

        super.assertEqual(bd.firstPlayer.reserve.cards.size(), 13L);
        super.assertEqual(bd.secondPlayer.reserve.cards.size(), 13L);

        return;
    }
    
    public void moveCardFromReserve() {
        Board bd = new Board();
        Card cd1 = new Card(7L, quotes.ClubsQuote.getInstance());
        Card cd2 = new Card(1L, quotes.SpadesQuote.getInstance());
        Card cd3 = new Card(6L, quotes.HeartsQuote.getInstance());
        Card cd4 = new Card(5L, quotes.DiamondsQuote.getInstance());
        Card cd5 = new Card(2L, quotes.SpadesQuote.getInstance());
        Card cd6 = new Card(3L, quotes.SpadesQuote.getInstance());
        Card cd7 = new Card(4L, quotes.DiamondsQuote.getInstance());
        bd.firstPlayer = new Player(quotes.RedQuote.getInstance());
        bd.secondPlayer = new Player(quotes.BlueQuote.getInstance());
        bd.turn = quotes.FirstPlayerQuote.getInstance();
        Utils.mapSeqUpdate(bd.tableau, 1L, new Deck());
        bd.addCardToTableau(1L, cd1);
        Utils.mapSeqUpdate(bd.foundations, 1L, new Deck());
        bd.firstPlayer.reserve.addIntoDeckFront(cd5);
        bd.firstPlayer.reserve.addIntoDeckFront(cd4);
        bd.firstPlayer.reserve.addIntoDeckFront(cd3);
        bd.firstPlayer.reserve.addIntoDeckFront(cd2);
        bd.firstPlayer.reserve.turnUpTopCard();
        bd.secondPlayer.reserve.addIntoDeckFront(cd6);
        bd.secondPlayer.reserve.turnUpTopCard();
        bd.secondPlayer.waste.addIntoDeckFront(cd7);
        bd.secondPlayer.waste.turnUpTopCard();
        super.assertEqual(bd.moveCardFromReserveToFoundation(1L), true);
        super.assertEqual(bd.moveCardFromReserveToFoundation(1L), false);
        super.assertEqual(bd.moveCardFromReserveToTableau(1L), true);
        super.assertEqual(bd.moveCardFromReserveToTableau(1L), false);
        super.assertEqual(bd.moveCardFromReserveToReserve(), false);
        super.assertEqual(bd.moveCardFromReserveToWaste(), true);
        super.assertEqual(bd.moveCardFromReserveToWaste(), false);
        super.assertEqual(bd.moveCardFromReserveToReserve(), true);
    }

    public void moveCardFromTableau() {
        Board bd = new Board();
        Card cd1 = new Card(7L, quotes.ClubsQuote.getInstance());
        Card cd2 = new Card(6L, quotes.HeartsQuote.getInstance());
        Card cd3 = new Card(1L, quotes.SpadesQuote.getInstance());
        Card cd4 = new Card(8L, quotes.ClubsQuote.getInstance());
        Card cd5 = new Card(5L, quotes.HeartsQuote.getInstance());
        Card cd6 = new Card(7L, quotes.SpadesQuote.getInstance());
        bd.firstPlayer = new Player(quotes.RedQuote.getInstance());
        bd.secondPlayer = new Player(quotes.BlueQuote.getInstance());
        bd.turn = quotes.FirstPlayerQuote.getInstance();
        Utils.mapSeqUpdate(bd.tableau, 1L, new Deck());
        bd.addCardToTableau(1L, cd1);
        bd.addCardToTableau(1L, cd2);
        Utils.mapSeqUpdate(bd.tableau, 2L, new Deck());
        bd.addCardToTableau(2L, cd3);
        Utils.mapSeqUpdate(bd.tableau, 3L, new Deck());
        bd.addCardToTableau(3L, cd6);
        Utils.mapSeqUpdate(bd.foundations, 1L, new Deck());
        bd.secondPlayer.reserve.addIntoDeckFront(cd4);
        bd.secondPlayer.reserve.turnUpTopCard();
        bd.secondPlayer.waste.addIntoDeckFront(cd5);
        bd.secondPlayer.waste.turnUpTopCard();
        super.assertEqual(bd.moveCardFromTableauToWaste(2L), false);
        super.assertEqual(bd.moveCardFromTableauToTableau(2L, 3L), false);
        super.assertEqual(bd.moveCardFromTableauToFoundation(2L, 1L), true);
        super.assertEqual(bd.moveCardFromTableauToFoundation(1L, 1L), false);
        super.assertEqual(bd.moveCardFromTableauToTableau(1L, 3L), true);
        super.assertEqual(bd.moveCardFromTableauToReserve(3L), false);
        super.assertEqual(bd.moveCardFromTableauToReserve(1L), true);
        super.assertEqual(bd.moveCardFromTableauToWaste(3L), true);
    }

    public void endTurn() {
        return;
    }

    public String toString() {
        return "TestRussianBank{}";
    }
}
