# Pi Calculation using MapReduce with Hadoop

## Description
Calculating the value of Pi is a classic problem in computer science and mathematics. The Monte Carlo method is a statistical technique that uses random sampling to estimate numerical results. In the case of Pi calculation, we can use Monte Carlo simulations to estimate Pi by generating random points within a square and determining how many fall within a quarter of a unit circle. The ratio of points within the circle to the total number of points generated provides an approximation of Pi.

## Objective
The objective of this project is to implement a MapReduce program in Apache Hadoop to perform Pi calculation using the Monte Carlo method. This involves generating random points within a square, determining which points fall within a quarter of a unit circle, and aggregating the results to estimate the value of Pi.

## Design
The design involves generating random points within a square and using a MapReduce program to determine how many points fall within a quarter of a unit circle. The ratio of points within the circle to the total number of points generated is used to estimate Pi.


## Requirement

Follow this [link](https://github.com/Senedaa/Cloud-Computing/blob/main/CS570_week2_q11_20073_Sened_Desalegn.pdf) to install VM on GCP.



## Implementation Steps
1. **Setup Java and Hadoop**:
   - Ensure Java is installed.
   - Download and install Hadoop.
   ```sh
   sudo apt-get update
   sudo apt-get install -y openjdk-8-jdk
   wget https://downloads.apache.org/hadoop/common/hadoop-3.3.4/hadoop-3.3.4.tar.gz
   tar -xzvf hadoop-3.3.4.tar.gz
   mv hadoop-3.3.4 ~/hadoop
   
   ```
   

2. **Generate Input Data**:
   - Create a Java program to generate random points within a square.
   - Compile and run the program to generate input data.
   - This the GenerateRandomNumbers.java file.
3. **Configure Hadoop**:
   - Set up SSH for communication between nodes.
   - Format HDFS and start DFS.
   ```sh
   cd ~/hadoop
   ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
   cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
   chmod 0600 ~/.ssh/authorized_keys
   bin/hdfs namenode -format
   sbin/start-dfs.sh
   ```
4. **Upload Input Data**:

Create directories in HDFS and upload the input data:

  ```sh
    bin/hdfs dfs -mkdir /user
    bin/hdfs dfs -mkdir /user/sdesaleg137
    bin/hdfs dfs -mkdir /user/sdesaleg137/picalculate
    bin/hdfs dfs -mkdir /user/sdesaleg137/picalculate/input
    bin/hdfs dfs -put ~/PiCalculation/PiCalculationInput /user/sdesaleg137/picalculate/input

  ```

5. **Write and Execute MapReduce Program**:
   - Write a MapReduce program in Java. PiCalculation.java
   - Compile, create a JAR file, and execute the MapReduce job.
  
6. **Result**:

   

# Enhancement Ideas

- **Double-Type Random Number Pairs**: Generate double-type random number pairs instead of integer type to improve accuracy.

- **Implement Combine Function**: Implement a combine function for the MapReduce program to reduce the time taken for data transfer and the amount of data.

# Conclusion

The implementation of a MapReduce program using Hadoop provides an efficient way to estimate the value of Pi using the Monte Carlo method. By distributing the computation across multiple nodes, Hadoop enables the processing of large datasets for more accurate results.

# Google Slide Presentation
https://docs.google.com/presentation/d/17Z8Cx5pM8YwFcgVy2UzIgJ74IPEbrtQ0/edit?usp=sharing&ouid=114649998640594619120&rtpof=true&sd=true

# Appendix

   