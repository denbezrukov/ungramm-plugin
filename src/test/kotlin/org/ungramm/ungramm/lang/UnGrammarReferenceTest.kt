package org.ungramm.ungramm.lang

import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.ungramm.ungramm.lang.psi.UnGrammarAtom
import org.ungramm.ungramm.lang.psi.UnGrammarFile
import org.ungramm.ungramm.lang.psi.UnGrammarRule
import org.ungramm.ungramm.lang.psi.UnGrammarUtil

class UnGrammarReferenceTest : BasePlatformTestCase() {

    fun testFindRulesInFile() {
        val file = myFixture.configureByText(
            "test.ungram",
            "Foo = 'foo'"
        ) as UnGrammarFile

        val rules = PsiTreeUtil.getChildrenOfTypeAsList(file, UnGrammarRule::class.java)
        assertEquals("Should find 1 rule", 1, rules.size)

        val ruleNames = rules.mapNotNull { it.name }
        assertTrue("Should contain Foo", ruleNames.contains("Foo"))
    }

    fun testFindRuleByName() {
        myFixture.addFileToProject("test1.ungram", "Foo = 'foo'")
        myFixture.addFileToProject("test2.ungram", "Bar = 'bar'")

        val rule = UnGrammarUtil.findRule(project, "Foo")
        assertNotNull("Should find rule Foo", rule)
        assertEquals("Foo", rule?.name)

        val notFound = UnGrammarUtil.findRule(project, "NotExists")
        assertNull("Should not find non-existent rule", notFound)
    }

    fun testAtomHasReference() {
        // Create a test file with Foo rule
        myFixture.addFileToProject("foo.ungram", "Foo = 'foo'")

        // Create another file that references it
        val file = myFixture.configureByText(
            "test.ungram",
            "Bar = Foo"
        ) as UnGrammarFile

        val atoms = PsiTreeUtil.findChildrenOfType(file, UnGrammarAtom::class.java)
        val fooAtom = atoms.firstOrNull { it.text == "Foo" }

        assertNotNull("Should find Foo atom", fooAtom)

        val reference = fooAtom?.reference
        assertNotNull("Atom should have reference", reference)

        val resolved = reference?.resolve()
        assertNotNull("Reference should resolve", resolved)
        assertTrue("Should resolve to UnGrammarRule", resolved is UnGrammarRule)
        assertEquals("Should resolve to Foo rule", "Foo", (resolved as? UnGrammarRule)?.name)
    }

    fun testReferenceToUndefinedRule() {
        val file = myFixture.configureByText(
            "test.ungram",
            "Bar = Undefined"
        ) as UnGrammarFile

        val atoms = PsiTreeUtil.findChildrenOfType(file, UnGrammarAtom::class.java)
        val undefinedAtom = atoms.firstOrNull { it.text == "Undefined" }

        assertNotNull("Should find Undefined atom", undefinedAtom)

        val reference = undefinedAtom?.reference
        assertNotNull("Atom should have reference", reference)

        val resolved = reference?.resolve()
        assertNull("Reference to undefined should not resolve", resolved)
    }

    fun testRuleNameIdentifier() {
        val file = myFixture.configureByText(
            "test.ungram",
            "Foo = 'foo'"
        ) as UnGrammarFile

        val rule = PsiTreeUtil.findChildOfType(file, UnGrammarRule::class.java)
        assertNotNull("Should find rule", rule)

        assertEquals("Foo", rule?.name)
        assertNotNull("Should have name identifier", rule?.nameIdentifier)
        assertEquals("Foo", rule?.nameIdentifier?.text)
    }

    fun testMultipleRulesWithSameName() {
        myFixture.addFileToProject("test1.ungram", "Foo = 'foo'")
        myFixture.addFileToProject("test2.ungram", "Foo = 'bar'")

        val rules = UnGrammarUtil.findRules(project)
        val fooRules = rules.filter { it.name == "Foo" }

        // Should find both (even though they're in different files)
        assertEquals("Should find 2 Foo rules", 2, fooRules.size)
    }
}
