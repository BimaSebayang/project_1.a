package id.co.roxas.app.web.uda.config;

import org.springframework.http.HttpStatus;

public class HttpRestResponse {
      private HttpStatus status;
      private String body;
      
 
	public HttpRestResponse(HttpStatus status, String body) {
		this.status = status;
		this.body = body;
	}

	
	
	public HttpStatus getStatus() {
		return status;
	}



	public String getBody() {
		return body;
	}
      
      
}
