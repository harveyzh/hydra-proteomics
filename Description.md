# Introduction #

Shotgun Proteomics involves large search problems comparing many spectra with possible peptides. As researchers  apply modifications and consider alternate cleavages, the search space grows by a few orders of magnitude. Modern searches strain the resources of a single machine. We have an implementation which uses the Hadoop version of Google's Map-Reduce algorithm to search Proteomics databases.




# Details #

Shotgun Proteomics involves large search problems comparing many spectra with possible peptides. As researchers  apply modifications and consider alternate cleavages, the search space grows by a few orders of magnitude. Modern searches strain the resources of a single machine. We have an implementation which uses the Hadoop version of Google's Map-Reduce algorithm to search Proteomics databases.

# Settable Properties #
All properties are stored in a the same property set used by X!Tandem parameters. All names start with  _italic\_org.systemsbiology_italic_to avoid conflicts with properties used by other systems_

## Job Properties ##
| **Property** | **Description** | **Sample** |
|:-------------|:----------------|:-----------|
| org.systemsbiology.aws.UseSpotMaster| use spot intances | yes        |
| org.systemsbiology.aws.ClusterSize| Number of instances | 10         |
|org.systemsbiology.aws.KeepClusterAfterJob| if true leave the cluster running | yes        |
|org.systemsbiology.aws.InstanceSize| A size designated by aws | C1Medium   |

## Cluster Properties ##
| **Property** | **Description** | **Sample** |
|:-------------|:----------------|:-----------|
| org.systemsbiology.aws.UseSpotMaster| use spot intances | yes        |
| org.systemsbiology.aws.ClusterSize| Number of instances | 10         |
|org.systemsbiology.aws.KeepClusterAfterJob| if true leave the cluster running | yes        |
|org.systemsbiology.aws.InstanceSize| A size designated by aws | C1Medium   |


## Amazon Properties ##
| **Property** | **Description** | **Sample** |
|:-------------|:----------------|:-----------|
| org.systemsbiology.aws.UseSpotMaster| use spot intances | yes        |
| org.systemsbiology.aws.ClusterSize| Number of instances | 10         |
|org.systemsbiology.aws.KeepClusterAfterJob| if true leave the cluster running | yes        |
|org.systemsbiology.aws.InstanceSize| A size designated by aws | C1Medium   |