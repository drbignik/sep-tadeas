package selenium_tests.src.po;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
  private final WebDriver driver;
  private final WebDriverWait wait;

  @FindBy(id = "username")
  private WebElement usernameInput;

  @FindBy(id = "password")
  private WebElement passwordInput;

  @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/form/button[1]")
  private WebElement signInButton;

  @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/form/button[2]")
  private WebElement resetButton;

  @FindBy(xpath = "table")
  private WebElement tasksTable;

  public LoginPage(final WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    this.wait = new WebDriverWait(driver, 30);
  }

  public void goTo() {
    this.driver.get("http://localhost:3000/login");
  }

  public void login(String username, String password) throws InterruptedException {
    this.usernameInput.clear();
    this.passwordInput.clear();

    this.usernameInput.sendKeys(username);
    this.passwordInput.sendKeys(password);
    System.out.println("Sent username: " + username);
    System.out.println("Sent password: " + password);
    wait.until(ExpectedConditions.elementToBeClickable(this.signInButton));
    this.signInButton.click();
  }

  public WebElement getTable() {
    return this.tasksTable;
  }

  public boolean isAlertPresent() {
    try {
      wait.until(ExpectedConditions.alertIsPresent());
      driver.switchTo().alert();
      return true;
    }   // try
    catch (NoAlertPresentException Ex) {
      return false;
    }   // catch
  }   // isAlertPresent()

  public void confirmAlert() {
    driver.switchTo().alert().accept();
  }

}
