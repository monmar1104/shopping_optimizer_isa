package com.infoshareacademy.czerwoni.products.servlets;


import com.infoshareacademy.czerwoni.product.BarCodeReader;
import com.infoshareacademy.czerwoni.product.ProductProcessor;
import com.infoshareacademy.czerwoni.products.domain.FileInfo;
import com.infoshareacademy.czerwoni.products.service.UploadService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.UUID;

import com.infoshareacademy.czerwoni.product.Product;


@WebServlet("/FileUpload")

@MultipartConfig

public class ImageUploadServlet extends HttpServlet {

    private static Logger LOGGER = LoggerFactory.getLogger(ImageUploadServlet.class);
    private static String NO_BARCODE_FOUND = "Nie znaleziono kodu kreskowego";
    private final String GENERIC_ERR_MSG = "Albo nie wskazałeś pliku do przesłania, albo "
            + "próbujesz go zapisać w nieistniejącej lub niedostępnej "
            + "lokalizacji.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product-info.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet that uploads files with barcodes";
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FileInfo fileInfo = new FileInfo();

        try {
            fetchDataFromRequest(request, fileInfo);
            UploadService.saveFileIntoStorage(fileInfo);
            prepareOutData(request, fileInfo);
        } catch (FileNotFoundException fne) {
            String errMsg = fne.getMessage().equals("") ? GENERIC_ERR_MSG : fne.getMessage();
            request.setAttribute("errMsg", errMsg);
            LOGGER.error("Problemy w trakcie przesyłu pliku. Błąd: {}",
                    fne.getMessage());
        }
    }

    private void prepareOutData(HttpServletRequest request, FileInfo fileInfo) {

        String productBarcode = BarCodeReader.decodeBarcodeFromFile(fileInfo.getFilePath() + File.separator + fileInfo.getFileName());
        if (productBarcode.isEmpty()) {
            request.setAttribute("errMsg", NO_BARCODE_FOUND);
            LOGGER.warn(NO_BARCODE_FOUND);
        } else {
            Product foundProduct = ProductProcessor.getProductFromAPI(productBarcode);
            if (foundProduct == null) {
                String errMsg = "Nie znaleziono produktu dla kodu: " + productBarcode;
                request.setAttribute("errMsg", errMsg);
                LOGGER.warn(errMsg);
            } else {
                LOGGER.trace("odczytany kod: " + productBarcode + "; produkt: " + foundProduct.toString());
                request.setAttribute("product", foundProduct);

                String imgFilePath = "/barcodes" + File.separator + fileInfo.getFileName();
                request.setAttribute("localImg", imgFilePath);
            }
        }
    }

    private void fetchDataFromRequest(HttpServletRequest request, FileInfo fileInfo)
            throws ServletException, IOException {

        fileInfo.setFilePart(request.getPart("file"));
        if (fileInfo.getFilePart().getSubmittedFileName().equals(""))
            throw new FileNotFoundException("Podano pustą nazwę pliku z kodem kreskowym");

        fileInfo.setFilePath(UploadService.getStoragePath());
        fileInfo.setFileName(UUID.randomUUID().toString());

        String extension = FilenameUtils.getExtension(UploadService.getFileName(fileInfo.getFilePart()));
        if (!extension.isEmpty()) {
            fileInfo.setFileName(fileInfo.getFileName() + "." + extension);
        }
    }








}