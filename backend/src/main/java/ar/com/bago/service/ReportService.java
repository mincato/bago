package ar.com.bago.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import org.osgi.framework.ServiceException;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Service
public class ReportService {

    public byte[] create(InputStream reportStream, Map<String, Object> parameters, Collection<?> beanCollection) {

        try {
            JasperPrint print = JasperFillManager.fillReport(reportStream, parameters, new JRBeanCollectionDataSource(
                    beanCollection));
            return exportPrintToPDF(print);
        } catch (JRException e) {
            throw new ServiceException("Hubo un error creando reporte", e);
        }
    }

    private byte[] exportPrintToPDF(JasperPrint print) throws JRException {
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));

        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setCompressed(true);

        exporter.setConfiguration(configuration);

        exporter.exportReport();
        return out.toByteArray();
    }

}
