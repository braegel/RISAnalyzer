;~ AutoITMerge.au3 is an AutoIT script for semiautomatic patient merge using Siemens Syngo Workflow Browser sWF Software
;~ Author is bernd@braegelmann.de 20.08.2010 Düren, Germany


HotKeySet("n", "NextMerge")
HotKeySet("{PAUSE}", "Terminate")
$linenumber=0;

	;~ Open patientmodule
	MouseMove(30, 80)
	MouseDown("left")
	Sleep(100)
	MouseUp("left")
	Send("{ESC}")
	Sleep(100)
	Send("{ESC}")
	Sleep(100)
	Send("{ESC}")
	Sleep(100)
	MouseDown("left")
	Sleep(1000)
	MouseUp("left")



While 1
    Sleep(100)
WEnd

Func Terminate()
    Exit 0
EndFunc

Func NextMerge()
	Send("{ESC}")					
	
	Sleep(100)
	Send("{ESC}")
	Sleep(100)

	;~ Open merge-dialoge
	MouseMove(500, 120)
	MouseDown("left")
	Sleep(100)					
	
	MouseUp("left")
	Sleep(1000)
	$file = FileOpen("\\172.24.31.154\sasupdate\braegelmann\Scripte\autoitdubletten.txt", 0)

; Check if file opened for reading OK
	If $file = -1 Then
		MsgBox(0, "Error", "Unable to open file.")
		Exit
	EndIf
	
	$linenumber=$linenumber+1;
	
    $line = FileReadLine($file,$linenumber)
	FileClose($file)

	$patid=StringSplit($line,",",1)
		
	Send($patid[1])
	Sleep(200)
	Send("{TAB}{TAB}{TAB}{TAB}{TAB}{ENTER}")
	Sleep(2000)
	Send("{TAB}{TAB}{TAB}{TAB}")
	Send($patid[2])
	Send("{TAB}{TAB}{TAB}{TAB}{TAB}{ENTER}")
	Sleep(1500)
	MouseMove(500, 390)
	MouseDown("left")
	Sleep(100)
	MouseUp("left")
	Sleep(100)
	MouseMove(700, 390)
	MouseDown("left")
	Sleep(100)
	MouseUp("left")
	Sleep(100)
	
EndFunc

