package quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class FirstPlayerQuote {
    private static int hc = 0;
    private static FirstPlayerQuote instance = null;

    public FirstPlayerQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static FirstPlayerQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new FirstPlayerQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof FirstPlayerQuote;
    }

    public String toString() {
        return "<FirstPlayer>";
    }
}
