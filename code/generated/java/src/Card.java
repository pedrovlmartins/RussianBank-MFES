import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Card {
    public Number number;
    public Object suit;
    public Object color;
    public Object status = quotes.Face_DownQuote.getInstance();

    public Card(final Number nr, final Object sui) {
        cg_init_Card_1(nr, ((Object) sui));
    }

    public Card() {
    }

    public void cg_init_Card_1(final Number nr, final Object sui) {
        number = nr;
        suit = sui;

        Boolean orResult_21 = false;

        if (Utils.equals(sui, quotes.ClubsQuote.getInstance())) {
            orResult_21 = true;
        } else {
            orResult_21 = Utils.equals(sui, quotes.SpadesQuote.getInstance());
        }

        if (orResult_21) {
            color = quotes.BlackQuote.getInstance();
        } else {
            color = quotes.RedQuote.getInstance();
        }

        return;
    }

    public Number getNumber() {
        return number;
    }

    public Object getSuit() {
        return suit;
    }

    public void turnCard() {
        if (Utils.equals(status, quotes.Face_DownQuote.getInstance())) {
            status = quotes.Face_UpQuote.getInstance();
        } else {
            status = quotes.Face_DownQuote.getInstance();
        }
    }

    public Number compare(final Card c) {
        if (number.longValue() > c.getNumber().longValue()) {
            return 1L;
        } else if (number.longValue() < c.getNumber().longValue()) {
            return -1L;
        } else {
            return 0L;
        }
    }

    public String toString() {
        return "Card{" + "number := " + Utils.toString(number) + ", suit := " +
        Utils.toString(suit) + ", color := " + Utils.toString(color) +
        ", status := " + Utils.toString(status) + "}";
    }
}
