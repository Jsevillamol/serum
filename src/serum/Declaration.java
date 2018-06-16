/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serum;

import serum.codegen.PInstruction;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jsevillamol
 */
public class Declaration extends Instruction implements ASTNode, Typable {
    private String id;
    private Type type;


    int address;
    
    public Declaration(Type type, String id){
        this.id = id;
        this.type = type;
    }
    
    public String getId(){return id;}
    
    @Override
    public Type getType(){return type;}
    
    @Override
    public Boolean typeCheck() {
        return true;
    }

    public int getAddress() { return address; }

    @Override
    public List<PInstruction> toCode() {return new LinkedList<>();}
}
