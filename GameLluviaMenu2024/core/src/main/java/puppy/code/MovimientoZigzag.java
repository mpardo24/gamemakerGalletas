package puppy.code;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class MovimientoZigzag implements EstrategiaMovimiento {
    private float velocidadCaida;
    private float amplitud;
    private float tiempo;
    private boolean moviendoDerecha;

    public MovimientoZigzag(float velocidadCaida, float amplitud) {
        this.velocidadCaida = velocidadCaida;
        this.amplitud = amplitud;
        this.tiempo = 0;
        this.moviendoDerecha = true;
    }

    @Override
    public void mover(Rectangle area, float deltaTime, boolean tarroHerido) {
        if (tarroHerido) return; // Si el tarro está herido, no mover el botiquín

        tiempo += deltaTime;
        area.y -= velocidadCaida * deltaTime;

        // Movimiento horizontal
        if (moviendoDerecha) {
            area.x += amplitud * deltaTime;
        } else {
            area.x -= amplitud * deltaTime;
        }

        // Rebote en los bordes
        if (area.x <= 0) {
            area.x = 0;
            moviendoDerecha = true;
        } else if (area.x + area.width >= 800) {
            area.x = 800 - area.width;
            moviendoDerecha = false;
        }
    }
}
