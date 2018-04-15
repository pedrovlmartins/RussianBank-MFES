package quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class DiamondsQuote {
    private static int hc = 0;
    private static DiamondsQuote instance = null;

    public DiamondsQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static DiamondsQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new DiamondsQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof DiamondsQuote;
    }

    public String toString() {
        return "<Diamonds>";
    }
}
