package Core.Services;

import Core.Models.exceptions.CustomerException;
import Core.Interfaces.CustomerServiceInterface;
import Core.Models.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.checkerframework.checker.units.qual.C;

public class CustomerService implements CustomerServiceInterface {

    HashMap<UUID, Customer> customers = new HashMap<>();

    public Customer createCustomer(String username, String email, LocalDate dateOfBirth) throws CustomerException {
        UUID newID = UUID.randomUUID();
        if (dateOfBirth.isAfter(LocalDate.now().minusYears(18))) {
            throw CustomerException.underAge();
        }
        else if (!email.contains("@") || email.split("@").length != 2) {
            throw CustomerException.invalidEmail();
        }
        Customer newCustomer = new Customer(newID, username, email, dateOfBirth);
        customers.put(newID, newCustomer);
        return new Customer(newCustomer);
    }

    @Override
    public Customer getCustomerById(UUID id) {
        if(customers.containsKey(id)) {
            Customer customer = customers.get(id);
            return new Customer(customer);
        }
        else {
            throw CustomerException.customerDoesNotExist();
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws CustomerException {
        validateUpdatedCustomer(customer);
        customers.put(customer.getId(), customer);
    }

    private void validateUpdatedCustomer(Customer customer){
        if(!customers.containsKey(customer.getId())){
            throw CustomerException.customerDoesNotExist();
        }
        else if(customer.getEmail() != null && (!customer.getEmail().contains("@") || customer.getEmail().split("@").length != 2)){
            throw CustomerException.invalidEmail();
        }
        else if(customer.getDateOfBirth() != null && customer.getDateOfBirth().isAfter(LocalDate.now().minusYears(18))){
            throw CustomerException.underAge();
        }
    }

    @Override
    public void deleteCustomer(UUID id) {
        if(customers.containsKey(id)) {
            customers.remove(id);
        }
        else {
            throw CustomerException.customerDoesNotExist();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<Customer>(customers.values());
    }

    @Override
    public void deleteAllCustomers() {
        customers.clear();
    }
    


    //o  CRUD+ (Create, Read, Update, Delete, Read all, Delete all) 




}
