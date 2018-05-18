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
        switch(opType){
            case NOT_OP:
                return Type.TBool;
            case NEG_OP:
                return Type.TInt;
            default:
                throw new UnsupportedOperationException("OP not supported yet.");
        }
    }

    @Override
    public String toCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean typeCheck() {
        switch(opType){
            case NOT_OP:
                if (!op1.equals(Type.TBool)){
                    System.out.println(
                        "Type error. Expected TBool for operand of unary operator in line " 
                         + row + ", " + op1.getType() + " received");
                    return false;
                } 
                return true;
            case NEG_OP:
                if (!op1.equals(Type.TInt)){
                    System.out.println(
                        "Type error. Expected TBool for operand of unary operator in line " 
                         + row + ", " + op1.getType() + " received");
                    return false;
                } 
                return true;
            default:
                throw new UnsupportedOperationException("OP not supported yet.");
        }
    }
    
    public enum OpType {NOT_OP, NEG_OP};
}