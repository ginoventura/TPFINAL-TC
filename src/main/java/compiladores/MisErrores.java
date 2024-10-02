package compiladores;

import org.antlr.v4.runtime.*;

public class MisErrores extends BaseErrorListener {

    private static final String RESET = "\033[0m";
    private static final String ROJO = "\033[31m";
    private static final String AMARILLO = "\033[33m";

    private boolean error = false;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object simbolo, int linea, int posicion, String msj,
            RecognitionException e) {
        error = true;
        System.out.println(" ");
        System.err.println(ROJO + "Error de sintaxis detectado en la línea " + linea + ":" + posicion
                + ". Verifica tu código." + RESET);
    }

    public boolean hasError() {
        return error;
    }

    public void faltaPuntoYComa(String posicion) {
        System.out.println(AMARILLO + "Error en línea " + posicion + ": Se esperaba un punto y coma" + RESET);
    }

    public void variableDeclarada(String posicion, String id) {
        System.out.println(
                ROJO + "Error en línea " + posicion + ": '" + id + "' ya ha sido declarada en este contexto." + RESET);
    }

    public void variableNoDeclarada(String posicion, String id) {
        System.out.println(ROJO + "Error en línea " + posicion + ": '" + id + "' no ha sido declarado." + RESET);
    }

    public void variableNoInicializada(String posicion, String varName) {
        System.out.println(AMARILLO + "Advertencia en " + posicion
                + ": la variable ha sido declarada pero no inicializada." + RESET);
    }

    public void variableNoUtilizada(String posicion, String varName) {
        System.out.println(AMARILLO + "Advertencia en " + posicion + ": la variable " + varName
                + " ha sido declarada pero no utilizada." + RESET);
    }

    public void funcionDeclarada(String posicion, String id) {
        System.out
                .println(
                        ROJO + "Error en línea " + posicion + ": La función '" + id + "' ya ha sido declarada" + RESET);
    }

    public void funcionNoDeclarada(String posicion, String id) {
        System.out
                .println(
                        ROJO + "Error en línea " + posicion + ": La función '" + id + "' no ha sido declarada" + RESET);
    }

    public void cantidadArgsDistinta(String posicion, String id, int paramsEsperados, int argsActuales) {
        System.out.println(ROJO + "Error en línea " + posicion + ": La función '" + id + "' esperaba " + paramsEsperados
                + " parámetros, pero se recibieron " + argsActuales + "." + RESET);
    }

    public void funcionYaImplementada(String posicion, String funcion) {
        System.err.println(
                ROJO + "Error en línea " + posicion + ": La función '" + funcion + "' ya ha sido implementada."
                        + RESET);
    }

    public void funcionNoImplementada(String posicion, String funcion) {
        System.err.println(ROJO + "Error en línea " + posicion + ": La función '" + funcion
                + "' ha sido declarada, pero no implementada." + RESET);
    }

    public void funcionSinReturn(String posicion, String funcion) {
        System.err.println(ROJO + "Error en línea " + posicion + ": La función '" + funcion
                + "' debe tener una sentencia return." + RESET);
    }

    public void funcionParametrosIncompatibles(String posicion, String nombreFuncion) {
        System.err.println(ROJO + "Error: La función '" + nombreFuncion + "' declarada en " + posicion
                + " tiene parámetros incompatibles con la definición." + RESET);
    }
}
