package edu.sage.datacommonsdashboard.controller;

import edu.sage.datacommonsdashboard.repository.HpcHostRepository;
import edu.sage.datacommonsdashboard.util.TimeZoneUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HpcHostController {

    private final HpcHostRepository repository;
    private final HpcHostTransformer transformer;
    private final TimeZoneUtil timeZoneUtil = new TimeZoneUtil();

    // Inject the property value from application.properties
    @Value("${hpc.page.refresh.interval}")
    private int refreshInterval;

    public HpcHostController(HpcHostRepository repository, HpcHostTransformer transformer) {
        this.repository = repository;
        this.transformer = transformer;
    }

    @GetMapping(value = "/hpc/dashboard/host")
    public String showHpcHosts(Model model) {
        model.addAttribute("pageTitle", "HPC Hosts");
        model.addAttribute("hpcHosts", getHpcHosts());
        model.addAttribute("refreshInterval", refreshInterval); // Refresh every 60 seconds

        Integer epochSeconds = Math.toIntExact(Instant.now().getEpochSecond());

        model.addAttribute("timestamp", epochSeconds);
        model.addAttribute("formattedTimestamp", timeZoneUtil.convertTimestampToDateString(epochSeconds, ZoneId.of("America/Denver")));

        return "hpc-host";
    }

    List<HpcHostModel> getHpcHosts() {
        return repository.getAll().stream()
                .map(transformer::transform)
                .collect(Collectors.toList());
    }
}
