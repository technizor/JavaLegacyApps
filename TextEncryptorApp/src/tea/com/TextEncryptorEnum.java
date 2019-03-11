package tea.com;

import java.awt.Dimension;

/**
*	DayCounterEnum Class
*	This class is a holder for most of the generic constants
*	used by this program. Such includes enumerations, and finals.
*
*	@author Sherman Ying <idiotioc@gmail.com>
*	@version 1.6
*	@since 2011.0715
**/

public class TextEncryptorEnum {
/**
*	This enumeration stores the label and tool tip information for
*	every button in the AppWindowControl class.
**/	public enum buttonProperties {
		close("X","Close this application."),
		minimize("_","Minimize this application.");
		private String labelText;
		private String toolTip;
		buttonProperties(String l, String t)
		{
			labelText = l;
			toolTip = t;			
		}
		public String getLabel()
		{
			return labelText;
		}
		public String getToolTip()
		{
			return toolTip;
		}
	}

/**
*	This enumeration stores the label and tool tip information for
*	every component in the AppInterface class.
**/	public enum elementProperties {
		title("Text Encryptor Application", "Click to drag this window."),
		numLabel("PIN: ", "Personal Identification Number - Used for encryption."),
		keyLabel("Keyword: ", "Text Keyword/Sequence - Used for encryption."),
		key("", "Maximum of 16 characters."),
		num("", "Required: 4 digits."),
		outFile("Save to Text/Encryption File", "Click to save to a text file."),
		inFile("Load from Text/Encryption File", "Click to load from a text file."),
		outTextLabel("\\" + "/ Text to be decrypted \\" + "/", "Type your message below."),
		decryptIt("<<< Decrypt <<<", "Click to decrypt the above text."),
		inTextLabel("\\" + "/ Text to be encrypted \\" + "/", "Type your message below."),
		encryptIt(">>> Encrypt >>>", "Click to encrypt the above text.");
		private String labelText;
		private String toolTip;
		elementProperties(String l, String t)
		{
			labelText = l;
			toolTip = t;			
		}
		public String getLabel()
		{
			return labelText;
		}
		public String getToolTip()
		{
			return toolTip;
		}
	}

/**
*	This is the default size for most of the components.
**/	final static public String compSize = "normal";

/**
*	This is the default size for the window control components.
**/	final static public String controlSize = "small";

/**
*	This is the preferred size of the window.
**/	final static public Dimension panelSize = new Dimension(375, 140);

/**
*	This is a string required in all statements that would set the size of a component.
**/	final static public String sizeVari = "JComponent.sizeVariant";

	final static public String DEFAULTKEY = "qwertyuiop";
	final static public int DEFAULTNUM = 1337;
	final static public Boolean DEBUG = false;
	final static public char PINSPACER = '-';
	final static public String PINMASK = "####";
}
