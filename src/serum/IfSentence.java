/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serum;

import serum.codegen.Jump;
import serum.codegen.PInstruction;

import java.util.List;

/**
 *
 * @author jsevillamol
 */
public class IfSentence extends Instruction {
    private Expression condition;
    private Instruction conditional;
    
    public IfSentence(Expression condition, Instruction conditional){
        this.condition = condition;
        this.conditional = conditional;
    }

    @Override
    public List<PInstruction> toCode() {
        List<PInstruction> code = condition.toCode();
        List<PInstruction> ifCode = conditional.toCode();
        code.add(new Jump(ifCode.get(ifCode.size()-1),
                          true/*conditional*/,
                          false/*a la instruccion que sigue a la dada*/));
        code.addAll(ifCode);
        return code;
    }

    @Override
    public Boolean typeCheck() {
        Boolean res = condition.typeCheck() && conditional.typeCheck();
        if (!condition.getType().equals(Type.TBool)){
            System.out.println(
                    "Type error. Expected TBool for if condition in line " 
                    + row + ", " + condition.getType() + " received");
            res = false;
        }
        return res;
    }
}
