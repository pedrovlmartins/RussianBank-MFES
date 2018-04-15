import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Player {
    public Number score = 0L;
    public Object color;
    public Deck fullDeck = new Deck();
    public Deck reserve = new Deck();
    public Deck waste = new Deck();
    public Deck hand = new Deck();

    public Player(final Object clr) {
        cg_init_Player_1(((Object) clr));
    }

    public Player() {
    }

    public void cg_init_Player_1(final Object clr) {
        color = clr;
        fullDeck = fullDeck.createCompleteDeck();

        return;
    }

    public void createHandReserve() {
        VDMSeq crds = fullDeck.cards;
        reserve.cards = SeqUtil.subSeq(Utils.copy(crds), 1L, 13L);
        hand.cards = SeqUtil.subSeq(Utils.copy(crds), 14L, 52L);
        reserve.turnUpTopCard();
    }

    public void shuffleDeck() {
        fullDeck.shuffleDeck();
    }

    public String toString() {
        return "Player{" + "score := " + Utils.toString(score) + ", color := " +
        Utils.toString(color) + ", fullDeck := " + Utils.toString(fullDeck) +
        ", reserve := " + Utils.toString(reserve) + ", waste := " +
        Utils.toString(waste) + ", hand := " + Utils.toString(hand) + "}";
    }
}
