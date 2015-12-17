This is a rewrite of the X!Tandem search program using Hadoop for supporting Tandem Mass Spectrometry Proteomics developed by the Institute for Systems Biology in Seattle. The code is capable of running stand alone on a machine supporting Java (advised only for small problems), on an external Hadoop cluster or on Amazon EC2 using Elastic Map-Reduce.
> The program can take as an input the same parameters file as X!Tandem with properties of the cluster configured in external configurations. Output files are generated on the cluster and copied into the same local directories used by X!Tandem.
> > More advanced modes allow the input to specify a directory of input (mzxml or mzml files) allowing all to be processed. There is an option to output pep.xml format directly.
Check out the following documentation:
**[General Practice for deploying the binaries](GeneralDeploymentIssues.md)** [Setting Hydra Properties](ParametersInLauncherProperties.md)
**[Specifics of Windows deployment](DeploymentOnWindows.md)** [Specifying properties of the cluster](ParametersInLauncherProperties.md)
