package allnew.okk.Currency.Flyweight;

// Week 4, Pattern Flyweight 1 Oliwier Majewski
// Enum storing supported currencies with hardcoded exchange rates relative to PLN
public enum CurrencyType {
    PLN(1.0f, "zł"),
    EUR(4.25f, "€"),
    USD(3.95f, "$");

    private final float rateFromPLN; // how many PLN = 1 unit of this currency
    private final String symbol;

    CurrencyType(float rateFromPLN, String symbol) {
        this.rateFromPLN = rateFromPLN;
        this.symbol = symbol;
    }

    public float getRateFromPLN() { return rateFromPLN; }
    public String getSymbol() { return symbol; }

    public static CurrencyType fromString(String code) {
        for (CurrencyType c : values()) {
            if (c.name().equalsIgnoreCase(code)) return c;
        }
        throw new IllegalArgumentException("Unsupported currency code: " + code);
    }
}
// End Week 4, Pattern Flyweight 1 Oliwier Majewski