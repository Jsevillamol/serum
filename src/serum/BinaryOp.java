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

    @Override
    public Type getType() {
        switch (opType){
            case SUM_OP: case SUBS_OP: case DIV_OP: case PROD_OP:
                return Type.TInt;
            case OR_OP: case AND_OP: case EQ_OP: case LT_OP: case GT_OP: case LET_OP: case GET_OP:
                return Type.TBool;
            default:
                throw new UnsupportedOperationException("This OP does not exist");
        }
    }

    @Override
    public String toCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean typeCheck() {
        Boolean res = op1.typeCheck() && op2.typeCheck();
        switch (opType){
            case SUM_OP: case SUBS_OP: case DIV_OP: case PROD_OP: case EQ_OP: case LT_OP: case GT_OP: case LET_OP: case GET_OP:
                if (!op1.equals(Type.TInt)){
                    System.out.println(
                        "Type error. Expected TInt for left operand of binary operator in line " 
                         + row + ", " + op1.getType() + " received");
                    res = false;
                } 
                else if (!op2.equals(Type.TInt)){
                    System.out.println(
                        "Type error. Expected TInt for left operand of binary operator in line " 
                         + row + ", " + op2.getType() + " received");
                    res = false;
                }
                break;
            case OR_OP: case AND_OP:
                if (!op1.equals(Type.TBool)){
                    System.out.println(
                        "Type error. Expected TBool for left operand of binary operator in line " 
                         + row + ", " + op1.getType() + " received");
                    res = false;
                } 
                else if (!op2.equals(Type.TBool)){
                    System.out.println(
                        "Type error. Expected TBool for left operand of binary operator in line " 
                         + row + ", " + op2.getType() + " received");
                    res = false;
                }
                break;
                    
            default:
                throw new UnsupportedOperationException("This OP does not exist");
        }
        return res;
    }
    
    public enum OpType {SUM_OP, SUBS_OP, DIV_OP, PROD_OP, OR_OP, AND_OP, EQ_OP, LT_OP, GT_OP, LET_OP, GET_OP};
}
