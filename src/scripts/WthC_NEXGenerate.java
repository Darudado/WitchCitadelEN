package data.scripts;

import com.fs.starfarer.api.campaign.SectorAPI;
import exerelin.campaign.SectorManager;

//来源于虚无主义教程
public class WthC_NEXGenerate extends WthC_NormalGenerate {

    @Override
    public void generate(SectorAPI sector) {
        if (SectorManager.getManager().isCorvusMode()) { // 检测 nex 是否为原版地图模式
            super.generate(sector);
        }
    }
}