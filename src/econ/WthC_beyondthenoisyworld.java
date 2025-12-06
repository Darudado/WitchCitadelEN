//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.econ;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketImmigrationModifier;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin;

// Modified from population control

public class WthC_beyondthenoisyworld extends BaseMarketConditionPlugin implements MarketImmigrationModifier {
    public WthC_beyondthenoisyworld() {
    }

    public void apply(String id) {
        this.market.addTransientImmigrationModifier(this);
    }

    public void unapply(String id) {
        this.market.removeTransientImmigrationModifier(this);
    }

    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        incoming.getWeight().modifyFlat(this.getModId(), -50f, "Beyond the Noisy World");
    }
}