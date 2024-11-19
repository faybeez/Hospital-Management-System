package com.hms.enums;

import java.util.EnumSet;

public enum Gender {
    FEMALE {
        @Override
        public String toString() {
            return "Female";
        }
    },
    MALE {
        @Override
        public String toString() {
            return "Male";
        }
    };

    public static Gender getByValue(String value) {
        for(final Gender element : EnumSet.allOf(Gender.class)) {
            if(element.toString().equalsIgnoreCase(value)) {
                return element;
            }
        }
        return null;
    }
};

