package e2e.stepDefinitions.expensesOverview;

import no.sanchezrolfsen.framework.selenium.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExpensesPage {
    private final WebDriver driver;
    @FindBy(css = "[cy-data-selector='add-new-expense']")
    WebElement addNewExpenseButton;

    ExpensesPage() {
        driver = Browser.vanillaDriver();
        PageFactory.initElements(driver, this);
    }

    boolean existExpense(String title) {
        return driver.findElements(ExpenseItemComponent.SELECTOR).stream()
                .map(ExpenseItemComponent::new)
                .filter(ei -> ei.getDescription().equals(title))
                .toList()
                .size() > 0;
    }
}
