pluginManagement {
    plugins {
        kotlin("jvm") version "2.2.0"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "ktor-apigateway"

include(
    ":ktor-apigateway-core",
    ":ktor-apigateway-gcp",
    ":ktor-apigateway-aws",
    ":ktor-apigateway-azure",
)
