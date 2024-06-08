package BUSSINESS;

import BUSSINESS.ENTITIES.Producte;

import java.util.ArrayList;

/**
 * Classe que representa un carret de compres amb una llista de productes seleccionats.
 */
public class Carret {
    private ArrayList<Producte> productesCarrito;


    //*********************************__CONSTRUCTORS__*****************************************************************

    /**
     * constructor del carret on inicialitza el arrayList de productesTenda
     */
    public Carret() {
        productesCarrito = new ArrayList<>();
    }


    //*********************************__GETTERS__**********************************************************************

    /**
     * retorna l'arraylist de productes que te el carret guardats
     * @return
     */
    public ArrayList<Producte> getProductesCarrito() {
        return productesCarrito;
    }




    //*********************************__SETTERS__**********************************************************************

    /**
     * guarda un nou producte al carrito
     * @param productesCarrito
     */
    public void setProductesCarrito(Producte productesCarrito) {
        this.productesCarrito.add(productesCarrito);
    }

    /**
     * borra tots els productes del carrito
     */
    public void removeProductesCarrito(){
        this.productesCarrito.clear();
    }
}
