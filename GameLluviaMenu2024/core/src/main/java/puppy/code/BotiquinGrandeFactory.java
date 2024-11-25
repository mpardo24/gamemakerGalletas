package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class BotiquinGrandeFactory implements BotiquinFactory {
    @Override
    public Botiquin crearBotiquin(Texture textura, float x, float y, Sound sonido) {
        return new BotiquinGrande(textura, x, y, sonido);
    }
}
