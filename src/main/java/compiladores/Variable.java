package compiladores;

public class Variable {
    private String nombre;
    private boolean inicializado;
    private boolean usado;

    public Variable(String nombre) {
        this.nombre = nombre;
        this.inicializado = false;
        this.usado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isInicializado() {
        return inicializado;
    }

    public void setInicializado(boolean inicializado) {
        this.inicializado = inicializado;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }
}
