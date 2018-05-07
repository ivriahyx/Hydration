package com.example.a16023018.hydration;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgressFragment extends Fragment {
  public static ProgressFragment newInstance(){
      ProgressFragment fragment = new ProgressFragment();
      return fragment;
  }
private int mProgress = 0;

DataSource dataSource;
  TextView tvCurrentProgress, tvTotal,tvCurrentcupNum;
  ImageView ivMug;
  ImageButton ibUndo;
  ImageButton ibAdd;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //((MainActivity)getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ibUndo = (ImageButton)getView().findViewById(R.id.ibUndo);
        ibAdd = (ImageButton)getView().findViewById(R.id.ibAdd);
        tvTotal = (TextView)getView().findViewById(R.id.textViewTotal);
        ivMug = (ImageView)getView().findViewById(R.id.imageView);
        tvCurrentcupNum = (TextView)getView().findViewById(R.id.tvCurrentcupNum);

        dataSource = new DataSource(getActivity());
        tvTotal.setText("You have drank " + mProgress +" cup of water for today");


        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mProgress!=8) {
                    mProgress = mProgress + 1;

                    }else{
                    mProgress = mProgress;
                }
                for (int i=0;i<dataSource.getAllData().size();i++){
                    dataSource.updateData(mProgress,i);
                }

                tvTotal.setText("You have drank " + mProgress +" cup of water for today");
                if(mProgress==0){
                    ivMug.setImageResource(R.drawable.mug0);
                }else if(mProgress==1){
                    ivMug.setImageResource(R.drawable.mug1);
                }else if(mProgress==2){
                    ivMug.setImageResource(R.drawable.mug2);
                }else if(mProgress==3){
                    ivMug.setImageResource(R.drawable.mug3);
                }else if(mProgress==4){
                    ivMug.setImageResource(R.drawable.mug4);
                }else if(mProgress==5){
                    ivMug.setImageResource(R.drawable.mug5);
                }else if(mProgress==6){
                    ivMug.setImageResource(R.drawable.mug6);
                }else if(mProgress==7){
                    ivMug.setImageResource(R.drawable.mug7);
                }else {
                    ivMug.setImageResource(R.drawable.mug8);
                }

                SharedPreferences preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = preferences.edit();
                prefEdit.putInt("cupNumCurrent",mProgress);
                prefEdit.commit();
            }
        });

       ibUndo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(mProgress>0) {
                   mProgress = mProgress - 1;
                   dataSource.updateData(mProgress,0);
                   //dataSource.addData(mProgress,""+Calendar.DATE);
                   tvTotal.setText("You have drank " + mProgress +" cup of water for today");
               }else{

               }
               if(mProgress==0){
                   ivMug.setImageResource(R.drawable.mug0);
               }else if(mProgress==1){
                   ivMug.setImageResource(R.drawable.mug1);
               }else if(mProgress==2){
                   ivMug.setImageResource(R.drawable.mug2);
               }else if(mProgress==3){
                   ivMug.setImageResource(R.drawable.mug3);
               }else if(mProgress==4){
                   ivMug.setImageResource(R.drawable.mug4);
               }else if(mProgress==5){
                   ivMug.setImageResource(R.drawable.mug5);
               }else if(mProgress==6){
                   ivMug.setImageResource(R.drawable.mug6);
               }else if(mProgress==7){
                   ivMug.setImageResource(R.drawable.mug7);
               }else {
                   ivMug.setImageResource(R.drawable.mug8);
               }
              /*
               SharedPreferences preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
               SharedPreferences.Editor prefEdit = preferences.edit();
               prefEdit.putInt("cupNumCurrent",mProgress);
               prefEdit.commit();
               */
           }
       });





        //dataSource.addData(mProgress,"Today");
        //tvCurrentcupNum.setText(dataSource.getAllData().get(0).cupnumber+"");



    }

//SharedPref
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = preferences.edit();
        prefEdit.putInt("cupNumCurrent",mProgress);
        prefEdit.commit();

    }
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        int intCurrentProgress = preferences.getInt("cupNumCurrent", 0);
        //tvTotal.setText("You have drank " + intCurrentProgress + " cup of water for today");
        //tvCurrentcupNum.setText(intCurrentProgress);
    }

}
