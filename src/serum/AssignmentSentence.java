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
public class AssignmentSentence {
    private Variable lhs;
    private Expression rhs;
    
    public AssignmentSentence(Variable lhs, Expression rhs){
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    
}
