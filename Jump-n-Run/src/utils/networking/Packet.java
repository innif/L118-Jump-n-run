package utils.networking;

import java.net.DatagramPacket;

public class Packet {

	private int type;		//0 Invalid, 1 login, 2 data,3 move
	private String content;
	private boolean toForward;
	
	public Packet(int type, String content) {
		this.type = type;
		this.content = content;
		toForward = (type<3) ? false: true;
	}
	
	public Packet() {
		type = 2;
		content = "";
		toForward = false;
	}
	
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content= content;
	}
	
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	
	public boolean toForward() {
		return toForward;
	}

	public void setToForward(boolean toForward) {
		this.toForward = toForward;
	}
	
	public String toString(){
		String s = Integer.toString(type);
		s+=content;
		return s;
	}
	
	public static Packet parsePacket(DatagramPacket p){
			Packet returnP = new Packet(); //type content
			String s = "";
			
			byte[] data = new byte[p.getData().length];
			for(int c = 0; c < data.length;c++) s += String.valueOf((char)data[c]); 

			returnP.setType((int)p.getData()[0]);
			returnP.setContent(s);
			returnP.setToForward((int)p.getData()[0] < 3);
			
			return returnP;
	} //Packet parsePacket

	
}//class Packet
