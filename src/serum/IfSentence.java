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
public class IfSentence extends Instruction {
    private Expression condition;
    private Instruction conditional;
    
    public IfSentence(Expression condition, Instruction conditional){
        this.condition = condition;
        this.conditional = conditional;
    }
    
    public Boolean checkType(){
        return condition.getType().equals(TBoolean);
    }
}
