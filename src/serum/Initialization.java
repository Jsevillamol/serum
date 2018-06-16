/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serum;

import serum.codegen.LoadConstant;
import serum.codegen.PInstruction;
import serum.codegen.Store;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jsevillamol
 */
public class Initialization extends Declaration {
    
    private Expression rhs;
    
    public Initialization(Type type, String id, Expression rhs) {
        super(type, id);
        this.rhs = rhs;
    }
    
    @Override
    public Boolean typeCheck() {
        Boolean res = rhs.typeCheck();
        if (!type.equals(rhs.getType())){
            System.out.println(
                    "Type error. Type declaration and rhs types of initialization in line " 
                    + row + " do not match. "
                    + " type declaration=" + this.type
                    + " rhs type=" + rhs.getType());
            res = false;
        }
        return res;
    }

    @Override
    public List<PInstruction> toCode() {
        List<PInstruction> code = new LinkedList<>();
        code.add(new LoadConstant(this.getAddress()));
        code.addAll(rhs.toCode());
        code.add(new Store());
        return code;
    }

    @Override
    public void identifiers(IdTable idTable) {
        rhs.identifiers(idTable);
        super.identifiers(idTable);
    }
}
