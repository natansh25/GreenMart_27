package infinity1087.android.com.examplehr.ProductDetalModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;



public class ResponseDetail implements Serializable {

        @SerializedName("P")
        @Expose
        private Pz p;
        @SerializedName("PG")
        @Expose
        private PGZ pG;
        @SerializedName("B")
        @Expose
        private B b;
        @SerializedName("PriceDetail")
        @Expose
        private List<PriceDetails> priceDetail = null;

        public Pz getP() {
            return p;
        }

        public void setP(Pz p) {
            this.p = p;
        }

        public PGZ getPG() {
            return pG;
        }

        public void setPG(PGZ pG) {
            this.pG = pG;
        }

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }

        public List<PriceDetails> getPriceDetail() {
            return priceDetail;
        }

        public void setPriceDetail(List<PriceDetails> priceDetail) {
            this.priceDetail = priceDetail;
        }

    }
