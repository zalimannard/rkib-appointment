package ru.zalimannard.rkibappointmentuitest;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseTest {

	protected String startUrl = "http://localhost:5173/login";

	@BeforeAll
	public static void init() {
		SelenideLogger.addListener(
				"AllureSelenide",
				new AllureSelenide().screenshots(true).savePageSource(true)
		);
	}

}
