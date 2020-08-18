;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	LOGGER.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Unknown
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	The logger is used to record notes about bugs found during the QA and
;;;;	beta processes.
;;;;
;;;;	Procedures:
;;;;		Log
;;;;
;;;;	Instances:
;;;;		sysLogger


(script# LOGGER)
(include game.sh)
(use Main)
(use String)
(use Print)
(use PolyPath)
(use System)

;;;(procedure
;;;	Log
;;;)

(public
	sysLogger 0
)

(local
	logHandle = 0
)

(enum 1
	Txt
	Num
	Uns
	Hex
	Inp
	Tim
	Dat
)

(define MAXCOMMENTS 10)

(procedure (Log how aLabel anArg &tmp buffer tm retval)
	(= buffer (String newWith: 80 {}))
	(buffer format: {%15s: } aLabel)
	(FileIO FileFPuts logHandle (buffer data?))
	(buffer copy: {})

	(switch how
		(Txt	(buffer copy: (if anArg anArg else {})))
		(Num	(buffer format: {%d} anArg))
		(Uns	(buffer format: {%u} anArg))
		(Hex	(buffer format: {%x} anArg))
		(Inp
			(if anArg
				(GetInput buffer 50 anArg 999)
			)
			(= retval (buffer size:))
		)
		(Tim
			; get system time stamp (minutes after 12:00)
			(= tm (GetTime SysTime2))
			(buffer format: 
				{%02d:%02d:%02d}
				(>> tm 11)
				(& (>> tm 5) %111111)
				(* (& tm %11111) 2)
			)
		)
		(Dat
			; get system date stamp (mm/dd/yy)
			(= tm (GetTime SysDate))
			(buffer format:
				{%02d/%02d/%02d}
				(& (>> tm 5) %1111)
				(& tm %11111)
				(+ 80 (>> tm 9))
			)
		)
	)
	
	(buffer cat: {\r})
	(FileIO FileFPuts logHandle (buffer data?))
	(buffer dispose:)
	retval
);procedure Log


(instance sysLogger of Code
	
	(method (doit 
			&tmp	i j l c firstNote theDrv commented saveInfont
			str
			cfgPath
			thePath
			theToken
			QAinitials
			kbdDrvEntry
			joyDrvEntry
			videoDrvEntry
			soundDrvEntry
			mouseDrvEntry
			audioDrvEntry
		)

		;;	initialize some variables:

		(= str 				(String new: 80))
		(= cfgPath 			(String new: 60))
		(= thePath 			(String new: 60))
		(= theToken  		(String new: 60))
		(= QAinitials 		(String new: 40))
		(= kbdDrvEntry 	(String new: 80))
		(= joyDrvEntry 	(String new: 80))
		(= videoDrvEntry 	(String new: 80))
		(= soundDrvEntry 	(String new: 80))
		(= mouseDrvEntry 	(String new: 80))
		(= audioDrvEntry 	(String new: 80))
		
		(= saveInfont inputFont)
		(= inputFont 999)
		
		(= firstNote (not (sysLogPath size:)))
		
		;; if path argument is NULL, we need some initial info 
		(if firstNote
			(while (not (< 0 (thePath size:) 19))
				(Print
				 	font:		999,
					fore:		0,
					back:		(Palette PalMatch 127 127 127),
				 	addText:	{Enter drive letter, path, and your name\n
				 				(no extension, max 40 characters)},
				 	addEdit: thePath 40 0 30,
				 	init:
				)
			)
			(sysLogPath copy: (thePath data?))
		)

;		; This commented code was intended to check disk space before
;		;	trying to write a log, but the kernel call FileIO FileCheckFreeSpace
;		;	doesn't appear to be working!
;
;		; First thing we do is check available disk space
;		; If we don't have at least 2k, don't even bother
;		;	with the rest of this - BKH
;
;		(if (not (FileIO FileCheckFreeSpace (sysLogPath data?)))
;			(Print
;				font:			999,
;				addText:		{Error: Not enough disk space for new entry},
;				init:
;			)
;			(return FALSE)
;		)

		;;access "memory variable" file to seed data
		(thePath format: {%s.mem} (String StrGetData sysLogPath))
		
		(if (!= -1 (= logHandle (FileIO FileOpen (thePath data?) fRead)))
			(FileIO FileFGets (QAinitials data?) 80 logHandle)
			(FileIO FileFGets (cfgPath data?) 80 logHandle)
			(FileIO FileClose logHandle)
		else
			(QAinitials copy: {})
			(cfgPath copy: {resource.cfg})
		)
		
		(if firstNote
			(Print
				font:		999,
				addText:	{Enter your login name\n
							(max 8 characters):},
				addEdit: QAinitials 12 0 30,
				init:
			)
			(QAinitials at: 8 NULL)
		)
		
		;; read configuration file
		(while
			(and
				(or
					(not firstNote)
					(Print
						font:		999,
						addText:	{Enter configuration file name:},
						addEdit:	cfgPath 30 0 30 cfgPath,
						init:
					)
			  	)
			  	(cfgPath at: 0)
			  	(== -1 (= logHandle (FileIO FileOpen (cfgPath data?) fRead)))
			)
			(cfgPath at: 0 0)
		)

		(if (!= -1 logHandle) ; opened config file
			(while (FileIO FileFGets (str data?) 80 logHandle)
				
				; strip leading whitespace
				(for 
					((= i 0)) 
					(and (= c (str at: i)) (OneOf c TAB SPACEBAR)) 
					((++ i))
				)
				
				(for 	((= j 0))
						(and	(= c (str at: i))
								(not (OneOf c `= `: TAB SPACEBAR))
						) 
						((++ i) (++ j))
					(theToken at: j c)
				)
				
				(theToken at: j NULL)
				
				(= theDrv
					(cond
						((theToken compToFrom: 0 {kbdDrv} 0 6)
							kbdDrvEntry
						)
						((theToken compToFrom: 0 {joyDrv} 0 6)
							joyDrvEntry
						)
						((theToken compToFrom: 0 {videoDrv} 0 8)
							videoDrvEntry
						)
						((theToken compToFrom: 0 {soundDrv} 0 8)
							soundDrvEntry
						)
						((theToken compToFrom: 0 {mouseDrv} 0 8)
							mouseDrvEntry
						)
						((theToken compToFrom: 0 {audioDrv} 0 8)
							audioDrvEntry
						)
						(else
							0
						)
					)
				)

				(if theDrv
					
					;;skip trailing white space
					(while (and (= c (str at: i)) (OneOf c `= `: TAB SPACEBAR))
						(++ i)
					)

					;;find last delimiter and period
					(for	((= j i) (= l 0))
							(= c (str at: j))
							((++ j))

						(cond
							((OneOf c `: `
							;EO: This commented out part can't be compiled, since it causes SCICompanion to give an error:
               	;Error: (Logger.sc) Expected an expression. (224, 36): "\"
									;\\
									`/)
								(= i (+ j 1))
							)
							((== c `.)
								(= l (- j i))
							)
						)
					)
					(if (== l 0)
						(= l (- j i))
					)
					
					(theDrv copyToFrom: 0 (str data?) i l)
				)
				
			)
			
			(FileIO FileClose logHandle)
			
		)
		
		;;NOW, open log file!
		(thePath format: {%s.log} (String StrGetData sysLogPath))
		(if (and
				firstNote
				(or
					;; file doesn't exist so start new one
					(== -1 (= logHandle (FileIO FileOpen (thePath data?) fRead)))
					;; file exists, ask whether to overwrite
					(and (str format: {Log file \"%s\" exists} (thePath data?)) FALSE)
					(Print
						font:				999,
						addText:			str,
						addButtonBM: 	SAVE 2 0 FALSE {Append to it} 0 12,
						addButtonBM: 	SAVE 2 0 TRUE	{Overwrite it} 75 12,
						init:
					)
				)
			)
			(FileIO FileClose logHandle)
			(= logHandle (FileIO FileOpen (thePath data?) fTrunc))
		else
			(= logHandle (FileIO FileOpen (thePath data?) fAppend))
		)
		
		(if (== -1 logHandle)
			(Print
				font:			999,
				addTextF:	{Error: Couldn't open %s} thePath,
				init:
			)
			
		else
			
			;;ษอออออออออออออออออออออออออออออออออออออป
			;;บ    Match Fields With Import Items   บ
			;;ฬอออออออออออออออออออออออออออออออออออออน
			;;บ Number of Items in Import File: 63  บ
			;;ฬออออออหออออออออออออออหออออออหออออออออน
			;;บ Item บ Field Name   บ Type บ Length บ
			;;ฬออออออฮออออออออออออออฮออออออฮออออออออน
			;;บ   1  บ BUG-NUMBER   บ   N  บ     7  บ
			;;บ   2  บ BACKWARD     บ   B  บ     7  บ
			;;บ   3  บ FORWARD      บ   F  บ     7  บ
			;;บ   4  บ GAME         บ   A  บ     6  บ
			
			(Log Txt {GAME} (theGame name?))
			
			;;บ   5  บ VERSION      บ   A  บ     7  บ
			
			(Log Txt {VERSION} (if version version else {unknown}))
			
			;;บ   6  บ QA-DATE      บ   D  บ     6  บ
			
			(Log Dat {QA-DATE})
			
			;;บ   7  บ ANALYST      บ   A  บ     3  บ
			
			(Log Txt {ANALYST} (QAinitials data?))
			
			;;บ   8  บ QA-STATUS    บ   A  บ     1  บ
			;;บ   9  บ RE-CHECK     บ   D  บ     6  บ
			;;บ  10  บ SEVERITY     บ   A  บ     1  บ
			
			(Log Txt {SEVERITY}
				(Print
 					font:				999,
					addText:			{Severity of bug...},
					addButtonBM: 	SAVE 2 0 {Fatal} 		{FATAL}		0 12,
					addButtonBM: 	SAVE 2 0 {Moderate} 	{MODERATE} 	70 12,
					addButtonBM: 	SAVE 2 0 {Minor} 		{MINOR} 		140 12,
					saveCursor:		TRUE,
					init:
				)
			)
			
			;;บ  11  บ REPRODUCIBLE บ   A  บ     3  บ

			(Log Txt {REPRODUCIBLE}
				(Print
					font:				999,
					addText:			{Reproducible?},
					addButtonBM:	SAVE 0 0	{Yes} 			{YES}			0 12,
					addButtonBM:	SAVE 0 0	{No} 				{NO}		  	55 12,
					addButtonBM:	SAVE 2 0	{Intermittent} {INTERMIT.}	110 12,
					saveCursor:		TRUE,
					init:
				)
			)

			;;บ  12  บ QA-COMMENT1  บ   A  บ    76  บ
			;;บ  13  บ QA-COMMENT2  บ   A  บ    76  บ
			;;บ  14  บ QA-COMMENT3  บ   A  บ    76  บ
			;;บ  15  บ QA-COMMENT4  บ   A  บ    76  บ
			;;บ  16  บ QA-COMMENT5  บ   A  บ    76  บ
			;;บ  17  บ QA-COMMENT6  บ   A  บ    76  บ
			;;บ  18  บ QA-COMMENT7  บ   A  บ    76  บ
			;;บ  19  บ QA-COMMENT8  บ   A  บ    76  บ
			;;บ  20  บ QA-COMMENT9  บ   A  บ    76  บ
			;;บ  21  บ QA-COMMENT10 บ   A  บ    76  บ
			
			(for 
				((= i 1) (= commented TRUE)) 
				(<= i MAXCOMMENTS)
				((++ i)) 
				
				(theToken format: {QA-COMMENT%d} i)
				(str format: {Comment line %d of %d:} i MAXCOMMENTS)
				(if commented
					(= commented (Log Inp (theToken data?) (str data?)))
				else
					(Log Txt (theToken data?) NULL)
				)
			)
			;;บ  22  บ DEPARTMENT   บ   A  บ     1  บ
			
			(Log Txt {DEPARTMENT}
				(Print
					font:				999,
					addText:			{Who can fix bug...},
					addButtonBM: 	SAVE 0 0 {Art} 			{ART} 		0 12,
					addButtonBM: 	SAVE 0 0 {Programming} 	{PROG} 		55 12,
					addButtonBM:	SAVE 0 0 {Music}			{MUSIC}		110 12,
					addButtonBM: 	SAVE 0 0 {Design} 		{DESIGN} 	165 12,
					saveCursor:		TRUE,
					init:
				)
			)
			
			;;บ  23  บ RESPONSE-BY  บ   A  บ     3  บ
			;;บ  24  บ RESP-DATE    บ   D  บ     6  บ
			;;บ  25  บ ACTION       บ   A  บ     1  บ
			;;บ  26  บ RESPONSE-1   บ   A  บ    76  บ
			;;บ  27  บ RESPONSE-2   บ   A  บ    76  บ
			;;บ  28  บ RESPONSE-3   บ   A  บ    76  บ
			;;บ  29  บ RESPONSE-4   บ   A  บ    76  บ
			;;บ  30  บ RESPONSE-5   บ   A  บ    76  บ
			
			;;บ  31  บ ROOM         บ   N  บ     4  บ
			
			(str format: {%5hu} curRoomNum) ;increased size to 5, also 
			(Log Txt {ROOM} (str data?))	  ; used unsigned short -gtp
			
			;;บ  32  บ ROOM-SCRIPT  บ   A  บ    15  บ
			;;บ  33  บ ROOM-STATE   บ   N  บ     5  บ
			
			(= i (curRoom script?))
			(Log Txt {ROOM-SCRIPT} (if i (i name?)))
			(Log Num {ROOM-STATE} (if i (i state?)))
			
			;;บ  34  บ EGO-X        บ   A  บ     3  บ
			;;บ  35  บ EGO-Y        บ   A  บ     3  บ
			;;บ  36  บ EGO-Z        บ   A  บ     3  บ
			
			(Log Num {EGO-X} (ego x?))
			(Log Num {EGO-Y} (ego y?))
			(Log Num {EGO-Z} (ego z?))
			
			;;บ  37  บ EGO-SCRIPT   บ   A  บ    15  บ
			;;บ  38  บ EGO-STATE    บ   N  บ     5  บ
			
			(= i (ego script?))
			(Log Txt {EGO-SCRIPT} (if i (i name?)))
			(Log Num {EGO-STATE} (if i (i state?)))
			
			;;บ  39  บ EGO-VIEW     บ   A  บ     4  บ
			;;บ  40  บ EGO-LOOP     บ   A  บ     2  บ
			;;บ  41  บ EGO-CEL      บ   A  บ     2  บ
			;;บ  42  บ EGO-PRIORITY บ   A  บ     3  บ
			;;บ  43  บ EGO-HEADING  บ   A  บ     3  บ
			
			(Log Num {EGO-VIEW} (ego view?))
			(Log Num {EGO-LOOP} (ego loop?))
			(Log Num {EGO-CEL} (ego cel?))
			(Log Num {EGO-PRIORITY} (ego priority?))
			(Log Num {EGO-HEADING} (ego heading?))
			
			;;บ  44  บ EGO-CYCLER   บ   A  บ    15  บ
			
			(Log Txt {CYCLER} (if (ego cycler?) ((ego cycler?) name?)))
			
			;;บ  45  บ EGO-MOVER    บ   A  บ    15  บ
			;;บ  46  บ MOVER-X      บ   A  บ     3  บ
			;;บ  47  บ MOVER-Y      บ   A  บ     3  บ
			;;บ  48  บ EGO-MOVESPD  บ   A  บ     4  บ
			
			(= i (ego mover?))
			(Log Txt {EGO-MOVER} (if i (i name?)))
			(Log Num {MOVER-X} 
				(cond 
					((not i) NULL)
					((i isMemberOf: PolyPath) (i finalX?))
					(else (i x?))
				)
			)
			(Log Num {MOVER-Y} 
				(cond 
					((not i) NULL)
					((i isMemberOf: PolyPath) (i finalY?))
					(else (i y?))
				)
			)
			
			(Log Num {EGO-MOVESPD} (ego moveSpeed?))
			
			;;บ  49  บ SIGNAL-BITS  บ   A  บ     4  บ
			
			(Log Hex {SIGNAL-BITS} (ego signal?))
			
			;;บ  50  บ ILLEGAL-BITS บ   A  บ     4  บ
			
			(Log Hex {ILLEGAL-BITS} (ego illegalBits?))
			
			;;บ  51  บ HOWFAST      บ   A  บ     1  บ
			
			(Log Num {HOWFAST} howFast)
			
			;;บ  52  บ ICONBAR      บ   A  บ    15  บ
			
			(Log Txt {ICONBAR} (if theIconBar (theIconBar name?)))
			(Log Txt {CUR-ICON} 
				(if (and theIconBar (theIconBar curIcon?))
					((theIconBar curIcon?) name?)
				)
			)
			;;บ  53  บ DETAIL-LEVEL บ   A  บ     2  บ
			
			(Log Num {DETAIL-LEVEL} (theGame detailLevel:))
			
			;;บ  54  บ CD-AUDIO     บ   A  บ     1  บ
			
			(Log Num {CD-AUDIO} (& msgType CD_MSG))
			
			;;บ  55  บ VIDEO-DRV    บ   A  บ     8  บ
			;;บ  56  บ SOUND-DRV    บ   A  บ     8  บ
			;;บ  57  บ AUDIO-DRV    บ   A  บ     8  บ
			;;บ  58  บ KEYBOARD-DRV บ   A  บ     8  บ
			;;บ  59  บ JOY-DRV      บ   A  บ     8  บ
			;;บ  60  บ MOUSE        บ   A  บ     1  บ
			
			(Log Txt {VIDEO-DRV} (videoDrvEntry data?))
			(Log Txt {SOUND-DRV} (soundDrvEntry data?))
			(Log Txt {AUDIO-DRV} (audioDrvEntry data?))
			(Log Txt {KEYBOARD-DRV} (kbdDrvEntry data?))
			(Log Txt {JOY-DRV} (joyDrvEntry data?))
			(Log Txt {MOUSE} (mouseDrvEntry data?))
			
			
			;;บ  61  บ LARGEST-HEAP บ   A  บ     5  บ
			;;บ  62  บ FREE-HEAP    บ   A  บ     5  บ
			;;บ  63  บ TOTAL-HUNK   บ   A  บ     3  บ
			;;บ  64  บ LARGEST-HUNK บ   A  บ     5  บ
			;;บ  65  บ FREE-HUNK    บ   A  บ     3  บ
			
			(Log Uns {LARGEST-HEAP} (MemoryInfo MemLargest))
			(Log Uns {FREE-HEAP} (MemoryInfo MemLargest))
			(Log Uns {TOTAL-HUNK} (>> (MemoryInfo MemLargest) 6))
			(Log Uns {LARGEST-HUNK} (MemoryInfo MemLargest))
			(Log Uns {FREE-HUNK} (>> (MemoryInfo MemLargest) 6))
			
			;;ศออออออสออออออออออออออสออออออสออออออออผ
			
			(FileIO FileFPuts logHandle {**********************************\r})
			(FileIO FileClose logHandle)
		)
		
		(thePath format: {%s.mem} (String StrGetData sysLogPath))
		(if (and
				(== -1 (= logHandle (FileIO FileOpen (thePath data?) fTrunc))) ;existing file
				(== -1 (= logHandle (FileIO FileOpen (thePath data?) fAppend))) ;new file
			)
			(Print
				font:			999,
				addTextF:	{Error: Couldn't open memory file %s!} (thePath data?),
				init:
			)
		else
			(FileIO FileFPuts logHandle (QAinitials data?))
			(FileIO FileFPuts logHandle {\n})
			(FileIO FileFPuts logHandle (cfgPath data?))
			(FileIO FileFPuts logHandle {\n})
			(FileIO FileClose logHandle)
		)
		
		(= inputFont saveInfont)
		
		(str dispose:)
		(cfgPath dispose:)
		(thePath dispose:)
		(theToken dispose:)
		(QAinitials dispose:)
		(kbdDrvEntry dispose:)
		(joyDrvEntry dispose:)
		(videoDrvEntry dispose:)
		(soundDrvEntry dispose:)
		(mouseDrvEntry dispose:)
		(audioDrvEntry dispose:)

		;; unload me
		(DisposeScript LOGGER)
	)
)
