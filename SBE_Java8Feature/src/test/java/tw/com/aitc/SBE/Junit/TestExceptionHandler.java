package tw.com.aitc.SBE.Junit;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.LifecycleMethodExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestExceptionHandler implements TestExecutionExceptionHandler, LifecycleMethodExecutionExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(TestExceptionHandler.class);
	
	@Override
	public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
		logger.info("handleTestExecutionException");
//		throw throwable;
	}

	@Override
	public void handleBeforeAllMethodExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
		logger.info("handleBeforeAllMethodExecutionException");
//		throw throwable;
	}

	@Override
	public void handleBeforeEachMethodExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
		logger.info("handleBeforeEachMethodExecutionException");
//		throw throwable;
	}

	@Override
	public void handleAfterEachMethodExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
		logger.info("handleAfterEachMethodExecutionException");
//		throw throwable;
	}

	@Override
	public void handleAfterAllMethodExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
		logger.info("handleAfterAllMethodExecutionException");
//		throw throwable;
	}
}
