package com.restaurantmanagement_system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.restaurantmanagement_system.model.Order;
import com.restaurantmanagement_system.service.orderServiceImp;

@Controller
public class OrderController {
	
	@Autowired
	private orderServiceImp orderServiceImp;
	
	private Logger order_logger = LoggerFactory.getLogger(OrderController.class); 
	
	@RequestMapping( value = {"/" , "/orders"} , method = RequestMethod.GET )
	public ModelAndView getAllOrders(){
		ModelAndView modelAndView = new ModelAndView();
		List<Order> orderList = orderServiceImp.getAllOrders();
	    System.out.println("Order List: " + orderList);
		modelAndView.addObject("orderList", orderList);
		order_logger.info("Gathering up All Orders");
		modelAndView.setViewName("orders");
		return modelAndView;
	}
	
	@RequestMapping( value = "/createOrder", method = RequestMethod.GET )
	public ModelAndView CreateOrder(){
		ModelAndView modelAndView = new ModelAndView();
		Order order = new Order();
		modelAndView.addObject("order", order );
		modelAndView.setViewName("createOrderForm");
		return modelAndView;
	}
	
	@RequestMapping( value = "/saveOrder" , method = RequestMethod.POST )
	public ModelAndView saveOrder( @ModelAttribute Order order , RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		int orderResult = orderServiceImp.createOrder(order);
		
		if( orderResult > 0 ) {
			redirectAttributes.addFlashAttribute("orderResult", orderResult ); // carried out items enhancements successfully
			order_logger.info(" Order Created : {} " + order.getOrderId());
			modelAndView.setViewName("redirect:/orders");
		}
		else {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to create order!.Plesase Try again!!");
			modelAndView.setViewName("redirect:/createOrder");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/orders/delete/{orderId}", method = RequestMethod.GET)
	public ModelAndView deleteOrder(@PathVariable int orderId, RedirectAttributes redirectAttributes) {
	    ModelAndView modelAndView = new ModelAndView();
	    
	    modelAndView.setViewName("redirect:/orders");
	    int rowEffected = orderServiceImp.deleteOrder(orderId);
	    if( rowEffected == 0 ) {
			order_logger.error(" Order Incapacitatingly failed to delete!");
	    	redirectAttributes.addFlashAttribute("rowEffected", rowEffected);
	    }
	    else {
	    	redirectAttributes.addFlashAttribute("errorMessage","Items Deleted Vivaciously");
			order_logger.info(" Order Deleted : {} " + orderId);
	    }
	    
	    return modelAndView;
	}
	
	@RequestMapping(value = "/orders/edit/{orderId}", method = RequestMethod.GET)
	public ModelAndView editOrder(@PathVariable int orderId) {
	    ModelAndView modelAndView = new ModelAndView();
	    Order order = orderServiceImp.getOrderById(orderId);
		order_logger.info(" Order Created : {} ");
	    modelAndView.addObject("order",order);
	    order_logger.info("Editing Order : {} " + orderId);
	    modelAndView.setViewName("updateForm");
	    return modelAndView;
	}
	
	   @RequestMapping(value = "/orders/update/{orderId}", method = RequestMethod.POST)
	    public ModelAndView updateOrder(@PathVariable int orderId, @ModelAttribute Order updatedOrder, RedirectAttributes redirectAttributes) {
	        ModelAndView modelAndView = new ModelAndView();
	        updatedOrder.setOrderId(orderId);
	        int result = orderServiceImp.updateOrder(updatedOrder);
	        if (result > 0) {
	            redirectAttributes.addFlashAttribute("updateSuccess", true);
	            order_logger.info("Order Updated Successfully : {} " + orderId );
	        } else {
	        	order_logger.warn("Erroneous popped while updating!" + orderId);
	            redirectAttributes.addFlashAttribute("updateError", true);
	        }
	        modelAndView.setViewName("redirect:/orders");
	        return modelAndView;
	    }
	   
	   @RequestMapping(value = "/checkout/{tableNumber}", method = RequestMethod.GET)
	   public ModelAndView checkout(@PathVariable int tableNumber) {
	       ModelAndView modelAndView = new ModelAndView();
	       List<Order> orders = orderServiceImp.getOrdersFromTable(tableNumber);
	       
//	       double totalBill = 0;
//	       for( Order order : orders ) {
//	    	   totalBill =  order.getQuantity() * order.getTotalPrices();
//	    	
//	       }
	       
	       double totalBill = orders.stream().mapToDouble( order -> order.getTotalPrices() * order.getQuantity()).sum();
	 	   modelAndView.addObject("totalBill", totalBill);
	  	   modelAndView.addObject("orders", orders);
	  	   order_logger.info("Aggregating Total Bills" + totalBill );
	       modelAndView.setViewName("checkout");
	       return modelAndView;
	   }
}
