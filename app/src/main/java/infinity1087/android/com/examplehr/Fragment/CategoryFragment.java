package infinity1087.android.com.examplehr.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

import infinity1087.android.com.examplehr.MainActivity;
import infinity1087.android.com.examplehr.Network.NetworkClass;
import infinity1087.android.com.examplehr.R;
import infinity1087.android.com.examplehr.RoundedTransformation;
import infinity1087.android.com.examplehr.appExecuter.AppExecutors;
import infinity1087.android.com.examplehr.model.MainButtonImage;
import retrofit2.http.Url;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    ImageView img_btn_veggie, img_btn_milk, img_button_fruits, img_btn_waffers,img_offer;
    TextView txt_veggie,txt_fruits,txt_milk,txt_waffer;
    String milk_url, fruits_url, veggie_url, waffers_url,txtvegurl,txtfruiturl ,txtmilkurl ,txtwafferurl;


    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        txt_veggie = view.findViewById(R.id.txt_VegGroup);
        txt_fruits = view.findViewById(R.id.txt_fruitGroup);
        txt_milk = view.findViewById(R.id.txt_milkGroup);
        txt_waffer = view.findViewById(R.id.txt_wafferGroup);
        img_btn_veggie = view.findViewById(R.id.img_veggie);
        img_btn_milk = view.findViewById(R.id.img_milk);
        img_btn_waffers = view.findViewById(R.id.img_waffers);
        img_button_fruits = view.findViewById(R.id.img_fruits);
        img_offer = view.findViewById(R.id.img_offer);

        getData();
       Picasso.get().load("http://image.barodaweb.net/api/EGreen/Magic/270/ProductGroup-1/46bc8c078310497c981203c9ab0c5b95.jpg/100").error(R.drawable.veg).transform(new RoundedTransformation(20, 0)).into(img_button_fruits);
//        Picasso.get().load("http://image.barodaweb.net/api/EGreen/Magic/1170/ProductGroup-1/"+fruits_url+"/100").into(img_button_fruits);







        Picasso.get().load("http://image.barodaweb.net/api/EGreen/Magic/270/ProductGroup-2/3bd998d9f04c4d909953b42c461b9291.jpg/100").error(R.drawable.fruits).transform(new RoundedTransformation(20, 0)).into(img_btn_veggie);
        Picasso.get().load("http://image.barodaweb.net/api/EGreen/Magic/270/ProductGroup-3/337c510d37de424aae977caf29b669a9.jpg/100").error(R.drawable.waffer).transform(new RoundedTransformation(20, 0)).into(img_btn_waffers);
        Picasso.get().load("http://image.barodaweb.net/api/EGreen/Magic/270/ProductGroup-4/7ae6459c529c4fb5842415ff0e1b45b8.jpg/100").error(R.drawable.milk).transform(new RoundedTransformation(20, 0)).into(img_btn_milk);

        // just add the complete url i am only fetching the image name eg gjhdf9dflhfgldf.jpg complete the url
        // for eg "http://image.barodaweb.net/api/EGreen/Magic/200/ProductGroup-Vegetables/gjhdf9dflhfgldf.jpg"
        //Picasso.get().load("cffab3047c1149a89a43fa3ce2bc42d5.jpg" + fruits_url)   like this
//        Picasso.get().load("http://image.barodaweb.net/api/EGreen/Magic/500/"+txtvegurl +veggie_url+"/100")
//                .error(R.drawable.ic_launcher_background).placeholder(R.drawable.img1)
//                .transform(new RoundedTransformation(20, 0)).fit()
//                .into(img_btn_veggie);
//
//        Log.d("veg",veggie_url+"");
        Picasso.get().load("http://image.barodaweb.net/api/EGreen/Magic/1170/ProductGroup-1/cffab3047c1149a89a43fa3ce2bc42d5.jpg/10").transform(new RoundedTransformation(20,0)).into(img_offer);
        return view;

    }

    private void getData() {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                URL url = NetworkClass.buildURl(NetworkClass.MAIN_BUTTON_URL);
                List<MainButtonImage> result = NetworkClass.fetchMainButtonData(url);

                // getting results

                MainButtonImage textveg = result.get(0);
                txtvegurl = textveg.getGetProductname();

                MainButtonImage image = result.get(0);
                fruits_url = image.getGroupImage();
                // eg image.getGroupIcon or image.getGroupBannerImage or image.getGroupIcon
                MainButtonImage image2 = result.get(1);
                veggie_url = image2.getGroupImage();

                MainButtonImage image3 = result.get(2);
                waffers_url = image3.getGroupImage();

                MainButtonImage image4 = result.get(3);
                milk_url = image4.getGroupImage();

                Log.d("ti", fruits_url + "   " + veggie_url +  "  "+ milk_url + "  " + waffers_url+"" + txtvegurl+"");


            }
        });


    }

}
