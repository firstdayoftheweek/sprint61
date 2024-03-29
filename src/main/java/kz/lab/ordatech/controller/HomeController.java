package kz.lab.ordatech.controller;

import kz.lab.ordatech.model.ApplicationRequest;
import kz.lab.ordatech.repository.ApplicationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ApplicationRequestRepository applicationRequestRepository;

    @GetMapping(value = "/home")
    public String getHomePage(Model model){
        List<ApplicationRequest> applicationRequestList = applicationRequestRepository.findAll();
        model.addAttribute("appList", applicationRequestList);
        return "home";
    }

    @GetMapping(value = "/addrequest")
    public String addRequestPage(){
        return "addrequest";
    }

    @PostMapping(value = "/addNewReq")
    public String addNewReq(@RequestParam(value = "user") String userName,
                            @RequestParam(value = "course") String courseName,
                            @RequestParam(value = "phone") String phoneNumber,
                            @RequestParam(value = "comment") String comment){
        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setCommentary(comment);
        applicationRequest.setPhone(phoneNumber);
        applicationRequest.setUserName(userName);
        applicationRequest.setCourseName(courseName);
        applicationRequest.setHandled(false);
        applicationRequestRepository.save(applicationRequest);
        return "redirect:/home";
    }

    @GetMapping(value = "/details/{id}")
    public String detailsPage(@PathVariable(value = "id") Long id,
                              Model model){
        ApplicationRequest appRequest = applicationRequestRepository.findById(id).orElseThrow();
        model.addAttribute("appRequest", appRequest);
        return "details";
    }

    @GetMapping(value = "/deleteRequest/{id}")
    public String deleteRequest(@PathVariable(value = "id") Long id){
        applicationRequestRepository.deleteById(id);
        return "redirect:/home";
    }

    @GetMapping(value = "/changeRequest/{id}")
    public String changeRequest(@PathVariable(value = "id") Long id){
        ApplicationRequest request = applicationRequestRepository.findById(id).orElseThrow();
        request.setHandled(true);
        applicationRequestRepository.save(request);
        return "redirect:/home";
    }
}
