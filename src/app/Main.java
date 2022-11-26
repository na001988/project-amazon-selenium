package app;

import java.io.IOException;
import java.sql.SQLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Home;

public class Main {
	
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException, SQLException {
		
		try {
			
		driver = new ChromeDriver();
		ReadFile rf = new ReadFile(); //read file with constants
		Setup obj = new Setup(driver); // object of main layer logic
		Home p1 = new Home(driver); // object of UI page for automation 
		ConnectionDB cn = new ConnectionDB(); // object of database
		Long time =  Long.parseLong(rf.getString("timeOut")); //get value from JSON file
		obj.run(rf.getString("base_uri")); // open requested resource defined in JSON file

		//linear flow of actions on UI
		//get price of best deal #1 by category, name and price
		//go back

		p1.run_execution(1);
		p1.run_execution(2);
		p1.run_execution(3);
		
		Thread.sleep(time); // set timer only for visualization   
		driver.close();
		driver.quit();
		
		cn.getTableContent("t_products"); //print info from table in database
		
		}catch(Exception e) {
        	System.out.println(e.getMessage());
        	driver.close();
        }
	}
}

