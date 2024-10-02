package compiladores;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class MiVisitor extends compiladoresBaseVisitor<String> {
    private int cantTemp = 0;
    private int cantLabel = 0;
    private List<String> codigo = new ArrayList<>();

    // Codigos de colores para imprimir el código de tres direcciones
    final String RESET = "\u001B[0m";
    final String VERDE = "\u001B[32m";
    final String ROJO = "\u001B[31m";
    final String AZUL = "\u001B[34m";
    final String AMARILLO = "\u001B[33m";
    final String MAGENTA = "\u001B[35m";
    final String CIAN = "\u001B[36m";
    final String NARANJA = "\u001B[38;5;214m";

    private String newTemp() {
        return "t" + (cantTemp++);
    }

    private String newLabel() {
        return "L" + (cantLabel++);
    }

    public List<String> getcodigo() {
        return codigo;
    }

    @Override
    public String visitDeclaracion(compiladoresParser.DeclaracionContext ctx) {
        return visit(ctx.declaracionLista());
    }

    @Override
    public String visitDeclaracionLista(compiladoresParser.DeclaracionListaContext ctx) {
        String variable = ctx.ID().getText();

        // Verificar si existe una asignación
        if (ctx.asignacion_opcional() != null && ctx.asignacion_opcional().asign() != null) {
            String expr = visit(ctx.asignacion_opcional().asign()); // Obtener el valor de la operación
            codigo.add(variable + " = " + expr);
        } else {
            // Si la variable no tiene una asignación
            // codigo.add("// Advertencia: La variable '" + variable + "' fue declarada pero
            // no inicializada.");
            // Le asigno valor por defecto
            codigo.add(variable + " = 0");
        }

        // Si hay más declaraciones (por ejemplo, `int x, y;`)
        if (ctx.siguienteDeclaracion() != null) {
            visit(ctx.siguienteDeclaracion()); // Visitar la siguiente en la lista
        }

        return null;
    }

    @Override
    public String visitAsignacion(compiladoresParser.AsignacionContext ctx) {
        String id = ctx.ID().getText();
        String expr = visit(ctx.operacion());
        if (expr != null) {
            codigo.add(id + " = " + expr);
        }
        return null;
    }

    @Override
    public String visitOperacion(compiladoresParser.OperacionContext ctx) {
        // Manejo de incremento/decremento
        if (ctx.getText().contains("++")) {
            String variable = ctx.getText().replace("++", "").trim();
            String temp = newTemp();
            codigo.add(temp + " = " + variable + " + 1"); // temporal = variable + 1
            codigo.add(variable + " = " + temp); // asignamos temporal a la variable
            return temp;
        } else if (ctx.getText().contains("--")) {
            String variable = ctx.getText().replace("--", "").trim();
            String temp = newTemp();
            codigo.add(temp + " = " + variable + " - 1");
            codigo.add(variable + " = " + temp);
            return temp;
        } else if (ctx.aritmetica() != null) {
            // si es una operación aritmética
            return visit(ctx.aritmetica());
        } else if (ctx.getChildCount() == 3) {
            // si es una operacion lógica
            String izq = visit(ctx.getChild(0));
            String der = visit(ctx.getChild(2));
            String op = ctx.getChild(1).getText();
            String temp = newTemp(); // Temporal
            codigo.add(temp + " = " + izq + " " + op + " " + der);
            return temp;
        } else {
            // operaciones simples como números o variables
            return visitChildren(ctx);
        }
    }

    @Override
    public String visitAritmetica(compiladoresParser.AritmeticaContext ctx) {
        String izq = visit(ctx.term(0));
        for (int i = 1; i < ctx.term().size(); i++) {
            String der = visit(ctx.term(i));
            String op = ctx.getChild(2 * i - 1).getText();
            String temp = newTemp();
            codigo.add(temp + " = " + izq + " " + op + " " + der);
            izq = temp; // Actualizar el operando izquierdo para la siguiente operación
        }
        return izq;
    }

    @Override
    public String visitTerm(compiladoresParser.TermContext ctx) {
        String izq = visit(ctx.factor(0));
        for (int i = 1; i < ctx.factor().size(); i++) {
            String der = visit(ctx.factor(i));
            String op = ctx.getChild(2 * i - 1).getText(); // Ej: A + B * C. para i = 1, obtengo +:
            String temp = newTemp();
            codigo.add(temp + " = " + izq + " " + op + " " + der);
            izq = temp;
        }
        return izq;
    }

    @Override
    public String visitFactor(compiladoresParser.FactorContext ctx) {
        if (ctx.ENTERO() != null) {
            return ctx.ENTERO().getText();
        } else if (ctx.ID() != null) {
            return ctx.ID().getText();
        } else if (ctx.operacion() != null) {
            return visit(ctx.operacion());
        } else if (ctx.llamadaFuncion() != null) {
            return visit(ctx.llamadaFuncion());
        }
        return null;
    }

    @Override
    public String visitDefinicionFuncion(compiladoresParser.DefinicionFuncionContext ctx) {
        String nombreFuncion = ctx.ID().getText();
        String label = nombreFuncion;
        codigo.add(" \n ");
        codigo.add(label + ":");

        if (ctx.parametros() != null) {
            List<compiladoresParser.ParamContext> listaParam = ctx.parametros().param();
            for (int i = 0; i < listaParam.size(); i++) {
                String nombreParam = listaParam.get(i).ID().getText();
                codigo.add(nombreParam + " = param" + (i + 1));
            }
        }

        visit(ctx.bloque());
        return null;
    }

    @Override
    public String visitLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx) {
        String nombreFuncion = ctx.ID().getText();

        codigo.add("\n ");
        // Paso de los argumentos a la función
        List<compiladoresParser.OperacionContext> args = ctx.argumentos().operacion();
        for (int i = 0; i < args.size(); i++) {
            String argumento = visit(args.get(i));
            codigo.add("param" + (i + 1) + " = " + argumento);
        }

        String temp = newTemp();
        codigo.add(temp + " = call " + nombreFuncion);
        codigo.add("\n ");

        return temp;
    }

    @Override
    public String visitRetorno(compiladoresParser.RetornoContext ctx) {
        String retorno = visit(ctx.operacion());
        codigo.add("return " + retorno);
        return null;
    }

    @Override
    public String visitIif(compiladoresParser.IifContext ctx) {
        codigo.add("\n");
        String condicion = visit(ctx.operacion());
        String lblVerdadero = newLabel();
        String lblFalso = newLabel();

        codigo.add("if " + condicion + " goto " + lblVerdadero);
        codigo.add("goto " + lblFalso);

        codigo.add(lblVerdadero + ":");
        visit(ctx.bloque(0));

        if (ctx.ELSE() != null) {
            // Genera etiqueta de salida y el salto si hay un else
            String lblFin = newLabel();
            codigo.add("goto " + lblFin); // Salta al final después de la rama verdadera

            // Rama falsa (bloque del else)
            codigo.add(lblFalso + ":");
            visit(ctx.bloque(1)); // Visita bloque else

            codigo.add(lblFin + ":");
        } else {
            // Si no hay else
            codigo.add(lblFalso + ":");
        }

        return null;
    }

    @Override
    public String visitIwhile(compiladoresParser.IwhileContext ctx) {
        String lblInicio = newLabel();
        String lblVerdadero = newLabel();
        String lblFin = newLabel();

        codigo.add(lblInicio + ":");

        // Evaluación de la condición del while
        String cond = visit(ctx.operacion());
        codigo.add("if " + cond + " goto " + lblVerdadero); // Si la condición es verdadera, entra al bucle
        codigo.add("goto " + lblFin); // Si la condición es falsa, sale del bucle

        codigo.add(lblVerdadero + ":");
        visit(ctx.bloque()); // Ejecuta cuerpo del while

        // Al final del cuerpo, se vuelve a evaluar la condición
        codigo.add("goto " + lblInicio);

        // salida del bucle
        codigo.add(lblFin + ":");

        return null;
    }

    @Override
    public String visitIfor(compiladoresParser.IforContext ctx) {
        String lblInicio = newLabel();
        String lblVerdadero = newLabel();
        String lblFin = newLabel();

        visit(ctx.inicializacionFor());

        codigo.add("\n");
        codigo.add(lblInicio + ":");

        String cond = visit(ctx.operacion(0)); // Obtener la condición (ej: i < 5)
        codigo.add("if " + cond + " goto " + lblVerdadero); // Si la condición es verdadera, entra al cuerpo
        codigo.add("goto " + lblFin); // Si la condición es falsa, sale del bucle

        codigo.add(lblVerdadero + ":");
        visit(ctx.bloque()); // Ejecutar el cuerpo del for

        if (ctx.operacion(1) != null) { // Si hay una actualización
            visit(ctx.operacion(1)); // Visita la operación de incremento/decremento
        }

        codigo.add("goto " + lblInicio);
        codigo.add(lblFin + ":");
        codigo.add("\n");

        return null;
    }

    public void imprimirCodigoTresDir() {
        generarArchivoCodigoTresDir("codigoTresDirecciones.txt");
        System.out.println("----------- Código de tres direcciones ------------");
        System.out.println(" ");
        int lineaNum = 1;
        for (String linea : codigo) {
            String lineaTrimeada = linea.trim();
            if (!lineaTrimeada.isEmpty()) {
                linea = linea.replaceAll("(L[0-9]+)", NARANJA + "$1" + RESET);

                linea = linea.replaceAll("(t[0-9]+)", AMARILLO + "$1" + RESET);

                linea = linea.replaceAll("(param[0-9]+)", VERDE + "$1" + RESET);

                linea = linea.replaceAll("(\\+|\\-|\\*|\\/|>|<|>=|<=|==|!=|=)", ROJO + "$1" + RESET);

                linea = linea.replaceAll("\\b(if|goto|call|return)\\b", MAGENTA + "$1" + RESET);

                linea = linea.replaceAll("\\b([a-zA-Z_][a-zA-Z_0-9]*)\\b", CIAN + "$1" + RESET);

                System.out.println(lineaNum++ + ": " + linea);
            } else {
                System.out.println(" ");
            }
        }
        System.out.println(" ");
    }

    public List<String> obtenerVariablesNoUsadas(TablaSimbolos tabla) {
        List<String> variablesNoUsadas = new ArrayList<>();
        for (Variable variable : tabla.getVariables().values()) {
            if (!variable.isUsado()) {
                variablesNoUsadas.add(variable.getNombre());
            }
        }
        return variablesNoUsadas;
    }

    public List<String> verificarVariables(TablaSimbolos tabla) {
        List<String> codigoOptimizado = new ArrayList<>();
        List<String> variablesNoUsadas = obtenerVariablesNoUsadas(tabla);

        for (String linea : codigo) {
            boolean esVariableUsada = true;

            // Verifica si la línea contiene alguna de las variables no usadas
            for (String variable : variablesNoUsadas) {
                if (linea.contains(variable)) {
                    esVariableUsada = false;
                    break;
                }
            }
            if (esVariableUsada) {
                codigoOptimizado.add(linea);
            }
        }
        return codigoOptimizado;
    }

    public void imprimirCodigoOptimizado(TablaSimbolos tabla) {
        generarArchivoCodigoTresDirOptimizado("codigoTresDireccionesOpt.txt",tabla);
        List<String> codigoOptimizado = verificarVariables(tabla);
        System.out.println("----------- Código de tres direcciones optimizado ------------");
        int lineaNum = 1;
        for (String linea : codigoOptimizado) {
            String lineaTrimeada = linea.trim();
            if (!lineaTrimeada.isEmpty()) {
                linea = linea.replaceAll("(L[0-9]+)", NARANJA + "$1" + RESET);

                linea = linea.replaceAll("(t[0-9]+)", AMARILLO + "$1" + RESET);

                linea = linea.replaceAll("(param[0-9]+)", VERDE + "$1" + RESET);

                linea = linea.replaceAll("(\\+|\\-|\\*|\\/|>|<|>=|<=|==|!=|=)", ROJO + "$1" + RESET);

                linea = linea.replaceAll("\\b(if|goto|call|return)\\b", MAGENTA + "$1" + RESET);

                linea = linea.replaceAll("\\b([a-zA-Z_][a-zA-Z_0-9]*)\\b", CIAN + "$1" + RESET);

                System.out.println(lineaNum++ + ": " + linea);
            } else {
                System.out.println(" ");
            }
        }
        System.out.println(" ");
    }

    public void generarArchivoCodigoTresDir(String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            for (String linea : codigo) {
                String lineaTrimeada = linea.trim();
                if (!lineaTrimeada.isEmpty()) {
                    writer.write(linea + "\n");
                } else {
                    writer.write("\n");
                }
            }
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generarArchivoCodigoTresDirOptimizado(String nombreArchivo, TablaSimbolos tabla) {
        List<String> codigoOptimizado = verificarVariables(tabla);

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            for (String linea : codigoOptimizado) {
                String lineaTrimeada = linea.trim();
                if (!lineaTrimeada.isEmpty()) {
                    writer.write(linea + "\n");
                } else {
                    writer.write("\n");
                }
            }
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}