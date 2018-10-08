package com.app.fixy_worker.network;

import com.app.fixy_worker.models.AdsModel;
import com.app.fixy_worker.models.AllServiceModel;
import com.app.fixy_worker.models.CItyModel;
import com.app.fixy_worker.models.GooglePlaceModal;
import com.app.fixy_worker.models.LoginModel;
import com.app.fixy_worker.models.NearbyPlaceModel;
import com.app.fixy_worker.models.RequestModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface ApiInterface {

    //API Method to get Questions From API


    @GET
    public Call<GooglePlaceModal> getGooglePlaces(@Url String url);

    @GET
    public Call<NearbyPlaceModel> getGoogleNearByPlaces(@Url String url);

    @FormUrlEncoded
    @POST("users/create_user")
    Call<LoginModel> create_user(@Field("country_code") String country_code,
                                @Field("phone_number") String phone_number,
                                @Field("user_type") String user_type,
                                @Field("application_mode") String application_mode,
                                @Field("platform_type") String platform_type,
                                @Field("device_token") String device_token
    );

    @FormUrlEncoded
    @POST("users/confirm_otp")
    Call<LoginModel> confirm_otp(@Field("access_token") String access_token,
                                @Field("otp") String otp,
                                @Field("device_token") String device_token);


    @FormUrlEncoded
    @POST("users/city")
    Call<CItyModel> city(@Field("access_token") String access_token, @Field("device_token") String device_token);

    @FormUrlEncoded
    @POST("users/update_city")
    Call<LoginModel> update_city(@Field("access_token") String access_token, @Field("device_token") String device_token,
                                @Field("city") String city,
                                @Field("city_id") String city_id);

    @FormUrlEncoded
    @POST("services")
    Call<AllServiceModel> services(@Field("access_token") String access_token,
                                   @Field("device_token") String device_token,
                                   @Field("city_id") String city_id);

    @Multipart
    @POST("users/create_profile")
    Call<LoginModel> create_profile(@Part("access_token") RequestBody access_token,
                                           @Part("user_type") RequestBody user_type,
                                           @Part("fullname") RequestBody fullname,
                                           @Part("email") RequestBody email,
                                           @Part("gender") RequestBody gender,
                                           @Part("referral_code") RequestBody referral_code,
                                           @Part("device_token") RequestBody device_token,
                                           @Part("city") RequestBody city,
                                           @Part("city_id") RequestBody city_id,
                                           @Part("service_ids") RequestBody service_ids,
                                           @Part("document_type") RequestBody document_type,
                                           @Part("document_name") RequestBody document_name,
                                           @Part("document_number") RequestBody document_number,
                                           @Part MultipartBody.Part profile_pic,
                                           @Part MultipartBody.Part front,
                                           @Part MultipartBody.Part back
    );

    @FormUrlEncoded
    @POST("users/fetch_current_service")
    Call<LoginModel> fetch_current_service(@Field("access_token") String access_token,
                                   @Field("device_token") String device_token);
    @FormUrlEncoded
    @POST("services/add_new_offer")
    Call<AdsModel> add_new_offer(@Field("access_token") String access_token,
                                 @Field("device_token") String device_token,
                                 @Field("category_id") String category_id,
                                 @Field("original_price") String original_price,
                                 @Field("offer_percentage") String offer_percentage ,
                                 @Field("offer_period") String offer_period,
                                 @Field("offer_description") String offer_description
                                   );


    @FormUrlEncoded
    @POST("requests/incomming_request")
    Call<RequestModel> incomming_request(@Field("access_token") String access_token,
                                         @Field("device_token") String device_token);


//    @FormUrlEncoded
//    @POST("/users/signup")
//    Call<SignupModel> userSignup(@Field("email") String email,
//                                 @Field("password") String password,
//                                 @Field("platform_status") String platform_status,
//                                 @Field("user_type") String user_type,
//                                 @Field("device_token") String device_token,
//                                 @Field("facebook_id") String facebook_id,
//                                 @Field("facebook_url") String facebook_url,
//                                 @Field("first_name") String first_name,
//                                 @Field("last_name") String last_name);
//
//    @FormUrlEncoded
//    @POST("/users/signin")
//    Call<SignupModel> userSignin(@Field("email") String email,
//                                 @Field("password") String password,
//                                 @Field("platform_status") String platform_status,
//                                 @Field("device_token") String device_token);
//
//    @Multipart
//    @POST("/users/create_profile")
//    Call<SignupModel> createProfile(@Part("first_name") RequestBody first_name,
//                                    @Part("last_name") RequestBody last_name,
//                                    @Part("country_code") RequestBody country_code,
//                                    @Part("phone_number") RequestBody phone_number,
//                                    @Part("access_token") RequestBody access_token,
//                                    @Part MultipartBody.Part image);
//
//    @FormUrlEncoded
//    @POST("/users/verify")
//    Call<SignupModel> userVerify(@Field("access_token") String access_token,
//                                 @Field("otp") String otp);
//
//    @FormUrlEncoded
//    @POST("/users/call")
//    Call<CallResendModel> callUser(@Field("access_token") String access_token);
//
//    @FormUrlEncoded
//    @POST("/users/resend")
//    Call<CallResendModel> callResend(@Field("access_token") String access_token);
//
//    @FormUrlEncoded
//    @POST("/users/forget_password")
//    Call<CallResendModel> forgetPassword(@Field("email") String email);
//
//    @POST("/tools/categories")
//    Call<CategoriesModel> getCategories();
//
//
//    @Multipart
//    @POST("/users/update_profile")
//    Call<SignupModel> updateProfile(@Part("access_token") RequestBody access_token,
//                                    @Part("first_name") RequestBody first_name,
//                                    @Part("last_name") RequestBody last_name,
//                                    @Part MultipartBody.Part image);
//
//    @FormUrlEncoded
//    @POST("users/view_profile")
//    Call<ProfileModel> viewProfile(@Field("access_token") String access_token);
//
//    @Multipart
//    @POST("payment/stripe_account")
//    Call<CreateStripeAccountModel> stripeBank(@Part("access_token") RequestBody access_token,
//                                              @Part("country_code") RequestBody country_code,
//                                              @Part("currency") RequestBody currency,
//                                              @Part("account_number") RequestBody account_number,
//                                              @Part("first_name") RequestBody first_name,
//                                              @Part("last_name") RequestBody last_name,
//                                              @Part("address") RequestBody address,
//                                              @Part("city") RequestBody city,
//                                              @Part("state") RequestBody state,
//                                              @Part("postal_code") RequestBody postal_code,
//                                              @Part("dob") RequestBody dob,
//                                              @Part("sort_code") RequestBody sort_code,
//                                              @Part MultipartBody.Part document);
//
//    @Multipart
//    @POST("tools/create_tool")
//    Call<CreateTulModel> createTul(@Part("access_token") RequestBody access_token,
//                                   @Part("title") RequestBody title,
//                                   @Part("category_id") RequestBody category_id,
//                                   @Part("description") RequestBody description,
//                                   @Part("quantity") RequestBody quantity,
//                                   @Part("price") RequestBody price,
//                                   @Part("tool_currency") RequestBody tool_currency,
//                                   @Part("additional_price") RequestBody additional_price,
//                                   @Part("tool_address") RequestBody tool_address,
//                                   @Part("latitude") RequestBody latitude,
//                                   @Part("longitude") RequestBody longitude,
//                                   @Part("rules") RequestBody rules,
//                                   @Part("preferences") RequestBody preferences,
//                                   @Part("stripe_account") RequestBody stripe_account,
//                                   @Part List<MultipartBody.Part> files,
//                                   @Part MultipartBody.Part document,
//                                   @Part MultipartBody.Part v_thumbnail);
//
//    @Multipart
//    @POST("add_image")
//    Call<CreateTulModel> demoAPI(@Part List<MultipartBody.Part> files);
//
//    @FormUrlEncoded
//    @POST("tools/view_tool")
//    Call<ViewTulModel> viewTul(@Field("access_token") String access_token,
//                               @Field("tool_id") int tool_id);
//
//    @FormUrlEncoded
//    @POST("tools/delete_tool")
//    Call<DemoModel> deleteTul(@Field("access_token") String access_token,
//                              @Field("tool_id") int tool_id);
//
//
//    @FormUrlEncoded
//    @POST("tools/near_by_tools")
//    Call<NearByTulModel> nearByTools(@Field("latitude") String latitude,
//                                     @Field("longitude") String longitude);
//
//    @FormUrlEncoded
//    @POST("tools/get_landing_tools")
//    Call<NearByTulListingModel> getNearByToolsList(@Field("access_token") String access_token,
//                                                   @Field("tool_ids") String tool_ids);
//
//    @FormUrlEncoded
//    @POST("tools/tools_by_category")
//    Call<NearByTulListingModel> getToolsByCategory(@Field("access_token") String access_token,
//                                                   @Field("category_id") String category_id,
//                                                   @Field("offset") int offset);
//
//    @FormUrlEncoded
//    @POST("users/wishlist")
//    Call<DemoModel> shortListTul(@Field("access_token") String access_token,
//                                 @Field("tool_id") int tool_id);
//
//    @FormUrlEncoded
//    @POST("feedback/report")
//    Call<DemoModel> ReportTul(@Field("access_token") String access_token,
//                              @Field("tool_id") int tool_id);
//
//    @FormUrlEncoded
//    @POST("payment/view_cards")
//    Call<CardModel> viewCard(@Field("access_token") String access_token);
//
//    @FormUrlEncoded
//    @POST("payment/delete_card")
//    Call<DeleteCardModel> deleteCard(@Field("access_token") String access_token, @Field("card_id") Integer card_id);
//
//    @FormUrlEncoded
//    @POST("payment/save_card")
//    Call<CardLocalModel> saveCard(@Field("access_token") String access_token,
//                                  @Field("card_number") String card_number,
//                                  @Field("name_on_card") String name_on_card,
//                                  @Field("expiry_month") Integer expiry_month,
//                                  @Field("expiry_year") Integer expiry_year);
//
//    @FormUrlEncoded
//    @POST("payment/bank_accounts")
//    Call<AccountModel> getAccounts(@Field("access_token") String access_token);
//
//
//    @FormUrlEncoded
//    @POST("payment/delete_bank_account")
//    Call<DemoModel> deleteAccount(@Field("access_token") String access_token,
//                                  @Field("account_id") int account_id);
//
//
//    @FormUrlEncoded
//    @POST("payment/make_account_primary")
//    Call<DemoModel> makePrimaryAccount(@Field("access_token") String access_token,
//                                       @Field("account_id") int account_id);
//
}