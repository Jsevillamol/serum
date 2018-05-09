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
        return index.equals(Type.TInt) && index.typeCheck(); 
    }
}
