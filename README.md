# Tontinen-Verwaltungssystem - Spring Boot API

##📌 Beschreibung
Diese Spring-Boot-Anwendung verwaltet ein Tontinen-System, das die Erstellung von Gruppen, das Hinzufügen von Mitgliedern, die Verfolgung von Zahlungen und die Festlegung eines "Boss" (Verantwortlichen) für jede Gruppe ermöglicht.

## 🚀 Geplante Funktionen

  - Gruppenverwaltung: Erstellen, Bearbeiten und Löschen von Tontinen-Gruppen.
  - Mitgliederverwaltung: Hinzufügen/Entfernen von Mitgliedern, Zahlungsverfolgung.
  - Rotationssystem: Festlegung einer Rotationsreihenfolge für Auszahlungen.
  - Boss-Verwaltung: Zuweisung eines Verantwortlichen für jede Gruppe.
  - RESTful API: Endpunkte für die Interaktion mit dem Backend.

## 🔧 Voraussetzungen
- Java 17+
- Maven 4.0.0+
- Datenbank PostgreSQL, H2 für die Entwicklung)
- Spring Boot 3.4.3+

## ⚙️ Installation & Einrichtung
### 1. Repository klonen
      git clone https://github.com/votre-repo/tontine-backend.git
      cd tontine-backend

### 2.  Datenbank konfigurieren
    ```   spring.datasource.url=jdbc:mysql://localhost:3306/tontine_db
          spring.datasource.username=root
          spring.datasource.password=votre_mdp
          spring.jpa.hibernate.ddl-auto=update
### 3.  Anwendung starten
    mvn clean install
    mvn spring-boot:run
### 4. 📡 API Endpoints

- Groupes
    - POST /api/groups - Eine neue Gruppe erstellen
    - GET /api/groups - Alle Gruppen auflisten
    - GET /api/groups/{id} - Eine bestimmte Gruppe erhalten
- Membres
    - POST /api/members - Mitglied hinzufügen
    - GET /api/members - Alle Mitglieder auflisten






