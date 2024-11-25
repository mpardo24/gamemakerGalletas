package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Botiquin extends ObjetoCayendo {
    protected Sound ladridoSound;  // Nuevo atributo para el sonido del ladrido

    public Botiquin(Texture textura, float x, float y, float ancho, float alto, EstrategiaMovimiento estrategia, Sound ladridoSound) {
        super(textura, x, y, ancho, alto, estrategia);  // Llama al constructor de ObjetoCayendo
        this.ladridoSound = ladridoSound;  // Guardar el sonido
    }

    // Método abstracto para aplicar el efecto al tarro
    public abstract void aplicarEfecto(Tarro tarro);

    public void destruir() {
        textura.dispose();
    }

    // Reproducir el sonido del ladrido
    protected void reproducirLadrido() {
        if (ConfiguracionJuegoSingleton.obtenerInstancia().audioEstaActivado() && ladridoSound != null) {
            ladridoSound.play();
        }
    }
}
