package serum;

import com.sun.istack.internal.NotNull;
import serum.codegen.PInstruction;
import serum.codegen.POperation;

import java.util.List;

/**
 *
 * @author jsevillamol, David RUbio
 */
public class BinaryOp extends Expression {

    /**Primer operando.*/
    private Expression op1;

    /**Segundo operando.*/
    private Expression op2;

    /**Tipo de operacion*/
    private OperationType operationType;

    public BinaryOp(Expression op1, Expression op2, OperationType operationType){
        this.op1 = op1;
        this.op2 = op2;
        this.operationType = operationType;
    }


    @Override
    public void identifiers(IdTable idTable) {
        op1.identifiers(idTable);
        op2.identifiers(idTable);
    }

    @Override
    public Type getType() { return operationType.getResultType(); }

    @Override
    public Boolean typeCheck() {
        Boolean res = op1.typeCheck() && op2.typeCheck();
        if (!(op1.getType()).equals(operationType.getArgumentsType())) {
            System.out.println(
                    "Type error. Expected " + operationType.getArgumentsType() +
                    " for left operand of binary operator in line " + row + ", " +
                    op1.getType() + " received. Operation: " + operationType);
            res = false;
        }
        if (!(op2.getType()).equals(operationType.getArgumentsType())) {
            System.out.println(
                    "Type error. Expected " + operationType.getArgumentsType() +
                    " for left operand of binary operator in line " + row + ", " +
                    op2.getType() + " received. Operation: " + operationType);
            res = false;
        }
        return res;
    }

    @NotNull
    @Override
    public List<PInstruction> toCode() {
        List code = op1.toCode();
        code.addAll(op2.toCode());
        code.add(new POperation(operationType));
        return code;
    }

}
