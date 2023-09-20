https://spotify-web-api-java.github.io/spotify-web-api-java/se/michaelthelin/spotify/requests/AbstractRequest.html

# Einleitung
* Diese Projekt besteht aus drei Services
    1. /auth PORT:9000
    2. /songs & /songLists PORT:9010
    3. /textanalyser PORT:9020
* Discovery/Registry Service -> Eureka PORT:8090
* API-Gateway PORT:8080

Diagramme mit mac programm?????

![Service Diagramm](workOnDrawings/ServiceDiagramm.png)
Service Diagramm

![ER Modell](workOnDrawings/ER-Modell-SongsMS-DB.png)
ER Modell

##Schema der SongsMS Datenbank
###Entitäten und Attribute
*Song*: {[**SongId:integer**, Title:string, Artist:string, Label:string, Released:integer]}

*SongLists*: {[**SongListsId:integer**, OwnerId:string, Name:string, IsPrivate]}

*User*: {[**UserId:string**, Password:string, FirstName:string, LastName:string, Token:string]}
### Relationen
*contains*: {[**SongListId:integer, SongId:integer**]}

*has*: {[**UserId:string, SongListId:integer**]}

![SongLists_sequenz_diagramm](workOnDrawings/SongListSequenz.png)

# Gliederung


## Service Discover/Registry Client and Server Registry

## APIGateway

## Zelkulon Service as a Service [#ZelSaaS(#ZelSaaS)]

## Ausblick

