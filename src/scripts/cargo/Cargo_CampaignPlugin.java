package data.scripts.cargo;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import org.apache.bcel.generic.PUSH;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/*
在modplugin的 on new game中加入
if (!Global.getSector().hasScript(Cargo_CampaignPlugin.class)) {
      Global.getSector().addScript(new Cargo_CampaignPlugin());
    }
 */
public class Cargo_CampaignPlugin implements EveryFrameScript {
    private float dayTimer = 0;
    private static final String key ="$Wthc_Cargo_auto";
    private static final String tag ="Cargo_target";
    
    //用这个控制这个脚本是否开启, 有这个key就开启
    private static final String hascargo_key ="$Wthc_Cargo_harcargo";
    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean runWhilePaused() {
        return false;
    }

    @Override
    public void advance(float amount) {
        if (Global.getSector() == null || Global.getSector().getPlayerFleet() == null) {
            return;
        }

        if(!Global.getSector().getMemoryWithoutUpdate().contains(hascargo_key)) {return;}

        dayTimer = dayTimer + Global.getSector().getClock().convertToDays(amount);
        float checkInterval = 1f; //时间间隔
        if (dayTimer < checkInterval) return;
        dayTimer = dayTimer - checkInterval;


        CargoAPI cargo =Global.getSector().getPlayerFleet().getCargo();
        if (cargo == null) return;

        WTHC_cargodata crateData = WTHC_cargodata.getInstance();

        // if(Global.getSector().getCampaignUI().isShowingMenu() || Global.getSector().getCampaignUI().getCurrentInteractionDialog() != null)
            // return;
        boolean hasCrate = false;

      //  Global.getLogger(this.getClass()).info("wwlfjldlfshdlfshfldsl");

            for (CargoStackAPI stack : cargo.getStacksCopy()) {

                if (should_goto_cargo(stack) ) {
                    if(should_go()){
                       // Global.getLogger(this.getClass()).info("goto");
                        crateData.getCargo().addFromStack(stack);
                        cargo.removeStack(stack);
                    }
                } else if (stack.getSpecialDataIfSpecial() instanceof special_cargodata ||(
                        stack.getSpecialItemSpecIfSpecial()!=null && stack.getSpecialDataIfSpecial().getId().equals("Wthc_cargo"))) {
                 //   Global.getLogger(this.getClass()).info("IHAVEIT "+"\n \b \n \n ");
                    special_cargodata data = (special_cargodata) stack.getSpecialDataIfSpecial();
                    if (data != null && data.getCargo() != null && !data.getCargo().isEmpty()) {
                        WTHC_cargodata.addCargo(data.getCargo());
                        data.getCargo().removeAll(WTHC_cargodata.getInstance().getCargo());
                    }
                    hasCrate = true;
                }

            }


        if (!hasCrate) {
           // Global.getLogger(this.getClass()).info("ADDEDDDDDDD "+"\n \b \n \n ");
            CargoStackAPI stack = Global.getFactory().createCargoStack(CargoAPI.CargoItemType.SPECIAL, new special_cargodata(), cargo);
            stack.setSize(1);
            cargo.addFromStack(stack);
         //   Global.getLogger(this.getClass()).info("addstack"+stack.getSpecialDataIfSpecial().getId());
        }
    }

    //这里是实际判断什么样的商品需要计入
    public boolean should_goto_cargo(CargoStackAPI stack){
        if (!stack.isSpecialStack()  ) {
            CommoditySpecAPI spec = stack.getResourceIfResource();
            if (spec != null && spec.getTags().contains(tag)) {
                return true;
            }
        }
        if(stack.isSpecialStack() && stack.getSpecialItemSpecIfSpecial()!=null ){
            if (stack.getSpecialDataIfSpecial() instanceof special_cargodata){
                return false;
            }
            if(stack.getSpecialItemSpecIfSpecial().getTags().contains(tag)){
                return true;
            }

        }
        return false;
    }
    public boolean should_go(){

        return !Global.getSector().getMemoryWithoutUpdate().contains(key)||
                Global.getSector().getMemoryWithoutUpdate().getBoolean(key);
    }
}
