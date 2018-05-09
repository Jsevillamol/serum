/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serum;

/**
 *
 * @author jsevillamol
 */
public class WhileSentence extends Instruction {
    Expression condition;
    Instruction body;
    
    public WhileSentence(Expression condition, Instruction body){
        this.condition = condition;
        this.body = body;
    }

    @Override
    public String toCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean typeCheck() {
        return condition.getType().equals(Type.TBool) && condition.typeCheck() && body.typeCheck();
    }
}
