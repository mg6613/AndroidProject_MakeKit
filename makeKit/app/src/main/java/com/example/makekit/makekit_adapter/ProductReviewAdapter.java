package com.example.makekit.makekit_adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.makekit.R;
import com.example.makekit.makekit_bean.Review;

import java.util.ArrayList;

// review 리스트
public class ProductReviewAdapter extends RecyclerView.Adapter<ProductReviewAdapter.MyViewHolder>{

    final static String TAG = "ProductAdapter";

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Review> data = null;
    private LayoutInflater inflater = null;
    private String urlImage;
    private String urlImageReal;

    public ProductReviewAdapter(Context mContext, int layout, ArrayList<Review> data, String urlImage) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.urlImage = urlImage;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.custom_productview_review, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        Log.v(TAG, data.get(position).getReviewImage());
        Log.v(TAG, urlImage);

        if(data.get(position).getReviewImage().equals("null")){
            holder.li_reviewImage.setVisibility(View.INVISIBLE);
            holder.li_reviewNonImage.setVisibility(View.VISIBLE);

            holder.tv_reviewContentNon.setText(data.get(position).getReviewContent());
            holder.tv_reviewWriterNon.setText(data.get(position).getReviewWriterName());
            holder.tv_reviewWriteDateNon.setText(data.get(position).getReviewDate());
            holder.tv_reviewStarNon.setText(data.get(position).getReviewStar() + " 점");

        }else {
            holder.li_reviewImage.setVisibility(View.VISIBLE);
            holder.li_reviewNonImage.setVisibility(View.INVISIBLE);
//
            Log.v(TAG, urlImage + "image/" + data.get(position).getReviewImage());
            urlImageReal = urlImage+ "image/" + data.get(position).getReviewImage();
           // holder.img_reviewImage.loadUrl(urlImageReal);

            // Initial webview
            holder.img_reviewImage.setWebViewClient(new WebViewClient());



            // Enable JavaScript
            holder.img_reviewImage.getSettings().setJavaScriptEnabled(true);
            holder.img_reviewImage.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            // Enable Zoom
            holder.img_reviewImage.getSettings().setBuiltInZoomControls(true);
            holder.img_reviewImage.getSettings().setSupportZoom(true);
            holder.img_reviewImage.getSettings().setSupportZoom(true); //zoom mode 사용.
            holder.img_reviewImage.getSettings().setDisplayZoomControls(false); //줌 컨트롤러를 안보이게 셋팅.


            // Adjust web display
            holder.img_reviewImage.setBackgroundColor(0);
            holder.img_reviewImage.getSettings().setLoadWithOverviewMode(true);
            holder.img_reviewImage.getSettings().setUseWideViewPort(true);
            holder.img_reviewImage.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
            holder.img_reviewImage.setInitialScale(15);

            // url은 알아서 설정 예) http://m.naver.com/
            holder.img_reviewImage.loadUrl(urlImageReal); // 접속 URL

            holder.tv_reviewContent.setText(data.get(position).getReviewContent());
            holder.tv_reviewWriter.setText(data.get(position).getReviewWriterName());
            holder.tv_reviewWriteDate.setText(data.get(position).getReviewDate());
            holder.tv_reviewStar.setText(data.get(position).getReviewStar() + " 점");


        }
    }

    @Override
    public int getItemCount() {
//        Log.v("AddressAdapter", "data.size = " + String.valueOf(data.size()));
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        WebView img_reviewImage;
        TextView tv_reviewWriter, tv_reviewStar, tv_reviewContent, tv_reviewWriteDate;
        TextView tv_reviewWriterNon, tv_reviewStarNon, tv_reviewContentNon, tv_reviewWriteDateNon;
        LinearLayout li_reviewImage, li_reviewNonImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            li_reviewImage = itemView.findViewById(R.id.linearImage_productviewreview);
            li_reviewNonImage = itemView.findViewById(R.id.linearNonImage_productviewreview);

            img_reviewImage =  itemView.findViewById(R.id.imgReview_productcview);
            tv_reviewWriter =  itemView.findViewById(R.id.writerReview_productview);
            tv_reviewStar =  itemView.findViewById(R.id.StarReview_productview);
            tv_reviewContent =  itemView.findViewById(R.id.contentReview_productview);
            tv_reviewWriteDate =  itemView.findViewById(R.id.writerDateReview_productview);
            tv_reviewWriterNon =  itemView.findViewById(R.id.writerReviewNon_productview);
            tv_reviewStarNon =  itemView.findViewById(R.id.StarReviewNon_productview);
            tv_reviewContentNon =  itemView.findViewById(R.id.contentReviewNon_productview);
            tv_reviewWriteDateNon =  itemView.findViewById(R.id.writerDateReviewNon_productview);
        }
    }

//    @Override
//    public long getItemId(int position) {
//        return Integer.parseInt(data.get(position).getOrderDetailNo());
//    }

}
