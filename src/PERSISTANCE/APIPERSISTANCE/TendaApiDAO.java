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

public class TendaApiDAO implements DataPersistanceAPI {
    private ApiHelper apiHelper;
    private String URL = "https://balandrau.salle.url.edu/dpoo/P1-G108/";
    private Gson gson;

    /**
     * Constructor de la classe ApiDAO que inicialitza un objecte ApiHelper i una instància Gson.
     *
     * @throws RuntimeException Si es produeix una ApiException durant la inicialització.
     */
    public TendaApiDAO() throws ApiException {
        try {
            apiHelper = new ApiHelper();
            gson = new Gson();
        } catch (ApiException e) {

        }
    }

    /**
     * Carrega les dades dels productes des de l'API amb un nom específic.
     *
     * @return Una llista d'objectes Producte carregada des de l'API.
     */
    @Override
    public ArrayList<JsonObject> loadInformation() {
        ArrayList<JsonObject> generic = new ArrayList<>();

        try {
            String info = apiHelper.getFromUrl(URL + "shops");
            Type listaGenericType = new TypeToken<List<JsonObject>>() {
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
     * Guarda les dades dels productes a l'API.
     *
     * @param tendalist    Llista d'objectes tenda que es volen desar a l'API.
     * @return Boolean Indoca si sha fet el guardat per api
     * @throws ApiException Si es produeix un error durant el desament de les dades a l'API.
     */
    @Override
    public boolean saveInformation(ArrayList<Producte> producteList, ArrayList<Tenda> tendalist) throws ApiException {
        gson = new Gson();

        deleteInfoURL("shops");
        try {
            for (Tenda tenda: tendalist) {
                String info = gson.toJson(tenda);
                apiHelper.postToUrl(URL + "shops", info);
            }
            if(tendalist.isEmpty()){
                apiHelper.postToUrl(URL + "shops", "");
            }
            return true;

        }catch (NullPointerException e){
            return false;

        }catch (ApiException e){
            return false;
        }
    }

    private void deleteInfoURL(String url) {
        try {
            apiHelper.deleteFromUrl(URL + url);
            apiHelper.postToUrl(URL+url," ");

        }catch (ApiException ErrorConect){


        }catch (NullPointerException e){


        }
    }
}
