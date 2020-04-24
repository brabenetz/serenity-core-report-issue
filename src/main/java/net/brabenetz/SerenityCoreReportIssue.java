package net.brabenetz;

import net.thucydides.core.reports.html.HtmlAggregateStoryReporter;
import net.thucydides.core.requirements.FileSystemRequirements;
import net.thucydides.core.requirements.Requirements;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerenityCoreReportIssue {

    public static void main(String[] args) throws IOException {
        Path source = Paths.get("src/main/resources/serenity");
        Path destination = Paths.get("target/site/serenity");

        // config the path to something invalid (like "src/xyz/not/exist") will fix the bug...
        Requirements requirements = new FileSystemRequirements("src/main/resources/features");
        HtmlAggregateStoryReporter reporter = new HtmlAggregateStoryReporter("SerenityCoreReportIssue", requirements);
        reporter.setSourceDirectory(source.toFile());
        reporter.setOutputDirectory(destination.toFile());
        reporter.setIssueTrackerUrl("https://github.com/serenity-js/serenity-js/issues/{0}");

        reporter.setGenerateTestOutcomeReports();

        reporter.generateReportsForTestResultsFrom(source.toFile());
    }

}
