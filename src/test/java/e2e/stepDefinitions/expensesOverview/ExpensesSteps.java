package e2e.stepDefinitions.expensesOverview;

import e2e.support.config.ConfigUtil;
import io.cucumber.java8.En;
import no.sanchezrolfsen.framework.selenium.Browser;

import static no.sanchezrolfsen.framework.selenium.SeleniumUtils.safeIsVisible;
import static org.assertj.core.api.Assertions.assertThat;

public class ExpensesSteps implements En {
    private ExpensesPage expensesPage;
    private NewExpenseForm newExpenseForm;

    public ExpensesSteps() {
        Given("^that I am on the main page$", () -> {
            Browser.vanillaDriver()
                    .navigate()
                    .to(ConfigUtil.getConfig().getBaseUrl());
        });
        When("^I press on add new expense$", () -> {
            expensesPage = new ExpensesPage();
            expensesPage.addNewExpenseButton.click();
        });
        Then("^the form to add expense is visible$", () -> {
            newExpenseForm = new NewExpenseForm();
            assertThat(safeIsVisible(newExpenseForm.newExpenseForm)).isTrue();
        });
        Then("^title textbox is on the left of amount text box$", () -> {
            newExpenseForm = new NewExpenseForm();
            assertThat(newExpenseForm.isTitleToTheLeftOfAmount()).isTrue();
        });
    }
}
