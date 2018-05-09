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
public class Type {
    private BasicType baseType;
    private int indirectionLevel;
    
    public Type(BasicType baseType, int indirectionLevel){
        this.baseType = baseType;
        this.indirectionLevel = indirectionLevel;
    }
    
    public Type reference(){
        return new Type(this.baseType, this.indirectionLevel + 1);
    }
    
    public Type dereference(){
        if (this.indirectionLevel == 0) throw new UnsupportedOperationException("Basic types cannot be derefenced");
        return new Type(this.baseType, this.indirectionLevel - 1);
    }
    
    public enum BasicType {T_INT, T_BOOL};
    
    public static final Type TBool = new Type(BasicType.T_BOOL, 0);
    public static final Type TInt = new Type(BasicType.T_INT, 0);
}
