# Introduction #

There is a windows installer program which will allow windows users to deploy on a windows machine. This code automates some of the manual steps required for deployment on a Linux or Mac machine.

# Requirements #

Hadoop is linux centric program and hard codes some linux commands. In order to run the hydra client or any other Hadoop based program Cygwin or some other tool to add common unix commands must be installed. Cygwin is available at [cygwin.com](http://www.cygwin.com/). Also the directory **c:\cygwin\bin** must be in the path to allow windows to use unix commands. Test this by opening a command window and typing **ls**.

# Details #

Download Hydra\_Installer.exe and execute it:
  * It will allow you to choose where the hydra client is installed
  * The environment variable HYDRA\_HOME is added to the environment as the install directory for hydra.
  * %HYDRA\_HOME%\bin is added to the path
  * a program group is added which includes an uninstaller
  * hydra is run from the command line


# Configuration #
Hydra may be configured either by editing a Launcher.properties file with the data or by setting user preferences on the system. The program configure _configure.bat_ in the bin directory does this. Running it with no arguments lists the variables which may be configured. Passwords are encrypted before being stored. running configure with the argument **test**  will access hdfs, create a directory and a file with known content, read the file and then delete it verifying good access.