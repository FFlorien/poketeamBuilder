package be.florien.poketeam.model;

public enum LanguageEnum {
    
    JA1(1, "ja"),
    JA2(2, "ja"),
    KO(3, "ko"),
    ZH(4, "zh"),
    FR(5, "fr"),
    DE(6, "de"),
    ES(7, "es"),
    IT(8, "it"),
    EN(9, "en"),
    CS(10, "cs"),
    NONE(11,"none");
    
    private int mId;
    private String mIso639;

    private LanguageEnum(int id, String iso639){
        this.mId = id;
        this.mIso639 = iso639;
    }
    
    public int getid(){
        return mId;
    }
    
    public String getIso(){
        return mIso639;
    }
    
    public static LanguageEnum getLanguageForIso(String iso){
        
        for(LanguageEnum value : LanguageEnum.values()){
            if(value.getIso().equals(iso)){
                return value;
            }
        }
        return NONE;
    }

}
