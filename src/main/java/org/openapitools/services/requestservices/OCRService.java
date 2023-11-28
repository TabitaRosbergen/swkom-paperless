package org.openapitools.services.requestservices;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;


@Service
public class OCRService {
    private final Logger log = LoggerFactory.getLogger(MessageService.class);

    public String readPdf(File file) {
        byte[] fileContent = new byte[0];
        try {
            fileContent = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Load file into PDFBox class
        PDDocument document = null;
        try {
            document = Loader.loadPDF(fileContent);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        PDFTextStripper stripper = new PDFTextStripper();
        String strippedText = null;

        try {
            strippedText = stripper.getText(document);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return strippedText;
    }

    public String getFileContent(File file) {
        Path filePath = Paths.get(file.toURI());
        String contentType = null;

        Tika tika = new Tika();
        try {
            contentType = tika.detect(file);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        contentType.replace("application/", "");

        System.out.println("Content type: " + contentType);

        switch (contentType) {
            case "pdf":
                return readPdf(file);
            default:
                return "";
        }
    }

//    public String getFileContent(File file) {
//        File imageFile = new File("src/main/resources/tessdata/Hello-Text-PNG.png");
//        ITesseract instance = new Tesseract();
//        instance.setDatapath("src/main/resources/tessdata"); // replace with your tessdata path
//        String result = null;
//        try {
//            result = instance.doOCR(imageFile);
//        } catch (TesseractException e) {
//            System.err.println(e.getMessage());
//        }
//        return result;
//    }

    //    private void readPdf(File file) throws IOException {
//        byte[] fileContent = Files.readAllBytes(file.toPath());
//
//        // Load file into PDFBox class
//        PDDocument document = null;
//        try {
//            document = Loader.loadPDF(fileContent);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        PDFTextStripper stripper = new PDFTextStripper();
//        String strippedText = null;
//        try {
//            strippedText = stripper.getText(document);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        // Check text exists into the file
//		if (strippedText.trim().isEmpty()){
//            try {
//                strippedText = extractTextFromScannedDocument(document);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (TesseractException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        System.out.println(strippedText);
//        log.info(strippedText);
//    }

//    private String extractTextFromScannedDocument(PDDocument document) throws IOException, TesseractException {
//
//		// Extract images from file
//		PDFRenderer pdfRenderer = new PDFRenderer(document);
//		StringBuilder out = new StringBuilder();
//
//        // tesseract funktioniert noch nicht
//        // unable to load library tesseract
//        // TODO fix it
//        ITesseract _tesseract = new Tesseract();
//		_tesseract.setDatapath("/usr/share/tessdata/");
//		_tesseract.setLanguage("ita"); // choose your language
//
//		for (int page = 0; page < document.getNumberOfPages(); page++)
//		{
//		    BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
//
//		    // Create a temp image file
//    	    File temp = File.createTempFile("tempfile_" + page, ".png");
//    	    ImageIO.write(bim, "png", temp);
//
//    	    // TODO: Apply here OCR logic
//
//            String result = null;
//            try {
//                result = _tesseract.doOCR(temp);
//            } catch (TesseractException e) {
//                throw new RuntimeException(e);
//            }
//            out.append(result);
//
//		    // Delete temp file
//		    temp.delete();
//
//		}
//
//		return out.toString();
//	}
}
