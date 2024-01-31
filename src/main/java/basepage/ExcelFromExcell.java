package basepage;

import java.io.IOException;

public class ExcelFromExcell {

	public String LoginId,password;
	public void readExcel() {
		try {
			String file="C:\\Users\\2304018\\eclipse-workspace\\seleniumproj\\test-data\\Login test-data.xlsx";
			int rows=ExcelUtility.getRowCount(file,"Sheet1");
			for(int i=1;i<=rows;i++) {
				LoginId=ExcelUtility.getCellData(file,"Sheet1",i,0);
				password=ExcelUtility.getCellData(file,"Sheet1",i,1);
				
				
				
			}
			System.out.println("loginId :"+LoginId);
			System.out.println("Password :"+password);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
