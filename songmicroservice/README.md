# Songs and SongList Service Documentation

[API] (#api)

[Payload] (#payload)

[Examples] (#examples)

Eingabeformat JSON Ausgabeformat

# API

| Endpoint                          | HTTP Method | Status Codes                       |
|-----------------------------------|-------------|------------------------------------|
| `/songs/hello` | GET         | 200 (OK)                           |
| `/songs/`      | GET         | 200 (OK), 404 (NoSongFoundException)|
| `/songs/`      | POST        | 201 (Created), 400 (BadRequest), 404 (NotFound) |
| `/songs/{id}`  | GET         | 200 (OK), 404 (ResourceNotFoundException)  |
| `/songs/{id}`  | PUT         | 204 (No Content), 400 (BadRequest), 404 (NotFound) |
| `/songs/{id}`  | DELETE      | 204 (No Content), 404 (NotFound)  |

## Songs

The following table lists the endpoints for the Song API:
Basepath: songsMS/rest/songs

| HTTP Method | Path | Param | @PathParam | @QueryParam | Beschreibung |
|:-----------:|:----:|:------|:----------:|:-----------:|:-------------|
|    POST     |  /   | Song  |     -      |  (userId)   | Song posten  |           
|     GET     | /all | -     |     -      |      -      | alle Songs   |           
|     GET     |  /   | -     |    {id}    |      -      | best. Song   |           
|     PUT     |  /   | Song  |    {id}    |      -      | update Song  |           
|   DELETE    |  /   | -     |    {id}    |      -      | Lösche Song  |

## Songlists

The following table lists the endpoints for the Song API:
Basepath: songsMS/rest/songsLists

| HTTP Method | Path | Param      |     @PathParam      | @QueryParam | Description                    |
|:-----------:|:----:|:-----------|:-------------------:|:-----------:|:-------------------------------|
|     GET     |  -   | -          |          -          |  (userId)   | Songliste zurückgeben von User |           
|     GET     |  /   | -          |    (songListId)     |      -      | Songliste von Id               |           
|    POST     |  -   | (SongList) |          -          |      -      | Songliste anlegen              |           
|     PUT     |  -   | (SongList) |          -          |      -      | Songliste Updaten (nur Owner)  |           
|   DELETE    |  /   | -          |    (songListId)     |      -      | Lösche Song                    |

# Payload


## Song Param
|  Field   | Datatype | Required |        Values         |      Description      |
|:--------:|:--------:|:---------|:---------------------:|:---------------------:|
|    id    | Integer  | true     |          22           |    Id eines Songs     | 
|  title   |  String  | true     |  "Afternoon Delight"  |    Titel des Songs    |
|  artist  |  String  | true     | "Starland Vocal Band" |   Artist des Songs    |
|  label   |  String  | true     |      "Windsong"       |    Label des Songs    |
| released | Integer  | true     |         1976          | Veröffentlichungsjahr |

## Song JSON

```JSON
{
  "id": 10,
  "title": "Chinese Food",
  "artist": "Alison Gold",
  "label": "PMW Live",
  "released": 2013
}
```

## SongList Param

|   Field   |   Datatype    | Required |          Values          |        Description        |
|:---------:|:-------------:|:---------|:------------------------:|:-------------------------:|
| isPrivate |    boolean    | true     |        true/false        | Private oder Public Liste | 
|   user    | [User](#user) | true     |     s. [User](#user)     |       Nutzer Objekt       |
|   name    |    String     | true     | "SomeName of a Playlist" |      Name der Liste       |
|  songSet  |   Set<Song>   | true     |        s. songSet        |     Eine Set an Songs     |

### User
```JSON
{  
  "userId":"maxime",
  "password":"pass1234"
}
```

### songset
```JSON
{
  "songs": [
    {
      "id": 10,
      "title": "Chinese Food",
      "artist": "Alison Gold",
      "label": "PMW Live",
      "released": 2013
    },
    {
      "id": 9,
      "title": "My Humps",
      "artist": "Black Eyed Peas",
      "label": "Universal Music",
      "released": 2005
    }
  ]
}
```

## SongList JSON

```JSON
{
  "songListId": "1",
  "isPrivate": false,
  "userId": "maxime",
  "name": "Maxime's PublicList",
  "songs": [
    {
      "id": 10,
      "title": "Chinese Food",
      "artist": "Alison Gold",
      "label": "PMW Live",
      "released": 2013
    },
    {
      "id": 9,
      "title": "My Humps",
      "artist": "Black Eyed Peas",
      "label": "Universal Music",
      "released": 2005
    }
  ]
}
```

# Examples

## GET Songs /all
### Status 200 Songs
#### Request:

```sh
curl -X POST \
-H "Content-Type: application/json" \
-v "http://localhost:8080/rest/songs/all"
```

#### Status 200 with Response: 
```json
[
	{
	    "id": 10,
		"title": "Chinese Food",
		"artist": "Alison Gold",
		"label": "PMW Live",
		"released": 2013
	},
	{
		"id": 1,
		"title": "MacArthur Park",
		"artist": "Richard Harris",
		"label": "Dunhill Records",
		"released": 1968
	}
]
```



### Status 404 or 500 Songs

#### Request:

```sh
curl -X POST \
-H "Content-Type: application/json" \
-v "http://localhost:8080/rest/songs" \
```
#### Status 204 with Response:

```text
"No Content"
```

#### Status 500 with Response:
```text
"Internal Server Error")
```

## GET Songs by Id
### Status 200 Songs by Id

#### Payload:

URL: http://localhost:8080/rest/songs/1

#### Request:

```sh
curl -X POST \
-H "Content-Type: application/json" \
-v "http://localhost:8080/rest/songs/1" \
```

#### Status 200 with Response:
```json
{
  "id": 1,
  "title": "MacArthur Park",
  "artist": "Richard Harris",
  "label": "Dunhill Records",
  "released": 1968
}
```

