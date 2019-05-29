
public class Address {
	//public Type typeData ;
	public String postalCodeData ;
    public String lastUpdatedData ;
    public String id ;
    public String typeCode;
    public String typeName;
    public String countryCode;
    public String countryName;
    public String cityOrTown;

    public String addressLine1;
    public String addressLine2;

    public String provinceCode;
    public String provinceName;
	
    public String getAddressLine1() {
		return addressLine1;
	}




	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}




	public String getAddressLine2() {
		return addressLine2;
	}




	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}




	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}




	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Address( String countryCodeInput,String countryNameInput,
    				String typeCodeInput,String typeNameInput,
    				String postalCodeDataInput,
    				String lastUpdatedDataInput,
    				String idInput,
    				String cityOrTownInput
    				) {
    	
    	 	typeCode=typeCodeInput;
    	 	typeName=typeNameInput;
        	countryCode=countryCodeInput;
        	countryName=countryNameInput;
        	postalCodeData= postalCodeDataInput;
        	lastUpdatedData= lastUpdatedDataInput;
        	id= idInput;
        	cityOrTown=cityOrTownInput;
    	
    }

    
    

	public String getPostalCodeData() {
		return postalCodeData;
	}


	public String getLastUpdatedData() {
		return lastUpdatedData;
	}


	public String getId() {
		return id;
	}


	public String getTypeCode() {
		return typeCode;
	}


	public String getTypeName() {
		return typeName;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public String getCountryName() {
		return countryName;
	}


	public String getCityOrTown() {
		return cityOrTown;
	}

    
}
