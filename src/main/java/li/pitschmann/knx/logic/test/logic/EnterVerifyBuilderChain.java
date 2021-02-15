package li.pitschmann.knx.logic.test.logic;

/**
 * Enter Verify Build Chain by 'verify()' to validate the logic scenario
 */
public final class EnterVerifyBuilderChain extends AbstractBuilderChain {
    EnterVerifyBuilderChain(final LogicScenarioContext context) {
        super(context);
    }

    /**
     * Verifies the {@link LogicScenarioContext}
     */
    public void verify() {
        new LogicScenarioChecker(getContext()).verify();
    }
}
