package data.scripts;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import data.world.systems.Other_Void;
import data.world.systems.WthC_Citadel;

//来源于虚无主义教程
// 修改文件名和下方为 前缀名_NormalGenerate
// 在教程范例中为 GearIn_NormalGenerate
public class WthC_NormalGenerate implements SectorGeneratorPlugin {

    @Override
    public void generate(SectorAPI sector) {
        // 在此准备生成星系
        new WthC_Citadel().generate(sector);
        new Other_Void().generate(sector);


        relationAdj(sector);
    }

    protected void relationAdj(SectorAPI sector) {
        FactionAPI faction = sector.getFaction("Crimson_citadel");

        // 在原版 data.world.factions 查找势力id
        // RepLevel 是关系等级，详见原版 data.data.scripts.world.SectorGen

        faction.setRelationship("player", 0f);
        faction.setRelationship("pirates", RepLevel.NEUTRAL);
        faction.setRelationship("hegemony", RepLevel.NEUTRAL);
        faction.setRelationship("tritachyon", RepLevel.NEUTRAL);

        faction.setRelationship("luddic_path", RepLevel.NEUTRAL);
        faction.setRelationship("derelict", RepLevel.NEUTRAL);
        faction.setRelationship("remnant", RepLevel.NEUTRAL);

        faction.setRelationship("cabal", RepLevel.NEUTRAL);

        FactionAPI faction2 = sector.getFaction("Pale_Phantom");

        // 在原版 data.world.factions 查找势力id
        // RepLevel 是关系等级，详见原版 data.data.scripts.world.SectorGen

        faction2.setRelationship("player", 0f);
        faction2.setRelationship("pirates", RepLevel.NEUTRAL);
        faction2.setRelationship("hegemony", RepLevel.NEUTRAL);
        faction2.setRelationship("tritachyon", RepLevel.NEUTRAL);

        faction2.setRelationship("luddic_path", RepLevel.NEUTRAL);
        faction2.setRelationship("derelict", RepLevel.NEUTRAL);
        faction2.setRelationship("remnant", RepLevel.NEUTRAL);

        faction2.setRelationship("cabal", RepLevel.NEUTRAL);
    }
}