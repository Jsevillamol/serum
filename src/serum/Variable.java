/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serum;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import serum.codegen.Indirection;
import serum.codegen.LoadConstant;
import serum.codegen.PInstruction;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jsevillamol
 */
public class Variable extends Expression {
    protected String id;
    protected Declaration declaration;
    
    public Variable(String id){
        this.id = id;
    }
    
    @Override
    public Type getType(){ return declaration.getType(); }

    @Override
    public List<PInstruction> toCode() {
        List<PInstruction> code = this.toCodeL();
        code.add(new Indirection());
        return code;
    }

    public List<PInstruction> toCodeL() {
        List<PInstruction> code = new LinkedList<>();
        code.add(new LoadConstant(declaration.getAddress()));
        return code;
    }

    @Override
    public Boolean typeCheck() {
        return true; 
    }

    @Override
    public void identifiers(IdTable idTable) { declaration = idTable.searchID(id); }
}
