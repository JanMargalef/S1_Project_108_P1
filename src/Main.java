import BUSSINESS.ProductManager;
import BUSSINESS.TendaManager;
import PRESENTATION.Controller;
import PRESENTATION.ManagerUI;

import java.io.IOException;

/**
 * Classe principal que inicia l'aplicació.
 * Crea instàncies del gestor d'interfície d'usuari (ManagerUI),
 * del gestor de productes (ProductManager), del gestor de tendes (TendaManager)
 * i del controlador (Controller). Finalment, executa el mètode run() del controlador.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ManagerUI managerUI = new ManagerUI();
        ProductManager managerProducte = new ProductManager();
        TendaManager managerTenda = new TendaManager();
        Controller controller = new Controller(managerUI,managerTenda,managerProducte);
        controller.run();
    }
}