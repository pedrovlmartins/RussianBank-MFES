package quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Face_UpQuote {
    private static int hc = 0;
    private static Face_UpQuote instance = null;

    public Face_UpQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static Face_UpQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new Face_UpQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof Face_UpQuote;
    }

    public String toString() {
        return "<Face_Up>";
    }
}
