package dto;

import po.User;

import java.util.List;

public class GetCompanyUserDto extends BaseDto {

    private List<User> result;

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }

}
