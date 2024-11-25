package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class BotiquinPequeno extends Botiquin {

    public BotiquinPequeno(Texture textura, float x, float y, Sound sonido) {
        super(textura, x, y, 32, 32, new MovimientoVertical(100), sonido); // Movimiento Vertical
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        if (tarro.getVidas() < 3) {
            tarro.sumarVida(1);
        } else {
            tarro.sumarPuntos(50);
        }
        reproducirLadrido(); // Reproduce el sonido
    }
}
