package com.ajolla.lactomama.mother.cart

import com.ajolla.lactomama.model.Product

data class CartItem(
    var product: Product,
    var quantity: Int = 0
)