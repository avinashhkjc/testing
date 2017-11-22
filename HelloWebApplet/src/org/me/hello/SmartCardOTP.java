package org.me.hello;

import java.applet.Applet;
import java.util.List;

import javax.smartcardio.ATR;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

public class SmartCardOTP extends Applet {
	public String hex2Decimal(String paramString) {
		String str1 = "0123456789ABCDEF";
		paramString = paramString.toUpperCase();
		int i = 0;
		for (int j = 0; j < paramString.length(); j++) {
			int k = paramString.charAt(j);
			int m = str1.indexOf(k);
			i = 16 * i + m;
		}
		String str2 = String.valueOf(i);
		return str2;
	}
	
	public String byteArrayToHex(byte paramByte) {
		StringBuilder localStringBuilder = new StringBuilder(2);
		localStringBuilder.append(String.format("%02x", new Object[] { Integer.valueOf(paramByte & 0xFF) }));
		return localStringBuilder.toString();
	}
	
	public String bytearray2OTP(byte[] paramArrayOfByte) {
		Object localObject = null;
		String str1 = "";
		String str2 = null;
		String str3 = null;
		
		for (byte b : paramArrayOfByte) {
			str1 = str1 + String.valueOf(byteArrayToHex(b));
		}
		
		str2 = hex2Decimal(str1);
		str3 = str2.substring(str2.length() - 6);
		return str3;
	}
	
	public String getOTP() {
		String str = "Smartcard is not ready1";
		try {
			TerminalFactory localTerminalFactory = TerminalFactory.getDefault();
			
			List localList = localTerminalFactory.terminals().list();
			
			CardTerminal localCardTerminal = (CardTerminal) localList.get(0);
			
			if (localCardTerminal.isCardPresent()) {
				Card localCard = localCardTerminal.connect("T=1");
				
				ATR localATR = localCard.getATR();
				byte[] arrayOfByte1 = localATR.getBytes();
				
				CardChannel localCardChannel = localCard.getBasicChannel();
				
				byte[] arrayOfByte2 = { 0, -92, 4, 0, 13, -96, 0, 0, 0, 48, -128, 0, 0, 0, 0, 40, 1, 1 };
				
				ResponseAPDU localResponseAPDU1 = localCardChannel.transmit(new CommandAPDU(arrayOfByte2));
				
				if ((localResponseAPDU1.getSW1() == 144) && (localResponseAPDU1.getSW2() == 0)) {
					byte[] arrayOfByte3 = localResponseAPDU1.getData();
					
					byte[] arrayOfByte4 = { 0, 42, 1, 0, 4 };
					
					ResponseAPDU localResponseAPDU2 = localCardChannel.transmit(new CommandAPDU(arrayOfByte4));
					
					if ((localResponseAPDU2.getSW1() == 144) && (localResponseAPDU2.getSW2() == 0)) {
						byte[] arrayOfByte5 = localResponseAPDU2.getData();
						
						str = bytearray2OTP(arrayOfByte5);
					}
					
				}
				
				localCard.disconnect(true);
			} else {
				str = "Smartcard is not ready1";
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		
		return str;
	}
}
