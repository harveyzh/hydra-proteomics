# Introduction #

Hydra maintains a separation of parameters for proteomic search, which are specified in a passed xml file capable of being used by other search engines such as tandem and properties

# Specifying the Cluster #

The  _italic_ remoteBaseDirectory _italic_ property determines where on the cluster the code is run.
**If not specified the code will run on the local box** If the

# Details #
Note - needed only if running on a remote cluster
  * **remote** host is the address of the main node on the cluster
  * **remotePort** is the job tracker port
  * **remoteJobTracker** may be found in the Hadoop configuration file **mapred-site.xml** as the property **mapred.job.tracker**.
  * **remoteUser** user authorized to use the remote host
  * **encryptedRemotePassword** is a string encrypting the users remote password - it is only used on the client and may be obtained by running the config command file in the n=bin deployment of using java to run org.systemsbiology.remotecontrol.RemoteUtilities and specifying password=

&lt;mypassword&gt;

 (for example password=secret). The program will give an encrypted string to use.
```
#
# properties file for Hydra Launcher
#
#
# may be specified on the command line as well
#
== Properties for defining the cluster == 
# If using a cluster this is the hdfs directory    if unspecified
# will run locally
# use line below to run remotely
#use line below to run on the cluster
#remoteBaseDirectory=/user/howdah/JXTandem/data/<LOCAL_DIRECTORY>
#use line below to run on the ec2
#remoteBaseDirectory=s3n://lordjoe/<LOCAL_DIRECTORY>
#
#  hdfs job tracker
remoteHost=glados
#
#  port on the job tracker
remotePort= 9000
#
#  Actual job tracker with port - not the same as name node port
remoteJobTracker= glados:9001
#
#  user on the job tracker
remoteUser=slewis
#
#  encrypted password  on the job tracker alternative is plainTextRemotePassword
#encryptedRemotePassword=aWnQ4DbdoSGivLcfi56hGKK8tx+LnqEYory3H4ueoRiivLcfi56hGA==
#
#  plain password  on the job tracker alternative is encryptedRemotePassword
#plainTextRemotePassword=secret
```

## Properties for defining Hydra on Hadoop ##
These are handled by the launch engine
```
#
#
# Maximum number of peptides to handle in a reducer - raising this will raise the memory requirements on the cluster
# default is 20000
maxPeptideFragmentsPerReducer=30000

#
# sets mapres.max.splt.size
#  a lower number forces more mappers in my splitters which is good
# the default is 64mb but for what we are doing smaller seems to be better
# when mappers do a lot of work - this is 16 megs
#maxSplitSize=16777216
#maxSplitSize=20000000

#
# MaxReduceTasks tunes the cluster - increase this number for
# bigger clusters this number usually works well at approximately the number of cores
maxReduceTasks = 36

#
#
# delete hadoop directories after the process is finished with them
# set fo false if debugging and the intermediate data wants to be examined
deleteOutputDirectories=true
 #
 # compressed files take less space and are harder to read
# set false if you plan to manually read intermediate files
compressIntermediateFiles=true
 #
 # Maximum memory assigned to child tasks  in megabytes
 # maps to "mapred.child.java.opts", "-Xmx" + maxMamory + "m"
maxClusterMemory=3600
```


## Properties passed to Hadoop tasks ##
These are handled passed to Hadoop as defined properties in the command line with the text DEFINE_removed from the property name - this is the
hydra way of
```
#
# override specific Hadoop prepoerties - save writing specific code
DEFINE_io.sort.factor=100
DEFINE_io.sort.mb=400
# don't start reducers until most mappers done
DEFINE_mapred.reduce.slowstart.completed.maps=0.5
#  number of map tasks for the database
DEFINE_org.systemsbiology.jxtandem.DesiredDatabaseMappers=8
#  number of map tasks for the spectra
DEFINE_org.systemsbiology.jxtandem.DesiredXMLInputMappers=7
# save a scans file
DEFINE_org.systemsbiology.xtandem.SaveScansData=yes
# Write out PepXML
DEFINE_org.systemsbiology.xtandem.hadoop.WritePepXML=yes
# algorithms
DEFINE_org.systemsbiology.algorithm=org.systemsbiology.xtandem.TandemKScoringAlgorithm;org.systemsbiology.xtandem.probidx.ProbIdScoringAlgorithm
# when yes each input file goes to a separate output file
DEFINE_org.systemsbiology.xtandem.MultipleOutputFiles=yes
```
## Properties for use with  Amazon Elastic Map Reduce ##
These are ignored unless remoteBaseDirectory starts with 3n://
indicating that the code is to execute on an instance of Elastic Map Reduce.
**bold** TODO Document How to set up AWS **bold**
```
#
# Used only when using Amazon Elastic Map Reduce
# try spot instances
# try spot instances
DEFINE_org.systemsbiology.aws.UseSpotMaster=yes
# use 6 instances
DEFINE_org.systemsbiology.aws.ClusterSize=9
# keep the cluster running
DEFINE_org.systemsbiology.aws.KeepClusterAfterJob=yes
# use this jar
#DEFINE_org.systemsbiology.tandem.hadoop.PrebuiltJar=Mar300846_0.jar
DEFINE_org.systemsbiology.aws.InstanceSize=large
```_
