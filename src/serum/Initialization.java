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
public class Initialization extends Declaration {
    
    private Expression rhs;
    
    public Initialization(Type type, String id, Expression rhs) {
        super(type, id);
        this.rhs = rhs;
    }
    
    @Override
    public Boolean typeCheck() {
        return rhs.getType().equals(type) && rhs.typeCheck();
    }

    @Override
    public String toCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
