package org.ungramm.ungramm.lang

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class UnGrammarFormatterTest : BasePlatformTestCase() {

    fun testFormatSpacesAroundEquals() {
        val file = myFixture.configureByText(
            "test.ungram",
            "Foo='foo'"
        )

        WriteCommandAction.runWriteCommandAction(project) {
            CodeStyleManager.getInstance(project).reformat(file)
        }

        assertEquals("Foo = 'foo'", file.text)
    }

    fun testFormatSpacesAroundPipe() {
        val file = myFixture.configureByText(
            "test.ungram",
            "Foo = A|B|C"
        )

        WriteCommandAction.runWriteCommandAction(project) {
            CodeStyleManager.getInstance(project).reformat(file)
        }

        assertEquals("Foo = A | B | C", file.text)
    }

    fun testFormatNoSpaceBeforePostfix() {
        val file = myFixture.configureByText(
            "test.ungram",
            "Foo=Bar?"
        )

        WriteCommandAction.runWriteCommandAction(project) {
            CodeStyleManager.getInstance(project).reformat(file)
        }

        assertEquals("Foo = Bar?", file.text)
    }

    fun testFormatNoSpaceAroundColon() {
        val file = myFixture.configureByText(
            "test.ungram",
            "Foo = label : Bar"
        )

        WriteCommandAction.runWriteCommandAction(project) {
            CodeStyleManager.getInstance(project).reformat(file)
        }

        assertEquals("Foo = label:Bar", file.text)
    }

    fun testFormatParentheses() {
        val file = myFixture.configureByText(
            "test.ungram",
            "Foo = ( A | B )"
        )

        WriteCommandAction.runWriteCommandAction(project) {
            CodeStyleManager.getInstance(project).reformat(file)
        }

        assertEquals("Foo = (A | B)", file.text)
    }

    fun testFormatComplexRule() {
        val file = myFixture.configureByText(
            "test.ungram",
            "Rule=name:IDENT'='body:Alternation"
        )

        WriteCommandAction.runWriteCommandAction(project) {
            CodeStyleManager.getInstance(project).reformat(file)
        }

        // Formatter adds space around =, but not around strings embedded in sequences
        assertEquals("Rule = name:IDENT'='body:Alternation", file.text)
    }

    fun testFormatAlreadyFormatted() {
        val input = "Foo = Bar | Baz*"
        val file = myFixture.configureByText("test.ungram", input)

        WriteCommandAction.runWriteCommandAction(project) {
            CodeStyleManager.getInstance(project).reformat(file)
        }

        assertEquals(input, file.text)
    }

    fun testFormatMultipleSpaces() {
        val file = myFixture.configureByText(
            "test.ungram",
            "Foo   =   Bar   |   Baz"
        )

        WriteCommandAction.runWriteCommandAction(project) {
            CodeStyleManager.getInstance(project).reformat(file)
        }

        assertEquals("Foo = Bar | Baz", file.text)
    }

    fun testFormatGraphql() {
        val file = myFixture.configureByText(
            "test.ungram",
            "GraphqlOperationType=value_token:('query'|'mutation'|'subscription')"
        )

        WriteCommandAction.runWriteCommandAction(project) {
            CodeStyleManager.getInstance(project).reformat(file)
        }

        // Formatter adds space around = and |
        assertEquals("GraphqlOperationType = value_token:('query' | 'mutation' | 'subscription')", file.text)
    }
}
