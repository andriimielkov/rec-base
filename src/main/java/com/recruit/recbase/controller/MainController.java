package com.recruit.recbase.controller;

import com.recruit.recbase.model.CandidateCard;
import com.recruit.recbase.security.Recruiter;
import com.recruit.recbase.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController()
public class MainController {

    @Autowired
    CandidateService candidateService;

    @GetMapping()
    public ModelAndView getAllCandidates(){
        ModelAndView mav = new ModelAndView("list");
        mav.addObject("list", candidateService.findAll());
        return mav;
    }

    @PostMapping("/post")
    public String postCadidate(@RequestBody CandidateCard candidateCard){
        candidateService.saveCandidate(candidateCard);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public @ResponseBody CandidateCard getCandidate(@PathVariable String id){
       return candidateService.findOne(id);
    }


    @GetMapping("/search")
    public @ResponseBody List<CandidateCard> getCandidateByName(@RequestParam("name") String name){
        return candidateService.findByName(name);
    }

    @PutMapping("/assignee/{id}")
      public @ResponseBody CandidateCard assigneeRecruiter(@PathVariable String id){
        Recruiter recruiter = (Recruiter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return candidateService.assigneeRecruiter(id, recruiter.getUsername());

    }

}
