//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignUIAPI.CoreUITradeMode;
import com.fs.starfarer.api.campaign.CoreUIAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.impl.campaign.submarkets.BaseSubmarketPlugin;
import com.fs.starfarer.api.util.Highlights;
import com.fs.starfarer.api.util.Misc;
import org.apache.log4j.Logger;

import java.awt.*;

//从军用市场修改
public class Database_Dust extends BaseSubmarketPlugin {
    public static Logger log = Global.getLogger(Database_Dust.class);

    private RepLevel minStanding;


    public Database_Dust() {
            this.minStanding = RepLevel.getLevelFor(0.6f);
    }

    public void init(SubmarketAPI submarket) {
        super.init(submarket);
    }

    public void updateCargoPrePlayerInteraction() {
    }

    @Override
    public float getTariff() {return 100f;}


    public String getName() {
        return this.submarket.getFaction().getId().equals("database_dust") ? "Crimson" : "Bridge Database";
    }


    public boolean isEnabled(CoreUIAPI ui) {
        if (ui.getTradeMode() == CoreUITradeMode.SNEAK) {
            return false;
        } else {
            RepLevel level = RepLevel.getLevelFor(WthC_Citadel.getHILLYA().getRelToPlayer().getRel());
            return level.isAtWorst(this.minStanding);
        }
    }

    public OnClickAction getOnClickAction(CoreUIAPI ui) {
        return OnClickAction.OPEN_SUBMARKET;
    }

    public String getTooltipAppendix(CoreUIAPI ui) {
        if (!this.isEnabled(ui)) {
            return "Requirement: Personal relationship with the Crimson Lord greater than 60";
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

    public PlayerEconomyImpactMode getPlayerEconomyImpactMode() {
        return PlayerEconomyImpactMode.PLAYER_SELL_ONLY;
    }

    public boolean isMilitaryMarket() {
        return true;
    }
}
