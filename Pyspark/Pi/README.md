### Description

This document outlines the process of calculating an estimate of Pi using the Monte Carlo method with PySpark on Google Cloud Dataproc. The Monte Carlo method involves randomly sampling points within a square and determining the ratio that fall within a quarter-circle inscribed within the square. This ratio approximates Pi.

### Prerequisites

[Guide to Creating Bucket and Dataproc on GCP (PDF)](https://github.com/Senedaa/Big-Data-Processing-Analytics/blob/main/Pyspark/Creating%20Bucket%20and%20Dataproc%20on%20GCP.pdf)

### Design

- **Architecture Overview**: 
  - Utilizes Google Cloud Dataproc for managed Apache Spark and Hadoop clusters.
  - Dynamic resource allocation ensures scalability and cost-efficiency.

- **Data Processing Strategy**:
  - Implements the Monte Carlo method for Pi estimation.
  - Parallelizes computation using Spark across multiple partitions for performance optimization.

- **Data Management**:
  - Stores input data, intermediate results, and final output in Google Cloud Storage (GCS).
  - Outputs results in JSON format for easy retrieval and analysis.

### Implementation

#### Step 1: Prepare the PySpark Script

1. **Script Creation**: Create a Python script named `calculate_pi.py` that contains the PySpark code for the Pi calculation using the Monte Carlo method.

2. **Upload Script**: Upload `calculate_pi.py` to a Google Cloud Storage (GCS) bucket. Replace `gs://bigdata_pyspark/` with your own GCS bucket URI.

   ```bash
   gsutil cp calculate_pi.py gs://your-bucket-name/
   ```
![Image Alt Text](https://github.com/Senedaa/Big-Data-Processing-Analytics/raw/main/Pyspark/Pi/images/images1.png)

#### Step 2: Submit PySpark Job to Dataproc

1. **Authenticate**: Log in to Google Cloud with authentication.

   ```bash
   gcloud auth login
   ```

2. **Submit Job**: Submit the PySpark job to Dataproc, specifying your cluster name and region.

   ```bash
   gcloud dataproc jobs submit pyspark gs://your-bucket-name/calculate_pi.py \
       --cluster=your-dataproc-cluster-name \
       --region=your-cluster-region \
       -- \
       --partitions=4 \
       --output_uri=gs://your-bucket-name/pi-output
   ```

   - `--partitions`: Number of parallel partitions for the calculation.
   - `--output_uri`: URI where the output JSON file will be saved.

#### Step 3: View Output

1. **Check Job Status**: Monitor job status and verify completion.

   ```bash
   gcloud dataproc jobs list --region=your-cluster-region
   ```

2. **Retrieve Results**: View the results stored in the specified output URI.

   ```bash
   gsutil ls gs://your-bucket-name/pi-output/
   gsutil cat gs://your-bucket-name/pi-output/*.json
   ```
   ![Result](https://github.com/Senedaa/Big-Data-Processing-Analytics/raw/main/Pyspark/Pi/images/result.png)


### Conclusion
In conclusion, this document has illustrated the process of estimating Pi using the Monte Carlo method implemented with PySpark on Google Cloud Dataproc. By leveraging cloud computing capabilities, we efficiently parallelized the computation across multiple nodes, enhancing performance and scalability. This approach not only demonstrates the power of distributed computing but also provides a practical example of using cloud infrastructure for scientific computation tasks.

### Presentation Link
https://docs.google.com/presentation/d/13aso3mSUf12XZJQTF4u2T5FJW0z-uB234VQy4_6ZADc/edit?usp=sharing

## Appendix
