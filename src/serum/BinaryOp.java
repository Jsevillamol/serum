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
public class BinaryOp extends Expression {
    private Expression op1;
    private Expression op2;
    private OpType opType;
    
    public BinaryOp(Expression op1, Expression op2, OpType opType){
        this.op1 = op1;
        this.op2 = op2;
        this.opType = opType;
    }
    
    public Expression getOp1(){ return op1;}
    public Expression getOp2(){ return op2;}
    public OpType getOpType(){ return opType; }
    
    public enum OpType {SUM_OP, SUBS_OP, DIV_OP, PROD_OP, OR_OP, AND_OP, EQ_OP, LT_OP, GT_OP, LET_OP, GET_OP};
}
