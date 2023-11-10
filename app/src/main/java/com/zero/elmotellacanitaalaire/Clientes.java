package com.zero.elmotellacanitaalaire;

import android.os.Parcel;
import android.os.Parcelable;
public class Clientes implements Parcelable {
    int Id;
    double Velocidad;

    boolean Estado;
    double Temperatura;

    String Zona;
    String FechaIngreso;

    public Clientes(int Id,  double Velocidad, double Temperatura, boolean Estado, String Zona, String FechaIngreso) {
        this.Id = Id;
        this.Velocidad = Velocidad;
        this.Temperatura = Temperatura;
        this.Zona = Zona;
        this.FechaIngreso = FechaIngreso;
        this.Estado = Estado;
    }

    // Constructor necesario para Parcelable
    protected Clientes(Parcel in) {
        Id = in.readInt();
        Velocidad = in.readDouble();
        Estado = in.readByte() != 0;
        Temperatura = in.readDouble();

        Zona = in.readString();
        FechaIngreso = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeDouble(Velocidad);
        dest.writeByte((byte) (Estado ? 1 : 0));
        dest.writeDouble(Temperatura);

        dest.writeString(Zona);
        dest.writeString(FechaIngreso);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Clientes> CREATOR = new Creator<Clientes>() {
        @Override
        public Clientes createFromParcel(Parcel in) {
            return new Clientes(in);
        }

        @Override
        public Clientes[] newArray(int size) {
            return new Clientes[size];
        }
    };

    public int getId(){
        return Id;
    }

    public double getVelocidad(){
        return Velocidad;
    }
    public double getTemperatura(){
        return Temperatura;
    }
    public String getZona(){
        return Zona;
    }

    public String getFechaIngreso(){
                            return FechaIngreso;
    }


    public void setEstado(boolean estado) {
        Estado = estado;
    }

    public boolean getEstado(){
        return Estado;
    }
}
