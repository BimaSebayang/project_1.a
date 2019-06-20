package id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.diffutils.me.xdrops.diffutils.structs;

public final class MatchingBlock {
    public int spos;
    public int dpos;
    public int length;

    @Override
    public String toString() {
        return "(" + spos + "," + dpos + "," + length + ")";
    }
}
