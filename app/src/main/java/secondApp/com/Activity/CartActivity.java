package secondApp.com.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import secondApp.com.Adaptor.CartListAdaptor;
import secondApp.com.Helper.ManagermentCard;
import secondApp.com.Interface.ChangeNumberItemsListener;
import secondApp.com.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagermentCard managermentCard;
    private TextView totalTxt, totalFeeTxt, deliveryTxt, taxTxt, emptyTxt;
    private ScrollView scrollView;
    double tax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managermentCard = new ManagermentCard(this);
        initView();
        initList();
        caculateCart();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout cartNaviBtn = findViewById(R.id.cartNaviBtn);
        LinearLayout homeNaviBtn = findViewById(R.id.homeBtn);
        cartNaviBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, CartActivity.class));
            }
        });
        homeNaviBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.listItem);
        totalTxt = findViewById(R.id.totalTxt);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        taxTxt = findViewById(R.id.taxTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollViewCart);
    }
    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdaptor(managermentCard.getListCard(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                caculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if(managermentCard.getListCard().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else  {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void caculateCart() {
        double percenTax = 0.02;
        double delivery = 10;

        tax = Math.round((managermentCard.getTotalFee() * percenTax) *100) /100;
        double total = Math.round((managermentCard.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managermentCard.getTotalFee() * 100) /100;

        totalFeeTxt.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);
    }

}