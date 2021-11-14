/**
 * Author: Sina Darian 
 * Created at: 26.08.2020
 */

package de.darian.core
/**
 * A Class which performs jenkins basic steps like sh or echo. It is used to hide away Jenkins API and provide a
 * means to be able to mock Jenkins API.
 */

class JenkinsEngine implements Jenkins {

    private def script

    /**
     *
     * @param script
     */
    JenkinsEngine(def script) {
        this.script = script
    }

    /**
     * Runs a Bourne shell script on a linux node.
     *
     * @param command
     * @param returnStdout
     * @param label
     * @return
     */
    @Override
    String sh(String command, boolean returnStdout = false, String label = "") {
        String stdout = this.script.sh(script: command, returnStdout: returnStdout, label: label)
        if (returnStdout) {
            return stdout.trim()
        } else {
            return stdout
        }
    }

    /**
     * Prints a given text in Jenkins
     * @param text
     */
    @Override
    void echo(String text) {
        this.script.echo(text)
    }

    /**
     *  Gets the value of the parameterName
     * @param parameterName The name of the parameter
     * @return
     */
    @Override
    String getParameter(String parameterName) {
        return this.script.params.getOrDefault(parameterName, null)
    }

    /**
     * Equivalent to:
     * <br/>
     * script {<br/>
     *     echo currentBuild.displayName<br/>
     *}<br/>
     *
     * @return displayName
     */
    @Override
    String getCurrentBuildDisplayName() {
        return this.script.currentBuild.displayName
    }

    /**
     * Sets Display name. Equivalent to:
     * <br/>
     * script {<br/>
     *      currentBuild.displayName = "To be displayed name"<br/>
     *}<br/>
     *
     * @param displayName
     */
    @Override
    void setCurrentBuildDisplayName(String displayName) {
        this.script.currentBuild.displayName = displayName
    }

    /**
     * Equivalent to:
     * <br/>
     * script {<br/>
     *     echo currentBuild.description<br/>
     *}<br/>
     *
     * @return description
     */
    @Override
    String getCurrentBuildDescription() {
        return this.script.currentBuild.description
    }

    /**
     * Sets description. Equivalent to:
     * <br/>
     * script {<br/>
     *      currentBuild.description = "To be displayed description"<br/>
     *}<br/>
     *
     * @param description
     */
    @Override
    void setCurrentBuildDescription(String description) {
        this.script.currentBuild.description = description
    }

    /**
     * Gets the current build number. Equivalent to: <br/><br/>
     *
     * <code>currentBuild.BUILD_NUMBER</code>
     * @return current build number
     */
    @Override
    String getCurrentBuildNumber() {
        this.script.currentBuild.number
    }

    /**
     * Gets the Job name of the current build.
     *
     * <code>env.JOB_NAME</code>
     * @return
     */
    @Override
    String getCurrentBuildsJobName() {
        return this.script.env.JOB_NAME
    }

    /**
     * Gets the Url of the current build's Job.
     *
     * <code>env.JOB_URL</code>
     * @return
     */
    @Override
    String getCurrentBuildsJobUrl() {
        return this.script.env.JOB_URL
    }
    /**
     * Gets the full project name of the current build.
     *
     * <code>currentBuild.fullProjectName</code>
     * @return
     */

    @Override
    String getCurrentBuildFullProjectName() {
        return this.script.currentBuild.fullProjectName
    }

    /**
     * Gets the url of the current build.
     *
     * <code>env.BUILD_URL</code>
     * @return
     */
    @Override
    String getCurrentBuildsUrl() {
        return this.script.env.BUILD_URL
    }

    /**
     * Gets the project name of the current build
     *
     * <code>currentBuild.projectName</code>
     * @return
     */

    @Override
    String getCurrentBuildProjectName() {
        return this.script.currentBuild.projectName
    }
    /**
     * Gets the base Url of the Jenkins
     *
     * <code>env.JENKINS_URL</code>
     * @return
     */
    @Override
    String getJenkinsBaseUrl() {
        return this.script.env.JENKINS_URL
    }

    /**
     * Gets changeSets of the current build.
     *
     * @return
     */
    @Override
    Object getCurrentBuildChangeSets() {
        return this.script.currentBuild.changeSets
    }

    /**
     * Gets the build status of the current build. Possible returned values are: SUCCESS, UNSTABLE, FAILURE etc.
     * @return
     */
    @Override
    String getCurrentBuildStatus() {
        return this.script.currentBuild.currentResult
    }

    /**
     * Gets a list of cause objects of the current build.
     * @return
     */
    @Override
    List<Object> getBuildCauses() {
        return this.script.currentBuild.buildCauses
    }
}
