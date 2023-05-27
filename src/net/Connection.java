package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {
	
	private Socket socket;
	private DataOutputStream salida;
	private DataInputStream entrada;
	
	
	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		salida = new DataOutputStream(socket.getOutputStream());
		entrada = new DataInputStream(socket.getInputStream());
	}
	
	public int readInt() throws IOException {
		return entrada.readInt();
	}
	
	public String readUTF() throws IOException {
		return entrada.readUTF();
	}
	
	public void writeInt(int number) throws IOException {
		salida.writeInt(number);
	}
	
	public void writeUTF(String string) throws IOException {
		salida.writeUTF(string);
	}
	
	public void writeBoolean(Boolean boolean1) throws IOException {
		salida.writeBoolean(boolean1);
	}
	
	
}
