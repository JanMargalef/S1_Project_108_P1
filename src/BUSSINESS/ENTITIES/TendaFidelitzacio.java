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
    public TendaFidelitzacio(String nom, String descripcio, int anyFundacio, float ganacies, String modelNegoci, ArrayList<ProducteTenda> catalogue, float preuFidelitzacio) {
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
    public TendaFidelitzacio(String nom, String descripcio, int anyFundacio, String modelNegoci, ArrayList<ProducteTenda> catalogue, float preuFidelitzacio) {
        super(nom, descripcio, anyFundacio, modelNegoci, catalogue);
        this.loyaltyThreshold = preuFidelitzacio;
    }

    /**
     * Obté el llindar de fidelitat per a la tenda.
     *
     * @return El llindar de fidelitat com a valor de punt flotant (Float).
     */
    public Float getLoyaltyThreshold() {
        return loyaltyThreshold;
    }
}
