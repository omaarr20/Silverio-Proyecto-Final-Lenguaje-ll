import org.hibernate.Session;

import com.sakila.utils.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSession()) {
            System.out.println("Conexión exitosa con la base de datos.");
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
