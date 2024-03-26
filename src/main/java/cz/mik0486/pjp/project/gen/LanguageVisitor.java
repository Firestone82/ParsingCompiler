// Generated from C:/Users/Pavel/OneDrive - VSB-TUO/Skola/Vysoka/6-Semestr/PJP/Project/src/main/antlr4/Project/Language.g4 by ANTLR 4.13.1
package cz.mik0486.pjp.project.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LanguageParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(LanguageParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(LanguageParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(LanguageParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(LanguageParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(LanguageParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(LanguageParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(LanguageParser.LiteralContext ctx);
}