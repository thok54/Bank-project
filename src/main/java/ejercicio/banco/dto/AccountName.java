package ejercicio.banco.dto;

public enum AccountName {
    Luis, Pedro, Sancho, Sonia, Josefina, Nacho, Huski;

    public static  AccountName fromValue(String type){
        for(AccountName accountName: AccountName.values()){
            if(accountName.name().equalsIgnoreCase(type)){
                return accountName;
            }
        }
        return null;
    }
}
