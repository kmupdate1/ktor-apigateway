plugins {
    alias { libs.plugins.kotlin.jvm }
    `maven-publish`
}

group = project.property("GROUP_JITPACK") as String
version = project.property("VERSION") as String

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(project(":ktor-apigateway-core"))
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.property("GROUP") as String
            artifactId = "ktor-apigateway-azure"
            version = project.property("VERSION") as String

            from(components["java"])
        }
    }
}
