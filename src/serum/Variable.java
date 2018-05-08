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
    private String id;
    private Declaration reference;
    
    public Variable(String id){
        this.id = id;
    }
    
    public void setDeclaration( Declaration dec) { this.reference = dec; }
    public Type getType(){ return reference.getType(); }
}
