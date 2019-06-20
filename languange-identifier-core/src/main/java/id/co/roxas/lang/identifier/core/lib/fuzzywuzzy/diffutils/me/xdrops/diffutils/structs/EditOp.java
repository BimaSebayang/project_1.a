package id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.diffutils.me.xdrops.diffutils.structs;

public final class EditOp {

    public EditType type;
    public int spos; // source block pos
    public int dpos; // destination block pos

    @Override
    public String toString() {
        return type.name() + "(" + spos + "," + dpos + ")";
    }

}
