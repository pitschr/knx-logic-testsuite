package li.pitschmann.knx.logic.test.logic;

/**
 * Input Chaining by adding input(..) and inputs(..) methods.
 * Will be 'closed' with 'then()' which enters the {@link OutputBuilderChain}
 */
public final class InputBuilderChain extends AbstractInputBuilderChain<InputBuilderChain> {
    InputBuilderChain(final LogicScenarioContext context) {
        super(context);
    }

    /**
     * Leaving input chaining and entering the output chaining
     *
     * @return the {@link EnterOutputBuilderChain}
     */
    public EnterOutputBuilderChain then() {
        return new EnterOutputBuilderChain(getContext());
    }

    @Override
    protected InputBuilderChain nextStep() {
        return this;
    }
}
