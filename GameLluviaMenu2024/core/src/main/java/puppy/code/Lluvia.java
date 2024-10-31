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
    private float velocidadCaida = 300;  // Velocidad base de caída de las gotas
    private long tiempoEntreGotas = 1000000000;  // Tiempo base entre gotas (nanosegundos)

    public Lluvia(Texture gotaBuena, Texture gotaMala, Sound ss, Music mm) {
        rainMusic = mm;
        dropSound = ss;
        this.gotaBuena = gotaBuena;
        this.gotaMala = gotaMala;
    }

    public void crear() {
        rainDropsPos = new Array<Rectangle>();
        rainDropsType = new Array<Integer>();
        crearGotaDeLluvia();
        rainMusic.setLooping(true);
        rainMusic.play();
    }

    private void crearGotaDeLluvia() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        rainDropsPos.add(raindrop);
        if (MathUtils.random(1, 10) < 5) {
            rainDropsType.add(1);  // Gota dañina
        } else {
            rainDropsType.add(2);  // Gota buena
        }
        lastDropTime = TimeUtils.nanoTime();
    }

    public void aumentarDificultad() {
        // Aumenta la velocidad de caída y reduce el tiempo entre gotas
        velocidadCaida += 50;  // Aumenta la velocidad de caída en 50
        tiempoEntreGotas = (long) (tiempoEntreGotas * 0.9);  // Reduce el tiempo entre gotas en un 10%
    }

    public boolean actualizarMovimiento(Tarro tarro) {
        // Crear nueva gota si ha pasado suficiente tiempo
        if (TimeUtils.nanoTime() - lastDropTime > tiempoEntreGotas) {
            crearGotaDeLluvia();
        }

        // Revisar el movimiento de las gotas
        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            raindrop.y -= velocidadCaida * Gdx.graphics.getDeltaTime();

            // Si la gota cae fuera de la pantalla, eliminarla
            if (raindrop.y + 64 < 0) {
                rainDropsPos.removeIndex(i);
                rainDropsType.removeIndex(i);
            }

            // Si la gota colisiona con el tarro
            if (raindrop.overlaps(tarro.getArea())) {
                if (rainDropsType.get(i) == 1) {  // Gota dañina
                    tarro.dañar();
                    if (tarro.getVidas() <= 0) {
                        return false;  // El juego termina si el tarro pierde todas las vidas
                    }
                } else {  // Gota buena
                    tarro.sumarPuntos(10);
                    dropSound.play();
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
            if (rainDropsType.get(i) == 1) {  // Gota dañina
                batch.draw(gotaMala, raindrop.x, raindrop.y);
            } else {  // Gota buena
                batch.draw(gotaBuena, raindrop.x, raindrop.y);
            }
        }
    }

    public void destruir() {
        dropSound.dispose();
        rainMusic.dispose();
    }

    public void pausar() {
        rainMusic.pause();
    }

    public void continuar() {
        rainMusic.play();
    }
}
