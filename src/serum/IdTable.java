package serum;

import java.util.HashMap;
import java.util.Stack;

/**
 * Esta clase implementa una tabla de simbolos con la interfaz propuesta
 * en los apuntes (pag. 93), pero con el nombre en inglés y sin método de
 * inicio de tabla, pues basta con la constructora por defecto.
 *
 * Está implementado como una pila de HashMaps, cada uno asociado a un ambito.
 * El HashMap de la cima esta asociado al ambito en el que estamos.
 *
 * @author David Rubio
 */
public class IdTable {

    /**Cada HashMap de la pila contiene las declaraciones de ese ambito.*/
    private Stack<HashMap<String, Declaration>> pilaAmbitos = new Stack<>();

    /**Cada entero representa la primera dirección que puede usar ese ambito.*/
    private Stack<Integer> rho = new Stack<>();

    /**Siguiente dirección de memoria disponible.*/
    private int nextRho = 5;

    /**Para indicar que entramos en un nuevo ambito.
     * Simplemente se apila un HashMap y la primera dirección que se puede usar.*/
    public void openBlock(){
        pilaAmbitos.push(new HashMap<>());
        rho.push(nextRho);
    }

    /**Para indicar que cerramos el ambito en el que estabamos.
     * Desapila en HashMap y la primerea dirección disponible.*/
    public void closeBlock() {
        pilaAmbitos.pop();
        nextRho = rho.pop();
    }

    /**Añade una declaración de variable en la tabla.
     * @return Dirección de memoria asignada a esa variable.*/
    public int insertDeclaration(Declaration declaration){
        pilaAmbitos.peek().put(declaration.getId(), declaration);
        int returnValue = nextRho;
        nextRho += declaration.getType().getSize();
        return returnValue;
    }

    /**Busca la declaración de la variable dada.
     * @return Declaración de la variable dada.*/
    public Declaration searchID(Variable variable) {
        for (HashMap<String, Declaration> hashMap : pilaAmbitos) {
            Declaration declaration = hashMap.get(variable.id);
            if (declaration != null)
                return declaration;
        }
        //TODO error: identificador no encontrado
        System.out.println("Id " + variable.id + " not found.");
        return null;
    }
}
