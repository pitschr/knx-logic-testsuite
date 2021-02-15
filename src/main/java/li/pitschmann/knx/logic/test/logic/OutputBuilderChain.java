package li.pitschmann.knx.logic.test.logic;

/**
 * Output Chaining by adding output(..) and outputs(..) methods.
 * Will be 'closed' with 'verify()'.
 */
public final class OutputBuilderChain extends AbstractOutputBuilderChain<OutputBuilderChain> {
    OutputBuilderChain(final LogicScenarioContext context) {
        super(context);
    }

    /**
     * Verifies the {@link LogicScenarioContext}.
     * <p>
     * The builder chain will be completed with this step.
     */
    public void verify() {
        new LogicScenarioChecker(getContext()).verify();
    }

    @Override
    protected OutputBuilderChain nextStep() {
        return this;
    }
}
