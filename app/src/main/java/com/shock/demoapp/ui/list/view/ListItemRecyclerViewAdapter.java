package com.shock.demoapp.ui.list.view;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.shock.demoapp.R;
import com.shock.demoapp.ui.list.module.DataItem;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListItemRecyclerViewAdapter extends RecyclerView.Adapter<ListItemRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "ListItemViewAdapter";
    private final List<DataItem> dataItems;
    private final OnListFragmentInteractionListener listener;
    private final Context context;
    private int lastPosition = -1;

    public ListItemRecyclerViewAdapter(Context context, List<DataItem> dataItems, OnListFragmentInteractionListener listener) {
        this.dataItems = dataItems;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (this.lastPosition < position) {
            Animation slide_up = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            holder.mView.startAnimation(slide_up);
            this.lastPosition = position;
        }

        DataItem dataItem = dataItems.get(position);

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(dataItem.getPictures().get(0).getUrl()))
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(holder.imgProd.getController())
                .build();
        holder.imgProd.setController(controller);
        //holder.imgProd.setImageURI(Uri.parse(dataItem.getPictures().get(0).getUrl()));
        Log.d(TAG, "onBindViewHolder: " + dataItem.getUrl());

        holder.tvProdName.setText(dataItem.getName());

        String locality = null;
        if (dataItem.getProductLocality() != null && !dataItem.getProductLocality().isEmpty())
            locality = dataItem.getProductLocality() + ", ";
        if (dataItem.getLocation() != null && !dataItem.getLocation().isEmpty())
            locality += dataItem.getLocation();
        holder.tvProdLocality.setText(locality);

        holder.tvProdScore.setText(dataItem.getScore());

        if (dataItem.getOfferPercentage() > 0) {
            holder.tvProdOfferPercentage.setVisibility(View.VISIBLE);
            holder.tvProdOfferPercentageOff.setVisibility(View.VISIBLE);
            holder.tvProdOfferPercentage.setText(String.valueOf(dataItem.getOfferPercentage()));
            holder.imgProdOfferPercentage.setVisibility(View.VISIBLE);
        } else {
            holder.tvProdOfferPercentageOff.setVisibility(View.GONE);
            holder.tvProdOfferPercentage.setVisibility(View.GONE);
            holder.imgProdOfferPercentage.setVisibility(View.GONE);
        }

        if (dataItem.getPerDayRent() > 0) {
            holder.tvProdPerDayRentAmt.setVisibility(View.VISIBLE);
            holder.tvProdPerDayRent.setVisibility(View.VISIBLE);
            holder.tvProdPerDayRentAmt.setText(String.format(Locale.ENGLISH, "%s %s", context.getString(R.string.rupee_symbol),
                    String.valueOf(dataItem.getPerDayRent())));
            holder.tvProdPerDayRent.setText(context.getString(R.string.per_day));
            if (dataItem.getOfferPercentage() > 0) {
                holder.tvProdPerDayRentOfferAmt.setVisibility(View.VISIBLE);
                holder.tvProdPerDayRentOfferAmt.setText(String.format(Locale.ENGLISH, "%s %s", context.getString(R.string.rupee_symbol),
                        String.valueOf(calculateOfferAmt(dataItem.getPerDayRent(), dataItem.getOfferPercentage()))));
                holder.tvProdPerDayRentAmt.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.tvProdPerDayRentAmt.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
                holder.tvProdPerDayRentOfferAmt.setVisibility(View.GONE);
            }
        } else {
            holder.tvProdPerDayRentAmt.setVisibility(View.GONE);
            holder.tvProdPerDayRentOfferAmt.setVisibility(View.GONE);
            holder.tvProdPerDayRent.setVisibility(View.GONE);
        }

        if (dataItem.getPerWeekRent() > 0) {
            holder.tvProdPerWeekRentAmt.setVisibility(View.VISIBLE);
            holder.tvProdPerWeekRent.setVisibility(View.VISIBLE);
            holder.tvProdPerWeekRentAmt.setText(String.format(Locale.ENGLISH, "%s %s", context.getString(R.string.rupee_symbol),
                    String.valueOf(dataItem.getPerWeekRent())));
            holder.tvProdPerWeekRent.setText(context.getString(R.string.per_week));
            if (dataItem.getOfferPercentage() > 0) {
                holder.tvProdPerWeekRentOfferAmt.setVisibility(View.VISIBLE);
                holder.tvProdPerWeekRentOfferAmt.setText(String.format(Locale.ENGLISH, "%s %s", context.getString(R.string.rupee_symbol),
                        String.valueOf(calculateOfferAmt(dataItem.getPerWeekRent(), dataItem.getOfferPercentage()))));
                holder.tvProdPerWeekRentAmt.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.tvProdPerWeekRentAmt.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
                holder.tvProdPerWeekRentOfferAmt.setVisibility(View.GONE);
            }
        } else {
            holder.tvProdPerWeekRentAmt.setVisibility(View.GONE);
            holder.tvProdPerWeekRent.setVisibility(View.GONE);
            holder.tvProdPerWeekRentOfferAmt.setVisibility(View.GONE);
        }

        if (dataItem.getPerMonthRent() > 0) {
            holder.tvProdPerMonthRentAmt.setVisibility(View.VISIBLE);
            holder.tvProdPerMonthRent.setVisibility(View.VISIBLE);
            holder.tvProdPerMonthRentAmt.setText(String.format(Locale.ENGLISH, "%s %s", context.getString(R.string.rupee_symbol),
                    String.valueOf(dataItem.getPerMonthRent())));
            holder.tvProdPerMonthRent.setText(context.getString(R.string.per_month));
            if (dataItem.getOfferPercentage() > 0) {
                holder.tvProdPerMonthRentOfferAmt.setVisibility(View.VISIBLE);
                holder.tvProdPerMonthRentOfferAmt.setText(String.format(Locale.ENGLISH, "%s %s", context.getString(R.string.rupee_symbol),
                        String.valueOf(calculateOfferAmt(dataItem.getPerMonthRent(), dataItem.getOfferPercentage()))));
                holder.tvProdPerMonthRentAmt.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.tvProdPerMonthRentAmt.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
                holder.tvProdPerMonthRentOfferAmt.setVisibility(View.GONE);
            }
        } else {
            holder.tvProdPerMonthRentAmt.setVisibility(View.GONE);
            holder.tvProdPerMonthRent.setVisibility(View.GONE);
            holder.tvProdPerMonthRentOfferAmt.setVisibility(View.GONE);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onListFragmentInteraction(dataItems.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public int calculateOfferAmt(int perDayRent, int offerPercentage) {
        return (perDayRent - ((perDayRent) * offerPercentage) / 100);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        @BindView(R.id.img_prod)
        SimpleDraweeView imgProd;

        @BindView(R.id.tv_prod_name)
        AppCompatTextView tvProdName;

        @BindView(R.id.tv_prod_locality)
        AppCompatTextView tvProdLocality;

        @BindView(R.id.tv_prod_score)
        AppCompatTextView tvProdScore;

        @BindView(R.id.img_prod_score_star)
        AppCompatImageView imgProdScoreStar;

        @BindView(R.id.tv_prod_offer_percentage)
        AppCompatTextView tvProdOfferPercentage;

        @BindView(R.id.tv_prod_offer_percetage_off)
        AppCompatTextView tvProdOfferPercentageOff;

        @BindView(R.id.img_prod_offer_percentage)
        AppCompatImageView imgProdOfferPercentage;

        @BindView(R.id.tv_prod_per_day_rent_amt)
        AppCompatTextView tvProdPerDayRentAmt;

        @BindView(R.id.tv_prod_per_day_rent_offer_amt)
        AppCompatTextView tvProdPerDayRentOfferAmt;

        @BindView(R.id.tv_prod_per_day_rent)
        AppCompatTextView tvProdPerDayRent;

        @BindView(R.id.tv_prod_per_week_rent_amt)
        AppCompatTextView tvProdPerWeekRentAmt;

        @BindView(R.id.tv_prod_per_week_rent_offer_amt)
        AppCompatTextView tvProdPerWeekRentOfferAmt;

        @BindView(R.id.tv_prod_per_week_rent)
        AppCompatTextView tvProdPerWeekRent;

        @BindView(R.id.tv_prod_per_month_rent_amt)
        AppCompatTextView tvProdPerMonthRentAmt;

        @BindView(R.id.tv_prod_per_month_rent_offer_amt)
        AppCompatTextView tvProdPerMonthRentOfferAmt;

        @BindView(R.id.tv_prod_per_month_rent)
        AppCompatTextView tvProdPerMonthRent;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }
    }
}
