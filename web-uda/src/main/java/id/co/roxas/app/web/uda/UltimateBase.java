package id.co.roxas.app.web.uda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;

import id.co.roxas.app.web.uda.config.HttpRestResponse;
import id.co.roxas.app.web.uda.config.HttpSecurityService;
import id.co.roxas.app.web.uda.lib.ParamQueryCustomLib;
import id.co.roxas.data.transfer.object.UserDataActivation.response.PageResponse;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;

/*
 * creator : Bima Satrya Sebayang.
 */

//Please do not change access identifier,
@Component
public class UltimateBase {
	@Value("${roxas.gateway.port-title.uaa}")
	protected String END_POINT_URL;
	@Value("${roxas.user-uda}")
	protected String USER_UDA;
	@Value("${roxas.password-uda}")
	protected String PASSWORD_UDA;
	@Value("${roxas.size-page}")
	protected String SIZE_PAGE;
	@Value("${roxas.grant-type}")
	protected String PASSWORD;
	@Value("${roxas.login-url}")
	protected String LOGIN_URL;
	
	protected static final String DASHBOARD_URL = "/web-uda/master-web-uda-index";
	private List<ParamQueryCustomLib> paramQueryCustomLibs = new ArrayList<>();
	protected String getToken(HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");
		System.err.println("current token : " + token);
		return token;
	}

	protected void lastWantedUrlRequest(String url, HttpServletRequest request) {
		request.getSession().setAttribute("last-url", url);
	}

	protected String lastUrl(HttpServletRequest request) {
		String neededUrl = (String) request.getSession().getAttribute("last-url");
		System.err.println("last result url : " + neededUrl);
		if (neededUrl == null) {
			return DASHBOARD_URL;
		} else {
			return neededUrl;
		}
	}

	protected WsResponse resultWsWithoutSecurity(String url, Object body, HttpMethod method,
			Map<String, String> headerMap, ParamQueryCustomLib... paramQuery) {
		return getResultWs(url, body, method, headerMap, paramQuery);
	}

	protected ParamQueryCustomLib[] retrieveAllPagingNeeded(ParamQueryCustomLib... paramQueryCustomLibs) {
		for (ParamQueryCustomLib paramQueryCustomLib : paramQueryCustomLibs) {
			this.paramQueryCustomLibs.add(paramQueryCustomLib);
		}
		return this.paramQueryCustomLibs.toArray(new ParamQueryCustomLib[this.paramQueryCustomLibs.size()]);
	}

	protected void paramPaging(String page, String size, String search, String defaultSort, String... sort) {
		this.paramQueryCustomLibs = new ArrayList<>();
		if (Strings.isBlank(page)) {
			this.paramQueryCustomLibs.add(new ParamQueryCustomLib("page", "0"));
		} else {
			this.paramQueryCustomLibs.add(new ParamQueryCustomLib("page", page));
		}
		this.paramQueryCustomLibs.add(new ParamQueryCustomLib("size", SIZE_PAGE));
		if (Strings.isBlank(search)) {
			this.paramQueryCustomLibs.add(new ParamQueryCustomLib("search", ""));
		} else {
			this.paramQueryCustomLibs.add(new ParamQueryCustomLib("search", search));
		}

		if (sort.length == 0) {
			this.paramQueryCustomLibs.add(new ParamQueryCustomLib("sort", defaultSort));
		}

		for (String s : sort) {
			this.paramQueryCustomLibs.add(new ParamQueryCustomLib("sort", s));
		}
	}

	protected PageResponse pageResultsWithSecurityAccess(String url, Object body, HttpMethod method,
			Map<String, String> headerMap, String auth, HttpSecurityService httpSecurityService,
			ParamQueryCustomLib... paramQuery) {
		if (headerMap == null) {
			headerMap = new HashMap<>();
		}
		headerMap.put("Authorization", "Bearer ".concat(auth));
		headerMap.put("Content-Type", httpSecurityService.getContentType());
		if (httpSecurityService.getUuidConnectorBody() != null)
			headerMap.put("uuid-connector-body", httpSecurityService.getUuidConnectorBody());
		if (httpSecurityService.getUuidConnectorResponse() != null)
			headerMap.put("uuid-connector-response", httpSecurityService.getUuidConnectorResponse());
		if (httpSecurityService.getModule() != null)
			headerMap.put("module", httpSecurityService.getModule());
		return getResultWsPage(url, body, method, headerMap, paramQuery);
	}

	protected WsResponse resultWsWitSecurityAccess(String url, Object body, HttpMethod method,
			Map<String, String> headerMap, String auth, HttpSecurityService httpSecurityService,
			ParamQueryCustomLib... paramQuery) {
		if (headerMap == null) {
			headerMap = new HashMap<>();
		}
		headerMap.put("Authorization", "Bearer ".concat(auth));
		headerMap.put("Content-Type", httpSecurityService.getContentType());
		if (httpSecurityService.getUuidConnectorBody() != null)
			headerMap.put("uuid-connector-body", httpSecurityService.getUuidConnectorBody());
		if (httpSecurityService.getUuidConnectorResponse() != null)
			headerMap.put("uuid-connector-response", httpSecurityService.getUuidConnectorResponse());
		if (httpSecurityService.getModule() != null)
			headerMap.put("module", httpSecurityService.getModule());
		return getResultWs(url, body, method, headerMap, paramQuery);
	}

	protected String restingToken(String userName, String userPassword) {
		Map<String, Object> mapToken = new HashMap<>();
		Map<String, String> header = new HashMap<>();
		header.put("Authorization",
				"Basic ".concat(Base64.getEncoder().encodeToString((USER_UDA + ":" + PASSWORD_UDA).getBytes())));
		HttpRestResponse httpRestResponse = wsBody(END_POINT_URL + "/oauth/token", null, HttpMethod.POST, header,
				new ParamQueryCustomLib("grant_type", PASSWORD), new ParamQueryCustomLib("username", userName),
				new ParamQueryCustomLib("password", userPassword));
		switch (httpRestResponse.getStatus()) {
		case OK:

			mapToken = mapResultApi(httpRestResponse.getBody());
			if (mapToken != null && mapToken.get("access_token") != null) {
				return (String) mapToken.get("access_token");
			}

			break;

		case NOT_ACCEPTABLE:
			return null;
		case INTERNAL_SERVER_ERROR:
			return null;
		default:
			return null;
		}

		return null;
	}

	protected Map<String, Object> mapperJsonToHashMap(String result) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Map<String, Object> finalMap = new HashMap<>();
		try {
			finalMap = mapper.readValue(result, new TypeReference<HashMap<String, Object>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			finalMap.put("error_method", e.getMessage());
		}
		return finalMap;
	}

	protected <T> T mapperJsonToSingleDto(String json, Class<T> clazz) throws Exception {
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return om.readValue(json, clazz);
	}

	protected <T> List<T> mapperJsonToListDto(String json, Class<T> clazz) throws Exception {
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		om.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		TypeFactory t = TypeFactory.defaultInstance();
		List<T> list = om.readValue(json, t.constructCollectionType(ArrayList.class, clazz));
		return list;
	}

	@SuppressWarnings("rawtypes")
	private HttpRestResponse wsBody(String url, Object body, HttpMethod method, Map<String, String> headerMap,
			ParamQueryCustomLib... paramQuery) {
		MultiValueMap<String, Object> header = new LinkedMultiValueMap<>();
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
		if (headerMap != null) {
			for (Entry<String, String> hm : headerMap.entrySet()) {
				headers.add(hm.getKey(), hm.getValue());
			}
		}
		StringBuilder paramBuilder = new StringBuilder();
		if (paramQuery != null) {
			if (paramQuery.length != 0) {
				paramBuilder.append("?");
				for (int i = 0; i < paramQuery.length; i++) {
					paramBuilder.append(paramQuery[i].getKey().concat("=".concat(paramQuery[i].getValue())));
					if (i < paramQuery.length - 1) {
						paramBuilder.append("&");
					}
				}
			}
		}
		System.err.println("body : " + new Gson().toJson(body));
		System.err.println("header : " + new Gson().toJson(headers));
		
		HttpEntity httpEntity = new HttpEntity(body, headers);
		RestTemplate restTemplate = new RestTemplate();
		System.err.println("url yang diberikan : " + url.concat(paramBuilder.toString()));

		String resultApi = new String();
		try {
			ResponseEntity<String> responseEntity = restTemplate.
					exchange(url.concat(paramBuilder.toString()), method,
					httpEntity, String.class);
			System.err.println("status : " + responseEntity.getStatusCode());
			System.err.println("result api : " + responseEntity.getBody());
			return new HttpRestResponse(responseEntity.getStatusCode(), responseEntity.getBody());
		} catch (Exception exp) {
			if (exp.getMessage().contains("400")) {
				System.err.println("error 400");
				return new HttpRestResponse(HttpStatus.BAD_REQUEST, "User atau Password Salah");
			} else if (exp.getMessage().contains("Connection refused")) {
				System.err.println("Connection refused");
				return new HttpRestResponse(HttpStatus.INTERNAL_SERVER_ERROR,
						"Tidak dapat berkomunikasi dengan service");
			} else {
				System.err.println("exp said: " + exp.getMessage());
				System.err.println("error unidentified");
				return new HttpRestResponse(HttpStatus.NOT_EXTENDED, "Cannot Identified Error Record");
			}
		}
	}

	private PageResponse getResultWsPage(String url, Object body, HttpMethod method, Map<String, String> headerMap,
			ParamQueryCustomLib... paramQuery) {
		PageResponse pageResponse = new PageResponse();
		HttpRestResponse httpRestResponse = wsBody(url, body, method, headerMap, paramQuery);

		switch (httpRestResponse.getStatus()) {
		case OK:
			ObjectMapper om = new ObjectMapper();
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				pageResponse = om.readValue(httpRestResponse.getBody(), PageResponse.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return pageResponse;
		case NOT_ACCEPTABLE:
			return null;
		case INTERNAL_SERVER_ERROR:
			return null;
		default:
			return null;
		}
	}

	private WsResponse getResultWs(String url, Object body, HttpMethod method, Map<String, String> headerMap,
			ParamQueryCustomLib... paramQuery) {
		WsResponse wsResponse = new WsResponse();
		HttpRestResponse httpRestResponse = wsBody(url, body, method, headerMap, paramQuery);

		switch (httpRestResponse.getStatus()) {
		case OK:
			ObjectMapper om = new ObjectMapper();
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				wsResponse = om.readValue(httpRestResponse.getBody(), WsResponse.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return wsResponse;
		case NOT_ACCEPTABLE:
			return null;
		case INTERNAL_SERVER_ERROR:
			return null;
		default:
			return null;
		}
	}

	private Map<String, Object> mapResultApi(String result) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Map<String, Object> finalMap = new HashMap<>();
		try {
			finalMap = mapper.readValue(result, new TypeReference<HashMap<String, Object>>() {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalMap;
	}

}
