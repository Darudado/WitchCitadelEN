//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.CoreUIAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.FactionDoctrineAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SubmarketPlugin;
import com.fs.starfarer.api.campaign.CampaignUIAPI.CoreUITradeMode;
import com.fs.starfarer.api.campaign.SubmarketPlugin.OnClickAction;
import com.fs.starfarer.api.campaign.SubmarketPlugin.PlayerEconomyImpactMode;
import com.fs.starfarer.api.campaign.SubmarketPlugin.TransferAction;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import com.fs.starfarer.api.util.Highlights;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.impl.campaign.submarkets.BaseSubmarketPlugin;
import com.fs.starfarer.api.impl.campaign.submarkets.OpenMarketPlugin;
import java.awt.Color;
import java.util.Random;

import exerelin.utilities.NexConfig;
import org.apache.log4j.Logger;

//从军用市场修改
public class Crimson_Treasure extends BaseSubmarketPlugin {
    public static Logger log = Global.getLogger(Crimson_Treasure.class);
    private RepLevel minStanding;

    public Crimson_Treasure() {
        this.minStanding = RepLevel.getLevelFor(0.1f);
    }

    public void init(SubmarketAPI submarket) {
        super.init(submarket);
    }

    public void updateCargoPrePlayerInteraction() {
    }

    @Override
    public float getTariff() {return 100f;}


    public String getName() {
        return this.submarket.getFaction().getId().equals("crimson_treasure") ? "Crimson" : "Crimson Treasure";
    }


    public boolean isEnabled(CoreUIAPI ui) {
        if (ui.getTradeMode() == CoreUITradeMode.SNEAK) {
            return false;
        } else {
            RepLevel level = RepLevel.getLevelFor(WthC_Citadel.getHILLYA().getRelToPlayer().getRel());
            return level.isAtWorst(this.minStanding);
        }
    }

    public SubmarketPlugin.OnClickAction getOnClickAction(CoreUIAPI ui) {
        return OnClickAction.OPEN_SUBMARKET;
    }

    public String getTooltipAppendix(CoreUIAPI ui) {
        if (!this.isEnabled(ui)) {
            return "Requirement: Personal relationship with the Crimson Lord greater than 10";
        } else {
            return ui.getTradeMode() == CoreUITradeMode.SNEAK ? "Requires: Authorization for normal docking" : null;
        }
    }

    public Highlights getTooltipAppendixHighlights(CoreUIAPI ui) {
        String appendix = this.getTooltipAppendix(ui);
        if (appendix == null) {
            return null;
        } else {
            Highlights h = new Highlights();
            h.setText(new String[]{appendix});
            h.setColors(new Color[]{Misc.getNegativeHighlightColor()});
            return h;
        }
    }

    public SubmarketPlugin.PlayerEconomyImpactMode getPlayerEconomyImpactMode() {
        return PlayerEconomyImpactMode.PLAYER_SELL_ONLY;
    }

    public boolean isMilitaryMarket() {
        return true;
    }
}
