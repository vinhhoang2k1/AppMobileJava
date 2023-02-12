package secondApp.com.Domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private String title, pic, description;
    private Double fee;
    private int numberInCard;

    public FoodDomain( String title, String pic,String description, Double fee, int numberInCard) {
        this.pic = pic;
        this.title = title;
        this.description = description;
        this.fee = fee;
        this.numberInCard = numberInCard;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCard() {
        return numberInCard;
    }

    public void setNumberInCard(int numberInCard) {
        this.numberInCard = numberInCard;
    }
}
