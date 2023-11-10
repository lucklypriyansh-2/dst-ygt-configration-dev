plugins {
    java
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.allopen") version "1.7.21"
    id("io.quarkus")
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    id("com.contrastsecurity.contrastplugin") version "2.0.0"
    id("pl.allegro.tech.build.axion-release") version "1.14.4"
}

group = "com.yara"
project.version = scmVersion.version

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project
val awsSdk2Version: String by project

sourceSets {
    create("integration-tests") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output

        resources {
            srcDir(file("src/integration-test/java"))
            exclude("**/*.java")
        }
    }
}

val integrationTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.implementation.get())
}

configurations["integrationTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

dependencies {
    implementation(enforcedPlatform("$quarkusPlatformGroupId:$quarkusPlatformArtifactId:$quarkusPlatformVersion"))

    // Quarkus
    implementation("io.quarkus:quarkus-kotlin:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-rest-client:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-rest-client-jackson:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-amazon-lambda-http:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-jaxb:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-arc:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-amazon-dynamodb:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-smallrye-fault-tolerance:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-config-yaml:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-resteasy:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-resteasy-jackson:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-smallrye-metrics:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-mutiny:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-cache:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-jacoco:$quarkusPlatformVersion")
    implementation("io.quarkus:quarkus-smallrye-health:$quarkusPlatformVersion")

    // FasterXML Jackson Kotlin module
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.+")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // arrow-kt.io
    implementation("io.arrow-kt:arrow-core:1.1.5")

    // AWS
    implementation("com.amazonaws:aws-lambda-java-runtime-interface-client:2.1.1")
    implementation(enforcedPlatform("software.amazon.awssdk:bom:$awsSdk2Version"))

    implementation("software.amazon.awssdk:url-connection-client")
    implementation("software.amazon.awssdk:iotsitewise")
    implementation("io.quarkus:quarkus-amazon-ssm")
    implementation("software.amazon.awssdk:secretsmanager:$awsSdk2Version")
    implementation("software.amazon.awssdk:apache-client:$awsSdk2Version")
    // Test

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    // testRuntimeOnly("org.junit.jupiter:junit-jupiter-api:5.8.2")

    testImplementation("io.quarkus:quarkus-junit5:$quarkusPlatformVersion")
    testImplementation("io.quarkus:quarkus-junit5-mockito:$quarkusPlatformVersion")
    testImplementation("io.quarkus:quarkus-test-amazon-lambda:$quarkusPlatformVersion")
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("io.specto:hoverfly-java:0.14.3") {
        exclude(group = "ch.qos.logback", module = "logback-classic")
    }

//    testImplementation("com.intuit.karate:karate-junit5:1.1.0") {
//        exclude(group = "org.slf4j", module = "slf4j-api")
//    }

    // Integration Test
    // integrationTestImplementation("org.graalvm.sdk:graal-sdk:21.3.1")
    integrationTestImplementation("org.graalvm.js:js:22.3.1")
    integrationTestImplementation("org.graalvm.js:js-scriptengine:22.3.1")
    integrationTestImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    integrationTestImplementation("com.intuit.karate:karate-junit5:1.3.0") {
        exclude(group = "ch.qos.logback", module = "logback-classic")
        exclude(group = "org.graalvm.js", module = "js")
        exclude(group = "org.graalvm.js", module = "js-scriptengine")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

val integrationTest = task<Test>("integrationTest") {
    description = "Runs integration tests."
    group = "verification"
    useJUnitPlatform()
    systemProperty("karate.options", System.getProperty("karate.options"))
    systemProperty("karate.env", System.getProperty("karate.env"))
    outputs.upToDateWhen { false }
    testLogging.showStandardStreams = true
    /*// E.g. gradlew integrationTest -Penv=dev
    val env = properties["env"]
    println("Environment: $env")*/

    testClassesDirs = sourceSets["integration-tests"].output.classesDirs
    classpath = sourceSets["integration-tests"].runtimeClasspath
    shouldRunAfter("test")
}

tasks.check { dependsOn(integrationTest) }

tasks.test {
    useJUnitPlatform()
    systemProperty("karate.options", System.getProperty("karate.options"))
    systemProperty("karate.env", System.getProperty("karate.env"))
    outputs.upToDateWhen { false }
    testLogging.showStandardStreams = true
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}
