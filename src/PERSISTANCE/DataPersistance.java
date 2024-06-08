package PERSISTANCE;

import BUSSINESS.ENTITIES.Producte;
import BUSSINESS.ENTITIES.Tenda;
import com.google.gson.JsonObject;
import edu.salle.url.api.exception.ApiException;

import java.util.ArrayList;

/**
 * Interfície que defineix operacions de persistència de dades, com ara connexió amb API, càrrega i desament de dades
 * relacionades amb productes i tendes, i operacions de desament local en format JSON.
 */
public interface DataPersistance {
    /**
     * Connecta amb l'API per realitzar operacions relacionades amb les dades.
     *
     * @throws ApiException Si es produeix un error durant la connexió amb l'API.
     */
    public void connectAPI() throws ApiException;
    /**
     * Carrega les dades dels productes des de l'API amb un nom específic.
     *
     * @param name Nom relacionat amb la cerca de productes a l'API.
     * @return Una llista d'objectes Producte carregada des de l'API.
     */
    public ArrayList<Producte> loadProductAPI(String name);
    /**
     * Guarda les dades dels productes a l'API.
     *
     * @param productes Llista d'objectes Producte que es volen desar a l'API.
     * @return Boolean Indoca si sha fet el guardat per api
     * @throws ApiException Si es produeix un error durant el desament de les dades a l'API.
     */
    public boolean saveProductAPI(ArrayList<Producte> productes) throws ApiException;
    /**
     * Carrega les dades de les tendes des de l'API amb un nom específic.
     *
     * @param name Nom relacionat amb la cerca de tendes a l'API.
     * @return Una llista d'objectes JsonObject carregada des de l'API.
     */
    public ArrayList<JsonObject> loadTendaAPI(String name);
    /**
     * Guarda les dades de les tendes a l'API.
     *
     * @param tendas Llista d'objectes Tenda que es volen desar a l'API.
     * @return
     * @throws ApiException Si es produeix un error durant el desament de les dades a l'API.
     */
    public Boolean saveTendaAPI(ArrayList<Tenda> tendas) throws ApiException;
    /**
     * Carrega les dades dels productes des d'una font local en format JSON.
     *
     * @return Una llista d'objectes Producte carregada des de la font local.
     */
    public ArrayList<Producte> loadProducteJson();
    /**
     * Carrega les dades de les tendes des d'una font local en format JSON.
     *
     * @return Una llista d'objectes JsonObject carregada des de la font local.
     */
    public ArrayList<JsonObject> loadTendaJson();
    /**
     * Guarda les dades dels productes en una font local en format JSON.
     *
     * @param productes Llista d'objectes Producte que es volen desar a la font local.
     */
    public void saveProducteJson(ArrayList<Producte> productes);
    /**
     * Guarda les dades de les tendes en una font local en format JSON.
     *
     * @param tendes Llista d'objectes Tenda que es volen desar a la font local.
     */
    public void saveTendaJson(ArrayList<Tenda> tendes);

}
