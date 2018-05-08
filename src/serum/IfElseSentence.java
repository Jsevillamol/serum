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
public class IfElseSentence extends Instruction{
    private Expression condition;
    private Instruction ifBranch;
    private Instruction elseBranch;
    
    public IfElseSentence(Expression condition, Instruction ifBranch, Instruction elseBranch){
        this.condition = condition;
        this.ifBranch = ifBranch;
        this.elseBranch = elseBranch;
    }
}
