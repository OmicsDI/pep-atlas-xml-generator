package uk.ac.ebi.ddi.task.pepatlasxmlgenerator.services;

import org.springframework.stereotype.Service;
import uk.ac.ebi.ddi.task.pepatlasxmlgenerator.utils.FileDownloadUtils;
import uk.ac.ebi.ddi.task.pepatlasxmlgenerator.utils.TsvUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PeptideAtlasService {

    private static final String SUMMARY_FILE =
            "http://www.peptideatlas.org/export/OmicsDI/current_peptideatlas_export.tsv";

    public List<String> getDatasetFiles() throws IOException {
        File tmpFile = File.createTempFile("ddi", "peptide.tsv");
        FileDownloadUtils.downloadFile(SUMMARY_FILE, tmpFile);
        List<Map<String, String>> data = TsvUtils.read(tmpFile);
        return data.stream().map(x -> x.get("XML_File")).collect(Collectors.toList());
    }
}
