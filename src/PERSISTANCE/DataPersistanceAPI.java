package PERSISTANCE;

import BUSSINESS.ENTITIES.Producte;
import BUSSINESS.ENTITIES.Tenda;
import com.google.gson.JsonObject;
import edu.salle.url.api.exception.ApiException;

import java.util.ArrayList;

public interface DataPersistanceAPI{
    /**
     * Carrega les dades dels productes des de l'API amb un nom específic.
     *
     * @return Una llista d'objectes Producte carregada des de l'API.
     */
    public ArrayList<JsonObject> loadInformation();
    /**
     * Guarda les dades dels productes a l'API.
     *
     * @param Productlist Llista d'objectes Producte o Tenda que es volen desar a l'API.
     * @param tendalist
     * @return Boolean Indoca si sha fet el guardat per api
     * @throws ApiException Si es produeix un error durant el desament de les dades a l'API.
     */
    public boolean saveInformation(ArrayList<Producte> Productlist,ArrayList<Tenda> tendalist) throws ApiException;
}
