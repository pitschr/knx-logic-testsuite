package li.pitschmann.knx.logic.test.logic;

/**
 * Enters the Input area by choosing: noOutputs() or output(..) or outputs(..)
 */
public final class EnterOutputBuilderChain extends AbstractOutputBuilderChain<OutputBuilderChain> {
    EnterOutputBuilderChain(final LogicScenarioContext context) {
        super(context);
    }

    /**
     * Special case for {@link EnterOutputBuilderChain}
     *
     * @return EnterVerifyBuilderChain
     */
    public EnterVerifyBuilderChain noOutputs() {
        return new EnterVerifyBuilderChain(getContext());
    }

    @Override
    protected OutputBuilderChain nextStep() {
        return new OutputBuilderChain(getContext());
    }
}
