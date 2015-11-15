import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.util.*;
import org.json.simple.JSONObject;

public class Server {
    /**
     * [main description]
     * @param  args      [description]
     * @throws Exception [description]
     */
	public static void run() throws Exception {
		int port = 8000;
		final SessionManager sessionMgr = new SessionManager();
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

		// /auth
		server.createContext("/auth", new HttpHandler() {
			@Override
			public void handle(HttpExchange t) throws IOException {
				Map <String, String> params = queryToMap(t.getRequestURI().getQuery());
				String name = params.get("name");
				String password = params.get("password");
				String token = sessionMgr.authenticate(name, password);

				JSONObject responseObj = new JSONObject();
				responseObj.put("token", token);
				int responseCode = token == null ? 401 : 200;

				sendJSONResponse(t, responseCode, responseObj);
			}
		});
	    // /usersa
	    server.createContext("/users", new HttpHandler() {
	      @Override
	      public void handle(HttpExchange t) throws IOException {
	        Map <String, String> params = queryToMap(t.getRequestURI().getQuery());
	        String token = params.get("token");
	        Boolean is_authorized = false;

	        Session session = sessionMgr.checkToken(token);

	        if(session!=null){
	            is_authorized = session.getUser().getRole().equals("admin");
	        }

	        JSONObject response = new JSONObject();
	        if(is_authorized){
	            response = User.getList();
	        }
	        int responseCode = is_authorized? 403 : 200;

	        sendJSONResponse(t, responseCode, response);
	      }
	    });
	    server.createContext("/", new StaticHttpHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
		System.out.println("Server started at " + port);
	}
    /**
     * Send JSON response
     * @param  t            [description]
     * @param  responseCode [description]
     * @param  responseObj  [description]
     * @throws IOException  [description]
     */
	static void sendJSONResponse(HttpExchange t, int responseCode, JSONObject responseObj) throws IOException {
		String response = responseObj.toString();
		OutputStream os = t.getResponseBody();
		t.getResponseHeaders().add("Content-Type", "application/json");
		t.sendResponseHeaders(responseCode, response.length());

		os.write(response.getBytes());
		os.close();
	}

	/**
	 * Utility method to convert query string into a hash map
	 * @param  query ex."a=1&b=3"
	 * @return
	 */
	static Map<String, String> queryToMap(String query) {
		Map<String, String> result = new HashMap<String, String>();
		for (String param : query.split("&")) {
			String pair[] = param.split("=");
			if (pair.length > 1) {
				result.put(pair[0], pair[1]);
			} else {
				result.put(pair[0], "");
			}
		}
		return result;
	}
}
