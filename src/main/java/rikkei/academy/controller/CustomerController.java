package rikkei.academy.controller;

import rikkei.academy.model.Customer;
import rikkei.academy.sercive.CustomerServiceIMPL;
import rikkei.academy.sercive.ICustomerService;

import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "/customer")
public class CustomerController extends HttpServlet {
    // tiem sercice
    private ICustomerService customerService = new CustomerServiceIMPL();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("-----> goi doGet");
        String action = request.getParameter("action");
        System.out.println("-----> goi action" + action);
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showFormCreate(request, response);
                break;
            case "detail":
                showCustomerByID(request, response);
                break;
            case "edit":
                showFromEdit(request, response);
                break;
            case "delete":
                actionDelete(request, response);
                break;
            default:
                showListCustomer(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-----> goi doPost");
        String action = request.getParameter("action");
        System.out.println("-----> goi doPost" + action);
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                actionCreate(request, response);
                break;
            case "edit":
                actionEdit(request, response);
                break;
        }
    }

    // show list r-crud
    public void showListCustomer(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> customerList = customerService.findAll();
        System.out.println(customerList);
        request.setAttribute("customerList", customerList);
        // gui tinh hieu sang view
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/customer/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // show crete c-crud
    public void showFormCreate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/customer/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void actionCreate(HttpServletRequest request, HttpServletResponse response) {
        int id = customerService.findAll().get(customerService.findAll().size() - 1).getId() + 1;
        String name = request.getParameter("ten");
        System.out.println("--> name" + name);

        String email = request.getParameter("email");
        String address = request.getParameter("address");
        Customer customer = new Customer(id, name, email, address);
        customerService.save(customer);

        request.setAttribute("validate", "create success");

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/customer/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // detail theo id
    public void showCustomerByID(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id--->" + id);
        Customer customer = customerService.findById(id);
        request.setAttribute("customer", customer);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/customer/detail.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Edit U-CRUD
    public void showFromEdit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        // show
        Customer customer = customerService.findById(id);
        System.out.println("customer edit --->" + customer);
        request.setAttribute("customer", customer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/customer/edit.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void actionEdit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        Customer customer1 = new Customer(id, name, email, address);
        customerService.save(customer1);
        showListCustomer(request, response);
    }

    // Delete D-CRUD
    public void actionDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        customerService.deleteById(id);
        showListCustomer(request, response);
    }
}