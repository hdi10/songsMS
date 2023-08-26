https://spotify-web-api-java.github.io/spotify-web-api-java/se/michaelthelin/spotify/requests/AbstractRequest.html

# Einleitung





# Gliederung

## Service Discover/Resgistry Client and Server Registry

## APIGateway

## Zelkulon Service as a Service [#ZelSaaS(#ZelSaaS)]

## Ausblick



//////////////////////////////
////            Template    /
////////////////////////////                    
# Song API Documentation

The following table lists the endpoints for the Song API:

| Basepath | HTTP Method | Endpoint | Query Patam |        Payload          | Description        |
| :------- | :--------- |:---------|:-------------|:-----------|:-------------------|
| /songsMS/rest/songs   |    GET   | /hello1   |   -   |     -           | Get a Greeting     |
| /songsMS/rest/songs   |    GET      | /        |   -  |     -           | Get all songs      |
| /songsMS/rest/songs   |    GET      | /{id}    |   -  |     -           | Get one song by ID |
| /songsMS/rest/songs   |    POST     | /        |-     | [Song JSONObjekt](#song) | Add a new song     |
| /songsMS/rest/songs   |    PUT      | /{id}    | -    | [Song ObjektJSON](#song) | Update a song      |
| /songsMS/rest/songs   |   DELETE    | /{id}    |  -   |      -           | Delete a song      |
| /songsWS/rest |   POST  | /auth    | | [User JSONObjekt, String authHeader](#user)   |        authenticate user            |

## Test Request

Sending a Test Request

```sh
http://localhost:8080/hello
```

## Response

Getting a Response 

```sh
HTTP/1.1 200 OK
Content-Type: text/plain
Testtring
```

----

## Get Song By ID

Get the song with the id=22

```sh
http://localhost:8080/songsMS/rest/songs/22
```
## Response
Reponse is a song
```json
{
    "id": 22,
    "title": "Wrecking Ball",
    "artist": "MILEY CYRUS",
    "label": "RCA",
    "released": 2013
}
```

### Response Header
The Response-Header returns a 200-Statuscode
```sh
HTTP/1.1 200 OK
Content-Type: application/json
```
---





## Post a song
Create a new Song in your Songslibrary

```sh
curl -X POST 'http://localhost:8080/songsMS/rest/songs' \
-H 'Authorization: qwertyuiiooxd1a245'
-H 'Content-Type: application/json' \
-d '{"title":"Wrecking Ball","artist":"MILEY CYRUS","label":"RCA","released":2013}'
```

### Request Header
The Response-Header should contain an Authorization Token
```sh
Authorization: qwertyuiiooxd1a245
Content-Type: application/json
```

## Song
The Song which is going to be Saved
```json
{
    "title": "Song Title",
    "artist": "Song Artist",
    "label": "Song Label",
    "released": 2000
}
```


// TODO
-->




## Requests
BasePath
```sh
localhost:8080/songsMS/rest/songs
```

# POST
```console
localhost:8080/songsMS/rest/songs
```

Payload:
```json
{"title":"Wrecking Ball","artist":"MILEY CYRUS","label":"RCA","released":2013}
```

```terminal
curl -X POST 'http://localhost:8080/songsMS/rest/songs' \
-H 'Content-Type: application/json' \
-d '{"title":"Wrecking Ball","artist":"MILEY CYRUS","label":"RCA","released":2013}'
```

Response:
```sh
HTTP/1.1 201 Created
Location: /songsMS/rest/songs?id=19
```
```json
{"title":"Wrecking Ball","artist":"MILEY CYRUS","label":"RCA","released":2013}
```


## findAll
QueryParam:



```
?all
```

```console
localhost:8080/songsMS/rest/songs?all
```

```terminal
curl -X POST 'http://localhost:8080/songsMS/rest/songs?all' \
-H 'Content-Type: application/json' \
```


Response
```sh
HTTP/1.1 200 OK
Content-Type: application/json
```
```json
[{
        "artist": "Alison Gold\"",
        "id": 1,
        "label": "PMW Live",
        "released": 2013,
        "title": "Chinese Food"
    }, ..., {
        "artist": "MILEY CYRUS",
        "id": 16,
        "label": "RCA",
        "released": 2013,
        "title": "Wrecking Ball"
    }
]
```





## Datenbank infos

