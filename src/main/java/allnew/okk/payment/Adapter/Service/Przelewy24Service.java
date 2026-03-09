package allnew.okk.payment.Adapter.Service;

import allnew.okk.account.Adapter.AccountDisplayable;

public class Przelewy24Service {
    public String makeTransaction(float amount, String currency, AccountDisplayable client){
        System.out.println("Przelewy24: Przetwarzano : "+currency+" "+amount+" dla klienta o emailu"+client.GetDisplayName());
        return "PAYU_"+System.currentTimeMillis();
    }

    public boolean returnMoney(String transactionID, float amount){
        System.out.println("Przelewy24: Zwrocono: "+amount+" za transakcje: "+transactionID);
        return true;
    }

    public String checkStatus(String transactionID){
        System.out.println("Przelewy24: sprawdzanie statusu dla transakcji: "+transactionID);
        return "COMPLETED";
    }
}
