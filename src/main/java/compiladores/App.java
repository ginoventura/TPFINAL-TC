package compiladores;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String entrada = "input/entrada.txt";

        System.out.println(" ");
        System.out.println(" ");

        System.out.println("---------------------------------------------------");
        System.out.println("| Final de Técnicas de Compilación - Ventura Gino |");
        System.out.println("---------------------------------------------------");

        try {
            // Cargar el archivo de código fuente
            CharStream input = CharStreams.fromFileName(entrada);

            // Crear el lexer con el input
            compiladoresLexer lexer = new compiladoresLexer(input);

            // Crear el token stream a partir del lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Crear el parser con el token stream
            compiladoresParser parser = new compiladoresParser(tokens);

            MisErrores errores = new MisErrores();

            parser.removeErrorListeners();
            parser.addErrorListener(errores);

            ParseTree tree = parser.programa();

            // Crear la tabla de símbolos
            TablaSimbolos tablaDeSimbolos = new TablaSimbolos();

            MiListener listener = new MiListener(tablaDeSimbolos);

            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, tree);

            if (!listener.getError() && !errores.hasError()) {
                listener.imprimirTablaDeSimbolos(tablaDeSimbolos);

                MiVisitor visitor = new MiVisitor();
                visitor.visit(tree);

                visitor.imprimirCodigoTresDir();

                visitor.imprimirCodigoOptimizado(tablaDeSimbolos);
            } else {
                System.out.println(" ");
                System.out.println("El archivo de entrada contiene errores. No se generará la tabla de símbolos");
                System.out.println(" ");

                return;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de código fuente: " + e.getMessage());
        }
    }
}

/* ------- CODIGOS PARA PROBAR EL PROGRAMA -------
 * -----------------------------------------------
 * - PROGRAMA COMPLETO:
 * int suma(int a, int b);
 * 
 * int x = 5;
 * int y = 10;
 * 
 * int resultado;
 * 
 * resultado = suma(x,y);
 * 
 * if (resultado > 10) {
 * resultado = resultado * 5;
 * } else {
 * resultado = resultado * 10;
 * }
 * 
 * int suma(int a, int b) {
 * return a + b;
 * }
 * 
 * -----------------------------------------------
 * - DECLARACIONES Y ASIGNACIONES:
 * int x = 5;
 * int y = 3;
 * int resultado;
 * resultado = x + y / 3;
 * 
 * -----------------------------------------------
 * - FUNCIONES:
 * int suma (int x, int y);
 * 
 * int x, int y;
 * 
 * int resultado = 0;
 * resultado = suma(x,y);
 * 
 * suma(x,y);
 * 
 * int suma(int a, int b) {
 * return a + b;
 * }
 * 
 * -----------------------------------------------
 * - IF:
 * int x = 5;
 * int y = 10;
 * if (x < y) {
 * x = x + 1;
 * } else {
 * x = x - 1;
 * }
 * 
 * if (x == y) {
 * y = y * 2;
 * }
 * 
 * return x;
 * 
 * -----------------------------------------------
 * - WHILE
 * int x = 10;
 * int y = 4;
 * while ((x > 0) && (y < 10)) {
 * x = x - 2;
 * }
 * return x;
 * 
 * -----------------------------------------------
 * - FOR
 * int x = 0;
 * for (int i = 0; i < 5; i++) {
 * x = x + 2;
 * }
 * return x;
 * 
 * -----------------------------------------------
 */