package com.mindtree.mib.constant;

public enum CricketerStatisticEnum {
	
	
	pid,
	
	profile,
	
	imageURL,
	
	battingStyle,
	
	bowlingStyle,
	
	born,
	
	name,
	
	country,
	
	playingRole,
	
	_10,
	
	_5w,
	
	_4w,
	
	SR,
	
	Econ,
	
	Ave,
	
	Wkts,
	
	Runs,
	
	Balls,
	
	Inns,
	
	Mat,
	
	_50,
	
	_100,
	
	St,
	
	Ct,
	
	_6s,
	
	_4s,
	
	HS,
	
	BF,
	
	unknown,
	
	NO;	
	
	
	/**
     * cricketerRequestParam.
     */
    private String cricketerRequestParam;

    /**
     * 
     * <pre>
     * <b>Description : </b>
     * Constructs an instance of 'BaggageSsrEnum'.
     * 
     * @param cricketerRequestParam , may be null.
     * </pre>
     */
    /*CricketerStatisticEnum(final String cricketerRequestParam) {
    	this.cricketerRequestParam = cricketerRequestParam;
    }*/

    /**
     * <pre>
     * <b>Description : </b>
     * xmlValue.
     * 
     * @return String , null if not found
     * </pre>
     */
    public String xmlValue() {
        return cricketerRequestParam;
    }

    /**
     * <pre>
     * <b>Description : </b>
     * convert.
     * 
     * @param value , may be null
     * @return BagDamageLocationType , null if not found
     * </pre>
     */
	public static CricketerStatisticEnum convert(String value) {
		for (CricketerStatisticEnum inst : values()) {
			if (inst.xmlValue().equals(value)) {
				return inst;
			}
		}
		return null;
	}

}
