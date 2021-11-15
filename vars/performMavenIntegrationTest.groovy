/**
 * Author: Sina Darian 
 * Created at: 02.09.2020
 */

import de.darian.steps.MavenSteps
import de.darian.core.JenkinsEngine


/**
 * Performs maven integration test. Equivalent to:
 * <br/>
 * <code>mvn org.apache.maven.plugins:maven-failsafe-plugin:integration-test -Dskip.ITs=false
 * org.apache.maven.plugins:maven-failsafe-plugin:verify</code>
 *
 * @return
 */
def call() {
    MavenSteps mvn = new MavenSteps(new JenkinsEngine(this))
    mvn.performMavenIntegrationTest("\"skip.ITs\"=false")
}

 

