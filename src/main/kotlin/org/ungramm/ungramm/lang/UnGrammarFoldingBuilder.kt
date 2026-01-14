package org.ungramm.ungramm.lang

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.ungramm.ungramm.lang.psi.UnGrammarRule

class UnGrammarFoldingBuilder : FoldingBuilderEx(), DumbAware {

    override fun buildFoldRegions(
        root: PsiElement,
        document: Document,
        quick: Boolean
    ): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()

        // Find all rules in the file
        val rules = PsiTreeUtil.findChildrenOfType(root, UnGrammarRule::class.java)

        for (rule in rules) {
            val ruleName = rule.name ?: continue
            val textRange = rule.textRange

            // Only fold if the rule spans multiple lines or is longer than 80 chars
            val startLine = document.getLineNumber(textRange.startOffset)
            val endLine = document.getLineNumber(textRange.endOffset)

            if (endLine > startLine || rule.text.length > 80) {
                descriptors.add(
                    FoldingDescriptor(
                        rule.node,
                        textRange
                    )
                )
            }
        }

        return descriptors.toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode): String? {
        val psi = node.psi
        if (psi is UnGrammarRule) {
            val ruleName = psi.name ?: return null
            return "$ruleName = ..."
        }
        return null
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        // Don't collapse by default, let user decide
        return false
    }
}
