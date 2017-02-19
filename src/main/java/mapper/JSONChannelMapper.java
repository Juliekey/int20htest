package mapper;

import entities.TVChannel;
import entities.TVProgram;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuliya Pedash on 18.02.2017.
 */
public class JSONChannelMapper {
    JSONObject jsonObject;
    private static final String DATA = "data";
    private static final String PROGRAMS = "programs";
    private static final String DATE = "date";

    public JSONChannelMapper(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public TVChannel map() {
        TVChannel tvChannel = new TVChannel();
        JSONObject channelJSONObject = (JSONObject) jsonObject.getJSONObject(DATA);
        JSONArray programs = channelJSONObject.getJSONArray(PROGRAMS);
        List<TVProgram> programList = new ArrayList<>();
        tvChannel.setDate(channelJSONObject.getString(DATE));
        for (int i = 0; i < programs.length(); i++) {
            JSONProgramMapper jsonProgramMapper = new JSONProgramMapper((JSONObject) programs.get(i));
            programList.add(jsonProgramMapper.getTVProgram());
        }
        tvChannel.setPrograms(programList);
        return tvChannel;


    }
}
