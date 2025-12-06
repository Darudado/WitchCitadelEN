package data.util;

import com.fs.starfarer.api.Global;

// 摘抄至北极星
public class I18nUtil {
    private static final String CATE_SHIP_SYSTEM = "shipSystem";
    private static final String CATE_STAR_SYSTEMS = "starSystems";
    private static final String CATE_HULL_MOD = "hullMod";

    public I18nUtil() {
    }

    public static String getString(String category, String id) {
        return Global.getSettings().getString(category, id);
    }

    public static String getShipSystemString(String id) {
        return getString("shipSystem", id);
    }

    public static String getStarSystemsString(String id) {
        return getString("starSystems", id);
    }

    public static String getHullModString(String id) {
        return getString("hullMod", id);
    }
}
