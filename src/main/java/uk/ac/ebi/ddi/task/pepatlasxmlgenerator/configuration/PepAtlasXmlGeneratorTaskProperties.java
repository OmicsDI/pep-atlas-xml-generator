package uk.ac.ebi.ddi.task.pepatlasxmlgenerator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("pep-atlas")
public class PepAtlasXmlGeneratorTaskProperties {
    private String prefix = "PepAtlas-";
    private String outputDir;

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
