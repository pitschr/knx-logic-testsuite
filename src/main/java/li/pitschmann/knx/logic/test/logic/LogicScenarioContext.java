package li.pitschmann.knx.logic.test.logic;

import li.pitschmann.knx.logic.components.LogicComponent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The context for {@link LogicScenario} and is a mutable class.
 * <p>
 * This class is package-protected and used for transferring between build chains.
 */
class LogicScenarioContext {
    final Map<String, ConnectorProperties> inputConnectors = new LinkedHashMap<>();
    final Map<String, ConnectorProperties> outputConnectors = new LinkedHashMap<>();
    final LogicComponent logicComponent;

    LogicScenarioContext(final LogicComponent logicComponent) {
        this.logicComponent = Objects.requireNonNull(logicComponent);
    }
}
