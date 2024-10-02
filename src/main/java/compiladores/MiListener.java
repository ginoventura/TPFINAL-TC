package compiladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.Token;

public class MiListener extends compiladoresBaseListener {
    private Stack<TablaSimbolos> pilaDeContextos = new Stack<>();
    private TablaSimbolos tablaDeSimbolosGlobal;
    private MisErrores errores = new MisErrores();
    private boolean error = false;
    private boolean tieneRetorno = false;

    private Funcion funcionActual = null;

    public MiListener(TablaSimbolos tablaDeSimbolosGlobal) {
        this.tablaDeSimbolosGlobal = tablaDeSimbolosGlobal;
        this.pilaDeContextos.push(tablaDeSimbolosGlobal);
    }

    private String posicionDelToken(Token token) {
        return "[" + token.getLine() + ":" + token.getCharPositionInLine() + "]";
    }

    private String lineaDelToken(Token token) {
        return "[" + token.getLine() + "]";
    }

    @Override
    public void enterBloque(compiladoresParser.BloqueContext ctx) {
        pilaDeContextos.push(new TablaSimbolos());
    }

    @Override
    public void exitBloque(compiladoresParser.BloqueContext ctx) {
        pilaDeContextos.pop();
    }

    public void enterDeclaracion(compiladoresParser.DeclaracionContext ctx) {
        compiladoresParser.DeclaracionListaContext declListaCtx = ctx.declaracionLista();

        while (declListaCtx != null) {
            String nombreVariable = declListaCtx.ID().getText();
            TablaSimbolos contextoActual = pilaDeContextos.peek();

            if (contextoActual.existeVariable(nombreVariable)) {
                error = true;
                errores.variableDeclarada(posicionDelToken(ctx.getStart()), nombreVariable);
            } else {
                contextoActual.agregarVariable(nombreVariable);

                // Verificar si hay asignación y marcar como inicializada
                Variable variable = contextoActual.obtenerVariable(nombreVariable);
                if (declListaCtx.asignacion_opcional() != null && declListaCtx.asignacion_opcional().asign() != null) {
                    variable.setInicializado(true);
                }

                // Si estamos dentro de una función, agregar la variable a la lista de variables locales
                if (funcionActual != null) {
                    funcionActual.agregarVariableLocal(variable);
                }
            }
            declListaCtx = declListaCtx.siguienteDeclaracion().declaracionLista();
        }
    }

    @Override
    public void enterAsignacion(compiladoresParser.AsignacionContext ctx) {
        String nombreVariable = ctx.ID().getText();
        Variable variable = obtenerVariableDesdeContextos(nombreVariable);

        if (variable != null) {
            variable.setInicializado(true);
        } else {
            error = true;
            errores.variableNoDeclarada(posicionDelToken(ctx.getStart()), nombreVariable);
        }
    }

    @Override
    public void exitDeclaracion(compiladoresParser.DeclaracionContext ctx) {
        // Verificacion si el punto y coma está presente
        if (ctx.getStop().getText().trim().isEmpty() || !ctx.getStop().getText().endsWith(";")) {
            error = true;
            errores.faltaPuntoYComa(lineaDelToken(ctx.getStart()));
        }
    }

    @Override
    public void exitAsignacion(compiladoresParser.AsignacionContext ctx) {
        if (ctx.getStop().getText().trim().isEmpty() || !ctx.getStop().getText().endsWith(";")) {
            error = true;
            errores.faltaPuntoYComa(lineaDelToken(ctx.getStart()));
        }
    }

    @Override
    public void enterOperacion(compiladoresParser.OperacionContext ctx) {
        for (ParseTree child : ctx.children) {
            if (child instanceof compiladoresParser.TermContext) {
                compiladoresParser.TermContext termCtx = (compiladoresParser.TermContext) child;
                for (ParseTree termChild : termCtx.children) {
                    if (termChild instanceof compiladoresParser.FactorContext) {
                        compiladoresParser.FactorContext factorCtx = (compiladoresParser.FactorContext) termChild;
                        if (factorCtx.ID() != null) {
                            String nombreVariable = factorCtx.ID().getText();
                            Variable variable = obtenerVariableDesdeContextos(nombreVariable);
                            if (variable != null) {
                                if (!variable.isInicializado()) {
                                    errores.variableNoInicializada(posicionDelToken(ctx.getStart()), nombreVariable);
                                }
                                variable.setUsado(true);
                            } else {
                                error = true;
                                errores.variableNoDeclarada(posicionDelToken(ctx.getStart()), nombreVariable);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void enterTerm(compiladoresParser.TermContext ctx) {
        for (ParseTree child : ctx.children) {
            if (child instanceof compiladoresParser.FactorContext) {
                compiladoresParser.FactorContext factorCtx = (compiladoresParser.FactorContext) child;
                if (factorCtx.ID() != null) {
                    String nombreVariable = factorCtx.ID().getText();
                    Variable variable = obtenerVariableDesdeContextos(nombreVariable);
                    if (variable != null) {
                        variable.setUsado(true);
                    } else {
                        error = true;
                        errores.variableNoDeclarada(posicionDelToken(ctx.getStart()), nombreVariable);
                    }
                }
            }
        }
    }

    @Override
    public void enterDeclaracionFuncion(compiladoresParser.DeclaracionFuncionContext ctx) {
        String nombreFuncion = ctx.ID().getText();
        List<Variable> parametros = new ArrayList<>();

        if (ctx.parametros() != null) {
            for (compiladoresParser.ParamContext paramCtx : ctx.parametros().param()) {
                String nombreParametro = paramCtx.ID() != null ? paramCtx.ID().getText() : "param" + parametros.size();
                parametros.add(new Variable(nombreParametro));
            }
        }

        if (!tablaDeSimbolosGlobal.existeFuncion(nombreFuncion)) {
            tablaDeSimbolosGlobal.agregarFuncion(nombreFuncion, parametros);
        } else {
            error = true;
            errores.funcionDeclarada(posicionDelToken(ctx.getStart()), nombreFuncion);
        }
    }

    @Override
    public void enterDefinicionFuncion(compiladoresParser.DefinicionFuncionContext ctx) {
        String nombreFuncion = ctx.ID().getText();
        List<Variable> parametros = new ArrayList<>();
        tieneRetorno = false;

        // Extraer los parámetros y registrarlos
        if (ctx.parametros() != null) {
            for (compiladoresParser.ParamContext paramCtx : ctx.parametros().param()) {
                String nombreParametro = paramCtx.ID().getText();
                parametros.add(new Variable(nombreParametro));
            }
        }

        // Obtener la función de la tabla de símbolos global
        Funcion funcion = tablaDeSimbolosGlobal.obtenerFuncion(nombreFuncion);
        if (funcion != null) {
            if (funcion.isImplementada()) {
                error = true;
                errores.funcionDeclarada(posicionDelToken(ctx.getStart()), nombreFuncion);
            } else {
                // Verificar que los parámetros coincidan
                if (funcion.getParametros().size() != parametros.size()) {
                    error = true;
                    errores.funcionParametrosIncompatibles(posicionDelToken(ctx.getStart()), nombreFuncion);
                }
                funcion.setImplementada(true);
            }
        } else {
            // Si no existe, la agregamos
            tablaDeSimbolosGlobal.agregarFuncion(nombreFuncion, parametros);
            funcion = tablaDeSimbolosGlobal.obtenerFuncion(nombreFuncion); // Obtener la función recién agregada
            funcion.setImplementada(true);
        }
        funcionActual = funcion;

        // Crear un nuevo contexto para la función y agregar los parámetros
        TablaSimbolos contextoFuncion = new TablaSimbolos();
        for (Variable param : parametros) {
            contextoFuncion.agregarVariable(param.getNombre());
        }
        pilaDeContextos.push(contextoFuncion); // Añadir el nuevo contexto a la pila
    }

    @Override
    public void exitDefinicionFuncion(compiladoresParser.DefinicionFuncionContext ctx) {
        if (!tieneRetorno) {
            String funcName = ctx.ID().getText();
            error = true;
            errores.funcionSinReturn(lineaDelToken(ctx.getStart()), funcName);
        }
        funcionActual = null;
        pilaDeContextos.pop();
    }

    @Override
    public void enterRetorno(compiladoresParser.RetornoContext ctx) {
        tieneRetorno = true;
    }

    @Override
    public void enterLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx) {
        String nombreFuncion = ctx.ID().getText();
        Funcion funcion = tablaDeSimbolosGlobal.obtenerFuncion(nombreFuncion);

        if (funcion != null) {
            funcion.setUsada(true);

            // Obtener los argumentos pasados a la función
            List<compiladoresParser.OperacionContext> argumentos = ctx.argumentos().operacion();

            for (compiladoresParser.OperacionContext argumento : argumentos) {
                String nombreVariable = argumento.getText();
                Variable variable = tablaDeSimbolosGlobal.obtenerVariable(nombreVariable);

                if (variable == null) {
                    error = true;
                    errores.variableNoDeclarada(posicionDelToken(argumento.start), nombreVariable);
                }
            }

        } else {
            error = true;
            errores.funcionNoDeclarada(posicionDelToken(ctx.getStart()), nombreFuncion);
        }
    }

    @Override
    public void exitLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx) {
        String nombreFuncion = ctx.ID().getText();

        // Verificar si la función existe en la tabla de símbolos
        Funcion funcion = tablaDeSimbolosGlobal.obtenerFuncion(nombreFuncion);
        if (funcion != null) {
            funcion.setUsada(true);
        } else {
            error = true;
            errores.funcionNoDeclarada(posicionDelToken(ctx.getStart()), nombreFuncion);
            return;
        }

        // Verificar los argumentos pasados en la llamada
        if (ctx.argumentos() != null) {
            for (compiladoresParser.OperacionContext arg : ctx.argumentos().operacion()) {
                String nombreVariable = arg.getText();
                Variable var = tablaDeSimbolosGlobal.obtenerVariable(nombreVariable);

                if (var != null) {
                    var.setUsado(true);
                } else {
                    error = true;
                }
            }
        }
    }

    private Variable obtenerVariableDesdeContextos(String nombreVariable) {
        // Busca la variable en todos los contextos, empezando por el más local
        for (int i = pilaDeContextos.size() - 1; i >= 0; i--) {
            TablaSimbolos contexto = pilaDeContextos.get(i);
            if (contexto.existeVariable(nombreVariable)) {
                return contexto.obtenerVariable(nombreVariable);
            }
        }
        return null;
    }

    public TablaSimbolos getTablaDeSimbolos() {
        return tablaDeSimbolosGlobal;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public void imprimirTablaDeSimbolos(TablaSimbolos tablaDeSimbolos) {
        final String RESET = "\u001B[0m";
        final String ROJO = "\u001B[31m";
        final String VERDE = "\u001B[32m";
        final String AMARILLO = "\u001B[33m";
        final String AZUL = "\u001B[34m";
        final String CYAN = "\u001B[36m";

        System.out.println(" ");
        
        System.out.println(AZUL + "---------------- Tabla de Símbolos ----------------" + RESET);
        System.out.println(" ");

        System.out.println(CYAN + "Variables Globales:" + RESET);
        for (Variable var : tablaDeSimbolos.getVariables().values()) {
            System.out.println("  Nombre: " + VERDE + var.getNombre() + RESET +
                    ", Inicializado: " + (var.isInicializado() ? VERDE + "Sí" + RESET : ROJO + "No" + RESET) +
                    ", Usado: " + (var.isUsado() ? VERDE + "Sí" + RESET : ROJO + "No" + RESET));
        }

        System.out.println("\n" + CYAN + "Funciones:" + RESET);
        if (tablaDeSimbolos.getFunciones().isEmpty()) {
            System.out.println("  " + ROJO + "No hay funciones declaradas en el programa." + RESET);
        } else {
            for (Funcion funcion : tablaDeSimbolos.getFunciones().values()) {
                System.out.println("  Funcion: " + VERDE + funcion.getNombre() + RESET +
                        ", Implementada: " + (funcion.isImplementada() ? VERDE + "Sí" + RESET : ROJO + "No" + RESET) +
                        ", Usada: " + (funcion.isUsada() ? VERDE + "Sí" + RESET : ROJO + "No" + RESET));

                System.out
                        .println("    Parámetros: " + AMARILLO + parametrosComoString(funcion.getParametros()) + RESET);

                if (funcion.getVariablesLocales().isEmpty()) {
                    System.out.println("    " + ROJO + "No hay variables locales declaradas." + RESET);
                } else {
                    System.out.println("    Variables Locales:");
                    for (Variable varLocal : funcion.getVariablesLocales()) {
                        System.out.println("      Nombre: " + VERDE + varLocal.getNombre() + RESET +
                                ", Inicializado: "
                                + (varLocal.isInicializado() ? VERDE + "Sí" + RESET : ROJO + "No" + RESET) +
                                ", Usado: " + (varLocal.isUsado() ? VERDE + "Sí" + RESET : ROJO + "No" + RESET));
                    }
                }
            }
        }

        System.out.println(" ");

        imprimirAdvertencias(tablaDeSimbolos);

        System.out.println(" ");
    }

    public static String parametrosComoString(List<Variable> parametros) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < parametros.size(); i++) {
            sb.append(parametros.get(i).getNombre());
            if (i < parametros.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void imprimirAdvertencias(TablaSimbolos tabla) {
        final String RESET = "\u001B[0m";
        final String AMARILLO = "\u001B[33m";

        for (Variable variable : tabla.getVariables().values()) {
            if (!variable.isInicializado()) {
                System.err.println(AMARILLO + "Advertencia: La variable global '" + variable.getNombre()
                        + "' fue declarada pero no inicializada." + RESET);
            }
            if (!variable.isUsado()) {
                System.err.println(AMARILLO + "Advertencia: La variable global '" + variable.getNombre()
                        + "' fue declarada pero nunca usada." + RESET);
            }
        }

        for (Funcion funcion : tabla.getFunciones().values()) {
            if (!funcion.isImplementada()) {
                System.err.println(AMARILLO + "Advertencia: La función '" + funcion.getNombre()
                        + "' fue declarada pero no implementada." + RESET);
            }
            if (!funcion.isUsada()) {
                System.err.println(AMARILLO + "Advertencia: La función '" + funcion.getNombre()
                        + "' fue declarada pero nunca usada." + RESET);
            }

            for (Variable variableLocal : funcion.getVariablesLocales()) {
                if (!variableLocal.isInicializado()) {
                    System.err.println(AMARILLO + "Advertencia: La variable local '" + variableLocal.getNombre()
                            + "' en la función '" + funcion.getNombre() + "' fue declarada pero no inicializada."
                            + RESET);
                }
                if (!variableLocal.isUsado()) {
                    System.err.println(AMARILLO + "Advertencia: La variable local '" + variableLocal.getNombre()
                            + "' en la función '" + funcion.getNombre() + "' fue declarada pero nunca usada." + RESET);
                }
            }
        }
    }
}