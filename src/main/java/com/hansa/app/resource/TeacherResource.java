/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.ClassSubjectMapping;
import com.hansa.app.data.DocumentType;
import com.hansa.app.data.Education;
import com.hansa.app.data.Experience;
import com.hansa.app.data.TransType;
import com.hansa.app.data.TransactionData;
import com.hansa.app.repo.TutorRepo;
import com.hansa.app.data.Tutor;
import com.hansa.app.data.TutorOtp;
import com.hansa.app.data.User;
import com.hansa.app.data.ZIpCode;
import com.hansa.app.error.RequestException;
import com.hansa.app.model.PagedResponse;
import com.hansa.app.repo.ClassSubjectMapRepo;
import com.hansa.app.repo.EducationRepo;
import com.hansa.app.repo.ExperienceRepository;
import com.hansa.app.repo.JobApplicationRepo;
import com.hansa.app.repo.ReviewRepo;
import com.hansa.app.repo.UserRepo;
import com.hansa.app.repo.ZipRepo;
import com.hansa.app.service.MailUtil;
import com.hansa.app.service.MapService;
import com.hansa.app.service.S3Service;
import com.hansa.app.service.SMSService;
import com.hansa.app.service.SequenceService;
import com.hansa.app.service.TransService;
import com.hansa.app.service.TutorService;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.YEAR;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sushant
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tutor")
public class TeacherResource {

    @Autowired
    private TutorRepo tutorRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private TutorService service;

    @Autowired
    private JobApplicationRepo applicationRepo;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private TransService transService;

    @Autowired
    private MapService mapService;

    @Autowired
    private EducationRepo educationRepo;

    @Autowired
    private ClassSubjectMapRepo classSubjectMapRepo;

    @Autowired
    private ZipRepo zipRepo;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private SequenceService sequenceService;
    
    @Autowired
    private SMSService smsService;
    
    private static final Log log = LogFactory.getFactory().getInstance(TeacherResource.class);

    @RequestMapping(value = "/zip/{id}", method = RequestMethod.DELETE)
    public void deleteZip(@PathVariable("id") Long id) {
        zipRepo.deleteById(id);
    }

    @RequestMapping(value = "/map/{id}", method = RequestMethod.DELETE)
    public void deleteMap(@PathVariable("id") Long id) {
        classSubjectMapRepo.deleteById(id);
    }

    @RequestMapping(value = "/education/{id}", method = RequestMethod.DELETE)
    public void deleteEducation(@PathVariable("id") Long id) {
        educationRepo.deleteById(id);
    }

    @RequestMapping(value = "/{id}/zip", method = RequestMethod.POST)
    public void updateZip(@PathVariable("id") Long id, @RequestBody List<ZIpCode> map) {
        List<ZIpCode> zip = new ArrayList<>();
        for (ZIpCode z : map) {
            z.setTutorId(id);
            zip.add(z);
        }
        zipRepo.saveAll(zip);

    }

    @RequestMapping(value = "/{id}/validateOtp")
    public void validateOtp(@PathVariable("id") Long id, @RequestBody TutorOtp otp) {

        log.info("Tutor Id "+id+", Otp "+otp.getOtp());
        Tutor t = tutorRepo.getById(id);
        log.info("Tutor Id "+id+", Otp "+otp.getOtp()+", Original Otp "+t.getOtp());
        if (t.isOtpValidated()) {
            return;
        }
        if (!t.getOtp().equalsIgnoreCase(otp.getOtp())) {
            throw new RequestException("Opt mismatch.");
        }

        t.setOtpValidated(true);
        tutorRepo.save(t);
    }
    
    
     @RequestMapping(value = "/{id}/resendOtp")
    public void resendOtp(@PathVariable("id") Long id) {

        Tutor t = tutorRepo.getById(id);
        if (t.isOtpValidated()) {
            return;
        }
        try {
            smsService.sendSMS(t.getMobile(), t.getOtp());
        } catch(Exception e) {
            throw new RequestException("Error is sending sms "+e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}/map", method = RequestMethod.POST)
    public void updateClassSubject(@PathVariable("id") Long id, @RequestBody List<ClassSubjectMapping> map) {
        map.forEach(it -> it.setTutorId(id));
        mapService.update(id, map);

    }

    @RequestMapping(value = "/{id}/map", method = RequestMethod.GET)
    public List<ClassSubjectMapping> getMap(@PathVariable("id") Long id) {
        return classSubjectMapRepo.get(id);
    }

    @RequestMapping(value = "/{id}/education", method = RequestMethod.GET)
    public List<Education> getEducation(@PathVariable("id") Long id) {
        return educationRepo.get(id);
    }

    @RequestMapping(value = "/{id}/experience", method = RequestMethod.GET)
    public List<Experience> getExperience(@PathVariable("id") Long id) {
        return experienceRepository.get(id);
    }

    @RequestMapping(value = "/{id}/zip", method = RequestMethod.GET)
    public List<ZIpCode> getZip(@PathVariable("id") Long id) {
        return zipRepo.getByTutorId(id);
    }

    @RequestMapping(value = "/{id}/education", method = RequestMethod.POST)

    public void updateEducation(@PathVariable("id") Long id, @RequestBody List<Education> map) {
        map.forEach(it -> it.setTutorId(id));
        educationRepo.saveAll(map);

    }

    @RequestMapping(value = "/{id}/experience", method = RequestMethod.POST)

    public void updateExperience(@PathVariable("id") Long id, @RequestBody List<Experience> map) {
        map.forEach(it -> it.setTutorId(id));
        experienceRepository.saveAll(map);

    }

    @RequestMapping(value = "/{id}/upload", method = RequestMethod.POST)
    public Boolean uploadPhoto(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String url = s3Service.save(bytes, "png", "tutor_" + id, DocumentType.PHOTO);
            System.out.println("Url " + url);

            Tutor tutor = tutorRepo.getById(id);
            tutor.setImageUrl(url);
            tutorRepo.save(tutor);
            return true;
        } catch (IOException | IllegalStateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/education/{id}/upload", method = RequestMethod.POST)
    public Boolean uploadEducationDocument(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String url = s3Service.save(bytes, "png", "tutor_" + id, DocumentType.CERTIFICATE);
            System.out.println("Url " + url);

            Education edu = educationRepo.getById(id);
            edu.setUrl(url);
            educationRepo.save(edu);
            return true;
        } catch (IOException | IllegalStateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/address/{id}/upload", method = RequestMethod.POST)
    public Boolean uploadAddressDocument(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String url = s3Service.save(bytes, "png", "tutor_" + id, DocumentType.ADDRESS);
            System.out.println("Url " + url);

            Education edu = educationRepo.getById(id);
            edu.setUrl(url);
            educationRepo.save(edu);
            return true;
        } catch (IOException | IllegalStateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @RequestMapping(method = {RequestMethod.GET})
    public PagedResponse getTutors(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "size", required = false) Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Tutor> pages = tutorRepo.findAll(pageRequest);

        PagedResponse pagedTutor = new PagedResponse();
        pagedTutor.setNext(pages.hasNext());
        pagedTutor.setPage(pages.getNumber());
        pagedTutor.setSize(pages.getTotalPages());
        pagedTutor.setTotalSize(pages.getTotalElements());
        pagedTutor.setContents(pages.getContent());
        return pagedTutor;
    }

    public Tutor updateCredit(@PathVariable("id") Long id, int credit) {

        return service.updateCredit(id, credit);
    }

    @RequestMapping("/{id}")
    public Tutor get(@PathVariable("id") Long id) {
        Tutor tutor = tutorRepo.getById(id);
        if (tutor == null) {
            throw new RequestException("Tutor not found " + id);
        }
        tutor.setReviews(reviewRepo.getByTutor(id));
        tutor.setEducation(educationRepo.get(id));
        tutor.getReviews().forEach(it -> it.setTutor(null));
        tutor.setZipCode(zipRepo.getByTutorId(id));
        tutor.setExperiences(experienceRepository.get(id));
        tutor.setMapping(classSubjectMapRepo.get(id));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dob = df.parse(tutor.getDob());
            tutor.setAge(getDiffYears(dob, new Date()));
        } catch (Exception e) {
            System.out.println("Error in parsig Date");
        }
        return tutor;
    }

    @RequestMapping(method = {RequestMethod.POST})
    public User save(@RequestBody Tutor tutor) {
        if (tutor.getMobile() == null || tutor.getMobile().isEmpty()) {
            throw new RequestException("Mobile number cant be empty");
        }

        Tutor existing = tutorRepo.getByMobile(tutor.getMobile());
        if (existing != null) {
            throw new RequestException("Mobile number already exists.");
        }

        tutor.setCredit(500);
        Tutor updated = tutorRepo.save(tutor);
        String seq = sequenceService.getTutorSequence(updated.getId());
        updated.setSequenceId(seq);
        String s = System.currentTimeMillis() + "";
        updated.setOtp(s.substring(0, 4));
        updated.setOtpValidated(false);
        try {
            smsService.sendSMS(tutor.getMobile(), updated.getOtp());
        } catch(Exception e) {
            log.warn("Send sms failed "+e.getMessage());
        }
        tutorRepo.save(updated);
        System.out.println("ID for new tutor is " + updated.getId());
        if (tutor.getMapping() != null) {
            System.out.println("Mappig for class subject foud.");
            tutor.getMapping().forEach(it -> it.setTutorId(updated.getId()));
            mapService.update(updated.getId(), tutor.getMapping());
        }

        User user = new User();
        user.setRefId(updated.getId());
        user.setType("TUTOR");
        user.setUserId(updated.getMobile());
        user.setPassword(tutor.getMobile());
        user.setActive(Boolean.TRUE);
        userRepo.save(user);
        user.setDetail(updated);

        TransactionData data = new TransactionData();
        data.setAmount(500);
        data.setDateTime(LocalDateTime.now());
        data.setRefId(updated.getId());
        data.setTransType(TransType.DEBIT);
        data.setUser(updated.getId());
        transService.save(data);
        mailUtil.tutorRegister(updated);
        return user;
    }

    @GetMapping("/mail")
    public void testMailTutor(@RequestParam("email") Long tutorid) {
        Tutor t = tutorRepo.getById(tutorid);
        mailUtil.tutorRegister(t);
    }

    private static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        return diff;
    }

    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

}
