package data.econ;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;

// Modified from light industry

public class WthC_Treasury_Vault extends BaseIndustry {
    public WthC_Treasury_Vault() {
    }

    public void apply() {
        super.apply(true);
        this.supply("cube_deepbule", 5);
        this.supply("cube_crimson", 5);
        this.supply("cube_densefog", 5);


    }
    public boolean isAvailableToBuild() {
        return false;
    }

    public boolean showWhenUnavailable() {
        return false;
    }

    public boolean canImprove() {
        return false;
    }


    public void unapply() {
        super.unapply();
    }



}
