import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@ManagedBean
@ViewScoped
public class Jsonupload {
	
	private String name;
	private Part FileObject;
	private InputStream inputStream;
	private OutputStream outputStream;
	private Boolean replaceFile;
	private ArrayList<Address> addressInformatoin =new ArrayList<Address>() ;
	private ArrayList<Address> validAddressInformatoin =new ArrayList<Address>() ;

	private  JSONArray arrayinfo;

	public Boolean getReplaceFile() {
		return replaceFile;
	}

	public void setReplaceFile(Boolean replaceFile) {
		this.replaceFile = replaceFile;
	}

	public Part getFileObject() {
		return FileObject;
	}

	public void setFileObject(Part fileObject) {
		FileObject = fileObject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public ArrayList<Address> getAddressInformatoin() {
		return addressInformatoin;
	}

	public void getAddress() {
		
		if(readFile()) 
		{
			addressInformatoin.clear();

			arrayinfo.forEach(infoData->prepList((JSONObject ) infoData));
		}
	}
	
	public void getValidAddress() {
		
		if(readFile()) 
		{
			arrayinfo.forEach(infoData->prepList((JSONObject ) infoData));
			validateAddress();
		}
	}
	public InputStream getInputStream() {
		return inputStream;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public ArrayList<Address> getValidAddressInformatoin() {
		return validAddressInformatoin;
	}

	public JSONArray getArrayinfo() {
		return arrayinfo;
	}

	private Boolean readFile() {
		
		 JSONParser parseData = new JSONParser();

		try (FileReader reader = new FileReader("c:\\addresses.json"))
        {
			Object objData = parseData.parse(reader);
			
			arrayinfo = (JSONArray)objData;
			return true;
			
        } catch (Exception e) {
        	
            return false;
        } 
	}
	
	public void prepList(JSONObject  info) {
		
		JSONObject countryObject = (JSONObject) info.get("country");		
		JSONObject typeObject = (JSONObject) info.get("type");		
		JSONObject provinceOrStateObject;
		JSONObject addressLineDetailObject;

		Address addresInfo = new Address( countryObject.get("code").toString(),countryObject.get("name").toString(),
				typeObject.get("code").toString(),typeObject.get("name").toString(),
				info.get("postalCode").toString(), 
				info.get("lastUpdated").toString(), 
				info.get("id").toString(), 
				info.get("cityOrTown").toString());
		
		//-->setting other data
		if(info.containsKey("provinceOrState")) {
			 provinceOrStateObject = (JSONObject) info.get("provinceOrState");
			 addresInfo.setProvinceName(provinceOrStateObject.get("code").toString());
			 addresInfo.setProvinceCode(provinceOrStateObject.get("name").toString());
		}
		
		if(info.containsKey("addressLineDetail")) {
			addressLineDetailObject = (JSONObject) info.get("addressLineDetail");
			 addresInfo.setAddressLine1(addressLineDetailObject.get("line1").toString());
			 addresInfo.setAddressLine2(addressLineDetailObject.get("line2").toString());
		}

		
		addressInformatoin.add(addresInfo);
		
		
	}
	public void validateAddress() {
		
		for(int t=0;t<addressInformatoin.size();t++) {
			
			//-->check if address validation is valid with one
			
			if(addressInformatoin.get(t).addressLine1 !="" || addressInformatoin.get(t).addressLine2 !=""  ) 
			{
				 
				//-->Check for valid postal code
				Pattern regPostal = Pattern.compile("^[0-9]*$");
			      
			    Matcher checkPostal = regPostal.matcher(addressInformatoin.get(t).postalCodeData);
			      
				if(checkPostal.find()) {
					
					//-->check for a country
					if(!addressInformatoin.get(t).countryName.isEmpty()) {
						
						
						//-->check for ZA code
						if(addressInformatoin.get(t).countryCode.toLowerCase()=="za") {
							
							//-->check if province is available
							if(!addressInformatoin.get(t).provinceName.isEmpty()) {
								
								addValidAddress(addressInformatoin.get(t));
							}
						}else {
							
								addValidAddress(addressInformatoin.get(t));
						}
					}
				}
			}
		}
		
		
	}

	public void addValidAddress(Address data) {
		
		validAddressInformatoin.add(data) ;

	}
	
	public void uploading() {
	

		if (FileObject.getSize() > 0) {
			String fileName = FileInformation.getFileNameFromPart(FileObject);
		
			try {
				
				if(replaceFile) {
					createFile(fileName);
				}else {
					if(!checkFile(fileName)) {
						createFile(fileName);
					}else {
						throw new Exception();
					}
				}
				
			}catch(Exception e) {
				
				
			}
		}
	}
	
	private void createFile(String fileName) throws IOException {
		
			File newFile = new File("c:\\"+ fileName);

			inputStream = FileObject.getInputStream();
			outputStream = new FileOutputStream(newFile);
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
		
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
				outputStream.close();
		
	}
	private boolean checkFile(String fileName) {
		
		File localStorage = new File("c:\\"+fileName);
		
		if(localStorage.exists()) {
			
			return true;
		}
	return false;
	}
}
