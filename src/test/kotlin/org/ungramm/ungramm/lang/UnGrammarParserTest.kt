package org.ungramm.ungramm.lang

import com.intellij.testFramework.ParsingTestCase

class UnGrammarParserTest : ParsingTestCase("parser", "ungram", UnGrammarParserDefinition()) {

    override fun getTestDataPath(): String = "src/test/testData"

    fun testSimpleRule() {
        doTest(true)
    }

    fun testAlternation() {
        doTest(true)
    }

    fun testRepetition() {
        doTest(true)
    }

    fun testLabeledFields() {
        doTest(true)
    }

    fun testGrouping() {
        doTest(true)
    }

    fun testComments() {
        doTest(true)
    }

    fun testComplexGrammar() {
        doTest(true)
    }

    fun testGraphql() {
        doTest(true)
    }
}
