package nicollassilva.extended.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import nicollassilva.extended.ExtendedArcturusRCON;
import com.eu.habbo.plugin.events.emulator.EmulatorLoadedEvent;
import com.eu.habbo.plugin.events.emulator.EmulatorStoppedEvent;

public class EmulatorEvents implements EventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtendedArcturusRCON.class);

    @EventHandler
    public static void onEmulatorLoaded(EmulatorLoadedEvent event) {
        ExtendedArcturusRCON.getManager().registerRCONs();

        LOGGER.info("[ExtendedArcturusRCON] Extended RCONs registered successfully with (" + ExtendedArcturusRCON.getManager().RCONsLength() + ") RCONs.");
    }

    @EventHandler
    public static void onEmulatorStopped(EmulatorStoppedEvent event) {
        LOGGER.info("[ExtendedArcturusRCON] Stopping executions...");
    }
}
