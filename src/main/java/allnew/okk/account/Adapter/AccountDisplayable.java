package allnew.okk.account.Adapter;

//Interfejs dla adaptera pozwalającego na pozyskanie odpowiednich nazw i identyfikatorów w Adapterze Platnosci
// Week 8, Segragated Interface 3 Oliwier Majewski
// Segregated interface nr.3 from Bloated AccountManager
public interface AccountDisplayable {
    String GetDisplayName();
    String GetIdentifier();
    boolean hasValidPaymentMethod();
}
// End Week 8, Segragated Interface 3 Oliwier Majewski
