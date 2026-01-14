package org.ungramm.ungramm.lang

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class UnGrammarCompletionTest : BasePlatformTestCase() {

    fun testCompletionOfRuleNames() {
        myFixture.configureByText(
            "test.ungram",
            """
            Foo = 'foo'

            Bar = 'bar'

            Baz = <caret>
            """.trimIndent()
        )

        val lookupElements = myFixture.completeBasic()

        assertNotNull(lookupElements)
        val completionStrings = lookupElements?.map { it.lookupString } ?: emptyList()

        // Should suggest both Foo and Bar
        assertTrue("Should contain Foo", completionStrings.contains("Foo"))
        assertTrue("Should contain Bar", completionStrings.contains("Bar"))
    }

    fun testCompletionInAlternation() {
        myFixture.configureByText(
            "test.ungram",
            """
            Foo = 'foo'

            Bar = 'bar'

            Baz = Foo | <caret>
            """.trimIndent()
        )

        val lookupElements = myFixture.completeBasic()

        assertNotNull(lookupElements)
        val completionStrings = lookupElements?.map { it.lookupString } ?: emptyList()

        // Should suggest both Foo and Bar
        assertTrue("Should contain Foo", completionStrings.contains("Foo"))
        assertTrue("Should contain Bar", completionStrings.contains("Bar"))
    }

    fun testCompletionWithPrefix() {
        myFixture.configureByText(
            "test.ungram",
            """
            FooBar = 'foo'

            FooBaz = 'bar'

            Bar = 'bar'

            Test = Foo<caret>
            """.trimIndent()
        )

        val lookupElements = myFixture.completeBasic()

        assertNotNull(lookupElements)
        val completionStrings = lookupElements?.map { it.lookupString } ?: emptyList()

        // Should suggest FooBar and FooBaz
        assertTrue("Should contain FooBar", completionStrings.contains("FooBar"))
        assertTrue("Should contain FooBaz", completionStrings.contains("FooBaz"))
    }

    fun testCompletionInMultipleFiles() {
        myFixture.addFileToProject("file1.ungram", "Foo = 'foo'")
        myFixture.addFileToProject("file2.ungram", "Bar = 'bar'")

        myFixture.configureByText(
            "file3.ungram",
            "Baz = <caret>"
        )

        val lookupElements = myFixture.completeBasic()

        assertNotNull(lookupElements)
        val completionStrings = lookupElements?.map { it.lookupString } ?: emptyList()

        // Should suggest rules from all files
        assertTrue("Should contain Foo", completionStrings.contains("Foo"))
        assertTrue("Should contain Bar", completionStrings.contains("Bar"))
    }

    fun testCompletionInLabel() {
        myFixture.configureByText(
            "test.ungram",
            """
            Foo = 'foo'

            Bar = label:<caret>
            """.trimIndent()
        )

        val lookupElements = myFixture.completeBasic()

        assertNotNull(lookupElements)
        val completionStrings = lookupElements?.map { it.lookupString } ?: emptyList()

        // Should suggest Foo
        assertTrue("Should contain Foo", completionStrings.contains("Foo"))
    }
}
