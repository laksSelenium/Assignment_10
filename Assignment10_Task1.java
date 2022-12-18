//Task - Create a course
//Open https://ineuron-courses.vercel.app/login
//Login with below credentials
//ineuron@ineuron.ai
//Ineuron
//Mouse Hover on manage
//Click on Manage courses
//Click on add new course
//Fill all details
//Verify course has been added
//Delete created course
//Verify course has been deleted
//Logout from application

package assignment_10;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Assignment10_Task1 {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://ineuron-courses.vercel.app/login");
		Thread.sleep(2000);

		WebElement email = driver.findElement(By.id("email1"));
		WebElement pwd = driver.findElement(By.id("password1"));
		WebElement signIn = driver.findElement(By.className("submit-btn"));

		email.sendKeys("ineuron@ineuron.ai");
		pwd.sendKeys("ineuron");
		signIn.click();
		Thread.sleep(5000);

		// Mousehover on manage tab
		WebElement manageTab = driver
				.findElement(By.xpath("//div[contains(@class,'item-manage')]//span[text()='Manage']"));

		Actions act = new Actions(driver);
		act.moveToElement(manageTab).build().perform();
		Thread.sleep(5000);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// List of Manage tab

		List<WebElement> manageTabs = driver.findElements(By.xpath("//div[contains(@class,'menu-item-hover')]//a"));
		for (byte i = 0; i < manageTabs.size(); i++) {
			String reqdSubTabText = manageTabs.get(i).getAttribute("href");
			System.out.println(reqdSubTabText);
			if (reqdSubTabText.contains("course/manage")) {
				js.executeScript("arguments[0].click();", manageTabs.get(i));
				break;
			}
		}

		Thread.sleep(5000);
		String getCurrentUrl = driver.getCurrentUrl();
		if (getCurrentUrl.contains("course/manage")) {
			System.out.println("Landed on manage course page and hence can click on add course button");

			// click on manage so that houseover tabs are set to default

			WebElement addNewCourse = driver.findElement(By.xpath("//button[contains(text(),'Add New Course')]"));

			js.executeScript("arguments[0].click();", addNewCourse);
			Thread.sleep(2000);

			driver.findElement(By.id("thumbnail")).sendKeys("C:\\Users\\Admin\\Desktop\\cy.jpg");

			// enter course name
			driver.findElement(By.id("name")).sendKeys("Cypress");

			driver.findElement(By.id("description")).sendKeys("Cypress Framework");

			driver.findElement(By.id("instructorNameId")).sendKeys("Mukesh Otwani");

			driver.findElement(By.id("price")).clear();
			driver.findElement(By.id("price")).sendKeys("5000");

			// setting satrt date

			WebElement startDate = driver.findElement(By.name("startDate"));
			startDate.click();
			Thread.sleep(1000);

			while (driver.findElement(By.xpath("//div[contains(@class,'current-month')]")).getText().contains("2022")) {
				driver.findElement(By.xpath("//button[contains(@class,'datepicker__navigation')]")).click();
			}

			while (!driver.findElement(By.xpath("//div[contains(@class,'current-month')]")).getText()
					.equalsIgnoreCase("February 2023")) {
				driver.findElement(By.xpath("//button[contains(@class,'datepicker__navigation--next')]")).click();
			}

			// set date as 10th Feb
			List<WebElement> days = driver.findElements(By.xpath("//div[contains(@class,'datepicker__week')]//div"));
			for (WebElement day : days) {
				if (day.getText().equalsIgnoreCase("10")) {
					day.click();
					Thread.sleep(2000);
					break;
				}
			}

			// setting end date

			WebElement endDate = driver.findElement(By.name("endDate"));
			endDate.click();
			Thread.sleep(1000);

			while (driver.findElement(By.xpath("//div[contains(@class,'current-month')]")).getText().contains("2022")) {
				driver.findElement(By.xpath("//button[contains(@class,'datepicker__navigation')]")).click();
			}

			while (!driver.findElement(By.xpath("//div[contains(@class,'current-month')]")).getText()
					.equalsIgnoreCase("June 2023")) {
				driver.findElement(By.xpath("//button[contains(@class,'datepicker__navigation--next')]")).click();
			}

			// set date as 20th June
			List<WebElement> enddays = driver.findElements(By.xpath("//div[contains(@class,'datepicker__week')]//div"));
			for (WebElement day : enddays) {
				if (day.getText().equalsIgnoreCase("20")) {
					day.click();
					Thread.sleep(5000);
					break;
				}
			}

			// choose category
			driver.findElement(By.className("menu-btn")).click();

			List<WebElement> categories = driver.findElements(By.cssSelector(".menu-items button"));
			for (WebElement cat : categories) {
				if (cat.getText().contains("Testing")) {
					cat.click();
					break;
				}
			}

			// save button
			driver.findElement(By.xpath("//div[contains(@class,'modal-footer')]//button[text()='Save']")).click();
			Thread.sleep(5000);

			// Delete course

			List<WebElement> courses = driver
					.findElements(By.xpath("//table[contains(@class,'courses-table')]//tr//td[2]"));
			System.out.println("Number of courses before deleting " + courses.size());
			byte courseCountBeforeDeleting = (byte) courses.size();
			byte courseCountAfterDeleting = 0;

			for (byte i = 0; i < courses.size(); i++) {
				// System.out.println(courses.get(i).getText());
				if (courses.get(i).getText().contains("Cypress")) {
					// Validate the other details
					String coursePrice = driver
							.findElement(
									By.xpath("(//table[contains(@class,'courses-table')]//tr//td[3])[" + (i + 1) + "]"))
							.getText();
					String instructor = driver
							.findElement(
									By.xpath("(//table[contains(@class,'courses-table')]//tr//td[4])[" + (i + 1) + "]"))
							.getText();
					String category = driver
							.findElement(
									By.xpath("(//table[contains(@class,'courses-table')]//tr//td[8])[" + (i + 1) + "]"))
							.getText();

					if (coursePrice.contains("5000") && (instructor.contains("Mukesh"))
							&& (category.contains("Testing"))) {
						System.out.println("Created course is found and hence can be deleted");

						WebElement reqdDeleteButton = driver.findElement(By.xpath(
								"(//table[contains(@class,'courses-table')]//tr//td[11])[" + (i + 1) + "]//button"));

						if (reqdDeleteButton.isEnabled()) {
							System.out.println("Delete button found for the course to be deleted");
							js.executeScript("arguments[0].click();", reqdDeleteButton);
							Thread.sleep(10000);
							break;
						}

					}

				}

			}

			try {
				courses = driver.findElements(By.xpath("//table[contains(@class,'courses-table')]//tr//td[2]"));
				System.out.println("Number of courses After deleting in try" + courses.size());
				courseCountAfterDeleting = (byte) courses.size();
			} catch (Exception e) {

				courses = driver.findElements(By.xpath("//table[contains(@class,'courses-table')]//tr//td[2]"));
				System.out.println("Number of courses After deleting in catch " + courses.size());
				courseCountAfterDeleting = (byte) courses.size();

			} finally {
				if (courseCountAfterDeleting < courseCountBeforeDeleting) {
					System.out.println("Course is successfully deleted");
					driver.quit();
				}

			}

		}

	}

}
