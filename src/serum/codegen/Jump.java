package serum.codegen;

/**
 * @author David Rubio
 */
public class Jump extends PInstruction {

    private PInstruction instruction;
    private boolean conditional;
    private boolean toThisInstruction;


    public Jump(PInstruction instruction, boolean conditional, boolean toThisInstruction) {
        this.instruction = instruction;
        this.conditional = conditional;
        this.toThisInstruction = toThisInstruction;
    }

    @Override
    public String toString() {
        String string;
        if (conditional)
            string = "fjp ";
        else
            string = "ujp ";
        int position = instruction.getPosition();
        if (!toThisInstruction) position++;
        return string + position + ";\n";
    }
}
