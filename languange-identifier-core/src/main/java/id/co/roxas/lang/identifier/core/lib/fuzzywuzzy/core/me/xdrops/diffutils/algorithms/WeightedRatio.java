package id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.algorithms;

import static java.lang.Math.round;

import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.FuzzySearch;
import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.ToStringFunction;


@SuppressWarnings("WeakerAccess")
public class WeightedRatio extends BasicAlgorithm  {

    public static final double UNBASE_SCALE = .95;
    public static final double PARTIAL_SCALE = .90;
    public static final boolean TRY_PARTIALS = true;


    @Override
    public int apply(String s1, String s2, ToStringFunction<String> stringProcessor) {

        s1 = stringProcessor.apply(s1);
        s2 = stringProcessor.apply(s2);

        int len1 = s1.length();
        int len2 = s2.length();

        if (len1 == 0 || len2 == 0) { return 0; }

        boolean tryPartials = TRY_PARTIALS;
        double unbaseScale = UNBASE_SCALE;
        double partialScale = PARTIAL_SCALE;
        FuzzySearch search = new FuzzySearch();
        int base = search.ratio(s1, s2);
        double lenRatio = ((double) Math.max(len1, len2)) / Math.min(len1, len2);

        // if strings are similar length don't use partials
        if (lenRatio < 1.5) tryPartials = false;

        // if one string is much shorter than the other
        if (lenRatio > 8) partialScale = .6;

        if (tryPartials) {
           
            double partial = search.partialRatio(s1, s2) * partialScale;
            double partialSor = search.tokenSortPartialRatio(s1, s2) * unbaseScale * partialScale;
            double partialSet = search.tokenSetPartialRatio(s1, s2) * unbaseScale * partialScale;

            return (int) round(max(base, partial, partialSor, partialSet));

        } else {

            double tokenSort = search.tokenSortRatio(s1, s2) * unbaseScale;
            double tokenSet = search.tokenSetRatio(s1, s2) * unbaseScale;

            return (int) round(max(base, tokenSort, tokenSet));

        }

    }


	private float max(int base, double... tokens) {
		float fl= base;
		for (double token : tokens) {
			if(token>fl) {
				fl = (float) token;
			}
		}
		return fl;
	}

}
