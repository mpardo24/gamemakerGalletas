package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class BotiquinGrande extends Botiquin implements Collectible {

    // Constructor para inicializar el botiquín grande con su textura y posición
    public BotiquinGrande(Texture texture, float x, float y, Sound ladridoSound) {
        super(texture, x, y, 48.0F, 48.0F, ladridoSound);
    }
    // Implementación del efecto específico para el botiquín grande
    @Override
    public void aplicarEfecto(Tarro tarro) {
        // Si el jugador tiene menos de tres vidas, restaura todas las vidas hasta el máximo (3)
        if (tarro.getVidas() < 3) {
            tarro.sumarVida(3 - tarro.getVidas());
        } else {
            // Si ya tiene el máximo de vidas, otorga una mayor cantidad de puntos
            tarro.sumarPuntos(100);
        }
        // Reproduce un sonido al recoger el botiquín
        this.reproducirLadrido();
    }

    @Override
    public void collect(Tarro tarro) {
        // Llamamos a aplicarEfecto que ya está implementado
        this.aplicarEfecto(tarro);
    }
}
