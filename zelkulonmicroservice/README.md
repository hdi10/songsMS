
# Meine Befehel

Spotify token
UND
nicht VERGESSEN ELASTICSEARCH CONTAINER ZU STARTEN

```sh
docker pull docker.elastic.co/elasticsearch/elasticsearch:7.14.0
docker run --name elasticsearch -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" docker.elastic.co/elasticsearch/elasticsearch:7.14.0
docker ps -a
docker rm elasticsearch

docker stop elasticsearch
docker start elasticsearch
```
https://www.youtube.com/watch?v=eP1BQ_WyuWg

## POST
man beachte im POST nutze ich den Endpoint
```sh
curl --location 'http://localhost:9020/songElastic' \
--header 'Content-Type: application/json' \
--data '{
"title": "Bohemian Rhapsody",
"artist": "Queen"
}'
```

## GET
man beachte im Model gebe ich den indexName an
```sh
curl --location 'http://localhost:9200/songelastic/_search'
```

------
## ✅ Elasticsearch Security Features
- Elasticsearch security features have been automatically configured!
- Authentication is enabled and cluster connections are encrypted.

## ℹ️ Elastic User Password
Password for the `elastic` user (reset with `bin/elasticsearch-reset-password -u elastic`):
```
1lH1vdu_8P-GaO66IyJg
```

## ℹ️ HTTP CA Certificate SHA-256 Fingerprint
```
dbcb3aec1058473e35b44c5eb562fc6954006d74ed5e9b21567da5c87bf0df98
```

## ℹ️ Configure Kibana
- Run Kibana and click the configuration link in the terminal when Kibana starts.
- Copy the following enrollment token and paste it into Kibana in your browser (valid for the next 30 minutes):
```
eyJ2ZXIiOiI4LjkuMiIsImFkciI6WyIxNzIuMjMuMC4yOjkyMDAiXSwiZmdyIjoiZGJjYjNhZWMxMDU4NDczZTM1YjQ0YzVlYjU2MmZjNjk1NDAwNmQ3NGVkNWU5YjIxNTY3ZGE1Yzg3YmYwZGY5OCIsImtleSI6ImtDNXlvSW9Ca2xJVC15YUxET0hfOl83Y1RoLVE0UzlxajhsdzJXbllDUkEifQ==
```

## ℹ️ Configure Other Nodes
- Copy the following enrollment token and start new Elasticsearch nodes with `bin/elasticsearch --enrollment-token <token>` (valid for the next 30 minutes):
```
eyJ2ZXIiOiI4LjkuMiIsImFkciI6WyIxNzIuMjMuMC4yOjkyMDAiXSwiZmdyIjoiZGJjYjNhZWMxMDU4NDczZTM1YjQ0YzVlYjU2MmZjNjk1NDAwNmQ3NGVkNWU5YjIxNTY3ZGE1Yzg3YmYwZGY5OCIsImtleSI6Imp5NXlvSW9Ca2xJVC15YUxET0hfOm5oYXUzVjk3UVRXcmtBcGRKSDN4NXcifQ==
```

### Docker Configuration
If you're running in Docker, copy the enrollment token and run:
```bash
docker run -e "ENROLLMENT_TOKEN=<token>" docker.elastic.co/elasticsearch/elasticsearch:8.9.2
```


## Was habe ich gemacht-->

https://accounts.spotify.com/authorize?client_id=YOUR_CLIENT_ID&response_type=code&redirect_uri=YOUR_REDIRECT_URI&scope=user-read-currently-playing


```
https://accounts.spotify.com/authorize?client_id=3373434b15ef4563bef4c04432187fd3&response_type=code&redirect_uri=http://localhost:9020/api&scope=user-read-currently-playing
```

die antwort in der Browserzeile ist der BEARER TOKEN


Dann dieser POST mit dem BEARER, der clientID und dem CLIENT SECRET

```
curl -X POST "https://accounts.spotify.com/api/token" \
-H "Content-Type: application/x-www-form-urlencoded" \
-H "Authorization: Basic $(echo -n '3373434b15ef4563bef4c04432187fd3:DEIN_CLIENT_SECRET' | base64)" \
-d "grant_type=authorization_code&code=AQCwSKPuKNO10hU4rWbwzOEHlXLR-2J20YRDp7vvygPAjMTPBuwub6KNBmFQga344Xgk0670qh9kiKC4FTHfu78XFDVgGtj2lv6kAz5oMBhD5Oh9qn1stGNns4KO7f5UEjsO9OIPaMtu4Tbmm43I1U-vdPW3AIZIdKAm9byZx0mmTAHwC4AqCEJdDhZPx9hJuSMWr6aCAw&redirect_uri=http://localhost:9020/api"
```

