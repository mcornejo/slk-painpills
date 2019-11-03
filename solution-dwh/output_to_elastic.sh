#!/bin/bash

#Daily
curl -X PUT "localhost:9200/painpills_day?pretty"
curl -X PUT "localhost:9200/painpills_day/_mapping?pretty" -H 'Content-Type: application/json' -d'
{
  "properties": {
    "drugName": {
      "type": "keyword"
    },
    "transactionYear": {
      "type": "keyword"
    },
    "transactionMonth": {
      "type": "keyword"
    },
    "transactionDay": {
      "type": "keyword"
    },
        "transactionDate": {
      "type": "date",
      "format": "MMddyyyy"
    },
    "count": {
      "type": "long"
    }
  }
}
'
curl -X POST "http://localhost:9200/painpills_day/_bulk" -H 'Content-Type: application/json' --data-binary @output/day/part-00000-233c915a-40f9-4193-8d1f-60b4833294bf-c000.json

# Monthly
curl -X PUT "localhost:9200/painpills_month?pretty"
curl -X PUT "localhost:9200/painpills_month/_mapping?pretty" -H 'Content-Type: application/json' -d'
{
  "properties": {
    "drugName": {
      "type": "keyword"
    },
    "transactionYear": {
      "type": "keyword"
    },
    "transactionMonth": {
      "type": "keyword"
    },
    "transactionDate": {
      "type": "date",
      "format": "MMddyyyy"
    },
    "count": {
      "type": "long"
    }
  }
}
'
curl -X POST "http://localhost:9200/painpills_month/_bulk" -H 'Content-Type: application/json' --data-binary @output/month/part-00000-d1e927b0-c44f-4db8-913b-f6f95d36dc32-c000.json

# City
curl -X PUT "localhost:9200/painpills_city?pretty"
curl -X PUT "localhost:9200/painpills_city/_mapping?pretty" -H 'Content-Type: application/json' -d'
{
  "properties": {
    "drugName": {
      "type": "keyword"
    },
    "buyerCity": {
      "type": "keyword"
    },
    "count": {
      "type": "long"
    }
  }
}
'
curl -X POST "http://localhost:9200/painpills_city/_bulk" -H 'Content-Type: application/json' --data-binary @output/city/part-00000-afac71ff-db7e-4a74-bbb1-3f6c9603b726-c000.json
