package puppy.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameLluviaMenu extends Game {

    // Atributos principales de renderizado y fuente de texto
    private SpriteBatch batch;
    private BitmapFont font;
    private int higherScore = 0;  // Variable para almacenar el puntaje más alto

    // Método que se llama al iniciar el juego
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();  // Fuente por defecto

        // Mostrar el menú de inicio al arrancar el juego
        this.setScreen(new MainMenuScreen(this));
    }

    // Método de renderizado, llamado en cada fotograma
    @Override
    public void render() {
        super.render();
    }

    // Método para liberar recursos cuando el juego se cierra
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    // Método getter para obtener el SpriteBatch compartido en todo el juego
    public SpriteBatch getBatch() {
        return batch;
    }

    // Método getter para obtener la fuente de texto compartida en todo el juego
    public BitmapFont getFont() {
        return font;
    }

    // Método para obtener el puntaje más alto
    public int getHigherScore() {
        return higherScore;
    }

    // Método para establecer un nuevo puntaje más alto
    public void setHigherScore(int higherScore) {
        this.higherScore = higherScore;
    }
}
