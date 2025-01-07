package com.restassured;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args){
        JsonPath jsonPath = new JsonPath(payload.coursePrice());

        //print number of courses
        int numberCourses = jsonPath.getInt("courses.size()");
        System.out.println(numberCourses);

        //Print Purchase Amount
        int totalAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        //Print Title of the first course
        String title = jsonPath.get("courses[0].title");
        System.out.println(title);

        //Print All course titles and their respective Prices
        for(int i=0; i<numberCourses;i++){
            String titleLoop = jsonPath.get("courses["+i+"].title");
            int priceLoop = jsonPath.getInt("courses["+i+"].price");
            System.out.println(titleLoop+" "+priceLoop);
        }

        // Print no of copies sold by RPA Course
        



        //Verify if Sum of all Course prices matches with Purchase Amount
    }

}
