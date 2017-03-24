package cn.itcast.bos.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * luopa 在 2017/3/24 创建.
 */
public class Noticebill implements Serializable {
    private String id;
    private String customerId;
    private String customerName;
    private String delegater;
    private String telephone;
    private String pickaddress;
    private String arrivecity;
    private String product;
    private Timestamp pickdate;
    private Integer num;
    private Double weight;
    private String volume;
    private String remark;
    private String ordertype;
    private User user;
    private Staff staff;
    private Set<Workbill> workbills = new HashSet(0);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDelegater() {
        return delegater;
    }

    public void setDelegater(String delegater) {
        this.delegater = delegater;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPickaddress() {
        return pickaddress;
    }

    public void setPickaddress(String pickaddress) {
        this.pickaddress = pickaddress;
    }

    public String getArrivecity() {
        return arrivecity;
    }

    public void setArrivecity(String arrivecity) {
        this.arrivecity = arrivecity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Timestamp getPickdate() {
        return pickdate;
    }

    public void setPickdate(Timestamp pickdate) {
        this.pickdate = pickdate;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Set<Workbill> getWorkbills() {
        return workbills;
    }

    public void setWorkbills(Set<Workbill> workbills) {
        this.workbills = workbills;
    }
}
