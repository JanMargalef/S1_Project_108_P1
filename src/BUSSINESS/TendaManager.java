package BUSSINESS;

import BUSSINESS.ENTITIES.*;
import PERSISTANCE.APIPERSISTANCE.ApiDAO;
import PERSISTANCE.DataPersistance;
import PERSISTANCE.JSONPERSISTANCE.ProducteJsonDAO;
import PERSISTANCE.JSONPERSISTANCE.TendaJsonDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.salle.url.api.exception.ApiException;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Gestor de tendes que conté una llista d'objectes Tenda i un carret de compra.
 */
public class TendaManager {

    private ArrayList<Tenda> tendes;
    private Carret carrito;
    /**
     * Constructor que inicialitza la llista de tendes i el carret de compra.
     */
    public TendaManager() {
        this.tendes = new ArrayList<>();
        this.carrito = new Carret();

    }
    /**
     * Carrega les dades de les tendes des de la font de dades, ja sigui una API o un arxiu local.
     *
     * @return Un valor que indica l'estat de la connexió amb la font de dades (0 - cap connexió, 1 - connexió reeixida amb API, 2 - connexió reeixida amb arxiu local).
     */
    public int loadData() {

        int apiconected = 0;
        ArrayList<JsonObject> tendaDAO;
        try {

            ApiDAO apiDAO = new ApiDAO();
            tendaDAO = apiDAO.loadTendaAPI("");
            if(tendaDAO.isEmpty()){
                DataPersistance dataPersistance = new TendaJsonDAO();
                tendaDAO = dataPersistance.loadTendaJson();
                apiconected = 2;
            }else{
                apiconected = 1;

            }


        } catch (ApiException e) {
            DataPersistance dataPersistance = new ProducteJsonDAO();
            tendaDAO = dataPersistance.loadTendaJson();
            apiconected = 2;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tendes.clear();
        for (JsonObject tenda : tendaDAO) {
            orderTendes(tenda);
        }
        return apiconected;
    }
    /**
     * Guarda les dades de les tendes a la font de dades, ja sigui una API o un arxiu local.
     *
     * @return Un valor que indica l'estat de la connexió amb la font de dades (0 - cap connexió, 1 - connexió reeixida amb API, 2 - connexió reeixida amb arxiu local).
     */
    public int saveData() throws IOException {
        int apiconected = 0;
        try {

            ApiDAO apiDAO = new ApiDAO();
            if(apiDAO.saveTendaAPI(tendes)){
                apiconected = 1;

            }else{
                DataPersistance dataPersistance = new TendaJsonDAO();
                dataPersistance.saveTendaJson(tendes);
                apiconected = 2;
            }


        } catch (ApiException e) {
            DataPersistance dataPersistance = new TendaJsonDAO();
            dataPersistance.saveTendaJson(tendes);
            apiconected = 2;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return apiconected;
    }


    /**
     * Ordena les tendes segons el model de negoci i les afegeix a la llista de tendes.
     *
     * @param jsonObject JsonObject que conté la informació de la tenda.
     */
    private void orderTendes(JsonObject jsonObject){

        String name = jsonObject.get("name").getAsString();
        String description = jsonObject.get("description").getAsString();
        int since = jsonObject.get("since").getAsInt();
        String bussinessModel = jsonObject.get("businessModel").getAsString();
        Float earnings = jsonObject.get("earnings").getAsFloat();
        String sponsoredBrand = (jsonObject.get("sponsoredBrand") != null) ? jsonObject.get("sponsoredBrand").getAsString() : null;
        float loyaltyThreshold = 0;
        if (jsonObject.has("loyaltyThreshold") && !jsonObject.get("loyaltyThreshold").isJsonNull()) {
            loyaltyThreshold = jsonObject.get("loyaltyThreshold").getAsFloat();
        }

        ArrayList<ProducteTenda> cataleg = jsonArrayToProductList(jsonObject.getAsJsonArray("catalogue"));



            switch (bussinessModel){
                case "MAX_PROFIT":
                    TendaGeneral tendaGeneral = new TendaGeneral(name, description, since, earnings, bussinessModel, cataleg);
                    tendes.add(tendaGeneral);
                    break;
                case "LOYALTY":
                    TendaFidelitzacio tendaFidelitzacio = new TendaFidelitzacio(name, description, since, earnings, bussinessModel, cataleg, loyaltyThreshold);
                    tendes.add(tendaFidelitzacio);
                    break;
                case "SPONSORED":
                    TendaSponsor tendaSponsor = new TendaSponsor(name, description, since, earnings, bussinessModel, cataleg, sponsoredBrand);
                    tendes.add(tendaSponsor);
                    break;
            }


    }
    /**
     * Converteix un JSONArray de productes en una llista d'objectes ProducteTenda.
     *
     * @param jsonArray JSONArray amb la informació del catàleg de la tenda.
     * @return Una llista d'objectes ProducteTenda.
     */
    private ArrayList<ProducteTenda> jsonArrayToProductList(JsonArray jsonArray) {
        ArrayList<ProducteTenda> productList = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            ProducteTenda producto = new Gson().fromJson(jsonObject, ProducteTenda.class);

            productList.add(producto);
        }

        return productList;
    }

    /**
     * retorna un arraylist amb totes les tendes
     * @return
     */
    public ArrayList<Tenda> getTendes() {
        return tendes;
    }

    /**
     * retorna el carrito
     * @return
     */
    public Carret getCarrito() {
        return carrito;
    }

    //*********************************__SETTERS LLISTA TENDES__***************************************************************

    /**
     * crea una nova tenda i la afegeix a dins de l'array list de tendes
     * @param nom nom de la tenda
     * @param descripcio despripcio de la tenda
     * @param anyFundacio any de fundació de la tenda
     * @param modelNegoci model de negoci de la tenda
     * @return retorna un boolea indicant que ha sigut o no possible crear la tenda
     */
    public boolean addTendaMaxProfit(String nom, String descripcio, int anyFundacio, String modelNegoci) {
        ArrayList<ProducteTenda> cataleg = new ArrayList<ProducteTenda>();
        TendaGeneral newTenda = new TendaGeneral(nom, descripcio, anyFundacio, modelNegoci, cataleg);
        for (Tenda tenda : tendes) {
            if (tenda.getName().equals(newTenda.getName())) {
                return true;
            }
        }

        tendes.add(newTenda);
        return false;
    }
    /**
     * Afegeix una nova tenda amb model de negoci de fidelització a la llista de tendes.
     *
     * @param nom Nom de la tenda.
     * @param descripcio Descripció de la tenda.
     * @param anyFundacio Any de fundació de la tenda.
     * @param modelNegoci Model de negoci de la tenda.
     * @param threshold Límit de fidelització de la tenda.
     * @return True si la tenda ja existeix a la llista, False si s'afegeix amb èxit.
     */
    public boolean addTendaLoyalty(String nom, String descripcio, int anyFundacio, String modelNegoci, float threshold) {
        ArrayList<ProducteTenda> cataleg = new ArrayList<ProducteTenda>();
        TendaFidelitzacio newTenda = new TendaFidelitzacio(nom, descripcio, anyFundacio, modelNegoci, cataleg, threshold);
        for (Tenda tenda : tendes) {
            if (tenda.getName().equals(newTenda.getName())) {
                return true;
            }
        }

        tendes.add(newTenda);
        return false;
    }
    /**
     * Afegeix una nova tenda amb model de negoci patrocinat a la llista de tendes.
     *
     * @param nom Nom de la tenda.
     * @param descripcio Descripció de la tenda.
     * @param anyFundacio Any de fundació de la tenda.
     * @param modelNegoci Model de negoci de la tenda.
     * @param sponsoredBrand Marca patrocinada de la tenda.
     * @return True si la tenda ja existeix a la llista, False si s'afegeix amb èxit.
     */
    public boolean addTendaSponsored(String nom, String descripcio, int anyFundacio, String modelNegoci, String sponsoredBrand) {
        ArrayList<ProducteTenda> cataleg = new ArrayList<ProducteTenda>();
        TendaSponsor newTenda = new TendaSponsor(nom, descripcio, anyFundacio, modelNegoci, cataleg, sponsoredBrand);
        for (Tenda tenda : tendes) {
            if (tenda.getName().equals(newTenda.getName())) {
                return true;
            }
        }

        tendes.add(newTenda);
        return false;
    }

    /**
     * Afegir un producte al cataleg de l tenda
     * @param productes passa l'arraylist de tots els productes
     * @param nomTenda el nom de la tenda al qual vol afegirse el producte
     * @param nomProducte el nom del producte
     * @param preu el preu que tindra a la tenda
     * @return retorna si ha sigut possible o no ferho
     */
    public boolean addProducte(ArrayList<Producte> productes, String nomTenda, String nomProducte, float preu) {
        ProducteTenda producteTenda;
        boolean crear = true;
        for (Producte producte : productes) { // comprovem que existeixi el producte
            if (producte.getName().equals(nomProducte)) {
                if (producte.getMrp() > preu) { // comprovem que el maxim preu no sigui superat per el de la tenda
                    producteTenda = new ProducteTenda(producte.getName(), producte.getBrand(), producte.getCategory(), producte.getMrp(), preu, nomTenda); // creem el producte de tenda

                    for (Tenda tenda : tendes) { // busquem la tenda a la que volem afegir el producte
                        ArrayList<ProducteTenda> cataleg = tenda.getCatalogue();

                        if (tenda.getName().equals(nomTenda)) {
                            if (cataleg.size() == 0){
                                tenda.setCatalogue(producteTenda);
                                crear = true; // indiquem que ja hem creat un
                                break; // parem el bucle aixi no peta el programa a causa del else if ja que al estar buid es surt de la longitut de l'array

                            }else{
                                for (int i = 0; i < cataleg.size(); i++) {
                                    if(cataleg.get(i).getName().equals(nomProducte)) { // si existeix el borrem i el tornem a crear de nou i si esta vuid el crei directe
                                        tenda.removeProductCatalogue(i);
                                        tenda.setCatalogue(producteTenda);
                                        crear = false; // indiquem que ja hem creat un
                                        break;
                                    }
                                }
                                if(crear){
                                    tenda.setCatalogue(producteTenda);
                                }
                            }

                            return true;
                        }
                    }
                } else {
                    return false;  // retorna fals si el preu del producte supera al maxim preu
                }
            }

        }
        return false;
    }

    /**
     * Elimina un producte del cataleg de totes les tendes
     * @param name nom del producte a eliminar
     */
    public void removeProducteCatalegs(String name) {
        for (Tenda tenda : tendes) {
            ArrayList<ProducteTenda> producteTendas = tenda.getCatalogue();
            for (int i = 0; i < producteTendas.size(); i++) {

                if (producteTendas.get(i).getName().equals(name)) {
                    tenda.removeProductCatalogue(i);
                }
            }

        }
    }

    /**
     * elimina del cataleg de una tenda el producte que es vol eliminar
     * @param nomTenda nom de la tenda
     * @param nomProducte nom del producte a eliminar
     * @return retorna un boolea de si sha eliminat o no el producte
     */
    public Boolean removeProductFromTenda(String nomTenda, String nomProducte) {
        for (Tenda tendes : tendes) {
            if (tendes.getName().equals(nomTenda)) {
                for (int i = 0; i < tendes.getCatalogue().size(); i++) {
                    if (tendes.getCatalogue().get(i).getName().equals(nomProducte)) { // seleccionem el que volem borrar
                        tendes.removeProductCatalogue(i); // El borrem.
                        return true;
                    }
                }


            }
        }
        return false;
    }



    //*********************************__COMPROVAR LLISTA TENDES__***************************************************************

    /**
     * Comprova la existencia de la tenda
     * @param nomTenda nom a comprobar
     * @return retorna si existeix o no la tenda
     */
    public boolean comprovarExisteincia(String nomTenda) {
        for (Tenda tenda : tendes) { // busquem la tenda
            if (tenda.getName().equals(nomTenda)) {
                return true;
            }
        }
        return false;  // retorna fals si no ho troba
    }

    /**
     * Afegir un producte de una tenda al carret
     * @param producteTenda producte de la tenda a afegir
     */
    public void addToCarret(ProducteTenda producteTenda) {
        carrito.setProductesCarrito(producteTenda);
    }

    /**
     * suma tots els veneficis a cada tenda si un producte es troba en el carrito
     */

    public void checkout() {
        calculCarretClientTenda(true);
    }

    /**
     * Calcula el cost total per al client segons els productes del carret i les tendes existents.
     *
     * @param checkout Indica si es tracta d'un procés de pagament (true) o simplement d'una consulta (false).
     * @return El cost total per al client.
     */
    public float calculCarretClientTenda(Boolean checkout) {
        ArrayList<ProducteTenda> carritoProductes = carrito.getProductesCarrito();
        ArrayList<Producte> productes = ProductManager.getProductes();

        float totalClient = 0;


        for (Tenda tenda:tendes) {
            float totalTenda = 0;

            boolean sponsored = false;
            boolean fidelitzat = false;

            for (ProducteTenda productTenda: carritoProductes) {
                if(tenda.getName().equals(productTenda.getTenda())){
                    float preuTenda = productTenda.getPreuTenda();


                    if(tenda instanceof TendaGeneral){
                        TendaGeneral tendaGeneral = (TendaGeneral) tenda;

                        for (Producte producte: productes) {
                            if(productTenda.getName().equals(producte.getName())){
                                totalTenda += calculPreuBenefici(preuTenda, producte);
                                totalClient += preuTenda;
                            }
                        }


                    }

                    if(tenda instanceof TendaFidelitzacio){
                        TendaFidelitzacio tendaFidelitzacio = (TendaFidelitzacio) tenda;

                        if(tendaFidelitzacio.getEarnings() >= tendaFidelitzacio.getLoyaltyThreshold()){
                            fidelitzat = true;
                        }else{
                            fidelitzat = false;
                        }

                        for (Producte producte: productes) {
                            if(productTenda.getName().equals(producte.getName())){
                                if(fidelitzat){
                                    float preu = (float)calculPreuBenefici(preuTenda, producte);
                                    totalTenda += (float)calculPreuBenefici(preu, producte);

                                    totalClient += preu;

                                }else{

                                    totalTenda += calculPreuBenefici(preuTenda, producte);
                                    totalClient += preuTenda;

                                }

                            }
                        }
                    }

                    if(tenda instanceof TendaSponsor){
                        TendaSponsor tendaSponsor = (TendaSponsor) tenda;

                        for (Producte producte: productes) {
                            if(productTenda.getName().equals(producte.getName())){
                                totalTenda += calculPreuBenefici(preuTenda, producte);

                                if(producte.getBrand().equals(tendaSponsor.getSponsorBrand())){
                                    sponsored = true;
                                }
                            }
                        }
                    }
                }

            }

            if(sponsored){
                sponsored = false;
                totalTenda = totalTenda * (float) 0.9;
            }

            if(checkout){
                tenda.setEarnings((float)totalTenda);
            }




        }
        return totalClient;
    }

    /**
     * elimina tots els productes del carrito
     */
    public void clearCart() {
        if(carrito.getProductesCarrito().size()!= 0){
            carrito.removeProductesCarrito();
        }

    }
    /**
     * Calcula el preu amb benefici d'un producte al carro de la compra.
     *
     * @param preuTenda Preu del producte a la tenda.
     * @param producte  Producte per al qual es calcula el preu amb benefici.
     * @return El preu amb benefici calculat.
     */
    private double calculPreuBenefici(float preuTenda, Producte producte) {

        double preu = 0.0;
        if (producte instanceof ProducteGeneral) {
            ProducteGeneral producteGeneral = (ProducteGeneral) producte;
            float iva = producteGeneral.getIva();
            preu = preuTenda/((iva/100)+1);
        }
        if (producte instanceof ProducteReduit) {
            ProducteReduit producteReduit = (ProducteReduit) producte;
            float iva = (producteReduit.getIva() / 100) + 1;
            preu = preuTenda/iva;
        }
        if (producte instanceof ProducteSuperReduit) {
            ProducteSuperReduit producteSuperReduit = (ProducteSuperReduit) producte;
            float iva = (producteSuperReduit.getIva() / 100) + 1;
            preu = preuTenda/iva;
        }
        return preu;


    }
}
