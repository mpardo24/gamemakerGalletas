package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    private final GameLluviaMenu game;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final OrthographicCamera camera;
    private final Texture menuImagen;

    public MainMenuScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 480);
        this.menuImagen = new Texture(Gdx.files.internal("menu_de_inicio.png"));
    }

    @Override
    public void render(float delta) {
        ConfiguracionJuegoSingleton configuracion = ConfiguracionJuegoSingleton.obtenerInstancia();

        // Limpiar pantalla
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(menuImagen, 0, 0, 800, 480);
        font.getData().setScale(2.0f);

        // Mostrar opciones del menú
        font.draw(batch, "Presiona 1, 2 o 3 para seleccionar la dificultad", 100, 400);
        font.draw(batch, "Dificultad actual: " + obtenerTextoDificultad(configuracion.obtenerNivelDificultad()), 100, 350);
        font.draw(batch, "Presiona M para activar/desactivar el sonido", 100, 300);
        font.draw(batch, "Sonido: " + (configuracion.audioEstaActivado() ? "Activado" : "Desactivado"), 100, 250);
        font.draw(batch, "Usa las flechas para iniciar el juego", 100, 200);
        font.draw(batch, "Presiona ESC para salir", 100, 150);

        batch.end();

        // Entrada del usuario
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            configuracion.configurarNivelDificultad(1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            configuracion.configurarNivelDificultad(2);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            configuracion.configurarNivelDificultad(3);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            configuracion.configurarAudioActivado(!configuracion.audioEstaActivado());
        }

        // Iniciar juego solo con flechas
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
                Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }

        // Salir del juego
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    private String obtenerTextoDificultad(int dificultad) {
        switch (dificultad) {
            case 1: return "Fácil";
            case 2: return "Media";
            case 3: return "Difícil";
            default: return "Desconocida";
        }
    }

    @Override
    public void dispose() {
        menuImagen.dispose();
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void show() {
        ConfiguracionJuegoSingleton configuracion = ConfiguracionJuegoSingleton.obtenerInstancia();

        // Detener cualquier música que esté sonando si el audio está desactivado
        if (!configuracion.audioEstaActivado()) {
            Lluvia lluviaTemporal = new Lluvia(null, null, null, Gdx.audio.newMusic(Gdx.files.internal("rain.mp3")));
            lluviaTemporal.pausar();
            lluviaTemporal.destruir();
        }
    }
    @Override
    public void hide() {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
}
