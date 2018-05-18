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

    @Override
    public String toCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
