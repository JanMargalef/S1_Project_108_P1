package BUSSINESS.ENTITIES;

import java.util.ArrayList;

/**
 * Representa una tenda amb un llindar de fidelitat específic.
 */
public class TendaFidelitzacio extends Tenda{

    private Float loyaltyThreshold;

    /**
     * Constructor de l'objecte tenda al qual es passen els atributs de la tenda que es vol crear
     *
     * @param nom         Nom de la tenda
     * @param descripcio  descripcio de la tenda
     * @param anyFundacio any de fundació de la tenda
     * @param ganacies    ganacies de la tenda
     * @param modelNegoci model de negoci de la tenda
     * @param catalogue   catalog de la tenda
     */
    public TendaFidelitzacio(String nom, String descripcio, int anyFundacio, float ganacies, String modelNegoci, ArrayList<Producte> catalogue, float preuFidelitzacio) {
        super(nom, descripcio, anyFundacio, ganacies, modelNegoci, catalogue);
        this.loyaltyThreshold = preuFidelitzacio;
    }

    /**
     * Constructor de l'objecte tenda al qual es passen els atributs de la tenda que es vol crear
     *
     * @param nom
     * @param descripcio
     * @param anyFundacio
     * @param modelNegoci
     * @param catalogue
     */
    public TendaFidelitzacio(String nom, String descripcio, int anyFundacio, String modelNegoci, ArrayList<Producte> catalogue, float preuFidelitzacio) {
        super(nom, descripcio, anyFundacio, modelNegoci, catalogue);
        this.loyaltyThreshold = preuFidelitzacio;
    }

    /**
     * Obté el llindar de fidelitat per a la tenda.
     *
     * @return El llindar de fidelitat com a valor de punt flotant (Float).
     */
    @Override
    public String getSpecialCaracteristica() {
        return loyaltyThreshold.toString();
    }

    /**
     * Funció que calcula el preu de tots els productes amb els seus descomptes pertinents.
     *
     * @param productes llista de productes que compra l'usuari.
     * @param checkout  boolea que indica si l'usuari realitza ja la compra.
     * @return cost total de la compra.
     */
    @Override
    public ArrayList<Float> calculPreuProductes(ArrayList<Producte> productes, boolean checkout){
        ArrayList<Float> PreuProductes = new ArrayList<Float>();
        float cost = 0;
        float benefici = 0;
        for (Producte producte: productes) {
            float preu = producte.getPreuIva(false);
            cost += preu;
            PreuProductes.addLast(preu);
            benefici += producte.getPreuBase(0);
        }
        PreuProductes.addLast(cost);

        if(cost >= loyaltyThreshold || (this.getEarnings() + cost)>= loyaltyThreshold){
            cost = 0;
            benefici = 0;
            PreuProductes.clear();
            for (Producte producte: productes) {
                float preu = producte.getPreuBase(0);
                cost += preu;
                PreuProductes.addLast(preu);
                benefici += producte.getPreuBase(1);
            }
            PreuProductes.addLast(cost);
        }
        if(checkout){ // si realment vol finalitzar la compra ja es sumen els beneficis a la tenda
            this.setEarnings(benefici);

        }
        return PreuProductes;
    }
}
