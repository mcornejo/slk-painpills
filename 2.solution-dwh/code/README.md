# PainPills Spark Job

This application contains the two main jobs to process the data of the PainPills Dataset.
- `KaggleJob`: This job creates the datawarehouse. It reads gzipped files from S3 (or any other source) 
and it parses each line in order to create a PainPills object, then it creates the partitions and stores
the data in ORC format. 
- `AggregateJob`: This job creates three data marts, every one of them with a different aggregation.

## Source Code
The project uses SBT as build tool. To compile the code:
```bash
$ sbt compile
```

To execute the code:
```bash
$ sbt run
```

In the `src` folder it is possible to find the package `com.murdix.painpills` containing all the code. 
In `jobs` there are the two Objects with Main. In `parser` there is a utility to parse the files and in `entities` there is the PainPills class definition. 
