# Introduction #

Hydra reads and executes from the same files and directory as XTandem but reads one added file _Launcher.properties_ which has parameters describing the Hadoop cluster and setting hydra specific variables.

# Sample Data #
The downloadable file [SampleHydraData.ZIP](http://code.google.com/p/hydra-proteomics/downloads/detail?name=SampleHydraData.ZIP&can=2&q=) is a very small sample which may be used for testing hydra. It has a small search space and was used in development to allow rapid execution. The archive contains a _Launcher.properties_ which **must be edited** to match the user's cluster

# Running #

Hydra needs to be run from the directory where the data files are stored. In general paths may be relative or absolute but the code works best when the spectral files _mzml,mzXML,or mgf_ files are in the current directory.  Hydra also expects a file _Launcher.properties_ in the current directory. The properties are hydra and cluster specific and the same properties may be used for many runs.
> Alternatively edit the shell script _hydra.bat_ or _hydra.sh_ in hydra's bin to reference an absolute location of the _Launcher.properties_ file.

# First Test - Running Locally #

Hadoop has the ability to run _VERY SLOWLY_ on a single machine. As long as the samples are small, this is a reasonable mode for testing the configuration.
  * make sure hydra is installed properly
  * download and extract the sample data as described above
  * open the file _Launcher.properties_ in the sample data directory
  * make sure the line containing remoteBaseDirectory starts with **#**. This comments out the line.
  * open a shell window and **cd** to the sample directory.
  * type **hydra tandem.xml**
  * you should see the following:
```
C:\HydraSample>java  -Xmx1024m -Xms128m -cp C:\hydra/lib/target.jar;C:\hydra/lib/commons-dbcp-1.2.2.jar;C:\hydra/lib/commons-pool-1.3.jar;C:\hydra/lib/sam-1.67.jar;C:\hydra/lib/jsch-0.1.44-1.jar;C:\hydra/lib/hadoop-core-0.20.2.jar;C:\hydra/lib/commons-cli-1.2.jar;C:\hydra/lib/xmlenc-0.52.jar;C:\hydra/lib/commons-httpclient-3.0.1.jar;C:\hydra/lib/commons-logging-1.1.1.jar;C:\hydra/lib/commons-codec-1.3.jar;C:\hydra/lib/commons-net-1.4.1.jar;C:\hydra/lib/jetty-6.1.14.jar;C:\hydra/lib/core-3.1.1.jar;C:\hydra/lib/commons-el-1.0.jar;C:\hydra/lib/gdata-core-1.0-1.41.5.jar;C:\hydra/lib/google-collections-1.0-rc1.jar;C:\hydra/lib/jsr305-1.3.9.jar;C:\hydra/lib/gdata-spreadsheet-3.0-1.41.5.jar;C:\hydra/lib/xml-apis-1.0.b2.jar;C:\hydra/lib/xml-apis-ext-1.3.04.jar;C:\hydra/lib/jfreechart-1.0.13.jar;C:
\hydra/lib/jcommon-1.0.16.jar;C:\hydra/lib/vecmath-1.3.1.jar;C:\hydra/lib/biojava3-core-3.0.4.jar;C:\hydra/lib/biojava-3.04.jar org.systemsbiology.xtandem.hadoop.JXTandemLauncher  config=C:\hydra/data/Launcher.properties jar=C:\hydra/data/Hydra.jar params=tandem.xml
reading params file tandem.xml
reading params file tandem.xml
No File name forced building file name
Starting Job
Sep 20, 2012 11:33:46 AM org.apache.hadoop.metrics.jvm.JvmMetrics init
INFO: Initializing JVM Metrics with processName=JobTracker, sessionId=

...
...
...
Capture Output in 2.207 sec


Input file = or20080317_s_silac-lh_1-1_01short.mzxml
Max Mass Fragments = 51
MaxFragmentsAtMass = 51
OutputLocationBase = E:/HydraSample/OutputData
ParamsPath = E:/HydraSample/tandem.xml
Reduce input records = 7
Taxonomy database = yeast_orfs_pruned.fasta
Total Fragments = 44623
TotalDatabaseFragments = 44623
TotalDotProducts = 11749
TotalScans = 190
TotalScoredScans = 25
TotalScoringScans = 7
TotalSpectra = 912
total time in 2.209 sec
Scorer took  6.33 sec
Score Combiner took  4.93 sec
Consolidator took  1.96 sec
Total Scoring took 13.75 sec
```

## Talking to HDFS ##
The distribution ships with a shell script **configure** (sh or bat file) to allow setup and testing of a configuration. This allows setting of Java preferences defining the Hadoop cluster. Most of the parameters can also be set in the _Launcher.properties_ file but configure will remember the values.
host**the name of ip of the system hosting hdfs.** **port** the port used to talk to hdfs 8020 is the current default - older systems use 9000
jobtracker **user** a user authorized to log onto the hadoop system.
password**the users password - this will be encrypted** **path** a default path into hdfs
Once these parameters are set up calling configure with the command line _test_ will test the ability to talk to hdfs


## Running on a remote system ##
The script **hydra** (sh or bat file) references a file called _Launcher.properties_. To run on a remote system this file must be configures. If **configure** has been run, the only property which needs to be specified is **remoteBaseDirectory**. This is the path on HDFS to run store data while running on the cluster.
NOTE_if the _remoteBaseDirectory_ ends with 

<LOCAL\_DIRECTORY>_ then the name of the current directory on the client machine will be used instead of 

<LOCAL\_DIRECTORY>

_. This allows the same_Launcher.properties\_to be used for many jobs.

> Once _Launcher.properties_ is edited and saved simply run hydra from the command line as was tested with the local version.