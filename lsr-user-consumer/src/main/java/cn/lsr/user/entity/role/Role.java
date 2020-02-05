package cn.lsr.user.entity.role;

import cn.lsr.user.entity.permission.Permission;

import java.util.Set;

public class Role {
    private String rid;

    private String rname;

    private String available;

    private Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available == null ? null : available.trim();
    }
}