package BUSSINESS.ENTITIES;

import java.util.ArrayList;

/**
 * Classe que representa un producte associat a una tenda, que hereta de la classe Producte.
 */
public class ProducteTenda extends Producte{

    private float preuTenda;
    private String tenda;
    /**
     * Constructor de la classe ProducteTenda amb informació completa, incloent preu en tenda i tenda associada.
     *
     * @param nom Nom del producte.
     * @param marca Marca del producte (format corregit).
     * @param categoria Categoria del producte.
     * @param maxPreu Preu màxim del producte.
     * @param valoracions Llista d'avaluacions del producte.
     */
    public ProducteTenda(String nom, String marca, String categoria, float maxPreu, ArrayList<String> valoracions) {
        super(nom, marca, categoria, maxPreu, valoracions);
    }
    /**
     * Constructor de la classe ProducteTenda amb informació completa, incloent preu en tenda i tenda associada.
     *
     * @param nom Nom del producte.
     * @param marca Marca del producte (format corregit).
     * @param categoria Categoria del producte.
     * @param maxPreu Preu màxim del producte.
     */
    public ProducteTenda(String nom, String marca, String categoria, float maxPreu) {
        super(nom, marca, categoria, maxPreu);
    }

    /**
     * Constructor de la classe ProducteTenda amb informació completa, incloent preu en tenda i tenda associada.
     *
     * @param nom Nom del producte.
     * @param marca Marca del producte (format corregit).
     * @param categoria Categoria del producte.
     * @param maxPreu Preu màxim del producte.
     * @param preuTenda Preu específic del producte en la tenda.
     * @param tenda Nom de la tenda on es ven el producte.
     */
    public ProducteTenda(String nom, String marca, String categoria, float maxPreu, float preuTenda, String tenda) {
        super(nom, marca, categoria, maxPreu);

        this.preuTenda = preuTenda;
        this.tenda = tenda;
    }



    //*********************************__GETTERS__**********************************************************************

    /**
     * retorna el preu en tenda que te el producte
     * @return
     */
    public float getPreuTenda() {
        return preuTenda;
    }

    /**
     * retorna la tenda en la qual esta
     * @return
     */
    public String getTenda(){ return tenda;}
}
