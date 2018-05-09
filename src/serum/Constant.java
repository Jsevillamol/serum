/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serum;

/**
 *
 * @author jsevillamol
 */
public class Constant extends Expression{
    Type type;
    Object value;
    
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
    public String toCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean typeCheck() {
        return true;
    }
}
