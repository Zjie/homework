package game;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GameSaver extends GameIOHandler {
	private DocumentBuilderFactory m_docFactory;
	private DocumentBuilder m_docBuilder;
	
	private TransformerFactory m_transformerFactory;
	private Transformer m_transformer;
	private DOMSource m_source;
	
	private static Document m_doc;
	private File m_file;
	private StreamResult m_result; 
	
	private static Element m_rootElement;
	
	private static Element m_playerOne;
	private static Element m_playerTwo;

	public GameSaver(String fileName){
		m_file = new File(fileName);
		m_docFactory = DocumentBuilderFactory.newInstance();
		try {
			m_docBuilder = m_docFactory.newDocumentBuilder();
			m_doc = m_docBuilder.newDocument();
			
			setRootElement();
			
			setGameTypeElement();
			setTimeElement();
			setPlayerTurnElement();
			
			initPlayerElement();
			setPlayerName();
			setPlayerType();
			setColour();
			setPlayerPieceCount();
			setPieces();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeFile();
	}
	
	public void setGameTypeElement(){}
	
	public void setPieces(){}
	
	public void setColour(){}
	
	public void setPlayerPieceCount(){}
	
	public void setPlayerType(){
		String p1Type = Game.getPlayer(0).getPlayerType();
		String p2Type = Game.getPlayer(1).getPlayerType();
		
		Element playerOneType = m_doc.createElement("type");
		Element playerTwoType = m_doc.createElement("type");
		
		playerOneType.appendChild(m_doc.createTextNode(p1Type));
		playerTwoType.appendChild(m_doc.createTextNode(p2Type));	
		
		m_playerOne.appendChild(playerOneType);
		m_playerTwo.appendChild(playerTwoType);
	}
	
	public void setPieceCount(){}
	
	public static void setTimeElement(){
		String gameTime = GameGUI.getTime() + "";
		Element time = m_doc.createElement("time");
		time.appendChild(m_doc.createTextNode(gameTime));
		m_rootElement.appendChild(time);
	}
	
	public static void setPlayerTurnElement(){
		String turn;
		if(Game.getPlayerTurn() % 2 == 0){
			turn = "Player 1";
		}else{
			turn = "Player 2";
		}
	
		Element playerTurn = m_doc.createElement("turn");
		playerTurn.appendChild(m_doc.createTextNode(turn));
		m_rootElement.appendChild(playerTurn);
	}
	
	public static void initPlayerElement(){
		m_playerOne = m_doc.createElement("player");
		m_playerTwo = m_doc.createElement("player");

		m_rootElement.appendChild(m_playerOne);
		m_rootElement.appendChild(m_playerTwo);
		
		m_playerOne.setAttribute("id", "1");
		m_playerTwo.setAttribute("id", "2");
	}
	
	public static void setRootElement(){
		m_rootElement = m_doc.createElement("game");
		m_doc.appendChild(m_rootElement);
	}
	
	public static void setPlayerName(){
		String playerOneName = Game.getPlayerName(-1);
		String playerTwoName = Game.getPlayerName(2);
		Element p1Name = m_doc.createElement("name");
		Element p2Name = m_doc.createElement("name");
		
		p1Name.appendChild(m_doc.createTextNode(playerOneName));
		p2Name.appendChild(m_doc.createTextNode(playerTwoName));
	
		m_playerOne.appendChild(p1Name);
		m_playerTwo.appendChild(p2Name);
	}
	
	public void writeFile(){
		m_transformerFactory = TransformerFactory.newInstance();
		try {
			m_transformer = m_transformerFactory.newTransformer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		m_source = new DOMSource(m_doc);
		m_result = new StreamResult(m_file);
		
		try {
			m_transformer.transform(m_source, m_result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("File saved!");
	}
	
	public Element getPlayers(int x){
		if(x == 0){
			return m_playerOne;
		}else{
			return m_playerTwo;
		}
	}

	public static Element getRootElement() {
		return m_rootElement;
	}
	
	public static Document getDoc(){
		return m_doc;
	}
}
