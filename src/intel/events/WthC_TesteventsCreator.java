package data.intel.events;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.BaseBarEventCreator;

// 从幻想工造军官酒馆事件修改
// 已不再生效
public class WthC_TesteventsCreator extends BaseBarEventCreator {
    public WthC_TesteventsCreator() {
    }

    public WthC_Testevents createBarEvent() {
        return new WthC_Testevents();
    }

    public boolean isPriority() {
        return true;
    }

    public float getBarEventFrequencyWeight() {
        return 100.0F;
    }

    public float getBarEventAcceptedTimeoutDuration() {
        return 1.0E10F;
    }
}
