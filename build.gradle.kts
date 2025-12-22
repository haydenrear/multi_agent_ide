plugins {
    id("com.hayden.spring-app")
    id("com.hayden.kotlin")

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
}

tasks.bootJar {
    mainClass = "com.hayden.multiagentide.MultiAgentIdeApplication"
}
