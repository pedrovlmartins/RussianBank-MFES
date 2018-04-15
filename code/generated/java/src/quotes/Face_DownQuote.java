package quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Face_DownQuote {
    private static int hc = 0;
    private static Face_DownQuote instance = null;

    public Face_DownQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static Face_DownQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new Face_DownQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof Face_DownQuote;
    }

    public String toString() {
        return "<Face_Down>";
    }
}
