package Core.Models.exceptions;

public class CustomerException extends RuntimeException {
    public static final String underAge = "User has to be 18 years old";
    public static final String invalidEmail = "Invalid email";
    public static final String customerDoesNotExist = "Customer does not exist";
    
    public CustomerException(String message) {
        super(message);
    }
    public static CustomerException underAge()
    {
        return new CustomerException(underAge);
    }
    public static CustomerException customerDoesNotExist()
    {
        return new CustomerException(customerDoesNotExist);
    }
    public static CustomerException invalidEmail()
    {
        return new CustomerException(invalidEmail);
    }
    

}
