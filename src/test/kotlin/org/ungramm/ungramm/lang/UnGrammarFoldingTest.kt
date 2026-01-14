package org.ungramm.ungramm.lang

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class UnGrammarFoldingTest : BasePlatformTestCase() {

    fun testFoldingLongRule() {
        val file = myFixture.configureByText(
            "test.ungram",
            "Foo = 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g' | 'h' | 'i' | 'j' | 'k' | 'l' | 'm' | 'n' | 'o' | 'p'\n\nBar = 'bar'"
        )

        val foldingBuilder = UnGrammarFoldingBuilder()
        val descriptors = foldingBuilder.buildFoldRegions(file, myFixture.editor.document, false)

        // Should have 1 folding region for the long rule
        assertEquals(1, descriptors.size)

        val descriptor = descriptors[0]
        val placeholderText = foldingBuilder.getPlaceholderText(descriptor.element)

        assertEquals("Foo = ...", placeholderText)
    }

    fun testNoFoldingShortRule() {
        val file = myFixture.configureByText(
            "test.ungram",
            "Foo = 'bar'"
        )

        val foldingBuilder = UnGrammarFoldingBuilder()
        val descriptors = foldingBuilder.buildFoldRegions(file, myFixture.editor.document, false)

        // Should have no folding regions for short rules
        assertEquals(0, descriptors.size)
    }

    fun testFoldingMultilineRule() {
        val file = myFixture.configureByText(
            "test.ungram",
            """
            Foo = 'a'
              | 'b'
              | 'c'

            Bar = 'bar'
            """.trimIndent()
        )

        val foldingBuilder = UnGrammarFoldingBuilder()
        val descriptors = foldingBuilder.buildFoldRegions(file, myFixture.editor.document, false)

        // Should have 1 folding region for the multiline rule
        assertEquals(1, descriptors.size)

        val placeholderText = foldingBuilder.getPlaceholderText(descriptors[0].element)
        assertEquals("Foo = ...", placeholderText)
    }

    fun testFoldingMultipleRules() {
        val file = myFixture.configureByText(
            "test.ungram",
            """
            Foo = 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g' | 'h' | 'i' | 'j' | 'k' | 'l' | 'm' | 'n' | 'o' | 'p'

            Bar = 'x' | 'y' | 'z' | 'aa' | 'bb' | 'cc' | 'dd' | 'ee' | 'ff' | 'gg' | 'hh' | 'ii' | 'jj' | 'kk'

            Baz = 'short'
            """.trimIndent()
        )

        val foldingBuilder = UnGrammarFoldingBuilder()
        val descriptors = foldingBuilder.buildFoldRegions(file, myFixture.editor.document, false)

        // Should have 2 folding regions (Foo and Bar, but not Baz which is short)
        assertEquals(2, descriptors.size)

        val placeholderTexts = descriptors.mapNotNull { foldingBuilder.getPlaceholderText(it.element) }.sorted()
        assertEquals(listOf("Bar = ...", "Foo = ..."), placeholderTexts)
    }

    fun testIsNotCollapsedByDefault() {
        val foldingBuilder = UnGrammarFoldingBuilder()
        val file = myFixture.configureByText(
            "test.ungram",
            "Foo = 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g' | 'h' | 'i' | 'j' | 'k' | 'l' | 'm' | 'n' | 'o' | 'p'"
        )

        val descriptors = foldingBuilder.buildFoldRegions(file, myFixture.editor.document, false)

        // Check that folding is not collapsed by default
        assertFalse(foldingBuilder.isCollapsedByDefault(descriptors[0].element))
    }
}
