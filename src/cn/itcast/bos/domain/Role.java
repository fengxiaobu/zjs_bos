package cn.itcast.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * luopa 在 2017/3/24 创建.
 */
public class Role {
    private String id;
    private String name;
    private String code;
    private String description;
    private Set<Function> functions = new HashSet(0);

    public void setFunctions(Set<Function> functions) {
        this.functions = functions;
    }

    public Set<Function> getFunctions() {
        return functions;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {

        this.users = users;
    }

    private Set<User> users = new HashSet(0);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
