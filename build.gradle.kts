plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.20"
    id("org.jetbrains.intellij.platform") version "2.10.2"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
    id("org.jetbrains.changelog") version "2.2.1"
}

group = "org.ungramm"
version = "0.1.0"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
dependencies {
    intellijPlatform {
        intellijIdea("2025.2.4")
        testFramework(org.jetbrains.intellij.platform.gradle.TestFrameworkType.Platform)

        // Add plugin dependencies for compilation here, example:
        // bundledPlugin("com.intellij.java")
    }

    testImplementation("junit:junit:4.13.2")
}

changelog {
    version.set(project.version.toString())
    path.set(file("CHANGELOG.md").canonicalPath)
}

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild = "252.25557"
        }

        changeNotes.set(provider {
            changelog.renderItem(
                changelog.get(project.version.toString()).withHeader(false).withEmptySections(false),
                org.jetbrains.changelog.Changelog.OutputType.HTML
            )
        })
    }

    pluginVerification {
        ides {
            recommended()
        }
    }
}

tasks {
    // Generate lexer from JFlex file
    val generateLexer = register<org.jetbrains.grammarkit.tasks.GenerateLexerTask>("generateUnGrammarLexer") {
        sourceFile.set(file("src/main/kotlin/org/ungramm/ungramm/lang/UnGrammar.flex"))
        targetOutputDir.set(file("src/main/gen/org/ungramm/ungramm/lang"))
        purgeOldFiles.set(false)
    }

    // Generate parser from BNF file
    val generateParser = register<org.jetbrains.grammarkit.tasks.GenerateParserTask>("generateUnGrammarParser") {
        sourceFile.set(file("src/main/kotlin/org/ungramm/ungramm/lang/UnGrammar.bnf"))
        targetRootOutputDir.set(file("src/main/gen"))
        pathToParser.set("org/ungramm/ungramm/lang/parser/UnGrammarParser.java")
        pathToPsiRoot.set("org/ungramm/ungramm/lang/psi")
        purgeOldFiles.set(false)
    }

    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
        dependsOn(generateLexer, generateParser)
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        dependsOn(generateLexer, generateParser)
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN") ?: "")
        enabled = System.getenv("PUBLISH_TOKEN") != null
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

sourceSets {
    main {
        java.srcDirs("src/main/gen")
    }
}
