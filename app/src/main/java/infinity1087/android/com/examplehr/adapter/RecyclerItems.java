package infinity1087.android.com.examplehr.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Person;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import infinity1087.android.com.examplehr.ProductDetalModel.PPZ;
import infinity1087.android.com.examplehr.ProductDetalModel.PriceDetails;
import infinity1087.android.com.examplehr.R;
import infinity1087.android.com.examplehr.RoundedTransformation;
import infinity1087.android.com.examplehr.ProductDetalModel.ResponseDetail;

public class RecyclerItems extends RecyclerView.Adapter<RecyclerItems.MyViewHolder> {

// http://portfolio.barodaweb.com/Dynamic/EGreenMarketAPI

    List<ResponseDetail> mData;
    List<PriceDetails> mPricel;
    List<String> mList=new ArrayList<>();

    public RecyclerItems(List<ResponseDetail> data, List<PriceDetails> price) {

        mData = data;
        mPricel = price;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                .item_list_grossary_new, viewGroup, false);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        List<String> people = new ArrayList<>();
        people.clear();

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        ResponseDetail datum = mData.get(i);
        PriceDetails details = datum.getPriceDetail().get(0);

        int size = datum.getPriceDetail().size();
        for (i = 0; i < size; i++) {

            PriceDetails details2 = datum.getPriceDetail().get(i);
            mList.add(String.valueOf(details.getPP().getWeight()));
        }
        Log.d("oal", String.valueOf(Arrays.asList(mList)));
        myViewHolder.niceSpinner.setText(mList.get(0));

        myViewHolder.niceSpinner.attachDataSource(mList);
        //mList.clear();





        //PriceDetails pd = mPricel.get(i);
        myViewHolder.txt_name.setText(datum.getP().getProductName());
        myViewHolder.txt_price.setText(String.valueOf(details.getPP().getBasicCost()));
        myViewHolder.txt_offer.setText(String.valueOf(details.getPP().getCheckeredCost()));


//       double datatype converter
//      myViewHolder.txt_price.setText(pd.getPP().getSellCost());
//      myViewHolder.d(pd.getPP().getSellCost());

        Picasso.get()
                .load("http://image.barodaweb.net/api/EGreen/Magic/270/Product-" + datum.getP().getProductId() + "/" + datum.getP().getProductImage() + "/100")
                .transform(new RoundedTransformation(20, 0))
                .into(myViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name, txt_price, txt_offer;
        ImageView imageView;
        NiceSpinner niceSpinner;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_item_name);
            imageView = itemView.findViewById(R.id.img_detail);
            txt_price = itemView.findViewById(R.id.txt_detail_amount);
            txt_offer = itemView.findViewById(R.id.txt_offer);
            niceSpinner = itemView.findViewById(R.id.spinner);

            // Double d = Double.valueOf(txt_offer.toString());

        }

        public void d(Double sellCost) {

        }
    }
}
