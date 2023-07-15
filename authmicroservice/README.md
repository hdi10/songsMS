# Auth Service Documentation

[API] (#api)

[Payload] (#payload)

[Examples] (#examples)

Eingabeformat JSON
Ausgabeformat

# API
The following table lists the endpoints for the Song API:
Basepath: songsMS/rest/


| HTTP Method  | Path  | Param  | @PathParam | @QueryParam  | Description                         |
|:------------:|:-----:|:-------|:----------:|:------------:|:------------------------------------|
|     POST     | /auth | (User) |     -      |      -       | legt neuen nutzer mit neuem token an |           





# Payload

| Field | Datatype | Required | Values  | Description |
|:-----:|:--------:|:---------|:-------:|:-----------:|
| user  |   JSON   | true     | s. user | Nutzer Json | 

User 
```JSON
{  
  "userId":"maxime",
  "password":"pass1234"
}
```

# Examples

```sh
curl -X POST \
-H "Content-Type: application/json" \
-v "http://localhost:8080/rest/auth" \
-d '{"userId":"maxime","password":"pass1234"}'
```

## Status 200

### Payload:
```JSON
{  
  "userId":"maxime",
  "password":"pass1234"
}
```
### Request:
```sh
curl -X POST \
-H "Content-Type: application/json" \
-v "http://localhost:8080/rest/auth" \
-d '{"userId":"maxime","password":"pass1234"}'
```

## Status 401

### Payload:
```JSON
{  
  "userId":"wrongId",
  "password":"pass1234"
}
```
### Request:
```sh
curl -X POST \
-H "Content-Type: application/json" \
-v "http://localhost:8080/rest/auth" \
-d '{"userId":"wrongId","password":"pass1234"}'
```
