package br.bruno.TesteAutomatizado;


import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAutomatizado {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Bruno/Downloads/chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://192.168.4.103:8080/correntista/novo");
		WebElement nome = driver.findElement(By.id("nome"));
		nome.sendKeys("Bruno Severino Martins");
		
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("bsmartins1993@gmail.com");
	
		WebElement botaoSalvar = driver.findElement(By.id("button1"));
		botaoSalvar.click();
		//driver.close();
		/*WebElement campoDeTexto = driver.findElement(By.name("q"));
		campoDeTexto.sendKeys("javali");
		campoDeTexto.submit();
		driver.close();*/
	}
	
	@Test
	public void TesteSelecnium() {
		WebDriver driver = new ChromeDriver();
		driver.get("http://192.168.4.103:8080/correntista/");
		boolean achouEmail =driver.getPageSource().contains("bsmartins1993@gmail.com");
		assertTrue(achouEmail);}

}
