package PRESENTATION;

import BUSSINESS.Carret;
import BUSSINESS.ENTITIES.Producte;
import BUSSINESS.ENTITIES.Tenda;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
/**
 * Classe que gestiona la interfície d'usuari (UI) i proporciona mètodes per interactuar amb l'usuari a través
 * de la línia de comandes. Conté textos predefinits per a missatges, menús i altres elements relacionats amb la UI.
 */
public class ManagerUI {

    private Scanner scanner;

    private static final String textLogo = " ________ ____ \n" +
            " ___ / / ____/___ / __/_______ \n" +
            "/ _ \\/ / / / __ \\/ /_/ ___/ _ \\\n" +
            "/ __/ / /___/ /_/ / __/ / / __/\n" +
            "\\___/_/\\____/\\____/_/ /_/ \\___/ \n" +
            " \n" +
            "Welcome to elCofre Digital Shopping Experiences.";
    private static final String textVerificacioLecturaFilesApi = "Checking API status...";
    private static final String textErrorLecturaApi = "Error: The API isn’t available.";
    private static final String textVerificacioLecturaFilesJson = "Verifying local files...";
    private static final String textErrorLecturaProductes = "Error: The products.json file can’t be accessed.";
    private static final String textErrorLecturaTendes = "Error: The Tends.json file can’t be accessed.";
    private static final String textApagantSistema = "Shutting down...";
    private static final String textIniciantSistema = "Starting program...\n";
    private static final String textErrorEntradaMenuInici = "Error: Enter a valid number";
    private static final String textMenuPrincipal = "\n1) Manage Products\n" +
            "2) Manage Shops\n" +
            "3) Search Products\n" +
            "4) List Shops\n" +
            "5) Your Cart\n" +
            " \n6)Exit\n" +
            "\nChoose a Digital Shopping Experience: ";


    private static final String textMenuGestioProducte =
            "\n   1) Create a Product\n" +
                    "   2) Remove a Product\n" +
                    "\n   3) Back\n" +
                    "\nChoose an option: ";

    private static final String textCategoriesProducte =
            "\nThe system supports the following product categories:\n" +
                    "   A) General\n" +
                    "   B) Reduced Taxes\n" +
                    "   C) Superreduced Taxes\n" +
                    "Please pick the product’s category: ";
    private static final String textMenuGestioTendes =
            "   1) Create a Shop\n" +
                    "   2) Expand a Shop’s Catalogue\n" +
                    "   3) Reduce a Shop’s Catalogue\n" +
                    "   4) Back\n" +
                    "Choose an option: ";

    private static final String textModelNegociTenda =
            "\nThe system supports the following business models:\n" +
                    "   A) Maximum Benefits\n" +
                    "   B) Loyalty\n" +
                    "   C) Sponsored\n" +
                    "Please pick the shop’s business model: ";

    private static final String textErrorEntrada = "ERROR: Error, enter a valid letter";
    private static final String textEleccioMenuGestio = "";
    private static final String textFatalError = "ERROR: Error not expexted";

    //*********************************__CONSTRUCTORS__**********************************************************************

    /**
     * Constructor de managerUI, inicia el Scanner per poder llegir els valors que entren de part de l'usuari
     */
    public ManagerUI() {
        this.scanner = new Scanner(System.in);
    }


    //*********************************__TEXT A MOSTRAR__**********************************************************************

    /**
     * Totes aquestes funcions unicament el que fan es mostrar tots els texts que mes es repeteixen
     */
    public void showIniciMenu() {

        System.out.println(textLogo);
        System.out.println("\n" + textVerificacioLecturaFilesApi);

    }

    /**
     * Finalitza l'aplicació mostrant el missatge indicant que el sistema s'està apagant.
     */
    public void exit() {
        System.out.println(textApagantSistema);
    }
    /**
     * Mostra un missatge indicant que el sistema s'està iniciant.
     */
    public void startingMessage() {
        System.out.println(textIniciantSistema);
    }
    /**
     * Mostra un missatge indicant que el producte s'ha creat amb èxit.
     */
    public void producteCreat() {
        System.out.println("Created Successfully");
    }
    /**
     * Mostra un missatge indicant que el producte ja existeix.
     */
    public void producteExistent() {
        System.out.println("The product already exists");
    }
    /**
     * Mostra un missatge indicant que la tenda s'ha creat amb èxit.
     */
    public void tendaCreat() {
        System.out.println("Created Successfully");
    }
    /**
     * Mostra un missatge indicant que la tenda ja existeix.
     */
    public void tendaExistent() {
        System.out.println("The shop already exists");
    }
    /**
     * Mostra un missatge indicant que el producte s'ha afegit amb èxit.
     */
    public void producteAfegitCorrectament() {
        System.out.println("Added Successfully");
    }


    // *********************************__MISSATGE DE CONTROL__**********************************************************************

    /**
     * Aquesta funcio el que fa es indicar si la tenda existeix o no
     * @param resultat es el resultat de la operacio executada per el managerTenda
     * @return retorna el resultat de Resultat
     */
    public boolean tendaNoExistent(Boolean resultat) {
        if (!resultat) {
            System.out.println("The shop doesn't exist");
            return false;
        } else {
            return true;
        }

    }

    /*** Aquesta funcio el que fa es indicar si el producte existeix o no
     * @param resultat es el resultat de la operacio executada per el managerProducte
     * @return retorna el resultat de Resultat
     */
    public boolean producteNoExistent(Boolean resultat) {
        if (!resultat) {
            System.out.println("The product doesn't exist");
            return false;
        } else {
            return true;
        }

    }

    /*** Aquesta funcio el que fa es indicar si el preu supera el maxim o no
     * @param resultat es el resultat de la operacio executada per comprovar si sobrepasa o no
     * @return retorna el resultat de Resultat
     */
    public boolean preuSuperiorMaxPreu(Boolean resultat) {
        if (!resultat) {
            System.out.println("The price is over the limit price");
            return false;
        } else {
            return true;
        }

    }

    /**
     * Es el missatge que s'envia si sha borrat correctament el producte del cataleg
     * @param resultat Es passa el valor resultant de si sha fet la operacio o no
     * @param nomTenda Es pasa el nom de la tenda per mostrarho
     * @param name es passa el nom del producte per mostrarho
     * @param brand es pasa la marca del producte per mostrarho
     */
    public void messageBorrarProducteCataleg(Boolean resultat, String nomTenda, String name, String brand) {
        if (resultat) {
            System.out.println("\"" + name + "\" by \"" + brand + "\" is no longer being sold at \"" + nomTenda + "\"");

        } else {
            System.out.println("Not possible to remove");
        }
    }

    // *********************************__MENUS A MOSTRAR__**********************************************************************

    /**
     * Aquest es el menu principal on es mostra el logo i despres es retorna la seleccio feta per el usuari
     * @return retorna un Enum (menuOptions) a controller per indicar al switch quin ha estat el valor seleccionat per el usuari
     */
    public MenuOpcions showMenuPrincipal() { // Menu principal del sistema
        System.out.print(textMenuPrincipal);
        try {
            int option = Integer.parseInt(this.scanner.nextLine());
            return switch (option) {
                case 1 -> MenuOpcions.GESTIO_PRODUCTES;
                case 2 -> MenuOpcions.GESTIO_TENDES;
                case 3 -> MenuOpcions.BUSCAR_PRODUCTES;
                case 4 -> MenuOpcions.LLISTAR_TENDES;
                case 5 -> MenuOpcions.CARRITO;
                case 6 -> MenuOpcions.EXIT;
                default -> {
                    System.out.println(textErrorEntradaMenuInici);
                    yield MenuOpcions.MENU_PRINCIPAL;
                }
            };
        } catch (NumberFormatException var2) {
            System.out.println(textErrorEntradaMenuInici);
            return MenuOpcions.MENU_PRINCIPAL;
        }
    }

    /**
     * Aquest es el menu de gestionar els productes generics
     * @return retornar un enum indicant quin valor ha elegit el usuari
     */
    public MenuGestioProductes menuGestioProducte() { // menu de gestio de productes
        System.out.print(textMenuGestioProducte);
        try {
            int option = Integer.parseInt(this.scanner.nextLine());
            switch (option) {
                case 1:
                    return MenuGestioProductes.CREAR_PRODUCTE;
                case 2:
                    return MenuGestioProductes.BORRAR_PRODUCTE;
                case 3:
                    return MenuGestioProductes.BACK;

                default:
                    System.out.println(textErrorEntradaMenuInici);
            }
        } catch (NumberFormatException var2) {
            System.out.println(textErrorEntradaMenuInici);
        }
        return MenuGestioProductes.ERROR;
    }

    /**
     * Missatge de error a causa de no poder carregar correctament el fitxer de tendes
     */
    public void showIniciMenuErrorLoad(boolean isProduct) {
        if(isProduct) {
            System.out.println(textErrorLecturaProductes);
        }
        else {
            System.out.println(textErrorLecturaTendes);
        }
    }

    /**
     * Mostra el menu de gestio tendes i retorna el valor desijar per el usuari
     * @return retorna el enum desitjat per el usuari
     */
    public MenuGestioTendes menuGestioTendes() {
        System.out.print(textMenuGestioTendes);
        try {
            int option = Integer.parseInt(this.scanner.nextLine());
            switch (option) {
                case 1:
                    return MenuGestioTendes.CREAR_TENDA;
                case 2:
                    return MenuGestioTendes.EXPANDIR_CATALEG;
                case 3:
                    return MenuGestioTendes.REDUIR_CATALEG;
                case 4:
                    return MenuGestioTendes.BACK;

                default:
                    System.out.println(textErrorEntradaMenuInici);
            }
        } catch (NumberFormatException var2) {
            System.out.println(textErrorEntradaMenuInici);
        }
        return MenuGestioTendes.BACK;
    }


    // *********************************__REQUEST A USUARI__**********************************************************************

    /**
     * Pregunta a l'usuari que introdueixi el que se li especifica en el text que li apareix i retorna aquest valor introuit
     * @return rertorna el valor introduit per el usuari
     */
    public String requestnomNouProducte() { // retorna
        System.out.print("Please enter the product’s name: ");
        String option = null;
        try {
            option = this.scanner.nextLine();

        } catch (NumberFormatException var2) {
            System.out.println(textFatalError);
        }
        return option;
    }

    /**
     Pregunta a l'usuari que introdueixi el que se li especifica en el text que li apareix i retorna aquest valor introuit
     * @return rertorna el valor introduit per el usuari
     */
    public String requestMarcaNouProducte() {
        System.out.print("Please enter the product’s brand: ");
        String option = null;
        try {
            option = this.scanner.nextLine();

        } catch (NumberFormatException var2) {
            System.out.println(textFatalError);
        }
        return option;
    }

    /**
     * Pregunta a l'usuari que introdueixi el que se li especifica en el text que li apareix i retorna aquest valor introuit
     *     Tambe comprova que aquest valor compleixi uns certs requisits com que sigui positiu i sigui un numero
     * @return rertorna el valor introduit per el usuari
     */

    public float requestPreuMaxNouProducte() {
        float option = 0.0f;
        boolean positive;
        do {
            System.out.print("Please enter the product’s maximum retail price: ");
            try {
                option = Float.parseFloat(this.scanner.nextLine());
                if(option<=0){
                    positive = false;
                }else{
                    positive = true;
                }
            } catch (NumberFormatException var2) {
                positive = false;
                System.out.println("Enter a number and this must be positive");
            }
        } while (!positive);

        return option;
    }

    /**
     * Pergunta a l'usuari que introdueixi una lletra per indicar quin es el valor que es vol introduir
     * @return retorna un text respecte la eleccio de l'ususuari
     */
    public String requestCategoriaNouProducte() {
        boolean option = false;
        do {
            System.out.print(textCategoriesProducte);

            try {

                switch (this.scanner.nextLine()) {
                    case "A", "a":
                        option = false;
                        return "GENERAL";
                    case "B", "b":
                        option = false;
                        return "REDUCED";
                    case "C", "c":
                        option = false;
                        return "SUPER_REDUCED";
                    default:
                        option = true;
                }

            } catch (NumberFormatException var2) {
                System.out.println(textErrorEntrada);
            }
        } while (option);

        return "";
    }

    /**
     * Aquesta funcio demana a l'ususari si es vol eliminar o no el producte que ha decidit eliminar, previament es mostra tot el llistat de productes i despres es fa una serie de preguntes
     * a l'usuari per determinar quin es vol eliminar i si finalment es vol eliminar o no
     * @param productes Es passa la llista de tots els productes generics existents
     * @return retorna un integer indicant quin sha de eliminar segons la posició a l'arrayList
     */
    public int requestDeleteProducte(ArrayList<Producte> productes) {
        int option = 0;
        for (int i = 0; i < productes.size(); i++) {
            System.out.println(i + 1 + ") \"" + productes.get(i).getName() + "\" by \"" + productes.get(i).getBrand() + "\"");
        }
        System.out.print("\n" + (productes.size() + 1) + ") Back\n\n");
        boolean correct = false;
        do{
            try {
                System.out.print("Which one would you like to remove?");
                option = Integer.parseInt(this.scanner.nextLine()) - 1;

                if (option < productes.size()) {
                    correct = true;
                    System.out.print("Are you sure you want to remove \"" + productes.get(option).getName() + "\" by \"" + productes.get(option).getBrand() + "\"? ");
                    String confirmation = this.scanner.nextLine().toLowerCase();

                    while (!confirmation.equals("yes") && !confirmation.equals("no")) {
                        System.out.println("Please enter 'yes' or 'no'.");
                        System.out.print("Are you sure you want to remove \"" + productes.get(option).getName() + "\" by \"" + productes.get(option).getBrand() + "\"? ");
                        confirmation = this.scanner.nextLine().toLowerCase();
                    }

                    if (confirmation.equals("yes")) {
                        System.out.println("\"" + productes.get(option).getName() + "\" by \"" + productes.get(option).getBrand() + "\" has been withdrawn from sale.");
                        return option;
                    } else if (confirmation.equals("no")) {
                        return productes.size();
                    }
                } else if (option == productes.size()) {
                    return productes.size();
                }else {
                    System.out.println("ERROR: Please enter a valid option.");
                }

            } catch (Exception e) {
                System.out.println("ERROR: Please enter a valid option.");
                correct = false;
            }
        }while (!correct);


        System.out.println("ERROR: Please enter a valid option.");

        return productes.size();

    }

    /**
     * Demana a l'usuari que introdueixi el nom de la tenda
     * @return retorna el valor introduit per l'usuari
     */
    public String requestnomNouTenda() { // retorna
        System.out.print("Please enter the shop’s name: ");
        String option = null;
        try {
            option = this.scanner.nextLine();

        } catch (NumberFormatException var2) {
            System.out.println(textFatalError);
        }
        return option;
    }

    /**
     *  demana que l'usuari introdueixi una descripció a la tenda
     * @return retorna el introduit per l'usuari
     */
    public String requestDescripcioNouTenda() { // retorna
        System.out.print("Please enter the shop’s description: ");
        String option = null;
        try {
            option = this.scanner.nextLine();

        } catch (NumberFormatException var2) {
            System.out.println(textFatalError);
        }
        return option;
    }

    /**
     * Demana a l'usuari que introdueixi el anyde fundació de la tenda controlant que sigui superior o igual a 0
     * @return retorna el any de fundació
     */
    public int requestAnyFundacioNouTenda() { // retorna
        int option = 0;
        do {
            System.out.print("Please enter the shop’s founding year: ");
            try {
                option = Integer.parseInt(this.scanner.nextLine());
                if (option >= 0 && option < 2025) {
                    return option;
                } else {
                    System.out.println("Number has to be positive and bellow 2025");

                }

            } catch (NumberFormatException var2) {
                System.out.println(textFatalError);
                option = -1;
            }

        } while (!(option >= 0 && option < 2025));
        return option;
    }

    /**
     * Menu per indicar a l'usuari quins tipus de model de negoci hi han
     * @return Retorna en forma de string la eleccio feta per el usuari
     */
    public String requestModelNegociNouTenda() {
        boolean option = false;
        do {
            System.out.print(textModelNegociTenda);

            try {

                switch (this.scanner.nextLine()) {
                    case "A", "a":
                        return "MAX_PROFIT";
                    case "B", "b":
                        return "LOYALTY";
                    case "C", "c":
                        return "SPONSORED";
                    default:
                        System.out.println("ERROR: Error, enter a valid letter");
                        option = false;
                }

            } catch (NumberFormatException var2) {
                System.out.println(textErrorEntrada);
            }
        } while (!option);

        return "";
    }

    /**
     * Sol·licita i retorna la marca patrocinadora de la botiga introduïda per l'usuari.
     *
     * @return La marca patrocinadora de la botiga introduïda per l'usuari.
     */
    public String requestSponsoredBrand(){
        try{
            System.out.print("Please enter the shop’s sponsoring brand:");
            return this.scanner.nextLine();
        }catch (NumberFormatException var2) {
            System.out.println(textFatalError);
        }

        return ("");

    }

    /**
     * Sol·licita i retorna el llindar de fidelitat de la botiga introduït per l'usuari.
     *
     * @return El llindar de fidelitat de la botiga introduït per l'usuari com a valor de punt flotant.
     */
    public float requestLoyalty(){
        float option = -1;
        do {
            try{
                System.out.print("Please enter the shop’s loyalty threshold:");
                option= Float.parseFloat(this.scanner.nextLine());
                if(option >= 0) {
                    return option;
                }else{
                    System.out.println("Number has to be positive");
                    option = -1;
                }
            }catch (NumberFormatException var2) {
                System.out.println(textFatalError);
            }
        }while (!(option >= 0));

        return (0);

    }


    /**
     * Aquesta funcio mostra el cataleg de la tenda que sha seleccionat  retornant quin d'aquests s'ha de eliminar
     * @param tendas es passa el arrayList complet de totes les tendes
     * @param nom es pasa el nom de la tenda que es vol veure
     * @return retorna el objecte Producte el qual ha de ser eliminat
     */
    public Producte requestmostrarCatalegTenda(ArrayList<Tenda> tendas, String nom) {
        int option;
        ArrayList<Producte> cataleg = new ArrayList<>();
        System.out.println("This shop sells the following products: ");

        for (Tenda tendes : tendas) {
            if (tendes.getName().equals(nom)) {
                cataleg = tendes.getCatalogue();
                for (int i = 0; i < cataleg.size(); i++) {
                    System.out.println("   " + (i + 1) + ") " + cataleg.get(i).getName() + "\" by " + "\"" + cataleg.get(i).getBrand() + "\"");
                }
                System.out.println("   " + (cataleg.size() + 1) + ") Back");
                do{
                    System.out.print("\nWhich one would you like to remove? ");
                    try {
                        option = Integer.parseInt(this.scanner.nextLine());
                        option = option - 1;
                        if (option < cataleg.size()) {
                            return tendes.getCatalogue().get(option);
                        }else if(option == cataleg.size()){
                            return null;

                        }else{
                            System.out.println("Enter a number from the list");
                        }
                    }catch (NumberFormatException e) {
                        System.out.println("Enter a number from the list");
                    }

                }while (true);


            }
        }

        return null;

    }

    /**
     * Pregunta l query a buscar
     * @return retorna el valor introduit per el usuari
     */
    public String requestQuery() {
        String query = "";
        System.out.print("\nEnter your query:");
        query = scanner.nextLine();
        if(query.isEmpty()){
            do{
                System.out.print("\nEnter a not empty query:");
                query = scanner.nextLine();
            }while (query.isEmpty());
        }

        return query;
    }

    /**
     * Aquesta funció mostra els productes que existeixen que coincideixin amb el valor que hi ha a query. La funcio previament el busca a la llista de productes i si existeix ho busca a veure
     * si es ven a alguna tenda, si es aixi mostra el preu i l nom de la tenda on s'esta venent
     * @param tendes ArrayList on estan totes les tendes existents
     * @param productes ArrayList de tots els productes existents
     * @param query String del valor que el usuari ha introduit
     * @return retorna el objecte producte indicant que aquest es el seleccionat
     */
    public Producte showAskedProducts(ArrayList<Tenda> tendes, ArrayList<Producte> productes, String query) {

        System.out.println("The following products where found:\n");
        int contador = 0;
        boolean trobatEnTenda = false;
        boolean coincidencia = false;
        ArrayList<Producte> productesTrobats = new ArrayList<>();

        for (Producte product:productes) {

            if(product.getName().contains(query) || product.getBrand().contains(query)){
                productesTrobats.add(product);
                System.out.println("   " + (contador + 1) + ") " + product.getName() + "\" by " + "\"" + product.getBrand() + "\"");
                trobatEnTenda = false;
                coincidencia = true;
                contador ++;
                for (Tenda tenda: tendes) {
                    ArrayList<Producte> cataleg = tenda.getCatalogue();

                    for (Producte prodCataleg: cataleg) {

                        if(prodCataleg.getName().equals(product.getName())){
                            String nomTenda = tenda.getName();
                            float preu = prodCataleg.getPreuIva(false);
                            trobatEnTenda = true;
                            System.out.println("Sold at:\n" +
                                    "    - " + nomTenda + ": " + preu + "\n");
                            break;
                        }
                    }
                }
                if (!trobatEnTenda) {
                    System.out.println("This product is not currently being sold in any shops.\n");

                }
            }

        }

        if(!coincidencia){
            System.out.println("This product doesn't exist");
        }

        System.out.println("    " + (contador +1) + ") Back");
        boolean format = false;
        int option = 0;
        do{
            System.out.print("Which one would you like to review?");

            try {
                option = Integer.parseInt(this.scanner.nextLine());
                if((contador + 1)< option){
                    System.out.println("Enter a valid option");
                }else{
                    format = true;
                }

            }catch (NumberFormatException e){
                format = false;
                System.out.print("Incorrect format, enter a number\n");

            }
        }while (!format);


        option = option -1;
        //fem que retorni el objecte senser per a la seguent funcio elegir si mirar les reviews de aquell objecte o afegir
        if(option < productesTrobats.size()){
            return productesTrobats.get(option);
        }else{
            return null;
        }


    }

    /**
     * Menu per indicar les opcions que te de reviews
     * @return retorna en integer la opcio elegida
     */
    public int requestReviewTipo() {
        System.out.println( "   1) Read Reviews\n" +
                "   2) Review Product");
        do {
            System.out.print("Choose an option:");
            try {
                int option = Integer.parseInt(this.scanner.nextLine());
                if(option == 1 || option == 2){
                    return option;
                }else{
                    System.out.println("Enter a valid option");
                }
            }catch (NumberFormatException e){
                System.out.println("Enter a valid option");
            }
        }while (true);

    }

    /**
     * Mostra les reviews del producte que li han passat, fent la mitja de les valoracions
     * @param producte es el producte el qual es vol veure les valoracions
     */
    public void showReviews(Producte producte){
        System.out.println("These are the reviews for \"" + producte.getName() + "\" by " + "\"" + producte.getBrand() + "\"");
        ArrayList<String> reviews = producte.getReviews();
        double mitja = 0;
        double cantitat = 0;
        double suma = 0;
        for (String review : reviews) {
            System.out.println(review); // ja es guarda amb el format "4* So tasty, just..."
            suma = suma + Integer.parseInt(review.substring(0,1));
            cantitat ++;
        }
        if(cantitat!=0){
            mitja = (suma)/(cantitat);
        } // comprovem que no dividim per 0
        System.out.println("Average rating:" + mitja +"*");
    }

    /**
     * Mostr les reviews del producte que se li ha passar a partir del nom.
     * @param nom es el nom del producte el qual es vol buscar per mostrar les reviews
     * @param productes es passa el arrayList dels productes generics per mostrar
     */
    public void showReviews(String nom, ArrayList<Producte> productes){
        for (Producte product:productes) {
            if(product.getName().equals(nom)){
                System.out.println("These are the reviews for \"" + product.getName() + "\" by " + "\"" + product.getBrand() + "\"");
                ArrayList<String> reviews = product.getReviews();
                float mitja = 0;
                int cantitat = 0;
                int suma = 0;
                for (String review : reviews) {
                    System.out.println(review); // ja es guarda amb el format "4* So tasty, just..."
                    suma = suma + Integer.parseInt(review.substring(0,1));
                    cantitat ++;
                }
                if(cantitat!=0){
                    mitja = (float) suma /cantitat;
                } // comprovem que no dividim per 0
                System.out.println("Average rating:" + mitja +"*");
            }

        }
    }


    /**
     * Aquesta funcio demana a l'usuari que indiqui el numero de estrelles que vol guardar a la review del producte
     * @return retorna un integer sen aquest entre 1 i 5 indicant el numero de estrelles
     */
    public int reviewStars() {

        boolean done = false;
        do{
            try {
                System.out.print("Please rate the product (1-5 stars):");
                String stars = this.scanner.nextLine();
                if((stars.length()<=5)&&(!stars.isEmpty())) {
                    switch (stars) {
                        case "*":
                            done = true;
                            return 1;
                        case "**":
                            done = true;

                            return 2;
                        case "***":
                            done = true;
                            return 3;
                        case "****":
                            done = true;
                            return 4;
                        case "*****":
                            done = true;
                            return 5;
                        default:
                            System.out.println("Enter the caracter '*' for the number of stars");
                            done = false;

                            break;
                    }
                }else{
                    System.out.println("Enter the caracter '*' for the number of stars");
                    done = false;
                }

            }catch (Exception e){
                System.out.println("Enter a '*'");
            }
        }while (!done);
        return 0;
    }

    /**
     * pregunta a l'usuari quin es el comentari a afegir a la review
     * @param producte es passa el producte al qual se li esta fent la review
     * @return retorna el comentari fet per l'usuari
     */
    public String reviewText(Producte producte) {
        System.out.print("Please add a comment to your review:");
        String text = this.scanner.nextLine();
        System.out.println("Thank you for your review of \"" + producte.getName() + "\" by " + "\"" + producte.getBrand() + "\"");
        return text;
    }

    /**
     * pregunta a lusuari que introdueixi el valor de la llista de la tenda la qual es vol veure le cataleg
     * @param tendes arrayList de tendes per poder mostrarles
     * @return retorna el integer amb la posició de l'array de la tenda que s'ha seleccionat
     */
    public int requestShops(ArrayList<Tenda> tendes){
        int i = 0;
        int option = 0;

        System.out.println("\nThe elCofre family is formed by the following shops:\n");
        for(i = 0; i < tendes.size(); i++){
            System.out.println("\t" + (i +1) + ") " + tendes.get(i).getName());
        }
        System.out.println("\n\t" + (i +1) + ") Back\n");

        option = getIntBetween(1, i+1, "Which catalogue do you want to see? ");
        return option -1;
    }

    /**
     * Pregunta a l'usuari quin es el producte del cataleg el qual vol seleccionar
     * @param tenda es passa la tenda que s'ha seleccionat
     * @return es retorna la posició de l'array del cataleg respecte el producte elegit
     */
    public int requestProduct(Tenda tenda){
        int i = 0;
        int option = 0;

        System.out.println("\n" + tenda.getName() + " - Since " + tenda.getSince());
        System.out.println(tenda.getDescription() + "\n");

        for(i = 0; i < tenda.getCatalogue().size(); i++){
            System.out.println("\t" + (i +1) + ") \"" + tenda.getCatalogue().get(i).getName() + "\" by \"" + tenda.getCatalogue().get(i).getBrand() + "\"");
            //System.out.println("\t\tPrice: " + tenda.getCatalogue().get(i).getPreuTenda() + "\n");
        }
        System.out.println("\t" + (i +1) + ") Back\n");

        option = getIntBetween(1, i+1, "Which one are you interested in? ");
        return option -1;
    }

    /**
     * Menu que pregunta a l'usuari si es vol fer una review, visualitarles o afegir el producte al carrito
     * @return retorna la opcio elegida com a integer.
     */
    public int requestOperation(){
        System.out.println("\n\t1) Read Reviews");
        System.out.println("\t2) Review Product");
        System.out.println("\t3) Add to Cart\n");

        return getIntBetween(1, 3, "Choose an option: ");
    }

    /**
     * Mostra a l'usuari les diferentes opcions que hi han al accedir al menu de cart i retorna le opcio seleccionada
     * @param carret es passa el carret per mostrar tots els productes que te en ell
     * @return retorna la opcio elegida en valor de integer
     */
    public int selectCarretFunction(Carret carret, ArrayList<Float> clientTotal){
        float totalPrice = 0;
        int option = 0;

        System.out.println("\nYour cart contains the following items:\n");
        for(int i = 0; i < carret.getProductesCarrito().size(); i++){
            System.out.println("\t- \"" + carret.getProductesCarrito().get(i).getName() + "\" by \"" + carret.getProductesCarrito().get(i).getBrand() + "\"");
            System.out.println("\t\tPrice: " + clientTotal.get(i) + "\"\n");
        }

        System.out.println("Total: " + clientTotal.getLast() + "\n");
        System.out.println( "\t1) Checkout\n" +
                "\t2) Clear cart\n\n" +
                "\t3) Back\n");

        return getIntBetween(1, 3, "Choose an option: ");
    }

    /**
     * Demana un enter a l'usuari fins que el que retorna està entre els valors adequats
     * @param min és el valor mínim que ha de tenir l'input de l'usuari per ser correcte
     * @param max és el valor màxim que ha de tenir l'input de l'usuari per ser correcte
     * @param text és el text que es mostra a l'usuari per a cada intent
     * @return retorna l'opció elegida per l'usuari
     */
    private int getIntBetween(int min, int max, String text) {
        int option = -1;
        while(option < min || option > max){
            System.out.print(text);

            try {
                option = scanner.nextInt();
                if (option < min || option > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number between " + min + " and " + max + ".\n");
            }
            scanner.nextLine();
        }

        return option;
    }

    /**
     * Pregunta a l'usuari si esta segur de fer la accio que es passa per el valor de text
     * @param text accio que el usuari vol fer
     * @return retorna en forma de boolea si vol que es faci la funcio o no
     */
    public boolean makeSure(String text){
        String option;

        System.out.print("\nAre you sure you want to " + text + "? ");
        do{

            option = scanner.nextLine().toLowerCase();

            if(Objects.equals(option, "YES") || Objects.equals(option, "Yes") || Objects.equals(option, "yes") || Objects.equals(option, "Y") || Objects.equals(option, "y")){
                return true;
            }else if(Objects.equals(option, "NO") || Objects.equals(option, "No") || Objects.equals(option, "no") || Objects.equals(option, "N") || Objects.equals(option, "n")) {
                return false;
            }
            System.out.print("Please enter 'yes' or 'no:");
        }while (true);

    }

    /**
     * Finalitza la compra de l'usuari mostrant la tenda de on s'ha comprat i el total de profit que ha tingut
     * @param carrito carrito on estan els productes comprats per l'usuari
     * @param tendes arraylist de tendes on estan totes les tendes per mostrar les ganaces que han tingut
     * @param preus arraylist de preus dels productes del carret ordenats per la posicio dels productes del carret
     */
    public void checkoutCompra(ArrayList<Producte> carrito, ArrayList<Tenda> tendes, ArrayList<Float> preus){

        int beneficioIndex = carrito.size() + 1;
        ArrayList<String> tiendasCompradas = new ArrayList<>();
        for (Producte carret: carrito) {
            if(!tiendasCompradas.contains(carret.getTenda())){
                tiendasCompradas.add(carret.getTenda());
            }
        }
        for (int i = 0; i < tiendasCompradas.size(); i++) {
            for (int t = 0; t < tendes.size(); t++) {
                if (tendes.get(t).getName().equals(tiendasCompradas.get(i))) {

                    float totalTenda = preus.get(beneficioIndex + i);
                    System.out.println("\"" + tendes.get(t).getName() + "\" has earned " + totalTenda + " for an historic total of " + tendes.get(t).getEarnings());
                }
            }
        }
    }

    /**
     * Mostra un missatge indicant que el carret de la compra s'ha buidat amb èxit.
     */
    public void clearCartDone(){
        System.out.println("Your cart has been cleared.\n");
    }
    /**
     * Mostra un missatge d'error indicant que l'API no està disponible i verifica els fitxers locals.
     */
    public void showIniciMenuErrorLoadApi(boolean isProduct) {
        if(isProduct){
            System.out.println("Error: The API isn’t available.\n Verifying local Products files...");
        }
        else{
            System.out.println("Error: The API isn’t available.\n Verifying local Shops   files...");
        }
    }
    /**
     * Mostra un missatge indicant que s'està verificant l'estat de l'API.
     */
    public void showIniciMenuApi() {
        System.out.println("Checking API status...\n");
    }
}
