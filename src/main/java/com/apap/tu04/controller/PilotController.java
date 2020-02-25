package com.apap.tu04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tu04.model.FlightModel;
import com.apap.tu04.model.PilotModel;
import com.apap.tu04.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;

	@RequestMapping("/")
	private String home() {
		return "Home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value= "/pilot/view", method = RequestMethod.GET)
	public String viewPilotId(@RequestParam(value = "licenseNumber", required = true) String licenseNumber, Model model2) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseumber(licenseNumber);
		if (pilot != null) {
			List<FlightModel> flight = pilot.getPilotFlight();
			model2.addAttribute("pilot", pilot);
			model2.addAttribute("flight", flight);
			return "view-pilot.html";	
		}
		else {
			return "notfound-1.html";
		}
	}
	
	@RequestMapping(value= "/pilot/viewAll")
	public String viewAllPilot(Model model) {
		List<PilotModel> pilot = pilotService.getAllPilot();
		model.addAttribute("pilot", pilot);
		return "viewall-pilot.html";	
	}
	
	@RequestMapping(value= "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
	public String deletePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
			try {
			PilotModel pilot = pilotService.getPilotDetailByLicenseumber(licenseNumber);
			pilotService.deletePilot(pilot);
			return "delete.html";	
			}
			catch (Exception e) {
				return "notfound-2.html";
			}
	}
	
	@RequestMapping(value= "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	public String updatePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseumber(licenseNumber);
		model.addAttribute("pilot", pilot);
		return "update-pilot.html";	
	}
	
	@RequestMapping(value = "/pilot/update", method = RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute PilotModel pilot, Long id) {
		pilotService.addPilot(pilot);
		return "update.html";
	}
	
}