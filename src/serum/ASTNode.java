package serum;

import serum.codegen.PInstruction;
import java.util.List;

/**
 *
 * @author jsevillamol
 */
public interface ASTNode {

    /**
     * @return Lista de p-instrucciones equivalentes
     * a la intrucción o expresión representada por este nodo
     */
    List<PInstruction> toCode();

    /**Comprueba que los tipos del subarbol de sintaxis abstracta que
     * tiene por raiz a este nodo sean correctos.*/
    Boolean typeCheck();
    
    void setRowAndCol(int row, int col);

    void identifiers(IdTable idTable);
}
