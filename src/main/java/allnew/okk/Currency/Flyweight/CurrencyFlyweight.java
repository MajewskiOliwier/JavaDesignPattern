package allnew.okk.Currency.Flyweight;

// Week 4, Pattern Flyweight 2 Oliwier Majewski
// The shared flyweight object — one instance per currency ever exists
public class CurrencyFlyweight {
    private final CurrencyType currencyType;

    public CurrencyFlyweight(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public float convert(float amountInPLN) {
        return amountInPLN / currencyType.getRateFromPLN();
    }

    public String getSymbol() { return currencyType.getSymbol(); }
    public CurrencyType getCurrencyType() { return currencyType; }
}
// End Week 4, Pattern Flyweight 2 Oliwier Majewski