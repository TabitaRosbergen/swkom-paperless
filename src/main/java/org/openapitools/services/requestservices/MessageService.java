package org.openapitools.services.requestservices;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openapitools.configuration.RabbitMQConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class MessageService {
    private final RabbitTemplate rabbit;
    private final MinioService minioService;
    private final Logger log = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    public MessageService(RabbitTemplate rabbit, MinioService minioService) {
        this.rabbit = rabbit;
        this.minioService = minioService;
    }

    @RabbitListener(queues = RabbitMQConfig.MESSAGE_IN_QUEUE)
    public void receive(String path) {
        log.info("MessageService received: '" + path + "'");
        System.out.println("MessageService received: '" + path + "'");

        //retrieve the document from minio
        File file = minioService.getDocument(path);

        byte[] bytes = null;
              FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            //read file into bytes[]
          fis.read(bytes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Print the document
        System.out.println("This is the file: " + file.getName());

        testOCR(bytes);
    }

    public void testOCR(byte[] bytes){
        // Load file into PDFBox class
        PDDocument document = null;
        try {
            document = Loader.loadPDF(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PDFTextStripper stripper = new PDFTextStripper();
        String strippedText = null;
        try {
            strippedText = stripper.getText(document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Check text exists into the file
		if (strippedText.trim().isEmpty()){
            try {
                strippedText = extractTextFromScannedDocument(document);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (TesseractException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(strippedText);
        log.info(strippedText);
    }

    private String extractTextFromScannedDocument(PDDocument document) throws IOException, TesseractException {

		// Extract images from file
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		StringBuilder out = new StringBuilder();

        ITesseract _tesseract = new Tesseract();
		_tesseract.setDatapath("/usr/share/tessdata/");
		_tesseract.setLanguage("ita"); // choose your language

		for (int page = 0; page < document.getNumberOfPages(); page++)
		{
		    BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

		    // Create a temp image file
    	    File temp = File.createTempFile("tempfile_" + page, ".png");
    	    ImageIO.write(bim, "png", temp);

    	    // TODO: Apply here OCR logic
            String result = null;
            try {
                result = _tesseract.doOCR(temp);
            } catch (TesseractException e) {
                throw new RuntimeException(e);
            }
            out.append(result);

		    // Delete temp file
		    temp.delete();

		}

		return out.toString();
	}

}
