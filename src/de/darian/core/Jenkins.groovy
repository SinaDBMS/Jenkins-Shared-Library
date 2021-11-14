/**
 * Author: Sina Darian 
 * Created at: 02.09.2020
 */

package de.darian.core

/**
 * A super type for different Jenkins implementations.
 */

interface Jenkins extends Serializable {

    /**
     * Runs a Bourne shell script on a linux node.
     *
     * @param command
     * @param returnStdout
     * @param label
     * @return
     */
    String sh(String command, boolean returnStdout, String label)

    /**
     * Prints a given text in Jenkins
     * @param text
     */
    void echo(String text)

    /**
     *  Gets the value of the parameterName
     * @param parameterName The name of the parameter
     * @return
     */
    String getParameter(String parameterName)

    /**
     * Equivalent to:
     * <br/>
     * script {<br/>
     *     echo currentBuild.displayName<br/>
     *}<br/>
     *
     * @return displayName
     */
    String getCurrentBuildDisplayName()

    /**
     * Sets Display name. Equivalent to:
     * <br/>
     * script {<br/>
     *      currentBuild.displayName = "To be displayed name"<br/>
     *}<br/>
     *
     * @param displayName
     */
    void setCurrentBuildDisplayName(String displayName)

    /**
     * Equivalent to:
     * <br/>
     * script {<br/>
     *     echo currentBuild.description<br/>
     *}<br/>
     *
     * @return description
     */
    String getCurrentBuildDescription()

    /**
     * Sets description. Equivalent to:
     * <br/>
     * script {<br/>
     *      currentBuild.description = "To be displayed description"<br/>
     *}<br/>
     *
     * @param description
     */
    void setCurrentBuildDescription(String description)


    /**
     * Gets the current build number. Equivalent to: <br/><br/>
     *
     * <code>currentBuild.number</code>
     * @return current build number
     */
    String getCurrentBuildNumber()

    /**
     * Gets the Job name of the current build.
     *
     * <code>env.JOB_NAME</code>
     * @return
     */
    String getCurrentBuildsJobName()

    /**
     * Gets the Url of the current build's Job.
     *
     * <code>env.JOB_URL</code>
     * @return
     */
    String getCurrentBuildsJobUrl()

    /**
     * Gets the url of the current build.
     *
     * <code>env.BUILD_URL</code>
     * @return
     */
    String getCurrentBuildsUrl()

    /**
     * Gets the full project name of the current build.
     *
     * <code>currentBuild.fullProjectName</code>
     * @return
     */
    String getCurrentBuildFullProjectName()

    /**
     * Gets the project name of the current build
     *
     * <code>currentBuild.projectName</code>
     * @return
     */
    String getCurrentBuildProjectName()

    /**
     * Gets changeSets of the current build.
     *
     * @return
     */
    Object getCurrentBuildChangeSets()


    /**
     * Gets the base Url of the Jenkins
     *
     * <code>env.JENKINS_URL</code>
     * @return
     */
    String getJenkinsBaseUrl()


    /**
     * Gets the build status of the current build. Possible returned values are: SUCCESS, UNSTABLE, FAILURE etc.
     * @return
     */
    String getCurrentBuildStatus()


    /**
     * Gets a list of cause objects of the current build.
     * @return
     */
    List<Object> getBuildCauses()
}