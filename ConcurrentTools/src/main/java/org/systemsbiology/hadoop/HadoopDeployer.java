package org.systemsbiology.hadoop;


import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * org.systemsbiology.hadoop.HadoopDeployer
 * build a hadoop jar file
 *
 * @author Steve Lewis
 * @date Apr 3, 2007
 */
public class HadoopDeployer {
    public static HadoopDeployer[] EMPTY_ARRAY = {};
    public static Class THIS_CLASS = HadoopDeployer.class;

    public static final String[] EXCLUDED_JARS_PREFIXES = {
            //          core-3.1.1.jar,\
            "hadoop-",
            "commons-logging-",
            "commons-lang-",
            "commons-codec-",
            "commons-httpclient-",
            "commons-net-",
            "avro-",
            "jackson-",
            "hsqldb-",
            "ftpserver-",
            "ftplet-",
            "jackson-",
            "ant-",
            "aspectjtools-",
            "aspectjrt-",
            "slf4j-",
            "xmlenc-",
            "paranamer-",
            "qdox-",
            "jetty-",
            "jsp-",
            "jasper-",
            "jetty-",
            "jets3t-",
            "jdiff-",
            "aws-",
            "mockito-",
            "oro-",
            "mina-",
            "jersey",
            "kfs-",
            "jetty-",
            "snappy",
            "servlet",
            "google",
            "jfreechart",
            "core",
            "gdata",
            "netty",
            "guava",
            "protobuf",
            "jaxb",
            "jettison",
            "jsr305",
            "velocity",
    };

    public static final String[] EXCLUDED_JARS_LIST = {
            "idea_rt.jar",
            "javaws.jar",
            "jce.jar",
            "hadoop-0.20.1+152-mrunit",
            "management-agent.jar",
            "alt-rt.jar",
            "charsets.jar",
            "classes.jar",
            "jconsole.jar",
            "slf4j-api-1.4.3.jar",
            "slf4j-log4j12-1.4.3.jar",
            "jsse.jar",
            "laf.jar",
            "ui.jar",
            "testng-5.5-jdk15.jar",
            "junit-dep-4.8.1.jar",
            //    "hadoop-0.20.2-core.jar",
            "hadoop-0.20.2-tools.jar",
            //     "hadoop-core-0.20.1.jar",
            //    "commons-logging-1.1.1.jar",
            //     "commons-cli-1.2.jar",
            //     "commons-logging-1.1.1.jar",
            "slf4j-log4j12-1.4.3.jar",
            "log4j-1.2.15.jar",
            //  "xmlenc-0.52.jar",           //
            //    "commons-cli-1.2.jar",          //
            //    "commons-logging-api-1.0.4.jar",
            //    "commons-httpclient-3.0.1.jar",    //
            //    "commons-net-1.4.1.jar",           //
            //    "slf4j-api-1.4.3.jar",
            "karmasphere-client.jar",
            "servlet-api-2.5-6.1.14.jar",
            //    "commons-codec-1.3.jar",     //
            //      "commons-el-1.0.jar",
            // //   "commons-io-1.4.jar",
            //    "aws-java-sdk-1.0.005.jar",    // leave off
            "mysql-connector-java-5.0.4.jar",
            "junit-4.4.jar",
            "aws-java-sdk-1.0.12.jar",
            //     "commons-httpclient-3.1.jar",
            //    "commons-codec-1.3.jar",
            "jackson-core-asl-1.6.1.jar",
            "stax-api-1.0.1.jar",
            "jetty-6.1.25.jar",
            "jetty-util-6.1.25.jar",
            //   "servlet-api-2.5-20081211.jar",
            "servlet-api-2.5.jar",
            "log4j-1.2.9.jar",
            "junit-4.8.1.jar",
            "openjpa-persistence-2.0.0.jar",
            "openjpa-kernel-2.0.0.jar",
            "openjpa-lib-2.0.0.jar",
            //      "commons-lang-2.1.jar",
            //      "commons-collections-3.2.1.jar",
            "serp-1.13.1.jar",
            "geronimo-jms_1.1_spec-1.1.1.jar",
            "geronimo-jta_1.1_spec-1.1.1.jar",
            "commons-pool-1.5.3.jar",
            "geronimo-jpa_2.0_spec-1.0.jar",
            "jackson-core-asl-1.7.4.jar",
            //   "xml-apis-1.0.b2.jar",
            //    "xml-apis-ext-1.3.04.jar",
            "tools.jar",
            "gragent.jar"
    };

    @SuppressWarnings("unchecked")
    public static final Set<String> EXCLUDED_JARS = new HashSet(Arrays.asList(EXCLUDED_JARS_LIST));

    @SuppressWarnings("UnusedDeclaration")
    public static void addExcludedLibrary(String lib) {
        EXCLUDED_JARS.add(lib);
    }

    public static final String HADOOP_HOME = "E:\\ThirdParty\\hadoop-0.21.0";

    @SuppressWarnings("UnusedDeclaration")
    private int gJarNumber = 0;
    @SuppressWarnings("UnusedDeclaration")
    private static boolean isQuiet;

    @SuppressWarnings("FieldCanBeLocal")
    private static Properties gExcludedProperty;

    public static File[] filterClassPath(String[] pathItems, String javaHome) {
        readExcludedProperties();

        List<File> holder = new ArrayList<File>();
        List<String> pathholder = new ArrayList<String>();
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < pathItems.length; i++) {
            String item = pathItems[i];
            String jarName = new File(item).getName();
            if (".".equals(item))
                continue;
            if (EXCLUDED_JARS.contains(item))
                continue;
            if (EXCLUDED_JARS.contains(jarName))
                continue;
            if (item.contains(javaHome))
                continue;
            File itemFile = new File(item);
            if (!itemFile.exists())
                continue;
            if (itemFile.isFile()) {
                pathholder.add(item);
            }
            //   pathholder.add(item);
        }

        pathItems = pathholder.toArray(new String[pathholder.size()]);

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < pathItems.length; i++) {
            String item = pathItems[i];
            File itemFile = new File(item);
            String jarName = itemFile.getName();
            if (".".equals(item))
                continue;
            if (inExcludedJars(jarName))
                continue;
            if (item.contains(javaHome))
                continue;
            if (jarName.startsWith("hadoop"))
                jarName = itemFile.getName();

            if (HADOOP_HOME.contains(javaHome))
                continue;

            if (applyRulesToExclude(item, jarName))
                continue;

            if (!itemFile.exists())
                continue;
            if (itemFile.isFile()) {
                holder.add(itemFile);
                continue;
            }
            if (itemFile.isDirectory()) {
                //noinspection UnnecessaryContinue
                continue;
            }

        }
        File[] ret = new File[holder.size()];
        holder.toArray(ret);
        return ret;
    }

    /**
     * get rid of a lot of libraries we don't need
     *
     * @param item
     * @param jarName
     * @return
     */
    protected static boolean applyRulesToExclude(String item, String jarName) {
        if (EXCLUDED_JARS.contains(item))
            return true;  // exclude
        if (EXCLUDED_JARS.contains(jarName))
            return true;  // exclude
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < EXCLUDED_JARS_PREFIXES.length; i++) {
            String testPrefix = EXCLUDED_JARS_PREFIXES[i];
            if (item.startsWith(testPrefix))
                return true;  // exclude
            if (jarName.startsWith(testPrefix))
                return true;  // exclude

        }
        return false; // OK
    }

    private static void readExcludedProperties() {
        try {
            gExcludedProperty = new Properties();
            InputStream resourceAsStream = HadoopDeployer.class.getResourceAsStream("ExcludedLibraries.properties");
            if (resourceAsStream != null) {
                gExcludedProperty.load(resourceAsStream);
                String prop = gExcludedProperty.getProperty("ExcludedLibraries");
                String[] props = prop.split(",");
                //noinspection ForLoopReplaceableByForEach
                Collections.addAll(EXCLUDED_JARS, props);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String GLOBAL_DIR = "E:\\ThirdParty";

    public static File[] filterClassPathDirectories(String[] pathItems, String javaHome) {
        List<File> holder = new ArrayList<File> ();
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < pathItems.length; i++) {
            String item = pathItems[i];
            if (".".equals(item))
                continue;
            if (GLOBAL_DIR.equals(item))
                continue;
            if (EXCLUDED_JARS.contains(item))
                continue;
            if (item.contains(javaHome))
                continue;
            File itemFile = new File(item);
            if (!itemFile.exists())
                continue;
            if (itemFile.isFile()) {
                continue;
            }
            if (itemFile.isDirectory())
                holder.add(itemFile);
        }


        File[] ret = new File[holder.size()];
        holder.toArray(ret);
        return ret;
    }

    protected static boolean inExcludedJars(String s) {
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < EXCLUDED_JARS_LIST.length; i++) {
            String test = EXCLUDED_JARS_LIST[i];
            if (s.endsWith(test))
                return true;
        }
        return false;
    }

    public static String pathToJarName(File itemFile) {
        String test = itemFile.getName();
        if ("classes".equalsIgnoreCase(test)) {
            test = itemFile.getParentFile().getName();
        }
        return test + ".jar";
    }

    @SuppressWarnings("UnusedDeclaration")
    public static File makeJar(File libDir, File itemFile) {
        String jarName = pathToJarName(itemFile);
        File jarFile = new File(libDir, jarName);
        String cmd = "jar -cvf " + jarFile.getAbsolutePath() + " -C " + itemFile.getAbsolutePath() + " .";
        System.out.println(cmd);
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jarFile;
    }

    public static void copyLibraries(ZipOutputStream out, File[] libs) throws IOException {
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < libs.length; i++) {
            File lib = libs[i];
            final String name = "lib/" + lib.getName();
            System.out.println(name);
            ZipEntry ze = new ZipEntry(name);
            out.putNextEntry(ze);
            copyFile(lib, out);
            out.closeEntry();
        }
    }

    /**
     * { method
     *
     * @param dst destination file name
     * @param src source file name
     * @return true for success
     *         }
     * @name copyFile
     * @function copy file named src into new file named dst
     */
    public static boolean copyFile(File src, ZipOutputStream dst) {
        int bufsize = 1024;
        try {
            RandomAccessFile srcFile = new RandomAccessFile(src, "r");
            long len = srcFile.length();
            if (len > 0x7fffffff) {
                return (false);
            }
            // too large
            int l = (int) len;
            if (l == 0) {
                return (false);
            }
            // failure - no data

            int bytesRead;
            byte[] buffer = new byte[bufsize];
            while ((bytesRead = srcFile.read(buffer, 0, bufsize)) != -1) {
                dst.write(buffer, 0, bytesRead);
            }
            srcFile.close();
            return true;
        } catch (IOException ex) {
            return (false);
        }
    }


    /**
     * { method
     *
     * @param TheFile name of file to create
     * @param data    date to write
     * @return true = success
     *         }
     * @name writeFile
     * @function write the string data to the file Filename
     */
    @SuppressWarnings("UnusedDeclaration")
    public static boolean writeFile(File TheFile, String data) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(TheFile));
            //noinspection ConstantConditions
            if (out != null) {
                out.print(data);
                out.close();
                return (true);
            }
            return (false);
            // failure
        } catch (IOException ex) {
            return (false); // browser disallows
        } catch (SecurityException ex) {
            return (false); // browser disallows
        }
    }


    public static void deployLibrariesToJar(File deployDir) {
        try {
            File parentFile = deployDir.getParentFile();
            if (parentFile != null)
                //noinspection ResultOfMethodCallIgnored
                parentFile.mkdirs();

            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(deployDir));
            Set<String> existing = new HashSet<String>();

            String javaHome = System.getProperty("java.home");
            String classpath = System.getProperty("java.class.path");
            String[] pathItems;
            if (classpath.contains(";")) {
                pathItems = classpath.split(";");
            } else {
                if (classpath.contains(":")) {
                    pathItems = classpath.split(":");   // Linux stlye
                } else {
                    //noinspection UnnecessaryLocalVariable
                    String[] items = {classpath};
                    pathItems = items; // only 1 I guess
                }
            }
            File[] pathLibs = filterClassPath(pathItems, javaHome);
            copyLibraries(out, pathLibs);
            File[] pathDirectories = filterClassPathDirectories(pathItems, javaHome);
            //noinspection ForLoopReplaceableByForEach
            for (int i = 0; i < pathDirectories.length; i++) {
                File pathDirectory = pathDirectories[i];
                copyLibraryDirectory("", pathDirectory, out,existing);
            }
            out.flush();
            out.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static void deployClassesToJar(File deployDir, String... loadedPackages) {
        try {
            File parentFile = deployDir.getParentFile();
            if (parentFile != null)
                //noinspection ResultOfMethodCallIgnored
                parentFile.mkdirs();

            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(deployDir));

            String javaHome = System.getProperty("java.home");
            String classpath = System.getProperty("java.class.path");
            String[] pathItems;
            if (classpath.contains(";")) {
                pathItems = classpath.split(";");
            } else {
                if (classpath.contains(":")) {
                    pathItems = classpath.split(":");   // Linux stlye
                } else {
                    //noinspection UnnecessaryLocalVariable
                    String[] items = {classpath};
                    pathItems = items; // only 1 I guess
                }
            }
            //noinspection UnusedDeclaration,MismatchedReadAndWriteOfArray
            File[] pathLibs = filterClassPath(pathItems, javaHome);
            Set<String> holder = new HashSet<String>();
            //noinspection ForLoopReplaceableByForEach
            for (int i = 0; i < loadedPackages.length; i++) {
                String replace = loadedPackages[i].replace(".", "/");
                holder.add(replace);
          }

            //           ignore libraries
//            copyLibraries(out, pathLibs);
            File[] pathDirectories = filterClassPathDirectories(pathItems, javaHome);
            //noinspection ForLoopReplaceableByForEach
            for (int i = 0; i < pathDirectories.length; i++) {
                File pathDirectory = pathDirectories[i];
                copySpecifiesLibraryDirectory("", pathDirectory, out, holder);
            }
            out.flush();
            out.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static String nextPath(String s, String name) {
        if (s == null || s.length() == 0)
            return name;
        return s + "/" + name;
    }

    private static void copyLibraryDirectory(final String s, final File dir, final ZipOutputStream pOut,Set<String> existing) throws IOException {
        File[] list = dir.listFiles();
        if (list == null) return;
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < list.length; i++) {
            File file = list[i];
            if (file.isDirectory()) {
                final String np = nextPath(s, file.getName());
                copyLibraryDirectory(np, file, pOut,existing);
            } else {
                final String np = nextPath(s, file.getName());
                // prevent duplicates
                if (!existing.contains(np)) {
                    ZipEntry ze = new ZipEntry(np);
                    pOut.putNextEntry(ze);
                    copyFile(file, pOut);
                    pOut.closeEntry();
                    existing.add(np);
                }
            }
        }
    }


    private static void copySpecifiesLibraryDirectory(final String s, final File dir, final ZipOutputStream pOut, Set<String> paths) throws IOException {
        File[] list = dir.listFiles();
        if (list == null) return;
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < list.length; i++) {
            File file = list[i];
            if (file.isDirectory()) {
                final String np = nextPath(s, file.getName());
                copySpecifiesLibraryDirectory(np, file, pOut, paths);
            } else {
                if (!paths.contains(s))
                    continue;
                final String np = nextPath(s, file.getName());
                ZipEntry ze = new ZipEntry(np);
                pOut.putNextEntry(ze);
                copyFile(file, pOut);
                pOut.closeEntry();
            }
        }
    }


    public static File makeHadoopJar(final String pJarName) {
        File deployDir = new File(pJarName);
        deployLibrariesToJar(deployDir);
        return deployDir;
    }

    /**
     * make a small jar for debugging
     *
     * @param pJarName
     */
    public static File makeClassOnlyHadoopJar(final String pJarName, String... loadedClasses) {
        File deployDir = new File(pJarName);
        deployClassesToJar(deployDir, loadedClasses);
        return deployDir;
    }

    public static void main(String[] args) {
        String jarName = "FooBar.jar";
        if (args.length > 0)
            jarName = args[0];
        makeHadoopJar(jarName);

    }
}