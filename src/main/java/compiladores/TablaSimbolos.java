package compiladores;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class TablaSimbolos {
    private Map<String, Variable> variables;
    private Map<String, Funcion> funciones;

    public TablaSimbolos() {
        this.variables = new HashMap<>();
        this.funciones = new HashMap<>();
    }

    public void agregarVariable(String nombre) {
        variables.put(nombre, new Variable(nombre));
    }

    public void agregarFuncion(String nombre, List<Variable> parametros) {
        if (funciones.containsKey(nombre)) {
            Funcion funcionExistente = funciones.get(nombre);
            List<Variable> parametrosExistentes = funcionExistente.getParametros();

            // Verifica que el número de parámetros coincida
            if (parametros.size() != parametrosExistentes.size()) {
                throw new IllegalArgumentException(
                        "La función " + nombre + " ya está declarada con una cantidad diferente de parámetros.");
            }
            // Si el número de parámetros coincide, actualiza la implementación
            funcionExistente.setParametros(parametros);
            funcionExistente.setImplementada(true);
        } else {
            // Si la función no existe, la agregamos
            Funcion nuevaFuncion = new Funcion(nombre, parametros);
            funciones.put(nombre, nuevaFuncion);
        }
    }

    public Variable obtenerVariable(String nombre) {
        return variables.get(nombre);
    }

    public Funcion obtenerFuncion(String nombre) {
        return funciones.get(nombre);
    }

    public boolean existeVariable(String nombre) {
        return variables.containsKey(nombre);
    }

    public boolean existeFuncion(String nombre) {
        return funciones.containsKey(nombre);
    }

    public Map<String, Variable> getVariables() {
        return variables;
    }

    public Map<String, Funcion> getFunciones() {
        return funciones;
    }
}
