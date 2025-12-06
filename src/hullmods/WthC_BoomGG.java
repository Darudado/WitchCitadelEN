package data.hullmods;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import data.util.WthC_ColorData;
import org.lwjgl.util.vector.Vector2f;
public class WthC_BoomGG extends BaseHullMod {

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        float pad = 10f;
        if (ship == null) {
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("Self-destruct at start!", WthC_ColorData.IE_WHITE, pad);
        }
        if (ship != null) {
            tooltip.addSectionHeading("Introduction", Alignment.TMID, 5.0F);
            tooltip.addPara("Self-destruct at start!", WthC_ColorData.IE_WHITE, pad);
        }

    }


    public void advanceInCombat(ShipAPI ship, float amount) {
        if (ship == null) {
            return;
        }
        Vector2f hitLoc = ship.getLocation();
        super.advanceInCombat(ship, amount);
        if (ship.isAlive()) {
            Global.getCombatEngine().applyDamage(ship, hitLoc, 500_000,
                    DamageType.OTHER, 500_000, true, false, null);
        }
    }
}
