package ejercicio.banco.dto;

public enum DataType {
    BANK, ACCOUNT, PAYMENT;

    public static DataType fromValue(String type) {
        for (DataType dataType : DataType.values()) {
            if (dataType.name().equalsIgnoreCase(type)) {
                return dataType;
            }
        }
        return null;
    }
}
