package com.zero.elmotellacanitaalaire;

import android.os.Parcel;
import android.os.Parcelable;
public class usuarios implements Parcelable {
    private String usuario;
    private String pass;
    private String rol;

    public usuarios(String usuario, String pass, String rol) {
        this.usuario = usuario;
        this.pass = pass;
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
    }

    public String getRol() {
        return rol;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(usuario);
        dest.writeString(pass);
        dest.writeString(rol);
    }
    protected usuarios(Parcel in) {
        usuario = in.readString();
        pass = in.readString();
        rol = in.readString();
    }
    public static final Parcelable.Creator<usuarios> CREATOR = new Parcelable.Creator<usuarios>() {
        @Override
        public usuarios createFromParcel(Parcel in) {
            return new usuarios(in);
        }

        @Override
        public usuarios[] newArray(int size) {
            return new usuarios[size];
        }
    };
}
