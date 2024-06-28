# PageRank Project on GCP

## Description
This project demonstrates the implementation of the PageRank algorithm using PySpark and Scala on Google Cloud Platform (GCP). The algorithm ranks web pages based on link structures, simulating the functionality of search engines.

## Design
The project is divided into two main parts:
1. **PySpark Implementation**: Using Python and Apache Spark for distributed computation.
2. **Scala Implementation**: Using Scala and Apache Spark for distributed computation.

## Implementation

### Part One: PySpark

#### Step 1: Create a Dataproc Cluster
- Authenticate and create a Dataproc cluster in GCP:
  ```bash
  gcloud auth login
  gcloud dataproc clusters create pagerank-cluster \
      --region=us-central1 \
      --zone=us-central1-a \
      --single-node \
      --master-machine-type=n1-standard-4 \
      --master-boot-disk-size=50GB \
      --image-version=1.5-debian10
  ```

#### Step 2: Prepare the PySpark Script
- Create `pagerank.py`.
#### Step 3: Submit the PySpark Job
- Submit the job to the cluster:
  ```bash
  gcloud dataproc jobs submit pyspark gs://your_bucket/pagerank.py \
      --cluster=pagerank-cluster \
      --region=us-central1 \
      -- gs://your_bucket/input.txt 10
  ```

### Part Two: Scala

#### Step 1: Set Up Project Structure
- Create the necessary directories and files:
  ```bash
  mkdir pagerank
  cd pagerank
  mkdir -p src/main/scala
  ```

#### Step 2: Create `build.sbt`
- Define dependencies follow the file.

#### Step 3: Create `SparkPageRank.scala`
- Implement the PageRank algorithm.

#### Step 4: Compile and Upload
- Compile the project and upload the JAR to GCS:
  ```bash
  sbt package
  gsutil cp target/scala-2.12/sparkpagerank_2.12-1.0.jar gs://your_bucket/
  ```

#### Step 5: Submit Spark Job
- Submit the job to the Dataproc cluster:
  ```bash
  gcloud dataproc jobs submit spark --cluster=pagerank-cluster --region=us-central1 \
      --jars=gs://your_bucket/sparkpagerank_2.12-1.0.jar \
      --class=org.apache.spark.examples.SparkPageRank \
      -- gs://your_bucket/input.txt 10
  ```

## Results
The PageRank algorithm was executed over multiple iterations. As the iterations increased, the ranks of the pages converged, demonstrating improved accuracy in ranking based on the link structure. This reflects the algorithm's capability to simulate the distribution of link importance across web pages.

## Conclusion
The project successfully utilized GCP's Dataproc for distributed computing with both PySpark and Scala. This implementation showcases the effectiveness of cloud resources for handling large-scale data processing tasks, such as the PageRank algorithm.

## Presentation Linke

## Appendix