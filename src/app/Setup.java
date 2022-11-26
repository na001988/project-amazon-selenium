package app;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Setup {
	
	protected WebDriver driver;
	private LocatorType locator;
	private ReadFile rf = new ReadFile();
	ConnectionDB cn = new ConnectionDB();
	private Long time =  Long.parseLong(rf.getString("timeOut"));
	List<String> l_name = new ArrayList<String>(); 
	List<String> l_price = new ArrayList<String>();
	List<String> l_category = new ArrayList<String>();
	
	public Setup(WebDriver driver) {
		this.driver=driver;
	}
	
	public void run(String val) throws InterruptedException {
		try {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.get(val);
        driver.manage().window().maximize();
		}catch(NoSuchElementException e) {
			System.out.println("Error on run() > "+e.getMessage());
			driver.close();
		}
	}
	
	public WebElement locateWebElement(WebDriver obj, LocatorType l, String info) throws InterruptedException {
		l_category.add(info);
		try {
		WebElement dynamicElement;
		switch(l) {
		case XPATH -> dynamicElement = obj.findElement(By.xpath(info));
		case ID -> dynamicElement = obj.findElement(By.id(info));
		case LINK_TEXT -> dynamicElement = obj.findElement(By.linkText(info));
		case CSS_SELECTOR -> dynamicElement = obj.findElement(By.cssSelector(info));
		default -> throw new TypeNotPresentException(locator.getClass().getName(),null);
		}
		
		Thread.sleep(time); 
		return dynamicElement;
		
		}catch(StaleElementReferenceException | ElementNotInteractableException | UnhandledAlertException ex) {
			System.out.println("Error on locateWebElement() > "+ex.getStackTrace());
			driver.close();
			return locateWebElement(driver, l, info);
		}
	}

	
	public void PerformAction(WebElement we, WebDriverAction act, String data) {
		String var_name="x1";
		String var_price="x2";
		String var_category="x3";
		Double price=0.0;
		try {
			switch(act) {
				case CLICK -> {
					we.click();
				}
				case CLEAR -> {
					we.click();
					we.clear();
				}
				case GETTEXT -> {
					var_price = we.getText();
					var_name = we.getAttribute("alt");
					l_name.add(var_name);
					l_price.add(var_price);
				} 
				default -> {
					we.wait(100);
				}
			}
			
			//manipulate and validate strings 
			if (l_price.size()>0 && l_price!=null) var_price = l_price.get(0).replace("$", "");
			if (var_price.length()>2) price = Double.parseDouble(var_price);
			var_category = l_category.get(0);
			
			//insert information in database
			cn.setTableData("t_products",l_name.get(1),price,var_category);
			
			}catch(Exception e) {
				if(!e.getMessage().contains("bounds")) System.out.println("Warning on PerformAction(x,y,z) > "+e.getMessage());
			}
	}
	
    public enum LocatorType
    {
        ID,
        NAME,
        CLASS_NAME,
        XPATH,
        TAG_NAME,
        CSS_SELECTOR,
        LINK_TEXT,
        PARTIAL_LINK_TEXT,
    }
	
    public enum WebDriverAction
    {
        CLEAR,
        CLICK,
        INPUT,
        SELECT,
        GETTEXT
    }
    
}
