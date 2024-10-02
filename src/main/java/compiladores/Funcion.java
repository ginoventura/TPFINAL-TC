package compiladores;

import java.util.ArrayList;
import java.util.List;

public class Funcion {
    private String nombre;
    private boolean implementada;
    private boolean usada;
    private List<Variable> parametros;
    private List<Variable> variablesLocales;

    public Funcion(String nombre, List<Variable> parametros) {
        this.nombre = nombre;
        this.implementada = false;
        this.usada = false;
        this.parametros = parametros;
        this.variablesLocales = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isImplementada() {
        return implementada;
    }

    public void setImplementada(boolean implementada) {
        this.implementada = implementada;
    }

    public List<Variable> getParametros() {
        return parametros;
    }

    public void setParametros(List<Variable> parametros) {
        this.parametros = parametros;
    }

    public boolean isUsada() {
        return usada;
    }

    public void setUsada(boolean usada) {
        this.usada = usada;
    }

    public void agregarVariableLocal(Variable variable) {
        this.variablesLocales.add(variable);
    }

    public List<Variable> getVariablesLocales() {
        return variablesLocales;
    }
}
