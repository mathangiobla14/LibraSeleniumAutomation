package testcases;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import base.BaseClass;
import pages.RunBackGroundjobTestPage;

public class RunBackGroundJobTest extends BaseClass {
	
	RunBackGroundjobTestPage runBackGroundJobTestPage;
	
	public RunBackGroundJobTest() {
		super();
	}

	
	public void setup() {
		initialization();
		runBackGroundJobTestPage = new RunBackGroundjobTestPage();
		
		
		
		
		
		WebElement pageTitle = driver.findElement(By.Class("page-header"));
		Assert.assertEquals(pageTitle, "Recurring Jobs");
		
		
		
		WebElement table = driver.findElement(By.CSS("table"));
		List<WebElement> rows = driver.findElements(By.CSS("tbody tr"));
		
		for (WebElement row : rows) {
			WebElement col = row.findElement(By.xpath("//td[contains(text()='FileImportCanada')"));
		}
		
		WebElement colToCheck = col.findElement(By.xpath("input[type='checkbox']"));
		
		
		System.out.println(scolToCheck.isEnabled());
		
		WebElement col = driver.findElement(By.text("Trigger now"));
		col.click();
		
		
		driver.findElement(By.css("ul li a[href='/hangfire/jobs/enqueued']")).click();
		
		
		WebElement jobs = driver.findElements(By.css("div#stats a"));
		
		String [] jobNameArray =new String[jobs.size()];
		

		int i=0;

		//Storing List elements text into String array
		for(WebElement jobName: jobs)
		{
		   jobNameArray[i]=jobName.getText();
		   i++;
		}
		
		
		WelElement jobMetric = driver.findElement(By.xpath("//div[@id='stats']/ a/span"));
				
String [] jobCountArray =new String[jobMetric.size()];
		

		int j=0;

		//Storing List elements text into String array
		for(WebElement jobCount: jobs)
		{
			jobCountArray[j]=jobCount.getText();
		   j++;
		}
		
Map<String, String> jobMap = new LinkedHashMap<String, String>();
	
for(int k=0;k<jobCountArray.length;k++) {
	jobMap.put(jobNameArray[k], jobCountArray[k]);
}
	
System.out.println(jobMap);
		
		
		String noOfProcJobs = jobMap.get("Processing");
		
		WebElement processingJob = driver.findElement(By.css("//div[@id='stats' and text()='Processing']")).click();
		
		Assert.assertEquals(processingJob, "Processing Jobs");
		WebElement alertInfo = driver.findElement(By.css("div[class='alert alert-info1]"));
		int count =0;
		while (noOfProcJobs>0) {
			int procJ0bCount = driver.findElement(By.css("span[data-metric='processing:count']")).getText();
			if(procJ0bCount>0)
				count++;
			else
				procJ0bCount=0; 
			break;
			
			new WebDriverWait(driver, 8000).until(ExpectedConditions.visibilityOfElementLocated(alertInfo));
			
		}
		
		if(alertInfo){
			Assert.assertEquals(alertInfo.getText(), "No jobs are being processed right now.");
			System.out.println("Job Succeeded");
		}else {
			System.out.println("Job Failed");
		}
		
		
		
	}
}
