package quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class SecondPlayerQuote {
    private static int hc = 0;
    private static SecondPlayerQuote instance = null;

    public SecondPlayerQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static SecondPlayerQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new SecondPlayerQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof SecondPlayerQuote;
    }

    public String toString() {
        return "<SecondPlayer>";
    }
}
