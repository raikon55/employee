package org.raikon.employee.enums;

public enum Role {

    INTERN("INTERN") {
        @Override
        public Float bonus() {
            return 1.0F;
        }
    },
    JUNIOR("JUNIOR") {
        @Override
        public Float bonus() {
            return 1.2F;
        }
    },
    PLAN("PLAN") {
        @Override
        public Float bonus() {
            return 1.5F;
        }
    },
    SENIOR("SENIOR") {
        @Override
        public Float bonus() {
            return 2.0F;
        }
    };

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public abstract Float bonus();
}