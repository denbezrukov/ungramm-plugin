# UnGrammar Language Plugin

IntelliJ IDEA plugin for [UnGrammar](https://github.com/rust-analyzer/ungrammar) - a DSL for specifying concrete syntax trees.

## Features

This plugin provides full IDE support for `.ungram` files:

### Syntax Highlighting
- Color-coded tokens (keywords, operators, strings, comments)
- Semantic highlighting for rule names and labels
- Customizable color scheme via IDE settings

### Code Intelligence
- **Go to Declaration** - Ctrl+Click (Cmd+Click) on rule references to navigate to definitions
- **Find Usages** - Find all references to a rule across the project
- **Code Completion** - Auto-complete rule names with type-ahead filtering
- **Rename Refactoring** - Inline rename with automatic reference updates (F6 or Shift+F6)

### Code Formatting
- Smart spacing around operators (`=`, `|`, `:`, `*`, `?`)
- Automatic code formatting (Ctrl+Alt+L / Cmd+Opt+L)

### Code Folding
- Fold long rules (>80 chars) or multi-line rules
- Placeholder shows `RuleName = ...`

## Installation

### From ZIP Archive

1. Download the latest `ungramm-1.0-SNAPSHOT.zip` from `build/distributions/`
2. In IntelliJ IDEA:
   - Go to **Settings/Preferences** → **Plugins**
   - Click ⚙️ (gear icon) → **Install Plugin from Disk...**
   - Select the ZIP file
   - Restart IDE

### From Source

```bash
# Build the plugin
./gradlew buildPlugin

# The ZIP will be at: build/distributions/ungramm-1.0-SNAPSHOT.zip

# Or run the plugin in a dev instance
./gradlew runIde
```

## Example UnGrammar File

```ungram
// Example grammar
Rule = name:IDENT '=' body:Alternation

Alternation = Sequence ('|' Sequence)*

Sequence = Item*

Item = labeled:IDENT? atom:Atom postfix:('*' | '?')?

Atom = IDENT | 'string' | Group

Group = '(' Alternation ')'
```

## Development

### Building

```bash
./gradlew build          # Build the plugin
./gradlew test           # Run tests (53 tests)
./gradlew buildPlugin    # Create distribution ZIP
```

### Running Tests

```bash
# All tests (53 total)
./gradlew test

# Specific test suite
./gradlew test --tests "org.ungramm.ungramm.lang.UnGrammarParserTest"
./gradlew test --tests "org.ungramm.ungramm.lang.UnGrammarRenameTest"
```

## Resources

- [UnGrammar Repository](https://github.com/rust-analyzer/ungrammar)
- [IntelliJ Platform SDK](https://plugins.jetbrains.com/docs/intellij)
- [Grammar-Kit](https://github.com/JetBrains/Grammar-Kit)
- [JFlex](https://jflex.de/)
