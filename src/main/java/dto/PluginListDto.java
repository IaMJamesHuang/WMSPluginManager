package dto;

import po.Plugin;

import java.util.List;

public class PluginListDto extends BaseDto {

    private List<Plugin> result;

    public List<Plugin> getResult() {
        return result;
    }

    public void setResult(List<Plugin> result) {
        this.result = result;
    }
}