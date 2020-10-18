package com.penny.quick.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.penny.database.ProjectConstants;
import com.penny.database.entities.RecentRecharge;
import com.penny.database.utils.DateUtils;
import com.penny.quick.R;
import com.penny.quick.utils.CommonUtils;
import java.util.List;

public class RecentRechargeAdapter extends
    RecyclerView.Adapter<RecentRechargeAdapter.MyViewHolder> {

  private List<RecentRecharge> recentRecharges;
  private Context context;

  public RecentRechargeAdapter(List<RecentRecharge> recentRecharges, Context context) {
    this.recentRecharges = recentRecharges;
    this.context = context;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.recent_recharge_item, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.setIsRecyclable(false);
    RecentRecharge recentRecharge = recentRecharges.get(position);
    CommonUtils.getImage(holder.itemView.getContext(), ProjectConstants.IMAGE_URL + recentRecharge.getUrl(),
        holder.company_icon, R.drawable.hamburger_icon);
    if (recentRecharge.getCompanyType().equals(ProjectConstants.DTH)) {
      holder.companyType.setText(
          String.format("%s - %s", ProjectConstants.DTH, recentRecharge.getDisplayName()));
    } else {
      holder.companyType.setText(recentRecharge.getDisplayName());
    }
    holder.customerId.setText(recentRecharge.getCustomerId());
    holder.transactionId.setText(recentRecharge.getTransactionId());
    holder.date.setText(DateUtils.getDateInRechargeDateFormat(recentRecharge.getDate()));
    holder.amount.setText(
        String.format("%s%s", context.getString(R.string.rupees_sign), recentRecharge.getAmount()));
    Drawable img = ContextCompat.getDrawable(context,
        (recentRecharge.getStatus().equals(ProjectConstants.SUCCESS)) ? R.drawable.success_small
            : (recentRecharge.getStatus().equals(ProjectConstants.PENDING))
                ? R.drawable.pending_small
                : R.drawable.failed_small);
    holder.amount.setCompoundDrawablesWithIntrinsicBounds(null, null, null, img);
  }

  public void setList(List<RecentRecharge> pRecentRecharges) {
    recentRecharges.clear();
    recentRecharges.addAll(pRecentRecharges);
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return recentRecharges.size();
  }

  static class MyViewHolder extends ViewHolder {

    private TextView companyType, customerId, transactionId, date, amount;
    private ImageView company_icon;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      company_icon = itemView.findViewById(R.id.company_icon);
      companyType = itemView.findViewById(R.id.company_type);
      customerId = itemView.findViewById(R.id.customer_id);
      transactionId = itemView.findViewById(R.id.transaction_id);
      date = itemView.findViewById(R.id.date);
      amount = itemView.findViewById(R.id.amount);
    }
  }
}
