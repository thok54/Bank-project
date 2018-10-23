package ejercicio.banco.dto;

public enum BankName {
    BESTBANK, GOODBANK, SHADYBANK, NOTABANK, BANKASIONES;

    public static  BankName fromValue(String type){
        for(BankName bankName: BankName.values()){
            if(bankName.name().equalsIgnoreCase(type)){
                return bankName;
            }
        }
        return null;
    }
}
