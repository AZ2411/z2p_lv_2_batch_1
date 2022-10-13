$(document).ready(function() {
	initializeLocalStorage();
	showCartCount();
	showCart()
	//update qty
	$('tbody.cart-items').on('change', '.update-qty', function() {
		
		let id_val = $(this).data('id');
		let qty_val = $(this).val();
		
	 	let cartItemList = localStorage.getItem('cart-item-data');
	 	let cartItemList_obj = JSON.parse(cartItemList);
	 	
	 	let update_index = -1;
	 	let update_product = '';
	 	
	 	$.each(cartItemList_obj, function(i, prod) {
			if(prod.productId == id_val) {
				update_index = i;
				update_product = prod;
			}
		});
		
		//update
		update_product.qty = qty_val;
		cartItemList_obj[update_index] = update_product;
		localStorage.setItem('cart-item-data', JSON.stringify(cartItemList_obj));
		
		showCart();
		
	});
	
	
	//remove product
	$('tbody.cart-items').on('click', '.btn-remove', function(evt) {
		evt.preventDefault();
		
		let id_val = $(this).data('id');
		
		let cartItemList = localStorage.getItem('cart-item-data');
		let cartItemList_obj = JSON.parse(cartItemList);
		
		let delete_index = -1;
		$.each(cartItemList_obj, function(i, prod) {
			if(prod.productId == id_val) {
				delete_index = i;
			}
			
		});
		
		cartItemList_obj.splice(delete_index, 1);
		localStorage.setItem('cart-item-data', JSON.stringify(cartItemList_obj));
		
		showCartCount();
		showCart();
	});
	//add product
	$('div.product').on('click' ,'.add-to-cart' , function(evt) {
		evt.preventDefault();
		let id_val = $(this).data('id');
		let name_val = $(this).data('name');
		let price_val = $(this).data('price');
		
		let cartItemList = localStorage.getItem('cart-item-data');
		let cartItemList_obj = JSON.parse(cartItemList);
		
		let flag = false;
		
		let found_index = -1;
		let old_product = '';
		$.each(cartItemList_obj, function(i, product) {
			if(id_val == product.productId) {
				flag = true;
				found_index = i;
				old_product = product;
			}
		});
		
		if(flag) {	// old
			old_product.qty = old_product.qty + 1;
			cartItemList_obj[found_index] = old_product;
		}
		else{ // new
			let new_prod = {
				productId: id_val,
				name: name_val,
				price: price_val,
				qty: 1
				};
				cartItemList_obj.push(new_prod);
		}
		
		//store arry obj -> localStorage
		localStorage.setItem('cart-item-data',JSON.stringify(cartItemList_obj))
		showCartCount();
		})
		
		function showCart() {
		let cartItemList = localStorage.getItem('cart-item-data');
		let cartItemList_obj = JSON.parse(cartItemList);
		let html = '';
		
		let total = 0;
		$.each(cartItemList_obj, function(i, prod) {
			
			let sub_total = prod.price * prod.qty;
			total += sub_total;
			console.log("pId = " + prod.productId + ", sub_total = " + sub_total);
			
			let tr = `
			
						      <tr class="text-center">
						        <td class="product-remove"><a href="#" data-id="${prod.productId}" class="btn-remove"><span class="ion-ios-close"></span></a></td>
						        
						        
						        
						         <td class="product-name">
						        	<p>${prod.name}</p>
						        </td>
						        <td class="price">${prod.price}</td>
						        
						        <td class="quantity">
						        	<div class="input-group mb-3">
					             	<input type="number" data-id="${prod.productId}"  name="quantity" class="form-control update-qty" value="${prod.qty}" min="1" max="100">
					             	</td>
						        
						        <td  >${sub_total}</td>
						      </tr>`;
			html += tr;
		});
		
		$('tbody.cart-items').html(html);
		$('.total').text(total + " ks.");
		$('.grand-total').text(total + " ks.");
		
		if(cartItemList_obj.length == 0) {
			$('a.btn-checkout').removeAttr('href');
		}
		
	}
	
	function showCartCount() {
		let cartItemList = localStorage.getItem('cart-item-data');
		let cartItemList_obj = JSON.parse(cartItemList);
		$('.cart-count').text(cartItemList_obj.length);
		
	}
	function initializeLocalStorage() {
		let cartItemList = localStorage.getItem('cart-item-data');
		if(! cartItemList) {
			cartItemList = [];
			localStorage.setItem('cart-item-data', JSON.stringify(cartItemList))
			
			
			}
			
		}
		
		
	
		});
