/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serum;

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
    protected Declaration reference;
    
    public Variable(String id){
        this.id = id;
    }
    
    public void setDeclaration(Declaration declaration) { this.reference = declaration; }
    
    @Override
    public Type getType(){ return reference.getType(); }

    @Override
    public List<PInstruction> toCode() {
        List<PInstruction> code = this.toCodeL();
        code.add(new Indirection());
        return code;
    }

    public List<PInstruction> toCodeL() {
        List<PInstruction> code = new LinkedList<>();
        code.add(new LoadConstant(reference.getAddress()));
        return code;
    }

    @Override
    public Boolean typeCheck() {
        return true; 
    }
}
