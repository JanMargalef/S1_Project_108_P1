package PRESENTATION;

import BUSSINESS.ENTITIES.Producte;
import BUSSINESS.ProductManager;
import BUSSINESS.TendaManager;

import java.io.IOException;

/**
 * Classe que actua com a controlador per gestionar la lògica de negoci entre la interfície d'usuari (UI),
 * el gestor de productes i el gestor de tendes.
 */
public class Controller {
    private ManagerUI managerUI;
    private ProductManager managerProducte;
    private TendaManager managerTenda;


    /** Constructor del controller. Inicia tot el programa fent llegir els dos Json per poder fer que el sistema funcioni
     * @param managerUI pasar un objeto ManagerUI
     * @param managerTenda pasar un objeto ManagerTenda
     * @param managerProducte pasar un objeto ManagerProducte
     */
    public Controller(ManagerUI managerUI, TendaManager managerTenda, ProductManager managerProducte) {
        this.managerUI = managerUI;
        this.managerProducte = managerProducte;
        this.managerTenda = managerTenda;


        managerUI.showIniciMenu(); // mostrem el logo
        loadFiles();
        managerUI.startingMessage();

    }

    /**
     * Metode run, aquest metode crida a les funcions dels altres managers per a poder interactuar entre si.
     * @throws IOException // controla totes les exepcions que es puguin generar a causa formats incorrectes
     */
    public void run() throws IOException{

        while (true){
            switch (managerUI.showMenuPrincipal()){ // mostrar el menu principal, depenent de elecció anem a un menu o un altre
                case MenuOpcions.GESTIO_PRODUCTES:
                    loadFiles();
                    gestioProductes();
                    saveFiles();

                    break;

                case MenuOpcions.GESTIO_TENDES:
                    loadFiles();
                    gestioTendes();
                    saveFiles();
                    break;

                case MenuOpcions.BUSCAR_PRODUCTES:
                    loadFiles();
                    buscarProductes();
                    saveFiles();
                    break;


                case MenuOpcions.LLISTAR_TENDES:
                    loadFiles();
                    llistarTendes();
                    saveFiles();


                    break;

                case MenuOpcions.CARRITO:
                    loadFiles();
                    //carrito();
                    saveFiles();
                    break;


                case MenuOpcions.EXIT:
                    loadFiles();
                    this.managerUI.exit();
                    saveFiles();
                    System.exit(0);
                    break;
            }
        }
    }


    /**
     * Carrega les dades de productes i tendes des de les respectives APIs.
     * Verifica la connexió amb les APIs i gestiona possibles errors durant la càrrega de dades.
     * En cas d'errors crítics, mostra missatges d'error i surt del sistema.
     */
    private void loadFiles() {

        int apiConnectedPoduct = 0;
        int apiConnectedTendes = 0;

        apiConnectedPoduct = this.managerProducte.loadData(); // carreguem els productes

        apiConnectedTendes = this.managerTenda.loadData();// carreguem les tendes
        // si hi ha un error de qualsevol tipus, missatge de error i sortir del sistema

        switch (apiConnectedPoduct){
            case 0:
                this.managerUI.showIniciMenuErrorLoadProductes();
                this.managerUI.exit();
                System.exit(0);
                break;
            case 1:
                break;
            case 2:
                this.managerUI.showIniciMenuErrorLoadProductesApi();
                break;
        }

        switch (apiConnectedTendes){
            case 0:
                this.managerUI.showIniciMenuErrorLoadTendes();
                this.managerUI.exit();
                System.exit(0);
                break;
            case 1:
                break;
            case 2:
                this.managerUI.showIniciMenuErrorLoadTendesApi();
                break;
        }
    }


    /**
     * Carrega les dades de productes i tendes des de les respectives APIs.
     * Verifica la connexió amb les APIs i gestiona possibles errors durant la càrrega de dades.
     * En cas d'errors crítics, mostra missatges d'error i surt del sistema.
     *
     * @throws IOException Si es produeix un error crític durant la càrrega de dades i es surt del sistema.
     *
     */
    private void saveFiles() throws IOException {

        int apiConnectedPoduct = 0;
        int apiConnectedTendes = 0;

        apiConnectedPoduct = this.managerProducte.saveData(); // carreguem els productes
        apiConnectedTendes = this.managerTenda.saveData();// carreguem les tendes

        switch (apiConnectedPoduct){
            case 0:
                this.managerUI.showIniciMenuErrorLoadProductes();
                this.managerUI.exit();
                System.exit(0);
                break;
            case 1:
                break;
            case 2:
                this.managerUI.showIniciMenuErrorLoadProductesApi();
                break;
        }

        switch (apiConnectedTendes){
            case 0:
                this.managerUI.showIniciMenuErrorLoadTendes();
                this.managerUI.exit();
                System.exit(0);
                break;
            case 1:
                break;
            case 2:
                this.managerUI.showIniciMenuErrorLoadTendesApi();
                break;
        }
    }



//    private void carrito() {
//        switch(managerUI.selectCarretFunction(managerTenda.getCarrito(), managerTenda.calculCarretClientTenda(false))){
//            case 1:
//                if(managerUI.makeSure("checkout")){
//                    managerTenda.checkout();
//                    managerUI.checkoutCompra(managerTenda.getCarrito().getProductesCarrito(),managerTenda.getTendes());
//                    managerTenda.clearCart();
//                    managerUI.clearCartDone();
//                }
//                break;
//
//            case 2:
//                if(managerUI.makeSure("clear your cart")) {
//                    managerTenda.clearCart();
//                    managerUI.clearCartDone();
//                }
//                break;
//
//            default:
//                break;
//        }
//    }

    private void llistarTendes() {
        int indexTenda = 0;
        int indexProduct = 0;
        Producte Producte;

        while(indexTenda != -1){
            indexTenda = managerUI.requestShops(managerTenda.getTendes());

            if(indexTenda < managerTenda.getTendes().size()){
                indexProduct = managerUI.requestProduct(managerTenda.getTendes().get(indexTenda));
                if(indexProduct < managerTenda.getTendes().get(indexTenda).getCatalogue().size()){
                    //producte = managerTenda.getTendes().get(indexTenda).getCatalogue().get(indexProduct);
                    Producte = managerTenda.getTendes().get(indexTenda).getCatalogue().get(indexProduct);

                    switch(managerUI.requestOperation()){
                        case 1:
                            managerUI.showReviews(Producte.getName(), managerProducte.getProductes());
                            break;

                        case 2:
                            managerProducte.addReviews(Producte.getName(), managerUI.reviewStars(), managerUI.reviewText(Producte));
                            break;

                        case 3:
                            managerTenda.addToCarret(Producte);
                            break;
                    }
                    indexTenda = -1;
                }
            }
            else{
                indexTenda = -1;
            }
        }
    }

    private void buscarProductes() {
        String query = managerUI.requestQuery();
        Producte producte = managerUI.showAskedProducts(managerTenda.getTendes(),managerProducte.getProductes(), query);
        if(producte!=null){
            int option = managerUI.requestReviewTipo();
            if(option == 1){
                managerUI.showReviews(producte);
            } else if (option == 2) {
                managerProducte.addReviews(producte, managerUI.reviewStars(), managerUI.reviewText(producte));
            }
        }
    }

    private void gestioTendes() {
        MenuGestioTendes select = managerUI.menuGestioTendes();
        switch (select){

            case MenuGestioTendes.CREAR_TENDA:
                Float threshold;
                String sponsoredBrand;
                String nom = managerUI.requestnomNouTenda();
                String descripcio = managerUI.requestDescripcioNouTenda();
                Integer anyFundacio = managerUI.requestAnyFundacioNouTenda();
                String modelNegoci = managerUI.requestModelNegociNouTenda();
                switch (modelNegoci){
                    case "LOYALTY":
                        threshold = managerUI.requestLoyalty();
                        if(managerTenda.addTendaLoyalty(nom,descripcio ,anyFundacio ,modelNegoci,threshold)){
                            managerUI.tendaExistent();
                        }else {
                            managerUI.tendaCreat();
                            select = MenuGestioTendes.BACK;
                        }
                        break;
                    case "SPONSORED":
                        sponsoredBrand = managerUI.requestSponsoredBrand();
                        if(managerTenda.addTendaSponsored(nom,descripcio ,anyFundacio ,modelNegoci,sponsoredBrand)){
                            managerUI.tendaExistent();
                        }else {
                            managerUI.tendaCreat();
                            select = MenuGestioTendes.BACK;
                        }
                        break;
                    case "MAX_PROFIT":
                        if(managerTenda.addTendaMaxProfit(nom,descripcio ,anyFundacio ,modelNegoci )){
                            managerUI.tendaExistent();
                        }else {
                            managerUI.tendaCreat();
                            select = MenuGestioTendes.BACK;
                        }
                        break;
                }


                break;

            case MenuGestioTendes.EXPANDIR_CATALEG:
                String nomTenda;
                String nomProducte;
                float preuProducte;
                boolean nomOK = false;
                boolean afegit = false;
                do{
                    nomTenda = managerUI.requestnomNouTenda();
                    nomOK = managerTenda.comprovarExisteincia(nomTenda);
                }while (!managerUI.tendaNoExistent(nomOK));// passa per enviar el missatge de que esta malament si es necessari


                do {
                    nomProducte = managerUI.requestnomNouProducte();
                    nomOK = managerProducte.comprovarExistencia(nomProducte);
                }while (!managerUI.producteNoExistent(nomOK));

                if(nomOK){
                    do {
                        preuProducte = managerUI.requestPreuMaxNouProducte();
                        afegit = managerTenda.addProducte(managerProducte.getProductes(),nomTenda, nomProducte,preuProducte);
                    }while (!managerUI.preuSuperiorMaxPreu(afegit));

                    managerUI.producteAfegitCorrectament();

                }



                break;


            case MenuGestioTendes.REDUIR_CATALEG:
                do{
                    nomTenda = managerUI.requestnomNouTenda();
                    nomOK = managerTenda.comprovarExisteincia(nomTenda);
                }while (!managerUI.tendaNoExistent(nomOK));// passa per enviar el missatge de que esta malament si es necessari

                Producte selection = managerUI.requestmostrarCatalegTenda(managerTenda.getTendes(), nomTenda);
                if(selection == null){
                    select = MenuGestioTendes.BACK;
                }else{
                    managerUI.messageBorrarProducteCataleg(managerTenda.removeProductFromTenda(nomTenda, selection.getName()),
                            nomTenda,selection.getName(),selection.getBrand()); // borrem i informem de que s'ha borrat
                    select = MenuGestioTendes.BACK;
                }

                break;


            case MenuGestioTendes.BACK:
                break;


        }
    }

    private void gestioProductes() {
        MenuGestioProductes selected;
        selected = managerUI.menuGestioProducte(); // seleccionem submenu de gestio de productes
        switch (selected){
            case CREAR_PRODUCTE:
                loadFiles();
                if(managerProducte.addProductes(managerUI.requestnomNouProducte(),managerUI.requestMarcaNouProducte(),managerUI.requestCategoriaNouProducte(), managerUI.requestPreuMaxNouProducte())){
                    managerUI.producteCreat();
                }else{
                    managerUI.producteExistent();
                    selected =MenuGestioProductes.BACK; // tornem al menu principal com diu l'enunciat
                }

                // afegim el producte a la llista principal de productes


                break;
            case BORRAR_PRODUCTE:
                loadFiles();
                int index = managerUI.requestDeleteProducte(managerProducte.getProductes());
                if(managerProducte.getProductes().isEmpty()){
                    break;
                }
                if(index < managerProducte.getProductes().size()){
                    String name = managerProducte.getNameProduct(index);
                    managerProducte.removeProducte(index);
                    managerTenda.removeProducteCatalegs(name);
                }else if(index == managerProducte.getProductes().size()){
                    selected = MenuGestioProductes.BACK;
                }

                break;

            case BACK:
                break;

            case ERROR:
                break;
        }

    }
}
