plugins {
    id("com.hayden.spring-app")
    id("com.hayden.kotlin")
    id("com.github.node-gradle.node")
    id("com.hayden.mcp")
}

group = "com.hayden"
version = "0.0.1-SNAPSHOT"
description = "multi-agent-ide"

dependencies {
    implementation("dev.langchain4j:langchain4j-spring-boot-starter:1.9.0-beta16")
    implementation("dev.langchain4j:langchain4j-anthropic-spring-boot-starter:1.9.0-beta16")
    implementation("dev.langchain4j:langchain4j-agentic:1.9.1-beta17")
    implementation("dev.langchain4j:langchain4j-open-ai-spring-boot-starter:1.9.1-beta17")
    implementation("com.agentclientprotocol:acp:0.10.3-SNAPSHOT")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("com.ag-ui.community:kotlin-core-jvm:0.2.4")
    implementation("org.springframework.ai:spring-ai-starter-mcp-server-webmvc")
    implementation(project(":utilitymodule"))
    implementation(project(":commit-diff-context"))
    implementation(project(":commit-diff-model"))
    implementation("org.springframework.boot:spring-boot-starter-security")
}

tasks.bootJar {
    mainClass = "com.hayden.multiagentide.MultiAgentIdeApplication"
}

// Node.js and npm configuration
//node {
//    version.set("20.11.0")
//    npmVersion.set("10.2.4")
//    download.set(true)
//    workDir.set(file("${project.layout.buildDirectory.get()}/nodejs"))
//    npmWorkDir.set(file("${project.layout.buildDirectory.get()}/npm"))
//}
//
//tasks.register<com.github.gradle.node.npm.task.NpmTask>("installFrontend") {
//    description = "Build Next.js frontend application"
//    workingDir.set(file("${project.projectDir}/fe"))
//
//    args.set(listOf("install"))
//
//    finalizedBy("buildFrontend")
//}

// Build the Next.js frontend
//tasks.register<com.github.gradle.node.npm.task.NpmTask>("buildFrontend") {
//    description = "Build Next.js frontend application"
//    workingDir.set(file("${project.projectDir}/fe"))
//
//    args.set(listOf("run", "build"))
//
//    inputs.files("${project.projectDir}/fe/src")
//    inputs.file("${project.projectDir}/fe/package.json")
//    inputs.file("${project.projectDir}/fe/next.config.ts")
//
//    outputs.dir("${project.projectDir}/fe/.next")
//
//    dependsOn("installFrontend")
//    finalizedBy("copyFrontendBuild")
//}

// Copy built frontend to static resources
tasks.register<Copy>("copyFrontendBuild") {

//    doFirst {
//        delete(file("${project.projectDir}/src/main/resources/static"))
//    }
//
//    description = "Copy Next.js build output to static resources"
//    dependsOn("installFrontend","buildFrontend")
//
//    from("${project.projectDir}/fe/out")
//    into("${project.layout.projectDirectory}/src/main/resources/static")

}

//tasks.getByPath("processResources").dependsOn("copyFrontendBuild")

// Make bootJar depend on frontend build
//tasks.getByPath("bootJar").dependsOn("copyFrontendBuild")

tasks.register<Copy>("copyToolGateway") {
    dependsOn(project(":mcp-tool-gateway").tasks.named("bootJar"))
    val sourcePaths = file(project(":mcp-tool-gateway").layout.buildDirectory).resolve("libs/mcp-tool-gateway.jar")
    from(sourcePaths)
    into(file(layout.buildDirectory).resolve("libs"))
    // Optionally rename it to a fixed name
    rename { "mcp-tool-gateway.jar" }
}

tasks.compileJava {
    dependsOn("copyToolGateway")
}
tasks.test {
    dependsOn("copyToolGateway")
}
