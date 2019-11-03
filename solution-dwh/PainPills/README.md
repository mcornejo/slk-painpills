# PainPills Spark Job

This application contains the two main jobs to process the data of the PainPills Dataset.
- `AggregateJob`: This job creates three data mart, every one of them with a different aggregation.
- `KaggleJob`: This job creates the datawarehouse. It reads gzipped files from S3 (or any other source) 
and it parses each lines in order to create a PainPills object. Finally it creates the partitions and stores
the data in ORC format. 

## Source Code
The project uses SBT as build tool. To compile the code:
```bash
$ sbt compile
```

In the package `com.murdix.painpills` there are all the code. In `jobs` there are the two Objects with Main. 
In `parser` there is an utility to parse the files and finally in `entities` there is the PainPills class definition. 
