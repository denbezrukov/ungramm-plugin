package org.ungramm.ungramm.lang.highlight

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import org.ungramm.ungramm.lang.psi.UnGrammarLabeled
import org.ungramm.ungramm.lang.psi.UnGrammarRule
import org.ungramm.ungramm.lang.psi.UnGrammarTypes

class UnGrammarAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is UnGrammarRule -> {
                // Highlight rule name (first IDENT in a rule)
                val nameIdentifier = element.nameIdentifier
                if (nameIdentifier != null) {
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(nameIdentifier)
                        .textAttributes(UnGrammarSyntaxHighlighter.RULE_NAME)
                        .create()
                }
            }
            is UnGrammarLabeled -> {
                // Highlight label names (IDENT before colon)
                val children = element.node.getChildren(null)
                for (child in children) {
                    if (child.elementType == UnGrammarTypes.IDENT) {
                        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                            .range(child.psi)
                            .textAttributes(UnGrammarSyntaxHighlighter.LABEL)
                            .create()
                        break // Only the first IDENT is the label
                    }
                }
            }
        }
    }
}
