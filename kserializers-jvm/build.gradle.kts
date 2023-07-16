import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    signing
    `maven-publish`

    val kotlinVersion = "1.8.22"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("org.jetbrains.dokka") version "1.8.20"
    
    `java-library`
}

group = "com.qualifiedcactus"
version = "0.0.1"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // Use the JUnit 5 integration.
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")

    testImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}


tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets {
        configureEach {
            jdkVersion.set(17)
            includes.from("Module.md")
        }
    }
}

tasks.register<Jar>("dokkaJavadocJar") {
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}

publishing {
    repositories {
        maven {
            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            authentication {
                credentials {
                    username = project.findProperty("ossrhUsername")?.toString()
                    password = project.findProperty("ossrhPassword")?.toString()
                }
            }
        }
    }

    publications {

        create<MavenPublication>("mavenCentral") {
            from(components["java"])
            artifact(tasks["dokkaJavadocJar"])
            artifact(tasks["kotlinSourcesJar"])

            pom {
                name.set("${project.group}:${project.name}")
                packaging = "jar"
                description.set("A light-weight Java dependency injection library written in Kotlin")
                url.set("https://github.com/qualified-cactus/cactus-di")

                scm {
                    connection.set("scm:git:git://github.com/qualified-cactus/cactus-di.git")
                    url.set("https://github.com/qualified-cactus/cactus-di")
                }

                licenses {
                    license {
                        name.set("Apache License Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0")
                    }

                }

                developers {
                    developer {
                        name.set("qualified-cactus")
                        email.set("lemanhhieu.work@gmail.com")
                    }
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenCentral"])
}
