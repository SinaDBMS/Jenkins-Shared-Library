/**
 * Author: Sina Darian 
 * Created at: 01.09.2020
 */

package de.darian.steps;


import static org.mockito.ArgumentMatchers.*;

import de.darian.util.JenkinsMock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class MavenStepsTest {

    @Mock
    private JenkinsMock jenkins;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(jenkins);
    }

    @ParameterizedTest
    @ValueSource(strings = {"\"skip.ITs\"=true"})
    public void testPerformMavenIntegrationTestWithParameter(String parameter) {
        // Arrange
        MavenSteps mvn = new MavenSteps(this.jenkins);
        ArgumentCaptor<String> command = ArgumentCaptor.forClass(String.class);

        // Act
        mvn.performMavenIntegrationTest(parameter);

        // Assert
        Mockito.verify(this.jenkins).sh(command.capture(), anyBoolean(), anyString());
        Assertions.assertTrue(command.getValue().matches(String.format("^.*test -D%s org.*$", parameter)));
    }

    @ParameterizedTest
    @MethodSource("parameterGenerator")
    public void testPerformMavenIntegrationTestWithNullAndEmptyParameter(String parameter) {
        // Arrange
        MavenSteps mvn = new MavenSteps(this.jenkins);
        ArgumentCaptor<String> command = ArgumentCaptor.forClass(String.class);

        // Act
        mvn.performMavenIntegrationTest(parameter);

        // Assert
        Mockito.verify(this.jenkins).sh(command.capture(), anyBoolean(), anyString());
        Assertions.assertTrue(command.getValue().matches("^.*(-D.*){0}.*$"));
    }

    private static Stream<String> parameterGenerator() {
        return Stream.of("", null);
    }
}
