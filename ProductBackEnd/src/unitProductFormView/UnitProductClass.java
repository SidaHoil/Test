package unitProductFormView;

public class UnitProductClass {

    private int id;
    private String name;
    private String color;
    private String type;
    private byte[] image;
    private int  importId;
    
    public UnitProductClass(){}
    
    public UnitProductClass(int id, String name, String color, String type,byte[] image, int importId){
    
        this.id = id;
        this.name = name;
        this.color = color;
        this.type = type;
        this.image = image;
        this.importId = importId;
       
    }
    
    
    public int getID(){
      return id;
    }
    
    public void setID(int ID){
        this.id = ID;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String Name){
        this.name = Name;
    }
    
    public String getColor(){
        return color;
    }
    
    public void setColor(String color){
        this.color = color;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public int getImportId(){
        return importId;
    }
    
    public void setImportId(int id){
        this.importId = id;
    }
    
    public byte[] getImage(){
        return image;
    }
}
