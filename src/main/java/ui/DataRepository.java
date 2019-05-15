package ui;

import dto.PluginListDto;
import net.RequestManagement;
import po.Plugin;

import java.util.List;

public class DataRepository {

    private static List<Plugin> pluginList;

    public static List<Plugin> getPluginList() {
        if (pluginList == null) {
            PluginListDto dto = RequestManagement.getPluginList();
            if (dto != null && dto.getResult() != null) {
                pluginList = dto.getResult();
            }
        }
        return pluginList;
    }

}
