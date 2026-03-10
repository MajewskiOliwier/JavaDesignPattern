package allnew.okk.account.Adapter;

//Interfejs dla adaptera pozwalającego na pozyskanie odpowiednich nazw i identyfikatorów w Adapterze Platnosci
public interface AccountDisplayable {
    String GetDisplayName();
    String GetIdentifier();
    String GetAccountType();
}
