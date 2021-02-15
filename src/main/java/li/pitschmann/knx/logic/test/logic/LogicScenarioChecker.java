package li.pitschmann.knx.logic.test.logic;

import li.pitschmann.knx.core.utils.Preconditions;
import li.pitschmann.knx.logic.components.LogicComponent;
import li.pitschmann.knx.logic.connector.Connector;
import li.pitschmann.knx.logic.connector.DynamicConnector;
import li.pitschmann.knx.logic.connector.StaticConnector;

import java.util.Map;
import java.util.Objects;

import static li.pitschmann.knx.logic.test.KnxLogicAssertions.assertThat;

class LogicScenarioChecker {
    private final Map<String, ConnectorProperties> expectedInputConnectors;
    private final Map<String, ConnectorProperties> expectedOutputConnectors;
    private final LogicComponent logicComponent;

    /**
     * Creates a new checker
     *
     * @param context the logic scenario context to be used for instantiation and verification; may not be null
     */
    LogicScenarioChecker(final LogicScenarioContext context) {
        logicComponent = Objects.requireNonNull(context.logicComponent);
        expectedInputConnectors = Map.copyOf(context.inputConnectors);
        expectedOutputConnectors = Map.copyOf(context.outputConnectors);
    }

    /**
     * Assert {@code actualConnector} if it contains the same data like the {@code expectedConnector}
     *
     * @param expectedConnector expected connector - pre-defined test data
     * @param actualConnector   actual connector - taken from component
     */
    private static void assertConnector(final ConnectorProperties expectedConnector, final Connector actualConnector) {
        // same connector type?
        assertThat(actualConnector).isExactlyInstanceOf(expectedConnector.getConnectorClass());

        // static connector
        if (actualConnector instanceof StaticConnector) {
            final var staticConnector = (StaticConnector) actualConnector;
            final var expectedValues = expectedConnector.getValues();
            assertThat(expectedValues).hasSize(1);
            assertThat(staticConnector.getPin().getValue()).isEqualTo(expectedValues[0]);
        }
        // dynamic connector
        else if (actualConnector instanceof DynamicConnector) {
            final var dynamicConnector = (DynamicConnector) actualConnector;
            final var actualFields = dynamicConnector.getPins();
            final var expectedValues = expectedConnector.getValues();
            assertThat(expectedValues).hasSameSizeAs(actualFields);

            for (var i = 0; i < actualFields.size(); i++) {
                final var actualField = actualFields.get(i);
                assertThat(actualField.getIndex()).isEqualTo(i);
                assertThat(actualField.getValue()).isEqualTo(expectedValues[i]);
            }
        } else {
            throw new AssertionError("Unexpected connector type found!");
        }
    }

    /**
     * Returns the expected {@link ConnectorProperties} by actual connector
     *
     * @param connectors      map of connectors as source for look up; cannot be null
     * @param actualConnector the actual connector; cannot be null
     * @return input {@link ConnectorProperties}
     * @throws AssertionError if no suitable {@link ConnectorProperties} could be found
     */
    private static ConnectorProperties getExpectedConnector(final Map<String, ConnectorProperties> connectors, final Connector actualConnector) {
        final var fieldName = actualConnector.getDescriptor().getName();
        final var data = connectors.get(fieldName);
        if (data == null) {
            throw new AssertionError("No connector found with name: " + fieldName);
        }

        return data;
    }

    /**
     * Verifies the current {@link LogicComponent} with given test data that
     * initializes the logic component with input values, executes the logic
     * and then asserts the output values.
     */
    void verify() {
        // assert same size of connectors
        assertThat(logicComponent)
                .numberOfConnectors(expectedInputConnectors.size() + expectedOutputConnectors.size())
                .numberOfInputConnectors(expectedInputConnectors.size())
                .numberOfOutputConnectors(expectedOutputConnectors.size());

        // Check if all expected input/output connectors are available and are in same instance type
        logicComponent.getInputConnectors().forEach(c ->
                assertThat(c).isExactlyInstanceOf(getExpectedConnector(expectedInputConnectors, c).getConnectorClass())
        );
        logicComponent.getOutputConnectors().forEach(c ->
                assertThat(c).isExactlyInstanceOf(getExpectedConnector(expectedOutputConnectors, c).getConnectorClass())
        );

        //
        // INPUT (SET INPUT TEST DATA)
        // ---------------------------
        for (final var actualConnector : logicComponent.getInputConnectors()) {
            final var expectedConnector = getExpectedConnector(expectedInputConnectors, actualConnector);

            if (actualConnector instanceof StaticConnector) {
                final var staticConnector = (StaticConnector) actualConnector;
                final var expectedValues = expectedConnector.getValues();

                Preconditions.checkArgument(expectedValues.length == 1,
                        "Test Data Setup issue detected. " +
                                "For static connector a single value must be present.");
                staticConnector.getPin().setValue(expectedValues[0]);
            } else if (actualConnector instanceof DynamicConnector) {
                final var dynamicConnector = (DynamicConnector) actualConnector;
                final var expectedValues = expectedConnector.getValues();

                // try to increase connector if required
                dynamicConnector.tryIncrease(expectedValues.length);
                assertThat(dynamicConnector.getPins()).hasSize(expectedValues.length);

                final var actualFields = dynamicConnector.getPins();
                Preconditions.checkArgument(expectedValues.length == actualFields.size(),
                        "Test Data Setup issue detected. " +
                                "For dynamic connector the size must be same: " +
                                "actual={}, expected={}", actualFields.size(), expectedValues.length);
                assertThat(expectedValues).hasSameSizeAs(actualFields);
                for (int j = 0; j < expectedValues.length; j++) {
                    dynamicConnector.getPin(j).setValue(expectedValues[j]);
                }
            } else {
                throw new AssertionError("Unexpected connector type found!");
            }
        }

        //
        // OUTPUT (SET OUTPUT SIZE FOR DYNAMIC OUTPUT CONNECTORS)
        //
        for (final var actualConnector : logicComponent.getOutputConnectors()) {
            if (actualConnector instanceof DynamicConnector) {
                final var dynamicConnector = (DynamicConnector) actualConnector;

                final var expectedConnector = getExpectedConnector(expectedOutputConnectors, actualConnector);
                final var expectedValuesSize = expectedConnector.getValues().length;

                // try to increase connector if required
                dynamicConnector.tryIncrease(expectedValuesSize);
                assertThat(dynamicConnector.getPins()).hasSize(expectedValuesSize);
            }
        }

        //
        // INPUT (VERIFICATION)
        // ----------------------
        for (final var actualInputConnector : logicComponent.getInputConnectors()) {
            final var expectedInputConnector = getExpectedConnector(expectedInputConnectors, actualInputConnector);
            assertConnector(expectedInputConnector, actualInputConnector);
        }

        //
        // EXECUTE LOGIC
        // ----------------------
        logicComponent.execute();

        //
        // OUTPUT (VERIFICATION)
        // ----------------------
        for (final var actualOutputConnector : logicComponent.getOutputConnectors()) {
            final var expectedOutputConnector = getExpectedConnector(expectedOutputConnectors, actualOutputConnector);
            assertConnector(expectedOutputConnector, actualOutputConnector);
        }
    }
}
