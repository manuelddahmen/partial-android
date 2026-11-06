REM # 1. Build & stage release AAR, Sources JAR, Javadoc JAR, and GPG signatures
call ./gradlew :partialandroid:publishReleasePublicationToStagingRepository

REM 2. Verify JReleaser deployment configuration
call ./gradlew jreleaserConfig

REM  3. Deploy to Maven Central via JReleaser
call ./gradlew jreleaserDeploy