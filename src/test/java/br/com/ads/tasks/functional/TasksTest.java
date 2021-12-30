package br.com.ads.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
	//	WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.6:4444/wd/hub"), cap);
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveSalvarTarefasComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
		
		// clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		
		// escrever a discrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		// escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("28/12/2022");
		
		// clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		// validar msg de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", message);
		} finally {
			
			// fechar o Browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naodeveSalvarTarefasSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
		
		// clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		
		// escrever a discrição
		
		// escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("27/12/2022");
		
		// clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		// validar msg de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description", message);
		} finally {
			
			// fechar o Browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naodeveSalvarTarefasSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
		
		// clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		
		// escrever a discrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		// escrever a data
		
		// clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		// validar msg de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", message);
		} finally {
			
			// fechar o Browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naodeveSalvarTarefasComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
		
		// clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		
		// escrever a discrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		// escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("27/12/2010");
		
		// clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		// validar msg de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", message);
		} finally {
			
			// fechar o Browser
			driver.quit();
		}
		
	}
	
	

}
