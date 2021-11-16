# Introduction

Due to the Microkernel architecture of Jenkins, it’s essential capabalities are realized by  means of installing
plugins. However in some organizations, firewalls make it nearly impossible  to download the plugins and to keeping them
up-to-date. Other than that, not for every  requirement there is a plugin ready to install. Jenkins suggests shared
libraries to implement user defined functionalities. Unfortunately to test the code, one has to check in the code to VCS
and run the pipeline to see whether the code works or not. This project helps you to perform unit tests using Mockito
on the Groovy Code and to make sure that the implemented functionality works fine before checking anything into VCS.

# Running the project locally

After downloading/cloning the project on your local machine, run the command `mvn test` in your terminal.
you should see there that maven successfully compiles both Java and Groovy files and performs the unit tests
followed by a _BUILD SUCCESS_ status. Of course, you need to have _Maven_ and _Java_ installed on your machine.

# Setting up Project in Jenkins

In order to be able to use the implemented functions in _vars_ directory, add the library to your Jenkins server in 
_Manage Jenkins -> Configure System_ under _Global Pipeline Libraries_:

![Alt text](./Jenkins-shared-library%20installation%20on%20Jenkins.png?raw=true "Project structure")

# Project structure

The directory structure of this project is as follows:

## var
The vars directory hosts Groovy script files that are exposed as a variable in Pipelines. The scripts in this
directory should act as a layer between the pipeline and the business logic in _src_ directory. Keep the scripts
here as simple as possible and don't put any business logic here. Otherwise, you loose the benefit of unit testing,
which is the sole purpose of this project. Here's an example of a Groovy script that performs a <code>mvn clean install</code>:

```
import de.darian.steps.MavenSteps
import de.darian.core.JenkinsEngine

def call() {
    MavenSteps mvn = new MavenSteps(new JenkinsEngine(this))
    mvn.performMavenCleanInstall()
}
```
In the body of _call_ function an instance of _MavenSteps_ is created. MavenSteps constructor requieres an Instance of the
_Jenkins_ interface, so that it could delegate the execution of maven commands to a Jenkins node. Here we use the
_JenkinsEngine_ implementation of  _Jenkins_ interface, which would be executed on a Jenkins node. Note that we should pass
a reference of the script to the _JenkinsEngine_ using _this_, so that it could call the relevant Jenkins APIs. Finally 
we call the _performMavenCleanInstall_ method of the MavenSteps to build the maven project in the pipeline.  

The call method can also accept arguments from the calling pipeline as follows:

```
def call(Map config) {
    String parameter_1 = config.parameter1 as String
    int parameter_2 = config.parameter2 as int
    
    //...
}
```

## src
This directory is similar to Java source directory structure that hosts classes and packages. But the source code
in here is written in Groovy and __NOT__ in Java. You should put the business logic of your code in this directory.
This has two advantages:

* Your Code would become highly structured; You leverage from packages and classes and in turn Object Oriented Programming.
* You could easily perform unit tests using Mockito in Java.

_Src_ contains two packages: _core_ and _steps_.

#### core
This package holds _Jenkins_ interface and an implementation of it, _JenkinsEngine_ class. There are already dozens of
functionalities of Jenkins API implemented. Here's a code snippet of JenkinsEngine:

```
package de.darian.core

class JenkinsEngine implements Jenkins {

    private def script

    /**
     *
     * @param script
     */
    JenkinsEngine(def script) {
        this.script = script
    }
    
    @Override
    void echo(String text) {
        this.script.echo(text)
    }
    
    // Other methods ...
}
```

Note that this class holds a reference to the script, from which this code is being called. Therefore always pass a reference
to the pipeline script as explained above in __vars__ section.

#### steps
In this package you could write classes that encapsulate business logics and rely on _Jenkins_ interface to execute code
on Jenkins nodes. An examples of such a step is _MavenSteps_:

```

package de.darian.steps

import de.darian.core.Jenkins


class MavenSteps implements Serializable {

    private Jenkins jenkins

    /**
     *
     * @param jenkins
     */
    MavenSteps(Jenkins jenkins) {
        this.jenkins = jenkins
    }

    /**
     * returns the version of the project which is specified in pom.xml.
     *
     * @return
     */
    String getProjectVersion() {
        String command = 'mvn -q -Dexec.executable=echo -Dexec.args=\'${project.version}\' --non-recursive exec:exec'
        return this.jenkins.sh(command, true, "Maven version")
    }
    
    // Other methods ...

}
```

The <code>getProjectVersion()</code> method of the MavenSteps retrieves the version of the project specified in _pom.xml_
(e.g. 1.0.0-SNAPSHOT).
As you see, the <code>sh</code> method of Jenkins interface is used to execute the command on a Jenkins node.


## test
Unit tests written in Java. To perform unit tests against your groovy classes in _src_, mock Jenkins using Mockito.

## build-scripts
Contains only the Jenkinsfile of this project. Note that this Pipeline is not an example of a pipeline for this project,
and it is **not necessary** for the project to run.

![Alt text](./Jenkins-shared-library.jpg?raw=true "Project structure")
*Project structure at a glance*
