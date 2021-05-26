package coba.daily.you.controller.report;


import coba.daily.you.model.entity.Product;
import coba.daily.you.report.CustomJRDataSource;
import coba.daily.you.repository.ProductRepository;
import net.sf.jasperreports.engine.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : backend
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 05/05/18
 * Time: 08.54
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ReportController {
    @Autowired
    ProductRepository productRepository;

    final static Logger logger = Logger.getLogger(ReportController.class);
    final static String pdfSource = "src/main/resources/static/assets/jasper/report.pdf";

    private void generateReport(List<Product> products) throws JRException{
        logger.info("[!] Start generate report");
        // Path to our template goes here
        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/static/assets/jasper/product.jrxml");
        // load data to datasource
        CustomJRDataSource<Product> dataSource = new CustomJRDataSource<Product>().using(products);
        // Map datasource to template
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String,Object>(), dataSource);
        // Export to pdf
        JasperExportManager.exportReportToPdfFile(jasperPrint,pdfSource);
    }
    @RequestMapping(value = "/getReport", method = RequestMethod.GET)
    public HttpEntity<byte[]> getReport() throws JRException,IOException {
        // Stub data

        List<Product> products = productRepository.findAll();
        System.out.println(products);
        generateReport(products);
        logger.info("[+] Generated report successfully");

        // Force download
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.set("Content-Disposition",
                "attachment; filename=" + "intan.pdf");
        byte[] documentBody = Files.readAllBytes(new File(pdfSource).toPath());
        header.setContentLength(documentBody.length);
        return new HttpEntity<byte[]>(documentBody, header);
    }
}
