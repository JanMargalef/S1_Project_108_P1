package BUSSINESS.ENTITIES;

import java.util.ArrayList;

/**
 * Representa una tenda amb un descompte per a les persones que comprin un producte patrocinat
 */
public class TendaSponsor extends Tenda{
    private String sponsorBrand;


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
    public TendaSponsor(String nom, String descripcio, int anyFundacio, float ganacies, String modelNegoci, ArrayList<Producte> catalogue, String sponsorBrand) {
        super(nom, descripcio, anyFundacio, ganacies, modelNegoci, catalogue);
        this.sponsorBrand = sponsorBrand;
    }

    /**
     * Constructor de l'objecte tenda al qual es passen els atributs de la tenda que es vol crear
     *
     * @param nom
     * @param descripcio
     * @param anyFundacio
     * @param modelNegoci
     * @param catalogue
     * @param sponsorBrand
     */
    public TendaSponsor(String nom, String descripcio, int anyFundacio, String modelNegoci, ArrayList<Producte> catalogue, String sponsorBrand) {
        super(nom, descripcio, anyFundacio, modelNegoci, catalogue);
        this.sponsorBrand = sponsorBrand;
    }

    /**
     * Obté el llindar de fidelitat per a la tenda.
     *
     * @return El llindar de fidelitat com a valor de punt flotant (Float).
     */
    @Override
    public String getSpecialCaracteristica() {
        return sponsorBrand;
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
        boolean aplicarDescompte = false;
        for (Producte producte: productes) {
            if(producte.getBrand().equals(sponsorBrand)){
                aplicarDescompte = true;
            }
        }
        if(aplicarDescompte){
            for (Producte producte: productes) {
                float preu = producte.getPreuIva(true);
                cost += preu;
                PreuProductes.addLast(preu);
                benefici += producte.getPreuBase(2);

            }

            PreuProductes.addLast(cost);


        }else{
            for (Producte producte: productes) {
                float preu = producte.getPreuIva(false);
                cost += preu;
                PreuProductes.addLast(preu);
                benefici += producte.getPreuBase(0);

            }
            PreuProductes.addLast(cost);
        }

        if(checkout){ // si realment vol finalitzar la compra ja es sumen els beneficis a la tenda
            this.setEarnings(benefici);

        }
        return PreuProductes;
    }
}
