package com.nexorape.safework.service.iam.domain.model.valueobjects.user;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;

/**
 * EmailAddress Value Object
 */
@Embeddable
public record EmailAddress(@Email String address) {
    /**
     * Default constructor
     */
    public EmailAddress() {
        this(null);
    }
}