package PERSISTANCE.JSONPERSISTANCE;

import BUSSINESS.ENTITIES.Producte;
import BUSSINESS.ENTITIES.Tenda;
import PERSISTANCE.DataPersistance;
import com.google.gson.Gson;
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
 * Implementació de l'interfície DataPersistance per a la persistència de dades en format JSON per als productes.
 */
public  class ProducteJsonDAO implements DataPersistance {
    private final String path = "Productes.json";
    private Gson gson;  // Declarar la variable gson

    /**
     * Guarda les dades dels productes en un fitxer JSON.
     *
     * @param productes Llista d'objectes Producte que es volen desar.
     * @throws RuntimeException Si es produeix un error d'entrada/sortida durant el procés de desament.
     */
    @Override
    public void saveProducteJson(ArrayList<Producte> productes) {
        try (FileWriter writer = new FileWriter("Productes.json")) {
            gson.toJson(productes, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Carrega les dades dels productes des d'un fitxer JSON.
     *
     * @return Una llista d'objectes Producte llegida des del fitxer JSON.
     * @throws RuntimeException Si es produeix un error d'entrada/sortida durant el procés de càrrega.
     */
    @Override
    public ArrayList<Producte> loadProducteJson() {
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(path)));
            Type listaGenericType = new TypeToken<List<Producte>>(){}.getType();
            return gson.fromJson(fileContent, listaGenericType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor de la classe ProducteJsonDAO.
     * Inicialitza la variable gson utilitzant una nova instància de la classe Gson.
     */
    public ProducteJsonDAO() {
        this.gson = new Gson();  // Inicializar la variable gson en el constructor
    }

    //********************NO USAGES*********************************************************************
    /**
     * Guarda les dades de les tendes en un fitxer JSON local.
     *
     * @param tendes Llista d'objectes Tenda que es volen desar.
     */
    @Override
    public void saveTendaJson(ArrayList<Tenda> tendes) {

    }
    /**
     * Connecta amb l'API per realitzar operacions relacionades amb les tendes i els productes.
     */
    @Override
    public void connectAPI() {

    }
    /**
     * Carrega les dades dels productes des de l'API amb un nom específic.
     *
     * @param name Nom relacionat amb la cerca de productes a l'API.
     * @return Una llista d'objectes Producte carregada des de l'API.
     */
    @Override
    public ArrayList<Producte> loadProductAPI(String name) {
        return null;
    }
    /**
     * Guarda les dades dels productes a l'API.
     *
     * @param productes Llista d'objectes Producte que es volen desar a l'API.
     * @return
     */
    @Override
    public boolean saveProductAPI(ArrayList<Producte> productes) {
        return false;
    }
    /**
     * Carrega les dades de les tendes des de l'API amb un nom específic.
     *
     * @param name Nom relacionat amb la cerca de tendes a l'API.
     * @return Una llista d'objectes JsonObject carregada des de l'API.
     */
    @Override
    public ArrayList<JsonObject> loadTendaAPI(String name) {
        return null;
    }
    /**
     * Guarda les dades de les tendes a l'API.
     *
     * @param tendas Llista d'objectes Tenda que es volen desar a l'API.
     * @return
     */
    @Override
    public Boolean saveTendaAPI(ArrayList<Tenda> tendas) {
        return false;
    }
    /**
     * Carrega les dades de les tendes des d'un fitxer JSON local.
     *
     * @return Una llista d'objectes JsonObject carregada des del fitxer JSON.
     */
    @Override
    public ArrayList<JsonObject> loadTendaJson() {
        return null;
    }
}
