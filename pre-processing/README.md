# Kaggle Dataset pre-processing

The following script (pre-processing.sh) automatises the following tasks:

## Kaggle Pain Pills Dataset
To download the [Kaggle Pain Pills Dataset](https://www.kaggle.com/paultimothymooney/pain-pills-in-the-usa/version/2) requires to create a login and a programmatic access to [Kaggle](https://www.kaggle.com) API.

## Split the file
The original dataset is one TSV (tab-separated values file) which is 6GB compressed and around 70GB uncompressed.
In order to be efficient increase the performance in the creation of the data warehouse, we need to parallelize the process. 
We split the whole compressed file into many compressed files. The idea is not to decompress the whole file, but just work with 
batches of 5000000 lines.

## Upload the compressed files to S3
Finally, we upload each part into S3
