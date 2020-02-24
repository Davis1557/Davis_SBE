package contracts.SBE

import org.springframework.cloud.contract.spec.Contract

import static org.springframework.http.HttpHeaders.CONTENT_TYPE

Contract.make {
    description "return customer"

    request {
        urlPath regex('/customer/\\d{1,3}')
        method GET()
        headers {
            header(CONTENT_TYPE, value(consumer(regex("application/json"))))
        }
    }

    response {
        status 200
        headers {
            header(CONTENT_TYPE, value(producer(regex("application/json"))))
        }
        body([
                "id"  : value(
                        consumer(fromRequest().path(1)),
                        producer(regex("\\d{1,3}"))
                ),
                "name": value(
                        consumer("Mock_${regex("(AAA|BBB)")}_${fromRequest().path(1)}"),
                        producer(regex("\\w{1,20}"))
                )
        ])
    }
}