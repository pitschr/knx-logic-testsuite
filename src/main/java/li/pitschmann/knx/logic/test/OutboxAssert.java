package li.pitschmann.knx.logic.test;

import li.pitschmann.knx.logic.components.OutboxComponent;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AssertJ for {@link OutboxComponent}
 */
public final class OutboxAssert extends AbstractComponentAssert<OutboxAssert, OutboxComponent> {
    OutboxAssert(final OutboxComponent actual) {
        super(actual, OutboxAssert.class);
    }

    /**
     * Asserts the number of input pins
     *
     * @param expected expected number of input pins
     * @return myself
     */
    public OutboxAssert numberOfInputPins(final int expected) {
        assertThat(this.actual.getInputPins()).hasSize(expected);
        return myself;
    }

    /**
     * Asserts the number of input connectors
     *
     * @param expected expected number of input connectors
     * @return myself
     */
    public OutboxAssert numberOfInputConnectors(final int expected) {
        assertThat(this.actual.getInputConnectors()).hasSize(expected);
        return myself;
    }

    /**
     * Asserts the value of static input connector with name {@code fieldName}
     *
     * @param expectedValue the expected value of static input connector
     * @return myself
     */
    public OutboxAssert input(final String fieldName,
                              final Object expectedValue) {
        return super.value(this.actual.getInputConnector(fieldName), expectedValue);
    }

    /**
     * Asserts all values of dynamic input connector with {@code fieldName}
     *
     * @param expectedValues all expected values of dynamic input connector
     * @return myself
     */
    public OutboxAssert inputs(final String fieldName,
                               final Object... expectedValues) {
        return super.values(this.actual.getInputConnector(fieldName), expectedValues);
    }
}
