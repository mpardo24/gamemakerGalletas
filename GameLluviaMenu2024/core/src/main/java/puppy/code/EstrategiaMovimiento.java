package puppy.code;

import com.badlogic.gdx.math.Rectangle;

public interface EstrategiaMovimiento {
    void mover(Rectangle area, float deltaTime, boolean tarroHerido);
}
