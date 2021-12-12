package e2e.stepDefinitions.expensesOverview;
import e2e.support.config.ConfigUtil;
import io.cucumber.java8.En;
import no.sanchezrolfsen.framework.selenium.Browser;

public class ExpensesSteps implements En {
    private ExpensesPage expensesPage;
    public ExpensesSteps() {
        Given("^that I am on the main page$", () -> Browser.vanillaDriver().navigate().to(ConfigUtil.getConfig().getBaseUrl()));
        When("^I press on add new expense$", () -> {
            expensesPage = new ExpensesPage();
            expensesPage.addNewExpenseButton.click();
        });
    }
}
