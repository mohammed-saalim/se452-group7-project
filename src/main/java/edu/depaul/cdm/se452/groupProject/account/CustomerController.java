package edu.depaul.cdm.se452.groupProject.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer/list";
    }

    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer/details";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/create";
    }

    @PostMapping
    public String createCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        customerService.createCustomer(customer);
        redirectAttributes.addFlashAttribute("message", "Customer created successfully!");
        return "redirect:/customer/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customerDetails,
            RedirectAttributes redirectAttributes) {
        customerService.updateCustomer(id, customerDetails);
        redirectAttributes.addFlashAttribute("message", "Customer updated successfully!");
        return "redirect:/customer/";
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        customerService.deleteCustomer(id);
        redirectAttributes.addFlashAttribute("message", "Customer deleted successfully!");
        return "redirect:/customer/";
    }

    // New API for Signup
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/signup"; // View for signup form
    }

    @PostMapping("/signup")
    public String signupCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        if (customerService.findByEmail(customer.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("error", "Email is already registered!");
            return "redirect:/customer/signup";
        }
        customerService.createCustomer(customer);
        redirectAttributes.addFlashAttribute("message", "Signup successful! Please login.");
        return "redirect:/customer/login";
    }

    // New API for Login
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "customer/login"; // View for login form
    }

    @PostMapping("/login")
    public String loginCustomer(@ModelAttribute LoginForm loginForm, RedirectAttributes redirectAttributes,
            Model model) {
        Customer customer = customerService.findByEmail(loginForm.getEmail());
        if (customer == null || !customer.getPassword().equals(loginForm.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Invalid email or password!");
            return "redirect:/customer/login";
        }
        // model.addAttribute("customer", customer);
        // return "redirect:/customer/" + customer.getCustomerId(); // Redirect to
        // customer details
        model.addAttribute("customer", customer);
        return "redirect:/customer/home/" + customer.getCustomerId(); // Redirect to home page

    }

    // Home API
    @GetMapping("/home/{id}")
    public String homePage(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer); // Pass customer details to the view
        return "customer/home"; // This corresponds to your home.html
    }
}
