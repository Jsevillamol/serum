/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serum;

import serum.codegen.PInstruction;
import serum.codegen.POperation;

import java.util.List;

/**
 *
 * @author jsevillamol
 */
public class BinaryOp extends Expression {
    private Expression op1;
    private Expression op2;
    private OperationType opType;
    
    public BinaryOp(Expression op1, Expression op2, OperationType opType){
        this.op1 = op1;
        this.op2 = op2;
        this.opType = opType;
    }

    //TODO para que son estos getters?
    public Expression getOp1(){ return op1;}
    public Expression getOp2(){ return op2;}
    public OperationType getOpType(){ return opType; }

    @Override
    public Type getType() {
        switch (opType){
            case SUM_OP: case SUBS_OP: case DIV_OP: case PROD_OP:
                return Type.TInt;
            case OR_OP: case AND_OP: case EQ_OP: case LT_OP: case GT_OP: case LET_OP: case GET_OP:
                return Type.TBool;
            default:
                throw new UnsupportedOperationException("This OperationType is not binary.");
        }
    }

    @Override
    public List<PInstruction> toCode() {
        List code = op1.toCode();
        code.addAll(op2.toCode());
        code.add(new POperation(opType));
        return code;
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
                if (!op2.equals(Type.TInt)){
                    System.out.println(
                        "Type error. Expected TInt for right operand of binary operator in line "
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
                if (!op2.equals(Type.TBool)){
                    System.out.println(
                        "Type error. Expected TBool for right operand of binary operator in line "
                         + row + ", " + op2.getType() + " received");
                    res = false;
                }
                break;
                    
            default:
                throw new UnsupportedOperationException("This OperationType is not binary.");
        }
        return res;
    }
}
