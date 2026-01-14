package org.ungramm.ungramm.lang

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class UnGrammarHighlighterTest : BasePlatformTestCase() {

    fun testRuleHighlighting() {
        myFixture.configureByText(
            "test.ungram",
            "Foo = Bar"
        )
        myFixture.checkHighlighting()
    }

    fun testCommentHighlighting() {
        myFixture.configureByText(
            "test.ungram",
            "// comment\nFoo = Bar"
        )
        myFixture.checkHighlighting()
    }

    fun testStringHighlighting() {
        myFixture.configureByText(
            "test.ungram",
            "Foo = 'string'"
        )
        myFixture.checkHighlighting()
    }

    fun testOperatorHighlighting() {
        myFixture.configureByText(
            "test.ungram",
            "Foo = A | B*"
        )
        myFixture.checkHighlighting()
    }

    fun testLabelHighlighting() {
        myFixture.configureByText(
            "test.ungram",
            "Foo = label:Bar"
        )
        myFixture.checkHighlighting()
    }

    fun testGraphqlSample() {
        // Just verify syntax highlighting doesn't crash on complex grammar
        myFixture.configureByText(
            "test.ungram",
            "GraphqlOperationType = value_token:('query' | 'mutation' | 'subscription')"
        )
        myFixture.checkHighlighting()
    }
}
