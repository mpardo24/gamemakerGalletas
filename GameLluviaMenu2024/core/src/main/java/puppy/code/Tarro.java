package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Tarro {
    private Rectangle bucket;
    private Texture bucketImage;
    private Sound sonidoHerido;
    private int vidas = 3; // Vidas iniciales del jugador
    private int puntos = 0; // Puntos iniciales del jugador
    private int velx = 400;
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;

    public Tarro(Texture tex, Sound ss) {
        bucketImage = tex;
        sonidoHerido = ss;
    }

    public int getPuntos() {
        return puntos; // Retornar puntos acumulados correctamente
    }

    public int getVidas() {
        return vidas;
    }

    public Rectangle getArea() {
        return bucket;
    }

    public void sumarPuntos(int pp) {
        puntos += pp; // Sumar puntos al total acumulado
    }

    public void crear() {
        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;
    }

    public void dañar() {
        vidas--;
        herido = true;
        tiempoHerido = tiempoHeridoMax;

        // Reproducir sonido solo si está activado
        if (ConfiguracionJuegoSingleton.obtenerInstancia().audioEstaActivado()) {
            sonidoHerido.play();
        }
    }


    public void dibujar(SpriteBatch batch) {
        if (!herido)
            batch.draw(bucketImage, bucket.x, bucket.y);
        else {
            batch.draw(bucketImage, bucket.x, bucket.y + MathUtils.random(-5, 5));
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
    }

    public void actualizarMovimiento() {
        // Movimiento desde teclado
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= velx * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += velx * Gdx.graphics.getDeltaTime();

        // Lógica para teletransporte
        if (bucket.x < 0) {
            bucket.x = 800 - 64; // Teletransportar al lado derecho
        }
        if (bucket.x > 800 - 64) {
            bucket.x = 0; // Teletransportar al lado izquierdo
        }
    }

    public void destruir() {
        bucketImage.dispose();
    }

    public boolean estaHerido() {
        return herido;
    }

    public void sumarVida(int cantidad) {
        vidas += cantidad;
        if (vidas > 3) vidas = 3; // Limitar a 3 vidas máximo
    }
}
