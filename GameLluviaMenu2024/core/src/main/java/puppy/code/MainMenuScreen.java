package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    final GameLluviaMenu game;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture menuImagen;  // Nueva variable para cargar la imagen del menú

    public MainMenuScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Cargar la imagen del menú desde la carpeta de assets
        menuImagen = new Texture(Gdx.files.internal("menu_de_inicio.png"));  // Asegúrate de que la imagen esté en la carpeta "assets"
    }

    @Override
    public void render(float delta) {
        // Limpiar la pantalla con un color oscuro
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Actualizar la cámara
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Iniciar el batch para dibujar la imagen del menú
        batch.begin();

        // Dibujar la imagen del menú en toda la pantalla
        batch.draw(menuImagen, 0, 0, 800, 480);

        batch.end();

        // Detectar si se toca la pantalla para empezar el juego
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));  // Cambiar a la pantalla del juego principal
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
        // Liberar la imagen cuando ya no se necesite
        menuImagen.dispose();
    }
}
