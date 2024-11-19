/**
 * @author: Bhumika Ramesh
 *          This package handles all Final Milestone features related to the
 *          Customer module,
 *          including integration with the Orders and other related modules for
 *          seamless account and customer management.
 * 
 *          <p>
 *          Key functionalities implemented in this package:
 *          </p>
 * 
 *          <ul>
 *          <li><b>Integration of Thymeleaf:</b>
 *          <p>
 *          Integrated Thymeleaf for the Customer module to manage front-end
 *          views, ensuring dynamic rendering of pages for various operations
 *          such as viewing,
 *          editing, and creating customer records.
 *          </p>
 *          </li>
 *          <li><b>CRUD Operations:</b>
 *          <p>
 *          Implemented complete Create, Read, Update, and Delete functionality
 *          for Customer records using Customer.java, CustomerRepository.java,
 *          CustomerController.java, CustomerService.java and
 *          CustomerServiceImpl.java
 *          Customers can perform the following actions:
 *          <ul>
 *          <li>View home page</li>
 *          <li>Create new customer profiles</li>
 *          <li>List all the customers</li>
 *          <li>Edit existing customer details</li>
 *          <li>View detailed customer information</li>
 *          <li>Delete a customer</li>
 *          </ul>
 *          </p>
 *          </li>
 *          <li><b>Circular Dependency Resolution:</b>
 *          <p>
 *          Addressed and resolved a circular dependency issue during customer
 *          deletion, ensuring proper cleanup of related data and casacding
 *          relationships without affecting the application's stability.
 *          </p>
 *          </li>
 *          <li><b>Authentication Functionality:</b>
 *          <p>
 *          Developed user authentication features with validation, allowing
 *          customers to:
 *          <ul>
 *          <li>Sign up for new accounts</li>
 *          <li>Log in securely using their credentials</li>
 *          </ul>
 *          </p>
 *          </li>
 *          <li><b>API Testing with Swagger-UI:</b>
 *          <p>
 *          Tested and verified all APIs related to the Customer module using
 *          Swagger-UI to ensure functionality, performance, and correctness of
 *          the endpoints.
 *          </p>
 *          </li>
 *          <b>Endpoints:</b>
 *          <ul>
 *          <li><a href=
 *          "http://localhost:8080/customer/signup">http://localhost:8080/customer/signup</a>
 *          - Customer sign-up</li>
 *          <li><a href=
 *          "http://localhost:8080/customer/login">http://localhost:8080/customer/login</a>
 *          - Customer login</li>
 *          <li><a href=
 *          "http://localhost:8080/customer/home/101">http://localhost:8080/customer/home/101</a>
 *          - Home page for customerId 101</li>
 *          <li><a href=
 *          "http://localhost:8080/customer/">http://localhost:8080/customer/</a>
 *          - List of all customers</li>
 *          <li><a href=
 *          "http://localhost:8080/customer/new">http://localhost:8080/customer/new</a>
 *          - Add a new customer</li>
 *          <li><a href=
 *          "http://localhost:8080/customer/101">http://localhost:8080/customer/101</a>
 *          - View details for customerId 101</li>
 *          <li><a href=
 *          "http://localhost:8080/customer/edit/101">http://localhost:8080/customer/edit/101</a>
 *          - Edit details for customerId 101</li>
 *          </ul>
 *          </ul>
 */
package edu.depaul.cdm.se452.groupProject.account;