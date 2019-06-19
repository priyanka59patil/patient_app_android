package com.werq.patient.MockData;

import com.google.gson.Gson;
import com.werq.patient.Models.ProfileData;
import com.werq.patient.Models.Responce;

public class ProfileJsonData {

   public static Responce getProfileData(){
       Responce profileData;
       String profilejson="{\n" +
               "  \"response\": {\n" +
               "    \n" +
               "    \"personal_info\":{\n" +
               "       \"first_name\": \"parag\",\n" +
               "    \"middle_name\": \"\",\n" +
               "    \"last_name\": \"mane\",\n" +
               "    \"phone_number\": \"656565655\",\n" +
               "      \"profile_url\": \"ASASaSA\",\n" +
               "        \"dob\":\"31 may,2009\", \n" +
               "    \"address\": {\n" +
               "      \"address1\": \"ahdvhjajdjsavd,sdasd,asdsad,asdasd,\",\n" +
               "      \"address2\": \"asdsadasdasdd,asdsa\",\n" +
               "      \"city\": \"mumbai\",\n" +
               "      \"state\": \"maharastra\",\n" +
               "      \"postal_code\": \"40072\"\n" +
               "    }\n" +
               "    },\n" +
               "    \n" +
               "    \"medical_info\": [\n" +
               "      {\n" +
               "        \"medical_info_id\": \"ASaS\",\n" +
               "        \"type\": \"ASaSA\",\n" +
               "        \"medical_details\": {\n" +
               "          \"value\": \"ASAS\",\n" +
               "          \"title\": \"ASASaSAS\"\n" +
               "        }\n" +
               "      },\n" +
               "      {\n" +
               "        \"medical_info_id\": \"23\",\n" +
               "        \"type\": \"ASAs\",\n" +
               "        \"medical_details\": {\n" +
               "          \"value\": \"ASA\",\n" +
               "          \"title\": \"aSAS\"\n" +
               "        }\n" +
               "      }\n" +
               "    ],\n" +
               "    \"insurance_info\": {\n" +
               "      \"front_card_url\": \"ASA\",\n" +
               "      \"back_card_url\": \"ASAs\",\n" +
               "      \"insurance_type\": \"ASAS\",\n" +
               "      \"insurance_address\": \"ASAS\",\n" +
               "      \"insurance_phone_number\": \"aSAS\",\n" +
               "      \"coverage_dates\": \"aSAS\",\n" +
               "      \"member_id\": \"aSA\",\n" +
               "      \"relationship_subscriber\": \"aSAS\",\n" +
               "      \"patient_address\": \"aSAS\",\n" +
               "      \"patient_phone_number\": \"aSAS\",\n" +
               "      \"patient_dob\": \"aSAsAS\",\n" +
               "      \"subscriber_id\": \"ASAS\",\n" +
               "      \"subscriber_dob\": \"sASA\",\n" +
               "      \"group_number\": \"asASAsaSAS\"\n" +
               "    },\n" +
               "      \"medications\": [\n" +
               "    {\n" +
               "      \"medication\": \"\",\n" +
               "      \"instruction\": \"\",\n" +
               "      \"dosage\": \"\",\n" +
               "      \"effectivedates\": \"\"\n" +
               "    }\n" +
               "  ]\n" +
               "  }\n" +
               "}";

       profileData=new Gson().fromJson(profilejson,Responce.class);
       return profileData;
   }


}
