# Getting Started

### Sample Request for Create Connection Group

> curl --location --request POST 'http://localhost:8080/connection-group' \
> --header 'Content-Type: application/json' \
> --data-raw '{
> "name": "test01",
> "virtualNode": {
> "nodeId": "vn1",
> "children": [
> {
> "nodeId": "vn2",
> "children": [
> {
> "nodeId": "vn5"
> },
> {
> "nodeId": "vn4"
> }
> ]
> },
> {
> "nodeId": "vn3"
> }
> ]
> }
> }'

### Sample Request to find Virtual Node

> curl --location --request GET 'http://localhost:8080/connection-group/node/vn1'