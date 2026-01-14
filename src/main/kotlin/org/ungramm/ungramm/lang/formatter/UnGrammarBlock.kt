package org.ungramm.ungramm.lang.formatter

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import org.ungramm.ungramm.lang.psi.UnGrammarTypes

class UnGrammarBlock(
    node: ASTNode,
    wrap: Wrap?,
    alignment: Alignment?,
    private val spacingBuilder: SpacingBuilder
) : AbstractBlock(node, wrap, alignment) {

    override fun buildChildren(): List<Block> {
        val blocks = mutableListOf<Block>()
        var child = myNode.firstChildNode

        while (child != null) {
            if (child.elementType != TokenType.WHITE_SPACE) {
                val block = UnGrammarBlock(
                    child,
                    Wrap.createWrap(WrapType.NONE, false),
                    null,
                    spacingBuilder
                )
                blocks.add(block)
            }
            child = child.treeNext
        }

        return blocks
    }

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun getIndent(): Indent? {
        // Rules are not indented, alternation content can be indented
        return when (myNode.elementType) {
            UnGrammarTypes.RULE -> Indent.getNoneIndent()
            UnGrammarTypes.ALTERNATION -> Indent.getNoneIndent()
            UnGrammarTypes.SEQUENCE -> Indent.getNoneIndent()
            else -> Indent.getNoneIndent()
        }
    }

    override fun isLeaf(): Boolean {
        return myNode.firstChildNode == null
    }
}
