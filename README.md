# Test Automation Framework --- Java + Selenium + TestNG + ExtentReports + Log4j2

Framework di test automatizzato progettato per eseguire scenari
end-to-end sul sito **AutomationExercise**. L'architettura segue un
modello tradizionale, chiaro e facilmente estendibile, con gestione
professionale di driver, reportistica, log, wrapper Selenium e Page
Object Model.

------------------------------------------------------------------------

## Stack Tecnico

-   Java 21
-   Selenium WebDriver
-   TestNG
-   ExtentReports (report HTML avanzato)
-   Log4j2
-   Maven
-   WebDriverManager
-   Page Object Model
-   Wrapper Selenium personalizzato
-   ThreadLocal WebDriver

------------------------------------------------------------------------

## Struttura del Progetto

    src
    ├── main
    │   ├── java
    │   │   └── gianluca.com
    │   │        ├── configurazionereport/           # Sistema completo di reporting
    │   │        │     ├── ReportManager.java        # Interfaccia estendibile
    │   │        │     ├── ReportFactory.java        # Restituisce implementazione (Extent)
    │   │        │     ├── ExtentReportManager.java  # Implementazione ExtentReports
    │   │        │     ├── ExtentLogger.java         # Logging centrale per il report
    │   │        │     ├── ScreenshotUtils.java      # Screenshot automatici
    │   │        │     ├── TimeUtils.java            # Timestamp
    │   │        │     └── LoggerUtils.java          # Utility per log
    │   │        │
    │   │        ├── factory/
    │   │        │     ├── IBrowserDriver.java
    │   │        │     ├── ChromeDriverImpl.java
    │   │        │     ├── FirefoxDriverImpl.java
    │   │        │     ├── EdgeDriverImpl.java
    │   │        │     └── DriverFactory.java
    │   │        │
    │   │        ├── selenium/
    │   │        │     ├── ISeleniumActions.java
    │   │        │     ├── SeleniumActions.java
    │   │        │     └── BasePage.java
    │   │        │
    │   │        ├── pageobject/
    │   │        │     └── (...pagine...)
    │   │        │
    │   │        └── utils/
    │   │              ├── ConfigReader.java
    │   │              
    │   │
    │   └── resources
    │        └── environment/
    │              └── qa.properties
    │                   browser=chrome
    │                   baseUrl=https://automationexercise.com
    │                   timeout=10
    │                   report=extent
    │
    └── test
        ├── java
        │   └── gianluca.com
        │         ├── basefactory/
        │         │     └── BaseTest.java-->setup browser tear down
        │         │
        │         ├── listeners/
        │         │     └── TestListener.java
        │         │
        │         └── tests/
        │               └── (...classi di test...)
        │
        └── resources
             ├── log4j/
             │     └── log4j2.xml
             └── filetestXML/
                   └── suite.xml

------------------------------------------------------------------------

## Configurazione degli Ambienti
## L’ambiente viene selezionato al momento dell’esecuzione specificando il parametro JVM:
## mvn clean test -Denv=qa 

Esempio (`qa.properties`):

    browser=chrome
    baseUrl=https://automationexercise.com
    timeout=10
    report=extent

------------------------------------------------------------------------

## Driver Factory

-   Implementazione tramite `IBrowserDriver`
-   Chrome, Firefox, Edge supportati
-   WebDriverManager per gestione driver
-   WebDriver tramite ThreadLocal

------------------------------------------------------------------------

## Selenium Wrapper

Componenti:

-   `ISeleniumActions`
-   `SeleniumActions`
-   `BasePage`

------------------------------------------------------------------------

## Page Object Model

Esempio:

``` java
public class LoginPage extends BasePage {

    private By usernameField = By.id("user");
    private By passwordField = By.id("pass");

    public void login(String user, String pass) {
        selenium.write(usernameField, user);
        selenium.write(passwordField, pass);
        selenium.click(By.id("login"));
    }
}
```

------------------------------------------------------------------------

## Reportistica -- ExtentReports

Componenti principali:

-   `ReportManager`
-   `ReportFactory`
-   `ExtentReportManager`
-   `ExtentLogger`
-   `ScreenshotUtils`
-   `LoggerUtils`
-   `TimeUtils`
-   `TestListener`

Output:

    /reports/<data_ora>/ExtentReport.html
    /screenshots/<scenario>/FAIL_timestamp.png
    /logs/execution.log

------------------------------------------------------------------------

## Logging --- Log4j2

File:

    src/test/resources/log4j/log4j2.xml

------------------------------------------------------------------------


## Punti di Forza del Framework

-   Architettura pulita e tradizionale
-   Estendibile grazie alle interfacce
-   Driver gestiti tramite ThreadLocal
-   Reportistica avanzata e modulare
-   Page Object chiari
-   Wrapper Selenium solido
-   Configurazioni orientate ai vari ambienti
