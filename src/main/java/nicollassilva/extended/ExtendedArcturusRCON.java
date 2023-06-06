package nicollassilva.extended;

import org.slf4j.Logger;
import com.eu.habbo.Emulator;
import org.slf4j.LoggerFactory;
import com.eu.habbo.plugin.HabboPlugin;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.habbohotel.users.Habbo;
import nicollassilva.extended.events.EmulatorEvents;

public class ExtendedArcturusRCON extends HabboPlugin implements EventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtendedArcturusRCON.class);
    private static final RCONManager manager = new RCONManager();

    @Override
    public void onEnable() {
        Emulator.getPluginManager().registerEvents(this, new EmulatorEvents());

        LOGGER.info("[ExtendedArcturusRCON] Plugin started successfully.");
    }

    public static RCONManager getManager() {
        return manager;
    }

    @Override
    public void onDisable() {}

    @Override
    public boolean hasPermission(Habbo habbo, String s) {
        return false;
    }
}
