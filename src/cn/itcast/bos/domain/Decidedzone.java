package cn.itcast.bos.domain;

import java.io.Serializable;
import java.util.Set;

/**
 * luopa 在 2017/3/24 创建.
 */
public class Decidedzone implements Serializable {
    private String id;
    private String name;
    private Staff staff;
    private Set<Subarea> subareas;
    public Decidedzone(String id) {
        this.id = id;
    }

    /** full constructor */
    public Decidedzone(String id, Staff staff, String name, Set subareas) {
        this.id = id;
        this.staff = staff;
        this.name = name;
        this.subareas = subareas;
    }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Decidedzone that = (Decidedzone) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Decidedzone() {
    }

    public Set<Subarea> getSubareas() {
        return subareas;
    }

    public void setSubareas(Set<Subarea> subareas) {
        this.subareas = subareas;
    }
}
