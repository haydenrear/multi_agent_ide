./gradlew bootJar
cd build/libs
SPRING_PROFILES_ACTIVE=cli java -jar multi_agent_ide-0.0.1-SNAPSHOT.jar tui