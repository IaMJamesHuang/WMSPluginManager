package dto;

import po.UserAccessBean;

import java.util.List;

public class PluginAccessDto extends BaseDto {

    private List<UserAccessBean> result;

    public List<UserAccessBean> getResult() {
        return result;
    }

    public void setResult(List<UserAccessBean> result) {
        this.result = result;
    }

}
