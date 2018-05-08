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
    private Expression index;
    
    public ArrayAccess(String id, Expression index){
        this.index = index;
        super(id);
    }
    
    public Type getType(){
        
    }
}
