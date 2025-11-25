package org.example;
import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    // Mapa para guardar Producto -> Cantidad

    private Map<String, Integer> inventory = new HashMap<>();

    public void addItem(String item, int quantity) {
        // 1. Validaciones de entrada (Nombre)
        if (item == null || item.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        if (item.length() < 2 || item.length() > 50) {
            throw new IllegalArgumentException("El nombre debe tener entre 2 y 50 caracteres");
        }

        // 2. Validaciones de entrada (Cantidad)
        if (quantity < 1 || quantity > 1000) {
            throw new IllegalArgumentException("La cantidad debe estar entre 1 y 1000");
        }

        // 3. Lógica de negocio: Agregar o actualizar stock
        if (inventory.containsKey(item)) {
            // Si ya existe, obtenemos el actual y sumamos
            int stockActual = inventory.get(item);
            inventory.put(item, stockActual + quantity);
        } else {
            // Si es nuevo, lo agregamos directamente
            inventory.put(item, quantity);
        }
    }

    // Método auxiliar para verificar en los tests (necesario para assertEquals)
    public int getStock(String item) {
        if (inventory.containsKey(item)) {
            return inventory.get(item);
        }
        return 0;
    }

}
