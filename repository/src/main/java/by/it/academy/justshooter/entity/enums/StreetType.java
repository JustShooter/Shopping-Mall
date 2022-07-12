package by.it.academy.justshooter.entity.enums;

public enum StreetType {
    ULICA("Улица", "ул."),
    PEREULOK("Переулок", "пер."),
    PROSPEKT("Проспект", "пр-т"),
    BULVAR("Бульвар", "б-р"),
    PROEZD("Проезд", "пр-д"),
    TRAKT("Тракт", "тракт");

    private String fullName;
    private String shortName;

    StreetType(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }
}
