package uk.ac.ebi.ddi.task.pepatlasxmlgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.ebi.ddi.ddifileservice.services.IFileSystem;
import uk.ac.ebi.ddi.task.pepatlasxmlgenerator.configuration.PepAtlasXmlGeneratorTaskProperties;
import uk.ac.ebi.ddi.task.pepatlasxmlgenerator.services.PeptideAtlasService;
import uk.ac.ebi.ddi.task.pepatlasxmlgenerator.utils.FileDownloadUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class PepAtlasXmlGeneratorApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(PepAtlasXmlGeneratorApplication.class);

    @Autowired
    private PeptideAtlasService peptideAtlasService;

    @Autowired
    private PepAtlasXmlGeneratorTaskProperties taskProperties;

    @Autowired
    private IFileSystem fileSystem;

    public static void main(String[] args) {
        SpringApplication.run(PepAtlasXmlGeneratorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> datasetFiles = peptideAtlasService.getDatasetFiles();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (String file : datasetFiles) {
            File datasetFile = File.createTempFile("ddi", "tmp.xml");
            FileDownloadUtils.downloadFile(file, datasetFile);
            String filePath = taskProperties.getOutputDir() + "/"
                    + taskProperties.getPrefix() + atomicInteger.getAndIncrement() + ".xml";
            LOGGER.info("Attempting to write data to {}", filePath);
            fileSystem.copyFile(datasetFile, filePath);
        }
    }
}
