#!/bin/bash

kaggle datasets download paultimothymooney/pain-pills-in-the-usa
chmod a+x arcos_all_washpost.tsv.gz
# Generating files of 120 MB aprox.
gunzip -c arcos_all_washpost.tsv.gz | split -d -l 5000000 --filter='gzip > $FILE.gz'
aws s3 cp . s3://pain-pills-dump --recursive