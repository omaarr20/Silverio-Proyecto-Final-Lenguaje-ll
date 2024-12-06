

import com.sakila.controllers.CustomerController;
import com.sakila.models.Customer;
import java.util.List;

public class CustomerControllerMain {
    public static void main(String[] args) {
        CustomerController customerController = new CustomerController();

        // Obtener todos los clientes
        List<Customer> customers = customerController.getAllCustomers();
        if (customers != null && !customers.isEmpty()) {
            System.out.println("Clientes:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        } else {
            System.out.println("No se encontraron clientes.");
        }

        // Obtener un cliente por ID
        int customerId = 1; // Cambia este ID para probar con otros
        Customer customer = customerController.getCustomerById(customerId);
        if (customer != null) {
            System.out.println("Cliente encontrado: " + customer);
        } else {
            System.out.println("Cliente con ID " + customerId + " no encontrado.");
        }

        // Crear un nuevo cliente
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("John");
        newCustomer.setLastName("Doe");
        newCustomer.setEmail("john.doe@example.com");
        newCustomer.setActive(true);
        customerController.addCustomer(newCustomer);

        // Actualizar un cliente
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customerController.updateCustomer(customer);

        // Eliminar un cliente
        customerController.deleteCustomer(customerId);
    }
}
