package id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.algorithms;

import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.StringProcessor;

/**
 * @deprecated Use {@code ToStringFunction#NO_PROCESS} instead.
 */
@Deprecated
public class NoProcess extends StringProcessor {

    @Override
    @Deprecated
    public String process(String in) {
        return in;
    }

}
