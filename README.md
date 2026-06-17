# Distributed-Systems-Groupwork

1. PROBLEM 13.27: HostToIP.java
------------------------------------------------------------------------
* CODE EXPLANATION:
  This program performs a DNS lookup to map a hostname (like google.com) 
  to its corresponding IP address. It uses a Scanner to take the user's 
  string input and passes it to InetAddress.getByName(host). This method 
  queries the local system or external DNS servers. If a match is found, 
  the program prints the IP address using .getHostAddress(). If the domain 
  is invalid, an UnknownHostException is caught to keep the app from crashing.

* EXECUTION INSTRUCTIONS:
  $ javac HostToIP.java
  $ java HostToIP
  Type a domain name when prompted and press Enter.


2. PROBLEM 13.28: URLContentPuller.java
------------------------------------------------------------------------
* CODE EXPLANATION:
  This script connects to a web server and reads its raw source code. 
  It opens a raw input byte stream via url.openStream() targeted at 
  http://www.buyya.com. The stream is handled by an InputStreamReader 
  and wrapped in a BufferedReader so the program can read the server's 
  HTML response line by line. A standard while loop prints each line 
  to the console until readLine() returns null.

* EXECUTION INSTRUCTIONS:
  $ javac URLContentPuller.java
  $ java URLContentPuller


3. PROBLEM 13.29: UDP PING-PONG SYSTEM
------------------------------------------------------------------------
* CODE EXPLANATION:
  This system uses UDP via DatagramSocket and DatagramPacket instead of a 
  persistent TCP stream.
  
  - PingPongServer.java: Binds to port 6000 and loops indefinitely. It blocks 
    at socket.receive(packet) until bytes arrive, then converts them to a 
    String. If the text matches 'ping' (ignoring case), it replies with a 
    'pong' packet sent back to the client's IP and port. Any other message 
    falls through to the else block and is ignored/dropped.
    
  - PingPongClient.java: Sends a text packet to localhost:6000. It includes 
    socket.setSoTimeout(3000), meaning if the server drops the packet, the 
    client will timeout after 3 seconds rather than hanging forever.

* EXECUTION INSTRUCTIONS:
  Terminal 1 (Start Server):
  $ javac PingPongServer.java && java PingPongServer

  Terminal 2 (Run Client):
  $ javac PingPongClient.java && java PingPongClient


4. PROBLEM 13.30: UDP MATH SERVER APPLICATION
------------------------------------------------------------------------
* CODE EXPLANATION:
  This application processes mathematical queries statelessly over UDP. The 
  server runs on port 7000, unpacks the incoming string expression, and 
  passes it to a helper method. The string is split by whitespace using 
  .split("\\s+") to isolate the operator (ADD, SUB, MUL, DIV) and the two 
  numbers. The values are parsed as doubles, computed via a switch-case block, 
  and the result is sent back to the client's origin address in a return packet.

* EXECUTION INSTRUCTIONS:
  Terminal 1 (Start Server):
  $ javac UDPMathServer.java && java UDPMathServer

  Terminal 2 (Run Client):
  $ javac UDPMathClient.java && java UDPMathClient
  Type operations using the format: ADD 15 2.5, MUL 4 3, or DIV 10 2.


5. PROBLEM 13.31: CONCURRENT MULTITHREADED DICTIONARY SERVER
------------------------------------------------------------------------
* CODE EXPLANATION:
  This TCP dictionary application is built to handle multiple users at once without 
  blocking the server port.
  
  - Data Lookup: The definitions are stored in a static ConcurrentHashMap. 
    This provides thread-safe access and fast, constant-time O(1) lookups.
    
  - Multi-Threading: ServerSocket.accept() runs in a loop on port 8000. When a 
    client connects, the main thread hands the socket over to an independent 
    ClientHandler worker thread. This leaves the main server loop free to 
    immediately accept the next user while the child thread processes the query.

* EXECUTION INSTRUCTIONS:
  Terminal 1 (Start Server):
  $ javac DictionaryServer.java && java DictionaryServer

  Terminal 2 (Run Client):
  $ javac DictionaryClient.java && java DictionaryClient
  Look up preloaded words like "java", "socket", or "protocol".


6. PROBLEM 13.32: SIMPLE LINK CRAWLER
------------------------------------------------------------------------
* CODE EXPLANATION:
  This crawler parses web data to find outgoing links. It opens a stream using 
  URLConnection and overrides the default header via connection.setRequestProperty(
  "User-Agent", "Mozilla/5.0") to bypass basic anti-bot blocks. As the page 
  reads, each line is tested against a regular expression pattern: href="(http[s]?://.*?)". 
  A Matcher loop extracts the clean URL within the quotes using matcher.group(1) 
  and prints it.

* EXECUTION INSTRUCTIONS:
  $ javac SimpleLinkCrawler.java
  $ java SimpleLinkCrawler


7. PROBLEM 13.33: TCP UPPERCASE CHARACTER STREAM ECHO SYSTEM
------------------------------------------------------------------------
* CODE EXPLANATION:
  A classic connection-oriented echo protocol built over TCP streams.
  
  - UppercaseServer.java: Listens on port 9000. When a client connects, a 
    direct virtual connection is created. The server reads the incoming text line, 
    applies .toUpperCase(), and writes the converted text straight back into 
    the socket stream.
    
  - UppercaseClient.java: Connects to localhost:9000, takes text from the console, 
    pipes it down the socket stream via PrintWriter, and blocks until it reads 
    the capitalized response from the server.

* EXECUTION INSTRUCTIONS:
  Terminal 1 (Start Server):
  $ javac UppercaseServer.java && java UppercaseServer

  Terminal 2 (Run Client):
  $ javac UppercaseClient.java && java UppercaseClient


8. WORKSPACE CONFIGURATION (CODESPACES)
------------------------------------------------------------------------
  - For all client-server setups (UDP or TCP), the Server file must be 
    running first before starting the Client.
  - To run both files side by side, use the "Split Terminal" icon in the 
    top-right of the terminal window panel, or use the keyboard shortcut 
    Ctrl + Shift + 5 (Cmd + Shift + 5 on Mac).
  - Because clients and servers run within the same cloud workspace container, 
    they will talk to each other over 'localhost' natively. Any automated port-
    forwarding pop-ups can be closed or ignored.
========================================================================