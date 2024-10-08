package com.penny.quick.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.penny.quick.R;
import com.penny.quick.models.PlanModel;
import com.penny.quick.ui.activities.view_plans.ViewPlansActivity;
import java.util.List;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.MyViewHolder> {

  private final List<PlanModel> planModels;
  private final Context context;
  private final PlanClickListener planClickListener;

  public PlansAdapter(List<PlanModel> planModels, Context context) {
    this.planModels = planModels;
    this.context = context;
    this.planClickListener = (ViewPlansActivity) context;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.plans_item, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    PlanModel plan = planModels.get(position);
    holder.bindClickListener(plan,planClickListener);
    holder.talkTime.setText(String
        .format("%s%s", context.getString(R.string.rupees_sign), plan.getTalktime()));
    holder.data.setText(plan.getData());
    holder.validity.setText(plan.getValidity());
    holder.amount.setText(String
        .format("%s%s", context.getString(R.string.rupees_sign), plan.getAmount()));
    holder.message1.setText(plan.getMessage1());
    holder.message2.setText(plan.getMessage2());
    holder.message3.setText(plan.getMessage3());
  }

  @Override
  public int getItemCount() {
    return planModels.size();
  }

  static class MyViewHolder extends ViewHolder {

    private final TextView talkTime, data, validity, amount, message1, message2, message3;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      talkTime = itemView.findViewById(R.id.tv_talk_time);
      data = itemView.findViewById(R.id.tv_data);
      validity = itemView.findViewById(R.id.tv_validity);
      amount = itemView.findViewById(R.id.tv_amount);
      message1 = itemView.findViewById(R.id.message_1);
      message2 = itemView.findViewById(R.id.message_2);
      message3 = itemView.findViewById(R.id.message_3);
    }

    public void bindClickListener(PlanModel plan,PlanClickListener planClickListener) {
      itemView.setOnClickListener(view -> planClickListener.onPlanClick(plan));
    }
  }

  public interface PlanClickListener {
     void onPlanClick(PlanModel plan);
  }
}
