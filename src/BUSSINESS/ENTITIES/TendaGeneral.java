package BUSSINESS.ENTITIES;

import java.util.ArrayList;

/**
 * Representa una tenda amb un incentiu de treure el maxim venefici
 */
public class TendaGeneral extends Tenda{
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
    public TendaGeneral(String nom, String descripcio, int anyFundacio, float ganacies, String modelNegoci, ArrayList<Producte> catalogue) {
        super(nom, descripcio, anyFundacio, ganacies, modelNegoci, catalogue);
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
    public TendaGeneral(String nom, String descripcio, int anyFundacio, String modelNegoci, ArrayList<Producte> catalogue) {
        super(nom, descripcio, anyFundacio, modelNegoci, catalogue);
    }


}
