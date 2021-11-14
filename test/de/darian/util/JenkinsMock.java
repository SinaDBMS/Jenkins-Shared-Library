/**
 * Author: Sina Darian 
 * Created at: 02.09.2020
 */

package de.darian.util;

import de.darian.core.Jenkins;

import java.util.List;

/**
 * This class is not intended to be implemented at all. It is a Java (and not groovy) class in order to be mocked out
 * and passed to groovy.
 */
public class JenkinsMock implements Jenkins {
    /**
     * Runs a Bourne shell script on a linux node.
     *
     * @param command
     * @param returnStdout
     * @param label
     * @return
     */
    @Override public String sh(String command, boolean returnStdout, String label) {
        return null;
    }

    /**
     * Prints a given text in Jenkins
     *
     * @param text
     */
    @Override public void echo(String text) {

    }

    /**
     * Gets the value of the parameterName
     *
     * @param parameterName The name of the parameter
     * @return
     */
    @Override public String getParameter(String parameterName) {
        return null;
    }

    /**
     * Equivalent to:
     * <br/>
     * script {<br/>
     * echo currentBuild.displayName<br/>
     * }<br/>
     *
     * @return displayName
     */
    @Override public String getCurrentBuildDisplayName() {
        return null;
    }

    /**
     * Sets Display name. Equivalent to:
     * <br/>
     * script {<br/>
     * currentBuild.displayName = "To be displayed name"<br/>
     * }<br/>
     *
     * @param displayName
     */
    @Override public void setCurrentBuildDisplayName(String displayName) {

    }

    /**
     * Equivalent to:
     * <br/>
     * script {<br/>
     * echo currentBuild.description<br/>
     * }<br/>
     *
     * @return description
     */
    @Override public String getCurrentBuildDescription() {
        return null;
    }

    /**
     * Sets description. Equivalent to:
     * <br/>
     * script {<br/>
     * currentBuild.description = "To be displayed description"<br/>
     * }<br/>
     *
     * @param description
     */
    @Override public void setCurrentBuildDescription(String description) {

    }

    /**
     * Gets the current build number. Equivalent to: <br/><br/>
     *
     * <code>currentBuild.number</code>
     *
     * @return current build number
     */
    @Override public String getCurrentBuildNumber() {
        return null;
    }

    /**
     * Gets the Job name of the current build.
     *
     * <code>env.JOB_NAME</code>
     *
     * @return
     */
    @Override public String getCurrentBuildsJobName() {
        return null;
    }

    /**
     * Gets the Url of the current build's Job.
     *
     * <code>env.JOB_URL</code>
     *
     * @return
     */
    @Override public String getCurrentBuildsJobUrl() {
        return null;
    }

    /**
     * Gets the full project name of the current build.
     *
     * <code>currentBuild.fullProjectName</code>
     *
     * @return
     */
    @Override public String getCurrentBuildFullProjectName() {
        return null;
    }

    /**
     * Gets the url of the current build.
     *
     * <code>env.BUILD_URL</code>
     *
     * @return
     */
    @Override public String getCurrentBuildsUrl() {
        return null;
    }

    /**
     * Gets the project name of the current build
     *
     * <code>currentBuild.projectName</code>
     *
     * @return
     */
    @Override public String getCurrentBuildProjectName() {
        return null;
    }

    /**
     * Gets the base Url of the Jenkins
     *
     * <code>env.JENKINS_URL</code>
     *
     * @return
     */
    @Override public String getJenkinsBaseUrl() {
        return null;
    }

    /**
     * Gets changeSets of the current build.
     *
     * @return
     */
    @Override public Object getCurrentBuildChangeSets() {
        return null;
    }

    /**
     * Gets the build status of the current build. Possible returned values are: SUCCESS, UNSTABLE, FAILURE etc.
     *
     * @return
     */
    @Override public String getCurrentBuildStatus() {
        return null;
    }

    /**
     * Gets a list of cause objects of the current build.
     *
     * @return
     */
    @Override public List<Object> getBuildCauses() {
        return null;
    }
}
