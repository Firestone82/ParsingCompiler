// Generated from C:/Users/Pavel/OneDrive - VSB-TUO/Skola/Vysoka/6-Semestr/PJP/Project/src/main/antlr4/Project/Language.g4 by ANTLR 4.13.1
package cz.mik0486.pjp.project.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LanguageParser}.
 */
public interface LanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(LanguageParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(LanguageParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(LanguageParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(LanguageParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(LanguageParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(LanguageParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(LanguageParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(LanguageParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(LanguageParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(LanguageParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(LanguageParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(LanguageParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(LanguageParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(LanguageParser.LiteralContext ctx);
}