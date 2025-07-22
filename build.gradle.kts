plugins {
    alias { libs.plugins.kotlin.jvm } apply false
    alias { libs.plugins.kotlin.plugin.serialization } apply false
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
