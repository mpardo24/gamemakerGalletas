package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class BotiquinPequeno extends Botiquin implements Collectible {
    // Constructor para inicializar el botiquín pequeño con su textura y posición
    public BotiquinPequeno(Texture texture, float x, float y, Sound ladridoSound) {
        super(texture, x, y, 32.0F, 32.0F, ladridoSound);
    }
    // Implementación del efecto específico para el botiquín pequeño
    @Override
    public void aplicarEfecto(Tarro tarro) {
        // Si el jugador tiene menos de tres vidas, se suma una vida
        if (tarro.getVidas() < 3) {
            tarro.sumarVida(1);
        } else {
            // Si ya tiene el máximo de vidas, se otorgan puntos adicionales
            tarro.sumarPuntos(50);
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
