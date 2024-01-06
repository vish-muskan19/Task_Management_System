package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Entity.Status;
import com.example.service.StatusService;

@Controller
public class StatusController {
	
	private final StatusService statusService;

	public StatusController(StatusService statusService) {
		this.statusService = statusService;
	}

	@RequestMapping("/status")
	public String findAllStatus(Model model) {
		final List<Status> status = statusService.findAllStatus();

		model.addAttribute("status", status);
		return "list-status";
	}

	@RequestMapping("/status/{id}")
	public String findStatusById(@PathVariable("id") Long id, Model model) {
		final  Status status = statusService.findStatusById(id);

		model.addAttribute("status", status);
		return "list-status";
	}

	@GetMapping("/addStatus")
	public String showCreateForm(Status status) {
		return "add-status";
	}

	@RequestMapping("/add-status")
	public String createStatus(Status status, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-status";
		}

		statusService.createStatus(status);
		model.addAttribute("status", statusService.findAllStatus());
		return "redirect:/status";
	}

	@GetMapping("/updateStatus/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Status status = statusService.findStatusById(id);

		model.addAttribute("status", status);
		return "update-status";
	}

	@RequestMapping("/update-status/{id}")
	public String updateStatus(@PathVariable("id") Long id, Status status, BindingResult result, Model model) {
		if (result.hasErrors()) {
			status.setId(id);
			return "update-status";
		}

		statusService.updateStatus(status);
		model.addAttribute("status", statusService.findAllStatus());
		return "redirect:/status";
	}

	@RequestMapping("/remove-statu/{id}")
	public String deleteStatus(@PathVariable("id") Long id, Model model) {
		statusService.deleteStatus(id);

		model.addAttribute("statu", statusService.findAllStatus());
		return "redirect:/status";
	}
}
