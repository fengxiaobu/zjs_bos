package cn.itcast.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * BcRegion entity. @author MyEclipse Persistence Tools
 */

public class Region implements java.io.Serializable {

	// Fields

	private String id;
	private String province;
	private String city;
	private String district;
	private String postcode;
	private String shortcode;
	private String citycode;
    private Set subareas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Region() {
	}

	/** full constructor */
	public Region(String province, String city, String district,
                  String postcode, String shortcode, String citycode, Set subareas) {
        this.province = province;
		this.city = city;
		this.district = district;
		this.postcode = postcode;
		this.shortcode = shortcode;
		this.citycode = citycode;
        this.subareas = subareas;
    }

    public Region(String id, String province, String city, String district, String postcode, String shortcode, String citycode) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.district = district;
        this.postcode = postcode;
        this.shortcode = shortcode;
        this.citycode = citycode;
    }

    public String getName() {
        return province + city + district;
    }
    // Property accessors


	public String getId() {
        return id;
    }

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
        return province;
    }

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
        return city;
    }

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
        return district;
    }

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostcode() {
        return postcode;
    }

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getShortcode() {
        return shortcode;
    }

	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}

	public String getCitycode() {
        return citycode;
    }

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

    public Set getSubareas() {
        return subareas;
    }

    public void setSubareas(Set subareas) {
        this.subareas = subareas;
    }
}