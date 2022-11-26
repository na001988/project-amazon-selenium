package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import app.ReadFile;
import app.Setup;
import app.Setup.*;

public class Home{

	protected WebDriver driver;
	private Setup s = new Setup(driver);
	private ReadFile rf = new ReadFile();
	
	protected WebElement label;
	protected String price =  rf.getString("target_price_locator");
	protected String name =  rf.getString("target_name_locator");
	
	public Home(WebDriver driver) {
		this.driver=driver;
	}
	
    public void run_execution(int option) throws InterruptedException {
    	Home p1 = new Home(driver);
    	switch(option){
    	case 1 -> { 
    		p1.find_do("Best Sellers","", WebDriverAction.CLICK, LocatorType.LINK_TEXT);
    		p1.find_do("Amazon Devices & Accessories","",WebDriverAction.CLICK, LocatorType.LINK_TEXT);
    		p1.find_do(price, "$",WebDriverAction.GETTEXT,LocatorType.CSS_SELECTOR);
    		p1.find_do(name,"",WebDriverAction.GETTEXT,LocatorType.CSS_SELECTOR);
    		driver.navigate().back();
    	}
    	case 2 -> { 
    		p1.find_do("Amazon Launchpad","",WebDriverAction.CLICK, LocatorType.LINK_TEXT);
    		p1.find_do(price, "$",WebDriverAction.GETTEXT,LocatorType.CSS_SELECTOR);
    		p1.find_do(name,"",WebDriverAction.GETTEXT,LocatorType.CSS_SELECTOR);
    		driver.navigate().back();
    	}
    	case 3 -> { 
    		p1.find_do("Amazon Renewed","",WebDriverAction.CLICK, LocatorType.LINK_TEXT);
    		p1.find_do(price, "$",WebDriverAction.GETTEXT,LocatorType.CSS_SELECTOR);
    		p1.find_do(name,"",WebDriverAction.GETTEXT,LocatorType.CSS_SELECTOR);
    		driver.navigate().back();
    	}
    	default -> p1.wait();
    	}
    }
	

	public void find_do(String x, String y, WebDriverAction z, LocatorType l) throws InterruptedException {
		s.PerformAction(findByLinkText(x, l), z, y);
	}

	
	private WebElement findByLinkText(String x, LocatorType l) throws InterruptedException {
		return label = s.locateWebElement(driver, l, x);
	} 


}
