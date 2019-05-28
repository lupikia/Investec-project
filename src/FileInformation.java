import javax.servlet.http.Part;

public class FileInformation {


	public static String getFileNameFromPart(Part file) {
		
		final String Headers = file.getHeader("content-disposition");
		for (String data : Headers.split(";")) {
			if (data.trim().startsWith("filename")) {
				String getName = data.substring(data.indexOf('=') + 1).trim().replace("\"", "");
				
				String [] temp_data= getName.split("\\\\");
				String name = temp_data[temp_data.length -1];
				
				return name;
			}
		}
		
		String [] temp_data= Headers.split("\\\\");
		String name = temp_data[temp_data.length -1];
		
		return name;
	}
}
