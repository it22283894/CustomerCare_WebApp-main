package model;

public class Tracking extends Order {
    private int orderId;
    private String trackingNo;
    private String orderdate;
    private String deliverydate;
    private String status;
    private String address;
    private String paymentmethod;
    
	public Tracking(int orderId, String trackingNo, String orderdate, String deliverydate, String status,
			String address, String paymentmethod) {
		super();
		this.orderId = orderId;
		this.trackingNo = trackingNo;
		this.orderdate = orderdate;
		this.deliverydate = deliverydate;
		this.status = status;
		this.address = address;
		this.paymentmethod = paymentmethod;
	}

	public Tracking(String trackingNo, String orderdate, String deliverydate, String status, String address,
			String paymentmethod) {
		super();
		this.trackingNo = trackingNo;
		this.orderdate = orderdate;
		this.deliverydate = deliverydate;
		this.status = status;
		this.address = address;
		this.paymentmethod = paymentmethod;
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

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}
	
	
    
       
}
