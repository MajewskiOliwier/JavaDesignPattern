package allnew.okk.payment.Service;

public class PayUService {
    public String makeTransaction(float amount, String currency, String nameAndSurname, String email){
        System.out.println("PayU: Przetwarzano : "+currency+" "+amount);
        System.out.println("PayU: Klient: "+nameAndSurname+" Identyfikator:"+email);
        return "PAYU_"+System.currentTimeMillis();
    }

    public boolean returnMoney(String transactionID, float amount){
        System.out.println("PayU: Returning : "+amount+" for transaction: "+transactionID);
        return true;
    }

    public String checkStatus(String transactionID){
        System.out.println("PayU: Check status for "+transactionID);
        return "COMPLETED";
    }
}
