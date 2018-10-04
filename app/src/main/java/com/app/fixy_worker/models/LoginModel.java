package com.app.fixy_worker.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LoginModel extends BaseModel implements Parcelable {


    /**
     * response : {"id":"8","fullname":"Free","country_code":"+91","phone_number":"99900008888","access_token":"rwkjQouMyImWAbRpTcYxlsf9nz5N3JZvVgd4EBi62ODh1tUP80","gender":"0","email":"frr@gmail.com","user_type":"2","profile_pic":"http://fixyworker.com/fixyworker/uploads/657e9c92a034eb76a9bbc8c54325216b.jpg","refferal_code":"MQW43318","profile_status":"4","coins":"100","refferal_coins":"40","city":"Chandigarh","city_id":"1","number_verify":"1","average_rating":"5","selected_services":[{"id":"7","category_name":"water pump","category_pic":"","description":"qwewqr","category_price":"25"},{"id":"6","category_name":"Waxing","category_pic":"","description":"Waxingn zxcmsdmfvbsdm","category_price":"10"},{"id":"5","category_name":"Body message","category_pic":"","description":"wetqertegbr","category_price":"20"}]}
     * message : Update profile is successfully.
     * code : 200
     */

    private ResponseBean response;
    private String message;
    private int code;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

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

    public static class ResponseBean implements Parcelable {
        /**
         * id : 8
         * fullname : Free
         * country_code : +91
         * phone_number : 99900008888
         * access_token : rwkjQouMyImWAbRpTcYxlsf9nz5N3JZvVgd4EBi62ODh1tUP80
         * gender : 0
         * email : frr@gmail.com
         * user_type : 2
         * profile_pic : http://fixyworker.com/fixyworker/uploads/657e9c92a034eb76a9bbc8c54325216b.jpg
         * refferal_code : MQW43318
         * profile_status : 4
         * coins : 100
         * refferal_coins : 40
         * city : Chandigarh
         * city_id : 1
         * number_verify : 1
         * average_rating : 5
         * selected_services : [{"id":"7","category_name":"water pump","category_pic":"","description":"qwewqr","category_price":"25"},{"id":"6","category_name":"Waxing","category_pic":"","description":"Waxingn zxcmsdmfvbsdm","category_price":"10"},{"id":"5","category_name":"Body message","category_pic":"","description":"wetqertegbr","category_price":"20"}]
         */

        private String id;
        private String fullname;
        private String country_code;
        private String phone_number;
        private String access_token;
        private String gender;
        private String email;
        private String user_type;
        private String profile_pic;
        private String refferal_code;
        private String profile_status;
        private String coins;
        private String refferal_coins;
        private String city;
        private String city_id;
        private String number_verify;
        private String average_rating;
        private List<SelectedServicesBean> selected_services;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        public String getRefferal_code() {
            return refferal_code;
        }

        public void setRefferal_code(String refferal_code) {
            this.refferal_code = refferal_code;
        }

        public String getProfile_status() {
            return profile_status;
        }

        public void setProfile_status(String profile_status) {
            this.profile_status = profile_status;
        }

        public String getCoins() {
            return coins;
        }

        public void setCoins(String coins) {
            this.coins = coins;
        }

        public String getRefferal_coins() {
            return refferal_coins;
        }

        public void setRefferal_coins(String refferal_coins) {
            this.refferal_coins = refferal_coins;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getNumber_verify() {
            return number_verify;
        }

        public void setNumber_verify(String number_verify) {
            this.number_verify = number_verify;
        }

        public String getAverage_rating() {
            return average_rating;
        }

        public void setAverage_rating(String average_rating) {
            this.average_rating = average_rating;
        }

        public List<SelectedServicesBean> getSelected_services() {
            return selected_services;
        }

        public void setSelected_services(List<SelectedServicesBean> selected_services) {
            this.selected_services = selected_services;
        }

        public static class SelectedServicesBean implements Parcelable {
            /**0
             * id : 7
             * category_name : water pump
             * category_pic :
             * description : qwewqr
             * category_price : 25
             */

            private String id;
            private String category_name;
            private String category_pic;
            private String description;
            private String category_price;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCategory_price() {
                return category_price;
            }

            public void setCategory_price(String category_price) {
                this.category_price = category_price;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.category_name);
                dest.writeString(this.category_pic);
                dest.writeString(this.description);
                dest.writeString(this.category_price);
            }

            public SelectedServicesBean() {
            }

            protected SelectedServicesBean(Parcel in) {
                this.id = in.readString();
                this.category_name = in.readString();
                this.category_pic = in.readString();
                this.description = in.readString();
                this.category_price = in.readString();
            }

            public static final Parcelable.Creator<SelectedServicesBean> CREATOR = new Parcelable.Creator<SelectedServicesBean>() {
                @Override
                public SelectedServicesBean createFromParcel(Parcel source) {
                    return new SelectedServicesBean(source);
                }

                @Override
                public SelectedServicesBean[] newArray(int size) {
                    return new SelectedServicesBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.fullname);
            dest.writeString(this.country_code);
            dest.writeString(this.phone_number);
            dest.writeString(this.access_token);
            dest.writeString(this.gender);
            dest.writeString(this.email);
            dest.writeString(this.user_type);
            dest.writeString(this.profile_pic);
            dest.writeString(this.refferal_code);
            dest.writeString(this.profile_status);
            dest.writeString(this.coins);
            dest.writeString(this.refferal_coins);
            dest.writeString(this.city);
            dest.writeString(this.city_id);
            dest.writeString(this.number_verify);
            dest.writeString(this.average_rating);
            dest.writeTypedList(this.selected_services);
        }

        public ResponseBean() {
        }

        protected ResponseBean(Parcel in) {
            this.id = in.readString();
            this.fullname = in.readString();
            this.country_code = in.readString();
            this.phone_number = in.readString();
            this.access_token = in.readString();
            this.gender = in.readString();
            this.email = in.readString();
            this.user_type = in.readString();
            this.profile_pic = in.readString();
            this.refferal_code = in.readString();
            this.profile_status = in.readString();
            this.coins = in.readString();
            this.refferal_coins = in.readString();
            this.city = in.readString();
            this.city_id = in.readString();
            this.number_verify = in.readString();
            this.average_rating = in.readString();
            this.selected_services = in.createTypedArrayList(SelectedServicesBean.CREATOR);
        }

        public static final Parcelable.Creator<ResponseBean> CREATOR = new Parcelable.Creator<ResponseBean>() {
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
        dest.writeParcelable(this.response, flags);
        dest.writeString(this.message);
        dest.writeInt(this.code);
    }

    public LoginModel() {
    }

    protected LoginModel(Parcel in) {
        this.response = in.readParcelable(ResponseBean.class.getClassLoader());
        this.message = in.readString();
        this.code = in.readInt();
    }

    public static final Parcelable.Creator<LoginModel> CREATOR = new Parcelable.Creator<LoginModel>() {
        @Override
        public LoginModel createFromParcel(Parcel source) {
            return new LoginModel(source);
        }

        @Override
        public LoginModel[] newArray(int size) {
            return new LoginModel[size];
        }
    };
}
