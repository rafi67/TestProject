package io.soft.coronavirustracker.controllers;

import io.soft.coronavirustracker.models.LocationStats;
import io.soft.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalConfirmed = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("TotalConfirmed", totalConfirmed);
        return "home";
    }
}
