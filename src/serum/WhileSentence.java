/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serum;

import com.sun.istack.internal.NotNull;
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

    @NotNull
    @Override
    public List<PInstruction> toCode() {
        List<PInstruction> code = condition.toCode();
        List<PInstruction> bodyCode = body.toCode();
        PInstruction ujp = new Jump(code.get(0),
                false/*incondicional*/,
                true /*salto a la instruccion dada*/);
        code.add(new Jump(ujp,
                true /*condicional*/,
                false/*salto a la instruccion que sigue a la dada*/));
        code.addAll(bodyCode);
        code.add(ujp);
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

    @Override
    public void identifiers(IdTable idTable) {
        condition.identifiers(idTable);
        body.identifiers(idTable);
    }
}
