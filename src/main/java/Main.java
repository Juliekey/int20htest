import drawing.ImageProgramWriter;
import entities.TVChannel;
import mapper.JSONChannelMapper;
import org.json.JSONObject;
import json.JsonReader;

import java.util.GregorianCalendar;

/**
 * Created by Yuliya Pedash on 17.02.2017.
 */
public class Main {
    public static void main(String[] args) {
        final String URL = "https://api.ovva.tv/v2/ua/tvguide/1plus1/";
        JsonReader jsonReader = new JsonReader(URL);
        JSONObject jsonObject = jsonReader.readJson();
        JSONChannelMapper jsonChannelMapper = new JSONChannelMapper(jsonObject);
        TVChannel tvChannel = jsonChannelMapper.map();
        tvChannel.setName("1+1");
        ImageProgramWriter programWriter = new ImageProgramWriter(tvChannel);
        programWriter.writeAll();
        programWriter.saveImage();
    }
}
