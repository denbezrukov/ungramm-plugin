package org.ungramm.ungramm.lang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.ungramm.ungramm.lang.psi.UnGrammarTypes;
import com.intellij.psi.TokenType;

%%

%class UnGrammarLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \n\t\f]
LINE_COMMENT="//"[^\r\n]*
IDENT=[a-zA-Z_][a-zA-Z0-9_]*
STRING='[^']*'

%%

<YYINITIAL> {
    {WHITE_SPACE}+           { return TokenType.WHITE_SPACE; }
    {LINE_COMMENT}           { return UnGrammarTypes.COMMENT; }

    "="                      { return UnGrammarTypes.EQ; }
    "|"                      { return UnGrammarTypes.PIPE; }
    "*"                      { return UnGrammarTypes.STAR; }
    "?"                      { return UnGrammarTypes.QUESTION; }
    "("                      { return UnGrammarTypes.LPAREN; }
    ")"                      { return UnGrammarTypes.RPAREN; }
    ":"                      { return UnGrammarTypes.COLON; }
    ","                      { return UnGrammarTypes.COMMA; }

    {STRING}                 { return UnGrammarTypes.STRING; }
    {IDENT}                  { return UnGrammarTypes.IDENT; }
}

[^]                          { return TokenType.BAD_CHARACTER; }
