package net.phoenix.Webserver.containers;

import net.hypixel.api.reply.skyblock.SkyBlockBazaarReply;
import net.phoenix.Webserver.Constants;

public class NucleusRun {

    // Amber Crystal
    double goblin_egg = 0;
    String goblin_egg_type = "";

    // Sapphire crystal
    double electron_transmitter;
    double ftx_3070;
    double robotron_reflector;
    double superlite_motor;
    double control_switch;
    double synthetic_heart;

    // Amethyst Crystal
    double jungle_key;

    double total_cost;

    public NucleusRun(SkyBlockBazaarReply bazaar) {
        // Amber Crystal
        for (String eggType : Constants.EGG_TYPES) {
            SkyBlockBazaarReply.Product egg = bazaar.getProduct(eggType);
            if (goblin_egg == 0) {
                this.goblin_egg = egg.getQuickStatus().getBuyPrice() * 3;
                this.goblin_egg_type = eggType;
                continue;
            }
            if (egg.getQuickStatus().getBuyPrice() < this.goblin_egg) {
                this.goblin_egg = egg.getQuickStatus().getBuyPrice() * 3;
                this.goblin_egg_type = eggType;
            }
        }
        // Sapphire Crystal
        electron_transmitter = bazaar.getProduct("ELECTRON_TRANSMITTER").getQuickStatus().getBuyPrice();
        ftx_3070 = bazaar.getProduct("FTX_3070").getQuickStatus().getBuyPrice();
        robotron_reflector = bazaar.getProduct("ROBOTRON_REFLECTOR").getQuickStatus().getBuyPrice();
        superlite_motor = bazaar.getProduct("SUPERLITE_MOTOR").getQuickStatus().getBuyPrice();
        control_switch = bazaar.getProduct("CONTROL_SWITCH").getQuickStatus().getBuyPrice();
        synthetic_heart = bazaar.getProduct("SYNTHETIC_HEART").getQuickStatus().getBuyPrice();
        // Amethyst Crystal
        jungle_key = bazaar.getProduct("JUNGLE_KEY").getQuickStatus().getBuyPrice();

        total_cost = goblin_egg + electron_transmitter + ftx_3070 + robotron_reflector + superlite_motor + control_switch + synthetic_heart + jungle_key;
    }

    public String toPrettyJson() {
        return Constants.PRETTY_PRINT_GSON.toJson(this);
    }

    public String toJson() {
        return Constants.GSON.toJson(this);
    }
}
