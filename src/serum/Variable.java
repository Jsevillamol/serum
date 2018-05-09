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
public class Variable extends Expression {
    protected String id;
    protected Declaration reference;
    
    public Variable(String id){
        this.id = id;
    }
    
    public void setDeclaration( Declaration dec) { this.reference = dec; }
    
    @Override
    public Type getType(){ return reference.getType(); }

    @Override
    public String toCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean typeCheck() {
        return true; 
    }
}
