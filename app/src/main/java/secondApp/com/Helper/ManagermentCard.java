package secondApp.com.Helper;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;

import secondApp.com.Domain.FoodDomain;
import secondApp.com.Interface.ChangeNumberItemsListener;
import secondApp.com.R;

public class ManagermentCard {
    private Context context;
    private TinyDB tinyDB;

    public ManagermentCard(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood (FoodDomain item) {
        ArrayList<FoodDomain> listFood = getListCard();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if(listFood.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }
        if(existAlready) {
            listFood.get(n).setNumberInCard(item.getNumberInCard());
        }else {
            listFood.add(item);
        }
        tinyDB.putListObject("CardList", listFood);
        Toast myToast =  Toast.makeText(context, "Added To Card", Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        myToast.show();

//        Toast toast = new Toast(context);
//        View toast_view = LayoutInflater.from(context).inflate(R.layout.toast_text, null);
//        toast.setView(toast_view);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.show();

    }

    public ArrayList<FoodDomain> getListCard() {
        return tinyDB.getListObject("CardList");
    }

    public void plusNumberFood(ArrayList<FoodDomain> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
            listFood.get(position).setNumberInCard(listFood.get(position).getNumberInCard() + 1);
            changeNumberItemsListener.changed();
    }

    public void minusNumberFood(ArrayList<FoodDomain> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
       if(listFood.get(position).getNumberInCard() == 1) {
           listFood.remove(position);
       }else {
        listFood.get(position).setNumberInCard(listFood.get(position).getNumberInCard() - 1);
       }
       tinyDB.putListObject("CardList", listFood);
        changeNumberItemsListener.changed();
    }
    public Double getTotalFee() {
        ArrayList<FoodDomain> listFood = getListCard();
        double fee = 0;
        for(int i = 0; i<listFood.size(); i++) {
            fee = fee + (listFood.get(i).getFee() + listFood.get(i).getNumberInCard());
        }
        return  fee;
    }


}
