# SLK-PainPills

The idea of this repo is to show two alternatives to ingest and process files in a big data environment. To do so, we take the [Kaggle Pain Pills Dataset](https://www.kaggle.com/paultimothymooney/pain-pills-in-the-usa/version/2) that contains 178,598,026 rows (around 6GB compressed, 70GB uncompressed). 

We present two different approaches that can be used depending on the nature of the data. A first solution is completely based on Elasticsearch as a data aggregator. In this scenario, there are no aggregations to code, but everything is done using Elasticsearch. This alternative is a great way to do it when the data is "small enough". While Elasticsearch is capable of handling large amounts of data, it can be costly.

A second approach is presented by implementing a data warehouse, with data pipelines using Spark that ingest, transform and extract the data. It creates data marts with aggregated data. This approach is useful when the data is massive, and it requires multiple pipelines to process it.

There are three folders as follows:
- `0.pre-processing`: This folder contains some utils to handle the Dataset.
- `1.solution-elk`: Contains an elasticsearch-only solution to handle the aggregations and visualizations.
- `1.solution-elk/code`: Contains the code to ingest the data from the TSV to elasticsearch.
- `2.solution-dwh`: Contains the solution to handling a big data architecture using big-data tools.
- `2.solution-dwh/code`: Contains the code of two spark jobs, one for the creation of the data warehouse and one for aggregation.

To improve the readability of the project, each of the folder above contains its own README with its discussion.
