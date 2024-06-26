package PERSISTANCE;

import BUSSINESS.ENTITIES.Producte;
import BUSSINESS.ENTITIES.Tenda;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Interfície que defineix operacions de persistència de dades, com ara connexió amb API, càrrega i desament de dades
 * relacionades amb productes i tendes, i operacions de desament local en format JSON.
 */
public interface DataPersistanceJSON {

    /**
     * Carrega les dades de les tendes des d'una font local en format JSON.
     *
     * @return Una llista d'objectes JsonObject carregada des de la font local.
     */
    public ArrayList<JsonObject> loadInfoJson();
    /**
     * Guarda les dades dels productes en una font local en format JSON.
     *
     * @param ProductList Llista d'objectes Producte que es volen desar.
     * @param tendaList  Llista d'objectes Tenda que es volen desar.
     */
    public void saveInfoJson(ArrayList<Producte> ProductList,ArrayList<Tenda> tendaList);

}
