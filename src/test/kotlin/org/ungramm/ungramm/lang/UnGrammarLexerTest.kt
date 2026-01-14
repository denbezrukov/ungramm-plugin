package org.ungramm.ungramm.lang

import com.intellij.psi.TokenType
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.ungramm.ungramm.lang.psi.UnGrammarTypes

class UnGrammarLexerTest : BasePlatformTestCase() {

    fun testTokens() {
        val lexer = UnGrammarLexerAdapter()
        lexer.start("Foo = Bar")

        assertEquals(UnGrammarTypes.IDENT, lexer.tokenType)
        assertEquals("Foo", lexer.tokenText)

        lexer.advance()
        assertEquals(TokenType.WHITE_SPACE, lexer.tokenType)

        lexer.advance()
        assertEquals(UnGrammarTypes.EQ, lexer.tokenType)
        assertEquals("=", lexer.tokenText)

        lexer.advance()
        assertEquals(TokenType.WHITE_SPACE, lexer.tokenType)

        lexer.advance()
        assertEquals(UnGrammarTypes.IDENT, lexer.tokenType)
        assertEquals("Bar", lexer.tokenText)

        lexer.advance()
        assertNull(lexer.tokenType)
    }

    fun testString() {
        val lexer = UnGrammarLexerAdapter()
        lexer.start("Name = 'ident'")

        lexer.advance() // IDENT
        lexer.advance() // WS
        lexer.advance() // =
        lexer.advance() // WS

        assertEquals(UnGrammarTypes.STRING, lexer.tokenType)
        assertEquals("'ident'", lexer.tokenText)
    }

    fun testComment() {
        val lexer = UnGrammarLexerAdapter()
        lexer.start("// comment\nFoo")

        assertEquals(UnGrammarTypes.COMMENT, lexer.tokenType)
        assertEquals("// comment", lexer.tokenText)

        lexer.advance()
        assertEquals(TokenType.WHITE_SPACE, lexer.tokenType)

        lexer.advance()
        assertEquals(UnGrammarTypes.IDENT, lexer.tokenType)
    }

    fun testOperators() {
        val lexer = UnGrammarLexerAdapter()
        lexer.start("| * ? : , ( )")

        assertEquals(UnGrammarTypes.PIPE, lexer.tokenType)
        lexer.advance()
        lexer.advance() // WS

        assertEquals(UnGrammarTypes.STAR, lexer.tokenType)
        lexer.advance()
        lexer.advance() // WS

        assertEquals(UnGrammarTypes.QUESTION, lexer.tokenType)
        lexer.advance()
        lexer.advance() // WS

        assertEquals(UnGrammarTypes.COLON, lexer.tokenType)
        lexer.advance()
        lexer.advance() // WS

        assertEquals(UnGrammarTypes.COMMA, lexer.tokenType)
        lexer.advance()
        lexer.advance() // WS

        assertEquals(UnGrammarTypes.LPAREN, lexer.tokenType)
        lexer.advance()
        lexer.advance() // WS

        assertEquals(UnGrammarTypes.RPAREN, lexer.tokenType)
    }
}
