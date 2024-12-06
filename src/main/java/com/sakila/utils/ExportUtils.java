package com.sakila.utils;

import com.sakila.models.Inventory; // Asegúrate de importar Inventory
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportUtils {

    // Método para exportar la lista de inventarios a un archivo CSV
    public static void exportInventoryToCSV(List<Inventory> inventoryList, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("Inventory ID, Film ID, Store ID\n"); // Encabezados de las columnas

            for (Inventory inventory : inventoryList) {
                writer.append(String.format("%d,%d,%d\n",
                        inventory.getInventoryId(),  // Método correcto
                        inventory.getFilmId(),      // Método correcto
                        inventory.getStoreId()));   // Método correcto
            }

            System.out.println("Exportación a CSV completada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para exportar la lista de inventarios a un archivo JSON
    public static void exportInventoryToJSON(List<Inventory> inventoryList, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(fileName), inventoryList);
            System.out.println("Exportación a JSON completada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
