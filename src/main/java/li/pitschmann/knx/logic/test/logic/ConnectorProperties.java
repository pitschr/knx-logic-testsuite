package li.pitschmann.knx.logic.test.logic;

import li.pitschmann.knx.core.annotations.Nullable;
import li.pitschmann.knx.logic.connector.Connector;

import java.util.Objects;

/**
 * Properties for Connector for testing purposes
 */
class ConnectorProperties {
    private final Class<Connector> connectorClass;
    private final Object[] values;

    @SuppressWarnings("unchecked")
    ConnectorProperties(final Class<? extends Connector> connectorClass, final @Nullable Object[] values) {
        this.connectorClass = (Class<Connector>) Objects.requireNonNull(connectorClass);
        this.values = values == null ? new Object[0] : values.clone();
    }

    Class<Connector> getConnectorClass() {
        return connectorClass;
    }

    Object[] getValues() {
        return values.clone();
    }
}
