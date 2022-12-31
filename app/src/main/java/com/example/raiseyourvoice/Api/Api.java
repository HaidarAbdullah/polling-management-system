package com.example.raiseyourvoice.Api;

import com.example.raiseyourvoice.model.CheckoutRequest;
import com.example.raiseyourvoice.model.CheckoutResult;
import com.example.raiseyourvoice.model.CreatePollingRoomRequest;
import com.example.raiseyourvoice.model.CreatePollingRoomResult;
import com.example.raiseyourvoice.model.HomeResult;
import com.example.raiseyourvoice.model.LoginRequest;
import com.example.raiseyourvoice.model.LoginResult;
import com.example.raiseyourvoice.model.PollingBoxRequest;
import com.example.raiseyourvoice.model.PollingBoxResult;
import com.example.raiseyourvoice.model.RegisterRequestResult;
import com.example.raiseyourvoice.model.SupervisedRoom;
import com.example.raiseyourvoice.model.SupervisedRoomResult;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {
    @POST("/api/register/checkout")
    Call<CheckoutResult> checkout(@Body CheckoutRequest checkoutRequest);

    @POST("/api/login")
    Call<LoginResult> login(@Body LoginRequest loginRequest);

    @Multipart
    @POST("/api/submit_request")
    Call<RegisterRequestResult> submitRegisterRequest(@Part("name") RequestBody name,
                                                      @Part("contact_email") RequestBody contactemail ,
                                                      @Part("registeration_email") RequestBody registerationemail ,
                                                      @Part("password") RequestBody password ,
                                                      @Part("national_number") RequestBody number,
                                                      @Part MultipartBody.Part image);

    @GET("/api/home")
    Call<HomeResult> getRooms(@Header("Authorization") String authToken);

    @POST("/api/create_polling_room")
    Call<CreatePollingRoomResult> createPollingRoom(@Header("Authorization") String authToken,
                                                    @Body CreatePollingRoomRequest createPollingRoomRequest);

    @POST("/api/set_polling_box")
    Call<PollingBoxResult> setPollingBox(@Header("Authorization") String authToken,
                                         @Body PollingBoxRequest pollingBoxRequest);

    @POST("/api/logout")
    Call<RequestBody> logout(@Header("Authorization") String authToken);

    @GET("/api/supervised_rooms")
    Call<SupervisedRoomResult> showSupervisedRooms(@Header("Authorization") String authToken);
}
