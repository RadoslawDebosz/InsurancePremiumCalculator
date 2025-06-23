# InsurancePremiumCalculator


Dies ist eine Beispielanwendung zur Berechnung von Versicherungsprämien auf Basis von:

- Jährlicher Kilometerleistung
- Fahrzeugtyp
- Region der Fahrzeugzulassung (anhand der Postleitzahl)

Die Anwendung erfüllt die Anforderungen einer technischen Challenge und wurde in Java mit Spring Boot entwickelt. 
Zusätzlich stehen eine REST-API und eine einfache Weboberfläche zur Verfügung.


##  Technologien

- Java 21  
- Spring Boot 3.5.3  
- SQLite (persistente Speicherung)  
- JPA / Hibernate  
- Maven  
- JUnit 5, Mockito (Test-Frameworks)


##  Funktionsweise

Die Versicherungsprämie wird mit folgender Formel berechnet:

Kilometer-Faktor * Fahrzeugtyp-Faktor * Regionen-Faktor


### Kilometer-Faktor:

| Kilometerleistung pro Jahr | Faktor |
|----------------------------|--------|
| 0 – 5.000 km               | 0.5    |
| 5.001 – 10.000 km          | 1.0    |
| 10.001 – 20.000 km         | 1.5    |
| ab 20.001 km               | 2.0    |

### Fahrzeugtyp-Faktoren:

public enum VehicleType {
    CAR(1.0),
    MOTORCYCLE(0.8),
    TRUCK(1.5);
}


### Regionen-Faktor (nach Bundesland):

| Bundesland           | Faktor |
|----------------------|--------|
| Bayern, BW           | 1.2    |
| Berlin, Hamburg      | 1.1    |
| Sachsen, Thüringen   | 0.9    |
| Sonstige             | 1.0    |


## CSV-Datei

Die Datei `postcodes.csv` enthält regionale Informationen zur PLZ-Zuordnung:

- Spalte 6: Postleitzahl
- Spalte 2: Bundesland
- Spalte 7: Stadt

Diese wird beim Start geladen und im Service gecached.


## Projektstruktur

- `entity` – Datenmodell (InsuranceRequest)
- `repository` – Schnittstelle zu SQLite
- `service` – Fachlogik (Berechnung und Region-Service)
- `controller` – REST-Endpunkte
- `model` – Hilfsklassen und Enums
- `resources/static/index.html` – Web-UI


## REST API

### POST `/api/premium/calculate`

Berechnet und speichert eine Prämie.

Request (JSON):
json
{
  "kilometersPerYear": 8000,
  "plz": "10115",
  "vehicleType": "CAR"
}


Response:
<double> – berechnete Prämie


### GET `/api/premium/all-requests`

Gibt alle gespeicherten Anträge zurück (JSON-Liste).


##  Weboberfläche

Die Web-UI (HTML/JS) ist unter `/index.html` verfügbar. Sie zeigt alle bisherigen Anfragen in einer Tabelle und ermöglicht das Einreichen neuer Anfragen.


## Tests

### Unit-Tests
- `VehicleTypeTest` – Testet die Fahrzeugfaktoren
- `KilometerRangeFactorTest` – Testet die KM-Faktoren
- `PremiumCalculatorServiceTest` – Fachlogik mit Mockito

### Integration-Tests
- `PremiumControllerIntegrationTest` – Testet vollständige REST-Kommunikation (MockMvc)



## Datenbank

SQLite wurde gewählt als:
- eingebettete, leichtgewichtige Lösung
- ideal für kleine Anwendungen ohne DB-Server
- unterstützt durch JPA (Hibernate)

Die Datei `insurance.db` wird automatisch erstellt und verwendet.


## Servicestruktur

1. PremiumCalculatorService  
   - Berechnet die Prämie
   - Persistiert den Antrag

2. RegionService  
   - Lädt die CSV-Datei beim Start
   - Liefert Faktor und Existenzprüfung zur PLZ

Die Services sind nach fachlicher Zuständigkeit getrennt und gut testbar.


## Anwendung starten

bash
mvn clean install
mvn spring-boot:run


Webseite: [http://localhost:8080/index.html](http://localhost:8080/index.html)  
REST-API: [http://localhost:8080/api/premium](http://localhost:8080/api/premium)


## Fazit

Diese Anwendung erfüllt alle gestellten Anforderungen:

CSV-Verarbeitung  
Eingaben & Berechnungen mit Validation  
Speicherung in DB  
REST-API für Drittanbieter  
Web-Oberfläche  
Unit- und Integrationstests


## Autor

Radoslaw Debosz
