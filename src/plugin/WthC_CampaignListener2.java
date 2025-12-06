package data.plugin;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.FleetInteractionDialogPluginImpl;
import com.fs.starfarer.api.impl.campaign.ids.MemFlags;
import data.world.systems.WthC_Citadel;

import static com.fs.starfarer.api.impl.campaign.FleetInteractionDialogPluginImpl.*;


public class WthC_CampaignListener2 extends BaseCampaignEventListener implements EveryFrameScript {
    public WthC_CampaignListener2() {
        super(true);
    }
    @Override
    public void reportFleetSpawned(CampaignFleetAPI fleet){

        if(fleet.hasTag("WthC_CM-RRA")){


            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Pale_Phantom_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("SENetLink",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("helmsmanship",1);
                    officer.setPersonality("cautious");

                    officer.getStats().setLevel(4);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("SENetLink",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("helmsmanship",1);
                officer.setPersonality("cautious");

                officer.getStats().setLevel(4);
            }
        }
        if(fleet.hasTag("WthC_CM-Cross")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();
                if (officer == fleet.getCommander()) continue;
                if (!officer.isDefault()){
                    member.setCaptain(null);
                }

            }
        }
        if(fleet.hasTag("WthC_CM-IE")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Pale_Phantom_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("SENetLink",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("energy_weapon_mastery",2);
                    officer.getStats().setSkillLevel("missile_specialization",2);
                    officer.getStats().setSkillLevel("gunnery_implants",1);
                    officer.setPersonality("precise");

                    officer.getStats().setLevel(5);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("SENetLink",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("energy_weapon_mastery",2);
                officer.getStats().setSkillLevel("missile_specialization",2);
                officer.getStats().setSkillLevel("gunnery_implants",1);
                officer.setPersonality("precise");

                officer.getStats().setLevel(5);
            }
        }
        if(fleet.hasTag("WthC_CM-SSoul")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Pale_Phantom_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("SENetLink",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("ordnance_expert",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("systems_expertise",1);
                    officer.setPersonality("aggressive");
                    officer.getStats().setLevel(6);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("SENetLink",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("ordnance_expert",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("systems_expertise",1);
                officer.setPersonality("aggressive");
                officer.getStats().setLevel(6);
            }
        }
        if(fleet.hasTag("WthC_CM-Elf")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Pale_Phantom_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("SENetLink",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("gunnery_implants",1);
                    officer.setPersonality("timid");
                    officer.getStats().setLevel(4);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("SENetLink",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("gunnery_implants",1);
                officer.setPersonality("timid");
                officer.getStats().setLevel(4);
            }
        }
        if(fleet.hasTag("WthC_CM-Dust")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Pale_Phantom_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("SENetLink",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("missile_specialization",2);
                    officer.getStats().setSkillLevel("energy_weapon_mastery",1);
                    officer.setPersonality("reckless");
                    officer.getStats().setLevel(5);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("SENetLink",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("missile_specialization",2);
                officer.getStats().setSkillLevel("energy_weapon_mastery",1);
                officer.setPersonality("reckless");
                officer.getStats().setLevel(5);
            }
        }
        if(fleet.hasTag("WthC_BA-RRA")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Pale_Phantom_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("Pale_SENetLink",2);
                    officer.getStats().setSkillLevel("Pale_damage_control",2);
                    officer.getStats().setSkillLevel("Pale_polarized_armor",2);
                    officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                    officer.getStats().setSkillLevel("Pale_point_defense",1);
                    officer.getStats().setSkillLevel("Pale_impact_mitigation",1);
                    officer.setPersonality("cautious");
                    officer.getStats().setLevel(6);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("Pale_SENetLink",2);
                officer.getStats().setSkillLevel("Pale_damage_control",2);
                officer.getStats().setSkillLevel("Pale_polarized_armor",2);
                officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                officer.getStats().setSkillLevel("Pale_point_defense",1);
                officer.getStats().setSkillLevel("Pale_impact_mitigation",1);
                officer.setPersonality("cautious");
                officer.getStats().setLevel(6);
            }
        }
        if(fleet.hasTag("WthC_BA-Cross")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();
                if (officer == fleet.getCommander()) continue;
                if (!officer.isDefault()){
                    member.setCaptain(null);
                }

            }
        }
        if(fleet.hasTag("WthC_BA-IE")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Pale_Phantom_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("Pale_SENetLink",2);
                    officer.getStats().setSkillLevel("Pale_field_modulation",2);
                    officer.getStats().setSkillLevel("Pale_energy_weapon_mastery",2);
                    officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                    officer.getStats().setSkillLevel("Pale_gunnery_implants",1);
                    officer.getStats().setSkillLevel("Pale_missile_specialization",2);
                    officer.getStats().setSkillLevel("Pale_combat_endurance",1);
                    officer.setPersonality("precise");
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("Pale_SENetLink",2);
                officer.getStats().setSkillLevel("Pale_field_modulation",2);
                officer.getStats().setSkillLevel("Pale_energy_weapon_mastery",2);
                officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                officer.getStats().setSkillLevel("Pale_gunnery_implants",1);
                officer.getStats().setSkillLevel("Pale_missile_specialization",2);
                officer.getStats().setSkillLevel("Pale_combat_endurance",1);
                officer.setPersonality("precise");
                officer.getStats().setLevel(7);
            }
        }
        if(fleet.hasTag("WthC_BA-SSoul")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Pale_Phantom_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("Pale_SENetLink",2);
                    officer.getStats().setSkillLevel("Pale_field_modulation",2);
                    officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                    officer.getStats().setSkillLevel("Pale_missile_specialization",2);
                    officer.getStats().setSkillLevel("Pale_target_analysis",2);
                    officer.getStats().setSkillLevel("Pale_energy_weapon_mastery",2);
                    officer.getStats().setSkillLevel("Pale_systems_expertise",1);
                    officer.getStats().setSkillLevel("Pale_combat_endurance",1);
                    officer.setPersonality("aggressive");
                    officer.getStats().setLevel(8);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("Pale_SENetLink",2);
                officer.getStats().setSkillLevel("Pale_field_modulation",2);
                officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                officer.getStats().setSkillLevel("Pale_missile_specialization",2);
                officer.getStats().setSkillLevel("Pale_target_analysis",2);
                officer.getStats().setSkillLevel("Pale_energy_weapon_mastery",2);
                officer.getStats().setSkillLevel("Pale_systems_expertise",1);
                officer.getStats().setSkillLevel("Pale_combat_endurance",1);
                officer.setPersonality("aggressive");
                officer.getStats().setLevel(8);
            }
        }
        if(fleet.hasTag("WthC_BA-Elf")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Pale_Phantom_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("Pale_SENetLink",2);
                    officer.getStats().setSkillLevel("Pale_helmsmanship",2);
                    officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                    officer.getStats().setSkillLevel("Pale_missile_specialization",2);
                    officer.getStats().setSkillLevel("Pale_systems_expertise",1);
                    officer.getStats().setSkillLevel("Pale_combat_endurance",1);
                    officer.setPersonality("heroic");
                    officer.getStats().setLevel(6);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("Pale_SENetLink",2);
                officer.getStats().setSkillLevel("Pale_helmsmanship",2);
                officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                officer.getStats().setSkillLevel("Pale_missile_specialization",2);
                officer.getStats().setSkillLevel("Pale_systems_expertise",1);
                officer.getStats().setSkillLevel("Pale_combat_endurance",1);
                officer.setPersonality("heroic");
                officer.getStats().setLevel(6);
            }
        }
        if(fleet.hasTag("WthC_BA-Dust")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Pale_Phantom_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("Pale_SENetLink",2);
                    officer.getStats().setSkillLevel("Pale_target_analysis",2);
                    officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                    officer.getStats().setSkillLevel("Pale_missile_specialization",2);
                    officer.getStats().setSkillLevel("Pale_energy_weapon_mastery",2);
                    officer.getStats().setSkillLevel("Pale_systems_expertise",1);
                    officer.getStats().setSkillLevel("Pale_ballistic_mastery",1);
                    officer.setPersonality("heroic");
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("Pale_SENetLink",2);
                officer.getStats().setSkillLevel("Pale_target_analysis",2);
                officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                officer.getStats().setSkillLevel("Pale_missile_specialization",2);
                officer.getStats().setSkillLevel("Pale_energy_weapon_mastery",2);
                officer.getStats().setSkillLevel("Pale_systems_expertise",1);
                officer.getStats().setSkillLevel("Pale_ballistic_mastery",1);
                officer.setPersonality("heroic");
                officer.getStats().setLevel(7);
            }
        }
        if(fleet.hasTag("WthC_AL-1")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("pirates").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("SENetLink",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ordnance_expert",2);
                    officer.getStats().setSkillLevel("missile_specialization",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);

                    officer.getStats().setLevel(5);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("SENetLink",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ordnance_expert",2);
                officer.getStats().setSkillLevel("missile_specialization",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);

                officer.getStats().setLevel(5);
            }
        }
        if(fleet.hasTag("WthC_AL-3")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Pale_Phantom_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("Pale_SENetLink",2);
                    officer.getStats().setSkillLevel("Pale_target_analysis",2);
                    officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                    officer.getStats().setSkillLevel("Pale_missile_specialization",2);
                    officer.getStats().setSkillLevel("Pale_damage_control",2);
                    officer.getStats().setSkillLevel("Pale_point_defense",2);

                    officer.getStats().setLevel(6);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("Pale_SENetLink",2);
                officer.getStats().setSkillLevel("Pale_target_analysis",2);
                officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                officer.getStats().setSkillLevel("Pale_missile_specialization",2);
                officer.getStats().setSkillLevel("Pale_damage_control",2);
                officer.getStats().setSkillLevel("Pale_point_defense",2);

                officer.getStats().setLevel(6);
            }
        }

        if(fleet.hasTag("WthC_CAP")){

            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Pale_Phantom_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("Pale_SENetLink",2);
                    officer.getStats().setSkillLevel("Pale_target_analysis",2);
                    officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                    officer.getStats().setSkillLevel("Pale_missile_specialization",2);
                    officer.getStats().setSkillLevel("Pale_damage_control",2);
                    officer.getStats().setSkillLevel("Pale_point_defense",2);
                    officer.getStats().setSkillLevel("Pale_field_modulation",2);
                    officer.getStats().setSkillLevel("Pale_systems_expertise",2);
                    officer.getStats().setSkillLevel("Pale_impact_mitigation",2);

                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("Pale_SENetLink",2);
                officer.getStats().setSkillLevel("Pale_target_analysis",2);
                officer.getStats().setSkillLevel("Pale_ordnance_expert",2);
                officer.getStats().setSkillLevel("Pale_missile_specialization",2);
                officer.getStats().setSkillLevel("Pale_damage_control",2);
                officer.getStats().setSkillLevel("Pale_point_defense",2);
                officer.getStats().setSkillLevel("Pale_field_modulation",2);
                officer.getStats().setSkillLevel("Pale_systems_expertise",2);
                officer.getStats().setSkillLevel("Pale_impact_mitigation",2);

                officer.getStats().setLevel(9);
            }
        }

        if(fleet.hasTag("WthC_DR-RRA")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_INTERACTION_DIALOG_CONFIG_OVERRIDE_GEN, new ResistBattleFIDConfig());
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);

                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);

                officer.getStats().setLevel(9);
            }
        }
        if(fleet.hasTag("WthC_DR-Cross")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_INTERACTION_DIALOG_CONFIG_OVERRIDE_GEN, new ResistBattleFIDConfig());
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);

                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);

                officer.getStats().setLevel(9);
            }
        }
        if(fleet.hasTag("WthC_DR-IE")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_INTERACTION_DIALOG_CONFIG_OVERRIDE_GEN, new ResistBattleFIDConfig());
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);

                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);

                officer.getStats().setLevel(9);
            }
        }
        if(fleet.hasTag("WthC_DR-SSoul")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_INTERACTION_DIALOG_CONFIG_OVERRIDE_GEN, new ResistBattleFIDConfig());
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);

                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);

                officer.getStats().setLevel(9);
            }
        }
        if(fleet.hasTag("WthC_DR-Elf")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_INTERACTION_DIALOG_CONFIG_OVERRIDE_GEN, new ResistBattleFIDConfig());
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);

                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);

                officer.getStats().setLevel(9);
            }
        }
        if(fleet.hasTag("WthC_DR-Dust")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_INTERACTION_DIALOG_CONFIG_OVERRIDE_GEN, new ResistBattleFIDConfig());
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);

                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);

                officer.getStats().setLevel(9);
            }
        }



        if(fleet.hasTag("Valkyrja_novtry_1")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);

                    officer.getStats().setLevel(3);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);

                officer.getStats().setLevel(3);
            }
        }
        if(fleet.hasTag("Valkyrja_novtry_2")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);

                    officer.getStats().setLevel(3);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);

                officer.getStats().setLevel(3);
            }
        }
        if(fleet.hasTag("Valkyrja_novtry_3")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);

                    officer.getStats().setLevel(5);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);

                officer.getStats().setLevel(5);
            }
        }
        if(fleet.hasTag("Valkyrja_novtry_4")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);

                    officer.getStats().setLevel(5);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);

                officer.getStats().setLevel(5);
            }
        }
        if(fleet.hasTag("Valkyrja_novtry_5")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);

                    officer.getStats().setLevel(5);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);

                officer.getStats().setLevel(5);
            }
        }
        if(fleet.hasTag("Valkyrja_novtry_6")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);

                    officer.getStats().setLevel(5);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);

                officer.getStats().setLevel(5);
            }
        }
        if(fleet.hasTag("Valkyrja_novtry_7")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setLevel(7);
            }
        }
        if(fleet.hasTag("Valkyrja_novtry_8")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setLevel(7);
            }
        }
        if(fleet.hasTag("Valkyrja_novtry_8")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setLevel(7);
            }
        }
        if(fleet.hasTag("Valkyrja_novtry_9")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setLevel(7);
            }
        }
        if(fleet.hasTag("Valkyrja_novtry_10")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setLevel(7);
            }
        }
        if(fleet.hasTag("Valkyrja_novtry_11")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_INTERACTION_DIALOG_CONFIG_OVERRIDE_GEN, new ResistBattleFIDConfig());
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setLevel(7);
            }
        }
        if(fleet.hasTag("WthC_10086001")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setLevel(7);
            }
        }
        if(fleet.hasTag("WthC_10086002")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setLevel(7);
            }
        }
        if(fleet.hasTag("WthC_10086003")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setLevel(7);
            }
        }
        if(fleet.hasTag("WthC_10086004")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setLevel(7);
            }
        }
        if(fleet.hasTag("WthC_10086005")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();
                if (officer == fleet.getCommander()) continue;
                if (!officer.isDefault()){
                    member.setCaptain(null);
                }
            }
        }
        if(fleet.hasTag("WthC_Ju1")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();


                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setLevel(5);
            }
        }
        if(fleet.hasTag("WthC_Ju2")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();


                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("ordnance_expert",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setLevel(6);
            }
        }
        if(fleet.hasTag("WthC_Ju3")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("ordnance_expert",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("energy_weapon_mastery",2);
                officer.getStats().setLevel(7);
            }
        }

        if(fleet.hasTag("WthC_ykm_psmhunt_1")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_INTERACTION_DIALOG_CONFIG_OVERRIDE_GEN, new ResistBattleFIDConfig());
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setLevel(1);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setLevel(1);
            }
        }
        if(fleet.hasTag("WthC_ykm_psmhunt_2")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_INTERACTION_DIALOG_CONFIG_OVERRIDE_GEN, new ResistBattleFIDConfig());
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("ordnance_expert",1);
                    officer.getStats().setSkillLevel("polarized_armor",1);
                    officer.getStats().setSkillLevel("damage_control",1);
                    officer.getStats().setLevel(5);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("ordnance_expert",1);
                officer.getStats().setSkillLevel("polarized_armor",1);
                officer.getStats().setSkillLevel("damage_control",1);
                officer.getStats().setLevel(5);
            }
        }
        if(fleet.hasTag("WthC_ykm_psmhunt_3")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_INTERACTION_DIALOG_CONFIG_OVERRIDE_GEN, new ResistBattleFIDConfig());
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("impact_mitigation",1);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("target_analysis",1);
                    officer.getStats().setSkillLevel("missile_specialization",2);
                    officer.getStats().setSkillLevel("ordnance_expert",1);
                    officer.getStats().setSkillLevel("polarized_armor",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("impact_mitigation",1);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("target_analysis",1);
                officer.getStats().setSkillLevel("missile_specialization",2);
                officer.getStats().setSkillLevel("ordnance_expert",1);
                officer.getStats().setSkillLevel("polarized_armor",2);
                officer.getStats().setLevel(7);
            }
        }
        if(fleet.hasTag("WthC_ykm_psmhunt_4")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_INTERACTION_DIALOG_CONFIG_OVERRIDE_GEN, new ResistBattleFIDConfig());
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);
                    officer.getStats().setSkillLevel("missile_specialization",2);
                    officer.getStats().setSkillLevel("ordnance_expert",2);
                    officer.getStats().setSkillLevel("polarized_armor",2);
                    officer.getStats().setLevel(10);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);
                officer.getStats().setSkillLevel("missile_specialization",2);
                officer.getStats().setSkillLevel("ordnance_expert",2);
                officer.getStats().setSkillLevel("polarized_armor",2);
                officer.getStats().setLevel(10);
            }
        }
        if(fleet.hasTag("WthC_ykm_psmhunt_5")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_INTERACTION_DIALOG_CONFIG_OVERRIDE_GEN, new ResistBattleFIDConfig());
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);
                    officer.getStats().setSkillLevel("missile_specialization",2);
                    officer.getStats().setLevel(10);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);
                officer.getStats().setSkillLevel("missile_specialization",2);
                officer.getStats().setLevel(10);
            }
        }

        if(fleet.hasTag("WthC_DR-MIZUGI")){
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (!member.getHullId().contains("gremlin")){
                    if (officer.isDefault()){
                        officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                        member.setCaptain(officer);
                        officer.getStats().setSkillLevel("helmsmanship",1);
                        officer.getStats().setSkillLevel("combat_endurance",2);
                        officer.getStats().setSkillLevel("damage_control",1);
                        officer.getStats().setSkillLevel("field_modulation",2);
                        officer.getStats().setSkillLevel("target_analysis",1);
                        officer.getStats().setSkillLevel("systems_expertise",2);
                        officer.getStats().setLevel(6);
                        continue;
                    }

                    if (officer == fleet.getCommander()) continue;

                    for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                        officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                    }
                    officer.getStats().setSkillLevel("helmsmanship",1);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("damage_control",1);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("target_analysis",1);
                    officer.getStats().setSkillLevel("systems_expertise",2);
                    officer.getStats().setLevel(6);
                }


                if (member.getHullId().contains("gremlin")){
                    if (!officer.isDefault()){
                        member.setCaptain(null);
                    }
                }

            }
        }

        if(fleet.hasTag("WthC_DR-NIKUSHOKU")){
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",1);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("target_analysis",1);
                    officer.getStats().setSkillLevel("systems_expertise",2);
                    officer.getStats().setSkillLevel("polarized_armor",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",1);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("target_analysis",1);
                officer.getStats().setSkillLevel("systems_expertise",2);
                officer.getStats().setSkillLevel("polarized_armor",2);
                officer.getStats().setLevel(7);
            }
        }

        if(fleet.hasTag("WthC_DR-HANABI")){
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);
                    officer.getStats().setSkillLevel("missile_specialization",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("systems_expertise",2);
                officer.getStats().setSkillLevel("missile_specialization",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setLevel(7);
            }
        }

        if(fleet.hasTag("WthC_DR-EVILCREATURE")){
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.setPortraitSprite(Global.getSettings().getSpriteName("characters", "lida"));
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);
                    officer.getStats().setSkillLevel("missile_specialization",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setLevel(7);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("systems_expertise",2);
                officer.getStats().setSkillLevel("missile_specialization",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.setPortraitSprite(Global.getSettings().getSpriteName("characters", "lida"));
                officer.getStats().setLevel(7);
            }
        }

        if(fleet.hasTag("WthC_DiamondDoom1")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);
                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);
                officer.getStats().setLevel(9);
            }
        }

        if(fleet.hasTag("WthC_DiamondDoom2")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);
                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);
                officer.getStats().setLevel(9);
            }
        }

        if(fleet.hasTag("WthC_DiamondDoom3")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);
                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);
                officer.getStats().setLevel(9);
            }
        }

        if(fleet.hasTag("WthC_DR_bzlryx")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);
                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);
                officer.getStats().setLevel(9);
            }
        }

        if(fleet.hasTag("WthC_DR_yxzlryx")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);
                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);
                officer.getStats().setLevel(9);
            }
        }

        if(fleet.hasTag("WthC_DR_szlryx")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);
                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);
                officer.getStats().setLevel(9);
            }
        }

        if(fleet.hasTag("WthC_DR_sslryx")){
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_SHIP_RECOVERY, true);
            for (FleetMemberAPI member : fleet.getFleetData().getMembersListCopy()) {
                PersonAPI officer = member.getCaptain();

                if (officer.isDefault()){
                    officer = Global.getSector().getFaction("Dark_Real_Ex").createRandomPerson();
                    member.setCaptain(officer);
                    officer.getStats().setSkillLevel("helmsmanship",2);
                    officer.getStats().setSkillLevel("combat_endurance",2);
                    officer.getStats().setSkillLevel("impact_mitigation",2);
                    officer.getStats().setSkillLevel("damage_control",2);
                    officer.getStats().setSkillLevel("field_modulation",2);
                    officer.getStats().setSkillLevel("point_defense",2);
                    officer.getStats().setSkillLevel("target_analysis",2);
                    officer.getStats().setSkillLevel("ballistic_mastery",2);
                    officer.getStats().setSkillLevel("systems_expertise",2);
                    officer.getStats().setLevel(9);
                    continue;
                }

                if (officer == fleet.getCommander()) continue;

                for (MutableCharacterStatsAPI.SkillLevelAPI skill: officer.getStats().getSkillsCopy()){
                    officer.getStats().setSkillLevel(skill.getSkill().getId(),0);
                }
                officer.getStats().setSkillLevel("helmsmanship",2);
                officer.getStats().setSkillLevel("combat_endurance",2);
                officer.getStats().setSkillLevel("impact_mitigation",2);
                officer.getStats().setSkillLevel("damage_control",2);
                officer.getStats().setSkillLevel("field_modulation",2);
                officer.getStats().setSkillLevel("point_defense",2);
                officer.getStats().setSkillLevel("target_analysis",2);
                officer.getStats().setSkillLevel("ballistic_mastery",2);
                officer.getStats().setSkillLevel("systems_expertise",2);
                officer.getStats().setLevel(9);
            }
        }

    }

    public static class ResistBattleFIDConfig implements FIDConfigGen {

        @Override
        public FIDConfig createConfig() {
            FIDConfig config = new FIDConfig();
            config.alwaysPursue = true;
            config.dismissOnLeave = false;
            config.leaveAlwaysAvailable = false;
            config.pullInAllies = true;
            config.pullInEnemies = true;
            config.pullInStations = true;
            config.delegate = new ResistBattleFIDDelegate();
            return config;
        }
    }



    @Override
    public void reportPlayerClosedMarket(MarketAPI market) {
    }

    @Override
    public void reportEncounterLootGenerated(FleetEncounterContextPlugin plugin, CargoAPI loot) {
    }

    @Override
    public void reportBattleFinished(CampaignFleetAPI primaryWinner, BattleAPI battle) {
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean runWhilePaused() {
        return true;
    }

    @Override
    public void advance(float amount) {

    }
}