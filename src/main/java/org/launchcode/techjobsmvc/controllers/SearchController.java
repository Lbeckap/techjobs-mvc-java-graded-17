package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping(value = {"results", "search"})
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        List<ArrayList> jobs = new ArrayList<>();
        if (searchType.equals("all") || searchTerm.isBlank()) {
           jobs.add(JobData.findAll());
        } else {
            jobs.add(JobData.findByColumnAndValue(searchType, searchTerm));
        }
        System.out.println(jobs.get(0));
        model.addAttribute("jobs", jobs.get(0));
        model.addAttribute("columns", columnChoices);
       return "search";
    }

}

