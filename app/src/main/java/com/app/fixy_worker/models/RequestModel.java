package com.app.fixy_worker.models;

import java.util.List;

public class RequestModel {

    /**
     * response : {"id":"3","user_id":"12","worker_id":null,"category_id":"1","city_id":"1","address":"sfsfsgfdgsdgfdgdfgdfgsdg","additional_notes":"fdgsdgfdsg","request_price":"44","request_offer":null,"request_otp":"1111","otp_status":"0","request_status":"0","user_coins":"44","worker_coins":null,"cancel_request_charge":null,"accepted_time":null,"onthe_way_time":null,"completed_time":null,"request_latitude":"6436354364.664","request_longitude":"45654363563.564","cancel_by":"0","created_at":"2018-10-06 04:10:48"}
     * message : Create request is successfully.
     * code : 200
     */

    private List<ResponseBean> response;
    private String message;
    private String code;

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class ResponseBean {
        /**
         * id : 3
         * user_id : 12
         * worker_id : null
         * category_id : 1
         * city_id : 1
         * address : sfsfsgfdgsdgfdgdfgdfgsdg
         * additional_notes : fdgsdgfdsg
         * request_price : 44
         * request_offer : null
         * request_otp : 1111
         * otp_status : 0
         * request_status : 0
         * user_coins : 44
         * worker_coins : null
         * cancel_request_charge : null
         * accepted_time : null
         * onthe_way_time : null
         * completed_time : null
         * request_latitude : 6436354364.664
         * request_longitude : 45654363563.564
         * cancel_by : 0
         * created_at : 2018-10-06 04:10:48
         */

        private String id;
        private String user_id;
        private Object worker_id;
        private String category_id;
        private String city_id;
        private String address;
        private String additional_notes;
        private String request_price;
        private Object request_offer;
        private String request_otp;
        private String otp_status;
        private String request_status;
        private String user_coins;
        private Object worker_coins;
        private Object cancel_request_charge;
        private Object accepted_time;
        private Object onthe_way_time;
        private Object completed_time;
        private String request_latitude;
        private String request_longitude;
        private String cancel_by;
        private String created_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public Object getWorker_id() {
            return worker_id;
        }

        public void setWorker_id(Object worker_id) {
            this.worker_id = worker_id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
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

        public String getRequest_price() {
            return request_price;
        }

        public void setRequest_price(String request_price) {
            this.request_price = request_price;
        }

        public Object getRequest_offer() {
            return request_offer;
        }

        public void setRequest_offer(Object request_offer) {
            this.request_offer = request_offer;
        }

        public String getRequest_otp() {
            return request_otp;
        }

        public void setRequest_otp(String request_otp) {
            this.request_otp = request_otp;
        }

        public String getOtp_status() {
            return otp_status;
        }

        public void setOtp_status(String otp_status) {
            this.otp_status = otp_status;
        }

        public String getRequest_status() {
            return request_status;
        }

        public void setRequest_status(String request_status) {
            this.request_status = request_status;
        }

        public String getUser_coins() {
            return user_coins;
        }

        public void setUser_coins(String user_coins) {
            this.user_coins = user_coins;
        }

        public Object getWorker_coins() {
            return worker_coins;
        }

        public void setWorker_coins(Object worker_coins) {
            this.worker_coins = worker_coins;
        }

        public Object getCancel_request_charge() {
            return cancel_request_charge;
        }

        public void setCancel_request_charge(Object cancel_request_charge) {
            this.cancel_request_charge = cancel_request_charge;
        }

        public Object getAccepted_time() {
            return accepted_time;
        }

        public void setAccepted_time(Object accepted_time) {
            this.accepted_time = accepted_time;
        }

        public Object getOnthe_way_time() {
            return onthe_way_time;
        }

        public void setOnthe_way_time(Object onthe_way_time) {
            this.onthe_way_time = onthe_way_time;
        }

        public Object getCompleted_time() {
            return completed_time;
        }

        public void setCompleted_time(Object completed_time) {
            this.completed_time = completed_time;
        }

        public String getRequest_latitude() {
            return request_latitude;
        }

        public void setRequest_latitude(String request_latitude) {
            this.request_latitude = request_latitude;
        }

        public String getRequest_longitude() {
            return request_longitude;
        }

        public void setRequest_longitude(String request_longitude) {
            this.request_longitude = request_longitude;
        }

        public String getCancel_by() {
            return cancel_by;
        }

        public void setCancel_by(String cancel_by) {
            this.cancel_by = cancel_by;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
