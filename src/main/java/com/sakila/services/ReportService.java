package com.sakila.services;

import com.sakila.models.Inventory;
import com.sakila.utils.ExportUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.sakila.utils.HibernateUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReportService {

    // Método para obtener el total de pagos por tienda
    public long getPaymentsByStore(int storeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Long> query = session.createQuery("select sum(p.amount) from Payment p where p.store.id = :storeId", Long.class);
        query.setParameter("storeId", storeId);
        long totalPayments = query.uniqueResult();
        session.close();
        return totalPayments;
    }

    // Método para obtener el total de rentas por tienda
    public long getRentalsByStore(int storeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Long> query = session.createQuery("select count(r) from Rental r where r.store.id = :storeId", Long.class);
        query.setParameter("storeId", storeId);
        long totalRentals = query.uniqueResult();
        session.close();
        return totalRentals;
    }

    // Método para calcular el aging de una cuenta
    public long calculateAging(String rentalDate) {
        LocalDate rentalLocalDate = LocalDate.parse(rentalDate); // Asegúrate de tener el formato adecuado
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(rentalLocalDate, currentDate); // Calcula la diferencia en días
    }

    // Método para obtener el promedio de inventarios por tienda
    public double getAverageInventoriesByStore() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Double> query = session.createQuery("select avg(count(i)) from Inventory i group by i.store", Double.class);
        double avg = query.uniqueResult();
        session.close();
        return avg;
    }

    // Método para obtener el total de inventarios
    public long getTotalInventories() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Long> query = session.createQuery("select count(*) from Inventory", Long.class);
        long total = query.uniqueResult();
        session.close();
        return total;
    }

    // Método para exportar el inventario a CSV y JSON
    public void exportInventoryReport(List<Inventory> inventoryList, String fileName) {
        // Exportar a CSV
        ExportUtils.exportInventoryToCSV(inventoryList, fileName + ".csv");

        // Exportar a JSON
        ExportUtils.exportInventoryToJSON(inventoryList, fileName + ".json");
    }
}
