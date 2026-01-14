package org.ungramm.ungramm.lang.highlight

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import org.ungramm.ungramm.lang.UnGrammarLexerAdapter
import org.ungramm.ungramm.lang.psi.UnGrammarTypes

class UnGrammarSyntaxHighlighter : SyntaxHighlighterBase() {

    companion object {
        val KEYWORD = TextAttributesKey.createTextAttributesKey(
            "UNGRAMMAR.KEYWORD",
            DefaultLanguageHighlighterColors.KEYWORD
        )

        val RULE_NAME = TextAttributesKey.createTextAttributesKey(
            "UNGRAMMAR.RULE_NAME",
            DefaultLanguageHighlighterColors.INSTANCE_FIELD
        )

        val IDENTIFIER = TextAttributesKey.createTextAttributesKey(
            "UNGRAMMAR.IDENTIFIER",
            DefaultLanguageHighlighterColors.IDENTIFIER
        )

        val STRING = TextAttributesKey.createTextAttributesKey(
            "UNGRAMMAR.STRING",
            DefaultLanguageHighlighterColors.STRING
        )

        val COMMENT = TextAttributesKey.createTextAttributesKey(
            "UNGRAMMAR.COMMENT",
            DefaultLanguageHighlighterColors.LINE_COMMENT
        )

        val OPERATOR = TextAttributesKey.createTextAttributesKey(
            "UNGRAMMAR.OPERATOR",
            DefaultLanguageHighlighterColors.OPERATION_SIGN
        )

        val PARENTHESES = TextAttributesKey.createTextAttributesKey(
            "UNGRAMMAR.PARENTHESES",
            DefaultLanguageHighlighterColors.PARENTHESES
        )

        val LABEL = TextAttributesKey.createTextAttributesKey(
            "UNGRAMMAR.LABEL",
            DefaultLanguageHighlighterColors.METADATA
        )

        val BAD_CHARACTER = TextAttributesKey.createTextAttributesKey(
            "UNGRAMMAR.BAD_CHARACTER",
            HighlighterColors.BAD_CHARACTER
        )
    }

    override fun getHighlightingLexer(): Lexer = UnGrammarLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            UnGrammarTypes.IDENT -> arrayOf(IDENTIFIER)
            UnGrammarTypes.STRING -> arrayOf(STRING)
            UnGrammarTypes.COMMENT -> arrayOf(COMMENT)
            UnGrammarTypes.EQ -> arrayOf(OPERATOR)
            UnGrammarTypes.PIPE -> arrayOf(OPERATOR)
            UnGrammarTypes.STAR -> arrayOf(OPERATOR)
            UnGrammarTypes.QUESTION -> arrayOf(OPERATOR)
            UnGrammarTypes.COLON -> arrayOf(OPERATOR)
            UnGrammarTypes.COMMA -> arrayOf(OPERATOR)
            UnGrammarTypes.LPAREN, UnGrammarTypes.RPAREN -> arrayOf(PARENTHESES)
            TokenType.BAD_CHARACTER -> arrayOf(BAD_CHARACTER)
            else -> emptyArray()
        }
    }
}
