package quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class SpadesQuote {
    private static int hc = 0;
    private static SpadesQuote instance = null;

    public SpadesQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static SpadesQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new SpadesQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof SpadesQuote;
    }

    public String toString() {
        return "<Spades>";
    }
}
