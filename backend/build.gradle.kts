import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val serializationVersion = "1.3.3"
val ktorVersion = "2.0.3"
val coroutineVersion = "1.6.1"
val exposedVersion = "0.39.2"
val postgresVersion = "42.5.0"

plugins {
    id("kotlin-platform-jvm")
    kotlin("plugin.serialization") version "1.6.10"
    application
    id("io.ktor.plugin") version "2.1.1"
}

dependencies {
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.postgresql:postgresql:$postgresVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")

    implementation("io.ktor:ktor-server-core:${ktorVersion}")
    implementation("io.ktor:ktor-server-netty:${ktorVersion}")
    implementation("io.ktor:ktor-server-cors:${ktorVersion}")
    implementation("io.ktor:ktor-server-content-negotiation:${ktorVersion}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${ktorVersion}")

    implementation("io.ktor:ktor-client-java:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")

    implementation(project(":shared"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("ServerKt")
}