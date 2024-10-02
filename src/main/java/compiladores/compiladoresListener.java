// Generated from c:/Users/Asus/Escritorio/Gino/2023/FINAL-TC-2023/src/main/java/compiladores/compiladores.g4 by ANTLR 4.13.1

package compiladores;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link compiladoresParser}.
 */
public interface compiladoresListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(compiladoresParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(compiladoresParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#instrucciones}.
	 * @param ctx the parse tree
	 */
	void enterInstrucciones(compiladoresParser.InstruccionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#instrucciones}.
	 * @param ctx the parse tree
	 */
	void exitInstrucciones(compiladoresParser.InstruccionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(compiladoresParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(compiladoresParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(compiladoresParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(compiladoresParser.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#retorno}.
	 * @param ctx the parse tree
	 */
	void enterRetorno(compiladoresParser.RetornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#retorno}.
	 * @param ctx the parse tree
	 */
	void exitRetorno(compiladoresParser.RetornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(compiladoresParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(compiladoresParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#declaracionLista}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionLista(compiladoresParser.DeclaracionListaContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#declaracionLista}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionLista(compiladoresParser.DeclaracionListaContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#siguienteDeclaracion}.
	 * @param ctx the parse tree
	 */
	void enterSiguienteDeclaracion(compiladoresParser.SiguienteDeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#siguienteDeclaracion}.
	 * @param ctx the parse tree
	 */
	void exitSiguienteDeclaracion(compiladoresParser.SiguienteDeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#asignacion_opcional}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion_opcional(compiladoresParser.Asignacion_opcionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#asignacion_opcional}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion_opcional(compiladoresParser.Asignacion_opcionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#asign}.
	 * @param ctx the parse tree
	 */
	void enterAsign(compiladoresParser.AsignContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#asign}.
	 * @param ctx the parse tree
	 */
	void exitAsign(compiladoresParser.AsignContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(compiladoresParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(compiladoresParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#operacion}.
	 * @param ctx the parse tree
	 */
	void enterOperacion(compiladoresParser.OperacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#operacion}.
	 * @param ctx the parse tree
	 */
	void exitOperacion(compiladoresParser.OperacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#aritmetica}.
	 * @param ctx the parse tree
	 */
	void enterAritmetica(compiladoresParser.AritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#aritmetica}.
	 * @param ctx the parse tree
	 */
	void exitAritmetica(compiladoresParser.AritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(compiladoresParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(compiladoresParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(compiladoresParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(compiladoresParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#ifor}.
	 * @param ctx the parse tree
	 */
	void enterIfor(compiladoresParser.IforContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#ifor}.
	 * @param ctx the parse tree
	 */
	void exitIfor(compiladoresParser.IforContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#inicializacionFor}.
	 * @param ctx the parse tree
	 */
	void enterInicializacionFor(compiladoresParser.InicializacionForContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#inicializacionFor}.
	 * @param ctx the parse tree
	 */
	void exitInicializacionFor(compiladoresParser.InicializacionForContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#iwhile}.
	 * @param ctx the parse tree
	 */
	void enterIwhile(compiladoresParser.IwhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#iwhile}.
	 * @param ctx the parse tree
	 */
	void exitIwhile(compiladoresParser.IwhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#iif}.
	 * @param ctx the parse tree
	 */
	void enterIif(compiladoresParser.IifContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#iif}.
	 * @param ctx the parse tree
	 */
	void exitIif(compiladoresParser.IifContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#declaracionFuncion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionFuncion(compiladoresParser.DeclaracionFuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#declaracionFuncion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionFuncion(compiladoresParser.DeclaracionFuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#definicionFuncion}.
	 * @param ctx the parse tree
	 */
	void enterDefinicionFuncion(compiladoresParser.DefinicionFuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#definicionFuncion}.
	 * @param ctx the parse tree
	 */
	void exitDefinicionFuncion(compiladoresParser.DefinicionFuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(compiladoresParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(compiladoresParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(compiladoresParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(compiladoresParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 */
	void enterLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 */
	void exitLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void enterArgumentos(compiladoresParser.ArgumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void exitArgumentos(compiladoresParser.ArgumentosContext ctx);
}