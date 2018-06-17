/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serum;

import com.sun.istack.internal.NotNull;
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
    public Type getType() { return operationType.getResultType(); }

    @NotNull
    @Override
    public List<PInstruction> toCode() {
        List<PInstruction> code = expression.toCode();
        code.add(new POperation(operationType));
        return code;
    }

    @Override
    public Boolean typeCheck() {
        if (!expression.getType().equals(operationType.getArgumentsType())){
            System.out.println(
                    "Type error. Expected " + operationType.getArgumentsType() +
                    " for operand of unary operator in line "
                    + row + ", " + expression.getType() + " received.");
            return false;
        }
        return true;
    }

    @Override
    public void identifiers(IdTable idTable) {
        expression.identifiers(idTable);
    }
}