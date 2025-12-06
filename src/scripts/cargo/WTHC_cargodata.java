package data.scripts.cargo;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;

public class WTHC_cargodata {
        private final CargoAPI cargo;

        public CargoAPI getCargo() {
            return cargo;
        }

        public WTHC_cargodata() {
            this.cargo = Global.getFactory().createCargo(true);
        }

        public WTHC_cargodata(CargoAPI cargo) {
            this.cargo = cargo;
        }

        private static String GLOBAL_KEY = "Wthc_persistent_cargo_key";
        public static WTHC_cargodata getInstance() {
            if (!Global.getSector().getPersistentData().containsKey(GLOBAL_KEY)) {
                Global.getSector().getPersistentData().put(GLOBAL_KEY, new WTHC_cargodata());
            }

            return (WTHC_cargodata) Global.getSector().getPersistentData().get(GLOBAL_KEY);
        }

        public static void addCargo(CargoAPI cargo) {
            WTHC_cargodata data = getInstance();
            if (data == null) {
                data = new WTHC_cargodata(cargo);
            } else {
                data.cargo.addAll(cargo);
            }
            Global.getSector().getPersistentData().put(GLOBAL_KEY,  data);
        }

}
