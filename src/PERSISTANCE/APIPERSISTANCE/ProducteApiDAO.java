package PERSISTANCE.APIPERSISTANCE;


import BUSSINESS.ENTITIES.Producte;
import BUSSINESS.ENTITIES.Tenda;
import PERSISTANCE.DataPersistanceAPI;
import com.google.gson.Gson;
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
public class ProducteApiDAO implements DataPersistanceAPI {

    private ApiHelper apiHelper;
    private String URL = "https://balandrau.salle.url.edu/dpoo/P1-G108/";
    private Gson gson;


    /**
     * Constructor de la classe ApiDAO que inicialitza un objecte ApiHelper i una instància Gson.
     *
     * @throws RuntimeException Si es produeix una ApiException durant la inicialització.
     */
    public ProducteApiDAO() throws ApiException {
        try {
            apiHelper = new ApiHelper();
            gson = new Gson();
        } catch (ApiException e) {

        }
    }

    /**
     * Carrega les dades dels productes des de l'API amb un nom específic o tots els productes si el nom és buit.
     *
     * @return Una llista d'objectes Producte carregada des de l'API.
     */
    @Override
    public ArrayList<JsonObject> loadInformation() {
        ArrayList<JsonObject> generic = new ArrayList<>();

        try {
            String info = apiHelper.getFromUrl(URL + "products");
            Type listaGenericType = new TypeToken<List<Producte>>() {
            }.getType();
            if (!info.equals("[[]]")) {
                generic = gson.fromJson(info, listaGenericType);
            }

            return generic;

        } catch (ApiException ErrorConect) {
            return generic;

        } catch (NullPointerException e) {
            return generic;

        }


    }

    /**
     * Desa les dades dels productes a l'API, eliminant les dades existents i afegint les noves.
     *
     * @param productes Llista d'objectes Producte que es volen desar a l'API.
     * @param tendalist
     * @return
     * @throws ApiException Si es produeix un error durant el desament de les dades a l'API.
     */
    @Override
    public boolean saveInformation(ArrayList<Producte> productes, ArrayList<Tenda> tendalist) throws ApiException {

        deleteInfoURL("products");
        gson = new Gson();

        try {
            for (Producte producte : productes) {
                String info = gson.toJson(producte);
                apiHelper.postToUrl(URL + "products", info);
            }
            if (productes.isEmpty()) {
                apiHelper.postToUrl(URL + "products", "");
            }
            return true;

        } catch (ApiException ErrorConect) {
            return false;

        } catch (NullPointerException e) {
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
            apiHelper.postToUrl(URL + url, " ");

        } catch (ApiException ErrorConect) {


        } catch (NullPointerException e) {


        }
    }


}