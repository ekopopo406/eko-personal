package com.ibm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

public class yamlTest {
	private YAMLFactory factory = new YAMLFactory();

	public void test(String jsonString) {
		try {
			ObjectMapper mapper = new ObjectMapper(factory);
			JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
			
			
			Map<String, Object> map1 = new HashMap<String, Object>();
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("name", 123);
			map1.put("eko", map2);
			//convert map to json string
			String json = JSONObject.parseObject(JSON.toJSONString(map1)).toString();
			//read map`s json string to json node format
			ObjectNode objectNode = (ObjectNode) jsonNodeTree;
			//put new node to main yaml structure
			objectNode.put("testkey", mapper.readTree(json));
			//read main structure
			String result = mapper.writeValueAsString(jsonNodeTree);
			factory.setCodec(mapper);
			//write file
			YAMLGenerator generator = factory.createGenerator(new FileOutputStream("D:/user.yml"), JsonEncoding.UTF8);
			//beautiful format
			generator.useDefaultPrettyPrinter();
			//write new jsonnode to target file
			generator.writeObject(jsonNodeTree);

			System.out.println(result);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String yamlToJson(String file) {
		JSONObject gs = new JSONObject();
		Map<String, Object> loaded = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			Yaml yaml = new Yaml();
			loaded = (Map<String, Object>) yaml.load(fis);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		return gs.toJSONString(loaded);
	}

	public static void main(String[] args) {
		URL u = yamlTest.class.getResource("application.yml");

		new yamlTest().test(yamlToJson(u.getPath()));
		// try {
		// //createYaml("D:/123321.yml",yamlToJson(u.getPath()));
		// } catch (JsonProcessingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}