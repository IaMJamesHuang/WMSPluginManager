package ui;

import dto.GetCompanyUserDto;
import dto.PluginAccessDto;
import dto.PluginListDto;
import net.RequestManagement;
import po.Company;
import po.Plugin;
import po.User;
import po.UserAccessBean;

import java.util.List;

public class DataRepository {

    private static List<Plugin> pluginList;

    private static List<User> userList;

    private static Company company;

    private static List<UserAccessBean> pluginAccessList;

    public static List<Plugin> getPluginList() {
        if (pluginList == null) {
            PluginListDto dto = RequestManagement.getPluginList();
            if (dto != null && dto.getResult() != null) {
                pluginList = dto.getResult();
            }
        }
        return pluginList;
    }

    public static List<User> getUserList() {
        if (userList == null) {
            GetCompanyUserDto dto = RequestManagement.getCompanyUserList(company.getId());
            if (dto != null && dto.getResult() != null) {
                userList = dto.getResult();
            }
        }
        return userList;
    }

    public static List<UserAccessBean> getPluginAccessList() {
        if (pluginAccessList == null) {
            PluginAccessDto dto = RequestManagement.queryPluginAccessList(company.getId());
            if (dto != null && dto.getResult() != null) {
                pluginAccessList = dto.getResult();
            }
        }
        return pluginAccessList;
    }

    public static boolean hasAccess(User user, Plugin plugin) {
        if (pluginAccessList == null) {
            getPluginAccessList();
        }
        for (UserAccessBean val : pluginAccessList) {
            if (val.getNick().equals(user.getNick()) && val.getName().equals(plugin.getName()) && val.getAuthority() == 1) {
                return true;
            }
        }
        return false;
    }

    public static void setCompany(Company value) {
        company = value;
    }

}
