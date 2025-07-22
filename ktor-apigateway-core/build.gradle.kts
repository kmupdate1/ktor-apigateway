plugins {
    kotlin("jvm")
    `maven-publish`
}

group = project.property("GROUP_JITPACK") as String
version = project.property("VERSION") as String

kotlin {
    jvmToolchain(23)
}

dependencies {
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
