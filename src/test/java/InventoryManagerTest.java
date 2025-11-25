import org.example.InventoryManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class InventoryManagerTest {


    @BeforeAll
    public static void setUpClass() {
    }

    @Test
    public void testAgregarItemNuevo() {
        InventoryManager manager = new InventoryManager();
        manager.addItem("Laptop", 5);
        assertEquals(5, manager.getStock("Laptop"));
    }

    @Test
    public void testAgregarCantidadNegativa() {
        InventoryManager manager = new InventoryManager();
        assertThrows(IllegalArgumentException.class, () -> {
            manager.addItem("Mouse", -1);
        });
    }

    @Test
    public void testNombreVacio() {
        InventoryManager manager = new InventoryManager();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            manager.addItem("", 5);
        });
        assertEquals("El nombre del producto no puede estar vacío", ex.getMessage());
    }

    @Test
    public void testNombreMuyCorto() {
        InventoryManager manager = new InventoryManager();

        assertThrows(IllegalArgumentException.class, () -> {
            manager.addItem("A", 10);
        });
    }

    @Test
    public void testSumarStockExistente() {
        InventoryManager manager = new InventoryManager();
        manager.addItem("Teclado", 10);
        manager.addItem("Teclado", 20); // Agrego 20 +
        assertEquals(30, manager.getStock("Teclado")); // Total 30
    }

    @Test
    public void testExcederLimiteCantidad() {
        InventoryManager manager = new InventoryManager();
        // Máximo es 1000
        assertThrows(IllegalArgumentException.class, () -> {
            manager.addItem("Monitor", 1001);
        });
    }
   //TEST AGREGADOS PARA MEJOR COBERTURA Y RAMAS

    @Test
    public void testConsultarStockInexistente() {
        InventoryManager manager = new InventoryManager();

        // Consultamos un producto que NO hemos agregado
        int stock = manager.getStock("ProductoFantasma");

        // Debería devolver 0 porque no existe en el mapa
        assertEquals(0, stock);
    }
    //AGREGAMOS UN VACIO PERO AGREGAREMOS UN TEST PARA EL NULL POR LAS DUDAS
    @Test
    public void testNombreNulo() {
        InventoryManager manager = new InventoryManager();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            manager.addItem(null, 5);
        });
        assertEquals("El nombre del producto no puede estar vacío", ex.getMessage());
    }
    @Test
    public void testNombreMuyLargo() {
        InventoryManager manager = new InventoryManager();

        // EL MAXIMO ES 50
        String nombreLargo = "A".repeat(51);

        assertThrows(IllegalArgumentException.class, () -> {
            manager.addItem(nombreLargo, 10);
        });
    }
}
