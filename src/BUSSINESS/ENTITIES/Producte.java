package BUSSINESS.ENTITIES;

import java.util.ArrayList;

/**
 * Classe que representa un producte amb la seva informació associada.
 */
public class Producte {
    private String name;
    private String brand;
    private float mrp;
    private String category;
    private ArrayList<String> reviews;

    //*********************************__CONSTRUCTORS__**********************************************************************
    /**
     * Constructor de la classe Producte amb informació completa.
     *
     * @param nom Nom del producte.
     * @param marca Marca del producte (format corregit).
     * @param categoria Categoria del producte.
     * @param maxPreu Preu màxim del producte.
     * @param valoracions Llista d'avaluacions del producte.
     */
    public Producte(String nom, String marca, String categoria, float maxPreu, ArrayList<String> valoracions) {
        this.name = nom;
        this.brand = CorregirFormatString(marca);
        this.category = categoria;
        this.mrp = maxPreu;
        this.reviews = valoracions;

    }
    /**
     * Constructor de la classe Producte sense informació d'avaluacions.
     *
     * @param nom Nom del producte.
     * @param marca Marca del producte (format corregit).
     * @param categoria Categoria del producte.
     * @param maxPreu Preu màxim del producte.
     */
    public Producte(String nom, String marca, String categoria, float maxPreu){
        this.name = nom;
        this.brand = CorregirFormatString(marca);
        this.category = categoria;
        this.mrp = maxPreu;
        this.reviews = new ArrayList<>();

    }


    //*********************************__GETTERS__**********************************************************************

    /**
     * retorna el nom del producte
     * @return retorna string nom producte
     */
    public String getName() {
        return name;
    }

    /**
     * retorna la marca del producte
     * @return
     */
    public String getBrand() {
        return brand;
    }

    /**
     * retorna la categoria del producte
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     * retorna el preu maxim de venta
     * @return
     */
    public float getMrp() {
        return mrp;
    }

    /**
     * retorna les reviews del producte
     * @return
     */
    public ArrayList<String> getReviews() {
        return reviews;
    }


    //*********************************__SETTERS__**********************************************************************

    /**
     * guarda les reviews del producte
     * @param newReview
     */
    public void setReviews(String newReview){
        this.reviews.add(newReview);

    }

    //*********************************__VERIFICAR FORMATS__**********************************************************************

    /**
     * Corretgeix el format de la string de marca fent que totes les
     * paraules comencin per majúscula.
     *
     * @param frase Frase original.
     *
     * @return frase amb totes les paraules començant amb majúscula.
     */
    private String CorregirFormatString(String frase){
        frase = frase.toLowerCase();
        String[] paraula = frase.split(" ");
        for (int i = 0; i < paraula.length; i++) {
            char s = paraula[i].charAt(0);
            s = Character.toUpperCase(s);
            paraula[i] = s + paraula[i].substring(1);
        }
        for (int i = 0; i < paraula.length; i++) {
            if(i == 0){
                frase = paraula[i];
            }else{
                frase += " " + paraula[i];
            }

        }
        return frase;
    }

    /**
     * Obté l'Impost sobre el Valor Afegit (IVA) associat al producte amb impost reduït.
     *
     * @return Valor de l'IVA del producte amb impost reduït.
     */
    public int getIva() {
        return 0;
    }

    /**
     * Es calcula el preu base del producte. Aquest no te en compte el IVA ni els descomptes.
     *
     * @return float amb el preu base del producte.
     */
    public float getPreuBase(int recalcular) {
        // si es 0, es calcula normal, si es 1 es recalcula el preu base a partir del preu base, si es 2 es fa a partir del preu normal amb 10% descompte
        return 0;
    }

    /**
     * Calcula el preu de venda amb iva i tenint en compte les ofertes.
     *
     * @param descompte boolea que indica si hi ha una oferta a aplicar.
     *
     * @return el preu final.
     */
    public float getPreuIva(boolean descompte) {
        return 0;
    }

    /**
     * S'obté la tenda on es ven el producte.
     *
     * @return la tenda on es ven el producte.
     */
    public String getTenda(){
        return null;
    }
}
