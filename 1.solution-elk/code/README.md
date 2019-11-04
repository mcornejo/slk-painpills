# Ingestion Code
The code provided is to ingest data from Kaggle's [Pain Pills dataset](https://www.kaggle.com/paultimothymooney/pain-pills-in-the-usa) to elasticsearch. 

It takes as input a TSV (Tab-Separated Value), parses each line as a Pain Pills object, then it is sent to the elasticsearch API in json format.

In order to execute the code, an elasticsearch API should be up listening on localhost:9200 (to run the server, use the docker-compose util provided in the `0.pre-processing` folder).

## Code
The code is written in Scala 2.12 using Akka 2.5.26. Akka is a framework for building highly concurrent, distributed, and resilient message-driven applications. Using streams is an intuitive and safe way to do asynchronous, non-blocking backpressured processing of data.

The application uses as source of the stream a file in the filesystem, but it can be easily replaced by another source like Kafka, S3, Cassandra, HBase, HTTP, Mongo, etc. depending on the requirements.

In general terms, the app first opens the file, then it creates tokens based on `\n`, then parses each line, converts it to json, creates the HttpRequest and sends it via an HttpPool, all this in a stream fashion.

### Structure
In the `src` folder there is the `main` code and the `test` (each of them in its respective folders). The main logic is in `Main.scala` in the `src` folder.


## Run
To compile the code:
```bash
$ sbt compile
```

To run the code:
```bash
$ sbt run
```