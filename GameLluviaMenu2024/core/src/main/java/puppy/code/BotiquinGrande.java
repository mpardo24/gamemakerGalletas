package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class BotiquinGrande extends Botiquin {

    public BotiquinGrande(Texture textura, float x, float y, Sound sonido) {
        super(textura, x, y, 48, 48, new MovimientoZigzag(100, 150), sonido); // Movimiento Zigzag
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        if (tarro.getVidas() < 3) {
            tarro.sumarVida(3 - tarro.getVidas());
        } else {
            tarro.sumarPuntos(100);
        }
        reproducirLadrido(); // Reproduce el sonido
    }
}
