package PERSISTANCE.JSONPERSISTANCE;

import BUSSINESS.ENTITIES.Producte;
import BUSSINESS.ENTITIES.Tenda;
import PERSISTANCE.DataPersistance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementació de l'interfície DataPersistance per a la persistència de dades en format JSON per a les tendes.
 */
public class TendaJsonDAO implements DataPersistance {
    private final String path = "Tendes.json";
    private Gson gson;
    private String file = "";


    /**
     * Carrega les dades de les tendes des d'un fitxer JSON local.
     *
     * @return Una llista d'objectes JsonObject carregada des del fitxer JSON.
     */
    @Override
    public ArrayList<JsonObject> loadTendaJson() {
        Type listaGenericType = new TypeToken<List<JsonObject>>(){}.getType();
        ArrayList<JsonObject> generic = gson.fromJson(file, listaGenericType);

        return generic;
    }


    /**
     * Guarda les dades de les tendes en un fitxer JSON local.
     *
     * @param tendes Llista d'objectes Tenda que es volen desar.
     */
    @Override
    public void saveTendaJson(ArrayList<Tenda> tendes) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        try (FileWriter writer = new FileWriter("Tendes.json")) {
            gson.toJson(tendes, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //*********************************__CONSTRUCTORS__**********************************************************************
    /**
     * Constructor de la classe TendaJsonDAO.
     * Inicialitza la variable gson i carrega el contingut del fitxer JSON local en la variable 'file'.
     *
     * @throws IOException Si es produeix un error d'entrada/sortida durant la lectura del fitxer JSON.
     */
    public TendaJsonDAO() throws IOException {
        this.gson = new Gson();
        file = new String(Files.readAllBytes(Paths.get(path)));
    }

    //*********************************__NO USAGES__**********************************************************************
    /**
     * No implementat. Aquest mètode no realitza cap operació.
     *
     * @param productes Llista d'objectes Producte que es volen desar.
     */
    @Override
    public void saveProducteJson(ArrayList<Producte> productes) {

    }
    /**
     * No implementat. Aquest mètode no realitza cap operació.
     */
    @Override
    public void connectAPI() {

    }

    /**
     * No implementat. Aquest mètode retorna un valor nul ja que no realitza cap operació.
     *
     * @param name Nom relacionat amb la cerca de productes a l'API.
     * @return Un valor nul ja que aquest mètode no realitza cap operació.
     */
    @Override
    public ArrayList<Producte> loadProductAPI(String name) {
        return null;
    }
    /**
     * No implementat. Aquest mètode no realitza cap operació.
     *
     * @param productes Llista d'objectes Producte que es volen desar a l'API.
     * @return
     */
    @Override
    public boolean saveProductAPI(ArrayList<Producte> productes) {
        return false;
    }
    /**
     * No implementat. Aquest mètode retorna un valor nul ja que no realitza cap operació.
     *
     * @param name Nom relacionat amb la cerca de tendes a l'API.
     * @return Un valor nul ja que aquest mètode no realitza cap operació.
     */
    @Override
    public ArrayList<JsonObject> loadTendaAPI(String name) {
        return null;
    }
    /**
     * No implementat. Aquest mètode no realitza cap operació.
     *
     * @param tendas Llista d'objectes Tenda que es volen desar a l'API.
     * @return
     */
    @Override
    public Boolean saveTendaAPI(ArrayList<Tenda> tendas) {
        return false;
    }
    /**
     * No implementat. Aquest mètode retorna un valor nul ja que no realitza cap operació.
     *
     * @return Un valor nul ja que aquest mètode no realitza cap operació.
     */
    @Override
    public ArrayList<Producte> loadProducteJson() {
        return null;
    }

}
