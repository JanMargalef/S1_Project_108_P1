package BUSSINESS.ENTITIES;

import java.util.ArrayList;

/**
 * Classe que representa un producte amb impost súper reduït, que hereta de la classe Producte.
 */
public class ProducteSuperReduit extends Producte{

    private Integer iva = 4;
    private String tenda;
    private float preuTenda;

    /**
     * Constructor de la classe ProducteSuperReduit amb informació completa.
     *
     * @param nom Nom del producte.
     * @param marca Marca del producte (format corregit).
     * @param categoria Categoria del producte.
     * @param maxPreu Preu màxim del producte.
     * @param valoracions Llista d'avaluacions del producte.
     */
    public ProducteSuperReduit(String nom, String marca, String categoria, float maxPreu, ArrayList<String> valoracions) {
        super(nom, marca, categoria, maxPreu, valoracions);

    }
    /**
     * Constructor de la classe ProducteSuperReduit sense informació d'avaluacions.
     *
     * @param nom Nom del producte.
     * @param marca Marca del producte (format corregit).
     * @param categoria Categoria del producte.
     * @param maxPreu Preu màxim del producte.
     */
    public ProducteSuperReduit(String nom, String marca, String categoria, float maxPreu, ArrayList<String> valoracions, float preu, String tenda) {
        super(nom, marca, categoria, maxPreu);
        if(preuTenda >= 100) {
            this.iva = 0;
        }else{
            this.iva = 4;
        }
        this.preuTenda = preu;
        this.tenda = tenda;
    }

    /**
     * Obté l'Impost sobre el Valor Afegit (IVA) associat al producte amb impost súper reduït.
     *
     * @return Valor de l'IVA del producte amb impost súper reduït.
     */
    @Override
    public int getIva() {
        return iva;
    }

    /**
     * Es calcula el preu base del producte. Aquest no te en compte el IVA ni els descomptes.
     *
     * @return float amb el preu base del producte.
     */
    @Override
    public float getPreuBase(int recalcular) {
        float preuOriginal = 0;
        switch (recalcular){
            case 0:
                preuOriginal= preuTenda/ (1 - (iva/100));
                break;
            case 1:
                float preubase = preuTenda/ (1 - (iva/100));
                preuOriginal = preubase/ (1 - (iva/100));
                break;
            case 2:
                float preuDescompte = preuTenda * (90/100);
                preuOriginal = preuDescompte/ (1 - (iva/100));
                break;
        }

        return preuOriginal;
    }

    /**
     * Calcula el preu de venda amb iva i tenint en compte les ofertes.
     *
     * @param descompte boolea que indica si hi ha una oferta a aplicar.
     *
     * @return el preu final.
     */
    @Override
    public float getPreuIva(boolean descompte){
        if(descompte){
            return preuTenda *(90/100);
        }
        return preuTenda;
    }

    /**
     * S'obté la tenda on es ven el producte.
     *
     * @return la tenda on es ven el producte.
     */
    @Override
    public String getTenda(){
        return tenda;
    }
}
