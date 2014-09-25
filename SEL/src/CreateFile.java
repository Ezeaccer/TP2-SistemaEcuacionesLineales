import java.io.*;

public class CreateFile {
	private FileWriter file = null;
	private PrintWriter pw = null;
	
	public CreateFile(String filePath) throws Exception{
		file = new FileWriter(filePath);
		pw = new PrintWriter(file);
	}
	
	public void writeLine(String line){
		pw.println(line);
	}
	
	public void close(){
		if(pw!=null)
			pw.close();
	}
}