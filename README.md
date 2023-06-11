
# Neuen Micorservice hinzufügen

1. Neues Verzeichnis im Root-Verzeichnis als Modul anlegen
2. In der confic-server eine config Datei im Ordner resources/config anlegen mit neuem Port
3. Im neuen Mircoservice Ordner die "applications.properties" umbenennen in application.yaml
4. In der "application.yaml" diese Einstellungen eintragen
```yaml
spring:
  application:
    name: productservice
  config:
    import: "optional:configserver:http://localhost:8088"

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /
```
5. 
