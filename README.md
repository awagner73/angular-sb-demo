# Spring-Boot Angular  Demo
Im Backend basiert die Anwendung dabei auf 
[Spring Boot](https://spring.io/projects/spring-boot) und im Frontend
auf [Angular](https://angular.io/).

## Notwendige Tools
Das Projekt benutzt [Maven](https://maven.apache.org/) zum Bauen und für
die Java-Dependencies.

Wenn man das Projekt nur durchbauen möchte, so wie es zum Beispiel auch 
ein Build-Server machen würde, kann man einer der beiden Wrapper-Skripte
(mvnw) benutzen, da diese Maven selbst herunterladen.
Für Windows wäre dieser Aufruf `mvnw clean install` oder 
`./mvnw clean install` unter Linux.

Für die Entwicklung empfiehlt es sich aber Maven auf seinem Rechner zu
installieren, sodass man immer die Befehle, wie es auch in den meisten
Anleitungen steht mittels `mvn` aufrufen kann.

Für die Frontend-Entwicklung und das Bauen wird 
[NodeJS](https://nodejs.org/) benötigt.

Mittels des 
[maven-frontend-plugin](https://github.com/eirslett/frontend-maven-plugin)
wird zum Beispiel auf einem Build-Server `node` und `npm` lokal hingelegt,
so dass der Build-Server dies nicht selbst installiert haben muss.
Falls man nur Backend entwickelt kann man auch auf NodeJS verzichten.

Für die Frontend-Entwicklung muss man aber NodeJS installiert haben.

Nachdem NodeJS installiert ist, sollte um auch Angular richtig 
entwickeln zu können, auch die Angular-CLI installiert werden.
Dies geschieht mittels:

    npm install -g @angular/cli

Danach kann die Angular-CLI mittels `ng` aufgerufen werden.

## Projektstruktur
Für den Java-Backend-Teil folgt die Anwendung dem normalen Maven-Layout,
das heißt, dass die normalen Quellen unter `src/main/java`, Test-Code
unter `src/test/java` und Ressourcen-Dateien, wie zu Beispiel 
Konfigurationen unter `src/main/resources` liegen.

Der Angular-Anwendung, also das Frontend, liegt unter `src/main/webapp`.

Im Ordner `e2e` liegt außerdem noch ein Beispiel End-To-End-Test, der normalerweise
in einem eigenen Projekt liegen würde.

## Dependencies im Backend
Im Backend werden verschiedene Dependencies verwedent, dies sind unter
Anderem folgende:

* Spring Boot für die eigentliche Spring-Boot-Anwendung
* [Spring Web MVC](https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/mvc.html) für die Erstellung der REST-Ressourcen
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa) für die Persistenz-Schicht
* Hibernate (implizit, da dies der Spring-Boot-Standard bei Spring Data JPA ist) als JPA-Implementierung
* [Liquibase](https://www.liquibase.org/) für die Datenbankänderungen
* [Lombok](https://projectlombok.org/) zum Generieren von Getter/Setter/Konstruktoren mittels Annotationen
* [H2](https://www.h2database.com) als In-Memory-Datenbank
* [Mapstruct](https://mapstruct.org/) zum Mappen der Entities auf die
  Datentransfer-Objekte (DTOs)
* [Mockito](https://site.mockito.org/) (implizit durch den Spring Boot Starter `spring-boot-starter-test`) zum Mocken
  von Klassen und Interfaces in Tests

## Entwicklung

### Import in die IDE
Zum Importieren des Projekts in die IDE kann in IntelliJ einfach die 
`pom.xml` geöffnet werden. Bei Eclipse geschieht dies zum Beispiel so:
[Anleitung](https://vaadin.com/learn/tutorials/import-maven-project-eclipse)

### Starten des Backends
Zum Starten des Backends kann einfach die Klasse `DaleApplication`
gestartet werden.

Dies startet dann intern einen Tomcat und nach dem Start ist die Anwendung unter
[http://localhost:8080](http://localhost:8080/) erreichbar.

Wenn man nicht den Angular-Entwicklungsserver benutzt, muss die Angular-Anwendung
einmal gebaut worden sein, da sonst die HTML und Javascript-Dateien nicht an 
der richtigen Stelle liegen.
Dies wird zum Beispiel durch den anfänglich erwähnten Aufruf `mvn clean install` 
erledigt, kann aber auch direkter mittels `mvn generate-resources` geschehen, 
was beim Aufruf von `install` implizit mit passiert.

## Backend

### Backendstruktur
Im Backend gibt es ein par wenige Hauptklassen, in dem die meiste
Logik passiert.

* `DaleApplication` 
  
  Der Startpunkt der Applikation
* `DarlehenController`
  
  Die REST-Schicht der Anwendung, das heißt, dort werden die Ressourcen 
  definiert
* `DarlehenService`
  
  Die Business-Schicht der Anwendung. Dort wird auf die Persistenzschicht zugegriffen und das Mapping
  auf die DTOs für das Frontend gemacht.
* `DarlehenRepository`
  
  Die Persistenz-Schicht der Anwendung, wobei dies nur ein Interface ist
  und der Rest von Spring Data JPA erledigt wird.
* `DarlehenMapper`
  
  Interface zum Mappen von DTO auf Entity und der Rückweg.
  Die eigentliche Implementierung übernimmt Mapstruct.

### Datenbankänderungen
Die Datenbank wird mittels Liquibase aufgesetzt und versioniert,
dabei schaut Liquibase immer in einer speziellen Tabelle nach,
welche Version die Datenbank hat, um zu überprüfen, welche Changesets
ausgeführt werden müssen.
Da in diesem Projekt eine In-Memory-Datenbank benutzt wird, müssen bei
jedem Start alle Changesets ausgeführt werden.
Der Einstiegspunkt ist die Datei `src/main/resources/db/changelog/db.changelog-master.xml` die auf eine 1.0-XML-Datei
`src/main/resources/db/changelog/db.changelog-1.0.xml` verweist.
In dieser wird nur die einzige Tabelle angelegt und dann werden Testdaten aus der
`src/main/resources/db/changelog/export.csv` importiert. 

### Konfiguration der Anwendung
Bei Spring Boot wird die Anwendung normalerweise über eine 
`application.properties` oder eine `application.yaml` konfiguriert.
Diese liegt unter `src/main/resources`.
Aktuell gibt es nicht viele Konfigurationen, weil für das meiste der
Spring Boot Standard benutzt wird.
Eine Beispiel Änderung ist die Property

    spring
      liquibase:
        change-log: classpath:/db/changelog/db.changelog-master.xml

welche den Pfad für die Liquibase-Changelogs vom Standard ändert.

### Tests
Die Tests sind mit JUnit 5 erstellt.
Teilweise sind dies einfache Unit-Tests, die zum Beispiel Mocks mittels
Mockito erstellen, als auch Komponenten oder Integrationstests, die 
Spring Boot Test Annotationen benutzen.

## Frontend

### Frontendstruktur
Das Verzeichnis `src/main/webapp` kann als root-Verzeichnis
für die Angular-Anwendung gesehen werden, das heißt, ab dort gibt es
dann nochmal ein neues `src`-Verzeichnis, wo die Angular Quellen liegen.

Unter `src/app` liegen die einzelnen Komponenten und Services des 
Frontends.
Eine Komponente besitzt in Angular normalerweise ein eigenes Unterverzeichnis,
wo nochmal 4 Dateien erstellt werden:
* eine CSS-Datei für das Styling
* eine HTML-Datei für die HTML-Struktur der Komponent
* eine TypeScript-Datei für den eigentlichen Komponenten-Code
* eine `.spec.ts`-Datei für den Test der Komponente

Wenn man wissen will, wie die Komponenten in der Anwendung aufgebaut sind,
kann man sich an den HTML-Dateien entlang hangeln.
Als Einstiegspunkt kann die `app.component.html` angeschaut werden
und dort sieht man dann, dass dort 2 Komponenten benutzt werden
(Alle Komponenten haben das Präfix `app-`):
* frame
* toasts-container

Wenn man der frame-Komponente (dem Rahmen) dann folgt (Verzeichnis-Frame),
kann man sich dort auch wieder die HTML-Datei anschauen und kann dort die 
nächsten Komponenten inspizieren.

Ein Sonderfall ist `<router-outlet></router-outlet>` in der Frame-HTML-Datei.
Denn damit in dem Frame sowohl die Übersicht, als auch das Erstellen
eines Darlehens sichtbar ist, werden bestimmte Routen definiert,
die dann in dem `<router-outlet>` gerendert werden.

Die Definition dafür geschieht in der `app-routing.module.ts`.
Dort ist definiert, dass für den Pfad `overview` die `DaleOverviewComponent`
benutzt werden soll und für den Pfad `create` die `DarlehenCreateComponent`.

Das bedeutet, dass je nach URL (`/overview` oder `/create`) eine 
andere Komponente in das `router-outlet` gerendert wird.

Es gibt außerdem eine Service-Klasse für die Kommunikation mit dem Backend:
`darlehen.service.ts` und Modell-Klassen, die das JSON vom Backend Mappen:
`darlehen.ts` und `dale-overview.ts`.

### "Installation"
Um die notwendigen Abhängigkeiten zu installieren, muss in dem Verzeichnis
`src/main/webapp` `npm install` ausgeführt werden. Dies wird auch zum Beispiel
durch Maven aufgerufen.
Danach sind alle Abhängigkeiten der Anwendung in dem Verzeichnis `node_modules`
installiert.

### Entwicklung
Für die Entwicklung des Frontends kann ein Angular-Entwicklungsserver
gestartet werden mittels `ng serve`.
Das sorgt dafür, dass die Anwendung kompiliert wird und unter
[http://localhost:4200](http://localhost:4200/) erreichbar ist.

Per Default, wird die Browser-Seite automatisch neugeladen, wenn es
Änderungen an den Quelltext-Dateien gibt.

### Test
Um die Tests im Frontend laufen zu lassen, kann `ng test` gestartet werden.
Das sorgt dafür, dass ein Chrome-Browser sich öffnet und alle Tests ausführt.
Auch hier ist es so, dass die Tests automatisch neudurchlaufen werden, wenn es
Änderungen am Quelltext gibt.

### Bauen der Anwendung
Wenn man die Anwendung ohne Maven bauen möchte geschieht dies durch den Aufruf
`ng build` beziehungsweise `ng build --prod` für Produktionscode.

## End-To-End Test
Im Verzeichnis `e2e` liegt ein Beispielhafter End-To-End-Test mittels 
[Cypress](https://www.cypress.io/).

Auch hierfür wird NodeJS und NPM benötigt und es muss erst einmal `npm install`
aufgerufen werden, um die Abhängigkeiten zu installieren.

Cypress kann dann mittels `npm run cypress:open` geöffnet werden und von dort aus
kann manuell der Test gestartet werden, der gegen die Anwendung unter 
`http://localhost:8080` testet.
