/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serum;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jsevillamol
 */
public class InstructionList extends Instruction {
    private List<Instruction> instructions;
    
    public InstructionList(){
        this.instructions = new ArrayList<Instruction>();
    }
    
    public InstructionList(Instruction instruction){
        this.instructions = new ArrayList<Instruction>();
        instructions.add(instruction);
    }
    
    public InstructionList(Instruction instruction, InstructionList instructionList){
        this.instructions = instructionList.instructions;
        this.instructions.add(instruction);
    }

    @Override
    public String toCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean typeCheck() {
        Boolean aux = true;
        for(Instruction ins : instructions){
            aux = ins.typeCheck() && aux;
        }
        return aux;
    }
}
