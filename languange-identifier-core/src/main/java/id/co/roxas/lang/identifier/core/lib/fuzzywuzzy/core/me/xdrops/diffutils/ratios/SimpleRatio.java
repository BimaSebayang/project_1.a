package id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.ratios;

import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.Ratio;
import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.ToStringFunction;
import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.diffutils.me.xdrops.diffutils.DiffUtils;


public class SimpleRatio implements Ratio {

    /**
     * Computes a simple Levenshtein distance ratio between the strings
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The resulting ratio of similarity
     */
    @Override
    public int apply(String s1, String s2) {

        return (int) Math.round(100 * DiffUtils.getRatio(s1, s2));

    }

    @Override
    public int apply(String s1, String s2, ToStringFunction<String> sp) {
        return apply(sp.apply(s1), sp.apply(s2));
    }
}