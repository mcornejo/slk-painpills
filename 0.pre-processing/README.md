# Kaggle Dataset pre-processing

The following script (`pre-processing.sh`) automatises the following tasks:

## Kaggle Pain Pills Dataset
To download the [Kaggle Pain Pills Dataset](https://www.kaggle.com/paultimothymooney/pain-pills-in-the-usa/version/2) requires to create a login and a programmatic access to [Kaggle](https://www.kaggle.com) API.

## Split the file
The original dataset is one TSV (tab-separated values file) which is 6GB compressed and around 70GB uncompressed.
In order to increase the performance at the creation of the data warehouse, we need to parallelize the process.

We split the whole compressed file into many compressed files. The idea is not to decompress the whole file at once, but just work with batches of 5,000,000 lines. This size (around 120MB) is good to profit of the parallel nature of Spark but in this case, we will use it as one hour of data. 

## Upload
Finally, we upload each compressed part into AWS S3 using the aws Command Line Interface.