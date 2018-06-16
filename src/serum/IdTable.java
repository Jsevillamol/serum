package serum;

import java.util.HashMap;
import java.util.Stack;

/**
 * Esta clase implementa una tabla de simbolos con la interfaz propuesta
 * en los apuntes (pag. 93), pero con el nombre en inglés y sin metodo de
 * inicio de tabla, pues basta con la constructora por defecto.
 * @author David Rubio
 */
public class IdTable {

    /**Cada HashMap de la pila contiene las declaraciones de ese ambito.*/
    private Stack<HashMap<String, Declaration>> pilaAmbitos = new Stack<>();

    /**Cada entero representa la primera dirección que puede usar ese ambito.*/
    private Stack<Integer> rho = new Stack<>();

    /**Siguiente dirección de memoria disponible.*/
    private int nextRho = 5;

    public void openBlock(){
        pilaAmbitos.push(new HashMap<>());
        rho.push(nextRho);
    }

    public void closeBlock() {
        pilaAmbitos.pop();
        nextRho = rho.pop();
    }

    public int insertDeclaration(Declaration declaration){
        pilaAmbitos.peek().put(declaration.getId(), declaration);
        return nextRho++;
    }

    public Declaration searchID(String id) {
        for (HashMap<String, Declaration> hashMap : pilaAmbitos) {
            Declaration declaration = hashMap.get(id);
            if (declaration != null)
                return declaration;
        }
        //TODO error: identificador no encontrado
        System.out.println("Id not found.");
        return null;
    }
}
