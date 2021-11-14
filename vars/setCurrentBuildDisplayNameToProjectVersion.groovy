import de.darian.steps.CommonSteps
import de.darian.core.JenkinsEngine

/**
 * Author: Sina Darian 
 * Created at: 03.09.2020
 */


/**
 * Sets the current build info. This method has two boolean parameters:<br/><br/>
 *
 *
 * @param config <code>setDisplayName</code>, <code>setDescription</code>
 * @return
 */
def call() {
    CommonSteps commonSteps = new CommonSteps(new JenkinsEngine(this))
    commonSteps.setCurrentBuildDisplayNameToProjectVersion()
}
 

