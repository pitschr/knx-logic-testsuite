package li.pitschmann.knx.logic.test.logic;

import li.pitschmann.knx.logic.connector.DynamicConnector;
import li.pitschmann.knx.logic.connector.StaticConnector;
import li.pitschmann.knx.logic.test.TestHelpers;

import java.util.Objects;

/**
 * Output Chaining by adding output(..) and outputs(..) methods.
 * Will be 'closed' with 'verify()'.
 * <p>
 * Inherited by {@link EnterOutputBuilderChain} and {@link OutputBuilderChain}
 *
 * @param <T> the type of instance that is used for next step
 */
abstract class AbstractOutputBuilderChain<T> extends AbstractBuilderChain {
    protected AbstractOutputBuilderChain(final LogicScenarioContext context) {
        super(context);
    }

    /**
     * Returns the next step of builder chain
     *
     * @return an instance of type {@code T}
     */
    protected abstract T nextStep();

    /**
     * Add expected output value for output static connector
     *
     * @param fieldName     name of field; may not be null
     * @param expectedValue expected static output value
     * @return an instance of type <T> for the next chain step
     */
    public T output(final String fieldName, final Object expectedValue) {
        getContext().outputConnectors.put(Objects.requireNonNull(fieldName), new ConnectorProperties(StaticConnector.class, new Object[]{expectedValue}));
        return nextStep();
    }

    /**
     * Add expected output values for output dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic output values
     * @return an instance of type <T> for the next chain step
     */
    public T outputs(final String fieldName, final Object... expectedValues) {
        getContext().outputConnectors.put(Objects.requireNonNull(fieldName), new ConnectorProperties(DynamicConnector.class, expectedValues));
        return nextStep();
    }

    /**
     * Add expected output values for output dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic output values as boolean array
     * @return an instance of type <T> for the next chain step
     */
    public T outputs(final String fieldName, final boolean[] expectedValues) {
        return outputs0(fieldName, expectedValues);
    }

    /**
     * Add expected output values for output dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic output values as byte array
     * @return an instance of type <T> for the next chain step
     */
    public T outputs(final String fieldName, final byte[] expectedValues) {
        return outputs0(fieldName, expectedValues);
    }

    /**
     * Add expected output values for output dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic output values as char array
     * @return an instance of type <T> for the next chain step
     */
    public T outputs(final String fieldName, final char[] expectedValues) {
        return outputs0(fieldName, expectedValues);
    }

    /**
     * Add expected output values for output dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic output values as short array
     * @return an instance of type <T> for the next chain step
     */
    public T outputs(final String fieldName, final short[] expectedValues) {
        return outputs0(fieldName, expectedValues);
    }

    /**
     * Add expected output values for output dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic output values as int array
     * @return an instance of type <T> for the next chain step
     */
    public T outputs(final String fieldName, final int[] expectedValues) {
        return outputs0(fieldName, expectedValues);
    }

    /**
     * Add expected output values for output dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic output values as long array
     * @return an instance of type <T> for the next chain step
     */
    public T outputs(final String fieldName, final long[] expectedValues) {
        return outputs0(fieldName, expectedValues);
    }

    /**
     * Add expected output values for output dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic output values as float array
     * @return an instance of type <T> for the next chain step
     */
    public T outputs(final String fieldName, final float[] expectedValues) {
        return outputs0(fieldName, expectedValues);
    }

    /**
     * Add expected output values for output dynamic connector
     *
     * @param fieldName      name of field; may not be null
     * @param expectedValues expected dynamic output values as double array
     * @return an instance of type <T> for the next chain step
     */
    public T outputs(final String fieldName, final double[] expectedValues) {
        return outputs0(fieldName, expectedValues);
    }

    private T outputs0(final String fieldName, final Object expectedAsAnArray) {
        return outputs(fieldName, TestHelpers.asArray(expectedAsAnArray));
    }
}
