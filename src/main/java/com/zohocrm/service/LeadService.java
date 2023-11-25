package com.zohocrm.service;

import com.zohocrm.entity.Lead;
import com.zohocrm.payload.LeadDto;

import java.util.List;

public interface LeadService {
    List<LeadDto> getAllLeads() ;


    LeadDto createLead(LeadDto leadDto);

    void deleteLeadById(String lid);
}
