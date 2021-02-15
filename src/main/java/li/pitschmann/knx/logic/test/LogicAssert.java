package li.pitschmann.knx.logic.test;

import li.pitschmann.knx.logic.components.LogicComponent;

import static org.assertj.core.api.Assertions.assertThat;

public final class LogicAssert extends AbstractComponentAssert<LogicAssert, LogicComponent> {
    LogicAssert(final LogicComponent actual) {
        super(actual, LogicAssert.class);
    }

    /**
     * Assert how often the logic component has been executed
     *
     * @param expected
     * @return myself
     */
    public LogicAssert executedCount(final long expected) {
        assertThat(this.actual.executedCount()).isEqualTo(expected);
        return this.myself;
    }

    /**
     * Assert how often the logic component has been executed
     *
     * @param expected
     * @return myself
     */
    public LogicAssert logicCount(final long expected) {
        assertThat(this.actual.logicCount()).isEqualTo(expected);
        return this.myself;
    }

    /**
     * Asserts the number of all pins
     *
     * @param expected expected number of all pins
     * @return myself
     */
    public LogicAssert numberOfPins(final int expected) {
        assertThat(this.actual.getPins()).hasSize(expected);
        return myself;
    }

    /**
     * Asserts the number of all connectors
     *
     * @param expected expected number of all connectors
     * @return myself
     */
    public LogicAssert numberOfConnectors(final int expected) {
        assertThat(this.actual.getConnectors()).hasSize(expected);
        return myself;
    }

    /**
     * Asserts the number of input pins
     *
     * @param expected expected number of input pins
     * @return myself
     */
    public LogicAssert numberOfInputPins(final int expected) {
        assertThat(this.actual.getInputPins()).hasSize(expected);
        return myself;
    }

    /**
     * Asserts the number of input connectors
     *
     * @param expected expected number of input connectors
     * @return myself
     */
    public LogicAssert numberOfInputConnectors(final int expected) {
        assertThat(this.actual.getInputConnectors()).hasSize(expected);
        return myself;
    }

    /**
     * Asserts the value of static input connector with name {@code fieldName}
     *
     * @param expectedValue the expected value of static input connector
     * @return myself
     */
    public LogicAssert input(final String fieldName,
                             final Object expectedValue) {
        return super.value(this.actual.getInputConnector(fieldName), expectedValue);
    }

    /**
     * Asserts all values of dynamic input connector with {@code fieldName}
     *
     * @param expectedValues all expected values of dynamic input connector
     * @return myself
     */
    public LogicAssert inputs(final String fieldName,
                              final Object... expectedValues) {
        return super.values(this.actual.getInputConnector(fieldName), expectedValues);
    }

    /**
     * Asserts the number of output pins
     *
     * @param expected expected number of output pins
     * @return myself
     */
    public LogicAssert numberOfOutputPins(final int expected) {
        assertThat(this.actual.getOutputPins()).hasSize(expected);
        return myself;
    }

    /**
     * Asserts the number of output connectors
     *
     * @param expected expected number of output connectors
     * @return myself
     */
    public LogicAssert numberOfOutputConnectors(final int expected) {
        assertThat(this.actual.getOutputConnectors()).hasSize(expected);
        return myself;
    }

    /**
     * Asserts the value of static output connector with name {@code fieldName}
     *
     * @param expectedValue the expected value of static output connector
     * @return myself
     */
    public LogicAssert output(final String fieldName,
                              final Object expectedValue) {
        return super.value(this.actual.getOutputConnector(fieldName), expectedValue);
    }

    /**
     * Asserts all values of dynamic output connector with {@code fieldName}
     *
     * @param expectedValues all expected values of dynamic output connector
     * @return myself
     */
    public LogicAssert outputs(final String fieldName,
                               final Object... expectedValues) {
        return super.values(this.actual.getOutputConnector(fieldName), expectedValues);
    }
}
