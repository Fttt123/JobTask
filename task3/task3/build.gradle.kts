plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.json:json:20210307")
}

tasks.test {
    useJUnitPlatform()
}

val mainClass = "org.example.Main"

tasks.jar {
    dependsOn(configurations.runtimeClasspath)

    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    }) {
        exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA")
    }
    manifest {
        attributes["Main-Class"] = mainClass
    }
}