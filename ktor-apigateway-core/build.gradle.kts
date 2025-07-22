import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias { libs.plugins.kotlin.jvm }
    alias { libs.plugins.kotlin.plugin.serialization }
    `maven-publish`
}

group = project.property("GROUP_JITPACK") as String
version = project.property("VERSION") as String

kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.ktor.server.core)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.property("GROUP") as String
            artifactId = "ktor-apigateway-core"
            version = project.property("VERSION") as String

            from(components["java"])
        }
    }
}
