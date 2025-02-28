// Generated from c:/Users/Asus/Escritorio/Gino/2023/FINAL-TC-2023/src/main/java/compiladores/compiladores.g4 by ANTLR 4.13.1

package compiladores;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class compiladoresLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MAIN=1, INT=2, RETURN=3, SUM=4, RES=5, MUL=6, DIV=7, COMA=8, IGUAL=9, 
		AND=10, OR=11, NOT=12, MAYOR=13, MENOR=14, MAYOR_IGUAL=15, MENOR_IGUAL=16, 
		EQL=17, DISTINTO=18, FOR=19, WHILE=20, IF=21, ELSE=22, LLA=23, LLC=24, 
		PA=25, PC=26, PYC=27, ID=28, ENTERO=29, WS=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"MAIN", "INT", "RETURN", "SUM", "RES", "MUL", "DIV", "COMA", "IGUAL", 
			"AND", "OR", "NOT", "MAYOR", "MENOR", "MAYOR_IGUAL", "MENOR_IGUAL", "EQL", 
			"DISTINTO", "FOR", "WHILE", "IF", "ELSE", "LLA", "LLC", "PA", "PC", "PYC", 
			"ID", "ENTERO", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'main'", "'int'", "'return'", "'+'", "'-'", "'*'", "'/'", "','", 
			"'='", "'&&'", "'||'", "'!'", "'>'", "'<'", "'>='", "'<='", "'=='", "'!='", 
			"'for'", "'while'", "'if'", "'else'", "'{'", "'}'", "'('", "')'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "MAIN", "INT", "RETURN", "SUM", "RES", "MUL", "DIV", "COMA", "IGUAL", 
			"AND", "OR", "NOT", "MAYOR", "MENOR", "MAYOR_IGUAL", "MENOR_IGUAL", "EQL", 
			"DISTINTO", "FOR", "WHILE", "IF", "ELSE", "LLA", "LLC", "PA", "PC", "PYC", 
			"ID", "ENTERO", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public compiladoresLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "compiladores.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001e\u009d\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b"+
		"\u0005\u001b\u0090\b\u001b\n\u001b\f\u001b\u0093\t\u001b\u0001\u001c\u0004"+
		"\u001c\u0096\b\u001c\u000b\u001c\f\u001c\u0097\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0000\u0000\u001e\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n"+
		"\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b"+
		"7\u001c9\u001d;\u001e\u0001\u0000\u0004\u0003\u0000AZ__az\u0004\u0000"+
		"09AZ__az\u0001\u000009\u0003\u0000\t\n\r\r  \u009e\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000"+
		"\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000"+
		"#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001"+
		"\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000"+
		"\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u0000"+
		"1\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001"+
		"\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000"+
		"\u0000\u0000;\u0001\u0000\u0000\u0000\u0001=\u0001\u0000\u0000\u0000\u0003"+
		"B\u0001\u0000\u0000\u0000\u0005F\u0001\u0000\u0000\u0000\u0007M\u0001"+
		"\u0000\u0000\u0000\tO\u0001\u0000\u0000\u0000\u000bQ\u0001\u0000\u0000"+
		"\u0000\rS\u0001\u0000\u0000\u0000\u000fU\u0001\u0000\u0000\u0000\u0011"+
		"W\u0001\u0000\u0000\u0000\u0013Y\u0001\u0000\u0000\u0000\u0015\\\u0001"+
		"\u0000\u0000\u0000\u0017_\u0001\u0000\u0000\u0000\u0019a\u0001\u0000\u0000"+
		"\u0000\u001bc\u0001\u0000\u0000\u0000\u001de\u0001\u0000\u0000\u0000\u001f"+
		"h\u0001\u0000\u0000\u0000!k\u0001\u0000\u0000\u0000#n\u0001\u0000\u0000"+
		"\u0000%q\u0001\u0000\u0000\u0000\'u\u0001\u0000\u0000\u0000){\u0001\u0000"+
		"\u0000\u0000+~\u0001\u0000\u0000\u0000-\u0083\u0001\u0000\u0000\u0000"+
		"/\u0085\u0001\u0000\u0000\u00001\u0087\u0001\u0000\u0000\u00003\u0089"+
		"\u0001\u0000\u0000\u00005\u008b\u0001\u0000\u0000\u00007\u008d\u0001\u0000"+
		"\u0000\u00009\u0095\u0001\u0000\u0000\u0000;\u0099\u0001\u0000\u0000\u0000"+
		"=>\u0005m\u0000\u0000>?\u0005a\u0000\u0000?@\u0005i\u0000\u0000@A\u0005"+
		"n\u0000\u0000A\u0002\u0001\u0000\u0000\u0000BC\u0005i\u0000\u0000CD\u0005"+
		"n\u0000\u0000DE\u0005t\u0000\u0000E\u0004\u0001\u0000\u0000\u0000FG\u0005"+
		"r\u0000\u0000GH\u0005e\u0000\u0000HI\u0005t\u0000\u0000IJ\u0005u\u0000"+
		"\u0000JK\u0005r\u0000\u0000KL\u0005n\u0000\u0000L\u0006\u0001\u0000\u0000"+
		"\u0000MN\u0005+\u0000\u0000N\b\u0001\u0000\u0000\u0000OP\u0005-\u0000"+
		"\u0000P\n\u0001\u0000\u0000\u0000QR\u0005*\u0000\u0000R\f\u0001\u0000"+
		"\u0000\u0000ST\u0005/\u0000\u0000T\u000e\u0001\u0000\u0000\u0000UV\u0005"+
		",\u0000\u0000V\u0010\u0001\u0000\u0000\u0000WX\u0005=\u0000\u0000X\u0012"+
		"\u0001\u0000\u0000\u0000YZ\u0005&\u0000\u0000Z[\u0005&\u0000\u0000[\u0014"+
		"\u0001\u0000\u0000\u0000\\]\u0005|\u0000\u0000]^\u0005|\u0000\u0000^\u0016"+
		"\u0001\u0000\u0000\u0000_`\u0005!\u0000\u0000`\u0018\u0001\u0000\u0000"+
		"\u0000ab\u0005>\u0000\u0000b\u001a\u0001\u0000\u0000\u0000cd\u0005<\u0000"+
		"\u0000d\u001c\u0001\u0000\u0000\u0000ef\u0005>\u0000\u0000fg\u0005=\u0000"+
		"\u0000g\u001e\u0001\u0000\u0000\u0000hi\u0005<\u0000\u0000ij\u0005=\u0000"+
		"\u0000j \u0001\u0000\u0000\u0000kl\u0005=\u0000\u0000lm\u0005=\u0000\u0000"+
		"m\"\u0001\u0000\u0000\u0000no\u0005!\u0000\u0000op\u0005=\u0000\u0000"+
		"p$\u0001\u0000\u0000\u0000qr\u0005f\u0000\u0000rs\u0005o\u0000\u0000s"+
		"t\u0005r\u0000\u0000t&\u0001\u0000\u0000\u0000uv\u0005w\u0000\u0000vw"+
		"\u0005h\u0000\u0000wx\u0005i\u0000\u0000xy\u0005l\u0000\u0000yz\u0005"+
		"e\u0000\u0000z(\u0001\u0000\u0000\u0000{|\u0005i\u0000\u0000|}\u0005f"+
		"\u0000\u0000}*\u0001\u0000\u0000\u0000~\u007f\u0005e\u0000\u0000\u007f"+
		"\u0080\u0005l\u0000\u0000\u0080\u0081\u0005s\u0000\u0000\u0081\u0082\u0005"+
		"e\u0000\u0000\u0082,\u0001\u0000\u0000\u0000\u0083\u0084\u0005{\u0000"+
		"\u0000\u0084.\u0001\u0000\u0000\u0000\u0085\u0086\u0005}\u0000\u0000\u0086"+
		"0\u0001\u0000\u0000\u0000\u0087\u0088\u0005(\u0000\u0000\u00882\u0001"+
		"\u0000\u0000\u0000\u0089\u008a\u0005)\u0000\u0000\u008a4\u0001\u0000\u0000"+
		"\u0000\u008b\u008c\u0005;\u0000\u0000\u008c6\u0001\u0000\u0000\u0000\u008d"+
		"\u0091\u0007\u0000\u0000\u0000\u008e\u0090\u0007\u0001\u0000\u0000\u008f"+
		"\u008e\u0001\u0000\u0000\u0000\u0090\u0093\u0001\u0000\u0000\u0000\u0091"+
		"\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092"+
		"8\u0001\u0000\u0000\u0000\u0093\u0091\u0001\u0000\u0000\u0000\u0094\u0096"+
		"\u0007\u0002\u0000\u0000\u0095\u0094\u0001\u0000\u0000\u0000\u0096\u0097"+
		"\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0001\u0000\u0000\u0000\u0098:\u0001\u0000\u0000\u0000\u0099\u009a\u0007"+
		"\u0003\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u009c\u0006"+
		"\u001d\u0000\u0000\u009c<\u0001\u0000\u0000\u0000\u0003\u0000\u0091\u0097"+
		"\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}