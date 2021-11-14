/**
 * Author: Sina Darian 
 * Created at: 03.09.2020
 */

package de.darian.steps

import de.darian.core.Jenkins

import java.nio.charset.StandardCharsets


class CommonSteps implements Serializable {

    private Jenkins jenkins

    /**
     *
     * @param jenkins
     */
    CommonSteps(Jenkins jenkins) {
        this.jenkins = jenkins
    }

    /**
     * Sets the <code>currentBuild.displayName</code> to a detailed version of the project. E.g.: <br/><br/>
     *
     * <i>#78__2.9.0-SNAPSHOT</i>
     */
    void setCurrentBuildDisplayNameToProjectVersion() {
        MavenSteps mvn = new MavenSteps(this.jenkins)
        this.jenkins.setCurrentBuildDisplayName(
                String.format("#%s__%s", this.jenkins.getCurrentBuildNumber(), mvn.getProjectVersion()))
    }

}
