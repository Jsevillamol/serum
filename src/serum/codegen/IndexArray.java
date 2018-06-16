package serum.codegen;

/**
 * @author David Rubio
 */
public class IndexArray extends PInstruction {

    private int size;

    public IndexArray(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ixa " + size + ";\n";
    }
}

