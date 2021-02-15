package li.pitschmann.knx.logic.test;

import li.pitschmann.knx.logic.Logic;
import li.pitschmann.knx.logic.components.InboxComponent;
import li.pitschmann.knx.logic.components.InboxComponentImpl;
import li.pitschmann.knx.logic.components.LogicComponent;
import li.pitschmann.knx.logic.components.LogicComponentImpl;
import li.pitschmann.knx.logic.components.OutboxComponent;
import li.pitschmann.knx.logic.components.OutboxComponentImpl;
import li.pitschmann.knx.logic.components.inbox.Inbox;
import li.pitschmann.knx.logic.components.outbox.Outbox;
import li.pitschmann.knx.logic.connector.Connector;
import li.pitschmann.knx.logic.connector.ConnectorAware;
import li.pitschmann.knx.logic.connector.DynamicConnector;
import li.pitschmann.knx.logic.connector.StaticConnector;
import li.pitschmann.knx.logic.event.EventKey;
import org.assertj.core.util.CheckReturnValue;

import java.util.NoSuchElementException;
import java.util.UUID;

import static li.pitschmann.knx.logic.test.KnxLogicAssertions.assertThat;

/**
 * Abstract Test for all test cases
 */
public abstract class AbstractTest {
    /**
     * Create assertion for {@link Logic} class.
     *
     * @param actualClass the logic class that should be asserted
     * @return the created assertion object.
     */
    @CheckReturnValue
    public LogicAssert assertLogic(final Class<? extends Logic> actualClass) {
        final var logic = TestHelpers.newInstance(actualClass);
        return assertThat(new LogicComponentImpl(logic));
    }

    /**
     * Create assertion for {@link LogicComponent} instance.
     *
     * @param actual the {@link LogicComponent} instance that should be verified
     * @return the created assertion object.
     */
    @CheckReturnValue
    public LogicAssert assertLogic(final LogicComponent actual) {
        return assertThat(actual);
    }

    /**
     * Create assertion for {@link InboxComponent}.
     *
     * @param actualClass the actual value.
     * @return the created assertion object.
     */
    @CheckReturnValue
    public InboxAssert assertInbox(final Class<? extends Inbox> actualClass) {
        final var inbox = TestHelpers.newInstance(actualClass);
        ;
        return assertInbox(new InboxComponentImpl(new EventKey("test", UUID.randomUUID().toString()), inbox));
    }

    /**
     * Create assertion for {@link InboxComponent} instance.
     *
     * @param actual the {@link InboxComponent} instance that should be verified
     * @return the created assertion object.
     */
    @CheckReturnValue
    public InboxAssert assertInbox(final InboxComponent actual) {
        return assertThat(actual);
    }

    /**
     * Create assertion for {@link OutboxComponent}.
     *
     * @param actual the actual value.
     * @return the created assertion object.
     */
    @CheckReturnValue
    public OutboxAssert assertOutbox(final Class<? extends Outbox> actual) {
        final var outbox = TestHelpers.newInstance(actual);
        return assertOutbox(new OutboxComponentImpl(new EventKey("test", UUID.randomUUID().toString()), outbox));
    }

    /**
     * Create assertion for {@link OutboxComponent} instance.
     *
     * @param actual the {@link OutboxComponent} instance that should be verified
     * @return the created assertion object.
     */
    @CheckReturnValue
    public OutboxAssert assertOutbox(final OutboxComponent actual) {
        return assertThat(actual);
    }

    /**
     * Handy method to return a connector {@code fieldName} from {@link ConnectorAware}
     * instance.
     *
     * @param object    object that contains the connector; cannot be null
     * @param fieldName the field name used for look-up; cannot be null
     * @return a connector
     * @throws NoSuchElementException in case the connector could not be found
     */
    protected final Connector connector(final ConnectorAware object, final String fieldName) {
        return object.getConnector(fieldName);
    }

    /**
     * Handy method to return a dynamic connector {@code fieldName} from {@link ConnectorAware}
     * instance. It will also ensure that the connector is an instance of {@link StaticConnector}.
     *
     * @param object    object that contains the static connector; cannot be null
     * @param fieldName the field name used for look-up; cannot be null
     * @return a static connector
     * @throws NoSuchElementException in case the connector could not be found
     */
    protected final StaticConnector staticConnector(final ConnectorAware object, final String fieldName) {
        final var connector = connector(object, fieldName);
        assertThat(connector).isExactlyInstanceOf(StaticConnector.class);
        return (StaticConnector) connector;
    }

    /**
     * Handy method to return a dynamic connector {@code fieldName} from {@link ConnectorAware}
     * instance. It will also ensure that the connector is an instance of {@link DynamicConnector}.
     *
     * @param object    object that contains the dynamic connector; cannot be null
     * @param fieldName the field name used for look-up; cannot be null
     * @return a dynamic connector
     * @throws NoSuchElementException in case the connector could not be found
     */
    protected final DynamicConnector dynamicConnector(final ConnectorAware object, final String fieldName) {
        final var connector = connector(object, fieldName);
        assertThat(connector).isExactlyInstanceOf(DynamicConnector.class);
        return (DynamicConnector) connector;
    }
}
