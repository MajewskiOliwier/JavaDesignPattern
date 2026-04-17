package allnew.okk.account.Builder;

import allnew.okk.account.Prototype.CompanyAccount;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

// Week 2, Pattern Builder 1
//klasa implementujaca klase abstrakcji dla której parametr generyczny T jest CompanyAccountBuilder (ta klasa)
public class CompanyAccountBuilder extends AccountBuilder<CompanyAccountBuilder>{
    private String legalName;
    private String vatNumber;


    //Week 7, Otwarte - zamkniete - 1
    public CompanyAccountBuilder() {
        super(List.of(
            Validators.EMAIL_REQUIRED,
            Validators.PASSWORD_MIN_LENGTH
        ));
    }
    //End Week 7, Otwarte - zamkniete - 1

    // Week 7, Otwarte - zamkniete - 2 (sterowanie danymi: mapa pól własnych)
    private static final Map<String, BiConsumer<CompanyAccountBuilder, String>> COMPANY_FIELDS =
        Map.of(
            "legalName", (b, v) -> b.legalName = v,
            "vatNumber", (b, v) -> b.vatNumber = v
        );

    public CompanyAccountBuilder applyCompanyFields(Map<String, String> fields) {
        fields.forEach((key, value) -> {
            BiConsumer<CompanyAccountBuilder, String> setter = COMPANY_FIELDS.get(key);
            if (setter == null)
                throw new IllegalArgumentException("Nieznane pole: " + key);
            setter.accept(this, value);
        });
        return this;
    }
    // End Week 6, Otwarte - zamkniete - 2

    //metoda zwracająca tą klase, jest wykorzystywana w metodach bazowych klasy AccountBuilder by zwracały one klase CompanyAccountBuilder
    @Override
    protected CompanyAccountBuilder Self() {
        return this;
    }

    //metoda typu setter zwracajaca tą klase
    public CompanyAccountBuilder SetLegalName(String legalName){
        this.legalName = legalName;
        return this;
    }

    //metoda typu setter zwracajaca tą klase
    public CompanyAccountBuilder SetVatNumber(String vatNumber){
        this.vatNumber = vatNumber;
        return this;
    }

    //metoda finalizujaca budowanie przy uzyciu wzorca builder, zwraca utworzone nowe konto firmowe
    public CompanyAccount Build(){
       return new CompanyAccount(email, password, legalName, vatNumber, adress, phone);
    }
}
// End Week 2, Pattern Builder 3