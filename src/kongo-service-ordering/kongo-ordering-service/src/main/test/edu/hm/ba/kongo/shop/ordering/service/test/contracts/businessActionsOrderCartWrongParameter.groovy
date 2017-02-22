package edu.hm.ba.kongo.shop.ordering.service.test.contracts

import org.springframework.cloud.contract.spec.Contract


/**
 * Contract for GET on the base endpoint
 */
Contract.make {
    request {
        method 'POST'
        url '/businessActions/orderCart'
        headers {
            contentType(applicationJson())
        }
        body("""{}""")
    }
    response {
        status 422
        body("""
{
  "error": "Illegal Argument Exception",
  "message": "Expected values in body: String cartID - Missing [cartID]",
  "status": 422
}
""")
    }
}