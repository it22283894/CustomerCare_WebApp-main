package model;



public class Order {
    private int orderId;
    private String trackingNo;
    private String status;
    private String totalAmount;
    private String email;
    private String date;
    private String address;
    
    
    

	public Order(int orderId, String trackingNo, String status, String totalAmount,String date,String address) {
		super();
		this.orderId = orderId;
		this.trackingNo = trackingNo;
		this.status = status;
		this.totalAmount = totalAmount;
		this.date = date;
		this.address = address;
	}



	public Order(int orderId, String trackingNo, String status, String totalAmount, String email,String date,String address) {
		super();
		this.orderId = orderId;
		this.trackingNo = trackingNo;
		this.status = status;
		this.totalAmount = totalAmount;
		this.email = email;
		this.date = date;
		this.address = address;
	}
	
	

	public Order(String trackingNo, String status, String totalAmount, String email,String date,String address) {
		super();
		this.trackingNo = trackingNo;
		this.status = status;
		this.totalAmount = totalAmount;
		this.email = email;
		this.date = date;
		this.address = address;
	}



	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public String getTrackingNo() {
		return trackingNo;
	}



	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}
	    
       
}
