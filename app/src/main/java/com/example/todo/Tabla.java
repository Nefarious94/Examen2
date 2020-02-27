package com.example.todo;

public class Tabla {

    public static String TABLA_TODO="todo";
    public static String CAMPO_NAME="nombre";
    public static String CAMPO_DATE="fecha";
    public static String CAMPO_COMPLETE="completado";


    public static final String CREAR_TABLA_TODO = "CREATE TABLE "+TABLA_TODO+" ("+CAMPO_NAME+" TEXT, "+CAMPO_DATE+" TEXT, "+CAMPO_COMPLETE+" INTEGER)";

}
