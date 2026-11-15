# Enterprise Test Automation Framework

A multi-language test automation framework for **ParaBank**, a demo online banking application, built as a hands-on portfolio project.

| Module | Stack | Status |
|---|---|---|
| `java-framework` | Java 21, Selenium WebDriver, TestNG, Maven | Day 1: first smoke tests |
| `ts-framework` | TypeScript, Playwright | Planned (Week 2) |
| `contract-tests` | Pact, Spring Boot | Planned (Week 3) |
| `performance` | k6, JMeter | Planned (Week 3) |
| `security-a11y` | OWASP ZAP, axe | Planned (Week 3) |
| `ai-testing` | Promptfoo | Planned (Week 4) |
| CI | GitHub Actions, Docker | Planned (Week 2) |

## Day 1 setup (Windows)

1. **Install tools**
   - JDK 21 (Eclipse Temurin): https://adoptium.net
   - Maven: install via IntelliJ (bundled) or `winget install Apache.Maven`
   - Git: `winget install Git.Git`
   - IntelliJ IDEA Community: https://www.jetbrains.com/idea/download
   - Google Chrome (latest)
2. **Check versions** in a new PowerShell window:
   ```powershell
   java -version
   mvn -version
   git --version
   ```
3. **Create a GitHub repo** named `enterprise-test-automation-framework` (public), then:
   ```powershell
   cd enterprise-test-automation-framework
   git init
   git add .
   git commit -m "Day 1: Selenium + TestNG smoke tests for ParaBank"
   git branch -M main
   git remote add origin https://github.com/<your-username>/enterprise-test-automation-framework.git
   git push -u origin main
   ```
4. **Register a ParaBank user** at https://parabank.parasoft.com/parabank/register.htm and put the username and password in `LoginTest.java`.
5. **Run the tests**
   ```powershell
   cd java-framework
   mvn clean test
   ```
   Or right-click `testng.xml` in IntelliJ and choose **Run**.

## Notes

- Selenium Manager (built into Selenium 4) downloads the matching ChromeDriver automatically, so no driver setup is needed.
- The public ParaBank site is shared and sometimes resets its database or goes down. If login fails, register again. In Week 2 we will run ParaBank locally in Docker to remove this dependency.
