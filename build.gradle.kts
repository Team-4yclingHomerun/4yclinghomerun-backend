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

	// password encoder
	implementation("org.springframework.security:spring-security-crypto")
	runtimeOnly("org.bouncycastle:bcprov-jdk18on:1.78.1")
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
