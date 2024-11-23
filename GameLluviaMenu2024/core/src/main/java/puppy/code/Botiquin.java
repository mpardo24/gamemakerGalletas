package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Botiquin {
    protected Rectangle area;
    protected Texture texture;
    protected float velocidadCaida = 100;  // Velocidad de caída en píxeles por segundo
    protected Sound ladridoSound;  // Nuevo atributo para el sonido del ladrido

    public Botiquin(Texture texture, float x, float y, float width, float height, Sound ladridoSound) {
        this.texture = texture;
        this.area = new Rectangle(x, y, width, height);
        this.ladridoSound = ladridoSound;  // Guardar el sonido
    }

    // Método abstracto para aplicar el efecto al tarro
    public abstract void aplicarEfecto(Tarro tarro);

    // Método para actualizar la posición de caída del botiquín
    public void actualizarMovimiento(Tarro tarro) {
        if (!tarro.estaHerido()) {  // Solo se moverá si el tarro no está herido
            area.y -= velocidadCaida * Gdx.graphics.getDeltaTime();
        }
    }

    public void dibujar(SpriteBatch batch) {
        batch.draw(texture, area.x, area.y, area.width, area.height);
    }

    public Rectangle getArea() {
        return area;
    }

    public boolean fueraDePantalla() {
        return area.y + area.height < 0;  // Retorna true si el botiquín salió de la pantalla
    }

    public void destruir() {
        texture.dispose();
    }

    // Reproducir el sonido del ladrido
    protected void reproducirLadrido() {
        // Reproducir sonido solo si está activado
        if (ConfiguracionJuegoSingleton.obtenerInstancia().audioEstaActivado() && ladridoSound != null) {
            ladridoSound.play();
        }
    }
}
