plugins {
    java
    kotlin("jvm") version "1.4.31"
}

group = "org.example"
version = "1.3-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-jdbc
    implementation ("org.jetbrains.kotlin:kotlin-jdbc:0.12.213")
    // https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc
    implementation("org.xerial:sqlite-jdbc:3.34.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.31")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.31")
}
