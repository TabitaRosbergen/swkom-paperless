package org.openapitools.services.impl;

import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class OCRServiceTest {

    private static final String TEST_PDF_DIRECTORY = "src/test/resources/pdfs/";

    @BeforeEach
    void setUp() {
        // Any setup code needed before each test.
    }

    @AfterEach
    void tearDown() {
        // Any cleanup code needed after each test.
    }

    @Test
    void testReadPdfWithSearchablePdf() {
        OCRService ocrService = new OCRService();

        // Test with searchable PDF
        File searchablePdf = new File(TEST_PDF_DIRECTORY + "searchable.pdf");
        String searchableText = ocrService.readPdf(searchablePdf);
        assertNotNull(searchableText);
        String myString = "Python";
        byte[] utf8Bytes = myString.getBytes(StandardCharsets.UTF_8);
        assertTrue(searchableText.trim().contains(new String(utf8Bytes, StandardCharsets.UTF_8)));
    }

    @Test
    void testReadPdfWithNotSearchablePdf() {
        OCRService ocrService = new OCRService();

        // Test with non-searchable (scanned) PDF
        File nonSearchablePdf = new File(TEST_PDF_DIRECTORY + "not-searchable.pdf");
        String nonSearchableText = ocrService.readPdf(nonSearchablePdf);
        assertNotNull(nonSearchableText);
//        System.out.println(nonSearchableText);
        String myString = "Python";
        byte[] utf8Bytes = myString.getBytes(StandardCharsets.UTF_8);
        assertTrue(nonSearchableText.trim().contains(new String(utf8Bytes, StandardCharsets.UTF_8)));
    }

    @Test
    void testGetFileContentWithSearchablePdf() {
        OCRService ocrService = new OCRService();

        // Test with searchable PDF
        File searchablePdf = new File(TEST_PDF_DIRECTORY + "searchable.pdf");
        String searchableText = ocrService.getFileContent(searchablePdf);
        assertNotNull(searchableText);
        String myString = "Python";
        byte[] utf8Bytes = myString.getBytes(StandardCharsets.UTF_8);
        assertTrue(searchableText.trim().contains(new String(utf8Bytes, StandardCharsets.UTF_8)));
    }

    @Test
    void testGetFileContentWithNotSearchablePdf() {
        OCRService ocrService = new OCRService();

        // Test with non-searchable (scanned) PDF
        File nonSearchablePdf = new File(TEST_PDF_DIRECTORY + "not-searchable.pdf");
        String nonSearchableText = ocrService.getFileContent(nonSearchablePdf);
        assertNotNull(nonSearchableText);
        String myString = "Python";
        byte[] utf8Bytes = myString.getBytes(StandardCharsets.UTF_8);
        assertTrue(nonSearchableText.trim().contains(new String(utf8Bytes, StandardCharsets.UTF_8)));
    }

    @Test
    void testExtractTextFromScannedDocumentWithNotSearchablePdf() {
        OCRService ocrService = new OCRService();

        // Test with non-searchable (scanned) PDF
        File nonSearchablePdf = new File(TEST_PDF_DIRECTORY + "not-searchable.pdf");

        PDDocument document = null;
        try {
            document = OCRService.loadPDF(nonSearchablePdf);
            String scannedText = ocrService.extractTextFromScannedDocument(document);
            assertNotNull(scannedText);
            String myString = "Python";
            byte[] utf8Bytes = myString.getBytes(StandardCharsets.UTF_8);
            assertTrue(scannedText.trim().contains(new String(utf8Bytes, StandardCharsets.UTF_8)));
        } catch (IOException | TesseractException e) {
            fail("Failed to load PDF document: " + e.getMessage());
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    // Handle the exception if needed
                }
            }
        }
    }
}
