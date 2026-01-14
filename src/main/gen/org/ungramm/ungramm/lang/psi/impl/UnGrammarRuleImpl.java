// This is a generated file. Not intended for manual editing.
package org.ungramm.ungramm.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.ungramm.ungramm.lang.psi.UnGrammarTypes.*;
import org.ungramm.ungramm.lang.psi.*;

public class UnGrammarRuleImpl extends UnGrammarRuleMixin implements UnGrammarRule {

  public UnGrammarRuleImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull UnGrammarVisitor visitor) {
    visitor.visitRule(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof UnGrammarVisitor) accept((UnGrammarVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public UnGrammarAlternation getAlternation() {
    return findChildByClass(UnGrammarAlternation.class);
  }

  @Override
  @NotNull
  public PsiElement getIdent() {
    return findNotNullChildByType(IDENT);
  }

}
