package common.helper.context;

import java.util.List;

public class UserContext implements Context {
    private final String name;
    private final List<String> roleList;

    public UserContext(String name, String ...roleList) {
        this.name = name;
        this.roleList = List.of(roleList);
    }

    public String getName() {
        return name;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    @Override
    public String toString() {
        return getName() + " " + getRoleList();
    }
}
