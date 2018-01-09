package utils.networking;

import java.net.*;
import java.io.*;

//Author: Nico

public class Networker {

	int ip;
	int host_ip;
	int port;		//has to be somewhere 1000-45000
	
	
	
	public Networker() {
		
	}
	
	public void printAddress() {	//own IP
		System.out.println("Shit. Address?!");
	}
	
	public int sendChatMsg(String to, String msg) {  //to=Rezipient; msg= content
		return -1;//fail
		//return 0;//success
	}
	
	public int sendGamePacket(String content) { //game-content--> UDP
		return -1;//fail
		//return 0;//success
	}
	
	public boolean checkChatMsg(){	//check for new Chat-Msg
		return false;
	}
	
	public String getGamePacket() {
		String gameP=""; //GamePacketContent
		//magic
		return gameP;
	}
	
	public String getChatMsg() { 		
		String receive = "";
		
		return receive;
	}
	
}
