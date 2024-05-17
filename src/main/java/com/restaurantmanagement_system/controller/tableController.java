package com.restaurantmanagement_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.restaurantmanagement_system.model.Table;
import com.restaurantmanagement_system.service.tableServiceImp;

import java.util.*;

@Controller
public class tableController {
	
	@Autowired
	private tableServiceImp tableServiceImp;
	
   @RequestMapping( value = "/tables" , method = RequestMethod.GET)
	public ModelAndView viewAllTables() {
		   ModelAndView modelAndView = new ModelAndView();
		   List<Table> tableList = tableServiceImp.getAllTable();
		   System.out.println("Table List: " + tableList);
		   modelAndView.addObject("tableList", tableList);
		   modelAndView.setViewName("tableManagementPage");
		   return modelAndView;
	}
	  
	   @RequestMapping( value = "/occupyTable/{tableNumber}" , method = RequestMethod.POST)
	   public ModelAndView occupyTable( @PathVariable int tableNumber) {
	       ModelAndView modelAndView = new ModelAndView();
	       tableServiceImp.updateTableStatus(tableNumber, true);
	       modelAndView.setViewName("redirect:/tables");
	       return modelAndView;
	   }
	   
	   @RequestMapping( value = "/vacateTable/{tableNumber}" ,method = RequestMethod.POST)
	   public ModelAndView vacateTable( @PathVariable int tableNumber) {
	       ModelAndView modelAndView = new ModelAndView();
	       tableServiceImp.updateTableStatus(tableNumber, false);
	       modelAndView.setViewName("redirect:/tables");
	       return modelAndView;
	   }
	   
	
}
