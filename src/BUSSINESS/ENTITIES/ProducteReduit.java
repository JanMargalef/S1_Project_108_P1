package BUSSINESS.ENTITIES;

import java.util.ArrayList;

/**
 * Classe que representa un producte amb impost reduït, que hereta de la classe Producte.
 */
public class ProducteReduit extends Producte{
    private transient Integer iva = 10;

    /**
     * Constructor de la classe ProducteReduit amb informació completa.
     *
     * @param nom Nom del producte.
     * @param marca Marca del producte (format corregit).
     * @param categoria Categoria del producte.
     * @param maxPreu Preu màxim del producte.
     * @param valoracions Llista d'avaluacions del producte.
     */
    public ProducteReduit(String nom, String marca, String categoria, float maxPreu, ArrayList<String> valoracions) {
        super(nom, marca, categoria, maxPreu, valoracions);
    }

    /**
     * Constructor de la classe ProducteReduit sense informació d'avaluacions.
     *
     * @param nom Nom del producte.
     * @param marca Marca del producte (format corregit).
     * @param categoria Categoria del producte.
     * @param maxPreu Preu màxim del producte.
     */
    public ProducteReduit(String nom, String marca, String categoria, float maxPreu) {
        super(nom, marca, categoria, maxPreu);
    }
    /**
     * Obté l'Impost sobre el Valor Afegit (IVA) associat al producte amb impost reduït.
     *
     * @return Valor de l'IVA del producte amb impost reduït.
     */
    @Override
    public int getIva() {
        return iva;
    }
}
