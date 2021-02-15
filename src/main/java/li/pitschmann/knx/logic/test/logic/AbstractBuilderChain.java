package li.pitschmann.knx.logic.test.logic;

/**
 * Abstract Build Chain holding the {@link LogicScenarioContext}
 */
abstract class AbstractBuilderChain {
    private final LogicScenarioContext context;

    protected AbstractBuilderChain(final LogicScenarioContext context) {
        this.context = context;
    }

    protected final LogicScenarioContext getContext() {
        return context;
    }
}
