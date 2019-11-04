package com.werq.patient.MockData;

import com.google.gson.Gson;
import com.werq.patient.service.model.AppointmentResponce;
import com.werq.patient.service.model.FilesData;
import com.werq.patient.service.model.Responce;
import com.werq.patient.Utils.Helper;

public class JsonData {

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
               "        \"dob\":\"May 31, 2009\", \n" +
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

   public static AppointmentResponce getUpcomingData(){
       AppointmentResponce data;

       String json="{\n" +
               "    \"response\": [\n" +
               "        {\n" +
               "            \"referral_id\": \"1\",\n" +
               "            \"schedule_status\": \"Confirmed\",\n" +
               "            \"appointment_date\": \"2019-04-21T09:53:59\",\n" +
               "            \"created_at\": \"2019-03-02T01:53:59\",\n" +
               "            \"provider\": {\n" +
               "                \"first_name\": \"Ethan\",\n" +
               "                \"middle_name\": \"A\",\n" +
               "                \"last_name\": \"Hunt\",\n" +
               "                \"profile_photo\": \"p7.png\",\n" +
               "                \"speciality\": \"Otorhinolaryngology\",\n" +
               "                \"office\": {\n" +
               "                    \"id\": 123,\n" +
               "                    \"address1\": \"12840 Riverside Dr,\",\n" +
               "                    \"address2\": \"Ste 300\",\n" +
               "                    \"city\": \"Valley Village\",\n" +
               "                    \"state\": \"California\",\n" +
               "                    \"postal_code\": \"91607\",\n" +
               "                    \"country\": \"USA\",\n" +
               "                    \"longitude\": -118.4129786,\n" +
               "                    \"latitude\": 34.1573076,\n" +
               "                    \"organization_name\": \"USA Vein Clinics\",\n" +
               "                    \"phone_number\": \"3237981800\",\n" +
               "                    \"org_npi\": 0,\n" +
               "                    \"org_location_id\": 0,\n" +
               "                    \"about_Office\": \"Long Island Jewish Medical Center is one of the cornerstones of Northwell Health, providing the full\"\n" +
               "                }\n" +
               "            },\n" +
               "      \"files\": [\n" +
               "                    {\n" +
               "                        \"file_name\": \"image01.png\",\n" +
               "                        \"file_type\": \"png\",\n" +
               "                        \"file_url\": \"http://www.gettyimages.ca/gi-resources/images/Embed/new/embed2.jpg\",\n" +
               "                        \"created_at\": \"2019-02-24T05:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"image02.jpg\",\n" +
               "                        \"file_type\": \"jpg\",\n" +
               "                        \"file_url\": \"https://ak.picdn.net/assets/cms/97e1dd3f8a3ecb81356fe754a1a113f31b6dbfd4-stock-photo-photo-of-a-common-kingfisher-alcedo-atthis-adult-male-perched-on-a-lichen-covered-branch-107647640.jpg\",\n" +
               "                        \"created_at\": \"2019-02-02T03:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"X_ray_Report_note.pdf\",\n" +
               "                        \"file_type\": \"pdf\",\n" +
               "                        \"file_url\": \"https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf\",\n" +
               "                        \"created_at\": \"2019-01-10T04:53:59\"\n" +
               "                    }\n" +
               "                ]\n" +
               "        },\n" +
               "        {\n" +
               "            \"referral_id\": \"2\",\n" +
               "            \"schedule_status\": \"Confirmed\",\n" +
               "            \"appointment_date\": \"2019-04-24T01:53:59\",\n" +
               "            \"created_at\": \"2019-03-02T01:53:59\",\n" +
               "            \"provider\": {\n" +
               "                \"first_name\": \"Thomas\",\n" +
               "                \"middle_name\": \"B\",\n" +
               "                \"last_name\": \"Downey\",\n" +
               "                \"profile_photo\": \"p8.png\",\n" +
               "                \"speciality\": \"Dentistry\",\n" +
               "                \"office\": {\n" +
               "                    \"id\": 123,\n" +
               "                    \"address1\": \"1711 E Hallandale Beach Blvd\",\n" +
               "                    \"address2\": \"Ste 200\",\n" +
               "                    \"city\": \"Hallandale Beach\",\n" +
               "                    \"state\": \"Florida\",\n" +
               "                    \"postal_code\": \"33009\",\n" +
               "                    \"country\": \"USA\",\n" +
               "                    \"phone_number\": \"9546883968\",\n" +
               "                    \"longitude\": -118.4129786,\n" +
               "                    \"latitude\": 34.1573076,\n" +
               "                    \"organization_name\": \"USA Vein Clinics\",\n" +
               "                    \"org_npi\": 0,\n" +
               "                    \"org_location_id\": 0,\n" +
               "                    \"about_Office\": \"Long Island Jewish Medical Center is one of the cornerstones of Northwell Health, providing the full\"\n" +
               "                }\n" +
               "              \n" +
               "            },\n" +
               "\"files\": [\n" +
               "                    {\n" +
               "                        \"file_name\": \"image01.png\",\n" +
               "                        \"file_type\": \"png\",\n" +
               "                        \"file_url\": \"http://www.gettyimages.ca/gi-resources/images/Embed/new/embed2.jpg\",\n" +
               "                        \"created_at\": \"2019-02-24T05:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"image02.jpg\",\n" +
               "                        \"file_type\": \"jpg\",\n" +
               "                        \"file_url\": \"https://ak.picdn.net/assets/cms/97e1dd3f8a3ecb81356fe754a1a113f31b6dbfd4-stock-photo-photo-of-a-common-kingfisher-alcedo-atthis-adult-male-perched-on-a-lichen-covered-branch-107647640.jpg\",\n" +
               "                        \"created_at\": \"2019-02-02T03:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"X_ray_Report_note.pdf\",\n" +
               "                        \"file_type\": \"pdf\",\n" +
               "                        \"file_url\": \"https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf\",\n" +
               "                        \"created_at\": \"2019-01-10T04:53:59\"\n" +
               "                    }\n" +
               "                ]\n" +
               "        },\n" +
               "        {\n" +
               "            \"referral_id\": \"3\",\n" +
               "            \"schedule_status\": \"ToConfirm\",\n" +
               "            \"appointment_date\": \"2019-03-02T01:53:59\",\n" +
               "            \"created_at\": \"2019-03-02T01:53:59\",\n" +
               "            \"provider\": {\n" +
               "                \"first_name\": \"Kim\",\n" +
               "                \"middle_name\": \"Sewa\",\n" +
               "                \"last_name\": \"Cook\",\n" +
               "                \"profile_photo\": \"p9.png\",\n" +
               "                \"speciality\": \"Radiation Oncology\",\n" +
               "                \"office\": {\n" +
               "                    \"id\": 123,\n" +
               "                    \"address1\": \"12840 Riverside Dr\",\n" +
               "                    \"address2\": \"Ste 300\",\n" +
               "                    \"city\": \"Valley Village\",\n" +
               "                    \"state\": \"California\",\n" +
               "                    \"postal_code\": \"91607\",\n" +
               "                    \"country\": \"USA\",\n" +
               "                    \"longitude\": -118.4129786,\n" +
               "                    \"latitude\": 34.1573076,\n" +
               "                    \"organization_name\": \"USA Vein Clinics\",\n" +
               "                    \"phone_number\": \"3237981800\",\n" +
               "                    \"org_npi\": 0,\n" +
               "                    \"org_location_id\": 0,\n" +
               "                    \"about_Office\": \"Long Island Jewish Medical Center is one of the cornerstones of Northwell Health, providing the full\"\n" +
               "                }\n" +
               "               \n" +
               "            },\n" +
               "\"files\": [\n" +
               "                    {\n" +
               "                        \"file_name\": \"image01.png\",\n" +
               "                        \"file_type\": \"png\",\n" +
               "                        \"file_url\": \"http://www.gettyimages.ca/gi-resources/images/Embed/new/embed2.jpg\",\n" +
               "                        \"created_at\": \"2019-02-24T05:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"image02.jpg\",\n" +
               "                        \"file_type\": \"jpg\",\n" +
               "                        \"file_url\": \"https://ak.picdn.net/assets/cms/97e1dd3f8a3ecb81356fe754a1a113f31b6dbfd4-stock-photo-photo-of-a-common-kingfisher-alcedo-atthis-adult-male-perched-on-a-lichen-covered-branch-107647640.jpg\",\n" +
               "                        \"created_at\": \"2019-02-02T03:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"X_ray_Report_note.pdf\",\n" +
               "                        \"file_type\": \"pdf\",\n" +
               "                        \"file_url\": \"https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf\",\n" +
               "                        \"created_at\": \"2019-01-10T04:53:59\"\n" +
               "                    }\n" +
               "                ]\n" +
               "\n" +
               "        },\n" +
               "        {\n" +
               "            \"referral_id\": \"4\",\n" +
               "            \"schedule_status\": \"Confirmed\",\n" +
               "            \"appointment_date\": \"2019-02-15T010:53:59\",\n" +
               "            \"created_at\": \"2019-03-02T01:53:59\",\n" +
               "            \"provider\": {\n" +
               "                \"first_name\": \"Devid\",\n" +
               "                \"middle_name\": \"Lorwes\",\n" +
               "                \"last_name\": \"Helloys\",\n" +
               "                \"profile_photo\": \"p4.png\",\n" +
               "                \"speciality\": \"Neurology\",\n" +
               "                \"office\": {\n" +
               "                    \"id\": 123,\n" +
               "                    \"address1\": \"12840 Riverside Dr\",\n" +
               "                    \"address2\": \"Ste 300\",\n" +
               "                    \"city\": \"Valley Village\",\n" +
               "                    \"state\": \"California\",\n" +
               "                    \"postal_code\": \"91607\",\n" +
               "                    \"country\": \"USA\",\n" +
               "                    \"longitude\": -118.4129786,\n" +
               "                    \"latitude\": 34.1573076,\n" +
               "                    \"organization_name\": \"USA Vein Clinics\",\n" +
               "                    \"phone_number\": \"3237981800\",\n" +
               "                    \"org_npi\": 0,\n" +
               "                    \"org_location_id\": 0,\n" +
               "                    \"about_Office\": \"Long Island Jewish Medical Center is one of the cornerstones of Northwell Health, providing the full\"\n" +
               "                }\n" +
               "            },\n" +
               "                \"files\": [\n" +
               "                    {\n" +
               "                        \"file_name\": \"image02.jpg\",\n" +
               "                        \"file_type\": \"jpg\",\n" +
               "                        \"file_url\": \"https://ak.picdn.net/assets/cms/97e1dd3f8a3ecb81356fe754a1a113f31b6dbfd4-stock-photo-photo-of-a-common-kingfisher-alcedo-atthis-adult-male-perched-on-a-lichen-covered-branch-107647640.jpg\",\n" +
               "                        \"created_at\": \"2019-02-02T03:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"X_ray_Report_note.pdf\",\n" +
               "                        \"file_type\": \"pdf\",\n" +
               "                        \"file_url\": \"https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf\",\n" +
               "                        \"created_at\": \"2019-01-10T04:53:59\"\n" +
               "                    }\n" +
               "                ]\n" +
               "        },\n" +
               "        {\n" +
               "            \"referral_id\": \"5\",\n" +
               "            \"schedule_status\": \"Confirmed\",\n" +
               "            \"appointment_date\": \"2019-05-20T08:53:59\",\n" +
               "            \"created_at\": \"2019-05-20T08:53:59\",\n" +
               "            \"provider\": {\n" +
               "                \"first_name\": \"Kim\",\n" +
               "                \"middle_name\": \"Sewa\",\n" +
               "                \"last_name\": \"Cook\",\n" +
               "                \"profile_photo\": \"p5.png\",\n" +
               "                \"speciality\": \"Otorhinolaryngology\",\n" +
               "                \"office\": {\n" +
               "                    \"id\": 123,\n" +
               "                    \"address1\": \"1711 E Hallandale Beach Blvd\",\n" +
               "                    \"address2\": \"Ste 200\",\n" +
               "                    \"city\": \"Hallandale Beach\",\n" +
               "                    \"state\": \"Florida\",\n" +
               "                    \"postal_code\": \"33009\",\n" +
               "                    \"country\": \"USA\",\n" +
               "                    \"phone_number\": \"9546883968\",\n" +
               "                    \"longitude\": -118.4129786,\n" +
               "                    \"latitude\": 34.1573076,\n" +
               "                    \"organization_name\": \"USA Vein Clinics\",\n" +
               "                    \"org_npi\": 0,\n" +
               "                    \"org_location_id\": 0,\n" +
               "                    \"about_Office\": \"Long Island Jewish Medical Center is one of the cornerstones of Northwell Health, providing the full\"\n" +
               "                }\n" +
               "            },\n" +
               "                \"files\": [\n" +
               "                    {\n" +
               "                        \"file_name\": \"reportCard_Photo.png\",\n" +
               "                        \"file_type\": \"png\",\n" +
               "                        \"file_url\": \"https://imgct2.aeplcdn.com/img/800x600/car-data/big/honda-amaze-image-12749.png\",\n" +
               "                        \"created_at\": \"2019-02-05T08:53:59\"\n" +
               "                    }\n" +
               "                ]\n" +
               "        }\n" +
               "    ]\n" +
               "}";
       data=new Gson().fromJson(json,AppointmentResponce.class);
       return data;
   }
    public static AppointmentResponce getHistoryData(){
        AppointmentResponce data;
       String json="{\n" +
               "    \"response\": [\n" +
               "        {\n" +
               "            \"referral_id\": \"1\",\n" +
               "            \"schedule_status\": \"Missed\",\n" +
               "            \"appointment_date\": \"2019-04-21T09:53:59\",\n" +
               "            \"created_at\": \"2019-03-02T01:53:59\",\n" +
               "            \"provider\": {\n" +
               "                \"first_name\": \"Ethan\",\n" +
               "                \"middle_name\": \"A\",\n" +
               "                \"last_name\": \"Hunt\",\n" +
               "                \"profile_photo\": \"p7.png\",\n" +
               "                \"speciality\": \"Otorhinolaryngology\",\n" +
               "                \"office\": {\n" +
               "                    \"id\": 123,\n" +
               "                    \"address1\": \"12840 Riverside Dr,\",\n" +
               "                    \"address2\": \"Ste 300\",\n" +
               "                    \"city\": \"Valley Village\",\n" +
               "                    \"state\": \"California\",\n" +
               "                    \"postal_code\": \"91607\",\n" +
               "                    \"country\": \"USA\",\n" +
               "                    \"longitude\": -118.4129786,\n" +
               "                    \"latitude\": 34.1573076,\n" +
               "                    \"organization_name\": \"USA Vein Clinics\",\n" +
               "                    \"phone_number\": \"3237981800\",\n" +
               "                    \"org_npi\": 0,\n" +
               "                    \"org_location_id\": 0,\n" +
               "                    \"about_Office\": \"Long Island Jewish Medical Center is one of the cornerstones of Northwell Health, providing the full\"\n" +
               "                }\n" +
               "            },\n" +
               "      \"files\": [\n" +
               "                    {\n" +
               "                        \"file_name\": \"image01.png\",\n" +
               "                        \"file_type\": \"png\",\n" +
               "                        \"file_url\": \"http://www.gettyimages.ca/gi-resources/images/Embed/new/embed2.jpg\",\n" +
               "                        \"created_at\": \"2019-02-24T05:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"image02.jpg\",\n" +
               "                        \"file_type\": \"jpg\",\n" +
               "                        \"file_url\": \"https://ak.picdn.net/assets/cms/97e1dd3f8a3ecb81356fe754a1a113f31b6dbfd4-stock-photo-photo-of-a-common-kingfisher-alcedo-atthis-adult-male-perched-on-a-lichen-covered-branch-107647640.jpg\",\n" +
               "                        \"created_at\": \"2019-02-02T03:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"X_ray_Report_note.pdf\",\n" +
               "                        \"file_type\": \"pdf\",\n" +
               "                        \"file_url\": \"https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf\",\n" +
               "                        \"created_at\": \"2019-01-10T04:53:59\"\n" +
               "                    }\n" +
               "                ]\n" +
               "        },\n" +
               "        {\n" +
               "            \"referral_id\": \"2\",\n" +
               "            \"schedule_status\": \"Visited\",\n" +
               "            \"appointment_date\": \"2019-04-24T01:53:59\",\n" +
               "            \"created_at\": \"2019-03-02T01:53:59\",\n" +
               "            \"provider\": {\n" +
               "                \"first_name\": \"Thomas\",\n" +
               "                \"middle_name\": \"B\",\n" +
               "                \"last_name\": \"Downey\",\n" +
               "                \"profile_photo\": \"p8.png\",\n" +
               "                \"speciality\": \"Dentistry\",\n" +
               "                \"office\": {\n" +
               "                    \"id\": 123,\n" +
               "                    \"address1\": \"1711 E Hallandale Beach Blvd\",\n" +
               "                    \"address2\": \"Ste 200\",\n" +
               "                    \"city\": \"Hallandale Beach\",\n" +
               "                    \"state\": \"Florida\",\n" +
               "                    \"postal_code\": \"33009\",\n" +
               "                    \"country\": \"USA\",\n" +
               "                    \"phone_number\": \"9546883968\",\n" +
               "                    \"longitude\": -118.4129786,\n" +
               "                    \"latitude\": 34.1573076,\n" +
               "                    \"organization_name\": \"USA Vein Clinics\",\n" +
               "                    \"org_npi\": 0,\n" +
               "                    \"org_location_id\": 0,\n" +
               "                    \"about_Office\": \"Long Island Jewish Medical Center is one of the cornerstones of Northwell Health, providing the full\"\n" +
               "                }\n" +
               "              \n" +
               "            },\n" +
               "\"files\": [\n" +
               "                    {\n" +
               "                        \"file_name\": \"image01.png\",\n" +
               "                        \"file_type\": \"png\",\n" +
               "                        \"file_url\": \"http://www.gettyimages.ca/gi-resources/images/Embed/new/embed2.jpg\",\n" +
               "                        \"created_at\": \"2019-02-24T05:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"image02.jpg\",\n" +
               "                        \"file_type\": \"jpg\",\n" +
               "                        \"file_url\": \"https://ak.picdn.net/assets/cms/97e1dd3f8a3ecb81356fe754a1a113f31b6dbfd4-stock-photo-photo-of-a-common-kingfisher-alcedo-atthis-adult-male-perched-on-a-lichen-covered-branch-107647640.jpg\",\n" +
               "                        \"created_at\": \"2019-02-02T03:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"X_ray_Report_note.pdf\",\n" +
               "                        \"file_type\": \"pdf\",\n" +
               "                        \"file_url\": \"https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf\",\n" +
               "                        \"created_at\": \"2019-01-10T04:53:59\"\n" +
               "                    }\n" +
               "                ]\n" +
               "        },\n" +
               "        {\n" +
               "            \"referral_id\": \"3\",\n" +
               "            \"schedule_status\": \"Missed\",\n" +
               "            \"appointment_date\": \"2019-03-02T01:53:59\",\n" +
               "            \"created_at\": \"2019-03-02T01:53:59\",\n" +
               "            \"provider\": {\n" +
               "                \"first_name\": \"Kim\",\n" +
               "                \"middle_name\": \"Sewa\",\n" +
               "                \"last_name\": \"Cook\",\n" +
               "                \"profile_photo\": \"p9.png\",\n" +
               "                \"speciality\": \"Radiation Oncology\",\n" +
               "                \"office\": {\n" +
               "                    \"id\": 123,\n" +
               "                    \"address1\": \"12840 Riverside Dr\",\n" +
               "                    \"address2\": \"Ste 300\",\n" +
               "                    \"city\": \"Valley Village\",\n" +
               "                    \"state\": \"California\",\n" +
               "                    \"postal_code\": \"91607\",\n" +
               "                    \"country\": \"USA\",\n" +
               "                    \"longitude\": -118.4129786,\n" +
               "                    \"latitude\": 34.1573076,\n" +
               "                    \"organization_name\": \"USA Vein Clinics\",\n" +
               "                    \"phone_number\": \"3237981800\",\n" +
               "                    \"org_npi\": 0,\n" +
               "                    \"org_location_id\": 0,\n" +
               "                    \"about_Office\": \"Long Island Jewish Medical Center is one of the cornerstones of Northwell Health, providing the full\"\n" +
               "                }\n" +
               "               \n" +
               "            },\n" +
               "\"files\": [\n" +
               "                    {\n" +
               "                        \"file_name\": \"image01.png\",\n" +
               "                        \"file_type\": \"png\",\n" +
               "                        \"file_url\": \"http://www.gettyimages.ca/gi-resources/images/Embed/new/embed2.jpg\",\n" +
               "                        \"created_at\": \"2019-02-24T05:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"image02.jpg\",\n" +
               "                        \"file_type\": \"jpg\",\n" +
               "                        \"file_url\": \"https://ak.picdn.net/assets/cms/97e1dd3f8a3ecb81356fe754a1a113f31b6dbfd4-stock-photo-photo-of-a-common-kingfisher-alcedo-atthis-adult-male-perched-on-a-lichen-covered-branch-107647640.jpg\",\n" +
               "                        \"created_at\": \"2019-02-02T03:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"X_ray_Report_note.pdf\",\n" +
               "                        \"file_type\": \"pdf\",\n" +
               "                        \"file_url\": \"https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf\",\n" +
               "                        \"created_at\": \"2019-01-10T04:53:59\"\n" +
               "                    }\n" +
               "                ]\n" +
               "\n" +
               "        },\n" +
               "        {\n" +
               "            \"referral_id\": \"4\",\n" +
               "            \"schedule_status\": \"Visited\",\n" +
               "            \"appointment_date\": \"2019-02-15T010:53:59\",\n" +
               "            \"created_at\": \"2019-03-02T01:53:59\",\n" +
               "            \"provider\": {\n" +
               "                \"first_name\": \"Devid\",\n" +
               "                \"middle_name\": \"Lorwes\",\n" +
               "                \"last_name\": \"Helloys\",\n" +
               "                \"profile_photo\": \"p4.png\",\n" +
               "                \"speciality\": \"Neurology\",\n" +
               "                \"office\": {\n" +
               "                    \"id\": 123,\n" +
               "                    \"address1\": \"12840 Riverside Dr\",\n" +
               "                    \"address2\": \"Ste 300\",\n" +
               "                    \"city\": \"Valley Village\",\n" +
               "                    \"state\": \"California\",\n" +
               "                    \"postal_code\": \"91607\",\n" +
               "                    \"country\": \"USA\",\n" +
               "                    \"longitude\": -118.4129786,\n" +
               "                    \"latitude\": 34.1573076,\n" +
               "                    \"organization_name\": \"USA Vein Clinics\",\n" +
               "                    \"phone_number\": \"3237981800\",\n" +
               "                    \"org_npi\": 0,\n" +
               "                    \"org_location_id\": 0,\n" +
               "                    \"about_Office\": \"Long Island Jewish Medical Center is one of the cornerstones of Northwell Health, providing the full\"\n" +
               "                }\n" +
               "            },\n" +
               "                \"files\": [\n" +
               "                    {\n" +
               "                        \"file_name\": \"image02.jpg\",\n" +
               "                        \"file_type\": \"jpg\",\n" +
               "                        \"file_url\": \"https://ak.picdn.net/assets/cms/97e1dd3f8a3ecb81356fe754a1a113f31b6dbfd4-stock-photo-photo-of-a-common-kingfisher-alcedo-atthis-adult-male-perched-on-a-lichen-covered-branch-107647640.jpg\",\n" +
               "                        \"created_at\": \"2019-02-02T03:53:59\"\n" +
               "                    },\n" +
               "                    {\n" +
               "                        \"file_name\": \"X_ray_Report_note.pdf\",\n" +
               "                        \"file_type\": \"pdf\",\n" +
               "                        \"file_url\": \"https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf\",\n" +
               "                        \"created_at\": \"2019-01-10T04:53:59\"\n" +
               "                    }\n" +
               "                ]\n" +
               "        },\n" +
               "        {\n" +
               "            \"referral_id\": \"5\",\n" +
               "            \"schedule_status\": \"Missed\",\n" +
               "            \"appointment_date\": \"2019-05-20T08:53:59\",\n" +
               "            \"created_at\": \"2019-05-20T08:53:59\",\n" +
               "            \"provider\": {\n" +
               "                \"first_name\": \"Kim\",\n" +
               "                \"middle_name\": \"Sewa\",\n" +
               "                \"last_name\": \"Cook\",\n" +
               "                \"profile_photo\": \"p5.png\",\n" +
               "                \"speciality\": \"Otorhinolaryngology\",\n" +
               "                \"office\": {\n" +
               "                    \"id\": 123,\n" +
               "                    \"address1\": \"1711 E Hallandale Beach Blvd\",\n" +
               "                    \"address2\": \"Ste 200\",\n" +
               "                    \"city\": \"Hallandale Beach\",\n" +
               "                    \"state\": \"Florida\",\n" +
               "                    \"postal_code\": \"33009\",\n" +
               "                    \"country\": \"USA\",\n" +
               "                    \"phone_number\": \"9546883968\",\n" +
               "                    \"longitude\": -118.4129786,\n" +
               "                    \"latitude\": 34.1573076,\n" +
               "                    \"organization_name\": \"USA Vein Clinics\",\n" +
               "                    \"org_npi\": 0,\n" +
               "                    \"org_location_id\": 0,\n" +
               "                    \"about_Office\": \"Long Island Jewish Medical Center is one of the cornerstones of Northwell Health, providing the full\"\n" +
               "                }\n" +
               "            },\n" +
               "                \"files\": [\n" +
               "                    {\n" +
               "                        \"file_name\": \"reportCard_Photo.png\",\n" +
               "                        \"file_type\": \"png\",\n" +
               "                        \"file_url\": \"https://imgct2.aeplcdn.com/img/800x600/car-data/big/honda-amaze-image-12749.png\",\n" +
               "                        \"created_at\": \"2019-02-05T08:53:59\"\n" +
               "                    }\n" +
               "                ]\n" +
               "        }\n" +
               "    ]\n" +
               "}";
        data=new Gson().fromJson(json,AppointmentResponce.class);
        return data;
    }

    public static FilesData getFilesData(){
        FilesData filesData;
      String json="{\n" +
              "    \"response\": [\n" +
              "        {\n" +
              "            \"file_name\": \"image01.png\",\n" +
              "            \"file_type\": \"png\",\n" +
              "            \"file_url\": \"http://www.gettyimages.ca/gi-resources/images/Embed/new/embed2.jpg\",\n" +
              "            \"created_at\": \"2019-02-24T05:53:59\",\n" +
              "            \"provider\": {\n" +
              "                \"first_name\": \"Ethan\",\n" +
              "                \"middle_name\": \"A\",\n" +
              "                \"last_name\": \"Hunt\",\n" +
              "                \"profile_photo\": \"avtar.jpeg\"\n" +
              "            }\n" +
              "        },\n" +
              "        {\n" +
              "            \"file_name\": \"image02.jpg\",\n" +
              "            \"file_type\": \"jpg\",\n" +
              "            \"file_url\": \"https://ak.picdn.net/assets/cms/97e1dd3f8a3ecb81356fe754a1a113f31b6dbfd4-stock-photo-photo-of-a-common-kingfisher-alcedo-atthis-adult-male-perched-on-a-lichen-covered-branch-107647640.jpg\",\n" +
              "            \"created_at\": \"2019-02-02T03:53:59\",\n" +
              "            \"provider\": {\n" +
              "                \"first_name\": \"Ethan\",\n" +
              "                \"middle_name\": \"\",\n" +
              "                \"last_name\": \"Hunt\",\n" +
              "                \"profile_photo\": \"avtar.jpeg\"\n" +
              "            }\n" +
              "        },\n" +
              "        {\n" +
              "            \"file_name\": \"X_ray_Report_note.pdf\",\n" +
              "            \"file_type\": \"pdf\",\n" +
              "            \"file_url\": \"https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf\",\n" +
              "            \"created_at\": \"2019-01-10T04:53:59\",\n" +
              "            \"provider\": {\n" +
              "                \"first_name\": \"Ethan\",\n" +
              "                \"middle_name\": \"A\",\n" +
              "                \"last_name\": \"Hunt\",\n" +
              "                \"profile_photo\": \"avtar.jpeg\"\n" +
              "            }\n" +
              "        },\n" +
              "        {\n" +
              "            \"file_name\": \"reportCard_Photo.png\",\n" +
              "            \"file_type\": \"png\",\n" +
              "            \"file_url\": \"https://imgct2.aeplcdn.com/img/800x600/car-data/big/honda-amaze-image-12749.png\",\n" +
              "            \"created_at\": \"2019-02-05T08:53:59\",\n" +
              "            \"provider\": {\n" +
              "                \"first_name\": \"Ethan\",\n" +
              "                \"middle_name\": \"A\",\n" +
              "                \"last_name\": \"Hunt\",\n" +
              "                \"profile_photo\": \"avtar.jpeg\"\n" +
              "            }\n" +
              "        },\n" +
              "        {\n" +
              "            \"file_name\": \"Visit_note.pdf\",\n" +
              "            \"file_type\": \"pdf\",\n" +
              "            \"file_url\": \"https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf\",\n" +
              "            \"created_at\": \"2019-05-18T03:53:59\",\n" +
              "            \"provider\": {\n" +
              "                \"first_name\": \"Ethan\",\n" +
              "                \"middle_name\": \"A\",\n" +
              "                \"last_name\": \"Hunt\",\n" +
              "                \"profile_photo\": \"avtar.jpeg\"\n" +
              "            }\n" +
              "        },\n" +
              "        {\n" +
              "            \"file_name\": \"Visit_note_ReportCard.pdf\",\n" +
              "            \"file_type\": \"pdf\",\n" +
              "            \"file_url\": \"https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf\",\n" +
              "            \"created_at\": \"2019-04-24T06:53:59\",\n" +
              "            \"provider\": {\n" +
              "                \"first_name\": \"Ethan\",\n" +
              "                \"middle_name\": \"A\",\n" +
              "                \"last_name\": \"Hunt\",\n" +
              "                \"profile_photo\": \"avtar.jpeg\"\n" +
              "            }\n" +
              "        }\n" +
              "        \n" +
              "    ]\n" +
              "}";

        filesData= Helper.getGsonInstance().fromJson(json,FilesData.class);
        return filesData;

    }


}
