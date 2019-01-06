Run all four services in the below order
1)Discovery-service
2)Product-service
3)Order-service
4)Recommendation-service

product service is using in memory mongo DB to load and store the products
To browse the product from the product service from the archestrator order-service by text

http://localhost:8082/order?name=Flat Iron
http://localhost:8082/order?name=curl


Order service is calling the product service with feign client
To add the product to the order
http://localhost:8082/order  (Note: For the certification project, I will update the order service to load the product details from product repository instead of sending in the request below)

payload:


 {"id":1,

"userId":2,

"address":"Sacramento",

"quantity":1,

"productDetail":{

"code":1,

"name":"PARWIN CURLING IRON",

"description":"PRO 5 in 1 Curling Iron Wand Set with 5 Interchangeable Diamond Tourmaline Ceramic Curl Iron Barrels-No Cool Tip & Travel Bag"
   }

}




The above request calls recommendation service  to print the similar products as soon as the product is added to the order as below.
recommendation service is using elastic search to query the recommendations

[
    {
        "id": 1,
        "name": "PARWIN CURLING IRON",
        "description": "PRO 5 in 1 Curling Iron Wand Set with 5 Interchangeable Diamond Tourmaline Ceramic Curl Iron Barrels-No Cool Tip & Travel Bag",
        "price": 19.99
    },
    {
        "id": 5,
        "name": "LANPRO Flat Iron",
        "description": "LANPRO Hair Straightener, Upgraded Flat Iron Straightening Iron for All Hair Types Styling 2 in 1 Tourmaline Ceramic Curling Iron W/Rotating Adjustable...   ",
        "price": 29.99
    },
    {
        "id": 6,
        "name": "Round Ceramic Tourmaline Ionic Flat Iron",
        "description": " Professional Flat Iron for Hair, Hair Straightener Curling Iron 2 in 1 Round Ceramic Tourmaline Ionic Flat Iron with Rotating Adjustable Temperature 255-450°F - Fast Heats Up - Auto Turn Off ",
        "price": 13.99
    },
    {
        "id": 4,
        "name": "Tourmaline Ceramic Flat Iron",
        "description": "Professional Hair Straightener, Flat Iron for Hair Styling: 2 in 1 Tourmaline Ceramic Flat Iron for All Hair Types with Rotating Adjustable Temperature and Salon High Heat 250℉-450℉, 1 Inch, Gold ",
        "price": 11.99
    },
    {
        "id": 2,
        "name": "SULTRA CURL",
        "description": "Sultra The Seductress Curl, Wave & Straight Iron",
        "price": 14.99
    }
]

Postman collections for the above

https://www.getpostman.com/collections/22e080e254b1867913cf



Did setup the rabbit mq server to store the prduct detail from the order in to the recommendation repository for later recommendation search -added rabbit mq sceenshot with graph showing one message in last ten minutes in to the project folder project results screenshots
Rabbit MQ user name and password
guest
guest

Originally  the recommendation service is loaded with 6 products and after creating the order , streaming the product details to rabbit mq queue saved aother product to recommendation service made it grow to 7 products in size.



links to search product repository

http://localhost:8081/product/byName/containing?name=Flat
http://localhost:8081/product/byName/containing?name=CURL


links to search recommendation repository




