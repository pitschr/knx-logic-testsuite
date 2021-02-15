package li.pitschmann.knx.logic.test.logic;

/**
 * Enters the Input area by choosing: noInputs() or input(..) or inputs(..)
 */
public final class EnterInputBuilderChain extends AbstractInputBuilderChain<InputBuilderChain> {
    EnterInputBuilderChain(final LogicScenarioContext context) {
        super(context);
    }

    /**
     * Declares that there are no inputs and returns {@link ThenBuilderChain}
     *
     * @return ThenBuilderChain
     */
    public ThenBuilderChain noInputs() {
        return new ThenBuilderChain(getContext());
    }

    @Override
    protected InputBuilderChain nextStep() {
        return new InputBuilderChain(getContext());
    }
}
