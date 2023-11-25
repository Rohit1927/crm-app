package com.zohocrm.service.Impl;

import com.zohocrm.entity.Contact;
import com.zohocrm.entity.Lead;
import com.zohocrm.exception.LeadExists;
import com.zohocrm.payload.ContactDto;
import com.zohocrm.repository.ContactRepository;
import com.zohocrm.repository.LeadRepository;
import com.zohocrm.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

   private LeadRepository leadRepo;
   private ContactRepository contactRepo;
   private ModelMapper modelMapper;

    public ContactServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ContactServiceImpl(LeadRepository leadRepo, ContactRepository contactRepo) {
        this.leadRepo = leadRepo;
        this.contactRepo = contactRepo;
    }

    @Override
    public ContactDto createContact(String leadId) {
        Lead lead = leadRepo.findById(leadId).orElseThrow(
                () -> new LeadExists("Lead with this id is not exist: " + leadId));

        Contact contact = convertLeadToContact(lead);
        String cid = UUID.randomUUID().toString();
        contact.setCid(cid);
        Contact savedContact = contactRepo.save(contact);

        if(savedContact.getCid()!=null){
            leadRepo.deleteById(lead.getLid());
        }
        ContactDto dto = mapToDto(savedContact);
        dto.setCid(savedContact.getCid());
        return dto;

    }



    Contact convertLeadToContact(Lead lead){
        Contact contact = modelMapper.map(lead, Contact.class);
        return contact;
    }

    ContactDto mapToDto(Contact contact){
        return modelMapper.map(contact, ContactDto.class);
    }
}
