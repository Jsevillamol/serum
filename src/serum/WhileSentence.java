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
public class WhileSentence extends Instruction {
    private Expression condition;
    private Instruction body;
    
    public WhileSentence(Expression condition, Instruction body){
        this.condition = condition;
        this.body = body;
    }

    @Override
    public List<PInstruction> toCode() {
        List<PInstruction> code = condition.toCode();
        List<PInstruction> bodyCode = body.toCode();
        code.add(new Jump(bodyCode.get(bodyCode.size()-1),
                true /*condicional*/,
                false/*salto a la instruccion que sigue a la dada*/));
        code.addAll(bodyCode);
        code.add(new Jump(code.get(0),
                false/*incondicional*/,
                true /*salto a la instruccion dada*/));
        return code;
    }

    @Override
    public Boolean typeCheck() {
        Boolean res = condition.typeCheck() && body.typeCheck();
        if (!condition.getType().equals(Type.TBool)){
            System.out.println(
                    "Type error. Expected TBool for while condition in line " 
                    + row + ", " + condition.getType() + " received");
            res = false;
        }
        return res;
    }
}
