package mapper;

import entities.TVProgram;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yuliya Pedash on 18.02.2017.
 */
public class JSONProgramMapper {
    private static String PREVIEW = "preview";
    private static String IMAGE = "image";
    private static String TITLE = "title";
    private static String SUBTITLE = "subtitle";
    private static String REAL_BEGIN = "realtime_begin";
    private static String REAL_END = "realtime_end";

    JSONObject jsonObject;

    public JSONProgramMapper(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public TVProgram getTVProgram() {
        TVProgram tvProgram = new TVProgram();
        tvProgram.setImageLink(jsonObject.getJSONObject(IMAGE).getString(PREVIEW));
        tvProgram.setTitle(jsonObject.getString(TITLE));
        tvProgram.setSubtitle(jsonObject.getString(SUBTITLE));
        tvProgram.setRealtimeBegin(new Date(((long) jsonObject.getInt(REAL_BEGIN)) * 1000));
        tvProgram.setRealtimEnd(new Date(((long) jsonObject.getInt(REAL_END)) * 1000));
        return tvProgram;

    }


}
