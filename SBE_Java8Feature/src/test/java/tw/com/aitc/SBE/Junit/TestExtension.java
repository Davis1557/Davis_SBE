package tw.com.aitc.SBE.Junit;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback {

	private static final Logger logger = LoggerFactory.getLogger(TestExtension.class);

	@Override
	public void afterAll(ExtensionContext context) throws Exception {
		logger.info("AfterAllCallback");
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		logger.info("AfterEachCallback");
	}

	@Override
	public void afterTestExecution(ExtensionContext context) throws Exception {
		logger.info("AfterTestExecutionCallback");
	}

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		logger.info("BeforeAllCallback");
	}

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		logger.info("BeforeEachCallback");
	}

	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {
		logger.info("BeforeTestExecutionCallback");
	}
}
