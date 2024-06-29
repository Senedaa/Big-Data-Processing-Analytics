# Setting up Apache Spark on Kubernetes

This guide walks you through setting up Apache Spark on Google Kubernetes Engine (GKE), utilizing persistent storage with NFS, deploying Spark applications, and running example jobs like Word Count and PageRank.

## Description

Apache Spark is a fast and general-purpose cluster computing system that provides high-level APIs in Java, Scala, Python, and R, and an optimized engine that supports general execution graphs. Kubernetes, on the other hand, is an open-source container orchestration platform that automates the deployment, scaling, and management of containerized applications.

This setup combines the scalability and resource efficiency of Kubernetes with the data processing capabilities of Apache Spark, allowing you to run distributed Spark jobs on a Kubernetes cluster. Persistent storage using NFS ensures that data remains accessible and consistent across Spark application runs.

## Design

### Components Used:

- **Google Kubernetes Engine (GKE)**: Provides managed Kubernetes clusters.
- **NFS Server Provisioner**: Helm chart used to provision NFS server for persistent storage.
- **Bitnami Apache Spark Helm Chart**: Simplifies deployment and management of Apache Spark on Kubernetes.
- **Spark Applications**: Custom JAR files and Python scripts deployed to the Kubernetes cluster.
- **Persistent Volume Claim (PVC)**: Ensures data persistence across Spark jobs using NFS-based storage.

### Architecture:

1. **GKE Cluster**: A Kubernetes cluster is created on GKE with specific machine types.
2. **NFS Storage**: PersistentVolumeClaim (PVC) backed by NFS for storing Spark application data.
3. **Apache Spark Deployment**: Deployed using Helm on Kubernetes with configuration for persistent storage and worker node scaling.
4. **Application Deployment**: Spark applications (JAR files and Python scripts) are copied to the PVC.
5. **Job Execution**: Jobs such as Word Count and PageRank are submitted to Spark using Kubernetes integration.

## Implementation

### Step-by-Step Setup:

1. **Create GKE Cluster**:

   - Use `gcloud` CLI to create a GKE cluster with specified configurations.

   ```bash
   gcloud container clusters create spark --num-nodes=1 --machine-type=e2-highmem-2 --region=us-west1
   ```

2. **Install NFS Server Provisioner**:

   - Add Helm repository and install NFS Server Provisioner to provide persistent storage.

   ```bash
   helm repo add stable https://charts.helm.sh/stable
   helm install nfs stable/nfs-server-provisioner --set persistence.enabled=true,persistence.size=5Gi
   ```

3. **Create Persistent Volume and Pod**:

   - Define a PVC and Pod configuration YAML file, specifying NFS as the storage class.

   Apply the configuration:

   ```bash
   kubectl apply -f spark-pvc.yaml
   ```
![Image Description](https://github.com/Senedaa/Big-Data-Processing-Analytics/raw/main/Pyspark/Word%20Count%2B%20PageRank/images/Picture1.png)

4. **Prepare Application JAR File**:

   - Use Docker to extract Spark example JAR files for deployment.

   ```bash
   docker run -v /tmp:/tmp -it bitnami/spark -- find /opt/bitnami/spark/examples/jars/ -name spark-examples* -exec cp {} /tmp/my.jar \;
   ```

5. **Copy Files to Persistent Volume**:

   - Use `kubectl cp` to copy JAR files and data to the PVC.

   ```bash
   kubectl cp /tmp/my.jar spark-data-pod:/data/my.jar
   kubectl cp /tmp/test.txt spark-data-pod:/data/test.txt
   ```

6. **Deploy Apache Spark on Kubernetes**:

   - Create a Helm chart configuration file to deploy Spark with persistent storage configuration (`spark-chart.yaml`).

   Deploy Apache Spark using the Bitnami Apache Spark Helm chart:

   ```bash
   helm repo add bitnami https://charts.bitnami.com/bitnami
   helm install spark bitnami/spark -f spark-chart.yaml
   ```

7. **Access Apache Spark Web UI**:

   - Retrieve external IP of Spark service to access the Spark Web UI in a browser.

   ```bash
   kubectl get svc -l "app.kubernetes.io/instance=spark,app.kubernetes.io/name=spark"
   ```

8. **Run Spark Jobs**:

   - Submit Spark jobs (e.g., Word Count) using `kubectl run` and `spark-submit` commands.

   ```bash
   kubectl run --namespace default spark-client --rm --tty -i --restart='Never' \
   --image docker.io/bitnami/spark:3.0.1-debian-10-r115 \
   -- spark-submit --master spark://LOAD-BALANCER-External-ip-ADDRESS:7077 \
   --deploy-mode cluster \
   --class org.apache.spark.examples.JavaWordCount \
   /data/my.jar /data/test.txt
   ```

9. **View Job Output**:

   - Monitor job execution and view output logs on the Spark Web UI or by accessing worker pods.

   ```bash
   kubectl get pods -o wide | grep WORKER-NODE-ADDRESS
   kubectl exec -it <worker-node-name> -- bash
   cd /opt/bitnami/spark/work
   cat <taskname>/stdout
   ```

10. **Run PageRank on PySpark**:

    - Execute PySpark scripts for tasks like PageRank using interactive sessions.

    ```bash
    kubectl exec -it spark-master-0 -- bash
    pyspark
    ```

    ```python
    cd /opt/bitnami/spark/examples/src/main/python
    spark-submit pagerank.py /opt 2
    ```

## Results

The setup allows for scalable execution of Apache Spark jobs on Kubernetes, leveraging persistent storage for data integrity and reliability across job executions. The combination of GKE, NFS, and Apache Spark Helm charts facilitates seamless deployment and management of distributed data processing tasks.

By following these steps, you can effectively harness the power of Apache Spark on Kubernetes to handle large-scale data processing and analytics workloads in a cloud-native environment.

![Image Description](https://github.com/Senedaa/Big-Data-Processing-Analytics/raw/main/Pyspark/Word%20Count%2B%20PageRank/images/Picture3.png)

![Image Description](https://github.com/Senedaa/Big-Data-Processing-Analytics/raw/main/Pyspark/Word%20Count%2B%20PageRank/images/Picture4.png)


## Conclusion
The entire project is developed in Google Cloud Platform which provides different tools from which we are using Google Kubernetes Engine(GKE).
Then using PySpark API we implemented Word Count and PageRank applications on Apache Spark running on Google Kubernetes Engine.

## Presentation Link
https://docs.google.com/presentation/d/1RYzBBLNF0T0Ugr2Kh2CYHxtOds19omjI-XZCEtQIbeM/edit?usp=sharing

## Appendix
