package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public interface BotiquinFactory {
    Botiquin crearBotiquin(Texture textura, float x, float y, Sound sonido);
}
