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
public abstract class Type {
    
    public final static Type TBool = new BasicType.TBool();
    public final static Type TInt = new BasicType.TInt();
    
    public final static ArrayType array(Type baseType, int dim){
        return new ArrayType(baseType, dim);
    }
    
    public abstract Type dereference();
    
    public abstract static class BasicType extends Type{
        public static class TBool extends BasicType{}
        public static class TInt extends BasicType{}
        
        public Type dereference(){
            throw new UnsupportedOperationException("Basic types cannot be dereferenced");
        }
    }
    
    
    public static class ArrayType extends Type{
        
        private final Type baseType;
        private final int dim;
        
        ArrayType(Type baseType, int dim){
            this.baseType = baseType;
            this.dim = dim;
        }
        
        public Type dereference(){
            return baseType;
        }
    
    }
}
