package com.zohocrm.controller;

import com.zohocrm.payload.EmailDto;
import com.zohocrm.payload.LeadDto;
import com.zohocrm.service.LeadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {
    private LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }
    @PostMapping
    public ResponseEntity<LeadDto>createLead(@RequestBody LeadDto leadDto){
        LeadDto dto = leadService.createLead(leadDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{lid}")
    public ResponseEntity<String>deleteLeadById(@PathVariable String lid){
        leadService.deleteLeadById(lid);
        return new ResponseEntity<>("Lead is deleted", HttpStatus.OK);
    }
    @GetMapping
    public List<LeadDto> getAllLeads(){
        List<LeadDto> allLeads = leadService.getAllLeads();
        return allLeads;
    }

    public ResponseEntity<?> sendEmail(@RequestBody EmailDto emailDto){
        return null;
    }
}
