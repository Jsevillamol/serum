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
public class Declaration extends Instruction {
    String id;
    Type type;
    
    public Declaration(Type type, String id){
        this.id = id;
        this.type = type;
    }
    
    public String getId(){return id;}
    public Type getType(){return type;}
    
}
