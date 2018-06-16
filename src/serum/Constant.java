/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serum;

import serum.codegen.LoadConstant;
import serum.codegen.PInstruction;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jsevillamol
 */
public class Constant extends Expression{
    Type type;
    private Object value;
    
    Constant(Type type, Object value){
        this.type = type;
        this.value = value;
    }
    
    Constant(Integer value){
        this(Type.TInt, value);
    }
    
    Constant(Boolean value){
        this(Type.TBool, value);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public List<PInstruction> toCode() {
        List<PInstruction> code = new LinkedList<>();
        if (value instanceof Integer)
            code.add(new LoadConstant((Integer) value));
        else if (value instanceof Boolean)
            code.add(new LoadConstant((Boolean) value));
        else
            System.out.println("Constant value not an Integer or Boolean.");
        return code;
    }

    @Override
    public Boolean typeCheck() {
        return true;
    }

    @Override
    public void identifiers(IdTable idTable) {}
}
