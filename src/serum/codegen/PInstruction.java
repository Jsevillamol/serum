package serum.codegen;

/**
 * Todas las intrucciones de codigo p implementarán esta interfaz.
 *
 * @author David Rubio
 */
public abstract class PInstruction {

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
