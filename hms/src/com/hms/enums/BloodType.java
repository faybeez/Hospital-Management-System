package com.hms.enums;

import java.util.EnumSet;

public enum BloodType {
    OPLUS {
        @Override
        public String toString() {
            return "O+";
        }
    },
    OMINUS {
        @Override
        public String toString() {
            return "O-";
        }
    },
    APLUS {
        @Override
        public String toString() {
            return "A+";
        }
    },
    AMINUS {
        @Override
        public String toString() {
            return "A-";
        }
    },
    BPLUS {
        @Override
        public String toString() {
            return "B+";
        }
    },
    BMINUS {
        @Override
        public String toString() {
            return "B-";
        }
    },
    ABPLUS {
        @Override
        public String toString() {
            return "AB+";
        }
    },
    ABMINUS {
        @Override
        public String toString() {
            return "AB-";
        }
    };

    public static BloodType getByValue(String value) {
        for(final BloodType element : EnumSet.allOf(BloodType.class)) {
            if(element.toString().equals(value)) {
                return element;
            }
        }
        return null;
    }
};
