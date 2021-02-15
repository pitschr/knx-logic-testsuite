package li.pitschmann.knx.logic.test;

import li.pitschmann.knx.logic.components.InboxComponent;
import li.pitschmann.knx.logic.components.LogicComponent;
import li.pitschmann.knx.logic.components.OutboxComponent;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.CheckReturnValue;

public class KnxLogicAssertions extends Assertions {
    /**
     * Create assertion for {@link LogicComponent}.
     *
     * @param actual the actual value.
     * @return the created assertion object.
     */
    @CheckReturnValue
    public static LogicAssert assertThat(final LogicComponent actual) {
        return new LogicAssert(actual);
    }

    /**
     * Create assertion for {@link InboxComponent}.
     *
     * @param actual the actual value.
     * @return the created assertion object.
     */
    @CheckReturnValue
    public static InboxAssert assertThat(final InboxComponent actual) {
        return new InboxAssert(actual);
    }

    /**
     * Create assertion for {@link OutboxComponent}.
     *
     * @param actual the actual value.
     * @return the created assertion object.
     */
    @CheckReturnValue
    public static OutboxAssert assertThat(final OutboxComponent actual) {
        return new OutboxAssert(actual);
    }
}
