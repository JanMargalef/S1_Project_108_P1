package PERSISTANCE.JSONPERSISTANCE;

import BUSSINESS.ENTITIES.Producte;
import BUSSINESS.ENTITIES.Tenda;
import PERSISTANCE.DataPersistanceJSON;
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
public class TendaJsonDAO implements DataPersistanceJSON {
    private final String path = "aTendes.json";
    private Gson gson;
    private String file = "";


    /**
     * Carrega les dades de les tendes des d'un fitxer JSON local.
     *
     * @return Una llista d'objectes JsonObject carregada des del fitxer JSON.
     */
    @Override
    public ArrayList<JsonObject> loadInfoJson() {
        try {
            file = new String(Files.readAllBytes(Paths.get(path)));
            Type listaGenericType = new TypeToken<List<JsonObject>>() {
            }.getType();
            return gson.fromJson(file, listaGenericType);
        } catch (IOException e) {
            return null;
        }


    }


    /**
     * Guarda les dades de les tendes en un fitxer JSON local.
     *
     * @param Productlist No s'utilitza en aquesta implementació.
     * @param tendalist LLista d'objectes Tenda que es volen desar.
     */
    @Override
    public void saveInfoJson(ArrayList<Producte> Productlist, ArrayList<Tenda> tendalist) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        try (FileWriter writer = new FileWriter("Tendes.json")) {
            gson.toJson(tendalist, writer);
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

    }

}