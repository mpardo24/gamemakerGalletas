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
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    // Variable para el fondo
    private Texture backgroundImage;

    public GameOverScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

        // Inicializar la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);  // Ajusta esta resolución si es necesario

        // Cargar la imagen de fondo (asegúrate de que la imagen esté en assets)
        backgroundImage = new Texture(Gdx.files.internal("gameover_fondo.png"));
    }

    @Override
    public void render(float delta) {
        // Limpia la pantalla
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Dibujar la imagen de fondo
        batch.draw(backgroundImage, 0, 0, 800, 480);  // Ajustar a la resolución de tu pantalla

        // Puedes agregar más lógica aquí si es necesario

        batch.end();

        // Reiniciar el juego si se toca la pantalla
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        // Liberar recursos
        backgroundImage.dispose();
    }
}
