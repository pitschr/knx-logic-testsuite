package li.pitschmann.knx.logic.test;

import li.pitschmann.knx.logic.connector.Connector;
import li.pitschmann.knx.logic.connector.DynamicConnector;
import li.pitschmann.knx.logic.connector.StaticConnector;
import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AssertJ for Component. All methods are 'protected' so that they might be
 * re-used by e.g. {@link LogicAssert}, {@link InboxAssert} and {@link OutboxAssert}
 * <p>
 * {@link InboxAssert}, for example, has only output connector/pins and only those
 * selected methods will be 'public' then
 *
 * @param <SELF>   the "self" type of this assertion class
 * @param <ACTUAL> the type of the "actual" value.
 */
abstract class AbstractComponentAssert<SELF extends AbstractComponentAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractAssert<SELF, ACTUAL> {
    /**
     * Constructor for {@link AbstractComponentAssert}
     *
     * @param actual the actual value
     */
    AbstractComponentAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    /**
     * Asserts the {@link Connector} if the value equals to the expected value.
     *
     * @param connector     the {@link Connector} to be checked
     * @param expectedValue the expected value
     * @return myself
     */
    protected SELF value(final Connector connector,
                         final Object expectedValue) {
        assertThat(connector).isInstanceOf(StaticConnector.class);
        final var staticConnector = (StaticConnector) connector;
        assertThat(staticConnector.getPin().getValue()).isEqualTo(expectedValue);

        return this.myself;
    }

    /**
     * Asserts the {@link Connector} if the values equals to the expected
     * values in exactly same order.
     *
     * @param connector      the {@link Connector} to be checked
     * @param expectedValues expected values
     * @return myself
     */
    protected SELF values(final Connector connector,
                          final Object... expectedValues) {
        assertThat(connector).isInstanceOf(DynamicConnector.class);
        final var dynamicConnector = (DynamicConnector) connector;
        final var pins = dynamicConnector.getPins();
        assertThat(pins).hasSameSizeAs(expectedValues);
        for (int i = 0; i < pins.size(); i++) {
            assertThat(pins.get(i).getValue()).isEqualTo(expectedValues[i]);
        }

        return this.myself;
    }
}
