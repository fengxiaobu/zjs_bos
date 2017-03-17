package cn.itcast.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * BcDecidedzone entity. @author MyEclipse Persistence Tools
 */

public class Decidedzone implements java.io.Serializable {

	// Fields

	private String id;
	private Staff staff;
	private String name;
	private Set bcSubareas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Decidedzone() {
	}

	/** full constructor */
	public Decidedzone(Staff staff, String name, Set bcSubareas) {
		this.staff = staff;
		this.name = name;
		this.bcSubareas = bcSubareas;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Staff getBcStaff() {
		return this.staff;
	}

	public void setBcStaff(Staff staff) {
		this.staff = staff;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getBcSubareas() {
		return this.bcSubareas;
	}

	public void setBcSubareas(Set bcSubareas) {
		this.bcSubareas = bcSubareas;
	}

}