package cn.itcast.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * BcStaff entity. @author MyEclipse Persistence Tools
 */

public class Staff implements java.io.Serializable {

	// Fields

	private String id;//编号
	private String name;//姓名
	private String telephone;//手机号
	private String haspda = "0";//是否有pda 0为否 1为是
	private String deltag = "0";//删除标识 0为未删除 1为已删除
	private String station;//单位
	private String standard;//收派标准
    private Set decidedzones = new HashSet(0);

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** full constructor */
	public Staff(String name, String telephone, String haspda, String deltag,
                 String station, String standard, Set decidedzones) {
        this.name = name;
		this.telephone = telephone;
		this.haspda = haspda;
		this.deltag = deltag;
		this.station = station;
		this.standard = standard;
        this.decidedzones = decidedzones;
    }

	// Property accessors


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

	public String getTelephone() {
        return telephone;
    }

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHaspda() {
        return haspda;
    }

	public void setHaspda(String haspda) {
		this.haspda = haspda;
	}

	public String getDeltag() {
        return deltag;
    }

	public void setDeltag(String deltag) {
		this.deltag = deltag;
	}

	public String getStation() {
        return station;
    }

	public void setStation(String station) {
		this.station = station;
	}

	public String getStandard() {
        return standard;
    }

	public void setStandard(String standard) {
		this.standard = standard;
	}

    public Set getDecidedzones() {
        return decidedzones;
    }

    public void setDecidedzones(Set decidedzones) {
        this.decidedzones = decidedzones;
    }
}