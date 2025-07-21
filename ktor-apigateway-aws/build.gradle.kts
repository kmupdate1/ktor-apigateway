plugins {
    kotlin("jvm")
    `maven-publish`
}

kotlin {
    jvmToolchain(23)
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(project(":ktor-apigateway-core"))
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.property("GROUP") as String
            artifactId = "ktor-apigateway-aws"
            version = project.property("VERSION_NAME") as String

            from(components["java"])
        }
    }
}
