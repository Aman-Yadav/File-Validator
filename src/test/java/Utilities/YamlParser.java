package Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class YamlParser {
    public static Map<String, ColumnRule> parseYaml(String yamlPath) throws IOException {


        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(new File(yamlPath), mapper.getTypeFactory().constructMapType(Map.class, String.class, ColumnRule.class));


    }
}
