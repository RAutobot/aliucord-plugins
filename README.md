## Aliucord Plugin Template
### Getting Started
1. Open the root [build.gradle.kts](https://github.com/RazerTexz/aliucord-plugin-template/blob/main/build.gradle.kts) file and replace all placeholders.
2. Open the [build.gradle.kts](https://github.com/RazerTexz/aliucord-plugin-template/blob/main/plugins/ExamplePlugin/build.gradle.kts) file inside the `plugins/ExamplePlugin` folder to modify your plugin metadata.
3. Build your plugin using the appropriate command:
   - **Windows**: `.\gradlew.bat :plugins:ExamplePlugin:make`
   - **Linux and MacOS**: `./gradlew :plugins:ExamplePlugin:make`

### GitHub Actions Setup
To significantly speed up your builds on GitHub Actions, you need to add a secret key:
1. Go to your repository **Settings**.
2. Navigate to **Secrets and variables** > **Actions**.
3. Click on **New repository secret**.
4. Set the **Name** to `GRADLE_ENCRYPTION_KEY`.
5. Set the **Secret** to a Base64-encoded AES key. You can generate one using the following command: `openssl rand -base64 16`.
6. Click on **Add secret**.
7. Done!

## Plugin Documentation
See the [Aliucord documentation](https://github.com/Aliucord/documentation/tree/main/plugin-dev).
