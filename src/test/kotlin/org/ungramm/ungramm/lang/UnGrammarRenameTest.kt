package org.ungramm.ungramm.lang

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class UnGrammarRenameTest : BasePlatformTestCase() {

    fun testRenameRule() {
        myFixture.configureByText(
            "test.ungram",
            """
            Fo<caret>o = 'foo'

            Bar = Foo
            """.trimIndent()
        )

        myFixture.renameElementAtCaret("NewName")

        myFixture.checkResult(
            """
            NewName = 'foo'

            Bar = NewName
            """.trimIndent()
        )
    }

    fun testRenameRuleWithMultipleUsages() {
        myFixture.configureByText(
            "test.ungram",
            """
            Fo<caret>o = 'foo'

            Bar = Foo | Baz

            Baz = Foo*
            """.trimIndent()
        )

        myFixture.renameElementAtCaret("RenamedFoo")

        myFixture.checkResult(
            """
            RenamedFoo = 'foo'

            Bar = RenamedFoo | Baz

            Baz = RenamedFoo*
            """.trimIndent()
        )
    }

    fun testRenameFromUsage() {
        myFixture.configureByText(
            "test.ungram",
            """
            Foo = 'foo'

            Bar = Fo<caret>o
            """.trimIndent()
        )

        myFixture.renameElementAtCaret("RenamedFoo")

        myFixture.checkResult(
            """
            RenamedFoo = 'foo'

            Bar = RenamedFoo
            """.trimIndent()
        )
    }

    fun testRenameRuleWithLabel() {
        myFixture.configureByText(
            "test.ungram",
            """
            Fo<caret>o = 'foo'

            Bar = label:Foo

            Baz = x:Foo y:Foo
            """.trimIndent()
        )

        myFixture.renameElementAtCaret("NewFoo")

        myFixture.checkResult(
            """
            NewFoo = 'foo'

            Bar = label:NewFoo

            Baz = x:NewFoo y:NewFoo
            """.trimIndent()
        )
    }

    fun testInlineRename() {
        myFixture.configureByText(
            "test.ungram",
            """
            Fo<caret>o = 'foo'

            Bar = Foo
            """.trimIndent()
        )

        // Test that inline rename is available
        val element = myFixture.elementAtCaret
        assertNotNull("Element at caret should not be null", element)
        assertTrue("Element should be renameable", element is org.ungramm.ungramm.lang.psi.UnGrammarRule)

        myFixture.renameElementAtCaret("InlineRenamed")

        myFixture.checkResult(
            """
            InlineRenamed = 'foo'

            Bar = InlineRenamed
            """.trimIndent()
        )
    }
}
