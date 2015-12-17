# Introduction #

Hydra is designed to function as a pluggable replacement for the X!Tandem search engine with added capabilities described in other documents. It is designed to run from the command line using a single argument, an XML file containing parameters required for the search. The deployments (see [downloads](http://code.google.com/p/hydra-proteomics/downloads/list)) include a batch file (windows) or shell script


# Details #

## Environment Variables ##

  * **HYDRA\_HOME** absolute path to the directory where hydra code is installed _italic_
  * **HYDRA\_HOME/bin** should be added to the path

## Executing the script ##
  * run the program from the directory containing the spectrum files or its immediate parent directory.
  * Files referenced in the parameters file _usually tandem.xml_ should have paths relative to the directory in which the code runs.
  * parameters describing the Hadoop cluster and Hydra specific parameters are described in a config file,
