package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {

    private final GameLluviaMenu game;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final OrthographicCamera camera;
    private final Texture backgroundImage;
    private final int puntajeReciente;

    public GameOverScreen(final GameLluviaMenu game, int puntajeReciente) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 480);
        this.backgroundImage = new Texture(Gdx.files.internal("gameover_fondo.png"));
        this.puntajeReciente = puntajeReciente;
    }

    @Override
    public void render(float delta) {
        ConfiguracionJuegoSingleton configuracion = ConfiguracionJuegoSingleton.obtenerInstancia();

        // Limpiar la pantalla
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Fondo del Game Over
        batch.draw(backgroundImage, 0, 0, 800, 480);

        // Mensajes de fin del juego
        font.getData().setScale(2.0f);
        font.draw(batch, "GAME OVER", 300, 350);
        font.draw(batch, "Tu puntuación: " + puntajeReciente, 280, 300); // Mostrar puntaje reciente
        font.draw(batch, "Puntuación máxima: " + configuracion.obtenerPuntuacionMaxima(), 250, 250);
        font.draw(batch, "Toque para reiniciar", 280, 200);

        batch.end();

        // Reiniciar el juego si el jugador toca la pantalla
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void dispose() {
        backgroundImage.dispose();
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void show() {}
    @Override
    public void hide() {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
}
