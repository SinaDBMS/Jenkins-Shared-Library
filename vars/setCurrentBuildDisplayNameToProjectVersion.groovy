import de.darian.steps.CommonSteps
import de.darian.core.JenkinsEngine

/**
 * Author: Sina Darian 
 * Created at: 03.09.2020
 */


/**
 * Sets the current build info in jenkins classic view to the project version specified in pom.xml
 *
 *
 * @param config <code>setDisplayName</code>, <code>setDescription</code>
 * @return
 */
def call() {
    CommonSteps commonSteps = new CommonSteps(new JenkinsEngine(this))
    commonSteps.setCurrentBuildDisplayNameToProjectVersion()
}
 

