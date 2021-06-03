package com.Libary.libary.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

    @Getter
public enum Role implements GrantedAuthority {
        /**
         * The instances for Roles
         */
        USER,LIBRARIAN;

        @Override
        public String getAuthority() {
            return name();
        }
    }


