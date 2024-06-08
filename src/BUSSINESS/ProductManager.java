package BUSSINESS;

import BUSSINESS.ENTITIES.Producte;
import BUSSINESS.ENTITIES.ProducteGeneral;
import BUSSINESS.ENTITIES.ProducteReduit;
import BUSSINESS.ENTITIES.ProducteSuperReduit;
import PERSISTANCE.APIPERSISTANCE.ApiDAO;
import PERSISTANCE.DataPersistance;
import PERSISTANCE.JSONPERSISTANCE.ProducteJsonDAO;
import edu.salle.url.api.exception.ApiException;

import java.util.ArrayList;

/**
 * Gestor de productes que conté una llista d'objectes Producte.
 */
public class ProductManager {

    private static ArrayList<Producte> productes;
    /**
     * Constructor per defecte del gestor de productes. Inicialitza la llista de productes.
     */
    public ProductManager() {


        this.productes = new ArrayList<>();

    }
    /**
     * Carrega les dades dels productes des d'una API o un arxiu local, segons la disponibilitat.
     *
     * @return Retorna un codi indicant l'estat de connexió amb l'API o el fitxer local:
     *         - 0: No s'ha pogut connectar ni amb l'API ni amb el fitxer local.
     *         - 1: Connexió amb l'API establerta amb èxit.
     *         - 2: No s'ha pogut connectar amb l'API, carregant dades des del fitxer local.
     */
    public int loadData(){
        ArrayList<Producte> productesDAO;
        int apiconected = 0;
        try {

            ApiDAO apiDAO = new ApiDAO();
            productesDAO = apiDAO.loadProductAPI("");
            if(productesDAO.isEmpty()){
                DataPersistance dataPersistance = new ProducteJsonDAO();
                productesDAO = dataPersistance.loadProducteJson();
                apiconected = 2;
            }
            else{
                apiconected = 1;

            }


        } catch (ApiException e) {
            DataPersistance dataPersistance = new ProducteJsonDAO();
            productesDAO = dataPersistance.loadProducteJson();
            apiconected = 2;

        }
        productes.clear();
        for (Producte product: productesDAO) {
            orderProducts(product);
        }
        return apiconected;

    }
    /**
     * Guarda les dades dels productes a través de l'API o en un arxiu local, segons la disponibilitat.
     *
     * @return Retorna un codi indicant l'estat de connexió amb l'API o el fitxer local:
     *         - 0: No s'ha pogut connectar ni amb l'API ni amb el fitxer local.
     *         - 1: Connexió amb l'API establerta amb èxit.
     *         - 2: No s'ha pogut connectar amb l'API, guardant dades al fitxer local.
     */
    public int saveData() {
        int apiconected = 0;
        try {

            ApiDAO apiDAO = new ApiDAO();
            if(apiDAO.saveProductAPI(productes)){
                apiconected = 1;

            }else{
                DataPersistance dataPersistance = new ProducteJsonDAO();
                dataPersistance.saveProducteJson(productes);
                apiconected = 2;

            }


        } catch (ApiException e) {
        DataPersistance dataPersistance = new ProducteJsonDAO();
        dataPersistance.saveProducteJson(productes);
        apiconected = 2;
        }
        return apiconected;
    }


    /**
     * Ordena els productes segons la seva categoria i els afegeix a la llista de productes gestionada.
     *
     * @param product Producte a ser ordenat i afegit a la llista.
     */
    private void orderProducts(Producte product){
        switch (product.getCategory()){
            case "GENERAL":
                ProducteGeneral producteGeneral = new ProducteGeneral(product.getName(), product.getBrand(), product.getCategory(),product.getMrp(),product.getReviews());
                productes.add(producteGeneral);
                break;
                case "REDUCED":
                    ProducteReduit producteReduit = new ProducteReduit(product.getName(), product.getBrand(), product.getCategory(),product.getMrp(), product.getReviews());
                    productes.add(producteReduit);
                    break;
                case "SUPER_REDUCED":
                    ProducteSuperReduit producteSuperReduit = new ProducteSuperReduit(product.getName(), product.getBrand(), product.getCategory(),product.getMrp(), product.getReviews());
                    productes.add(producteSuperReduit);
                    break;
            }

    }
    /**
     * retorna el nom del producte respecte el index en el array list
     * @param index posició del producte a l'arrayList
     * @return retorna el nom del producte
     */
    public String getNameProduct(int index){
        return productes.get(index).getName();
    }

    /**
     * retorna tots els productes existents
     * @return
     */
    public static ArrayList<Producte> getProductes() {
        return productes;
    }




    //*********************************__SETTERS LLISTA PRODUCTES__***************************************************************

    /**
     * afegeix un nou producte a la llista de productes
     * @param nom nom del producte
     * @param marca marca del producte
     * @param categoria categoria del producte
     * @param maxPreu preu maxim del producte
     * @return retorna un boolea de si sha creat o no
     */
    public boolean addProductes(String nom, String marca, String categoria, float maxPreu){
        Producte newProducte = new Producte(nom, marca, categoria, maxPreu);
        for (Producte producte: productes) {
            if(producte.getName().equals(newProducte.getName())){
                return false;
            }
        }

        orderProducts(newProducte); // guarda a la llista el nou producte ja filtrat
        return true;
    }

    /**
     * eliminar producte de la llista
     * @param index index del producte a eliminar
     */
    public void removeProducte(int index){
        productes.remove(index);

    }



    //*********************************__COMPROVAR LLISTA PRODUCTES__***************************************************************

    /**
     * Comprova la existencia del producte amb el nom dins de la llista
     * @param nom nom a comprovar
     * @return retorna un boolea de si existeix o no el producte
     */
    public boolean comprovarExistencia(String nom){ // si existeix retorna true si no false
        for (Producte producte: productes) {
            if(producte.getName().equals(nom)){
                return true;
            }

        }
        return false;
    }

    /**
     * afegir una review a un producte
     * @param producte producte a afegirli la review
     * @param punts punts de la review
     * @param text comentari de la reivew
     */
    public void addReviews(Producte producte, int punts, String text) {
        String review = punts + "* " + text;
        for (Producte product: productes) {
            if(product.equals(producte)){
                product.setReviews(review);
            }

        }
    }

    /**
     * Afegir una review a un producte
     * @param nom nom del producte a afegir la review
     * @param punts punts de la review
     * @param text comentari de la review
     */

    public void addReviews(String nom, int punts, String text) {

        for (Producte product:productes) {
            if(product.getName().equals(nom)){
                String review = punts + " " + text;
                product.setReviews(review);
            }

        }

    }
}
