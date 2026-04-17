package allnew.okk.account.Builder;

import allnew.okk.account.Builder.AccountBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Week 7, Otwarte - zamkniete - 1
@FunctionalInterface
public interface AccountValidator {
    void validate(AccountBuilder<?> builder); // rzuca RuntimeException jeśli błąd

    static final Map<String, AccountValidator> REGISTRY = new HashMap<>(Map.of(
        "email_required",       b -> {
            if (b.getEmail() == null || b.getEmail().isBlank())
                throw new IllegalStateException("Email jest wymagany");
        },
        "password_min_length",  b -> {
            if (b.getPassword() == null || b.getPassword().length() < 8)
                throw new IllegalStateException("Hasło min. 8 znaków");
        }
    ));

    // Pobierz zestaw walidatorów po kluczach - sterowanie danymi
    static List<AccountValidator> of(String... keys) {
        return Arrays.stream(keys)
            .map(key -> {
                AccountValidator v = REGISTRY.get(key);
                if (v == null)
                    throw new IllegalArgumentException("Nieznany walidator: " + key);
                return v;
            })
            .toList();
    }

    // Rozszerzenie przez dane: nowy walidator bez modyfikacji kodu
    static void register(String key, AccountValidator validator) {
        REGISTRY.put(key, validator);
    }
    // End Week 6, Otwarte - zamkniete - 2
}
//End Week 7, Otwarte - zamkniete - 1
