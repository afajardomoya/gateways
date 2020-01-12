Gateways Management API

1. How to use the solution
- The solution uses an in-memory H2 database for simplicity in its implementation and further evaluation.
- Initial gateways and devices data are provided with an "import.sql" resource, which it's executed at application
start up.
- To run the solution, Apache Maven it's required (the project was developed using Maven version 3.6.3).
- Execute in the root path of the project the command "mvn springboot:run"; it should download all required dependencies
and plugins.

2. Testing the application
- Several unit tests were implemented, mainly to test the "create" and "delete" operations for gateways and devices
(at controller, service and repository instances).
- A simple integration test was implemented for the "create" endpoint in gateways controller. 
- To run all tests use "mvn clean test".

3. Using the API (curl examples):
Gateways
- List all gateways: curl -X GET http://localhost:8080/api/v1/gateways/ -H "Content-Type: application/json" -d '{"page": 0, "limit": 20}'
- Create a gateway: curl -X POST http://localhost:8080/api/v1/gateways/ -H "Content-Type: application/json" -d '{"serialNumber": "aaa-2366-89987", "name": "gateway 3", "ipAdress": "192.168.26.3"}'
- Update a gateway: curl -X PUT http://localhost:8080/api/v1/gateways/1 -H "Content-Type: application/json" -d '{"id": 1, "serialNumber": "xyz-123-456", "name": "gateway 1 reloaded", "ipAdress": "192.168.26.11"}'
- Delete a gateway: curl -X DELETE http://localhost:8080/api/v1/gateways/1 
Devices
- List all gateway devices: curl -X GET http://localhost:8080/api/v1/gateways/2/devices -H "Content-Type: application/json" -d '{"page": 0, "limit": 20}'
- Create a gateway device: curl -X POST http://localhost:8080/api/v1/gateways/2/devices -H "Content-Type: application/json" -d '{"uid": 999988889944774449, "vendor": "x-company", "status": "OFFLINE"}'
- Update a gateway device: curl -X PUT http://localhost:8080/api/v1/gateways/2/devices/4 -H "Content-Type: application/json" -d '{"id":4,"uid":4444444444444, "vendor":"x-company", "status":"ONLINE"}'
- Delete a gateway device: curl -X DELETE http://localhost:8080/api/v1/gateways/2/devices/4