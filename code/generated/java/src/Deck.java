import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Deck {
    public VDMSeq cards;
    public VDMSeq defaultCards = SeqUtil.seq(new Card(1L,
                quotes.ClubsQuote.getInstance()),
            new Card(2L, quotes.ClubsQuote.getInstance()),
            new Card(3L, quotes.ClubsQuote.getInstance()),
            new Card(4L, quotes.ClubsQuote.getInstance()),
            new Card(5L, quotes.ClubsQuote.getInstance()),
            new Card(6L, quotes.ClubsQuote.getInstance()),
            new Card(7L, quotes.ClubsQuote.getInstance()),
            new Card(8L, quotes.ClubsQuote.getInstance()),
            new Card(9L, quotes.ClubsQuote.getInstance()),
            new Card(10L, quotes.ClubsQuote.getInstance()),
            new Card(11L, quotes.ClubsQuote.getInstance()),
            new Card(12L, quotes.ClubsQuote.getInstance()),
            new Card(13L, quotes.ClubsQuote.getInstance()),
            new Card(1L, quotes.HeartsQuote.getInstance()),
            new Card(2L, quotes.HeartsQuote.getInstance()),
            new Card(3L, quotes.HeartsQuote.getInstance()),
            new Card(4L, quotes.HeartsQuote.getInstance()),
            new Card(5L, quotes.HeartsQuote.getInstance()),
            new Card(6L, quotes.HeartsQuote.getInstance()),
            new Card(7L, quotes.HeartsQuote.getInstance()),
            new Card(8L, quotes.HeartsQuote.getInstance()),
            new Card(9L, quotes.HeartsQuote.getInstance()),
            new Card(10L, quotes.HeartsQuote.getInstance()),
            new Card(11L, quotes.HeartsQuote.getInstance()),
            new Card(12L, quotes.HeartsQuote.getInstance()),
            new Card(13L, quotes.HeartsQuote.getInstance()),
            new Card(1L, quotes.SpadesQuote.getInstance()),
            new Card(2L, quotes.SpadesQuote.getInstance()),
            new Card(3L, quotes.SpadesQuote.getInstance()),
            new Card(4L, quotes.SpadesQuote.getInstance()),
            new Card(5L, quotes.SpadesQuote.getInstance()),
            new Card(6L, quotes.SpadesQuote.getInstance()),
            new Card(7L, quotes.SpadesQuote.getInstance()),
            new Card(8L, quotes.SpadesQuote.getInstance()),
            new Card(9L, quotes.SpadesQuote.getInstance()),
            new Card(10L, quotes.SpadesQuote.getInstance()),
            new Card(11L, quotes.SpadesQuote.getInstance()),
            new Card(12L, quotes.SpadesQuote.getInstance()),
            new Card(13L, quotes.SpadesQuote.getInstance()),
            new Card(1L, quotes.DiamondsQuote.getInstance()),
            new Card(2L, quotes.DiamondsQuote.getInstance()),
            new Card(3L, quotes.DiamondsQuote.getInstance()),
            new Card(4L, quotes.DiamondsQuote.getInstance()),
            new Card(5L, quotes.DiamondsQuote.getInstance()),
            new Card(6L, quotes.DiamondsQuote.getInstance()),
            new Card(7L, quotes.DiamondsQuote.getInstance()),
            new Card(8L, quotes.DiamondsQuote.getInstance()),
            new Card(9L, quotes.DiamondsQuote.getInstance()),
            new Card(10L, quotes.DiamondsQuote.getInstance()),
            new Card(11L, quotes.DiamondsQuote.getInstance()),
            new Card(12L, quotes.DiamondsQuote.getInstance()),
            new Card(13L, quotes.DiamondsQuote.getInstance()));

    public Deck() {
        cg_init_Deck_1();
    }

    public Deck(final VDMSeq c) {
        cg_init_Deck_2(Utils.copy(c));
    }

    public void cg_init_Deck_1() {
        cards = SeqUtil.seq();

        return;
    }

    public void cg_init_Deck_2(final VDMSeq c) {
        cards = Utils.copy(c);
    }

    public Deck createCompleteDeck() {
        cards = Utils.copy(defaultCards);

        return this;
    }

    public void removeFromDeck(final Number r) {
        Number length = cards.size();

        if (Utils.equals(r, 1L)) {
            cards = SeqUtil.tail(Utils.copy(cards));
        } else if (Utils.equals(r, length)) {
            cards = reverseCards(SeqUtil.tail(reverseCards(Utils.copy(cards))));
        } else if (Utils.equals(r, length.longValue() - 1L)) {
            cards = SeqUtil.conc(SeqUtil.subSeq(Utils.copy(cards), 1L,
                        r.longValue() - 1L),
                    SeqUtil.seq(((Card) reverseCards(Utils.copy(cards)).get(0))));
        } else {
            cards = SeqUtil.conc(SeqUtil.subSeq(Utils.copy(cards), 1L,
                        r.longValue() - 1L),
                    SeqUtil.subSeq(Utils.copy(cards), r.longValue() + 1L, length));
        }
    }

    public void addIntoDeckFront(final Card elem) {
        cards = SeqUtil.conc(SeqUtil.seq(elem), Utils.copy(cards));
    }

    public void turnUpTopCard() {
        Card crd = ((Card) cards.get(0));

        crd.turnCard();
        cards = SeqUtil.conc(SeqUtil.seq(crd), SeqUtil.tail(Utils.copy(cards)));
    }

    public void removeTopCard() {
        this.removeFromDeck(1L);
    }

    public Card getTopCard() {
        return ((Card) cards.get(0));
    }

    public void shuffleDeck() {
        VDMSeq res = SeqUtil.seq();
        Number lenlist = cards.size();
        Number i = 1L;
        Boolean whileCond_1 = true;

        while (whileCond_1) {
            whileCond_1 = i.longValue() <= lenlist.longValue();

            if (!(whileCond_1)) {
                break;
            }

            {
                Number n = MATH.rand(cards.size()).longValue() + 1L;
                res = SeqUtil.conc(Utils.copy(res),
                        SeqUtil.seq(((Card) Utils.get(cards, n))));

                this.removeFromDeck(n);
                i = i.longValue() + 1L;
            }
        }

        cards = Utils.copy(res);
    }

    public static Boolean isEmpty(final VDMSeq cds) {
        if (cds.size() > 0L) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean isShuffled(final Deck dk) {
        if (Utils.equals(dk.cards, dk.defaultCards)) {
            return false;
        } else {
            return true;
        }
    }

    public static VDMSeq reverseCards(final VDMSeq cds) {
        if (Utils.equals(cds.size(), 1L)) {
            return Utils.copy(cds);
        } else {
            return SeqUtil.conc(reverseCards(SeqUtil.tail(Utils.copy(cds))),
                SeqUtil.seq(((Card) cds.get(0))));
        }
    }

    public static Boolean isOrdered(final VDMSeq cds) {
        Card x = ((Card) cds.get(0));

        {
            VDMSeq remainder = SeqUtil.tail(Utils.copy(cds));

            Boolean ternaryIfExp_1 = null;

            if (isEmpty(Utils.copy(remainder))) {
                ternaryIfExp_1 = true;
            } else {
                Card y = ((Card) SeqUtil.tail(Utils.copy(cds)).get(0));

                Boolean ternaryIfExp_2 = null;

                Boolean orResult_22 = false;

                if (!(Utils.equals(x.number, y.number.longValue() - 1L))) {
                    orResult_22 = true;
                } else {
                    orResult_22 = Utils.equals(x.color, y.color);
                }

                if (orResult_22) {
                    ternaryIfExp_2 = false;
                } else {
                    Boolean ternaryIfExp_3 = null;

                    if (isEmpty(SeqUtil.tail(Utils.copy(cds)))) {
                        ternaryIfExp_3 = true;
                    } else {
                        ternaryIfExp_3 = isOrdered(SeqUtil.tail(Utils.copy(cds)));
                    }

                    ternaryIfExp_2 = ternaryIfExp_3;
                }

                ternaryIfExp_1 = ternaryIfExp_2;
            }

            return ternaryIfExp_1;
        }
    }

    public static Boolean isOrderedSuit(final VDMSeq cds) {
        Card x = ((Card) cds.get(0));

        {
            VDMSeq remainder = SeqUtil.tail(Utils.copy(cds));

            Boolean ternaryIfExp_4 = null;

            if (isEmpty(Utils.copy(remainder))) {
                ternaryIfExp_4 = true;
            } else {
                Card y = ((Card) SeqUtil.tail(Utils.copy(cds)).get(0));

                Boolean ternaryIfExp_5 = null;

                Boolean andResult_32 = false;

                if (!(Utils.equals(x.number, y.number.longValue() - 1L))) {
                    if (!(Utils.equals(x.suit, y.suit))) {
                        andResult_32 = true;
                    }
                }

                if (andResult_32) {
                    ternaryIfExp_5 = false;
                } else {
                    ternaryIfExp_5 = isOrderedSuit(SeqUtil.tail(Utils.copy(cds)));
                }

                ternaryIfExp_4 = ternaryIfExp_5;
            }

            return ternaryIfExp_4;
        }
    }

    public String toString() {
        return "Deck{" + "cards := " + Utils.toString(cards) +
        ", defaultCards := " + Utils.toString(defaultCards) + "}";
    }
}
