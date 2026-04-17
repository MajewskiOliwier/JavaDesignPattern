//Week 6, Otwarte - zamkniete - 1 

public static final AccountValidator EMAIL_REQUIRED =
        b -> { if (b.email == null || b.email.isBlank())
                   throw new IllegalStateException("Email jest wymagany"); };

    public static final AccountValidator PASSWORD_MIN_LENGTH =
        b -> { if (b.password == null || b.password.length() < 8)
                   throw new IllegalStateException("Hasło min. 8 znaków"); };

    public static final AccountValidator PHONE_REQUIRED =
        b -> { if (b.phone == null || b.phone.isBlank())
                   throw new IllegalStateException("Telefon jest wymagany"); };
}
//End Week 6, Otwarte - zamkniete - 1