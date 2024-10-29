/**
 * @author: Sai Rachana Venna
 * All the features or functionality related to products and their categories would be available in this package
 * 
 * <b>Persistence Update (Milestone 2):
 * <ul>
 * <li>Created Product, Category, ProductRepository and CategoryRepository java classes
 * <li>Defined bi-directional relationship between Product and Category classes
 * <li>Added comments or documentations for newly added features in Product and Category classes that were not covered in class
 * <li>New features used: Product class used nullable and Category class used cascade and orphanRemoval
 * <li>Added additional finders findByName and findByCategoryName in ProductRepository and findByName in CategoryRepository
 * <li>Updated data.sql to insert seed data into Product and Category tables
 * <li>Added unit tests for ProductRepository and CategoryRepository to demonstrate that both of them are working
 * </ul>
 * 
 * <b>Non-Persistence Update (Milestone 3):
 * <ul>
 * <li>Created ProductService, CategoryService java classes
 * <li>Added service methods related to products and categories including the usage of custom finders defined in ProductRepository and CategoryRepository
 * <li>Added logging for both ProductService and CategoryService
 * <li>Added additional logic in the service methods: createProduct, updateProduct, deleteProduct in ProductService by using method reference operator and exception handling
 * <li>Added additional logic in the service methods: updateCategory, deleteCategory in CategoryService by using exception handling
 * <li>Added comments or documentation for the above mentioned additional logic in ProductService and CategoryService as they were not discussed in class
 * <li>Added unit tests for ProductService and CategoryService to demonstrate that both of them are working 
 * </ul>
 */
package edu.depaul.cdm.se452.groupProject.products;