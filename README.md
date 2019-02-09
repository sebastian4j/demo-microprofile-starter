Aplicación que utiliza la base de proyecto generada por https://start.microprofile.io/ y keycloak para agregar la seguridad utilizando JWT :blush:


compilar: mvn clean package
ejecutar: java -jar target/demo.mp-thorntail.jar

### Configurar Keycloak

- Add Ream >
	Name: MicroProfile
- Realm Settings >
	Keys >
		Public Key: copiar en resources/META-INF/MP-JWT-SIGNER
- Groups >
	Name: protected	
- Users >
	Add user >
		Username: user	
- Users >
	user >
		Credentials: password >>
		Temporary: off >>
		reset Password			
- Users >
	user >
		Groups >
			protected: join
- Clients >
	Create >
		Client ID: java	>
		Root URL: http://localhost:8080/
- Clients >
	java >
		Mappers >
			Create >
				Mapper Type: Group Membership >>
				Name: group >>
				Token Claim Name: groups >>
				Full group path: off
		
### custom-value				
- Users >
	user >
		Attributes>
			key: att-key >>
			Value: att-value >> add + save

- Clients >
	java >
		Mappers >
			Create >
				name: mapper-att >>
				Mapper Type: User Attribute >>
				User Attribute: att-key >>
				Token Claim Name: custom-value >>
				Claim JSON Type: String
				
				
			
	
				
### solicitar recurso protegido sin usar token:
~~~
request: curl http://localhost:8080/data/protected

response: <html><head><title>Error</title></head><body>Unauthorized</body></html>
~~~
### solicitar token:

~~~
curl -s --data "grant_type=password&client_id=java&username=user&password=password" http://localhost:8282/auth/realms/MicroProfile/protocol/openid-connect/token

response: {"access_token":"eyJhbGciOi...........", ...}
~~~

### solicitar recurso protegido usando token:

~~~
curl --header "Authorization: Bearer eyJhbGciOi..........." http://localhost:8080/data/protected

response: Protected Resource; Custom value : att-value token: eyJhbGciOi...........

~~~












				


		
		
