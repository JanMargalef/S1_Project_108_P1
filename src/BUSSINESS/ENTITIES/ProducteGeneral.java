package BUSSINESS.ENTITIES;

import java.util.ArrayList;

/**
 * Classe que representa un producte general, que hereta de la classe Producte.
 */
public class ProducteGeneral extends Producte{

    private transient Integer iva = 21;

    /**
     * Constructor de la classe ProducteGeneral amb informació completa.
     *
     * @param nom Nom del producte.
     * @param marca Marca del producte (format corregit).
     * @param categoria Categoria del producte.
     * @param maxPreu Preu màxim del producte.
     * @param valoracions Llista d'avaluacions del producte.
     */
    public ProducteGeneral(String nom, String marca, String categoria, float maxPreu, ArrayList<String> valoracions) {
        super(nom, marca, categoria, maxPreu, valoracions);
    }

    /**
     * Constructor de la classe ProducteGeneral sense informació d'avaluacions.
     *
     * @param nom Nom del producte.
     * @param marca Marca del producte (format corregit).
     * @param categoria Categoria del producte.
     * @param maxPreu Preu màxim del producte.
     */
    public ProducteGeneral(String nom, String marca, String categoria, float maxPreu) {
        super(nom, marca, categoria, maxPreu);
    }

    /**
     * Obté l'Impost sobre el Valor Afegit (IVA) associat al producte general.
     *
     * @return Valor de l'IVA del producte general.
     */
    @Override
    public int getIva() {
        return iva;
    }
}
