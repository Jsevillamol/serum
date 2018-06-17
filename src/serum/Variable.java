package serum;

import com.sun.istack.internal.NotNull;
import serum.codegen.Indirection;
import serum.codegen.LoadConstant;
import serum.codegen.PInstruction;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jsevillamol
 */
public class Variable extends Expression {

    /**Nombre de la variable.*/
    public String id;

    /**Declaración de la variable.*/
    private Declaration declaration;

    /**Este constructor solo se usa para que las clases hijo
     * se puedan crear sin especificar un id.*/
    protected Variable(){}

    public Variable(String id){
        this.id = id;
    }
    
    @Override
    public Type getType(){ return declaration.getType(); }

    @NotNull
    @Override
    public List<PInstruction> toCode() {
        List<PInstruction> code = this.toCodeL();
        code.add(new Indirection());
        return code;
    }

    /**Genera el codigo necesario para apilar la direccion de la variable.*/
    public List<PInstruction> toCodeL() {
        List<PInstruction> code = new LinkedList<>();
        code.add(new LoadConstant(declaration.getAddress()));
        return code;
    }

    @Override
    public Boolean typeCheck() {
        return true; 
    }

    @Override
    public void identifiers(IdTable idTable) { declaration = idTable.searchID(this); }
}
