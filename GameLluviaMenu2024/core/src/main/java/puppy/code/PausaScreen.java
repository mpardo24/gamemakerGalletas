package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PausaScreen implements Screen {

    private final GameLluviaMenu game;
    private final GameScreen juego;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Texture pausaImagen;  // Imagen para la pausa

    public PausaScreen(final GameLluviaMenu game, GameScreen juego) {
        this.game = game;
        this.juego = juego;
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Cargar la imagen de pausa desde los assets
        pausaImagen = new Texture(Gdx.files.internal("pausa.png"));  // Imagen guardada como pausa.png en assets
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 1.0f, 0.5f);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Dibujar la imagen de pausa
        batch.draw(pausaImagen, 0, 0, 800, 480);  // Dibuja la imagen en toda la pantalla

        font.draw(batch, "", 100, 150);
        font.draw(batch, "", 100, 100);
        batch.end();

        // Verificar si se presiona "ESC" para reanudar el juego
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(juego);  // Regresa al juego
            dispose();
        }
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        // Liberar la imagen de pausa cuando ya no se necesite
        pausaImagen.dispose();
    }
}
