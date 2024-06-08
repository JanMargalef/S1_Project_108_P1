package BUSSINESS.ENTITIES;

import java.util.ArrayList;

/**
 * Classe que representa un producte amb impost súper reduït, que hereta de la classe Producte.
 */
public class ProducteSuperReduit extends Producte{
    private transient Integer iva = 4;
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
    public ProducteSuperReduit(String nom, String marca, String categoria, float maxPreu) {
        super(nom, marca, categoria, maxPreu);
    }
    /**
     * Obté l'Impost sobre el Valor Afegit (IVA) associat al producte amb impost súper reduït.
     *
     * @return Valor de l'IVA del producte amb impost súper reduït.
     */
    public Integer getIva() {
        return iva;
    }
}
