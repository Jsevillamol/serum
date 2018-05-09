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
public class Assignment extends Instruction {
    private Variable lhs;
    private Expression rhs;
    
    public Assignment(Variable lhs, Expression rhs){
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean typeCheck() {
        return lhs.getType().equals(rhs.getType()) && lhs.typeCheck() && rhs.typeCheck();
    }
    
    
}
