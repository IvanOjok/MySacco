package com.jambo.mysacco.models.entities;

public enum TransactionStatus {
    PENDING,      //user invoked
    PROCESSING,       //payment gateway handling
    SUCCESS,
    FAILED,
    REVERSED,
    CANCELLED,

}
