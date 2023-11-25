package com.zohocrm.service.Impl;

import com.zohocrm.entity.Contact;
import com.zohocrm.entity.Email;
import com.zohocrm.entity.Lead;
import com.zohocrm.exception.ContactExists;
import com.zohocrm.exception.LeadExists;
import com.zohocrm.payload.EmailDto;
import com.zohocrm.repository.ContactRepository;
import com.zohocrm.repository.EmailRepository;
import com.zohocrm.repository.LeadRepository;
import com.zohocrm.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {


    private JavaMailSender javaMailSender;

    private ModelMapper modelMapper;

    private EmailRepository emailRepo;

    private LeadRepository leadRepo;

    private ContactRepository contactRepo;

    public EmailServiceImpl(JavaMailSender javaMailSender, ModelMapper modelMapper, EmailRepository emailRepo, LeadRepository leadRepo, ContactRepository contactRepo) {
        this.javaMailSender = javaMailSender;
        this.modelMapper = modelMapper;
        this.emailRepo = emailRepo;
        this.leadRepo = leadRepo;
        this.contactRepo = contactRepo;
    }

    public EmailServiceImpl(EmailRepository emailsRepo, LeadRepository leadRepo) {
        this.emailRepo = emailRepo;
        this.leadRepo = leadRepo;
    }

    @Override
    public EmailDto sendEmail(EmailDto emailDto) {
        Lead lead = leadRepo.findByEmail(emailDto.getTo()).orElseThrow(
                () -> new LeadExists("Email is not registered-" + emailDto.getTo()));

//        Contact contact = contactRepo.findByEmail(emailDto.getTo()).orElseThrow(
//                () -> new ContactExists("Contact is not registered-" + emailDto.getTo()));

        SimpleMailMessage message =new SimpleMailMessage();
        message.setTo(emailDto.getTo());
        message.setSubject(emailDto.getSubject());
        message.setText(emailDto.getMessage());

        javaMailSender.send(message);

        Email email = mapToEntity(emailDto);
        String eid = UUID.randomUUID().toString();
        email.setEid(eid);

        Email sentEmail = emailRepo.save(email);
        return mapToDto(sentEmail);

    }

    Email mapToEntity(EmailDto emailDto){
        Email email = modelMapper.map(emailDto, Email.class);
        return email;
    }

    EmailDto mapToDto(Email email){
        EmailDto dto = modelMapper.map(email, EmailDto.class);
        return dto;
    }

}
