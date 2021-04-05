plugins {
    kotlin("jvm") version "1.4.31"
}

group = "org.example"
version = "1.3-SNAPSHOT"

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}