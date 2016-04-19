

curl -X POST -vu ecokrypt:12345 http://localhost:8282/ecokrypt/oauth/token -H "Accept: application/json" -d "password=ecokrypt&username=ecokrypt&grant_type=password&scope=write&client_secret=12345&client_id=ecokrypt"


curl http://localhost:8282/ecokrypt/articles -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE0NjA3NzY5NDIsInVzZXJfbmFtZSI6ImVjb2tyeXB0IiwianRpIjoiNWI5ZmE4NjYtMTBlNS00NDA2LTgxYmMtYjM3NWVmNGE0ZmQ1IiwiY2xpZW50X2lkIjoiZWNva3J5cHQiLCJzY29wZSI6WyJ3cml0ZSJdfQ.4jO9euBz4TSSNh7M3l2YBD1QPblE0ZyOfGm4VtyFMFY"



---------------*------------------------

http://stackoverflow.com/questions/25136532/allow-options-http-method-for-oauth-token-request

https://github.com/idursun/spring-and-angular/blob/master/src/main/java/com/idursun/springandangular/config/CorsConfiguration.java

http://stackoverflow.com/questions/30632200/standalone-spring-oauth2-jwt-authorization-server-cors/30638914#30638914

http://stackoverflow.com/questions/31724994/spring-data-rest-and-cors/31748398#31748398

http://stackoverflow.com/questions/25957879/filter-order-in-spring-boot/26147788#26147788