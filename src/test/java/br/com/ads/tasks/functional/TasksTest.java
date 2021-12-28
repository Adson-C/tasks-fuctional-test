package br.com.ads.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveSalvarTarefasComSucesso() {
		WebDriver driver = acessarAplicacao();
		
		try {
		
		// clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		
		// escrever a discrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		// escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("27/12/2021");
		
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
	public void naodeveSalvarTarefasSemDescricao() {
		WebDriver driver = acessarAplicacao();
		
		try {
		
		// clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		
		// escrever a discrição
		
		// escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("27/12/2021");
		
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
	public void naodeveSalvarTarefasSemData() {
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
	public void naodeveSalvarTarefasComDataPassada() {
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
