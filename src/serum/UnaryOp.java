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
public class UnaryOp extends Expression {
    private Expression expression;
    private OperationType operationType;
    
    public UnaryOp(Expression op1, OperationType opType){
        this.expression = op1;
        this.operationType = opType;
    }

    @Override
    public Type getType() {
        switch(operationType){
            case NOT_OP:
                return Type.TBool;
            case NEG_OP:
                return Type.TInt;
            default:
                throw new UnsupportedOperationException("This OperationType is not unary.");
        }
    }

    @Override
    public List<PInstruction> toCode() {
        List<PInstruction> code = expression.toCode();
        code.add(new POperation(operationType));
        return code;
    }

    @Override
    public Boolean typeCheck() {
        if (!expression.getType().equals(getType())){
            System.out.println(
                    "Type error. Expected " + getType().getClass().getName() +
                    " for operand of unary operator in line "
                    + row + ", " + expression.getType() + " received.");
            return false;
        }
        return true;
    }
}