package com.shock.demoapp.ui.list.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.shock.demoapp.R;
import com.shock.demoapp.ui.AppBaseFragment;
import com.shock.demoapp.ui.list.module.DataItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shahid on 17/8/16.
 */
public class ListActivityDetailFragment extends AppBaseFragment implements View.OnClickListener {

    private static final String TAG = ListActivityDetailFragment.class.getSimpleName();
    private GridCellAdapter adapter;
    private Calendar _calendar;
    private int month, year;
    private static final String dateTemplate = "MMMM yyyy";
    String flag = "abc";
    String date_month_year;

    @BindView(R.id.prevMonth)
    ImageView prevMonth;

    @BindView(R.id.currentMonth)
    Button currentMonth;

    @BindView(R.id.nextMonth)
    ImageView nextMonth;

    @BindView(R.id.calendar)
    GridView calendarView;

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

    @BindView(R.id.img_prod_offer_percentage)
    AppCompatImageView imgProdOfferPercentage;

    @BindView(R.id.tv_prod_offer_percetage_off)
    AppCompatTextView tvProdOfferPercentageOff;

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

    @BindView(R.id.tv_prod_desc_label)
    AppCompatTextView tvProdDescLabel;

    @BindView(R.id.tv_prod_desc)
    AppCompatTextView tvProdDesc;

    @BindView(R.id.tv_prod_tnc_label)
    AppCompatTextView tvProdTncLabel;

    @BindView(R.id.cv_prod_tnc)
    CardView cvProdTnc;

    @BindView(R.id.ll_prod_tnc)
    LinearLayoutCompat llProdTnc;

    DataItem dataItem;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataItem = (DataItem) getArguments().getSerializable(getResources().getString(R.string.serialize_data));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        setView();
        return view;
    }

    private void setView() {
        setCalendarView();
        setImage(imgProd);
        setDetails();
        setTNC();
    }

    private void setCalendarView() {
        _calendar = Calendar.getInstance(Locale.getDefault());
        month = _calendar.get(Calendar.MONTH) + 1;
        year = _calendar.get(Calendar.YEAR);

        prevMonth.setOnClickListener(this);

        currentMonth.setText(DateFormat.format(dateTemplate, _calendar.getTime()));

        nextMonth.setOnClickListener(this);

        adapter = new GridCellAdapter(getContext(), R.id.calendar_day_gridcell, month, year, dataItem.getBlockedDates());
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
    }

    private void setImage(SimpleDraweeView simpleDraweeView) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(dataItem.getPictures().get(0).getUrl()))
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
    }

    private void setDetails() {
        tvProdName.setText(dataItem.getName());

        String locality = null;
        if (dataItem.getProductLocality() != null && !dataItem.getProductLocality().isEmpty())
            locality = dataItem.getProductLocality() + ", ";
        if (dataItem.getLocation() != null && !dataItem.getLocation().isEmpty())
            locality += dataItem.getLocation();
        tvProdLocality.setText(locality);

        tvProdScore.setText(dataItem.getScore());

        if (dataItem.getOfferPercentage() > 0) {
            tvProdOfferPercentage.setVisibility(View.VISIBLE);
            tvProdOfferPercentageOff.setVisibility(View.VISIBLE);
            tvProdOfferPercentage.setText(String.valueOf(dataItem.getOfferPercentage()));
            imgProdOfferPercentage.setVisibility(View.VISIBLE);
        } else {
            tvProdOfferPercentageOff.setVisibility(View.GONE);
            tvProdOfferPercentage.setVisibility(View.GONE);
            imgProdOfferPercentage.setVisibility(View.GONE);
        }

        if (dataItem.getPerDayRent() > 0) {
            tvProdPerDayRentAmt.setVisibility(View.VISIBLE);
            tvProdPerDayRent.setVisibility(View.VISIBLE);
            tvProdPerDayRentAmt.setText(String.format(Locale.ENGLISH, "%s %s", getResources().getString(R.string.rupee_symbol),
                    String.valueOf(dataItem.getPerDayRent())));
            tvProdPerDayRent.setText(getResources().getString(R.string.per_day));
            if (dataItem.getOfferPercentage() > 0) {
                tvProdPerDayRentOfferAmt.setVisibility(View.VISIBLE);
                tvProdPerDayRentOfferAmt.setText(String.format(Locale.ENGLISH, "%s %s", getResources().getString(R.string.rupee_symbol),
                        String.valueOf(calculateOfferAmt(dataItem.getPerDayRent(), dataItem.getOfferPercentage()))));
                tvProdPerDayRentAmt.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                tvProdPerDayRentAmt.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
                tvProdPerDayRentOfferAmt.setVisibility(View.GONE);
            }
        } else {
            tvProdPerDayRentAmt.setVisibility(View.GONE);
            tvProdPerDayRentOfferAmt.setVisibility(View.GONE);
            tvProdPerDayRent.setVisibility(View.GONE);
        }

        if (dataItem.getPerWeekRent() > 0) {
            tvProdPerWeekRentAmt.setVisibility(View.VISIBLE);
            tvProdPerWeekRent.setVisibility(View.VISIBLE);
            tvProdPerWeekRentAmt.setText(String.format(Locale.ENGLISH, "%s %s", getResources().getString(R.string.rupee_symbol),
                    String.valueOf(dataItem.getPerWeekRent())));
            tvProdPerWeekRent.setText(getResources().getString(R.string.per_week));
            if (dataItem.getOfferPercentage() > 0) {
                tvProdPerWeekRentOfferAmt.setVisibility(View.VISIBLE);
                tvProdPerWeekRentOfferAmt.setText(String.format(Locale.ENGLISH, "%s %s", getResources().getString(R.string.rupee_symbol),
                        String.valueOf(calculateOfferAmt(dataItem.getPerWeekRent(), dataItem.getOfferPercentage()))));
                tvProdPerWeekRentAmt.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                tvProdPerWeekRentAmt.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
                tvProdPerWeekRentOfferAmt.setVisibility(View.GONE);
            }
        } else {
            tvProdPerWeekRentAmt.setVisibility(View.GONE);
            tvProdPerWeekRent.setVisibility(View.GONE);
            tvProdPerWeekRentOfferAmt.setVisibility(View.GONE);
        }

        if (dataItem.getPerMonthRent() > 0) {
            tvProdPerMonthRentAmt.setVisibility(View.VISIBLE);
            tvProdPerMonthRent.setVisibility(View.VISIBLE);
            tvProdPerMonthRentAmt.setText(String.format(Locale.ENGLISH, "%s %s", getResources().getString(R.string.rupee_symbol),
                    String.valueOf(dataItem.getPerMonthRent())));
            tvProdPerMonthRent.setText(getResources().getString(R.string.per_month));
            if (dataItem.getOfferPercentage() > 0) {
                tvProdPerMonthRentOfferAmt.setVisibility(View.VISIBLE);
                tvProdPerMonthRentOfferAmt.setText(String.format(Locale.ENGLISH, "%s %s", getResources().getString(R.string.rupee_symbol),
                        String.valueOf(calculateOfferAmt(dataItem.getPerMonthRent(), dataItem.getOfferPercentage()))));
                tvProdPerMonthRentAmt.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                tvProdPerMonthRentAmt.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
                tvProdPerMonthRentOfferAmt.setVisibility(View.GONE);
            }
        } else {
            tvProdPerMonthRentAmt.setVisibility(View.GONE);
            tvProdPerMonthRent.setVisibility(View.GONE);
            tvProdPerMonthRentOfferAmt.setVisibility(View.GONE);
        }

        tvProdDesc.setText(dataItem.getDescription());

    }

    public int calculateOfferAmt(int perDayRent, int offerPercentage) {
        return (perDayRent - ((perDayRent) * offerPercentage) / 100);
    }

    private void setTNC() {

        String[] tnc = dataItem.getTnc().split(getResources().getString(R.string.tnc_split_regex));
        for (String s : tnc) {
            LinearLayoutCompat compat = (LinearLayoutCompat) LayoutInflater.from(getActivity()).inflate(R.layout.tnc_view, null);
            TextView textView = ButterKnife.findById(compat, R.id.tv_text);
            textView.setText(s);
            llProdTnc.addView(compat);
        }
        cvProdTnc.setVisibility(View.VISIBLE);
    }

    private void setGridCellAdapterToDate(int month, int year) {
        adapter = new GridCellAdapter(getContext(), R.id.calendar_day_gridcell, month, year, dataItem.getBlockedDates());
        _calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
        currentMonth.setText(DateFormat.format(dateTemplate, _calendar.getTime()));
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v == prevMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            Log.d(TAG, " " + Calendar.getInstance().getTimeInMillis() + "<=" + calendar.getTimeInMillis());
            if (Calendar.getInstance().getTimeInMillis() < calendar.getTimeInMillis()) {
                if (month <= 1) {
                    month = 12;
                    year--;
                } else
                    month--;
                setGridCellAdapterToDate(month, year);
            }
        }
        if (v == nextMonth) {
            if (month > 11) {
                month = 1;
                year++;
            } else
                month++;
            setGridCellAdapterToDate(month, year);
        }
    }
}
