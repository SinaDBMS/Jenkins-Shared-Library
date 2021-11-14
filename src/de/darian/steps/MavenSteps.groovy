/**
 * Author: Sina Darian 
 * Created at: 26.08.2020
 */

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
     * Performs integration test.
     *
     *
     *
     * @param parameters A list of comma separated parameters with their respective values. E.g.:
     *
     * <ul>
     *      <br><li><code>"some.parameter"=value1, "some.other.parameter"=value2</code></li>
     * </ul>
     * It is obvious that in a String the double quotations need to be escaped.
     */
    void performMavenIntegrationTest(String parameters = "") {
        String DFlag = (parameters == null || parameters.empty) ? "" : String.format(" -D%s", parameters)
        String command = String.format("mvn org.apache.maven.plugins:maven-failsafe-plugin:integration-test" +
                "%s org.apache.maven.plugins:maven-failsafe-plugin:verify", DFlag)
        this.jenkins.sh(command, false, "Integration test")
    }

    void performMavenCleanInstall() {
        this.jenkins.sh("mvn clean install", false, "Maven Clean & Install")
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

}
