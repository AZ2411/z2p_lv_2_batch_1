package com.mmit.controller.request;

public class OrderProductData {
		private int productId;
		private int qty;
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		@Override
		public String toString() {
			return "OrderProductData [productId=" + productId + ", qty=" + qty + "]";
		}
		public int getQty() {
			return qty;
		}
		public void setQty(int qty) {
			this.qty = qty;
		}
		
}
