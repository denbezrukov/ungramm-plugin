package org.ungramm.ungramm.lang.psi

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import org.ungramm.ungramm.lang.UnGrammarIcons

class UnGrammarReference(element: PsiElement, textRange: TextRange) :
    PsiReferenceBase<PsiElement>(element, textRange), PsiPolyVariantReference {

    private val ruleName: String
        get() = element.text.substring(rangeInElement.startOffset, rangeInElement.endOffset)

    override fun resolve(): PsiElement? {
        val resolveResults = multiResolve(false)
        return if (resolveResults.size == 1) resolveResults[0].element else null
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val project = myElement.project
        val rule = UnGrammarUtil.findRule(project, ruleName)
        return if (rule != null) {
            arrayOf(PsiElementResolveResult(rule))
        } else {
            emptyArray()
        }
    }

    override fun getVariants(): Array<Any> {
        val project = myElement.project
        val rules = UnGrammarUtil.findRules(project)
        return rules.map { rule ->
            LookupElementBuilder.create(rule)
                .withIcon(UnGrammarIcons.FILE)
                .withTypeText(rule.containingFile.name)
        }.toTypedArray()
    }
}
