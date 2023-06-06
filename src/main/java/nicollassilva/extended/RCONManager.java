package nicollassilva.extended;

import java.util.Map;
import java.util.HashMap;
import com.eu.habbo.Emulator;
import com.eu.habbo.messages.rcon.RCONMessage;
import nicollassilva.extended.rcons.RemoveBadge;

public class RCONManager {
    protected final HashMap<String, Class<? extends RCONMessage<?>>> RCONs;

    public RCONManager() {
        RCONs = new HashMap<>();
    }

    public int RCONsLength() {
        return RCONs.size();
    }

    public void registerRCONs() {
        this.registerRCONsFromFolder();

        for (Map.Entry<String, Class<? extends RCONMessage<?>>> registeredRCON : RCONs.entrySet()) {
            Emulator.getRconServer().addRCONMessage(registeredRCON.getKey(), registeredRCON.getValue());
        }
    }

    public void registerRCONsFromFolder() {
        this.registerRCON("removebadge", RemoveBadge.class);
    }

    public void registerRCON(String key, Class<? extends RCONMessage<?>> targetClass) {
        RCONs.putIfAbsent(key, targetClass);
    }
}
