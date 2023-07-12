#! /bin/sh
#


if [ "$#" -ne 3 ]; then
    echo "Illegal number of parameters"
    echo "Usage: ./GETsongListTester.sh token path_to_songlist token songListId"
    echo "Example: ./GETsongListTester.sh 4c87dubo833qa songsWS-teames/rest/songlist 2"
    exit 1
fi

echo "--- REQUESTING JSON-SONGLIST $3 WITH TOKEN $1: ----------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/json" \
     -v "http://localhost:8080/$2/$3"
echo "-------------------------------------------------------"

echo "--- REQUESTING JSON-SONGLIST $3 WITH empty Auth Header: ----------"
curl -X GET \
     -H "Authorization;" \
     -H "Accept: application/json" \
     -v "http://localhost:8080/$2/$3"
echo "-------------------------------------------------------"
