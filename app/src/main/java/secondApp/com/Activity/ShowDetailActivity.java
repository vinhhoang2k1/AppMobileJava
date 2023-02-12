package secondApp.com.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import secondApp.com.Domain.FoodDomain;
import secondApp.com.Helper.ManagermentCard;
import secondApp.com.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView titleDetail, feeDetail, descriptionDetail, countOrderTxt;
    private ImageView picDetail, minusBtn, plusBtn;
    private TextView addToCardBtn;
    private FoodDomain foodobject;
    int countOrder = 1;

    private ManagermentCard managermentCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managermentCard = new ManagermentCard(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        foodobject = (FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourseid = this.getResources().getIdentifier(foodobject.getPic(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourseid)
                .into(picDetail);
        titleDetail.setText(foodobject.getTitle());
        feeDetail.setText(String.valueOf(foodobject.getFee()));
        descriptionDetail.setText(foodobject.getDescription());
        countOrderTxt.setText(String.valueOf(countOrder));

//        handle action
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countOrder++;
                countOrderTxt.setText(String.valueOf(countOrder));
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countOrder > 1) {
                    countOrder--;
                    countOrderTxt.setText(String.valueOf(countOrder));
                }
            }
        });

        addToCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodobject.setNumberInCard(countOrder);
                managermentCard.insertFood(foodobject);
            }
        });
    }


    private void initView()  {
        addToCardBtn = findViewById(R.id.addToCardBtn);
        picDetail = findViewById(R.id.picDetail);
        minusBtn = findViewById(R.id.minusBtn);
        plusBtn = findViewById(R.id.plusBtn);
        countOrderTxt = findViewById(R.id.countOrder);
        descriptionDetail = findViewById(R.id.descriptionDetail);
        feeDetail = findViewById(R.id.feeDetail);
        titleDetail = findViewById(R.id.titleDetail);
    }
}