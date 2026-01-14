// This is a generated file. Not intended for manual editing.
package org.ungramm.ungramm.lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class UnGrammarVisitor extends PsiElementVisitor {

  public void visitAlternation(@NotNull UnGrammarAlternation o) {
    visitPsiElement(o);
  }

  public void visitAtom(@NotNull UnGrammarAtom o) {
    visitPsiElement(o);
  }

  public void visitItem(@NotNull UnGrammarItem o) {
    visitPsiElement(o);
  }

  public void visitLabeled(@NotNull UnGrammarLabeled o) {
    visitPsiElement(o);
  }

  public void visitPostfix(@NotNull UnGrammarPostfix o) {
    visitPsiElement(o);
  }

  public void visitRule(@NotNull UnGrammarRule o) {
    visitNamedElement(o);
  }

  public void visitSequence(@NotNull UnGrammarSequence o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull UnGrammarNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
