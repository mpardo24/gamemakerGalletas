package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final GameLluviaMenu game;
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
    private int puntosParaAumentar = 200;  // Se usará para aumentar dificultad cada 200 puntos
    private float tiempoMensajeDificultad = 0;  // Controla la duración del mensaje en pantalla
    private boolean mostrarMensajeDificultad = false;

    // Cargar texturas de los corazones
    private Texture corazonCompleto;
    private Texture corazonRoto;

    public GameScreen(GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        this.tarro = new Tarro(new Texture(Gdx.files.internal("bucket.png")), hurtSound);
        this.backgroundImage = new Texture(Gdx.files.internal("fondo.png"));
        Texture gota = new Texture(Gdx.files.internal("drop.png"));
        Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        this.lluvia = new Lluvia(gota, gotaMala, dropSound, rainMusic);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800.0F, 480.0F);
        this.batch = new SpriteBatch();
        this.tarro.crear();
        this.lluvia.crear();
        this.botiquinPequenoTexture = new Texture(Gdx.files.internal("botiquinPequeno.png"));
        this.botiquinGrandeTexture = new Texture(Gdx.files.internal("botiquinGrande.png"));
        this.ladridoSound = Gdx.audio.newSound(Gdx.files.internal("ladrido.mp3"));

        // Cargar imágenes de los corazones solo una vez
        this.corazonCompleto = new Texture(Gdx.files.internal("corazonCompleto.png"));
        this.corazonRoto = new Texture(Gdx.files.internal("corazonRoto.png"));
    }

    public void render(float delta) {
        // Verificar si los puntos han alcanzado el umbral para aumentar la dificultad
        if (tarro.getPuntos() >= puntosParaAumentar) {
            puntosParaAumentar += 200;  // Aumentar el umbral para el próximo incremento
            mostrarMensajeDificultad = true;
            tiempoMensajeDificultad = 2.0f;  // Mostrar el mensaje por 2 segundos
            lluvia.aumentarDificultad();  // Aumentar la dificultad en Lluvia
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new PausaScreen(game, this));
            return;
        }

        ScreenUtils.clear(0.0F, 0.0F, 0.2F, 1.0F);
        this.camera.update();
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();

        // Ajustar tamaño de fuente
        font.getData().setScale(2.0f);  // Aumentar el tamaño de todos los textos

        // Dibuja el fondo
        this.batch.draw(this.backgroundImage, 0.0F, 0.0F, 800.0F, 480.0F);
        this.font.draw(this.batch, "Galletas totales: " + this.tarro.getPuntos(), 5.0F, 475.0F);
        this.font.draw(this.batch, "HighScore: " + this.game.getHigherScore(), this.camera.viewportWidth / 2.0F - 50.0F, 475.0F);

        // Dibuja las vidas como corazones
        drawHearts();

        if (!this.tarro.estaHerido()) {
            this.tarro.actualizarMovimiento();
            if (!this.lluvia.actualizarMovimiento(this.tarro)) {
                if (this.game.getHigherScore() < this.tarro.getPuntos()) {
                    this.game.setHigherScore(this.tarro.getPuntos());
                }

                this.game.setScreen(new GameOverScreen(this.game));
                this.dispose();
            }
        }

        this.tarro.dibujar(this.batch);
        this.lluvia.actualizarDibujoLluvia(this.batch);

        // Verificar si se crea un nuevo botiquín
        if (this.botiquin == null && MathUtils.random(1, 100) < 5) {
            float x = (float)MathUtils.random(0, 768);
            float y = 480.0F;
            if (MathUtils.random(1, 100) < 75) {
                this.botiquin = new BotiquinPequeno(this.botiquinPequenoTexture, x, y, this.ladridoSound);
            } else {
                this.botiquin = new BotiquinGrande(this.botiquinGrandeTexture, x, y, this.ladridoSound);
            }
        }

        // Actualizar y dibujar botiquín si está presente
        if (this.botiquin != null) {
            ((Botiquin) this.botiquin).actualizarMovimiento(tarro);
            ((Botiquin) this.botiquin).dibujar(this.batch);

            // Detectar si el botiquín colisiona con el tarro (jugador)
            if (((Botiquin) this.botiquin).getArea().overlaps(this.tarro.getArea())) {
                this.botiquin.collect(this.tarro);  // Llama al método collect de la interfaz
                this.botiquin = null;
            }

            // Si el botiquín sale de la pantalla
            if (this.botiquin != null && ((Botiquin) this.botiquin).fueraDePantalla()) {
                this.botiquin = null;
            }
        }

        // Mostrar mensaje de aumento de dificultad
        if (mostrarMensajeDificultad) {
            // Usar GlyphLayout para centrar el texto
            GlyphLayout layout = new GlyphLayout(font, "¡Ha aumentado la dificultad!");
            float x = (this.camera.viewportWidth - layout.width) / 2; // Centrado
            this.font.draw(this.batch, "¡Ha aumentado la dificultad!", x, 240);
            tiempoMensajeDificultad -= delta;
            if (tiempoMensajeDificultad <= 0) {
                mostrarMensajeDificultad = false;  // Ocultar el mensaje después de 2 segundos
            }
        }

        this.batch.end();
    }

    private void drawHearts() {
        // Coordenadas para dibujar los corazones
        float xOffset = 620; // Posición x de los corazones
        float yPosition = 420; // Posición y de los corazones

        for (int i = 0; i < 3; i++) {
            if (i < tarro.getVidas()) {
                batch.draw(corazonCompleto, xOffset, yPosition);
            } else {
                batch.draw(corazonRoto, xOffset, yPosition);
            }
            xOffset +=59; // Espacio entre corazones
        }
    }

    public void resize(int width, int height) {}

    public void show() {
        this.lluvia.continuar();
    }

    public void hide() {}

    public void pause() {
        this.lluvia.pausar();
        this.game.setScreen(new PausaScreen(this.game, this));
    }

    public void resume() {}

    public void dispose() {
        this.tarro.destruir();
        this.lluvia.destruir();
        this.backgroundImage.dispose();
        this.botiquinPequenoTexture.dispose();
        this.botiquinGrandeTexture.dispose();
        this.ladridoSound.dispose();

        // Destruir texturas de los corazones al final
        corazonCompleto.dispose();
        corazonRoto.dispose();
    }
}
