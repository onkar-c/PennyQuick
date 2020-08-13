package com.penny.quick.ui.activities.providersList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import androidx.cardview.widget.CardView;
import com.penny.database.ProjectConstants;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.accept_details.AcceptRechargeDetails;

public class ProvidersListActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_providers_list);
    setUpToolBar();
    setTitle(getString(R.string.select_provider));
    GridLayout dthGrid = (GridLayout) findViewById(R.id.dthGrid);
    GridLayout landLineGrid = (GridLayout) findViewById(R.id.landLineGrid);
    boolean isDth = getIntent().getBooleanExtra(ProjectConstants.IS_DTH, true);
    if (isDth) {
      setGridListener(dthGrid);
    } else {
      setGridListener(landLineGrid);
    }
    dthGrid.setVisibility(isDth ? View.VISIBLE : View.GONE);
    landLineGrid.setVisibility(isDth ? View.GONE : View.VISIBLE);
  }

  private void setGridListener(GridLayout grid) {
    for (int i = 0; i < grid.getChildCount(); i++) {
      ((CardView) grid.getChildAt(i)).setOnClickListener(this::handelRechargeGridClick);
    }
  }

  private void handelRechargeGridClick(View view) {
    Intent intent = new Intent(ProvidersListActivity.this, AcceptRechargeDetails.class);
    intent.putExtra(ProjectConstants.ACCEPT_ACCOUNT_ID, false);
    switch (view.getId()) {
      case R.id.dth_airtel:
        intent.putExtra(ProjectConstants.PROVIDER, getString(R.string.airtel_dth));
        intent.putExtra(ProjectConstants.PROVIDER_IMAGE, R.drawable.airtel_medium);
        intent.putExtra(ProjectConstants.CUSTOMER_ID_HINT, getString(R.string.customer_id));
        intent.putExtra(ProjectConstants.CUSTOMER_ID_MESSAGE,
            getString(R.string.recharge_detail_message_1));
        intent.putExtra(ProjectConstants.AMOUNT_MESSAGE,
            getString(R.string.recharge_detail_message_2));
        break;

      case R.id.dth_dish_tv:
        intent.putExtra(ProjectConstants.PROVIDER, getString(R.string.dish_tv));
        intent.putExtra(ProjectConstants.PROVIDER_IMAGE, R.drawable.dish_tv_medium);
        intent.putExtra(ProjectConstants.CUSTOMER_ID_HINT, getString(R.string.dish_hint));
        intent.putExtra(ProjectConstants.AMOUNT_MESSAGE,
            getString(R.string.recharge_detail_message_2));
        break;

      case R.id.dth_sun_direct:
        intent.putExtra(ProjectConstants.PROVIDER, getString(R.string.sun_direct));
        intent.putExtra(ProjectConstants.PROVIDER_IMAGE, R.drawable.sun_direct_medium);
        intent.putExtra(ProjectConstants.CUSTOMER_ID_HINT, getString(R.string.sun_direct_hint));
        break;

      case R.id.dth_tata_sky:
        intent.putExtra(ProjectConstants.PROVIDER, getString(R.string.tata_sky));
        intent.putExtra(ProjectConstants.PROVIDER_IMAGE, R.drawable.tata_sky_medium);
        intent.putExtra(ProjectConstants.CUSTOMER_ID_MESSAGE,
            getString(R.string.recharge_detail_message_1));
        intent.putExtra(ProjectConstants.CUSTOMER_ID_HINT, getString(R.string.tata_sky_hint));
        intent.putExtra(ProjectConstants.AMOUNT_MESSAGE,
            getString(R.string.recharge_detail_message_3));
        break;

      case R.id.dth_d2h:
        intent.putExtra(ProjectConstants.PROVIDER, getString(R.string.d2h));
        intent.putExtra(ProjectConstants.PROVIDER_IMAGE, R.drawable.d2h_medium);
        intent.putExtra(ProjectConstants.CUSTOMER_ID_HINT, getString(R.string.tata_sky_hint));
        intent.putExtra(ProjectConstants.CUSTOMER_ID_MESSAGE,
            getString(R.string.recharge_detail_message_1));
        intent.putExtra(ProjectConstants.AMOUNT_MESSAGE,
            getString(R.string.recharge_detail_message_2));
        break;

      case R.id.land_line_airtel:
        intent.putExtra(ProjectConstants.PROVIDER, getString(R.string.airtel));
        intent.putExtra(ProjectConstants.PROVIDER_IMAGE, R.drawable.airtel_medium);
        intent.putExtra(ProjectConstants.CUSTOMER_ID_HINT, getString(R.string.telephone_number));
        intent.putExtra(ProjectConstants.CUSTOMER_ID_MESSAGE,
            getString(R.string.recharge_detail_message_4));
        intent.putExtra(ProjectConstants.AMOUNT_MESSAGE,
            getString(R.string.recharge_detail_message_2));
        break;

      case R.id.land_line_bsnl:
        intent.putExtra(ProjectConstants.PROVIDER, getString(R.string.bsnl));
        intent.putExtra(ProjectConstants.PROVIDER_IMAGE, R.drawable.bsnl_medium);
        intent.putExtra(ProjectConstants.CUSTOMER_ID_HINT, getString(R.string.telephone_number));
        intent.putExtra(ProjectConstants.CUSTOMER_ID_MESSAGE,
            getString(R.string.recharge_detail_message_4));
        break;

      case R.id.land_line_docomo_gsm:
        intent.putExtra(ProjectConstants.PROVIDER, getString(R.string.tata_gsm1));
        intent.putExtra(ProjectConstants.PROVIDER_IMAGE, R.drawable.tata_medium);
        intent.putExtra(ProjectConstants.CUSTOMER_ID_HINT, getString(R.string.telephone_number));
        intent.putExtra(ProjectConstants.CUSTOMER_ID_MESSAGE,
            getString(R.string.recharge_detail_message_4));
        break;
      case R.id.land_line_docomo_postpaid:
        intent.putExtra(ProjectConstants.PROVIDER, getString(R.string.tata_postpaid));
        intent.putExtra(ProjectConstants.PROVIDER_IMAGE, R.drawable.tata_medium);
        intent.putExtra(ProjectConstants.CUSTOMER_ID_HINT, getString(R.string.telephone_number));
        intent.putExtra(ProjectConstants.CUSTOMER_ID_MESSAGE,
            getString(R.string.recharge_detail_message_4));
        break;

      case R.id.land_line_reliance:
        intent.putExtra(ProjectConstants.PROVIDER, getString(R.string.reliance));
        intent.putExtra(ProjectConstants.PROVIDER_IMAGE, R.drawable.reliance_medium);
        intent.putExtra(ProjectConstants.CUSTOMER_ID_HINT, getString(R.string.telephone_number));
        intent.putExtra(ProjectConstants.CUSTOMER_ID_MESSAGE,
            getString(R.string.recharge_detail_message_4));
        break;

      case R.id.land_line_mtnl_delhi:
        intent.putExtra(ProjectConstants.PROVIDER, getString(R.string.mtnl_delhi));
        intent.putExtra(ProjectConstants.PROVIDER_IMAGE, R.drawable.mtnl_medium);
        intent.putExtra(ProjectConstants.CUSTOMER_ID_HINT, getString(R.string.telephone_number));
        intent.putExtra(ProjectConstants.CUSTOMER_ID_MESSAGE,
            getString(R.string.recharge_detail_message_4));
        intent.putExtra(ProjectConstants.ACCEPT_ACCOUNT_ID, true);
        intent.putExtra(ProjectConstants.ACCOUNT_MESSAGE,
            getString(R.string.recharge_detail_message_5));
        break;

      case R.id.land_line_mtnl_mumbai:
        intent.putExtra(ProjectConstants.PROVIDER, getString(R.string.mtnl_mumbai));
        intent.putExtra(ProjectConstants.PROVIDER_IMAGE, R.drawable.mtnl_medium);
        intent.putExtra(ProjectConstants.CUSTOMER_ID_HINT, getString(R.string.telephone_number));
        intent.putExtra(ProjectConstants.CUSTOMER_ID_MESSAGE,
            getString(R.string.recharge_detail_message_4));
        intent.putExtra(ProjectConstants.ACCEPT_ACCOUNT_ID, true);
        intent.putExtra(ProjectConstants.ACCOUNT_MESSAGE,
            getString(R.string.recharge_detail_message_5));
        break;
    }
    startActivity(intent);
  }

}