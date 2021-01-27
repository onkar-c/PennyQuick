package com.penny.quick.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.penny.database.entities.Report;
import com.penny.quick.R;
import java.util.List;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.MyViewHolder> {

  private final List<Report> reports;
  private final Context context;

  public ReportsAdapter(List<Report> reports, Context context) {
    this.reports = reports;
    this.context = context;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.report_item, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Report report = reports.get(position);
    holder.reportType.setText(report.getReportType());
    holder.transactionType.setText(
        String.format("%s%s", report.getTransactionType(), context.getString(R.string.colon)));
    holder.transactionAmount.setText(String
        .format("%s%s", context.getString(R.string.rupees_sign), report.getTransactionAmount()));
    holder.date.setText(report.getDate());
    holder.description.setText(report.getDescription());
    holder.balance.setText(
        String.format("%s%s", context.getString(R.string.rupees_sign), report.getBalance()));
  }

  public void setList(List<Report> reports) {
    this.reports.clear();
    this.reports.addAll(reports);
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return reports.size();
  }

  static class MyViewHolder extends ViewHolder {

    private final TextView reportType, transactionType, transactionAmount, date, description, balance;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      reportType = itemView.findViewById(R.id.report_type);
      transactionType = itemView.findViewById(R.id.transaction_type);
      transactionAmount = itemView.findViewById(R.id.transaction_amount);
      date = itemView.findViewById(R.id.date);
      description = itemView.findViewById(R.id.description);
      balance = itemView.findViewById(R.id.balance);
    }
  }
}
