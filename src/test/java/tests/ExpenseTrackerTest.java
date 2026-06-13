package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTrackerTest extends BaseTest {

    // ─── LOCATORS ───
    private final By todayBtn = By.xpath("//android.widget.TextView[contains(@text,'oday')]");
    private final By thisMonthBtn    = By.xpath("//android.widget.TextView[@text='This Month']");
    private final By thisYearBtn     = By.xpath("//android.widget.TextView[@text='This Year']");
    private final By spendSoFar      = By.xpath("//android.widget.TextView[contains(@text,'Spend So Far')]");
    private final By noExpenseYet    = By.xpath("//android.widget.TextView[@text='No Expense Yet']");
    private final By fabButton = By.xpath("//*[@content-desc='Add']");
    private final By summaryTab      = By.xpath("//android.widget.TextView[@text='Summary']");
    private final By homeTab         = By.xpath("//android.widget.TextView[@text='Home']");
    private final By addExpenseTitle = By.xpath("//android.widget.TextView[@text='Add New Expense']");
    private final By amountField     = By.xpath("//android.widget.EditText[1]");
    private final By descField       = By.xpath("//android.widget.EditText[2]");
    private final By addExpenseBtn   = By.xpath("//android.widget.TextView[@text='Add Expense']");
    private final By yourSummary     = By.xpath("//android.widget.TextView[@text='Your Summary']");
    private final By monthBtn        = By.xpath("//android.widget.TextView[@text='Month']");
    private final By yearBtn         = By.xpath("//android.widget.TextView[@text='Year']");
    private final By appTitle        = By.xpath("//android.widget.TextView[@text='Expense Tracker']");
    private final By closeBtn        = By.xpath("//android.widget.TextView[@text='×']");

    // ────────────────────────────────────────────────────────────
    private WebDriverWait getWait()  {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private void waitForHome() throws InterruptedException {
        Thread.sleep(2000);
    }

    // ═══════════════════════════════════════════════════════
    // SMOKE TESTS (TC001-TC005)
    // ═══════════════════════════════════════════════════════

    @Test
    @DisplayName("TC001: App launches successfully")
    public void tc001_appLaunchesSuccessfully() {
        assertTrue(driver.findElement(appTitle).isDisplayed(),
                "App title should be visible");
    }

    @Test
    @DisplayName("TC002: Home screen loads")
    public void tc002_homeScreenLoads() {
        assertTrue(driver.findElement(spendSoFar).isDisplayed(),
                "Spend So Far should be visible");
    }

    @Test
    @DisplayName("TC003: FAB button visible")
    public void tc003_fabButtonVisible() {
        assertTrue(driver.findElement(fabButton).isDisplayed(),
                "FAB button should be visible");
    }

    @Test
    @DisplayName("TC004: Summary tab visible")
    public void tc004_summaryTabVisible() {
        assertTrue(driver.findElement(summaryTab).isDisplayed(),
                "Summary tab should be visible");
    }

    @Test
    @DisplayName("TC005: No Expense Yet shown on empty state")
    public void tc005_noExpenseYetShown() {
        assertTrue(driver.findElement(noExpenseYet).isDisplayed(),
                "No Expense Yet should be shown");
    }

    // ═══════════════════════════════════════════════════════
    // FUNCTIONAL VALID TESTS (TC006-TC025)
    // ═══════════════════════════════════════════════════════

    @Test
    @DisplayName("TC006: Click Today filter")
    public void tc006_clickTodayFilter() throws InterruptedException {
        driver.findElement(todayBtn).click();
        Thread.sleep(1000);
        assertTrue(driver.findElement(spendSoFar).isDisplayed());
    }

    @Test
    @DisplayName("TC007: Click This Month filter")
    public void tc007_clickThisMonthFilter() throws InterruptedException {
        driver.findElement(thisMonthBtn).click();
        Thread.sleep(1000);
        assertTrue(driver.findElement(spendSoFar).isDisplayed());
    }

    @Test
    @DisplayName("TC008: Click This Year filter")
    public void tc008_clickThisYearFilter() throws InterruptedException {
        driver.findElement(thisYearBtn).click();
        Thread.sleep(1000);
        assertTrue(driver.findElement(spendSoFar).isDisplayed());
    }

    @Test
    @DisplayName("TC009: Open Add Expense form")
    public void tc009_openAddExpenseForm() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(fabButton).click();
        Thread.sleep(3000);
        assertTrue(driver.findElement(addExpenseTitle).isDisplayed(),
                "Add New Expense form should open");
    }

    @Test
    @DisplayName("TC010: Add expense with valid data")
    public void tc010_addExpenseWithValidData() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(fabButton).click();
        Thread.sleep(2000);
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys("100");
        driver.findElement(descField).clear();
        driver.findElement(descField).sendKeys("Groceries");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(2000);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC011: Add expense with large amount")
    public void tc011_addExpenseWithLargeAmount() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys("999999");
        driver.findElement(descField).clear();
        driver.findElement(descField).sendKeys("Big Purchase");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC012: Add multiple expenses")
    public void tc012_addMultipleExpenses() throws InterruptedException {
        Thread.sleep(2000);
        for (int i = 1; i <= 3; i++) {
            driver.findElement(fabButton).click();
            Thread.sleep(3000);
            driver.findElement(amountField).clear();
            driver.findElement(amountField).sendKeys(String.valueOf(i * 10));
            Thread.sleep(1000);
            driver.findElement(descField).clear();
            driver.findElement(descField).sendKeys("Expense " + i);
            Thread.sleep(1000);
            driver.findElement(addExpenseBtn).click();
            Thread.sleep(3000);
        }
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }
    @Test
    @DisplayName("TC013: Navigate to Summary screen")
    public void tc013_navigateToSummaryScreen() throws InterruptedException {
        driver.findElement(summaryTab).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(yourSummary).isDisplayed(),
                "Your Summary should be displayed");
    }

    @Test
    @DisplayName("TC014: Click Month tab in Summary")
    public void tc014_clickMonthTabInSummary() throws InterruptedException {
        driver.findElement(summaryTab).click();
        Thread.sleep(1500);
        driver.findElement(monthBtn).click();
        Thread.sleep(1000);
        assertTrue(driver.findElement(yourSummary).isDisplayed());
    }

    @Test
    @DisplayName("TC015: Click Year tab in Summary")
    public void tc015_clickYearTabInSummary() throws InterruptedException {
        driver.findElement(summaryTab).click();
        Thread.sleep(1500);
        driver.findElement(yearBtn).click();
        Thread.sleep(1000);
        assertTrue(driver.findElement(yourSummary).isDisplayed());
    }

    @Test
    @DisplayName("TC016: Navigate back to Home from Summary")
    public void tc016_navigateBackToHomeFromSummary() throws InterruptedException {
        driver.findElement(summaryTab).click();
        Thread.sleep(1500);
        driver.findElement(homeTab).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }
    @Test
    @DisplayName("TC017: Close Add Expense form")
    public void tc017_closeAddExpenseForm() throws InterruptedException {
        waitForHome();
        driver.findElement(fabButton).click();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(1000);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }
    @Test
    @DisplayName("TC018: Add expense with description only")
    public void tc018_addExpenseWithDescriptionOnly() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(descField).clear();
        driver.findElement(descField).sendKeys("Test Description");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC019: Verify date is displayed on home screen")
    public void tc019_verifyDateDisplayedOnHomeScreen() {
        assertTrue(driver.findElement(
                        By.xpath("//android.widget.TextView[contains(@text,'2026')]"))
                .isDisplayed());
    }

    @Test
    @DisplayName("TC020: Switch filters multiple times")
    public void tc020_switchFiltersMultipleTimes() throws InterruptedException {
        driver.findElement(thisMonthBtn).click();
        Thread.sleep(500);
        driver.findElement(thisYearBtn).click();
        Thread.sleep(500);
        driver.findElement(todayBtn).click();
        Thread.sleep(500);
        assertTrue(driver.findElement(spendSoFar).isDisplayed());
    }

    @Test
    @DisplayName("TC021: FAB opens correct form")
    public void tc021_fabOpensCorrectForm() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(addExpenseTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC022: Amount field accepts numbers")
    public void tc022_amountFieldAcceptsNumbers() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).sendKeys("250");
        assertTrue(driver.findElement(amountField).isDisplayed());
    }

    @Test
    @DisplayName("TC023: Description field accepts text")
    public void tc023_descriptionFieldAcceptsText() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(descField).sendKeys("Test Expense");
        assertTrue(driver.findElement(descField).isDisplayed());
    }

    @Test
    @DisplayName("TC024: Summary shows No Expense Yet when empty")
    public void tc024_summaryShowsEmptyState() throws InterruptedException {
        driver.findElement(summaryTab).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(noExpenseYet).isDisplayed());
    }

    @Test
    @DisplayName("TC025: Home tab navigation works")
    public void tc025_homeTabNavigationWorks() throws InterruptedException {
        driver.findElement(summaryTab).click();
        Thread.sleep(1000);
        driver.findElement(homeTab).click();
        Thread.sleep(1000);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    // ═══════════════════════════════════════════════════════
    // NEGATIVE TESTS (TC026-TC035)
    // ═══════════════════════════════════════════════════════

    @Test
    @DisplayName("TC026: Add expense with zero amount")
    public void tc026_addExpenseWithZeroAmount() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys("0");
        driver.findElement(descField).sendKeys("Zero Amount");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC027: Add expense with empty description")
    public void tc027_addExpenseWithEmptyDescription() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).sendKeys("50");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC028: Add expense with special characters")
    public void tc028_addExpenseWithSpecialCharacters() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).sendKeys("100");
        driver.findElement(descField).sendKeys("!@#$%^&*()");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC029: Add expense with very long description")
    public void tc029_addExpenseWithLongDescription() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).sendKeys("10");
        driver.findElement(descField).sendKeys("This is a very long description that exceeds normal length limits for testing purposes only");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC030: Close form without saving")
    public void tc030_closeFormWithoutSaving() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).sendKeys("500");
        driver.navigate().back();
        Thread.sleep(1000);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC031: Add expense with negative amount")
    public void tc031_addExpenseWithNegativeAmount() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).sendKeys("-100");
        driver.findElement(descField).sendKeys("Negative Test");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC032: Add expense with decimal amount")
    public void tc032_addExpenseWithDecimalAmount() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).sendKeys("99.99");
        driver.findElement(descField).sendKeys("Decimal Amount");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC033: Add expense with only spaces in description")
    public void tc033_addExpenseWithSpacesOnly() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).sendKeys("10");
        driver.findElement(descField).sendKeys("     ");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC034: Rapid tap on FAB button")
    public void tc034_rapidTapOnFabButton() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(500);
        driver.navigate().back();
        Thread.sleep(500);
        driver.findElement(fabButton).click();
        Thread.sleep(500);
        assertTrue(driver.findElement(addExpenseTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC035: Add expense with alphabets in amount")
    public void tc035_addExpenseWithAlphabetsInAmount() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).sendKeys("abc");
        driver.findElement(descField).sendKeys("Alpha Amount");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    // ═══════════════════════════════════════════════════════
    // UI TESTS (TC036-TC040)
    // ═══════════════════════════════════════════════════════

    @Test
    @DisplayName("TC036: App title is displayed")
    public void tc036_appTitleDisplayed() {
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC037: Filter buttons are displayed")
    public void tc037_filterButtonsDisplayed() {
        assertTrue(driver.findElement(todayBtn).isDisplayed());
        assertTrue(driver.findElement(thisMonthBtn).isDisplayed());
        assertTrue(driver.findElement(thisYearBtn).isDisplayed());
    }

    @Test
    @DisplayName("TC038: Bottom navigation is displayed")
    public void tc038_bottomNavigationDisplayed() {
        assertTrue(driver.findElement(homeTab).isDisplayed());
        assertTrue(driver.findElement(summaryTab).isDisplayed());
    }

    @Test
    @DisplayName("TC039: Spend So Far card is displayed")
    public void tc039_spendSoFarCardDisplayed() {
        assertTrue(driver.findElement(spendSoFar).isDisplayed());
    }

    @Test
    @DisplayName("TC040: FAB button is displayed")
    public void tc040_fabButtonDisplayed() {
        assertTrue(driver.findElement(fabButton).isDisplayed());
    }

    // ═══════════════════════════════════════════════════════
    // REGRESSION TESTS (TC041-TC045)
    // ═══════════════════════════════════════════════════════

    @Test
    @DisplayName("TC041: Home screen loads after restart")
    public void tc041_homeScreenLoadsAfterRestart() {
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC042: Add expense still works")
    public void tc042_addExpenseStillWorks() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(addExpenseTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC043: Summary navigation still works")
    public void tc043_summaryNavigationStillWorks() throws InterruptedException {
        driver.findElement(summaryTab).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(yourSummary).isDisplayed());
    }

    @Test
    @DisplayName("TC044: Filters still work")
    public void tc044_filtersStillWork() throws InterruptedException {
        driver.findElement(thisMonthBtn).click();
        Thread.sleep(1000);
        assertTrue(driver.findElement(spendSoFar).isDisplayed());
    }

    @Test
    @DisplayName("TC045: FAB still opens form")
    public void tc045_fabStillOpensForm() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(addExpenseTitle).isDisplayed());
    }

    // ═══════════════════════════════════════════════════════
    // PERFORMANCE TESTS (TC046-TC049)
    // ═══════════════════════════════════════════════════════

    @Test
    @DisplayName("TC046: App launch time under 3 seconds")
    public void tc046_appLaunchTimeUnder3Seconds() {
        long start = System.currentTimeMillis();
        assertTrue(driver.findElement(appTitle).isDisplayed());
        long end = System.currentTimeMillis();
        assertTrue((end - start) < 3000,
                "App should load within 3 seconds");
    }

    @Test
    @DisplayName("TC047: Add Expense form opens quickly")
    public void tc047_addExpenseFormOpensQuickly() throws InterruptedException {
        long start = System.currentTimeMillis();
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(addExpenseTitle).isDisplayed());
        long end = System.currentTimeMillis();
        System.out.println("Form open time: " + (end - start) + "ms");
    }

    @Test
    @DisplayName("TC048: Summary screen loads quickly")
    public void tc048_summaryScreenLoadsQuickly() throws InterruptedException {
        long start = System.currentTimeMillis();
        driver.findElement(summaryTab).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(yourSummary).isDisplayed());
        long end = System.currentTimeMillis();
        System.out.println("Summary load time: " + (end - start) + "ms");
    }

    @Test
    @DisplayName("TC049: Filter switch response time")
    public void tc049_filterSwitchResponseTime() throws InterruptedException {
        long start = System.currentTimeMillis();
        driver.findElement(thisMonthBtn).click();
        Thread.sleep(500);
        assertTrue(driver.findElement(spendSoFar).isDisplayed());
        long end = System.currentTimeMillis();
        System.out.println("Filter switch time: " + (end - start) + "ms");
    }

    // ═══════════════════════════════════════════════════════
    // LOAD TESTS (TC050-TC052)
    // ═══════════════════════════════════════════════════════

    @Test
    @DisplayName("TC050: Open and close form 10 times")
    public void tc050_openCloseForm10Times() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            driver.findElement(fabButton).click();
            Thread.sleep(800);
            driver.navigate().back();
            Thread.sleep(800);
        }
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC051: Switch filters 20 times")
    public void tc051_switchFilters20Times() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            driver.findElement(todayBtn).click();
            Thread.sleep(300);
            driver.findElement(thisMonthBtn).click();
            Thread.sleep(300);
            driver.findElement(thisYearBtn).click();
            Thread.sleep(300);
        }
        assertTrue(driver.findElement(spendSoFar).isDisplayed());
    }

    @Test
    @DisplayName("TC052: Navigate between tabs 15 times")
    public void tc052_navigateBetweenTabs15Times() throws InterruptedException {
        for (int i = 0; i < 15; i++) {
            driver.findElement(summaryTab).click();
            Thread.sleep(500);
            driver.findElement(homeTab).click();
            Thread.sleep(500);
        }
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    // ═══════════════════════════════════════════════════════
    // STRESS TESTS (TC053-TC055)
    // ═══════════════════════════════════════════════════════

    @Test
    @DisplayName("TC053: Add 10 expenses rapidly")
    public void tc053_add10ExpensesRapidly() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            driver.findElement(fabButton).click();
            Thread.sleep(1000);
            driver.findElement(amountField).clear();
            driver.findElement(amountField).sendKeys(String.valueOf(i * 5));
            driver.findElement(descField).clear();
            driver.findElement(descField).sendKeys("Stress " + i);
            driver.findElement(addExpenseBtn).click();
            Thread.sleep(1000);
        }
        assertTrue(driver.findElement(appTitle).isDisplayed(),
                "App should not crash after adding 10 expenses rapidly");
    }

    @Test
    @DisplayName("TC054: Rapid tab switching")
    public void tc054_rapidTabSwitching() throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            driver.findElement(summaryTab).click();
            Thread.sleep(200);
            driver.findElement(homeTab).click();
            Thread.sleep(200);
        }
        assertTrue(driver.findElement(appTitle).isDisplayed(),
                "App should not freeze during rapid tab switching");
    }

    @Test
    @DisplayName("TC055: App stability after multiple operations")
    public void tc055_appStabilityAfterMultipleOperations() throws InterruptedException {
        driver.findElement(todayBtn).click();
        Thread.sleep(300);
        driver.findElement(thisMonthBtn).click();
        Thread.sleep(300);
        driver.findElement(fabButton).click();
        Thread.sleep(1000);
        driver.navigate().back();
        Thread.sleep(500);
        driver.findElement(summaryTab).click();
        Thread.sleep(1000);
        driver.findElement(homeTab).click();
        Thread.sleep(500);
        assertTrue(driver.findElement(appTitle).isDisplayed(),
                "App should remain stable after multiple operations");
    }

    // ═══════════════════════════════════════════════════════
    // END TO END TESTS (TC056-TC060)
    // ═══════════════════════════════════════════════════════

    @Test
    @DisplayName("TC056: Full journey add and verify")
    public void tc056_fullJourneyAddAndVerify() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys("150");
        driver.findElement(descField).clear();
        driver.findElement(descField).sendKeys("Full Journey Test");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC057: Add expense and check summary")
    public void tc057_addExpenseAndCheckSummary() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys("200");
        driver.findElement(descField).clear();
        driver.findElement(descField).sendKeys("Summary Check");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        driver.findElement(summaryTab).click();
        Thread.sleep(1500);
        assertTrue(driver.findElement(yourSummary).isDisplayed());
    }

    @Test
    @DisplayName("TC058: Filter switching after adding expense")
    public void tc058_filterSwitchingAfterAddingExpense() throws InterruptedException {
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys("75");
        driver.findElement(descField).clear();
        driver.findElement(descField).sendKeys("Filter Test");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        driver.findElement(thisMonthBtn).click();
        Thread.sleep(500);
        driver.findElement(thisYearBtn).click();
        Thread.sleep(500);
        assertTrue(driver.findElement(spendSoFar).isDisplayed());
    }

    @Test
    @DisplayName("TC059: Navigate all screens in sequence")
    public void tc059_navigateAllScreensInSequence() throws InterruptedException {
        assertTrue(driver.findElement(appTitle).isDisplayed());
        driver.findElement(summaryTab).click();
        Thread.sleep(1000);
        assertTrue(driver.findElement(yourSummary).isDisplayed());
        driver.findElement(homeTab).click();
        Thread.sleep(1000);
        driver.findElement(fabButton).click();
        Thread.sleep(1000);
        assertTrue(driver.findElement(addExpenseTitle).isDisplayed());
        driver.navigate().back();
        Thread.sleep(500);
        assertTrue(driver.findElement(appTitle).isDisplayed());
    }

    @Test
    @DisplayName("TC060: Complete app workflow")
    public void tc060_completeAppWorkflow() throws InterruptedException {
        driver.findElement(todayBtn).click();
        Thread.sleep(300);
        driver.findElement(fabButton).click();
        Thread.sleep(1500);
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys("300");
        driver.findElement(descField).clear();
        driver.findElement(descField).sendKeys("Complete Workflow");
        driver.findElement(addExpenseBtn).click();
        Thread.sleep(1500);
        driver.findElement(summaryTab).click();
        Thread.sleep(1000);
        driver.findElement(homeTab).click();
        Thread.sleep(1000);
        assertTrue(driver.findElement(appTitle).isDisplayed(),
                "Complete workflow should finish successfully");
    }
}