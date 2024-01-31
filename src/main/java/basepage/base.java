package basepage;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class base {
WebDriver driver=null;
private String username,password;

@Parameters("browserName")
@BeforeTest
public void setup(String br) {

if(br.equalsIgnoreCase("chrome"))
{
WebDriverManager.chromedriver().setup();
driver=new ChromeDriver();
}
else if(br.equalsIgnoreCase("edge"))
{
WebDriverManager.edgedriver().setup();
driver=new EdgeDriver();
   }
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
driver.get(" https://opensource-demo.orangehrmlive.com/");
driver.manage().window().maximize();
}
@Test(priority=1)
void read() {
	ExcelFromExcell obj=new ExcelFromExcell();
	obj.readExcel();
	username=obj.LoginId;
	password=obj.password;
}
@Test(priority=2)
public void search() throws InterruptedException{
	

driver.findElement(By.name("username")).sendKeys(username);
driver.findElement(By.name("password")).sendKeys(password);
driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

//verify login button is enabled or not
boolean status1=driver.findElement(By.xpath("//button")).isEnabled();
System.out.println(status1);
Thread.sleep(7000);

}
@Test(priority=3)
//To check whether the dashboard URL is matched or not
public void dashboardUrl() throws InterruptedException {
 if(driver.getCurrentUrl().contains("dashboard"))
  System.out.println("Valid URL");
 else
  System.out.println("Invalid URL");
 Thread.sleep(2000);

}
@Test(priority=4)
// Navigating to the Admin tab
public void searchingJobs() throws InterruptedException {
 
 
	driver.findElement(By.xpath("//span[text()='Admin']")).click();
 
 Thread.sleep(2000);
}


@Test(priority=5)
public void checkingJobTitle() throws InterruptedException {
//Go to the Job tab and check �Job Titles� is there or not.
	driver.findElement(By.xpath("//span[normalize-space()='Job']")).click();

Thread.sleep(5000);
//Click on �Job Titles�

driver.findElement(By.xpath("//a[text()='Job Titles']")).click();
Thread.sleep(5000);
//Job title validation
//To check whether the jobTitle is present or not

boolean status=driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[1]/h6")).isDisplayed();

System.out.println(status);
 List<WebElement> jobs=driver.findElements(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div/div/div[2]"));
System.out.println("List of jobs");
for(WebElement i:jobs)
{
System.out.println(i.getText());
}


System.out.println(jobs.size());
  }

//adding job 
@Test(priority=6)
public void addingJob() throws InterruptedException{
	if(driver.getPageSource().contains("Automation Tester")){
		driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")).sendKeys("Automation Tester");
		
		Thread.sleep(2000);
		System.out.println("-----Automation Tester exists-----");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Cancel']")).click();
	} 
	else {
		System.out.println("-----Automation Tester doesn't exist so we are adding a AUOTAMATION TESTER into the list-----");
		driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")).sendKeys("Automation Tester");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
		Thread.sleep(2000);
		System.out.println("-----Automation Tester added successfully-----");
		
	}
}



@Test(priority=7)
public void logOut() throws InterruptedException {
   
   driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
   Thread.sleep(2000);
   driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
   Thread.sleep(2000);
   System.out.println("-----Successfully logged-Out from the OrangeHRM-----");
  }
 
@AfterTest
public void quit() {
driver.quit();
}




}
