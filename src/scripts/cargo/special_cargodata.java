package data.scripts.cargo;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;

public class special_cargodata extends SpecialItemData{
     private final CargoAPI cargo;

    public CargoAPI getCargo() {
        return cargo;
    }

    public special_cargodata() {
        super("Wthc_cargo", "");
        this.cargo = Global.getFactory().createCargo(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        special_cargodata that = (special_cargodata) o;

        return cargo != null ? cargo.equals(that.cargo) : that.cargo == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (cargo != null ? cargo.hashCode() : 0);
        return result;
    }
}

