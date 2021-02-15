package li.pitschmann.knx.logic.test.logic;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.components.LogicComponent;
import li.pitschmann.knx.logic.components.LogicComponentImpl;
import li.pitschmann.knx.logic.test.TestHelpers;

/**
 * Test Data container for testing purposes
 */
public final class LogicScenario {
    /**
     * Private constructor. This class should be instantiated using:
     *
     * <ul>
     *     <li>{@link #given(LogicComponent)}</li>
     *     <li>{@link #given(Class)}</li>
     * </ul>
     */
    private LogicScenario() {
        throw new AssertionError("Do not touch me!");
    }

    /**
     * Enters creating a {@link LogicScenario} by entering the {@link InputBuilderChain}.
     * <p>
     * This will create a new {@link LogicComponent}. If you want to re-use the {@link LogicComponent}
     * then please use the {@link #given(LogicComponent)} instead.
     *
     * @param logicClass the logic class to be instantiated, will create a new {@link LogicComponent}
     * @return an instance of {@link EnterInputBuilderChain}
     */
    public static EnterInputBuilderChain given(final Class<? extends Logic> logicClass) {
        return given(new LogicComponentImpl(TestHelpers.newInstance(logicClass)));
    }

    /**
     * Enters creating a {@link LogicScenario} by entering the {@link InputBuilderChain}
     *
     * @param logicComponent the logic component to be tested, may be re-used
     * @return an instance of {@link EnterInputBuilderChain}
     */
    public static EnterInputBuilderChain given(final LogicComponent logicComponent) {
        final var context = new LogicScenarioContext(logicComponent);
        return new EnterInputBuilderChain(context);
    }
}
