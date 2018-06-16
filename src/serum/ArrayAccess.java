/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serum;

import serum.codegen.IndexArray;
import serum.codegen.PInstruction;

import java.util.List;

/**
 *
 * @author jsevillamol
 */
public class ArrayAccess extends Variable {
    private Variable reference;
    private Expression index;
    
    public ArrayAccess(Variable reference, Expression index){
        super(reference.id);
        this.reference = reference;
        this.index = index;
    }
    
    @Override
    public Type getType(){ return this.reference.getType().dereference(); }

    @Override
    public List<PInstruction> toCodeL() {
        List<PInstruction> code = reference.toCodeL();
        code.addAll(index.toCode());
        code.add(new IndexArray(reference.getType().getSize()));
        //Los arrays empiezan por 0, no hay necesidad de decrementar.
        return code;
    }

    @Override
    public Boolean typeCheck() {
        Boolean res = index.typeCheck();
        if (!(reference.getType() instanceof Type.ArrayType)){
            System.out.println(
                    "Type error. Expected ArrayType for array access declaration in line "
                    + row + ", " + reference.getType() + " received");
            res = false;
        }
        if (!index.getType().equals(Type.TInt)){
            System.out.println(
                    "Type error. Expected TInt for array access index in line " 
                    + row + ", " + reference.getType() + " received");
            res = false;
        }
        return res; 
    }

    @Override
    public void identifiers(IdTable idTable) {
        reference.identifiers(idTable);
        index.identifiers(idTable);
    }
}
