package com.devsu.hackerearth.backend.account.constants;

public final class AccountConstants {

    private AccountConstants() {
    }

    // Response Menssages
    public static final String NOT_FOUND = "Not Found";
    public static final String ALREADY_EXIST = "Already exists";
    public static final String INVALID_INPUT_DATA = "Invalid input data";
    public static final String UNPROCESABLE_ENTITY = "Unprocesable entity";
    public static final String BAD_REQUEST = "Bad request";

    // Messages exception
    public static final String ACCOUNT_ALREADY_EXIST = "Account already exist with account number %d";
    public static final String ACCOUNT_NOT_EXIST = "Account does not exist with number %d";
    public static final String PARTIAL_ACCOUNT_NULL = "Status cannot be null";

    public static final String TRANSACTION_ALREADY_EXIST = "Transaction already exist with account number %d";
    public static final String TRANSACTION_NOT_EXIST = "Transaction does not exist with number %d";
    public static final String TRANSACTION_NOT_EXECUTABLE = "Saldo no disponible";
    public static final String USER_ACCOUNT_NOT_EXIST = "User account not exist";

}
