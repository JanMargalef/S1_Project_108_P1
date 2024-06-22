package PERSISTANCE.JSONPERSISTANCE;

import BUSSINESS.ENTITIES.Producte;
import BUSSINESS.ENTITIES.Tenda;
import PERSISTANCE.DataPersistanceJSON;
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
public  class ProducteJsonDAO implements DataPersistanceJSON {
    private final String path = "Productes.json";
    private Gson gson;  // Declarar la variable gson

    /**
     * Guarda les dades dels productes en un fitxer JSON.
     *
     * @param Productlist Llista d'objectes Producte que es volen desar.
     * @param tendalist no s'utilitza en aquesta implementació.
     * @throws RuntimeException Si es produeix un error d'entrada/sortida durant el procés de desament.
     */
    @Override
    public void saveInfoJson(ArrayList<Producte> Productlist, ArrayList<Tenda> tendalist){
        try (FileWriter writer = new FileWriter("Productes.json")) {
            gson.toJson(Productlist, writer);
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
    public ArrayList<JsonObject> loadInfoJson() {
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(path)));
            Type listaGenericType = new TypeToken<List<Producte>>() {
            }.getType();
            return gson.fromJson(fileContent, listaGenericType);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Constructor de la classe ProducteJsonDAO.
     * Inicialitza la variable gson utilitzant una nova instància de la classe Gson.
     */
    public ProducteJsonDAO() {
        this.gson = new Gson();  // Inicializar la variable gson en el constructor
    }


}
