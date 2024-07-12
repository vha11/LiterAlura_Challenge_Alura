package challenge.dos.alura.appLibreriaDigital.model;

public enum Idioma {
    en("[en]", "Ingles"),
    es("[es]", "Español"),
    fr("[fr]", "Frances"),
    pt("[pt]", "Portugues");

    private String idiomaGutendex;
    private String idiomaEspanol;

    Idioma(String idiomaGutendex, String idiomaEspanol){
        this.idiomaGutendex = idiomaGutendex;
        this.idiomaEspanol = idiomaEspanol;

    }

    public static Idioma fromString(String answer){
        for (Idioma idioma : Idioma.values()){
            if (idioma.idiomaGutendex.equalsIgnoreCase(answer)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("El idioma "+answer+"no fue  encontrado.");
    }

    public static Idioma fromEspanol(String answer){
        for (Idioma idioma : Idioma.values()){
            if (idioma.idiomaEspanol.equalsIgnoreCase(answer)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("El idioma "+answer+"no fue  encontrado.");
    }

    public String getIdiomaGutendex() {
        return idiomaGutendex;
    }

    public String getIdiomaEspanol() {
        return idiomaEspanol;
    }

}