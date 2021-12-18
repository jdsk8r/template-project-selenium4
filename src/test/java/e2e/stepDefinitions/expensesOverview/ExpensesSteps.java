package e2e.stepDefinitions.expensesOverview;

import e2e.support.config.ConfigUtil;
import io.cucumber.java8.En;
import no.sanchezrolfsen.framework.selenium.Browser;

import java.time.LocalDateTime;

import static no.sanchezrolfsen.framework.selenium.SeleniumUtils.safeIsVisible;
import static org.assertj.core.api.Assertions.assertThat;

public class ExpensesSteps implements En {

    public ExpensesSteps() {
        Given("that I am on the main page", () -> Browser.vanillaDriver()
                .navigate()
                .to(ConfigUtil.getConfig().getBaseUrl()));
        When("I press on add new expense", () -> new ExpensesPage().addNewExpenseButton.click());
        Then("the form to add expense is visible", () -> assertThat(safeIsVisible(new NewExpenseForm().newExpenseForm)).isTrue());
        Then("title textbox is on the left of amount text box", () -> assertThat(new NewExpenseForm().isTitleToTheLeftOfAmount()).isTrue());
        Then("date textbox is below title textbox", () -> assertThat(new NewExpenseForm().isDateBelowOfTitle()).isTrue());
        When("I enter \"{}\" as title", (String title) -> new NewExpenseForm().newExpenseTitle.sendKeys(title));
        When("I enter {int} as amount", (Integer amount) -> new NewExpenseForm().newExpenseAmount.sendKeys(amount.toString()));
        When("I enter today as date", () -> new NewExpenseForm().newExpenseDate.sendKeys(LocalDateTime.now().format(ConfigUtil.getConfig().getStandardDateFormat())));
        When("press on add expense", () -> new NewExpenseForm().addExpense.click());
        Then("expense \"{}\" is visible on overview", (String title) -> assertThat(new ExpensesPage().existExpense(title)).isTrue());
    }
}
