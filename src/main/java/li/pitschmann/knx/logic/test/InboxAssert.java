package li.pitschmann.knx.logic.test;

import li.pitschmann.knx.logic.components.InboxComponent;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AssertJ for {@link InboxComponent}
 */
public final class InboxAssert extends AbstractComponentAssert<InboxAssert, InboxComponent> {
    InboxAssert(final InboxComponent actual) {
        super(actual, InboxAssert.class);
    }

    /**
     * Asserts the number of output pins
     *
     * @param expected expected number of output pins
     * @return myself
     */
    public InboxAssert numberOfOutputPins(final int expected) {
        assertThat(this.actual.getOutputPins()).hasSize(expected);
        return myself;
    }

    /**
     * Asserts the number of output connectors
     *
     * @param expected expected number of output connectors
     * @return myself
     */
    public InboxAssert numberOfOutputConnectors(final int expected) {
        assertThat(this.actual.getOutputConnectors()).hasSize(expected);
        return myself;
    }

    /**
     * Asserts the value of static output connector with name {@code fieldName}
     *
     * @param expectedValue the expected value of static output connector
     * @return myself
     */
    public InboxAssert output(final String fieldName,
                              final Object expectedValue) {
        return super.value(this.actual.getOutputConnector(fieldName), expectedValue);
    }

    /**
     * Asserts all values of dynamic output connector with {@code fieldName}
     *
     * @param expectedValues all expected values of dynamic output connector
     * @return myself
     */
    public InboxAssert outputs(final String fieldName,
                               final Object... expectedValues) {
        return super.values(this.actual.getOutputConnector(fieldName), expectedValues);
    }
}
