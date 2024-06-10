package tr.com.priper.coffeeshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Report implements Serializable{
	
	Date reportDate;
	double totalEarned;
	double avgDeliveryTime;
	int totalOrdersDelivered;
	int totalOrdersTaken;
	int totalOrdersNotDelivered;
	double thisMonthAvgDeliveryTime;
	double thisMonthTotalEarned;
	int thisMonthOrdersDelivered;
	int thisMonthOrdersTaken;
	int thisMonthOrdersNotDelivered;
	double thisYearAvgDeliveryTime;
	double thisYearTotalEarned;
	int thisYearOrdersDelivered;
	int thisYearOrdersTaken;
	int thisYearOrdersNotDelivered;
	
	public Report() {
		this.reportDate = new Date();
		this.totalEarned = 0;
		this.avgDeliveryTime = 0.00;
		this.totalOrdersNotDelivered = 0;
		this.totalOrdersTaken = 0;
		this.totalOrdersDelivered = 0;
		this.thisMonthOrdersDelivered = 0;
		this.thisMonthOrdersTaken = 0;
		this.thisMonthOrdersNotDelivered = 0;
		this.thisMonthAvgDeliveryTime = 0;
		this.thisMonthTotalEarned = 0;
		this.thisYearOrdersDelivered = 0;
		this.thisYearOrdersTaken = 0;
		this.thisYearOrdersNotDelivered = 0;
		this.thisYearAvgDeliveryTime = 0;
		this.thisYearTotalEarned = 0;
		ArrayList<Order> orders = (ArrayList<Order>)Main.importOrders();
		for (Order order: orders) {
			this.totalOrdersTaken += 1;
			if (order.isDelivered()) {
				this.totalEarned += order.getTotal();
				this.avgDeliveryTime += order.getDeliveryTimeInSeconds();
				this.totalOrdersDelivered += 1;
				if (order.getDateReceived().getYear() == this.reportDate.getYear()) {
					if (order.getDateReceived().getMonth() == this.reportDate.getMonth()) {
						this.thisMonthOrdersDelivered += 1;
						this.thisMonthOrdersTaken += 1;
						this.thisMonthAvgDeliveryTime += order.getDeliveryTimeInSeconds();
						this.thisMonthTotalEarned += order.getTotal();
					}
					this.thisYearOrdersDelivered += 1;
					this.thisYearOrdersTaken += 1;
					this.thisYearAvgDeliveryTime += order.getDeliveryTimeInSeconds();
					this.thisYearTotalEarned += order.getTotal();
				}
				
			}
			else { // if order is not delivered
				this.totalOrdersNotDelivered += 1;
				if (order.getDateReceived().getYear() == this.reportDate.getYear()) {
					if (order.getDateReceived().getMonth() == this.reportDate.getMonth()) {
						this.thisMonthOrdersTaken += 1;
						this.thisMonthOrdersNotDelivered += 1;
					}
					this.thisYearOrdersTaken += 1;
					this.thisYearOrdersNotDelivered += 1;
				}
			}
		}
		if (totalOrdersTaken != 0) this.avgDeliveryTime /= this.totalOrdersTaken;
		if (thisYearOrdersTaken != 0) this.thisMonthAvgDeliveryTime /= this.thisYearOrdersTaken;
		if (thisMonthOrdersTaken != 0) this.thisYearAvgDeliveryTime /= this.thisMonthOrdersTaken;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public double getTotalEarned() {
		return totalEarned;
	}

	public void setTotalEarned(double totalEarned) {
		this.totalEarned = totalEarned;
	}

	public double getAvgDeliveryTime() {
		return avgDeliveryTime;
	}

	public void setAvgDeliveryTime(double avgDeliveryTime) {
		this.avgDeliveryTime = avgDeliveryTime;
	}

	public int getTotalOrdersDelivered() {
		return totalOrdersDelivered;
	}

	public void setTotalOrdersDelivered(int totalOrdersDelivered) {
		this.totalOrdersDelivered = totalOrdersDelivered;
	}

	public int getTotalOrdersTaken() {
		return totalOrdersTaken;
	}

	public void setTotalOrdersTaken(int totalOrdersTaken) {
		this.totalOrdersTaken = totalOrdersTaken;
	}

	public int getTotalOrdersNotDelivered() {
		return totalOrdersNotDelivered;
	}

	public void setTotalOrdersNotDelivered(int totalOrdersNotDelivered) {
		this.totalOrdersNotDelivered = totalOrdersNotDelivered;
	}

	public double getThisMonthAvgDeliveryTime() {
		return thisMonthAvgDeliveryTime;
	}

	public void setThisMonthAvgDeliveryTime(double thisMonthAvgDeliveryTime) {
		this.thisMonthAvgDeliveryTime = thisMonthAvgDeliveryTime;
	}

	public double getThisMonthTotalEarned() {
		return thisMonthTotalEarned;
	}

	public void setThisMonthTotalEarned(double thisMonthtotalEarned) {
		this.thisMonthTotalEarned = thisMonthtotalEarned;
	}

	public int getThisMonthOrdersDelivered() {
		return thisMonthOrdersDelivered;
	}

	public void setThisMonthOrdersDelivered(int thisMonthOrdersDelivered) {
		this.thisMonthOrdersDelivered = thisMonthOrdersDelivered;
	}

	public int getThisMonthOrdersTaken() {
		return thisMonthOrdersTaken;
	}

	public void setThisMonthOrdersTaken(int thisMonthOrdersTaken) {
		this.thisMonthOrdersTaken = thisMonthOrdersTaken;
	}

	public int getThisMonthOrdersNotDelivered() {
		return thisMonthOrdersNotDelivered;
	}

	public void setThisMonthOrdersNotDelivered(int thisMonthOrdersNotDelivered) {
		this.thisMonthOrdersNotDelivered = thisMonthOrdersNotDelivered;
	}

	public double getThisYearAvgDeliveryTime() {
		return thisYearAvgDeliveryTime;
	}

	public void setThisYearAvgDeliveryTime(double thisYearAvgDeliveryTime) {
		this.thisYearAvgDeliveryTime = thisYearAvgDeliveryTime;
	}

	public double getThisYearTotalEarned() {
		return thisYearTotalEarned;
	}

	public void setThisYearTotalEarned(double thisYearTotalEarned) {
		this.thisYearTotalEarned = thisYearTotalEarned;
	}

	public int getThisYearOrdersDelivered() {
		return thisYearOrdersDelivered;
	}

	public void setThisYearOrdersDelivered(int thisYearOrdersDelivered) {
		this.thisYearOrdersDelivered = thisYearOrdersDelivered;
	}

	public int getThisYearOrdersTaken() {
		return thisYearOrdersTaken;
	}

	public void setThisYearOrdersTaken(int thisYearOrdersTaken) {
		this.thisYearOrdersTaken = thisYearOrdersTaken;
	}

	public int getThisYearOrdersNotDelivered() {
		return thisYearOrdersNotDelivered;
	}

	public void setThisYearOrdersNotDelivered(int thisYearOrdersNotDelivered) {
		this.thisYearOrdersNotDelivered = thisYearOrdersNotDelivered;
	}
	
	
	
	
}
