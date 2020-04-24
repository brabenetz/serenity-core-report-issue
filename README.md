# Serenity Core Report Issue

This is a small example project to solve a serenity issue fast.

## The Problem:

An Issue annotion "@issue:123" or "@issue:#123" in compination with FileSystemRequirements will throw a NPE,
The FileSystemRequirements is used in serenity-cli: https://github.com/serenity-bdd/serenity-cli/blob/master/src/main/java/net/serenitybdd/cli/reporters/RequirementsStrategy.java#L11
The NPE occurs in TestOutcome.getIssueKeys() from serenity-core: https://github.com/serenity-bdd/serenity-core/blob/master/serenity-model/src/main/java/net/thucydides/core/model/TestOutcome.java#L1920

The full Stacktrace was:
```log
[test:report] 10:28:10.870 [main] WARN  n.t.c.r.h.HtmlAggregateStoryReporter - Failed to generate report for net.thucydides.core.reports.html.HtmlTestOutcomeReportingTask@6e9319f - java.util.concurrent.ExecutionException: java.lang.NullPointerException
[test:report] net.serenitybdd.core.environment.EnvironmentSpecificConfiguration.environmentStrategyDefinedIn(EnvironmentSpecificConfiguration.java:179)
[test:report] java.util.concurrent.ExecutionException: java.lang.NullPointerException
[test:report]   at java.base/java.util.concurrent.FutureTask.report(FutureTask.java:122)
[test:report]   at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:205)
[test:report]   at net.thucydides.core.reports.html.Reporter.generateReports(Reporter.java:56)
[test:report]   at net.thucydides.core.reports.html.Reporter.generateReportsFor(Reporter.java:32)
[test:report]   at net.thucydides.core.reports.html.HtmlAggregateStoryReporter.generateReportsForTestResultsIn(HtmlAggregateStoryReporter.java:212)
[test:report]   at net.thucydides.core.reports.html.HtmlAggregateStoryReporter.generateReportsForTestResultsFrom(HtmlAggregateStoryReporter.java:136)
[test:report]   at net.serenitybdd.cli.reporters.CLIAggregateReportGenerator.generateReportsFrom(CLIAggregateReportGenerator.java:66)
[test:report]   at net.serenitybdd.cli.SerenityCLIReportCoordinator.execute(SerenityCLIReportCoordinator.java:56)
[test:report]   at net.serenitybdd.cli.Serenity.executeWith(Serenity.java:116)
[test:report]   at net.serenitybdd.cli.Serenity.main(Serenity.java:80)
[test:report] Caused by: java.lang.NullPointerException: null
[test:report]   at net.serenitybdd.core.environment.EnvironmentSpecificConfiguration.environmentStrategyDefinedIn(EnvironmentSpecificConfiguration.java:179)
[test:report]   at net.serenitybdd.core.environment.EnvironmentSpecificConfiguration.<init>(EnvironmentSpecificConfiguration.java:108)
[test:report]   at net.serenitybdd.core.environment.EnvironmentSpecificConfiguration.from(EnvironmentSpecificConfiguration.java:175)
[test:report]   at net.thucydides.core.ThucydidesSystemProperty.optionalPropertyValueDefinedIn(ThucydidesSystemProperty.java:1615)
[test:report]   at net.thucydides.core.ThucydidesSystemProperty.from(ThucydidesSystemProperty.java:1565)
[test:report]   at net.thucydides.core.ThucydidesSystemProperty.from(ThucydidesSystemProperty.java:1535)
[test:report]   at net.thucydides.core.issues.IssueKeyFormat.getProjectPrefix(IssueKeyFormat.java:17)
[test:report]   at net.thucydides.core.issues.IssueKeyFormat.andKey(IssueKeyFormat.java:25)
[test:report]   at net.thucydides.core.model.TestOutcome.lambda$getIssueKeys$21(TestOutcome.java:1920)
[test:report]   at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
[test:report]   at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1654)
[test:report]   at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
[test:report]   at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
[test:report]   at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
[test:report]   at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
[test:report]   at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
[test:report]   at net.thucydides.core.model.TestOutcome.getIssueKeys(TestOutcome.java:1921)
[test:report]   at net.thucydides.core.requirements.BaseRequirementsService.getAncestorRequirementsFor(BaseRequirementsService.java:80)
[test:report]   at net.thucydides.core.reports.html.HtmlAcceptanceTestReporter.addBreadcrumbs(HtmlAcceptanceTestReporter.java:201)
[test:report]   at net.thucydides.core.reports.html.HtmlAcceptanceTestReporter.addParentRequirmentFieldToContext(HtmlAcceptanceTestReporter.java:189)
[test:report]   at net.thucydides.core.reports.html.HtmlAcceptanceTestReporter.addTestOutcomeToContext(HtmlAcceptanceTestReporter.java:165)
[test:report]   at net.thucydides.core.reports.html.HtmlAcceptanceTestReporter.generateReportFor(HtmlAcceptanceTestReporter.java:107)
[test:report]   at net.thucydides.core.reports.html.HtmlTestOutcomeReportingTask.generateReports(HtmlTestOutcomeReportingTask.java:44)
[test:report]   at net.thucydides.core.reports.html.ReportExecutor.call(ReportExecutor.java:33)
[test:report]   at net.thucydides.core.reports.html.ReportExecutor.call(ReportExecutor.java:12)
[test:report]   at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
[test:report]   at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
[test:report]   at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
[test:report]   at java.base/java.lang.Thread.run(Thread.java:835)
```


