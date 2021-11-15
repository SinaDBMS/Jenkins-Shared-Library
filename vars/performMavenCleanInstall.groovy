/**
 * Author: Sina Darian
 * Created at: 02.09.2020
 */

import de.darian.steps.MavenSteps
import de.darian.core.JenkinsEngine

/**
 * Re-installs the maven project. Equivalent to:
 * <br/>
 * <code>mvn clean install</code>
 * @return
 */
def call() {
    MavenSteps mvn = new MavenSteps(new JenkinsEngine(this))
    mvn.performMavenCleanInstall()
}



