curl -X DELETE "localhost:9200/painpills?pretty"
curl -X PUT "localhost:9200/painpills?pretty"
curl -X PUT "localhost:9200/painpills/_mapping?pretty" -H 'Content-Type: application/json' -d'@painpills_mapping.json'

