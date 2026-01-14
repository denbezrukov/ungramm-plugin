package org.ungramm.ungramm.lang

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import org.ungramm.ungramm.lang.psi.UnGrammarTypes
import org.ungramm.ungramm.lang.psi.UnGrammarUtil

class UnGrammarCompletionContributor : CompletionContributor() {

    init {
        // Complete rule names when typing an identifier (atom)
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(UnGrammarTypes.IDENT),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    resultSet: CompletionResultSet
                ) {
                    val project = parameters.position.project
                    val rules = UnGrammarUtil.findRules(project)

                    rules.forEach { rule ->
                        rule.name?.let { ruleName ->
                            resultSet.addElement(
                                LookupElementBuilder.create(ruleName)
                                    .withIcon(UnGrammarIcons.FILE)
                                    .withTypeText(rule.containingFile.name)
                            )
                        }
                    }
                }
            }
        )
    }
}
