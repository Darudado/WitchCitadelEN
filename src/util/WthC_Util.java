package data.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import org.jetbrains.annotations.Nullable;
import data.scripts.util.MagicFakeBeam;
import org.lazywizard.lazylib.CollisionUtils;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;
import java.util.*;

//主要来自于北极星Util
// 只是为了实现某些功能是吧~？
public class WthC_Util {
	public static final Vector2f ZERO = new Vector2f();


	public static boolean shieldHit(BeamAPI beam, ShipAPI target) {
		return target.getShield() != null && target.getShield().isOn() && target.getShield().isWithinArc(beam.getTo());
	}

	public static Vector2f getShipCollisionPoint(Vector2f segStart, Vector2f segEnd, ShipAPI ship) {
		if (ship.getCollisionClass() == CollisionClass.NONE) {
			return null;
		} else {
			ShieldAPI shield = ship.getShield();
			if (shield != null && !shield.isOff()) {
				Vector2f circleCenter = shield.getLocation();
				float circleRadius = shield.getRadius();
				if (MathUtils.isPointWithinCircle(segStart, circleCenter, circleRadius)) {
					return shield.isWithinArc(segStart) ? segStart : getCollisionPointEX(segStart, segEnd, ship);
				} else {
					Vector2f tmp1 = MagicFakeBeam.getCollisionPointOnCircumference(segStart, segEnd, circleCenter, circleRadius);
					return tmp1 != null && shield.isWithinArc(tmp1) ? tmp1 : getCollisionPointEX(segStart, segEnd, ship);
				}
			} else {
				return getCollisionPointEX(segStart, segEnd, ship);
			}
		}
	}

	public static Vector2f getCollisionPointEX(Vector2f lineStart, Vector2f lineEnd, CombatEntityAPI target) {
		if (CollisionUtils.isPointWithinBounds(lineStart, target)) {
			return lineStart;
		}
		return CollisionUtils.getCollisionPoint(lineStart, lineEnd, target);
	}



	public static class I18nSection {
		private final String category;
		private final String keyPrefix;
		private static final List<I18nSection> sections = new ArrayList();

		public I18nSection(String category, String keyPrefix) {
			this.category = category;
			if (keyPrefix != null) {
				this.keyPrefix = keyPrefix;
			} else {
				this.keyPrefix = "";
			}

			sections.add(this);
		}

		public I18nSection(String category) {
			this(category, (String) null);
		}

		public String format(String keyMainBody, @Nullable Object... args) {
			return args != null && args.length > 0 ? this.absFormat(keyMainBody, args) : this.get(keyMainBody);
		}

		public String get() {
			try {
				return Global.getSettings().getString(this.category, this.keyPrefix);
			} catch (Exception var2) {
				return "[NULL]";
			}
		}

		public String get(String key) {
			try {
				return Global.getSettings().getString(this.category, this.keyPrefix + key);
			} catch (Exception var3) {
				return "[NULL]";
			}
		}

		private String absFormat(String key, Object... args) {
			try {
				String result = String.format(this.get(key), args);
				return result;
			} catch (Exception var5) {
				return "[NULL]";
			}
		}

		public static WthC_Util.I18nSection getInstance(String category, String keyPrefix) {
			Iterator i$ = sections.iterator();

			WthC_Util.I18nSection section;
			do {
				if (!i$.hasNext()) {
					return new WthC_Util.I18nSection(category, keyPrefix);
				}

				section = (WthC_Util.I18nSection) i$.next();
			} while (!section.category.contentEquals(category) || !section.keyPrefix.contentEquals(keyPrefix));

			return section;
		}
	}
}