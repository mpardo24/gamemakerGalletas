package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
    private Array<Rectangle> rainDropsPos;
    private Array<Integer> rainDropsType;
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Sound dropSound;
    private Music rainMusic;
    private float velocidadCaida = 300;  // Velocidad base de caída
    private long tiempoEntreGotas = 1_000_000_000L;  // Tiempo base entre gotas

    public Lluvia(Texture gotaBuena, Texture gotaMala, Sound dropSound, Music rainMusic) {
        this.gotaBuena = gotaBuena;
        this.gotaMala = gotaMala;
        this.dropSound = dropSound;
        this.rainMusic = rainMusic;
    }

    public void crear() {
        rainDropsPos = new Array<>();
        rainDropsType = new Array<>();
        crearGotaDeLluvia();

        // Reproducir música solo si está activada
        if (ConfiguracionJuegoSingleton.obtenerInstancia().audioEstaActivado()) {
            rainMusic.setLooping(true);
            rainMusic.play();
        }
    }


    private void crearGotaDeLluvia() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        rainDropsPos.add(raindrop);
        rainDropsType.add(MathUtils.random(1, 10) < 5 ? 1 : 2);  // Gota dañina o buena
        lastDropTime = TimeUtils.nanoTime();
    }

    public void aumentarDificultad() {
        velocidadCaida += 50;  // Aumenta la velocidad de caída
        tiempoEntreGotas *= 0.9;  // Reduce el tiempo entre gotas en un 10%
    }

    public boolean actualizarMovimiento(Tarro tarro) {
        if (TimeUtils.nanoTime() - lastDropTime > tiempoEntreGotas) {
            crearGotaDeLluvia();
        }

        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            raindrop.y -= velocidadCaida * Gdx.graphics.getDeltaTime();

            if (raindrop.y + 64 < 0) {
                rainDropsPos.removeIndex(i);
                rainDropsType.removeIndex(i);
            }

            if (raindrop.overlaps(tarro.getArea())) {
                if (rainDropsType.get(i) == 1) {
                    tarro.dañar();
                    if (tarro.getVidas() <= 0) {
                        return false; // Termina el juego
                    }
                } else {
                    tarro.sumarPuntos(10);

                    // Reproducir sonido solo si está activado
                    if (ConfiguracionJuegoSingleton.obtenerInstancia().audioEstaActivado()) {
                        dropSound.play();
                    }
                }
                rainDropsPos.removeIndex(i);
                rainDropsType.removeIndex(i);
            }
        }
        return true;
    }


    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            batch.draw(rainDropsType.get(i) == 1 ? gotaMala : gotaBuena, raindrop.x, raindrop.y);
        }
    }

    public void setVelocidadCaida(float velocidad) {
        this.velocidadCaida = velocidad;
    }

    public void setTiempoEntreGotas(long tiempo) {
        this.tiempoEntreGotas = tiempo;
    }

    public void destruir() {
        if (rainMusic != null) {
            rainMusic.stop();
            rainMusic.dispose();
        }
    }

    public void pausar() {
        if (rainMusic != null && rainMusic.isPlaying()) {
            rainMusic.pause();
        }
    }

    public void continuar() {
        // Reanudar música solo si está activada
        if (ConfiguracionJuegoSingleton.obtenerInstancia().audioEstaActivado()) {
            rainMusic.play();
        }
    }
}

