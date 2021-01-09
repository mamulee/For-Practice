package view;

import client.Client;
import server.Server;

public class ClientLaunch {

	public static void main(String[] args) {

		Client c = new Client();
		c.startClient();
	}

}
