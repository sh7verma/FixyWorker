package com.app.fixy_worker.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class RequestModel implements Parcelable {

    /**
     * response : [{"id":"9","worker_id":"6","user_id":"16","original_price":"","offer_percentage":"","offer_period":"","offer_description":null,"category_name":"Beauty","category_pic":"","request_price":"1","fullname":"hsgh","address":"hsgh","additional_notes":"gfhf","request_status":"0","email":"dsfgsd@ds.fh","request_otp":"1111","accepted_time":"","completed_time":"","created_at":"2018-10-08 07:10:59","average_rating":"5","profile_pic":""},{"id":"9","worker_id":"6","user_id":"16","original_price":"","offer_percentage":"","offer_period":"","offer_description":null,"category_name":"Beauty","category_pic":"","request_price":"1","fullname":"hsgh","address":"hsgh","additional_notes":"gfhf","request_status":"0","email":"dsfgsd@ds.fh","request_otp":"1111","accepted_time":"","completed_time":"","created_at":"2018-10-08 07:10:59","average_rating":"5","profile_pic":""}]
     * message : Incomming request.
     * code : 200
     */
    public static RequestModel instance;

    public static RequestModel getInstance() {
        if (instance == null){
            instance = new RequestModel();
        }
        return instance;
    }

    public static void setInstance(RequestModel insta ) {
        instance = insta;
    }

    private String message;
    private int code;
    private List<ResponseBean> response;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public static class ResponseBean implements Parcelable {
        /**
         * id : 9
         * worker_id : 6
         * user_id : 16
         * original_price :
         * offer_percentage :
         * offer_period :
         * offer_description : null
         * category_name : Beauty
         * category_pic :
         * request_price : 1
         * fullname : hsgh
         * address : hsgh
         * additional_notes : gfhf
         * request_status : 0
         * email : dsfgsd@ds.fh
         * request_otp : 1111
         * accepted_time :
         * completed_time :
         * created_at : 2018-10-08 07:10:59
         * average_rating : 5
         * profile_pic :
         */

        private String id;
        private String worker_id;
        private String user_id;
        private String original_price;
        private String offer_percentage;
        private String offer_period;
        private String offer_description;
        private String category_name;
        private String category_pic;
        private String request_price;
        private String fullname;
        private String address;
        private String additional_notes;
        private String request_status;
        private String email;
        private String request_otp;
        private String accepted_time;
        private String completed_time;
        private String created_at;
        private String average_rating;
        private String profile_pic;
        private String remainingTime;

        public String getRemainingTime() {
            return remainingTime;
        }

        public void setRemainingTime(String remainingTime) {
            this.remainingTime = remainingTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWorker_id() {
            return worker_id;
        }

        public void setWorker_id(String worker_id) {
            this.worker_id = worker_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public String getOffer_percentage() {
            return offer_percentage;
        }

        public void setOffer_percentage(String offer_percentage) {
            this.offer_percentage = offer_percentage;
        }

        public String getOffer_period() {
            return offer_period;
        }

        public void setOffer_period(String offer_period) {
            this.offer_period = offer_period;
        }

        public String getOffer_description() {
            return offer_description;
        }

        public void setOffer_description(String offer_description) {
            this.offer_description = offer_description;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getCategory_pic() {
            return category_pic;
        }

        public void setCategory_pic(String category_pic) {
            this.category_pic = category_pic;
        }

        public String getRequest_price() {
            return request_price;
        }

        public void setRequest_price(String request_price) {
            this.request_price = request_price;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAdditional_notes() {
            return additional_notes;
        }

        public void setAdditional_notes(String additional_notes) {
            this.additional_notes = additional_notes;
        }

        public String getRequest_status() {
            return request_status;
        }

        public void setRequest_status(String request_status) {
            this.request_status = request_status;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRequest_otp() {
            return request_otp;
        }

        public void setRequest_otp(String request_otp) {
            this.request_otp = request_otp;
        }

        public String getAccepted_time() {
            return accepted_time;
        }

        public void setAccepted_time(String accepted_time) {
            this.accepted_time = accepted_time;
        }

        public String getCompleted_time() {
            return completed_time;
        }

        public void setCompleted_time(String completed_time) {
            this.completed_time = completed_time;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getAverage_rating() {
            return average_rating;
        }

        public void setAverage_rating(String average_rating) {
            this.average_rating = average_rating;
        }

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.worker_id);
            dest.writeString(this.user_id);
            dest.writeString(this.original_price);
            dest.writeString(this.offer_percentage);
            dest.writeString(this.offer_period);
            dest.writeString(this.offer_description);
            dest.writeString(this.category_name);
            dest.writeString(this.category_pic);
            dest.writeString(this.request_price);
            dest.writeString(this.fullname);
            dest.writeString(this.address);
            dest.writeString(this.additional_notes);
            dest.writeString(this.request_status);
            dest.writeString(this.email);
            dest.writeString(this.request_otp);
            dest.writeString(this.accepted_time);
            dest.writeString(this.completed_time);
            dest.writeString(this.created_at);
            dest.writeString(this.average_rating);
            dest.writeString(this.profile_pic);
        }

        public ResponseBean() {
        }

        protected ResponseBean(Parcel in) {
            this.id = in.readString();
            this.worker_id = in.readString();
            this.user_id = in.readString();
            this.original_price = in.readString();
            this.offer_percentage = in.readString();
            this.offer_period = in.readString();
            this.offer_description = in.readString();
            this.category_name = in.readString();
            this.category_pic = in.readString();
            this.request_price = in.readString();
            this.fullname = in.readString();
            this.address = in.readString();
            this.additional_notes = in.readString();
            this.request_status = in.readString();
            this.email = in.readString();
            this.request_otp = in.readString();
            this.accepted_time = in.readString();
            this.completed_time = in.readString();
            this.created_at = in.readString();
            this.average_rating = in.readString();
            this.profile_pic = in.readString();
        }

        public static final Creator<ResponseBean> CREATOR = new Creator<ResponseBean>() {
            @Override
            public ResponseBean createFromParcel(Parcel source) {
                return new ResponseBean(source);
            }

            @Override
            public ResponseBean[] newArray(int size) {
                return new ResponseBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeInt(this.code);
        dest.writeList(this.response);
    }

    public RequestModel() {
    }

    protected RequestModel(Parcel in) {
        this.message = in.readString();
        this.code = in.readInt();
        this.response = new ArrayList<ResponseBean>();
        in.readList(this.response, ResponseBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<RequestModel> CREATOR = new Parcelable.Creator<RequestModel>() {
        @Override
        public RequestModel createFromParcel(Parcel source) {
            return new RequestModel(source);
        }

        @Override
        public RequestModel[] newArray(int size) {
            return new RequestModel[size];
        }
    };
}
