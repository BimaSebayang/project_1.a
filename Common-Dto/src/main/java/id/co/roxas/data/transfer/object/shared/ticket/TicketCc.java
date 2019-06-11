package id.co.roxas.data.transfer.object.shared.ticket;

import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;

public class TicketCc {
        private String accessIdentifier;
        private String sessionId;
        private String module;
        private String userIdentifier;
        private int isLogOut = 0;
		public String getAccessIdentifier() {
			return accessIdentifier;
		}
		public void setAccessIdentifier(String accessIdentifier) {
			this.accessIdentifier = accessIdentifier;
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
		public String getModule() {
			return module;
		}
		public void setModule(String module) {
			this.module = module;
		}
		public String getUserIdentifier() {
			return userIdentifier;
		}
		public void setUserIdentifier(String userIdentifier) {
			this.userIdentifier = userIdentifier;
		}
		public int getIsLogOut() {
			return isLogOut;
		}
		public void setIsLogOut(int isLogOut) {
			this.isLogOut = isLogOut;
		}
		
        
        
}
