package e2e.stepDefinitions.expensesOverview;

import no.sanchezrolfsen.framework.selenium.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class NewExpenseForm {
    private final WebDriver driver;
    @FindBy(css = "[cy-data-selector='new-expense-form']")
    WebElement newExpenseForm;
    @FindBy(css = "[cy-data-selector='new-expense-title']")
    WebElement newExpenseTitle;
    @FindBy(css = "[cy-data-selector='new-expense-amount']")
    WebElement newExpenseAmount;
    @FindBy(css = "[cy-data-selector='new-expense-date']")
    WebElement newExpenseDate;
    @FindBy(css = "[cy-data-selector='add-expense']")
    WebElement addExpense;


    public NewExpenseForm() {
        driver = Browser.vanillaDriver();
        PageFactory.initElements(driver, this);
    }

    boolean isTitleToTheLeftOfAmount() {
        return driver.findElement(with(By.cssSelector("[cy-data-selector='new-expense-title']")).toLeftOf(By.cssSelector("[cy-data-selector='new-expense-amount']"))).isDisplayed();
    }

    boolean isDateBelowOfTitle() {
        return driver.findElement(with(By.cssSelector("[cy-data-selector='new-expense-date']")).below(By.cssSelector("[cy-data-selector='new-expense-title']"))).isDisplayed();
    }
}
