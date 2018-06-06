package com.ailk.mobile.config;

import com.ailk.common.config.XMLConfig;
import com.ailk.mobile.util.MobileUtility;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class InterfaceConfig extends AbstractConfig {
	private static final String DATA_CONFIG_FILE = "server-interface.xml";
	private static final String DATA_PATH = "url";
	private static final String ATTR_NAME = "name";
	private static final String ATTR_VERIFY = "verify";
	private static InterfaceConfig config;

	private InterfaceConfig() throws Exception {
	}

	protected Map<String, ?> loadConfig() throws Exception {
		Element root = getRoot(DATA_CONFIG_FILE);
		List<Node> nodes = XMLConfig.getNodes(root, "action");
		String serverUrl = MobileConfig.getValue("serverUrl");
		Map<String, Map<String, String>> datasMap = new HashMap();
		for (Node node : nodes) {
			String name = ((Element) node).attributeValue(ATTR_NAME);
			Map<String, String> dataMap = new HashMap();
			dataMap.put(DATA_PATH, serverUrl+((Element) node).attributeValue(DATA_PATH));
			dataMap.put(ATTR_VERIFY, ((Element) node).attributeValue(ATTR_VERIFY));
			datasMap.put(name, dataMap);
		}

		return datasMap;
	}

	protected static InterfaceConfig getInstance() throws Exception {
		if (config == null) {
			config = new InterfaceConfig();
		}
		return config;
	}

	public static String getUrl(String name) throws Exception {
		return getInstance().getAttrValue(name, DATA_PATH);
	}

	public static boolean isVerify(String name) throws Exception {
		String verify = getInstance().getAttrValue(name, ATTR_VERIFY);
		return !"false".equals(verify);
	}

	private Element getRoot(String file) {
		InputStream in = null;
		try {
			in = InterfaceConfig.class.getClassLoader().getResourceAsStream(file);
			if (in == null) {
				throw new FileNotFoundException();
			}
			SAXReader reader = new SAXReader(false);
			final String encode = MobileConfig.getValue("encode", "UTF-8");
			reader.setEntityResolver(new EntityResolver() {
				public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
					String entityPath = systemId.substring(10);
					InputStream is = InterfaceConfig.class.getClassLoader().getResourceAsStream(entityPath);
					InputSource source = new InputSource(is);
					source.setEncoding(encode);
					return source;
				}

			});
			Element root = reader.read(in).getRootElement();
			return root;
		} catch (Exception e) {
			MobileUtility.error(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}
		}
		return null;
	}
}
