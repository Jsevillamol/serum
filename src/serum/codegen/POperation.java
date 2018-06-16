package serum.codegen;

import serum.OperationType;

/**
 * @author David Rubio
 */
public class POperation extends PInstruction{

    private OperationType operationType;

    public POperation(OperationType opType) {
        this.operationType = opType;
    }

    @Override
    public String toString() {
        String string = "";
        switch (operationType){
            case SUM_OP:
                string = "add";
                break;
            case SUBS_OP:
                string = "sub";
                break;
            case DIV_OP:
                string = "div";
                break;
            case PROD_OP:
                string = "mul";
                break;
            case OR_OP:
                string = "or";
                break;
            case AND_OP:
                string = "and";
                break;
            case EQ_OP:
                string = "equ";
                break;
            case LT_OP:
                string = "les";
                break;
            case  GT_OP:
                string = "grt";
                break;
            case LET_OP:
                string = "leq";
                break;
            case GET_OP:
                string = "get";
                break;
            case NOT_OP:
                string = "not";
                break;
            case NEG_OP:
                string = "neg";
                break;
        }
        return string + ";\n";
    }
}
