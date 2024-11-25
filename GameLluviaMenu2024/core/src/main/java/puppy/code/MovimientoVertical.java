package puppy.code;

import com.badlogic.gdx.math.Rectangle;

public class MovimientoVertical implements EstrategiaMovimiento {
    private float velocidadCaida;

    public MovimientoVertical(float velocidadCaida) {
        this.velocidadCaida = velocidadCaida;
    }

    @Override
    public void mover(Rectangle area, float deltaTime, boolean tarroHerido) {
        if (tarroHerido) return; // Si el tarro está herido, no mover el botiquín

        area.y -= velocidadCaida * deltaTime;
    }
}
