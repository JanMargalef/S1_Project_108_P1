package BUSSINESS.ENTITIES;

import java.util.ArrayList;

/**
 * Classe que representa una tenda amb informació com el nom, descripció, des de quan existeix, ingressos,
 * model de negoci i catàleg de productes.
 */
public class Tenda {
    private String name;
    private String description;
    private int since;
    private float earnings;
    private String businessModel;
    private ArrayList<Producte> catalogue = new ArrayList<>();


    //*********************************__CONSTRUCTORS__**********************************************************************

    /**
     * Constructor de l'objecte tenda al qual es passen els atributs de la tenda que es vol crear
     * @param nom Nom de la tenda
     * @param descripcio descripcio de la tenda
     * @param anyFundacio any de fundació de la tenda
     * @param ganacies ganacies de la tenda
     * @param modelNegoci model de negoci de la tenda
     * @param catalogue catalog de la tenda
     */
    public Tenda(String nom, String descripcio, int anyFundacio, float ganacies, String modelNegoci, ArrayList<Producte> catalogue) {
        this.name = nom;
        this.description = descripcio;
        this.businessModel = modelNegoci;
        this.since = anyFundacio;
        this.earnings = ganacies;
        this.catalogue.addAll(catalogue);
    }


    /**Constructor de l'objecte tenda al qual es passen els atributs de la tenda que es vol crear
     *
     * @param nom
     * @param descripcio
     * @param anyFundacio
     * @param modelNegoci
     * @param catalogue
     */
    public Tenda(String nom, String descripcio, int anyFundacio,String modelNegoci, ArrayList<Producte> catalogue) {
        this.name = nom;
        this.description = descripcio;
        this.businessModel = modelNegoci;
        this.since = anyFundacio;
        this.catalogue.addAll(catalogue);

    }




    //*********************************__GETTERS__**********************************************************************

    /**
     * Retorna les ganancies obtingudes
     * @return
     */
    public float getEarnings() {
        return earnings;
    }

    /**
     * retorna el catalog de la tenda
     * @return
     */
    public ArrayList<Producte> getCatalogue() {
        return catalogue;
    }

    /**
     * retorna el nom de la tenda
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * retorna la descripcio de la tenda
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * retorna el model de negoci de la tenda
     * @return
     */
    public String getBusinessModel() {
        return businessModel;
    }

    /**
     * retorna l'any de fundació de la tenda
     * @return
     */
    public int getSince() {
        return since;
    }



    //*********************************__SETTERS__**********************************************************************

    /**
     * Modifica el catalog de la tenda afegint productes nous
     * @param Producte objecte que es vol afegir a la tenda
     */
    public void setCatalogue(Producte Producte){
        this.catalogue.add(Producte);
    }

    /**
     * eliminar el producte del cataleg
     * @param index posicio en l'arrayList del producte en el cataleg que es vol eliminar
     */
    public void removeProductCatalogue(int index){
        this.catalogue.remove(index);
    }

    /**
     * suma el guanyat a el total de ganancies
     * @param profit
     */
    public void setEarnings(float profit){
        this.earnings = earnings + profit;
    }

    /**
     * Obté el llindar de fidelitat per a la tenda.
     *
     * @return El llindar de fidelitat com a valor de punt flotant (Float).
     */
    public String getSpecialCaracteristica() {
        return "";
    }

    /**
     * Funció que calcula el preu de tots els productes amb els seus descomptes pertinents.
     *
     * @param productes llista de productes que compra l'usuari.
     * @param checkout boolea que indica si l'usuari realitza ja la compra.
     *
     * @return cost total de la compra.
     */
    public float calculPreuProductes(ArrayList<Producte> productes, boolean checkout){
        return 0;
    }

}
