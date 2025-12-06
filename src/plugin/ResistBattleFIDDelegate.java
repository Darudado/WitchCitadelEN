package data.plugin;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.combat.BattleCreationContext;
import com.fs.starfarer.api.impl.campaign.FleetEncounterContext;
import com.fs.starfarer.api.impl.campaign.FleetInteractionDialogPluginImpl;
import com.fs.starfarer.api.impl.campaign.RuleBasedInteractionDialogPluginImpl;

public class ResistBattleFIDDelegate implements FleetInteractionDialogPluginImpl.FIDDelegate {

    public static final String ONCE_FLAG = "$ResistBattleFIDDelegate_KEY";


    @Override
    public void postPlayerSalvageGeneration(InteractionDialogAPI interactionDialogAPI, FleetEncounterContext fleetEncounterContext, CargoAPI cargoAPI) {

    }

    @Override
    public void battleContextCreated(InteractionDialogAPI interactionDialogAPI, BattleCreationContext battleCreationContext) {
        battleCreationContext.enemyDeployAll = true;
    }

    @Override
    public void notifyLeave(InteractionDialogAPI interactionDialogAPI) {
        SectorEntityToken other = interactionDialogAPI.getInteractionTarget();
        if(!(other instanceof CampaignFleetAPI)){
            interactionDialogAPI.dismiss();
            return;
        }

        CampaignFleetAPI fleet = (CampaignFleetAPI) other;
        if (!fleet.isEmpty()){
            interactionDialogAPI.dismiss();
            return;
        }

        if (fleet.getMemoryWithoutUpdate().getBoolean(ONCE_FLAG)) return;
        fleet.getMemoryWithoutUpdate().set(ONCE_FLAG, true);

        RuleBasedInteractionDialogPluginImpl plugin = new RuleBasedInteractionDialogPluginImpl("WthC_FID");
        interactionDialogAPI.setPlugin(plugin);
        plugin.init(interactionDialogAPI);
    }
}


