package tech.kddez.literalura.utils;

public interface IDataConverter {

    <T> T extractData(String json, Class <T> tClass);

}
