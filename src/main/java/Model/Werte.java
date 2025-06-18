package Model;

public enum Werte {
    SAU((11<<16)|0),
    ZEHNER((10<<16)|1),
    KOENIG((4<<16)|2),
    OBER((3<<16)|3),
    UNTER((2<<16)|4),
    NEUNER(0|5),
    ACHTER(0|6),
    SIEBENER(0|7);

    private final int wert;

    Werte(int i) {
        this.wert = i;
    }

    public int gebeIndex() {
        return wert&0xFFFF;
    }

    public int gebePunktzahl(){
        return wert>>16;
    }
}

// 寫些評論吧，你這個傻瓜