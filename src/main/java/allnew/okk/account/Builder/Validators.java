package allnew.okk.account.Builder;//Week 7, Otwarte - zamkniete - 1

import allnew.okk.account.Builder.AccountBuilder;
import allnew.okk.account.Builder.AccountValidator;

public class Validators {
    public static final AccountValidator EMAIL_REQUIRED =
            b -> {
                if (b.getEmail() == null || b.getEmail().isBlank())
                    throw new IllegalStateException("Email jest wymagany");
            };

    public static final AccountValidator PASSWORD_MIN_LENGTH =
            b -> {
                if (b.getPassword() == null || b.getPassword().length() < 8)
                    throw new IllegalStateException("Hasło min. 8 znaków");
            };

    public static final AccountValidator PHONE_REQUIRED =
            b -> {
                if (b.getPhone() == null || b.getPhone().isBlank())
                    throw new IllegalStateException("Telefon jest wymagany");
            };
}
//End Week 7, Otwarte - zamkniete - 1