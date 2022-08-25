package com.webapp.recaptcha;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
public class VerifyRecaptcha {
    public static final String URL = "https://www.google.com/recaptcha/api/siteverify";
    public static final String SECRET = "6Lf3M-sgAAAAAAL0pvavnK4SAW1EodBMu5KrukX7";
    private static final String USER_AGENT = "Mozilla/5.0";
    private VerifyRecaptcha(){}
    public static boolean verify(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }
        try{
            URL obj = new URL(URL);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            // add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String postParams = "secret=" + SECRET + "&response="
                    + gRecaptchaResponse;
            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + URL);
            System.out.println("Post parameters : " + postParams);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            System.out.println(response.toString());
            //parse JSON response and return 'success' value
            JsonObject jsonObject;
            try (JsonReader jsonReader = Json.createReader(new StringReader(response.toString()))) {
                jsonObject = jsonReader.readObject();
            }
            return jsonObject.getBoolean("success");
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}