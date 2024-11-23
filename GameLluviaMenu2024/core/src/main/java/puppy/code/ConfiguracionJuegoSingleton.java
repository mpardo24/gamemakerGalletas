package puppy.code;

public class ConfiguracionJuegoSingleton {

    private static ConfiguracionJuegoSingleton instancia;
    private int puntuacionMaxima = 0;
    private int nivelDificultad = 1;
    private boolean audioActivado = true;

    private ConfiguracionJuegoSingleton() {}

    public static ConfiguracionJuegoSingleton obtenerInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionJuegoSingleton();
        }
        return instancia;
    }

    public int obtenerPuntuacionMaxima() {
        return puntuacionMaxima;
    }

    public void configurarPuntuacionMaxima(int puntuacion) {
        if (puntuacion > puntuacionMaxima) {
            puntuacionMaxima = puntuacion;
        }
    }

    public int obtenerNivelDificultad() {
        return nivelDificultad;
    }

    public void configurarNivelDificultad(int nivel) {
        nivelDificultad = nivel;
    }

    public boolean audioEstaActivado() {
        return audioActivado;
    }

    public void configurarAudioActivado(boolean activado) {
        audioActivado = activado;
    }
}
