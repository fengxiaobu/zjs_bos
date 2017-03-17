package cn.itcast.bos.domain;

/**
 * BcSubarea entity. @author MyEclipse Persistence Tools
 */

public class Subarea implements java.io.Serializable {

	// Fields

	private String id;
	private Decidedzone decidedzone;
	private Region region;
	private String addresskey;
	private String startnum;
	private String endnum;
	private String single;
	private String position;

	// Constructors

	/** default constructor */
	public Subarea() {
	}

	/** full constructor */
	public Subarea(Decidedzone decidedzone, Region region,
			String addresskey, String startnum, String endnum, String single,
			String position) {
		this.decidedzone = decidedzone;
		this.region = region;
		this.addresskey = addresskey;
		this.startnum = startnum;
		this.endnum = endnum;
		this.single = single;
		this.position = position;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Decidedzone getBcDecidedzone() {
		return this.decidedzone;
	}

	public void setBcDecidedzone(Decidedzone decidedzone) {
		this.decidedzone = decidedzone;
	}

	public Region getBcRegion() {
		return this.region;
	}

	public void setBcRegion(Region region) {
		this.region = region;
	}

	public String getAddresskey() {
		return this.addresskey;
	}

	public void setAddresskey(String addresskey) {
		this.addresskey = addresskey;
	}

	public String getStartnum() {
		return this.startnum;
	}

	public void setStartnum(String startnum) {
		this.startnum = startnum;
	}

	public String getEndnum() {
		return this.endnum;
	}

	public void setEndnum(String endnum) {
		this.endnum = endnum;
	}

	public String getSingle() {
		return this.single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}