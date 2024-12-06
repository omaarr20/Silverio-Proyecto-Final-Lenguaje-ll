import com.sakila.models.Inventory;
import com.sakila.utils.ExportUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        List<Inventory> inventoryList = new ArrayList<>();
        Inventory inventory = new Inventory();
        inventory.setInventoryId(1);
        inventory.setFilmId(10);
        inventory.setStoreId(2);
        inventoryList.add(inventory);

        
        ExportUtils.exportInventoryToCSV(inventoryList, "inventories.csv");
        ExportUtils.exportInventoryToJSON(inventoryList, "inventories.json");

        System.out.println("Exportación completada.");
    }
}
