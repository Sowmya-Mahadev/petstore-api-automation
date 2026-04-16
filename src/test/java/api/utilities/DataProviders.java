package api.utilities;


	import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

	public class DataProviders {

	    // ================================
	    // 1. GET ALL DATA (2D ARRAY)
	    // ================================
	
	    @DataProvider(name = "Data")
	    public Object[][] getAllData() throws IOException {

	        String path = System.getProperty("user.dir") + "//testData/UserDetails.xlsx";
	        XLUtility xl = new XLUtility(path);

	        int rownum = xl.getRowCount("Sheet1");
	        int colcount = xl.getCellCount("Sheet1", 1);

	        List<Object[]> dataList = new ArrayList<>();

	        for (int i = 1; i <= rownum; i++) {

	            String userId = xl.getCellData("Sheet1", i, 0);

	            // ✅ Skip empty rows
	            if (userId == null || userId.trim().isEmpty()) {
	                continue;
	            }

	            Object[] rowData = new Object[colcount];

	            for (int j = 0; j < colcount; j++) {
	                rowData[j] = xl.getCellData("Sheet1", i, j);
	            }

	            dataList.add(rowData);
	        }

	        // ✅ Convert List → Array
	        Object[][] data = new Object[dataList.size()][colcount];

	        for (int i = 0; i < dataList.size(); i++) {
	            data[i] = dataList.get(i);
	        }

	        System.out.println("TOTAL VALID ROWS = " + data.length); // debug

	        return data;
	    }

	      

	    // ================================
	    // 2. GET SINGLE COLUMN DATA (Usernames)
	    // ================================
	    @DataProvider(name = "UserNames")
	    public String[] getUserNames() throws IOException {

	        String path = System.getProperty("user.dir") + "//testData/UserDetails.xlsx";

	        XLUtility xl = new XLUtility(path);

	        int rownum = xl.getRowCount("Sheet1");

	        List<String> namesList = new ArrayList<>();

	        for (int i = 1; i <= rownum; i++) {

	            String username = xl.getCellData("Sheet1", i, 1);

	            // ✅ Skip empty rows
	            if (username == null || username.trim().isEmpty()) {
	                continue;
	            }

	            namesList.add(username);
	        }

	        // Convert List → Array
	        String[] apidata = new String[namesList.size()];
	        return namesList.toArray(apidata);
	    }
	}	
	
	
	
	
	
	
	
	
	
	
	
	

