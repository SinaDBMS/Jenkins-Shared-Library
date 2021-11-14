/**
 * Author: Sina Darian 
 * Created at: 02.09.2020
 */

import de.darian.steps.MavenSteps
import de.darian.core.JenkinsEngine

def call() {
    MavenSteps mvn = new MavenSteps(new JenkinsEngine(this))
    mvn.performMavenIntegrationTest("\"skip.ITs\"=false")
}

 

