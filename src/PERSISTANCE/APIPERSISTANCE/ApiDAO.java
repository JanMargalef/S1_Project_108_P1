package PERSISTANCE.APIPERSISTANCE;


import BUSSINESS.ENTITIES.Producte;
import BUSSINESS.ENTITIES.Tenda;
import PERSISTANCE.DataPersistance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que gestiona la connexió i les operacions amb una API remota.
 */
public class ApiDAO implements DataPersistance {

    private ApiHelper apiHelper;
    private String URL = "https://balandrau.salle.url.edu/dpoo/P1-G108/";
    private Gson gson;
    @Override
    public void connectAPI() {



    }

    /**
     * Constructor de la classe ApiDAO que inicialitza un objecte ApiHelper i una instància Gson.
     *
     * @throws RuntimeException Si es produeix una ApiException durant la inicialització.
     */
    public ApiDAO() throws ApiException {
        try {
            apiHelper = new ApiHelper();
            gson = new Gson();
        } catch (ApiException e) {

        }
    }

    /**
     * Carrega les dades dels productes des de l'API amb un nom específic o tots els productes si el nom és buit.
     *
     * @param name Nom relacionat amb la cerca de productes a l'API. Si és buit, carregarà tots els productes.
     * @return Una llista d'objectes Producte carregada des de l'API.
     */
    @Override
    public ArrayList<Producte> loadProductAPI(String name) {
        ArrayList<Producte> generic = new ArrayList<>();
        if(name.isEmpty()){ // si esta buid carregar tot
            try {
                String info = apiHelper.getFromUrl(URL + "products");
                Type listaGenericType = new TypeToken<List<Producte>>(){}.getType();
                if(!info.equals("[[]]")){
                    generic = gson.fromJson(info, listaGenericType);
                }

                return generic;

            }catch (ApiException ErrorConect){
                return generic;

            }catch (NullPointerException e){
                return generic;

            }

        }else{ // si demana un nom buscar el producte unicament
            try {
                String info = apiHelper.getFromUrl(URL + "products?name=" + name);
                Type listaGenericType = new TypeToken<List<Producte>>(){}.getType();
                generic = gson.fromJson(info, listaGenericType);
                return generic;

            }catch (ApiException ErrorConect){

            }


        }
        return null;



    }
    /**
     * Desa les dades dels productes a l'API, eliminant les dades existents i afegint les noves.
     *
     * @param productes Llista d'objectes Producte que es volen desar a l'API.
     * @return
     * @throws ApiException Si es produeix un error durant el desament de les dades a l'API.
     */
    @Override
    public boolean saveProductAPI(ArrayList<Producte> productes) throws ApiException {

        deleteInfoURL("products");
        gson = new Gson();

        try {
            for (Producte producte: productes) {
                String info = gson.toJson(producte);
                apiHelper.postToUrl(URL + "products", info);
            }
            if(productes.isEmpty()){
                apiHelper.postToUrl(URL + "products", "");
            }
            return true;

        }catch (ApiException ErrorConect){
            return false;

        }catch (NullPointerException e){
            return false;

        }


    }

    /**
     * Carrega les dades de les tendes des de l'API amb un nom específic o totes les tendes si el nom és buit.
     *
     * @param name Nom relacionat amb la cerca de tendes a l'API. Si és buit, carregarà totes les tendes.
     * @return Una llista d'objectes JsonObject carregada des de l'API.
     */
    @Override
    public ArrayList<JsonObject> loadTendaAPI(String name) {

        ArrayList<JsonObject> generic = new ArrayList<>();

        if(name.isEmpty()){ // si esta buid carregar tot
            try {
                String info = apiHelper.getFromUrl(URL + "shops");
                Type listaGenericType = new TypeToken<List<JsonObject>>(){}.getType();
                if(!info.equals("[[]]")) {
                    generic = gson.fromJson(info, listaGenericType);
                }
                return generic;

            }catch (ApiException ErrorConect){
                return generic;

            }catch (NullPointerException e){
                return generic;

            }

        }else{ // si demana un nom buscar el producte unicament
            try {
                String info = apiHelper.getFromUrl(URL + "shops?name=" + name);
                Type listaGenericType = new TypeToken<List<Producte>>(){}.getType();
                generic = gson.fromJson(info, listaGenericType);
                return generic;

            }catch (ApiException ErrorConect){
                return generic;

            }


        }

    }

    /**
     * Desa les dades de les tendes a l'API, eliminant les dades existents i afegint les noves.
     *
     * @param tendas Llista d'objectes Tenda que es volen desar a l'API.
     * @return
     * @throws ApiException Si es produeix un error durant el desament de les dades a l'API.
     */
    @Override
    public Boolean saveTendaAPI(ArrayList<Tenda> tendas){
        gson = new Gson();

        deleteInfoURL("shops");
        try {
            for (Tenda tenda: tendas) {
                String info = gson.toJson(tenda);
                apiHelper.postToUrl(URL + "shops", info);
            }
            if(tendas.isEmpty()){
                apiHelper.postToUrl(URL + "shops", "");
            }
            return true;

        }catch (NullPointerException e){
            return false;

        }catch (ApiException e){
            return false;
        }
    }



    /**
     * Elimina les dades d'una URL específica a l'API.
     *
     * @param url La URL de l'API on es volen eliminar les dades.
     */

    private void deleteInfoURL(String url) {
        try {
            apiHelper.deleteFromUrl(URL + url);
            apiHelper.postToUrl(URL+url," ");

        }catch (ApiException ErrorConect){


        }catch (NullPointerException e){


        }
    }





    //************************ NO USAGES ********************************************************************
    /**
     * No implementat. Aquest mètode retorna un valor nul ja que no realitza cap operació.
     *
     * @return Un valor nul ja que aquest mètode no realitza cap operació.
     */
    @Override
    public ArrayList<Producte> loadProducteJson() {
        return null;
    }
    /**
     * No implementat. Aquest mètode retorna un valor nul ja que no realitza cap operació.
     *
     * @return Un valor nul ja que aquest mètode no realitza cap operació.
     */
    @Override
    public ArrayList<JsonObject> loadTendaJson() {
        return null;
    }
    /**
     * No implementat. Aquest mètode no realitza cap operació.
     *
     * @param productes Llista d'objectes Producte que es volen desar a la font local.
     */
    @Override
    public void saveProducteJson(ArrayList<Producte> productes) {

    }
    /**
     * No implementat. Aquest mètode no realitza cap operació.
     *
     * @param tendes Llista d'objectes Tenda que es volen desar a la font local.
     */
    @Override
    public void saveTendaJson(ArrayList<Tenda> tendes) {

    }
}
