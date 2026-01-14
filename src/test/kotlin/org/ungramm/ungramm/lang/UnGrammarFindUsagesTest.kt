package org.ungramm.ungramm.lang

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.usageView.UsageInfo

class UnGrammarFindUsagesTest : BasePlatformTestCase() {

    fun testFindUsagesOfRule() {
        myFixture.configureByText(
            "test.ungram",
            """
            Fo<caret>o = 'foo'

            Bar = Foo | Baz

            Baz = Foo*
            """.trimIndent()
        )

        val usages = myFixture.findUsages(myFixture.elementAtCaret)

        // Should find 2 usages: in Bar and in Baz
        assertEquals(2, usages.size)

        val usageTexts = usages.mapNotNull { it.element?.text }.sorted()
        assertEquals(listOf("Foo", "Foo"), usageTexts)
    }

    fun testFindUsagesInMultipleFiles() {
        myFixture.addFileToProject("file1.ungram", "Foo = 'foo'")
        myFixture.addFileToProject("file2.ungram", "Bar = Foo")
        myFixture.configureByText(
            "file3.ungram",
            "Baz = Fo<caret>o"
        )

        val usages = myFixture.findUsages(myFixture.elementAtCaret)

        // Should find 2 usages: in file2 and file3
        assertEquals(2, usages.size)
    }

    fun testFindUsagesOfRuleWithNoUsages() {
        myFixture.configureByText(
            "test.ungram",
            """
            Fo<caret>o = 'foo'

            Bar = Baz
            """.trimIndent()
        )

        val usages = myFixture.findUsages(myFixture.elementAtCaret)

        // Should find no usages
        assertEquals(0, usages.size)
    }

    fun testFindUsagesFromReference() {
        myFixture.configureByText(
            "test.ungram",
            """
            Foo = 'foo'

            Bar = Fo<caret>o
            """.trimIndent()
        )

        // Navigate to declaration first
        val reference = myFixture.file.findReferenceAt(myFixture.caretOffset)
        assertNotNull("Reference should exist", reference)

        val resolved = reference?.resolve()
        assertNotNull("Reference should resolve to rule", resolved)

        // Now find usages of the resolved element
        val usages = myFixture.findUsages(resolved!!)

        // Should find 1 usage: in Bar
        assertEquals(1, usages.size)
        val usageElement = usages.first().element
        assertNotNull(usageElement)
        assertEquals("Foo", usageElement?.text)
    }

    fun testFindUsagesWithLabels() {
        myFixture.configureByText(
            "test.ungram",
            """
            Fo<caret>o = 'foo'

            Bar = label:Foo

            Baz = x:Foo y:Foo
            """.trimIndent()
        )

        val usages = myFixture.findUsages(myFixture.elementAtCaret)

        // Should find 3 usages: label:Foo, x:Foo, y:Foo
        assertEquals(3, usages.size)
    }
}
