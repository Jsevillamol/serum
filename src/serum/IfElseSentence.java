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
public class IfElseSentence extends Instruction{
    private Expression condition;
    private Instruction ifBranch;
    private Instruction elseBranch;
    
    public IfElseSentence(Expression condition, Instruction ifBranch, Instruction elseBranch){
        this.condition = condition;
        this.ifBranch = ifBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public List<PInstruction> toCode() {
        List<PInstruction> code = condition.toCode();
        List<PInstruction> elseCode = elseBranch.toCode();
        code.add(new Jump(elseCode.get(0),
                          true/*condicional*/,
                          true/*salto a la instruccion dada*/));
        code.addAll(ifBranch.toCode());
        code.add(new Jump(code.get(code.size()-1),
                          false/*incondicional*/,
                          false/*salto a la instruccion que sigue a la dada*/));
        code.addAll(elseCode);
        return code;
    }

    @Override
    public Boolean typeCheck() {
        Boolean res = condition.typeCheck() && ifBranch.typeCheck() && elseBranch.typeCheck();
        if (!condition.getType().equals(Type.TBool)){
            System.out.println(
                    "Type error. Expected TBool for if-else condition in line " 
                    + row + ", " + condition.getType() + " received");
            res = false;
        }
        return res;
    }

    @Override
    public void identifiers(IdTable idTable) {
        condition.identifiers(idTable);
        ifBranch.identifiers(idTable);
        elseBranch.identifiers(idTable);
    }
}
