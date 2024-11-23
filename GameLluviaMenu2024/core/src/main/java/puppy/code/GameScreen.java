package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    private final GameLluviaMenu game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Tarro tarro;
    private Lluvia lluvia;
    private Texture backgroundImage;
    private Collectible botiquin;
    private Texture botiquinPequenoTexture;
    private Texture botiquinGrandeTexture;
    private Sound ladridoSound;
    private int puntosParaAumentar = 200;
    private float tiempoMensajeDificultad = 0;
    private boolean mostrarMensajeDificultad = false;

    private Texture corazonCompleto;
    private Texture corazonRoto;

    public GameScreen(GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800.0F, 480.0F);

        // Configuración de texturas y sonidos
        this.backgroundImage = new Texture(Gdx.files.internal("fondo.png"));
        this.botiquinPequenoTexture = new Texture(Gdx.files.internal("botiquinPequeno.png"));
        this.botiquinGrandeTexture = new Texture(Gdx.files.internal("botiquinGrande.png"));
        this.corazonCompleto = new Texture(Gdx.files.internal("corazonCompleto.png"));
        this.corazonRoto = new Texture(Gdx.files.internal("corazonRoto.png"));
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        this.ladridoSound = Gdx.audio.newSound(Gdx.files.internal("ladrido.mp3"));

        // Inicialización del jugador
        this.tarro = new Tarro(new Texture(Gdx.files.internal("bucket.png")), hurtSound);
        this.tarro.crear();

        // Inicialización de la lluvia
        Texture gota = new Texture(Gdx.files.internal("drop.png"));
        Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        this.lluvia = new Lluvia(gota, gotaMala, dropSound, rainMusic);
        this.lluvia.crear();
    }

    @Override
    public void render(float delta) {
        ConfiguracionJuegoSingleton configuracion = ConfiguracionJuegoSingleton.obtenerInstancia();

        // Verificar si se debe aumentar la dificultad
        if (tarro.getPuntos() >= puntosParaAumentar) {
            puntosParaAumentar += 200;
            mostrarMensajeDificultad = true;
            tiempoMensajeDificultad = 2.0f;
            lluvia.aumentarDificultad();
        }

        // Cambiar a pantalla de pausa
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new PausaScreen(game, this));
            return;
        }

        // Renderizado de la pantalla de juego
        ScreenUtils.clear(0.0F, 0.0F, 0.2F, 1.0F);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(backgroundImage, 0.0F, 0.0F, 800.0F, 480.0F);
        font.getData().setScale(2.0f);
        font.draw(batch, "Puntos: " + tarro.getPuntos(), 10, 470);
        font.draw(batch, "HighScore: " + configuracion.obtenerPuntuacionMaxima(), 400, 470); // Mostrar HighScore
        dibujarCorazones();

        // Actualizar jugador y gotas
        if (!tarro.estaHerido()) {
            tarro.actualizarMovimiento();
            if (!lluvia.actualizarMovimiento(tarro)) {
                if (tarro.getPuntos() > configuracion.obtenerPuntuacionMaxima()) {
                    configuracion.configurarPuntuacionMaxima(tarro.getPuntos());
                }
                game.setScreen(new GameOverScreen(game, tarro.getPuntos())); // Pasar puntaje actual a GameOverScreen
                dispose();
            }
        }

        tarro.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);

        // Manejo de botiquines
        gestionarBotiquines();

        // Mensaje de aumento de dificultad
        if (mostrarMensajeDificultad) {
            GlyphLayout layout = new GlyphLayout(font, "¡Dificultad aumentada!");
            float x = (camera.viewportWidth - layout.width) / 2;
            font.draw(batch, "¡Dificultad aumentada!", x, 240);
            tiempoMensajeDificultad -= delta;
            if (tiempoMensajeDificultad <= 0) {
                mostrarMensajeDificultad = false;
            }
        }

        batch.end();
    }

    private void dibujarCorazones() {
        float xOffset = 620;
        float yOffset = 420;
        for (int i = 0; i < 3; i++) {
            if (i < tarro.getVidas()) {
                batch.draw(corazonCompleto, xOffset, yOffset);
            } else {
                batch.draw(corazonRoto, xOffset, yOffset);
            }
            xOffset += 59;
        }
    }

    private void gestionarBotiquines() {
        if (botiquin == null && MathUtils.random(1, 100) < 5) {
            float x = MathUtils.random(0, 768);
            float y = 480.0F;
            botiquin = MathUtils.random(1, 100) < 75
                    ? new BotiquinPequeno(botiquinPequenoTexture, x, y, ladridoSound)
                    : new BotiquinGrande(botiquinGrandeTexture, x, y, ladridoSound);
        }

        if (botiquin != null) {
            ((Botiquin) botiquin).actualizarMovimiento(tarro);
            ((Botiquin) botiquin).dibujar(batch);

            if (((Botiquin) botiquin).getArea().overlaps(tarro.getArea())) {
                botiquin.collect(tarro);
                botiquin = null;
            }

            if (botiquin != null && ((Botiquin) botiquin).fueraDePantalla()) {
                botiquin = null;
            }
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {
        lluvia.continuar();
    }

    @Override
    public void hide() {
        // Pausar la música si salimos del GameScreen
        lluvia.pausar();
    }

    @Override
    public void pause() {
        lluvia.pausar();
        game.setScreen(new PausaScreen(game, this));
    }

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        tarro.destruir();
        lluvia.destruir();
        backgroundImage.dispose();
        botiquinPequenoTexture.dispose();
        botiquinGrandeTexture.dispose();
        ladridoSound.dispose();
        corazonCompleto.dispose();
        corazonRoto.dispose();
    }
}
