package quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class ClubsQuote {
    private static int hc = 0;
    private static ClubsQuote instance = null;

    public ClubsQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static ClubsQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new ClubsQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof ClubsQuote;
    }

    public String toString() {
        return "<Clubs>";
    }
}
