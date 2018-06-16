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

    
    public static ArrayType array(Type baseType, int numberOfElements){
        return new ArrayType(baseType, numberOfElements);
    }
    
    public abstract Type dereference();

    public abstract int getSize();
    
    public abstract static class BasicType extends Type{

        public static class TBool extends BasicType{}
        public static class TInt extends BasicType{}

        @Override
        public Type dereference(){
            throw new UnsupportedOperationException("Basic types cannot be dereferenced");
        }

        @Override
        public int getSize() { return 1; }
    }
    
    
    public static class ArrayType extends Type{
        
        private final Type baseType;
        private final int numberOfElements;
        
        ArrayType(Type baseType, int numberOfElements){
            this.baseType = baseType;
            this.numberOfElements = numberOfElements;
        }

        @Override
        public Type dereference(){
            return baseType;
        }

        @Override
        public int getSize(){ return numberOfElements * baseType.getSize();}
    
    }
}
