package tw.com.aitc.SBE.Junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith({TestExtension.class, TestExceptionHandler.class})
public class JunitLifeCycleTests {

	private static final Logger logger = LoggerFactory.getLogger(JunitLifeCycleTests.class);

	@BeforeAll
	static void beforeAll() {
		logger.info("BeforeAll");
		Assertions.assertTrue(false);
	}

	@AfterAll
	static void afterAll() {
		logger.info("AfterAll");
		Assertions.assertTrue(false);
	}

	@BeforeEach
	void beforeEach() {
		logger.info("BeforeEach");
		Assertions.assertTrue(false);
	}

	@AfterEach
	void afterEach() {
		logger.info("AfterEach");
		Assertions.assertTrue(false);
	}

	@Test
	void test1() {
		logger.info("TestExecution 1");
		Assertions.assertTrue(false);
	}

	// 禁用
//	@Disabled
	@Test
	void test2() {
		logger.info("TestExecution 2");
		Assertions.assertTrue(false);
	}
}
