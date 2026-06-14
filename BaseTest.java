package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setPlatformName("Android");
        options.setPlatformVersion("13.0");
        options.setAppPackage("com.example.expensetrackerapp");
        options.setAppActivity("com.example.expensetrackerapp.MainActivity");
        options.setNoReset(false);

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options
        );

        Thread.sleep(10000);

        // Skip onboarding by swiping left twice then clicking Get Started
        try {
            swipeLeft();
            Thread.sleep(1000);
            swipeLeft();
            Thread.sleep(1000);
            driver.findElement(
                    By.xpath("//android.widget.TextView[@text='Get Started']")).click();
            Thread.sleep(5000);
        } catch (Exception e) {
            // No onboarding screen, continue
        }
    }

    protected void swipeLeft() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int)(size.width * 0.8);
        int endX = (int)(size.width * 0.2);
        int centerY = size.height / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, centerY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(swipe));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
