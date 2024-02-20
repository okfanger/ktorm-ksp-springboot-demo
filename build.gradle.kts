import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"
}

group = "cn.akfang"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

// Kotlin DSL
kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    maven { setUrl("https://maven.aliyun.com/repository/central") }
    maven { setUrl("https://maven.aliyun.com/repository/google") }
    maven { setUrl("https://maven.aliyun.com/repository/jcenter") }
    maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin") }
    maven { setUrl("https://maven.aliyun.com/repository/public") }
    maven { setUrl("https://jitpack.io") }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.ktorm:ktorm-core:3.6.0")
        compileOnly("org.projectlombok:lombok")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        implementation("org.springframework.boot:spring-boot-starter-jdbc")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
// https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
        implementation("com.mysql:mysql-connector-j:8.0.33")
        implementation("org.ktorm:ktorm-core:3.6.0")
        implementation("org.ktorm:ktorm-jackson:3.6.0")
        implementation("org.ktorm:ktorm-support-mysql:3.6.0")
// https://mvnrepository.com/artifact/org.ktorm/ktorm-ksp-api
//        implementation("org.ktorm:ktorm-ksp-api:1.0.0-RC3")
        implementation(files("src/main/resources/lib/ktorm-ksp-api-1.0.0-RC3.jar"))
        ksp("org.ktorm:ktorm-ksp-compiler:1.0.0-RC3")

        // openapi
//        implementation("org.springdoc:springdoc-openapi-data-rest:1.6.0")
//        implementation("org.springdoc:springdoc-openapi-ui:1.6.0")
//        implementation("org.springdoc:springdoc-openapi-kotlin:1.6.0")
        // https://mvnrepository.com/artifact/com.github.xiaoymin/knife4j-openapi3-spring-boot-starter
        implementation("com.github.xiaoymin:knife4j-openapi3-spring-boot-starter:4.3.0")


        // https://mvnrepository.com/artifact/com.google.guava/guava
//        implementation("com.google.guava:guava:32.1.2-jre")
// https://mvnrepository.com/artifact/org.openjfx/javafx-controls
//        implementation("org.openjfx:javafx-controls:17.0.2")
        implementation("org.springframework.boot:spring-boot-starter-aop")

        // jwt
        implementation("com.auth0:java-jwt:3.18.1")

        // https://mvnrepository.com/artifact/com.alibaba/easyexcel
        implementation("com.alibaba:easyexcel:3.3.2")
        // https://mvnrepository.com/artifact/net.coobird/thumbnailator
        implementation("net.coobird:thumbnailator:0.4.8")

    }
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
