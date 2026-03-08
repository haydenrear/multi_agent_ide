echo "Cleaning first ..."
./gradlew clean
echo "Starting unit tests."
./gradlew test --info
#echo "Starting unit tests."
#./gradlew test -Pprofile=shell-integration
echo "Starting integration-y tests."
./gradlew test -Pprofile=integration --info
#echo "Starting acp-integration-y tests."
#./gradlew test -Pprofile=acp-integration

