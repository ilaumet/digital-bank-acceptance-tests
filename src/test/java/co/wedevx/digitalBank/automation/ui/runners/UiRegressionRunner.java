package co.wedevx.digitalBank.automation.ui.runners;

import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ui/features")
@ConfigurationParameter(key=GLUE_PROPERTY_NAME, value="co.wedevx.digitalbank.automation.ui.steps")
@IncludeTags("Test")
public class UiRegressionRunner {



}
