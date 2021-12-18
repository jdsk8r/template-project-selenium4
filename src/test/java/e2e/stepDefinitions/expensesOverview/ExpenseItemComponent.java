package e2e.stepDefinitions.expensesOverview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ExpenseItemComponent {
    static final By SELECTOR = By.cssSelector("[cy-data-selector='expense-item']");
    private final WebElement element;
    final By expenseItemDescription = By.cssSelector("[cy-data-selector='expenseDescription']");

    ExpenseItemComponent(WebElement element) {
        this.element = element;
    }

    String getDescription() {
        return element.findElement(expenseItemDescription).getText();
    }
}
