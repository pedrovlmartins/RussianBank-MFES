import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Board {
    public Player firstPlayer;
    public Player secondPlayer;
    public VDMSeq foundations = SeqUtil.seq();
    public VDMSeq tableau = SeqUtil.seq();
    public Object turn = null;

    public Board(final Player p1, final Player p2) {
        cg_init_Board_1(p1, p2);
    }

    public Board() {
    }

    public void cg_init_Board_1(final Player p1, final Player p2) {
        firstPlayer = p1;
        secondPlayer = p2;

        return;
    }

    public void addCardToTableau(final Number i, final Card c) {
        ((Deck) Utils.get(tableau, i)).addIntoDeckFront(c);
    }

    public void addCardToFoundation(final Number i, final Card c) {
        ((Deck) Utils.get(foundations, i)).addIntoDeckFront(c);
    }

    public Board createStartingBoard() {
        firstPlayer = new Player(quotes.RedQuote.getInstance());
        secondPlayer = new Player(quotes.BlueQuote.getInstance());
        firstPlayer.shuffleDeck();
        secondPlayer.shuffleDeck();
        firstPlayer.createHandReserve();
        secondPlayer.createHandReserve();
        fillInitialTableau();
        fillInitialFoundations();
        setFirstTurn();

        return this;
    }

    public Board createTestingBoard() {
        firstPlayer = new Player(quotes.RedQuote.getInstance());
        secondPlayer = new Player(quotes.BlueQuote.getInstance());

        return this;
    }

    public void fillInitialTableau() {
        long toVar_1 = 4L;

        for (Long i = 1L; i <= toVar_1; i++) {
            Utils.mapSeqUpdate(tableau, i,
                new Deck(SeqUtil.seq(firstPlayer.hand.getTopCard())));
            ((Deck) Utils.get(tableau, i)).turnUpTopCard();
            firstPlayer.hand.removeTopCard();
        }

        long toVar_2 = 8L;

        for (Long i = 5L; i <= toVar_2; i++) {
            Utils.mapSeqUpdate(tableau, i,
                new Deck(SeqUtil.seq(secondPlayer.hand.getTopCard())));
            ((Deck) Utils.get(tableau, i)).turnUpTopCard();
            secondPlayer.hand.removeTopCard();
        }
    }

    public void fillInitialFoundations() {
        long toVar_3 = 8L;

        for (Long i = 1L; i <= toVar_3; i++) {
            Utils.mapSeqUpdate(foundations, i, new Deck());
        }
    }

    public void setFirstTurn() {
        Card topReserve1 = firstPlayer.reserve.getTopCard();
        Card topReserve2 = secondPlayer.reserve.getTopCard();

        if (Utils.equals(topReserve1.compare(topReserve2), -1L)) {
            turn = quotes.FirstPlayerQuote.getInstance();
        } else if (Utils.equals(topReserve1.compare(topReserve2), 1L)) {
            turn = quotes.SecondPlayerQuote.getInstance();
        } else if (Utils.equals(((Deck) Utils.get(tableau, 1L)).getTopCard()
                                     .compare(((Deck) Utils.get(tableau, 5L)).getTopCard()),
                    -1L)) {
            turn = quotes.FirstPlayerQuote.getInstance();
        } else if (Utils.equals(((Deck) Utils.get(tableau, 1L)).getTopCard()
                                     .compare(((Deck) Utils.get(tableau, 5L)).getTopCard()),
                    1L)) {
            turn = quotes.SecondPlayerQuote.getInstance();
        } else if (Utils.equals(((Deck) Utils.get(tableau, 2L)).getTopCard()
                                     .compare(((Deck) Utils.get(tableau, 6L)).getTopCard()),
                    -1L)) {
            turn = quotes.FirstPlayerQuote.getInstance();
        } else if (Utils.equals(((Deck) Utils.get(tableau, 2L)).getTopCard()
                                     .compare(((Deck) Utils.get(tableau, 6L)).getTopCard()),
                    1L)) {
            turn = quotes.SecondPlayerQuote.getInstance();
        } else if (Utils.equals(((Deck) Utils.get(tableau, 3L)).getTopCard()
                                     .compare(((Deck) Utils.get(tableau, 7L)).getTopCard()),
                    -1L)) {
            turn = quotes.FirstPlayerQuote.getInstance();
        } else if (Utils.equals(((Deck) Utils.get(tableau, 3L)).getTopCard()
                                     .compare(((Deck) Utils.get(tableau, 7L)).getTopCard()),
                    1L)) {
            turn = quotes.SecondPlayerQuote.getInstance();
        } else if (Utils.equals(((Deck) Utils.get(tableau, 4L)).getTopCard()
                                     .compare(((Deck) Utils.get(tableau, 8L)).getTopCard()),
                    -1L)) {
            turn = quotes.FirstPlayerQuote.getInstance();
        } else if (Utils.equals(((Deck) Utils.get(tableau, 4L)).getTopCard()
                                     .compare(((Deck) Utils.get(tableau, 8L)).getTopCard()),
                    1L)) {
            turn = quotes.SecondPlayerQuote.getInstance();
        }
    }

    public void changePlayer(final Number numberCard) {
        if (Utils.equals(turn, quotes.FirstPlayerQuote.getInstance())) {
            if (!(Deck.isEmpty(firstPlayer.hand.cards))) {
                if (!(Deck.isEmpty(firstPlayer.waste.cards))) {
                    firstPlayer.waste.turnUpTopCard();
                }

                if (!(Utils.equals(numberCard, null))) {
                    firstPlayer.waste.addIntoDeckFront(((Card) Utils.get(
                            firstPlayer.hand.cards, numberCard)));
                    firstPlayer.waste.turnUpTopCard();
                    firstPlayer.hand.removeFromDeck(numberCard);
                }
            }

            turn = quotes.SecondPlayerQuote.getInstance();
        } else {
            if (!(Deck.isEmpty(secondPlayer.hand.cards))) {
                if (!(Deck.isEmpty(secondPlayer.waste.cards))) {
                    secondPlayer.waste.turnUpTopCard();
                }

                if (!(Utils.equals(numberCard, null))) {
                    secondPlayer.waste.addIntoDeckFront(((Card) Utils.get(
                            secondPlayer.hand.cards, numberCard)));
                    secondPlayer.waste.turnUpTopCard();
                    secondPlayer.hand.removeFromDeck(numberCard);
                }
            }

            turn = quotes.FirstPlayerQuote.getInstance();
        }
    }

    public Object isGameOver() {
        Boolean andResult_3 = false;

        if (Deck.isEmpty(firstPlayer.hand.cards)) {
            Boolean andResult_4 = false;

            if (Deck.isEmpty(firstPlayer.reserve.cards)) {
                if (Deck.isEmpty(firstPlayer.waste.cards)) {
                    andResult_4 = true;
                }
            }

            if (andResult_4) {
                andResult_3 = true;
            }
        }

        if (andResult_3) {
            return quotes.FirstPlayerQuote.getInstance();
        } else {
            Boolean andResult_5 = false;

            if (Deck.isEmpty(firstPlayer.hand.cards)) {
                Boolean andResult_6 = false;

                if (Deck.isEmpty(firstPlayer.reserve.cards)) {
                    if (Deck.isEmpty(firstPlayer.waste.cards)) {
                        andResult_6 = true;
                    }
                }

                if (andResult_6) {
                    andResult_5 = true;
                }
            }

            if (andResult_5) {
                return quotes.SecondPlayerQuote.getInstance();
            }
        }

        return null;
    }

    public Boolean moveCardFromTableauToFoundation(final Number i,
        final Number j) {
        if (Deck.isEmpty(((Deck) Utils.get(foundations, j)).cards)) {
            if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                    .getNumber(), 1L))) {
                return false;
            } else {
                ((Deck) Utils.get(foundations, j)).addIntoDeckFront(((Deck) Utils.get(
                        tableau, i)).getTopCard());
            }

            ((Deck) Utils.get(tableau, i)).removeTopCard();

            if (!(Deck.isEmpty(((Deck) Utils.get(tableau, i)).cards))) {
                ((Deck) Utils.get(tableau, i)).turnUpTopCard();
            }

            return true;
        } else {
            Boolean andResult_8 = false;

            if (Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                  .getSuit(),
                        ((Deck) Utils.get(foundations, j)).getTopCard().getSuit())) {
                if (Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                      .getNumber(),
                            ((Deck) Utils.get(foundations, j)).getTopCard()
                                 .getNumber().longValue() + 1L)) {
                    andResult_8 = true;
                }
            }

            if (andResult_8) {
                ((Deck) Utils.get(foundations, j)).turnUpTopCard();
                ((Deck) Utils.get(foundations, j)).addIntoDeckFront(((Deck) Utils.get(
                        tableau, i)).getTopCard());
                ((Deck) Utils.get(tableau, i)).removeTopCard();

                if (!(Deck.isEmpty(((Deck) Utils.get(tableau, i)).cards))) {
                    ((Deck) Utils.get(tableau, i)).turnUpTopCard();
                }

                return true;
            } else {
                return false;
            }
        }
    }

    public Boolean moveCardFromTableauToTableau(final Number i, final Number j) {
        if (Deck.isEmpty(((Deck) Utils.get(tableau, i)).cards)) {
            if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                    .getNumber(), 1L))) {
                return false;
            } else {
                ((Deck) Utils.get(tableau, j)).addIntoDeckFront(((Deck) Utils.get(
                        tableau, i)).getTopCard());
            }

            ((Deck) Utils.get(tableau, i)).removeTopCard();

            if (!(Deck.isEmpty(((Deck) Utils.get(tableau, i)).cards))) {
                ((Deck) Utils.get(tableau, i)).turnUpTopCard();
            }

            return true;
        } else {
            Boolean andResult_10 = false;

            if (!(Utils.equals(
                        ((Deck) Utils.get(tableau, i)).getTopCard().color,
                        ((Deck) Utils.get(tableau, j)).getTopCard().color))) {
                if (Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                      .getNumber(),
                            ((Deck) Utils.get(tableau, j)).getTopCard()
                                 .getNumber().longValue() - 1L)) {
                    andResult_10 = true;
                }
            }

            if (andResult_10) {
                ((Deck) Utils.get(tableau, j)).turnUpTopCard();
                ((Deck) Utils.get(tableau, j)).addIntoDeckFront(((Deck) Utils.get(
                        tableau, i)).getTopCard());
                ((Deck) Utils.get(tableau, i)).removeTopCard();

                if (!(Deck.isEmpty(((Deck) Utils.get(tableau, i)).cards))) {
                    ((Deck) Utils.get(tableau, i)).turnUpTopCard();
                }

                return true;
            } else {
                return false;
            }
        }
    }

    public Boolean moveCardFromTableauToWaste(final Number i) {
        if (Utils.equals(turn, quotes.FirstPlayerQuote.getInstance())) {
            Boolean andResult_11 = false;

            if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                    .getSuit(),
                        secondPlayer.waste.getTopCard().getSuit()))) {
                Boolean andResult_12 = false;

                if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                        .getNumber(),
                            secondPlayer.waste.getTopCard().getNumber()
                                                  .longValue() + 1L))) {
                    if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                            .getNumber(),
                                secondPlayer.waste.getTopCard().getNumber()
                                                      .longValue() - 1L))) {
                        andResult_12 = true;
                    }
                }

                if (andResult_12) {
                    andResult_11 = true;
                }
            }

            if (andResult_11) {
                return false;
            } else {
                if (!(Deck.isEmpty(secondPlayer.waste.cards))) {
                    secondPlayer.waste.turnUpTopCard();
                }

                secondPlayer.waste.addIntoDeckFront(((Deck) Utils.get(tableau, i)).getTopCard());
                ((Deck) Utils.get(tableau, i)).removeTopCard();

                if (!(Deck.isEmpty(((Deck) Utils.get(tableau, i)).cards))) {
                    ((Deck) Utils.get(tableau, i)).turnUpTopCard();
                }

                return true;
            }
        } else {
            Boolean andResult_13 = false;

            if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                    .getSuit(),
                        firstPlayer.waste.getTopCard().getSuit()))) {
                Boolean andResult_14 = false;

                if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                        .getNumber(),
                            firstPlayer.waste.getTopCard().getNumber()
                                                 .longValue() + 1L))) {
                    if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                            .getNumber(),
                                firstPlayer.waste.getTopCard().getNumber()
                                                     .longValue() - 1L))) {
                        andResult_14 = true;
                    }
                }

                if (andResult_14) {
                    andResult_13 = true;
                }
            }

            if (andResult_13) {
                return false;
            } else {
                if (!(Deck.isEmpty(firstPlayer.waste.cards))) {
                    firstPlayer.waste.turnUpTopCard();
                }

                firstPlayer.waste.addIntoDeckFront(((Deck) Utils.get(tableau, i)).getTopCard());
                ((Deck) Utils.get(tableau, i)).removeTopCard();

                if (!(Deck.isEmpty(((Deck) Utils.get(tableau, i)).cards))) {
                    ((Deck) Utils.get(tableau, i)).turnUpTopCard();
                }

                return true;
            }
        }
    }

    public Boolean moveCardFromTableauToReserve(final Number i) {
        if (Utils.equals(turn, quotes.FirstPlayerQuote.getInstance())) {
            Boolean andResult_15 = false;

            if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                    .getSuit(),
                        secondPlayer.reserve.getTopCard().getSuit()))) {
                Boolean andResult_16 = false;

                if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                        .getNumber(),
                            secondPlayer.reserve.getTopCard().getNumber()
                                                    .longValue() + 1L))) {
                    if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                            .getNumber(),
                                secondPlayer.reserve.getTopCard().getNumber()
                                                        .longValue() - 1L))) {
                        andResult_16 = true;
                    }
                }

                if (andResult_16) {
                    andResult_15 = true;
                }
            }

            if (andResult_15) {
                return false;
            } else {
                if (!(Deck.isEmpty(secondPlayer.reserve.cards))) {
                    secondPlayer.reserve.turnUpTopCard();
                }

                secondPlayer.reserve.addIntoDeckFront(((Deck) Utils.get(
                        tableau, i)).getTopCard());
                ((Deck) Utils.get(tableau, i)).removeTopCard();

                if (!(Deck.isEmpty(((Deck) Utils.get(tableau, i)).cards))) {
                    ((Deck) Utils.get(tableau, i)).turnUpTopCard();
                }

                return true;
            }
        } else {
            Boolean andResult_17 = false;

            if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                    .getSuit(),
                        firstPlayer.reserve.getTopCard().getSuit()))) {
                Boolean andResult_18 = false;

                if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                        .getNumber(),
                            firstPlayer.reserve.getTopCard().getNumber()
                                                   .longValue() + 1L))) {
                    if (!(Utils.equals(((Deck) Utils.get(tableau, i)).getTopCard()
                                            .getNumber(),
                                firstPlayer.reserve.getTopCard().getNumber()
                                                       .longValue() - 1L))) {
                        andResult_18 = true;
                    }
                }

                if (andResult_18) {
                    andResult_17 = true;
                }
            }

            if (andResult_17) {
                return false;
            } else {
                if (!(Deck.isEmpty(firstPlayer.reserve.cards))) {
                    firstPlayer.reserve.turnUpTopCard();
                }

                firstPlayer.reserve.addIntoDeckFront(((Deck) Utils.get(
                        tableau, i)).getTopCard());
                ((Deck) Utils.get(tableau, i)).removeTopCard();

                if (!(Deck.isEmpty(((Deck) Utils.get(tableau, i)).cards))) {
                    ((Deck) Utils.get(tableau, i)).turnUpTopCard();
                }

                return true;
            }
        }
    }

    public Boolean moveCardFromReserveToTableau(final Number i) {
        if (Utils.equals(turn, quotes.FirstPlayerQuote.getInstance())) {
            Boolean orResult_8 = false;

            if (Deck.isEmpty(firstPlayer.reserve.cards)) {
                orResult_8 = true;
            } else {
                Boolean orResult_9 = false;

                if (Utils.equals(
                            ((Deck) Utils.get(tableau, i)).getTopCard().color,
                            firstPlayer.reserve.getTopCard().color)) {
                    orResult_9 = true;
                } else {
                    orResult_9 = ((Deck) Utils.get(tableau, i)).getTopCard().number.longValue() <= firstPlayer.reserve.getTopCard().number.longValue();
                }

                orResult_8 = orResult_9;
            }

            if (orResult_8) {
                return false;
            } else {
                if (!(Deck.isEmpty(((Deck) Utils.get(tableau, i)).cards))) {
                    ((Deck) Utils.get(tableau, i)).turnUpTopCard();
                }

                ((Deck) Utils.get(tableau, i)).addIntoDeckFront(firstPlayer.reserve.getTopCard());
                firstPlayer.reserve.removeTopCard();

                if (!(Deck.isEmpty(firstPlayer.reserve.cards))) {
                    firstPlayer.reserve.turnUpTopCard();
                }

                return true;
            }
        } else {
            Boolean orResult_10 = false;

            if (Deck.isEmpty(secondPlayer.reserve.cards)) {
                orResult_10 = true;
            } else {
                Boolean orResult_11 = false;

                if (Utils.equals(
                            ((Deck) Utils.get(tableau, i)).getTopCard().color,
                            ((Deck) Utils.get(tableau, i)).getTopCard().color)) {
                    orResult_11 = true;
                } else {
                    orResult_11 = ((Deck) Utils.get(tableau, i)).getTopCard().number.longValue() <= secondPlayer.reserve.getTopCard().number.longValue();
                }

                orResult_10 = orResult_11;
            }

            if (orResult_10) {
                return false;
            } else {
                if (!(Deck.isEmpty(((Deck) Utils.get(tableau, i)).cards))) {
                    ((Deck) Utils.get(tableau, i)).turnUpTopCard();
                }

                ((Deck) Utils.get(tableau, i)).addIntoDeckFront(secondPlayer.reserve.getTopCard());
                secondPlayer.reserve.removeTopCard();

                if (!(Deck.isEmpty(secondPlayer.reserve.cards))) {
                    secondPlayer.reserve.turnUpTopCard();
                }

                return true;
            }
        }
    }

    public Boolean moveCardFromReserveToFoundation(final Number i) {
        if (Utils.equals(turn, quotes.FirstPlayerQuote.getInstance())) {
            if (Deck.isEmpty(((Deck) Utils.get(foundations, i)).cards)) {
                if (!(Utils.equals(firstPlayer.reserve.getTopCard().number, 1L))) {
                    return false;
                } else {
                    ((Deck) Utils.get(foundations, i)).addIntoDeckFront(firstPlayer.reserve.getTopCard());
                    firstPlayer.reserve.removeTopCard();

                    if (!(Deck.isEmpty(firstPlayer.reserve.cards))) {
                        firstPlayer.reserve.turnUpTopCard();
                    }

                    return true;
                }
            } else {
                Boolean orResult_13 = false;

                if (!(Utils.equals(((Deck) Utils.get(foundations, i)).getTopCard()
                                        .getSuit(),
                            firstPlayer.reserve.getTopCard().getSuit()))) {
                    orResult_13 = true;
                } else {
                    orResult_13 = !(Utils.equals(((Deck) Utils.get(
                                foundations, i)).getTopCard().number,
                            firstPlayer.reserve.getTopCard().number.longValue() -
                            1L));
                }

                if (orResult_13) {
                    return false;
                } else {
                    if (!(Deck.isEmpty(((Deck) Utils.get(foundations, i)).cards))) {
                        ((Deck) Utils.get(foundations, i)).turnUpTopCard();
                    }

                    ((Deck) Utils.get(foundations, i)).addIntoDeckFront(firstPlayer.reserve.getTopCard());
                    firstPlayer.reserve.removeTopCard();

                    if (!(Deck.isEmpty(firstPlayer.reserve.cards))) {
                        firstPlayer.reserve.turnUpTopCard();
                    }

                    return true;
                }
            }
        } else {
            if (Deck.isEmpty(((Deck) Utils.get(foundations, i)).cards)) {
                if (!(Utils.equals(secondPlayer.reserve.getTopCard().number, 1L))) {
                    return false;
                } else {
                    ((Deck) Utils.get(foundations, i)).addIntoDeckFront(secondPlayer.reserve.getTopCard());
                    secondPlayer.reserve.removeTopCard();

                    if (!(Deck.isEmpty(secondPlayer.reserve.cards))) {
                        secondPlayer.reserve.turnUpTopCard();
                    }

                    return true;
                }
            } else {
                Boolean orResult_14 = false;

                if (!(Utils.equals(((Deck) Utils.get(foundations, i)).getTopCard()
                                        .getSuit(),
                            secondPlayer.reserve.getTopCard().getSuit()))) {
                    orResult_14 = true;
                } else {
                    orResult_14 = !(Utils.equals(((Deck) Utils.get(
                                foundations, i)).getTopCard().number,
                            secondPlayer.reserve.getTopCard().number.longValue() -
                            1L));
                }

                if (orResult_14) {
                    return false;
                } else {
                    if (!(Deck.isEmpty(((Deck) Utils.get(foundations, i)).cards))) {
                        ((Deck) Utils.get(foundations, i)).turnUpTopCard();
                    }

                    ((Deck) Utils.get(foundations, i)).addIntoDeckFront(secondPlayer.reserve.getTopCard());
                    secondPlayer.reserve.removeTopCard();

                    if (!(Deck.isEmpty(secondPlayer.reserve.cards))) {
                        secondPlayer.reserve.turnUpTopCard();
                    }

                    return true;
                }
            }
        }
    }

    public Boolean moveCardFromReserveToReserve() {
        if (Utils.equals(turn, quotes.FirstPlayerQuote.getInstance())) {
            Boolean orResult_15 = false;

            if (!(Utils.equals(firstPlayer.reserve.getTopCard().getSuit(),
                        secondPlayer.reserve.getTopCard().getSuit()))) {
                orResult_15 = true;
            } else {
                Boolean andResult_22 = false;

                if (!(Utils.equals(firstPlayer.reserve.getTopCard().number,
                            secondPlayer.reserve.getTopCard().number.longValue() -
                            1L))) {
                    if (!(Utils.equals(
                                firstPlayer.reserve.getTopCard().number,
                                secondPlayer.reserve.getTopCard().number.longValue() +
                                1L))) {
                        andResult_22 = true;
                    }
                }

                orResult_15 = andResult_22;
            }

            if (orResult_15) {
                return false;
            } else {
                secondPlayer.reserve.turnUpTopCard();
                secondPlayer.reserve.addIntoDeckFront(firstPlayer.reserve.getTopCard());
                firstPlayer.reserve.removeTopCard();

                if (!(Deck.isEmpty(firstPlayer.reserve.cards))) {
                    firstPlayer.reserve.turnUpTopCard();
                }

                return true;
            }
        } else {
            Boolean orResult_16 = false;

            if (!(Utils.equals(firstPlayer.reserve.getTopCard().getSuit(),
                        secondPlayer.reserve.getTopCard().getSuit()))) {
                orResult_16 = true;
            } else {
                Boolean andResult_23 = false;

                if (!(Utils.equals(secondPlayer.reserve.getTopCard().number,
                            firstPlayer.reserve.getTopCard().number.longValue() -
                            1L))) {
                    if (!(Utils.equals(
                                secondPlayer.reserve.getTopCard().number,
                                firstPlayer.reserve.getTopCard().number.longValue() +
                                1L))) {
                        andResult_23 = true;
                    }
                }

                orResult_16 = andResult_23;
            }

            if (orResult_16) {
                return false;
            } else {
                firstPlayer.reserve.turnUpTopCard();
                firstPlayer.reserve.addIntoDeckFront(secondPlayer.reserve.getTopCard());
                secondPlayer.reserve.removeTopCard();

                if (!(Deck.isEmpty(secondPlayer.reserve.cards))) {
                    secondPlayer.reserve.turnUpTopCard();
                }

                return true;
            }
        }
    }

    public Boolean moveCardFromReserveToWaste() {
        if (Utils.equals(turn, quotes.FirstPlayerQuote.getInstance())) {
            Boolean orResult_19 = false;

            if (!(Utils.equals(firstPlayer.reserve.getTopCard().getSuit(),
                        secondPlayer.waste.getTopCard().getSuit()))) {
                orResult_19 = true;
            } else {
                Boolean andResult_30 = false;

                if (!(Utils.equals(firstPlayer.reserve.getTopCard().number,
                            secondPlayer.waste.getTopCard().number.longValue() -
                            1L))) {
                    if (!(Utils.equals(
                                firstPlayer.reserve.getTopCard().number,
                                secondPlayer.waste.getTopCard().number.longValue() +
                                1L))) {
                        andResult_30 = true;
                    }
                }

                orResult_19 = andResult_30;
            }

            if (orResult_19) {
                return false;
            } else {
                if (!(Deck.isEmpty(secondPlayer.waste.cards))) {
                    secondPlayer.waste.turnUpTopCard();
                }

                secondPlayer.waste.addIntoDeckFront(firstPlayer.reserve.getTopCard());
                firstPlayer.reserve.removeTopCard();

                if (!(Deck.isEmpty(firstPlayer.reserve.cards))) {
                    firstPlayer.reserve.turnUpTopCard();
                }

                return true;
            }
        } else {
            Boolean orResult_20 = false;

            if (!(Utils.equals(firstPlayer.reserve.getTopCard().getSuit(),
                        secondPlayer.waste.getTopCard().getSuit()))) {
                orResult_20 = true;
            } else {
                Boolean andResult_31 = false;

                if (!(Utils.equals(secondPlayer.reserve.getTopCard().number,
                            firstPlayer.waste.getTopCard().number.longValue() -
                            1L))) {
                    if (!(Utils.equals(
                                secondPlayer.reserve.getTopCard().number,
                                firstPlayer.waste.getTopCard().number.longValue() +
                                1L))) {
                        andResult_31 = true;
                    }
                }

                orResult_20 = andResult_31;
            }

            if (orResult_20) {
                return false;
            } else {
                if (!(Deck.isEmpty(firstPlayer.waste.cards))) {
                    firstPlayer.waste.turnUpTopCard();
                }

                firstPlayer.waste.addIntoDeckFront(secondPlayer.reserve.getTopCard());
                secondPlayer.reserve.removeTopCard();

                if (!(Deck.isEmpty(secondPlayer.reserve.cards))) {
                    secondPlayer.reserve.turnUpTopCard();
                }

                return true;
            }
        }
    }

    public void updatePoints() {
        Object winner = this.isGameOver();

        if (Utils.equals(winner, quotes.FirstPlayerQuote.getInstance())) {
            firstPlayer.score = firstPlayer.score.longValue() + 30L +
                secondPlayer.hand.cards.size() +
                secondPlayer.waste.cards.size() +
                (2L * secondPlayer.reserve.cards.size());
        } else {
            if (Utils.equals(winner, quotes.SecondPlayerQuote.getInstance())) {
                secondPlayer.score = secondPlayer.score.longValue() + 30L +
                    firstPlayer.hand.cards.size() +
                    firstPlayer.waste.cards.size() +
                    (2L * firstPlayer.reserve.cards.size());
            }
        }
    }

    public String toString() {
        return "Board{" + "firstPlayer := " + Utils.toString(firstPlayer) +
        ", secondPlayer := " + Utils.toString(secondPlayer) +
        ", foundations := " + Utils.toString(foundations) + ", tableau := " +
        Utils.toString(tableau) + ", turn := " + Utils.toString(turn) + "}";
    }
}
