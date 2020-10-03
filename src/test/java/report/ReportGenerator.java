package report;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {

    public static void main(String[]args){
        //Relative path to generate report.json
        String path="build/reports/cucumber/";

        //JSON report file
        File reportOutPut= new File(path + "Generated-ReportAPI");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add(path+"report.json");

        //Configuration
        Configuration configuration =  new Configuration(reportOutPut,"Todo.ly - API Rest test");
        configuration.setBuildNumber("v0.1");
        configuration.addClassifications("Platform","Windows");
        configuration.addClassifications("Owner","Gonzalo");
        configuration.addClassifications("Branch","master");
        configuration.addClassifications("Type Report","Local");

        //Build
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles,configuration);
        reportBuilder.generateReports();
        System.out.println(System.getProperty("buildNumber"));
    }
}
