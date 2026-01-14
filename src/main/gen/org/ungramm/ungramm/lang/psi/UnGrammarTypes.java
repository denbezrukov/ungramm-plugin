// This is a generated file. Not intended for manual editing.
package org.ungramm.ungramm.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.ungramm.ungramm.lang.psi.impl.*;

public interface UnGrammarTypes {

  IElementType ALTERNATION = new UnGrammarElementType("ALTERNATION");
  IElementType ATOM = new UnGrammarElementType("ATOM");
  IElementType ITEM = new UnGrammarElementType("ITEM");
  IElementType LABELED = new UnGrammarElementType("LABELED");
  IElementType POSTFIX = new UnGrammarElementType("POSTFIX");
  IElementType RULE = new UnGrammarElementType("RULE");
  IElementType SEQUENCE = new UnGrammarElementType("SEQUENCE");

  IElementType COLON = new UnGrammarTokenType(":");
  IElementType COMMA = new UnGrammarTokenType(",");
  IElementType COMMENT = new UnGrammarTokenType("COMMENT");
  IElementType EQ = new UnGrammarTokenType("=");
  IElementType IDENT = new UnGrammarTokenType("IDENT");
  IElementType LPAREN = new UnGrammarTokenType("(");
  IElementType PIPE = new UnGrammarTokenType("|");
  IElementType QUESTION = new UnGrammarTokenType("?");
  IElementType RPAREN = new UnGrammarTokenType(")");
  IElementType STAR = new UnGrammarTokenType("*");
  IElementType STRING = new UnGrammarTokenType("STRING");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ALTERNATION) {
        return new UnGrammarAlternationImpl(node);
      }
      else if (type == ATOM) {
        return new UnGrammarAtomImpl(node);
      }
      else if (type == ITEM) {
        return new UnGrammarItemImpl(node);
      }
      else if (type == LABELED) {
        return new UnGrammarLabeledImpl(node);
      }
      else if (type == POSTFIX) {
        return new UnGrammarPostfixImpl(node);
      }
      else if (type == RULE) {
        return new UnGrammarRuleImpl(node);
      }
      else if (type == SEQUENCE) {
        return new UnGrammarSequenceImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
