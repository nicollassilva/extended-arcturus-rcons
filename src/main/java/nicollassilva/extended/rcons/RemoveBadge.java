package nicollassilva.extended.rcons;

import com.google.gson.Gson;
import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.rcon.RCONMessage;
import com.eu.habbo.messages.outgoing.users.UserBadgesComposer;
import com.eu.habbo.habbohotel.users.inventory.BadgesComponent;
import com.eu.habbo.messages.outgoing.inventory.InventoryBadgesComposer;

public class RemoveBadge extends RCONMessage<RemoveBadge.JSON> {

    public RemoveBadge() {
        super(JSON.class);
    }

    @Override
    public void handle(Gson gson, JSON json) {
        if (json.user_id <= 0) {
            this.status = RCONMessage.HABBO_NOT_FOUND;
            return;
        }

        if (json.badge.isEmpty()) {
            this.status = RCONMessage.SYSTEM_ERROR;
            return;
        }

        Habbo habbo = Emulator.getGameEnvironment().getHabboManager().getHabbo(json.user_id);

        for (String badgeCode : json.badge.split(";")) {
            if(habbo != null) {
                habbo.getInventory().getBadgesComponent().removeBadge(badgeCode);

                habbo.getClient().sendResponse(new InventoryBadgesComposer(habbo));

                if(habbo.getHabboInfo().getCurrentRoom() != null) {
                    habbo.getHabboInfo().getCurrentRoom().sendComposer(
                            new UserBadgesComposer(habbo.getInventory().getBadgesComponent().getWearingBadges(), json.user_id).compose()
                    );
                }
            }

            this.removeUserBadge(json.user_id, badgeCode);
        }
    }

    public void removeUserBadge(int userId, String badgeCode) {
        BadgesComponent.deleteBadge(userId, badgeCode);
    }

    static class JSON {
        public int user_id = -1;

        public String badge;
    }
}
