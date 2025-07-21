plugins {
    alias { libs.plugins.kotlin.jvm }
    alias { libs.plugins.kotlin.plugin.serialization }
    `maven-publish`
}

kotlin {
    jvmToolchain(23)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.ktor.server.core)

    implementation(project(":ktor-apigateway-core"))
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.property("GROUP") as String
            artifactId = "ktor-apigateway-gcp"
            version = project.property("VERSION_NAME") as String

            from(components["java"])
        }
    }
}
