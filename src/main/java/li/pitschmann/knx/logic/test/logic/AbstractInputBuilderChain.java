package li.pitschmann.knx.logic.test.logic;

import li.pitschmann.knx.logic.connector.DynamicConnector;
import li.pitschmann.knx.logic.connector.StaticConnector;
import li.pitschmann.knx.logic.test.TestHelpers;

import java.util.Objects;


/**
 * Abstract Input Chaining by adding input(..) and inputs(..) methods.
 * <p>
 * Inherited by {@link EnterInputBuilderChain} and {@link InputBuilderChain}
 *
 * @param <T> the type of instance that is used for next step
 */
abstract class AbstractInputBuilderChain<T> extends AbstractBuilderChain {
    protected AbstractInputBuilderChain(final LogicScenarioContext context) {
        super(context);
    }

    /**
     * Returns the next step of builder chain
     *
     * @return an instance of type {@code T}
     */
    protected abstract T nextStep();

    /**
     * Add expected input value for input static connector
     *
     * @param fieldName     name of field; may not be null
     * @param expectedValue expected static input value
     * @return an instance of type <T> for the next chain step
     */
    public T input(final String fieldName, final Object expectedValue) {
        getContext().inputConnectors.put(Objects.requireNonNull(fieldName), new ConnectorProperties(StaticConnector.class, new Object[]{expectedValue}));
        return nextStep();
    }

    /**
     * Add expected input values for input dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic input values
     * @return an instance of type <T> for the next chain step
     */
    public T inputs(final String fieldName, final Object... expectedValues) {
        getContext().inputConnectors.put(Objects.requireNonNull(fieldName), new ConnectorProperties(DynamicConnector.class, expectedValues));
        return nextStep();
    }

    /**
     * Add expected input values for input dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic input values as boolean array
     * @return an instance of type <T> for the next chain step
     */
    public T inputs(final String fieldName, final boolean[] expectedValues) {
        return inputs0(fieldName, expectedValues);
    }

    /**
     * Add expected input values for input dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic input values as byte array
     * @return an instance of type <T> for the next chain step
     */
    public T inputs(final String fieldName, final byte[] expectedValues) {
        return inputs0(fieldName, expectedValues);
    }

    /**
     * Add expected input values for input dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic input values as char array
     * @return an instance of type <T> for the next chain step
     */
    public T inputs(final String fieldName, final char[] expectedValues) {
        return inputs0(fieldName, expectedValues);
    }

    /**
     * Add expected input values for input dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic input values as short array
     * @return an instance of type <T> for the next chain step
     */
    public T inputs(final String fieldName, final short[] expectedValues) {
        return inputs0(fieldName, expectedValues);
    }

    /**
     * Add expected input values for input dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic input values as int array
     * @return an instance of type <T> for the next chain step
     */
    public T inputs(final String fieldName, final int[] expectedValues) {
        return inputs0(fieldName, expectedValues);
    }

    /**
     * Add expected input values for input dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic input values as long array
     * @return an instance of type <T> for the next chain step
     */
    public T inputs(final String fieldName, final long[] expectedValues) {
        return inputs0(fieldName, expectedValues);
    }

    /**
     * Add expected input values for input dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic input values as float array
     * @return an instance of type <T> for the next chain step
     */
    public T inputs(final String fieldName, final float[] expectedValues) {
        return inputs0(fieldName, expectedValues);
    }

    /**
     * Add expected input values for input dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic input values as double array
     * @return an instance of type <T> for the next chain step
     */
    public T inputs(final String fieldName, final double[] expectedValues) {
        return inputs0(fieldName, expectedValues);
    }

    private T inputs0(final String fieldName, final Object expectedAsAnArray) {
        return inputs(fieldName, TestHelpers.asArray(expectedAsAnArray));
    }
}
