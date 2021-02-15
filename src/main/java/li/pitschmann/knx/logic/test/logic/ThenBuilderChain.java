package li.pitschmann.knx.logic.test.logic;

/**
 * Then Chaining by adding then(..) methods which switches from <strong>input</strong>
 * to the <strong>output</strong> context.
 */
public final class ThenBuilderChain extends AbstractBuilderChain {
    ThenBuilderChain(final LogicScenarioContext context) {
        super(context);
    }

    /**
     * Completes the input chain, and enters the output chain.
     *
     * @return EnterOutputBuilderChain
     */
    public EnterOutputBuilderChain then() {
        return new EnterOutputBuilderChain(getContext());
    }
}
