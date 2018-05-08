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
public class WhileSentence {
    Expression condition;
    Instruction body;
    
    public WhileSentence(Expression condition, Instruction body){
        this.condition = condition;
        this.body = body;
    }
}
