plugins {
	id 'org.springframework.boot' version '2.4.1'
	id 'io.spring.dependency-management' version '1.0.10.RELEASE'
	id 'java'
}

group = 'jpabook'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

configurations {
		compileOnly {
		extendsFrom annotationProcessor
	}
}

repositories {
	mavenCentral()
}
f
dependencies {
    implementation 'org.hibernate.validator:hibernate-validator:6.2.0.Final'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-devtools'
	
	implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.5.6'
	
	compile("com.querydsl:querydsl-core")
	compile("com.querydsl:querydsl-jpa")

	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.h2database:h2'
    annotationProcessor("com.querydsl:querydsl-apt:${dependencyManagement.importedProperties['querydsl.version']}:jpa") // querydsl JPAAnnotationProcessor 사용 지정
	annotationProcessor 'org.projectlombok:lombok'
	annotationProcessor("jakarta.persistence:jakarta.persistence-api")
	annotationProcessor("jakarta.annotation:jakarta.annotation-api")
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	
	//JUnit4 추가
	testImplementation("org.junit.vintage:junit-vintage-engine") {
		exclude group: "org.hamcrest", module: "hamcrest-core"
	}
}
test {
	useJUnitPlatform()
}
//JUnit4 추가
testImplementation("org.junit.vintage:junit-vintage-engine") {
	exclude group: "org.hamcrest", module: "hamcrest-core"
}