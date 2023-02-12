package secondApp.com.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import secondApp.com.Adaptor.CategoryAdaptor;
import secondApp.com.Adaptor.PopularAdaptor;
import secondApp.com.Domain.CategoryDomain;
import secondApp.com.Domain.FoodDomain;
import secondApp.com.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewPopolarList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        callback for render ui data
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout cartNaviBtn = findViewById(R.id.cartNaviBtn);
        LinearLayout homeNaviBtn = findViewById(R.id.homeBtn);
        cartNaviBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
        homeNaviBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        ArrayList<CategoryDomain> category = new  ArrayList<>();
        category.add(new CategoryDomain("Pizza", "cat_1"));
        category.add(new CategoryDomain("Berger", "cat_2"));
        category.add(new CategoryDomain("Hot dog", "cat_3"));
        category.add(new CategoryDomain("Drink", "cat_4"));
        category.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopolarList = findViewById(R.id.recyclerView2);
        recyclerViewPopolarList.setLayoutManager(linearLayoutManager);
        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("pizza", "pizza", "this is description", 9.70, 10 ));
        foodList.add(new FoodDomain("Hamberger", "pizza", "this is description", 12.70, 10 ));
        foodList.add(new FoodDomain("hotdog", "pizza", "this is description", 4.70, 10 ));
        foodList.add(new FoodDomain("latte", "pizza", "this is description", 16.70, 10 ));

        adapter = new PopularAdaptor(foodList);
        recyclerViewPopolarList.setAdapter(adapter);
    }

}