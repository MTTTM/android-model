package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.shop.model.Food;
import com.example.shop.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public EditText getNameEidiText() {
        return mNameEidiText;
    }

    public RadioGroup getSexRadioGroup() {
        return mSexRadioGroup;
    }

    public CheckBox getHotCheckBox() {
        return mHotCheckBox;
    }

    public CheckBox getFishCheck() {
        return mFishCheck;
    }

    public CheckBox getSourCheck() {
        return mSourCheck;
    }

    public SeekBar getSeekBar() {
        return mSeekBar;
    }

    public Button getSearchBtn() {
        return mSearchBtn;
    }

    public ToggleButton getToggleButton() {
        return mToggleButton;
    }

    public TextView getFootName() {
        return mFootName;
    }

    private EditText mNameEidiText;
    private RadioGroup mSexRadioGroup;
    private CheckBox mHotCheckBox;
    private CheckBox mFishCheck;
    private CheckBox mSourCheck;
    private SeekBar mSeekBar;
    private Button mSearchBtn;
    private ToggleButton mToggleButton;
    private TextView mFootName;
    private  TextView mPrizeText;
    private ArrayList<Food> mFoods;
    private Person mPerson;
    private  boolean mIsHot;
    private  boolean mIsFirsh;
    private  boolean misSour;
    private  int mPrice;
    private List<Food> mFoodResult;
    private  int mCurrIndex=10000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        findViews();
        //初始化数据
        initData();
        //为控件添加监听器,实现基本功能
        setListenters();
        //自测
    }
    private  void findViews(){
        mNameEidiText=findViewById(R.id.name);
        mSexRadioGroup=findViewById(R.id.sexGroup);
        mHotCheckBox=findViewById(R.id.hotCheckbox);
        mFishCheck=findViewById(R.id.fishCheckBox);
        mSourCheck=findViewById(R.id.sourCheckBox);
        mSeekBar=findViewById(R.id.seekBar);

        mSearchBtn=findViewById(R.id.searBtn);
        mToggleButton=findViewById(R.id.showTroggle);
        mToggleButton.setChecked(true);
        mFootName=findViewById(R.id.footName);
        mPrizeText=findViewById(R.id.prize);
    }
    private void  initData(){
        mFoods=new ArrayList<Food>();
        mFoods.add(new Food("麻辣香锅",55,true,false,false));
        mFoods.add(new Food("水煮鱼",48,true,true,false));
        mFoods.add(new Food("麻辣火锅",80,true,false,false));
        mFoods.add(new Food("清蒸鲈鱼",68,false,false,true));
        mFoods.add(new Food("桂林米粉",15,false,false,true));
        mFoods.add(new Food("上汤娃娃菜",28,false,false,true));
        mFoods.add(new Food("红烧肉哦",60,false,false,true));
        mFoods.add(new Food("木须肉",40,false,false,true));
        mFoods.add(new Food("酸菜牛肉面",35,false,false,true));
        mFoods.add(new Food("西芹炒百合",38,true,false,false));
        mFoods.add(new Food("酸辣汤",40,true,false,true));

       mPerson=new Person();
        mPrice=100;
        mSeekBar.setProgress(100);
        mPrizeText.setText(""+mPrice);
    }
    private  void  setListenters(){
        mSexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.male:
                        mPerson.setSex("男");
                        break;
                    case R.id.fmale:
                        mPerson.setSex("女");
                        break;
                }
            }
        });
        mHotCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mIsHot=b;
            }
        });
        mFishCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mIsFirsh=b;
            }
        });
        mSourCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                misSour=b;
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPrice=seekBar.getProgress();
                mPrizeText.setText(""+mPrice);
                Toast.makeText(MainActivity.this,"价格:"+mPrice,Toast.LENGTH_SHORT).show();
                Log.d("onStopTrackingTouch", "onStopTrackingTouch: "+mPrice);
            }
        });
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mToggleButton.isChecked()){
                    mCurrIndex++;
                    if(mCurrIndex<mFoodResult.size()){
                        mFootName.setText(mFoodResult.get(mCurrIndex).getName());
                    }
                    else{
                        Toast.makeText(MainActivity.this,"没有了",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    //查找菜单
    private  void  search(){
        //遍历所有菜单
        //如果符合条件，就加入我们的结果列表
        if(mFoodResult==null){
            mFoodResult=new ArrayList<Food>();
        }

        //先清除职务的结果
        mFoodResult.clear();
        for (int index=0;index<mFoods.size();index++){
            Food food=mFoods.get(index);
//            else{
//                mFootName.setText("");
//                Toast.makeText(MainActivity.this,"请先选择价格，和喜好",Toast.LENGTH_SHORT).show();
//            }
            if(food!=null){
                Log.d("food", food.getPrice()+"==="+mPrice+"ishOT"+food.isHot()+"&&**"+mIsHot);
                if(food.getPrice()<mPrice
                        &&(
                        (food.isHot()==true&&food.isHot()==mIsHot)||
                                (food.isSour()&&food.isSour()==misSour)||
                                (food.isFish()&&food.isFish()==mIsFirsh)
                        )){
                    mFoodResult.add(food);
                    mCurrIndex=0;
                }

            }


        }
        if (mFoodResult.size()==0){
            mFootName.setText("木有~~");
        }
        if(mCurrIndex<mFoodResult.size()){
            mFootName.setText(mFoodResult.get(mCurrIndex).getName());
        }
    }


}
