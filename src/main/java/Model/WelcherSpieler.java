package Model;

//Programmierer: Tom

public enum WelcherSpieler {
    NUTZER("Du"),
    LINKER("Der linke Spieler"),
    OBERER("Der obere Spieler"),
    RECHTER("Der rechte Spieler");

    private final String name;

    WelcherSpieler(String name) {
        this.name = name;
    }

    public String gebeName() {
        return name;
    }
}
