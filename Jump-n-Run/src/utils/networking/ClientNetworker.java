package utils.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class ClientNetworker extends Thread{
		
	private int port;		//has to be somewhere 1000-45000
	private DatagramSocket socket;
	private ArrayList<InetAddress> adresses= new ArrayList<InetAddress>();
	private ArrayList<Packet> packets = new ArrayList<Packet>();
	private InetAddress me;
	
	
	public ClientNetworker(){
		
		port = 4243;
		me = InetAddress.getLoopbackAddress();
		
		try{
			socket = new DatagramSocket(port, me);
		}
		catch(IOException e){ 
			e.printStackTrace();
		}
		
		InetSocketAddress a = new InetSocketAddress(me,port);
			try {
			socket.bind(a);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}//public ClientNetworker()
	
	public ClientNetworker(InetAddress adr){
		
		port = 4243;
		me = adr;
		
		try{
			socket = new DatagramSocket(port,me);
		}
		catch(IOException e){ 
			e.printStackTrace();
		}	
		
		InetSocketAddress a = new InetSocketAddress(me,port);
			try {
			socket.bind(a);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}//public ClientNetworker()		
	public ClientNetworker(InetAddress adr, int port){			
		this.port = port;
		me = adr;

		try{
			socket = new DatagramSocket(this.port,me);
		}
		catch(IOException e){ 
			e.printStackTrace();
		}	
	
		InetSocketAddress a = new InetSocketAddress(me,port);
		try {
			socket.bind(a);
		} catch (SocketException e) {
			e.printStackTrace();
		}
			
	}//public ClientNetworker()
		
	public void run(){
		while(true){
			byte[] data = new byte[1024]; 
			DatagramPacket dp = new DatagramPacket(data, data.length);
			try {
				socket.receive(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("received"+dp.toString());
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(!adresses.contains(dp.getAddress())) adresses.add(dp.getAddress());
			
			Packet p = Packet.parsePacket(dp);
			if(!packets.contains(p)) packets.add(p);
			
		}//while true()
	}//void run()
	
	public String getLastPacket() {										//Get last packet
		Packet last = (Packet) packets.toArray()[packets.size()-1]; 
		return last.toString();
	}
	public String getLastPacket(int type) {								//Get last packet of type [type]
		ArrayList<Packet> pList = new ArrayList<Packet>();
		for(Packet p : packets) if(p.getType() == type) pList.add(p);
		Packet last = (Packet) pList.toArray()[pList.size()-1];
		return last.toString();
	}
		
	public ArrayList<String> getLastPackets(int amount, int type) {		//Get last [amount] packets of type [type]
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<Packet> pList = new ArrayList<Packet>();
		for(Packet p : packets) if(p.getType() == type) pList.add(p);
		Packet[] ps = new Packet[pList.size()];
		for(int c = ps.length-1; (c> ps.length-amount)&&(c>=0);c--) a.add(ps[c].toString());
		return a;
	}
		
	public boolean sendMovePacket(InetAddress adr, String content) {
		return sendPacket(adr, new Packet(3,content), port);
	}
		
	public boolean sendLoginPacket(InetAddress adr, String content) {
		return sendPacket(adr, new Packet(1,content), port);
	}
		
	public boolean sendDataPacket(InetAddress adr, String content) {
		return sendPacket(adr, new Packet(2,content), port);
	}
		
		
	public boolean sendPacket(InetAddress adr, Packet content, int port) {
		return sendPacket(adr, content.toString().getBytes(),port);
	}
		
	public boolean sendPacket(InetAddress adr, byte[] data, int port){
		DatagramPacket dp = new DatagramPacket(data,data.length,adr,port);
		try{
			socket.send(dp);
		}
		catch(Exception e){
			System.out.println("Error sending packet.");
			e.printStackTrace();
			return false;
		}
		return true;
	}//boolean sendPacket()
		
	public void shutdown() {
		socket.close();
	}

		

	
	
}//class
