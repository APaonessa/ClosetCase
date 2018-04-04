package edu.pitt.cs.cs1635.amp224.closetcase;

public class Clothes{
     int id;
     String name;
     String color;
     String type;
     String pattern;
     String material;

     public Clothes(){
     }

     public Clothes(int id, String name, String color, String type, String pattern, String material){
         this.id = id;
         this.name = name;
         this.color = color;
         this.type = type;
         this.pattern = pattern;
         this.material = material;
     }

     public int getId(){
         return this.id;
     }

     public void setId(int id){
         this.id = id;
     }

     public String getName(){
         return this.name;
     }

     public void setName(String name){
         this.name = name;
     }

     public String getColor(){
         return this.color;
     }

     public void setColor(String color){
         this.color = color;
     }

     public String getType(){
         return this.type;
     }

     public void setType(String type){
         this.type = type;
     }

     public String getPattern(){
         return  this.pattern;
     }

     public void setPattern(String pattern){
         this.pattern = pattern;
     }

     public String getMaterial(){
         return this.material;
     }
     public void setMaterial(String material){
         this.material = material;
     }

}