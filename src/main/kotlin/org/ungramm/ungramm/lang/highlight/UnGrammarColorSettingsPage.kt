package org.ungramm.ungramm.lang.highlight

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import org.ungramm.ungramm.lang.UnGrammarIcons
import javax.swing.Icon

class UnGrammarColorSettingsPage : ColorSettingsPage {

    private val DESCRIPTORS = arrayOf(
        AttributesDescriptor("Rule Name", UnGrammarSyntaxHighlighter.RULE_NAME),
        AttributesDescriptor("Identifier", UnGrammarSyntaxHighlighter.IDENTIFIER),
        AttributesDescriptor("String", UnGrammarSyntaxHighlighter.STRING),
        AttributesDescriptor("Comment", UnGrammarSyntaxHighlighter.COMMENT),
        AttributesDescriptor("Operator", UnGrammarSyntaxHighlighter.OPERATOR),
        AttributesDescriptor("Parentheses", UnGrammarSyntaxHighlighter.PARENTHESES),
        AttributesDescriptor("Label", UnGrammarSyntaxHighlighter.LABEL),
        AttributesDescriptor("Bad Character", UnGrammarSyntaxHighlighter.BAD_CHARACTER)
    )

    override fun getIcon(): Icon = UnGrammarIcons.FILE

    override fun getHighlighter(): SyntaxHighlighter = UnGrammarSyntaxHighlighter()

    override fun getDemoText(): String {
        return """
// CSS Un-Grammar example
Rule = <ruleName>name</ruleName>:<label>IDENT</label> '=' <ruleName>body</ruleName>:<label>Alternation</label>

Alternation = Sequence ('|' Sequence)*

Sequence = Item*

Item = <label>label</label>:IDENT? <label>atom</label>:Atom <label>postfix</label>:('*' | '?')?

Atom = IDENT | 'string' | Group

Group = '(' Alternation ')'
        """.trimIndent()
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> {
        return mapOf(
            "ruleName" to UnGrammarSyntaxHighlighter.RULE_NAME,
            "label" to UnGrammarSyntaxHighlighter.LABEL
        )
    }

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String = "UnGrammar"
}
