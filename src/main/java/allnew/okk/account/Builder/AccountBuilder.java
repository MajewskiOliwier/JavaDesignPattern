package allnew.okk.account.Builder;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

// Week 2, Pattern Builder 3
//Abstrakcyjna klasa builder
public abstract class AccountBuilder <T extends  AccountBuilder<T>>{
    protected String email;
    protected String password;
    protected String adress;
    protected String phone;

    //metoda abstrakcyjna potrzebna do zwrócenia konkretnego typu buildera (CompanyAccountBuilder lub PrivateAccountBuilder)
    protected abstract T Self();

    // Week 6, Otwarte - zamkniete - 1
    //metoda ustawiająca Email dla konkretnego buildera, zwraca generyczne T w celu umożliwienia wywołania metod unikalnych dla typu T
    public final T Email(String email){
        this.email = email;
        return Self();
    }

    //metoda ustawiająca Hasło dla konkretnego buildera, zwraca generyczne T w celu umożliwienia wywołania metod unikalnych dla typu T
    public final T Password(String password){
        this.password = password;
        return Self();
    }

    //metoda ustawiająca Adres dla konkretnego buildera, zwraca generyczne T w celu umożliwienia wywołania metod unikalnych dla typu T
    public final T Adress(String adress){
        this.adress = adress;
        return Self();
    }

    //metoda ustawiająca Telefon dla konkretnego buildera, zwraca generyczne T w celu umożliwienia wywołania metod unikalnych dla typu T
    public final T Phone(String phone){
        this.phone = phone;
        return Self();
    }

    //Metoda typu setter dla emaila
    public final void SetEmail(String newEmail){
        this.email = newEmail;
    }

    //Metoda typu setter dla hasla
    public final void SetPassword(String newPassword){
        this.password = newPassword;
    }

    //Metoda typu setter dla adrres
    public final void SetAdress(String newAdress){
        this.adress = newAdress;
    }

    //Metoda typu setter dla phone
    public final void SetPhone(String newPhone){
        this.phone = newPhone;
    }

    // End Week 6, Otwarte - zamkniete - 1
    private final List<AccountValidator> validators;

    // Week 6, Otwarte - zamkniete - 2 (sterowanie danymi: mapa pól steruje zachowaniem)
    // Klasa jest zamknięta na modyfikacje - nowe pole = nowy wpis w mapie, nie nowa metoda
    private static final Map<String, BiConsumer<AccountBuilder<?>, String>> FIELD_SETTERS =
        Map.of(
            "email",    (b, v) -> b.email   = v,
            "password", (b, v) -> b.password = v,
            "adress",   (b, v) -> b.adress  = v,
            "phone",    (b, v) -> b.phone   = v
        );

    protected AccountBuilder(List<AccountValidator> validators) {
        this.validators = List.copyOf(validators);
    }

    // Week 6, Otwarte - zamkniete - 2 (sterowanie danymi)

    // Zamiast modyfikować klasę by obsłużyć nowe pole, przekazujesz dane z zewnątrz
    @SuppressWarnings("unchecked")
    public final T applyFields(Map<String, String> fields) {
        fields.forEach((key, value) -> {
            BiConsumer<AccountBuilder<?>, String> setter = FIELD_SETTERS.get(key);
            if (setter == null)
                throw new IllegalArgumentException("Nieznane pole: " + key);
            setter.accept(this, value);
        });
        return Self();
    }
    // End Week 6, Otwarte - zamkniete - 2


    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
}
// End Week 2, Pattern Builder 3