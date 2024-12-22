package reportConfig;//package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;
import commons.GlobalConstants;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstants.getGlobalConstants().getRelativeProjectPath() + "/extentReport/HTMLReportVersion5.html");
        reporter.config().setReportName("Everfit HTML Report");
        reporter.config().setDocumentTitle("Everfit HTML Report");
        reporter.config().setTimelineEnabled(true);
        reporter.config().setEncoding("utf-8");
        reporter.config().setTheme(Theme.DARK);

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Company", "Everfit");
        extentReports.setSystemInfo("Project", "Everfit");
        extentReports.setSystemInfo("JDK version", GlobalConstants.getGlobalConstants().getJavaVersion());
        return extentReports;
    }
}
