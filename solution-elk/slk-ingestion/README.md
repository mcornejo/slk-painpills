# Ingestion Code
The code provided is to ingest data from Kaggle's [Pain Pills dataset](https://www.kaggle.com/paultimothymooney/pain-pills-in-the-usa) to Elasticsearch. 

It takes as input a TSV (Tab-Separated Value), parses each line to an Pain Pills object. After it is send to elastic API in json format.

In order to execute the code, a elasticsearch API should be up listening in localhost:9200.

## Code
The code is written in Scala 2.12 using Akka 2.5.26. Akka is a framework for building 
highly concurrent, distributed, and resilient message-driven applications. Using streams is 
intuitive and safe way to do asynchronous, non-blocking backpressured processing of data.

The application uses as Source of the stream a file in the filesystem, but it can be easily 
replaced by another Source like Kafka, S3, Cassandra, HBase, HTTP, Mongo, etc. Depending on 
the requirements.

In general terms, the app first opens the file, then it creates tokens based on `\n`, then
Parses each line, converts it to json, creates the HttpRequest and sends it via a HttpPool.
All this in a sequentially-stream fashion.

### Structure
In the `src` folder there are the `main` code and the `test` (each of them in its respective folders). The Main.scala contains all the logic. The other are classes (PainPills) and utils (KaggleParser).


## Run
To compile the code:
```bash
$ sbt compile
```

To run the code:
```bash
$ sbt run
```