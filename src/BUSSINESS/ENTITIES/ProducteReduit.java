package BUSSINESS.ENTITIES;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Classe que representa un producte amb impost reduït, que hereta de la classe Producte.
 */
public class ProducteReduit extends Producte{

    private transient Integer iva = 10;

    private float preuTenda;

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
    public ProducteReduit(String nom, String marca, String categoria, float maxPreu, ArrayList<String> valoracions, float preu) {
        super(nom, marca, categoria, maxPreu);
        this.preuTenda = preu;
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
    @Override
    public float getPreuBase() {
        float preuOriginal= preuTenda/ (1 - (iva/100));
        return preuOriginal;
    }
    @Override
    public float getPreuIva(){
        return preuTenda;
    }
}
