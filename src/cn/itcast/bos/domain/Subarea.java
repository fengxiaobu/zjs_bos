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

    public Subarea(String id, Region region, String addresskey, String startnum, String endnum, String single, String position) {
        this.id = id;
        this.region = region;
        this.addresskey = addresskey;
        this.startnum = startnum;
        this.endnum = endnum;
        this.single = single;
        this.position = position;
    }

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

	public String getId() {
        return id;
    }

	public void setId(String id) {
		this.id = id;
	}

    public Decidedzone getDecidedzone() {
        return decidedzone;
    }

    public void setDecidedzone(Decidedzone decidedzone) {
        this.decidedzone = decidedzone;
	}

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
	}

	public String getAddresskey() {
        return addresskey;
    }

	public void setAddresskey(String addresskey) {
		this.addresskey = addresskey;
	}

	public String getStartnum() {
        return startnum;
    }

	public void setStartnum(String startnum) {
		this.startnum = startnum;
	}

	public String getEndnum() {
        return endnum;
    }

	public void setEndnum(String endnum) {
		this.endnum = endnum;
	}

	public String getSingle() {
        return single;
    }

	public void setSingle(String single) {
		this.single = single;
	}

	public String getPosition() {
        return position;
    }

	public void setPosition(String position) {
		this.position = position;
	}
}