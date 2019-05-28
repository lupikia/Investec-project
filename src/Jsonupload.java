import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
@ManagedBean
@ViewScoped
public class Jsonupload {
	
	private String name;
	private Part FileObject;
	private InputStream inputStream;
	private OutputStream outputStream;
	private Boolean replaceFile;
	private List<Address> addressInformatoin;
	
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

	
	public void getAddress() {
		
		 JSONParser parseData = new JSONParser();
		try (FileReader reader = new FileReader("c:\\addresses.json"))
        {
            
 
        } catch (Exception e) {
       
        	
        } 
		
	}
	public void validateAddress() {
		
		
		
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
