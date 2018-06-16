package serum;

import serum.codegen.PInstruction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jsevillamol
 */
public class InstructionList extends Instruction {
    private List<Instruction> instructions;
    
    public InstructionList(){
        this.instructions = new ArrayList<>();
    }
    
    public InstructionList(Instruction instruction){
        this.instructions = new ArrayList<>();
        instructions.add(instruction);
    }
    
    public InstructionList(Instruction instruction, InstructionList instructionList){
        this.instructions = instructionList.instructions;
        this.instructions.add(instruction);
    }

    /**
     *
     * @return La concatenación de las p-instrucciones de cada instrucción de la lista
     */
    @Override
    public List<PInstruction> toCode() {
        List<PInstruction> instructionList = new LinkedList<>();
        for (Instruction instruction : instructions) {
            instructionList.addAll(instruction.toCode());
        }
        return instructionList;
    }

    @Override
    public Boolean typeCheck() {
        Boolean tipoCorrecto = true;
        for(Instruction instruction : instructions){
            tipoCorrecto = instruction.typeCheck() && tipoCorrecto;
        }
        return tipoCorrecto;
    }
}
