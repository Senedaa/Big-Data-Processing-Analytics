# Full Inverted Index Generation using Hadoop MapReduce

## Description

This project outlines the steps to generate a full inverted index using Hadoop MapReduce. An inverted index is a data structure used to map words to the documents (or files) in which they occur. The full inverted index includes all words across all input files, providing a comprehensive mapping of word occurrences throughout the dataset.

## Design

### Data Flow:
- **Input:** Text files containing words.
- **Mapper:** Processes each line of input and emits word-document pairs.
- **Combiner:** Optional step to reduce the amount of data transferred between Mapper and Reducer by aggregating results locally.
- **Reducer:** Aggregates all word-document pairs and produces the final inverted index.

### Components:
- **HDFS:** Hadoop Distributed File System to store input and output data.
- **MapReduce Program:** Java code to perform the mapping and reducing tasks.

## Requirements

### Setting Up Hadoop and Preparing Input

1. **Create Directories in HDFS:**
    ```bash
    cd ~/hadoop-3.4.0

    bin/hdfs dfs -mkdir /user
    bin/hdfs dfs -mkdir /user/sdesalge137
    bin/hdfs dfs -mkdir /user/sdesalge137/fullinvertedindexcalculation
    bin/hdfs dfs -mkdir /user/sdesalge137/fullinvertedindexcalculation/input
    ```

2. **Verify Directory Creation:**
    ```bash
    bin/hdfs dfs -ls /user/sdesalge137/fullinvertedindexcalculation/input
    ```

3. **Create the Input Files:**
    ```bash
    echo “it is what it is” > file0
    echo “what is it” > file1
    echo “it is a banana” > file2
    ```

4. **Upload Input Files to HDFS:**
    ```bash
    bin/hdfs dfs -put ~/InvertedIndex/file0 /user/sdesalge137/fullinvertedindexcalculation/input
    bin/hdfs dfs -put ~/InvertedIndex/file1 /user/sdesalge137/fullinvertedindexcalculation/input
    bin/hdfs dfs -put ~/InvertedIndex/file2 /user/sdesalge137/fullinvertedindexcalculation/input
    ```

### Compiling Java Code

5. **Compile Java Code:**

    1. **Compile InvertedIndex.java:**
        - This file contains the main class for the MapReduce job, defining the job configuration and execution logic.
    
    2. **Compile InvertedIndexDriver.java:**
        - This script serves as the driver class, specifying job parameters, including input/output paths and mapper/reducer classes, and launching the MapReduce job.
    
    3. **Compile InvertedIndexMapper.java:**
        - This file implements the Mapper class, parsing input text lines, emitting intermediate key-value pairs representing word-document occurrences.
    
    4. **Compile InvertedIndexReducer.java:**
        - This script implements the Reducer class, aggregating intermediate key-value pairs from the mapper, and constructing the final inverted index mapping words to the documents where they occur.

    ```bash
    javac -classpath $(~/hadoop-3.4.0/bin/hadoop classpath) *.java
    ```

### Creating JAR File

6. **Create JAR File:**
    ```bash
    jar cf inverted-index.jar *.class
    ```

## Implementation

### Running the MapReduce Job

7. **Submit and Run the Job:**
    ```bash
    cd ~/hadoop-3.4.0

    bin/hadoop jar inverted-index.jar InvertedIndex /user/sdesalge137/fullinvertedindexcalculation/input /user/sdesalge137/fullinvertedindexcalculation/output
    ```

### Testing

8. **Verify Output:**
    ```bash
    bin/hdfs dfs -ls /user/sdesalge137/fullinvertedindexcalculation/output
    bin/hdfs dfs -cat /user/sdesalge137/fullinvertedindexcalculation/output/part-*
    ```

## Conclusion

This documentation outlines the steps required to set up Hadoop, compile your Java code, create a JAR file, and run a MapReduce job to generate a full inverted index from input files stored in HDFS. Adjust paths and commands as necessary based on your specific environment and file locations. This documentation can serve as a reference for future deployments and troubleshooting.

## Google Slide Presenation 

https://docs.google.com/presentation/d/1fM-vx3PeApHRCqt088Lb8qD9oNINXJ7sxkdOzcjqm2A/edit#slide=id.g27322a04079_1_235

## Appendix

