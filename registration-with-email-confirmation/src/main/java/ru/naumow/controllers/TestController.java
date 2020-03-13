package ru.naumow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView process(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        //Map<String, String> mailData = new HashMap<>();
        /*mailData.put("confirm_id", confirmId);

        StringWriter mailOut = new StringWriter();*/

        //modelAndView.addObject("confirm_id", "hi");
        modelAndView.setViewName("test");
        //modelAndView.addObject("asd", session.getAttribute("user-email"));
        return modelAndView;
    }


}
