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
public abstract class Expression implements Typable, ASTNode {
    protected int row, col;
    
    public void setRowAndCol(int row, int col){
        this.row = row;
        this.col = col;
    }
    
}
