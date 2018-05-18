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
public class ArrayAccess extends Variable {
    private Variable reference;
    private Expression index;
    
    public ArrayAccess(Variable reference, Expression index){
        super(reference.id);
        this.reference = reference;
        this.index = index;
    }
    
    @Override
    public Type getType(){ return this.reference.getType().dereference(); }

    @Override
    public String toCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean typeCheck() {
        Boolean res = index.typeCheck();
        if (!(reference.getType() instanceof Type.ArrayType)){
            System.out.println(
                    "Type error. Expected ArrayType for array access reference in line " 
                    + row + ", " + reference.getType() + " received");
            res = false;
        }
        if (!index.getType().equals(Type.TInt)){
            System.out.println(
                    "Type error. Expected TInt for array access index in line " 
                    + row + ", " + reference.getType() + " received");
            res = false;
        }
        return res; 
    }
}
