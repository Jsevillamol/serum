package serum;

/**
 *
 * @author jsevillamol
 */
public abstract class Type {

    /**Tipo básico de los booleanos.*/
    public final static Type TBool = new BasicType.TBool();

    /**Tipo básico de los enteros.*/
    public final static Type TInt = new BasicType.TInt();

    public static ArrayType array(Type baseType, int numberOfElements){
        return new ArrayType(baseType, numberOfElements);
    }

    /***/
    /*TODO cambiar el nombre de este método ya que no se corresponde con el significado habitual.*/
    public abstract Type dereference();

    /**@return El tamaño que ocupa un elemento de este tipo en memoria*/
    public abstract int getSize();

    @Override
    public boolean equals(Object o) {
        return o!=null && o.getClass() == this.getClass();
    }

    public abstract static class BasicType extends Type{

        public static class TBool extends BasicType{
            @Override
            public String toString() {return "TBool";}
        }

        public static class TInt extends BasicType{
            @Override
            public String toString() {return "TInt";}
        }

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
        public Type dereference(){ return baseType; }

        @Override
        public int getSize(){ return numberOfElements * baseType.getSize();}

        @Override
        public boolean equals(Object o) {
            if (o==null || !(o instanceof ArrayType))
                return false;
            ArrayType arrayType = (ArrayType) o;
            return this.baseType.equals(arrayType.baseType) &&
                   this.numberOfElements == arrayType.numberOfElements;
        }
    }
}
