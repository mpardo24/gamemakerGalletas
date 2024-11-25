package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoCayendo {
    protected Rectangle area;
    protected Texture textura;
    private EstrategiaMovimiento estrategiaMovimiento;

    public ObjetoCayendo(Texture textura, float x, float y, float ancho, float alto, EstrategiaMovimiento estrategia) {
        this.textura = textura;
        this.area = new Rectangle(x, y, ancho, alto);
        this.estrategiaMovimiento = estrategia;
    }

    public void actualizarMovimiento(float deltaTime, boolean tarroHerido) {
        estrategiaMovimiento.mover(area, deltaTime, tarroHerido);
    }


    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, area.x, area.y, area.width, area.height);
    }

    public boolean colisionaConTarro(Tarro tarro) {
        return area.overlaps(tarro.getArea());
    }

    public boolean estaFueraDePantalla() {
        return area.y + area.height < 0;
    }

    public void setEstrategiaMovimiento(EstrategiaMovimiento nuevaEstrategia) {
        this.estrategiaMovimiento = nuevaEstrategia;
    }

    public Rectangle getArea() {
        return area;
    }

    public abstract void aplicarEfecto(Tarro tarro);
}
