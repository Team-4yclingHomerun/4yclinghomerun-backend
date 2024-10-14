plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
	configureEach {
		// exclude logback
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}
}

repositories {
	// maven { url = uri("https://mvnrepository.com") }
	mavenCentral()
	// maven { url = uri("https://google.com") }
    google()
	maven { url = uri("https://jitpack.io") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-log4j2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	// database
	runtimeOnly("org.postgresql:postgresql:42.7.4")

	// mapstruct
	implementation("org.mapstruct:mapstruct:1.6.0")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.0")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

	// flyway
	implementation("org.flywaydb:flyway-core:9.22.3")

	// lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// enum
	implementation("com.github.merge-simpson:enum-util:0.1.0")

	// password encoder
	implementation("org.springframework.security:spring-security-crypto")
	runtimeOnly("org.bouncycastle:bcprov-jdk18on:1.78.1")

	// Jwt
	implementation("io.jsonwebtoken:jjwt-api:0.12.3") //인터페이스 제공
	implementation("io.jsonwebtoken:jjwt-impl:0.12.3") //인터페이스 구현체
	implementation("io.jsonwebtoken:jjwt-jackson:0.12.3") // JSON 처리

    // swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")
}

tasks.withType<Test> {
	useJUnitPlatform()
	jvmArgs("--enable-preview")
}

tasks.withType<JavaCompile> {
	options.compilerArgs.add("--enable-preview")
}

tasks.named<JavaExec>("bootRun") {
	jvmArgs("--enable-preview")
}
