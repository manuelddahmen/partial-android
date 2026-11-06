# 1. Build & stage release AAR, Sources JAR, Javadoc JAR, and GPG signatures
./gradlew :partialandroid:publishReleasePublicationToStagingRepository

# 2. Verify JReleaser deployment configuration
./gradlew jreleaserConfig

# 3. Deploy to Maven Central via JReleaser
./gradlew jreleaserDeploy