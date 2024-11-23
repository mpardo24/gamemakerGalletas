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
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final OrthographicCamera camera;
    private final Texture pausaImagen;

    public PausaScreen(final GameLluviaMenu game, GameScreen juego) {
        this.game = game;
        this.juego = juego;
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 480);

        // Cargar la imagen de pausa
        this.pausaImagen = new Texture(Gdx.files.internal("pausa.png"));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 1.0f, 0.5f);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Dibujar la imagen de pausa
        batch.draw(pausaImagen, 0, 0, 800, 480);

        // Mostrar mensajes de pausa
        font.getData().setScale(2.0f);
        font.draw(batch, "Juego en pausa", 300, 400);
        font.draw(batch, "Usa las flechas para reanudar el juego", 200, 300);
        font.draw(batch, "Presiona ESC para volver al menu inicial", 200, 250);

        batch.end();

        // Reanudar el juego con las flechas
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
                Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            game.setScreen(juego); // Regresar al juego
            dispose();
        }

        // Volver al menú inicial con ESC
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }

    @Override
    public void dispose() {
        pausaImagen.dispose();
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
