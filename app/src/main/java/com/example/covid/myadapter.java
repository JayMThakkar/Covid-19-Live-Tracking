package com.example.covid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class myadapter extends ArrayAdapter<countryModel> {

    private Context context;
    private List<countryModel> countryModelList;

    private List<countryModel> countryModelListfilter;
    public myadapter(Context context, List<countryModel> countryModelList) {
        super(context, R.layout.list_custom__item,countryModelList);
        this.context = context;
        this.countryModelList = countryModelList;
        this.countryModelListfilter = countryModelList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom__item,null,true);
        TextView name = view.findViewById(R.id.countryname);
        ImageView img = view.findViewById(R.id.imgFlag);

        name.setText(countryModelListfilter.get(position).getCountry());
        Glide.with(context).load(countryModelListfilter.get(position).getFlag()).into(img);

        return view;
    }

    @Override
    public int getCount() {
        return countryModelListfilter.size();
    }

    @Override
    public countryModel getItem(int position) {
        return countryModelListfilter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countryModelList.size();
                    filterResults.values = countryModelList;

                }else{
                    List<countryModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(countryModel itemsModel:countryModelList){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countryModelListfilter = (List<countryModel>) results.values;
                countries.countryModelsList = (List<countryModel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
