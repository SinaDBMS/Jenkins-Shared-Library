/**
 * Author: Sina Darian
 * Created at: 09.09.2020
 */

package de.darian.steps;

import de.darian.util.JenkinsMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class CommonStepsTest {

    @Mock
    private JenkinsMock jenkins;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(jenkins);
    }

    @Test
    public void testSetCurrentBuildDisplayNameToProjectVersion() {
        // Arrange
        CommonSteps commonSteps = new CommonSteps(this.jenkins);
        ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);
        Random rnd = new Random();
        String projectVersion = String.format("%d.%d.%d-SNAPSHOT", rnd.nextInt(20), rnd.nextInt(20), rnd.nextInt(20));
        int currentBuildNumber = rnd.nextInt(200);
        Mockito.when(this.jenkins.sh("mvn -q -Dexec.executable=echo -Dexec.args='${project.version}' " +
                "--non-recursive exec:exec", true, "Maven version")).thenReturn(projectVersion);
        Mockito.when(this.jenkins.getCurrentBuildNumber()).thenReturn(Integer.toString(currentBuildNumber));

        // Act
        commonSteps.setCurrentBuildDisplayNameToProjectVersion();

        // Assert
        Mockito.verify(this.jenkins).setCurrentBuildDisplayName(arg.capture());
        Assertions.assertEquals(String.format("#%d__%s", currentBuildNumber, projectVersion), arg.getValue());
    }

}
