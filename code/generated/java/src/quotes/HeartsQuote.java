package quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class HeartsQuote {
    private static int hc = 0;
    private static HeartsQuote instance = null;

    public HeartsQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static HeartsQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new HeartsQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof HeartsQuote;
    }

    public String toString() {
        return "<Hearts>";
    }
}
