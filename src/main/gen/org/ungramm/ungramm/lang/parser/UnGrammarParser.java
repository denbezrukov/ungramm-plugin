// This is a generated file. Not intended for manual editing.
package org.ungramm.ungramm.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.ungramm.ungramm.lang.psi.UnGrammarTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class UnGrammarParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return ungrammarFile(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // sequence (PIPE sequence)*
  public static boolean alternation(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "alternation")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ALTERNATION, "<alternation>");
    result_ = sequence(builder_, level_ + 1);
    result_ = result_ && alternation_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (PIPE sequence)*
  private static boolean alternation_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "alternation_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!alternation_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "alternation_1", pos_)) break;
    }
    return true;
  }

  // PIPE sequence
  private static boolean alternation_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "alternation_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PIPE);
    result_ = result_ && sequence(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // atom_ident | STRING | group
  public static boolean atom(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "atom")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ATOM, "<atom>");
    result_ = atom_ident(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, STRING);
    if (!result_) result_ = group(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // !(IDENT EQ) IDENT
  static boolean atom_ident(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "atom_ident")) return false;
    if (!nextTokenIs(builder_, IDENT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = atom_ident_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // !(IDENT EQ)
  private static boolean atom_ident_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "atom_ident_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !atom_ident_0_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // IDENT EQ
  private static boolean atom_ident_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "atom_ident_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENT, EQ);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPAREN alternation RPAREN
  static boolean group(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "group")) return false;
    if (!nextTokenIs(builder_, LPAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAREN);
    result_ = result_ && alternation(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // labeled | atom postfix?
  public static boolean item(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ITEM, "<item>");
    result_ = labeled(builder_, level_ + 1);
    if (!result_) result_ = item_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // atom postfix?
  private static boolean item_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = atom(builder_, level_ + 1);
    result_ = result_ && item_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // postfix?
  private static boolean item_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_1_1")) return false;
    postfix(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENT COLON atom postfix?
  public static boolean labeled(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "labeled")) return false;
    if (!nextTokenIs(builder_, IDENT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENT, COLON);
    result_ = result_ && atom(builder_, level_ + 1);
    result_ = result_ && labeled_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, LABELED, result_);
    return result_;
  }

  // postfix?
  private static boolean labeled_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "labeled_3")) return false;
    postfix(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // STAR | QUESTION
  public static boolean postfix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix")) return false;
    if (!nextTokenIs(builder_, "<postfix>", QUESTION, STAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, POSTFIX, "<postfix>");
    result_ = consumeToken(builder_, STAR);
    if (!result_) result_ = consumeToken(builder_, QUESTION);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IDENT EQ alternation
  public static boolean rule(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "rule")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, RULE, "<rule>");
    result_ = consumeTokens(builder_, 2, IDENT, EQ);
    pinned_ = result_; // pin = 2
    result_ = result_ && alternation(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, UnGrammarParser::rule_recover);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // !(IDENT EQ)
  static boolean rule_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "rule_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !rule_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // IDENT EQ
  private static boolean rule_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "rule_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENT, EQ);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // sequence_item*
  public static boolean sequence(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "sequence")) return false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SEQUENCE, "<sequence>");
    while (true) {
      int pos_ = current_position_(builder_);
      if (!sequence_item(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "sequence", pos_)) break;
    }
    exit_section_(builder_, level_, marker_, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // !(IDENT EQ) item
  static boolean sequence_item(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "sequence_item")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = sequence_item_0(builder_, level_ + 1);
    result_ = result_ && item(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // !(IDENT EQ)
  private static boolean sequence_item_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "sequence_item_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !sequence_item_0_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // IDENT EQ
  private static boolean sequence_item_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "sequence_item_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENT, EQ);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // rule*
  static boolean ungrammarFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ungrammarFile")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!rule(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ungrammarFile", pos_)) break;
    }
    return true;
  }

}
