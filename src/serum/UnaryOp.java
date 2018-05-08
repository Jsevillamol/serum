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
public class UnaryOp extends Expression {
    private Expression op1;
    private OpType opType;
    
    public UnaryOp(Expression op1, OpType opType){
        this.op1 = op1;
        this.opType = opType;
    }

    @Override
    public Type getType() {
        
    }
    
    public enum OpType {NOT_OP, NEG_OP};
}