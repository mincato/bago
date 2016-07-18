package ar.com.bago.service;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.com.bago.common.exception.NotFoundException;
import ar.com.bago.common.exception.ServiceException;
import ar.com.bago.common.pagination.PageRequest;
import ar.com.bago.common.pagination.PageResponse;
import ar.com.bago.model.developer.Developer;
import ar.com.bago.model.developer.DeveloperListView;
import ar.com.bago.model.developer.Seniority;
import ar.com.bago.model.developer.Stats;
import ar.com.bago.persistence.DeveloperRepository;
import ar.com.bago.persistence.StatsRepository;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository repository;

    @Value("${developer.report.details.resource}")
    private String detailReportResourceName;

    @Autowired
    private ReportService reportService;

    @Autowired
    private StatsRepository statsRepository;

    public PageResponse<DeveloperListView> find(String name, String lastName, Seniority seniority, Integer pageNumber,
            Integer pageSize) {
        String nameParameter = getStringParameter(name);
        String lastNameParameter = getStringParameter(lastName);

        List<DeveloperListView> developers = repository.find(nameParameter, lastNameParameter, seniority,
                new PageRequest(pageNumber, pageSize));

        Long count = repository.count(nameParameter, lastNameParameter, seniority);

        PageResponse<DeveloperListView> page = new PageResponse<DeveloperListView>(developers, pageNumber, pageSize,
                count);
        return page;
    }

    private final static String PARAMETER_FORMAT = "%{0}%";

    private String getStringParameter(String parameter) {
        return parameter != null && !parameter.isEmpty() ? MessageFormat.format(PARAMETER_FORMAT,
                parameter.toUpperCase()) : null;
    }

    public Developer find(Integer id) {
        Developer developer = repository.findById(id);
        if (developer == null) {
            throw new NotFoundException();
        }
        return developer;
    }

    public Developer save(Developer newDeveloper) {
        repository.save(newDeveloper);
        return newDeveloper;
    }

    public Developer update(Integer id, Developer developer) {
        if (!repository.exists(id)) {
            throw new NotFoundException();
        }
        developer.setId(id);
        repository.update(developer);
        return developer;
    }

    public void delete(Integer id) {
        repository.delete(id);
    }

    public void throwServiceException(String param) {
        ServiceException serviceException = new ServiceException();
        serviceException.addParam(param);
        throw serviceException;
    }

    public void setRepository(DeveloperRepository repository) {
        this.repository = repository;
    }

    public Seniority[] getSeniorities() {
        return Seniority.values();
    }

    public byte[] getDetailReport(Seniority seniority) {
        InputStream reportStream = this.getClass().getResourceAsStream(detailReportResourceName);
        List<Developer> developers = null;
        String title = null;
        if (seniority == null) {
            developers = this.repository.findAll();
            title = "All";
        } else {
            developers = this.repository.findBySeniority(seniority);
            title = seniority.toString();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("ReportTitle", String.format("%s Developers", title));
        return reportService.create(reportStream, params, developers);
    }

    public List<Stats> getStats() {
        List<Stats> stats = statsRepository.getStats();
        return stats;
    }
}
