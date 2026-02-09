./gradlew bootJar
cd build/libs
SPRING_PROFILES_ACTIVE=cli java -Dloader.path=/Users/hayde/IdeaProjects/multi_agent_ide_parent/multi_agent_ide_java_parent/multi_agent_ide/src/main/resources -jar multi_agent_ide-0.0.1-SNAPSHOT.jar tui
