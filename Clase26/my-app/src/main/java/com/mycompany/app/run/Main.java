package com.mycompany.app.run;

import static com.mycompany.app.run.ObjectMapperUtils.*;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		objectToJsonString();
		objectToJsonInFile();
		objectToBytes();
		jsonStrToObject();
		jsonInFileToObject();
		readJsonStringAsMap();
		readJsonArrayStringAsList();
		parseJsonStringAsJsonNode();
		createJsonNodeStr();
		demoUnknownField();
		demoNullPrimitiveValues();
		objectWithDateToJsonString();
		jsonStringWithDateToObject();
		demoCustomSerializer();
		demoCustomDeSerializer();
	}
}
