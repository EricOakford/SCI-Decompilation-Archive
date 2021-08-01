;;; Sierra Script 1.0 - (do not remove this comment)
(script# LOGGER)
(include game.sh)
(use Main)
(use Intrface)
(use PolyPath)
(use System)

;;;(procedure
;;;	Log
;;;)

(public	sysLogger 0)

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

(procedure (Log how aLabel anArg &tmp [buffer 40] tm retval)
	(Format @buffer LOGGER 0 aLabel)
	(FileIO fileFPuts logHandle @buffer)
	(= buffer NULL)
	(switch how
		(Txt	(StrCpy @buffer (if anArg anArg else {})))
		(Num	(Format @buffer LOGGER 1 anArg))
		(Uns	(Format @buffer LOGGER 2 anArg))
		(Hex	(Format @buffer LOGGER 3 anArg))
		(Inp
			(if anArg
				(GetInput @buffer 66 anArg)
			)
			(= retval (StrLen @buffer))
		)
		(Tim
			; get system time stamp (minutes after 12:00)
			(= tm (GetTime SYSTIME2))
			(Format 
				@buffer 
				LOGGER 4
				(>> tm 11)
				(& (>> tm 5) %111111)
				(* (& tm %11111) 2)
			)
		)
		(Dat
			; get system date stamp (mm/dd/yy)
			(= tm (GetTime SYSDATE))
			(Format 
				@buffer 
				LOGGER 5
				(& (>> tm 5) %1111)
				(& tm %11111)
				(+ 80 (>> tm 9))
			)
		)
	)
	
	(StrCat @buffer {\r})
	(FileIO fileFPuts logHandle @buffer)
	retval
);procedure Log

(instance sysLogger of Code
	
	(method (doit 
			&tmp	i j l c firstNote theDrv commented saveInfont
			[str 40]
			[cfgPath 30]
			[thePath 30]
			[theToken 30]
			[QAinitials 5]
			[kbdDrvEntry 40]
			[joyDrvEntry 40]
			[videoDrvEntry 40]
			[soundDrvEntry 40]
			[mouseDrvEntry 40]
			[audioDrvEntry 40]
		)
		
		;;	initialize some variables:
		
		(= saveInfont inputFont)
		(= inputFont 999)
		
		(= str 
			(= thePath 
				(= theToken 
					(= kbdDrvEntry 
						(= joyDrvEntry 
							(= videoDrvEntry
								(= soundDrvEntry 
									(= mouseDrvEntry
										(= audioDrvEntry 0)))))))))
		
		(= firstNote (== 0 (StrLen @sysLogPath)))
		
		;; if path argument is NULL, we need some initial info 
		(if firstNote
			(while (not (< 0 (StrLen @thePath) 19))
				(GetInput @thePath 40 
					{Enter drive letter, path and your name\n
					(no extension, max 40 characters)}
				)
			)
			(StrCpy @sysLogPath @thePath 40)
		)
		
		;;access "memory variable" file to seed data
		(Format @thePath LOGGER 6 @sysLogPath)
		
		(if (!= -1 (= logHandle (FileIO fileOpen @thePath fRead)))
			(FileIO fileFGets @QAinitials 80 logHandle)
			(FileIO fileFGets @cfgPath 80 logHandle)
			(FileIO fileClose logHandle)
		else
			(= QAinitials 0)
			(StrCpy @cfgPath {resource.cfg})
		)
		
		(if firstNote
			(GetInput @QAinitials 5
				{Enter your initials (up to 3 characters):})
			(StrAt @QAinitials 3 NULL)
		)
		
		;; read configuration file
		(while
			(and 
				(or (not firstNote)
					(GetInput @cfgPath 30 
						{Enter configuration file name (or hit return to skip):})
				)
				(== -1 (= logHandle (FileIO fileOpen @cfgPath fRead)))
				(StrAt @cfgPath 0)
			)
			(StrAt @cfgPath 0 0)
		);while
		
		(if (!= -1 logHandle) ; opened config file
			
			(while (FileIO fileFGets @str 80 logHandle)
				
				; strip leading whitespace
				(for 
					((= i 0)) 
					(and (= c (StrAt @str i)) (OneOf c TAB SPACEBAR)) 
					((++ i))
				);for
				
				(for ((= j 0))
					(and
						(= c (StrAt @str i))
						(not (OneOf c `= `: TAB SPACEBAR))
					) 
					((++ i) (++ j))
					(StrAt @theToken j c)
				)
				
				(StrAt @theToken j NULL)
				
				(= theDrv
					(cond
						((== 0 (StrCmp @theToken {kbdDrv}))		@kbdDrvEntry)
						((== 0 (StrCmp @theToken {joyDrv}))		@joyDrvEntry)
						((== 0 (StrCmp @theToken {videoDrv}))	@videoDrvEntry)
						((== 0 (StrCmp @theToken {soundDrv}))	@soundDrvEntry)
						((== 0 (StrCmp @theToken {mouseDrv}))	@mouseDrvEntry)
						((== 0 (StrCmp @theToken {audioDrv}))	@audioDrvEntry)
					);cond
				);=
				
				(if theDrv
					
					;;skip trailing white space
					(while (and (= c (StrAt @str i)) (OneOf c `= `: TAB SPACEBAR))
						(++ i)
					)
					
					;;find last delimiter and period
					(for ((= j i) (= l 0)) (= c (StrAt @str j)) ((++ j))
						(if (OneOf c `: `/) ;`\\ `/)
							(= i (+ j 1))
						)
						(if (== c `.)
							(= l (- j i))
						)
					);for
					
					(if (== l 0)
						(= l (- j i))
					)
					
					(StrCpy theDrv (+ @str i) l)
					
				);if theDrv
				
			);while
			
			(FileIO fileClose logHandle)
			
		);if opened config file
		
		;;NOW, open log file!
		(Format @thePath LOGGER 7 @sysLogPath)
		(if (and
				firstNote
				(or
					;; file doesn't exist so start new one
					(== -1 (= logHandle (FileIO fileOpen @thePath fRead)))
					;; file exists, ask whether to overwrite
					(and (Format @str LOGGER 8 @thePath) FALSE)
					(Print @str
						#button: {append to it} FALSE
						#button: {overwrite it} TRUE
					)
				);or
			);and
			
			(FileIO fileClose logHandle)
			(= logHandle (FileIO fileOpen @thePath fTrunc))
		else
			(= logHandle (FileIO fileOpen @thePath fAppend))
		);if-else
		
		(if (== -1 logHandle)
			
			(Printf LOGGER 9 @thePath)
			
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
			
			(Log Txt {VERSION} version)
			
			;;บ   6  บ QA-DATE      บ   D  บ     6  บ
			
			(Log Dat {QA-DATE})
			
			;;บ   7  บ ANALYST      บ   A  บ     3  บ
			
			(Log Txt {ANALYST} @QAinitials)
			
			;;บ   8  บ QA-STATUS    บ   A  บ     1  บ
			;;บ   9  บ RE-CHECK     บ   D  บ     6  บ
			;;บ  10  บ SEVERITY     บ   A  บ     1  บ
			
			(Log Txt {SEVERITY}
				(Print 
					"Severity of bug..."
					#button: {FATAL} {F}
					#button: {NON-FATAL} {N}
					#button: {SUGGESTION} {S}
				)
			)
			
			;;บ  11  บ QA-COMMENT1  บ   A  บ    76  บ
			;;บ  12  บ QA-COMMENT2  บ   A  บ    76  บ
			;;บ  13  บ QA-COMMENT3  บ   A  บ    76  บ
			;;บ  14  บ QA-COMMENT4  บ   A  บ    76  บ
			;;บ  15  บ QA-COMMENT5  บ   A  บ    76  บ
			;;บ  16  บ QA-COMMENT6  บ   A  บ    76  บ
			;;บ  17  บ QA-COMMENT7  บ   A  บ    76  บ
			;;บ  18  บ QA-COMMENT8  บ   A  บ    76  บ
			;;บ  19  บ QA-COMMENT9  บ   A  บ    76  บ
			;;บ  20  บ QA-COMMENT10 บ   A  บ    76  บ
			
			(for 
				((= i 1) (= commented TRUE)) 
				(<= i MAXCOMMENTS)
				((++ i)) 
				
				(Format @theToken LOGGER 10 i)
				(Format @str LOGGER 12 i MAXCOMMENTS)
				(if commented
					(= commented (Log Inp @theToken @str))
				else
					(Log Txt @theToken NULL)
				)
			)
			;;บ  21  บ DEPARTMENT   บ   A  บ     1  บ
			
			(Log Txt {DEPARTMENT}
				(Print 
					LOGGER 13
					#button: {PROG}   {P}  
					#button: {ART}    {A}  
					#button: {DESIGN} {D}
				)
			)
			
			;;บ  22  บ RESPONSE-BY  บ   A  บ     3  บ
			;;บ  23  บ RESP-DATE    บ   D  บ     6  บ
			;;บ  24  บ ACTION       บ   A  บ     1  บ
			;;บ  25  บ RESPONSE-1   บ   A  บ    76  บ
			;;บ  26  บ RESPONSE-2   บ   A  บ    76  บ
			;;บ  27  บ RESPONSE-3   บ   A  บ    76  บ
			;;บ  28  บ RESPONSE-4   บ   A  บ    76  บ
			;;บ  29  บ RESPONSE-5   บ   A  บ    76  บ
			
			;;บ  30  บ ROOM         บ   N  บ     4  บ
			
			(Log Num {ROOM} curRoomNum)
			
			;;บ  31  บ ROOM-SCRIPT  บ   A  บ    15  บ
			;;บ  32  บ ROOM-STATE   บ   N  บ     5  บ
			
			(= i (curRoom script?))
			(Log Txt {ROOM-SCRIPT} (if i (i name?)))
			(Log Num {ROOM-STATE} (if i (i state?)))
			
			;;บ  33  บ EGO-X        บ   A  บ     3  บ
			;;บ  34  บ EGO-Y        บ   A  บ     3  บ
			;;บ  35  บ EGO-Z        บ   A  บ     3  บ
			
			(Log Num {EGO-X} (ego x?))
			(Log Num {EGO-Y} (ego y?))
			(Log Num {EGO-Z} (ego z?))
			
			;;บ  36  บ EGO-SCRIPT   บ   A  บ    15  บ
			;;บ  37  บ EGO-STATE    บ   N  บ     5  บ
			
			(= i (ego script?))
			(Log Txt {EGO-SCRIPT} (if i (i name?)))
			(Log Num {EGO-STATE} (if i (i state?)))
			
			;;บ  38  บ EGO-VIEW     บ   A  บ     4  บ
			;;บ  39  บ EGO-LOOP     บ   A  บ     2  บ
			;;บ  40  บ EGO-CEL      บ   A  บ     2  บ
			;;บ  41  บ EGO-PRIORITY บ   A  บ     3  บ
			;;บ  42  บ EGO-HEADING  บ   A  บ     3  บ
			
			(Log Num {EGO-VIEW} (ego view?))
			(Log Num {EGO-LOOP} (ego loop?))
			(Log Num {EGO-CEL} (ego cel?))
			(Log Num {EGO-PRIORITY} (ego priority?))
			(Log Num {EGO-HEADING} (ego heading?))
			
			;;บ  43  บ EGO-CYCLER   บ   A  บ    15  บ
			
			(Log Txt {CYCLER} (if (ego cycler?) ((ego cycler?) name?)))
			
			;;บ  44  บ EGO-MOVER    บ   A  บ    15  บ
			;;บ  45  บ MOVER-X      บ   A  บ     3  บ
			;;บ  46  บ MOVER-Y      บ   A  บ     3  บ
			;;บ  47  บ EGO-MOVESPD  บ   A  บ     4  บ
			
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
			
			;;บ  48  บ SIGNAL-BITS  บ   A  บ     4  บ
			
			(Log Hex {SIGNAL-BITS} (ego signal?))
			
			;;บ  49  บ HOWFAST      บ   A  บ     1  บ
			
			(Log Num {HOWFAST} howFast)
			
			;;บ  50  บ ICONBAR      บ   A  บ    15  บ
			
			(Log Txt {ICONBAR} (if theIconBar (theIconBar name?)))
			(Log Txt {CUR-ICON} 
				(if (and theIconBar (theIconBar curIcon?))
					((theIconBar curIcon?) name?)
				)
			)
			;;บ  51  บ DETAIL-LEVEL บ   A  บ     2  บ
			
			(Log Num {DETAIL-LEVEL} (theGame detailLevel:))
			
			;;บ  52  บ CD-AUDIO     บ   A  บ     1  บ
			
			(Log Num {CD-AUDIO} cDAudio)
			
			;;บ  53  บ VIDEO-DRV    บ   A  บ     8  บ
			;;บ  54  บ SOUND-DRV    บ   A  บ     8  บ
			;;บ  55  บ AUDIO-DRV    บ   A  บ     8  บ
			;;บ  56  บ KEYBOARD-DRV บ   A  บ     8  บ
			;;บ  57  บ JOY-DRV      บ   A  บ     8  บ
			;;บ  58  บ MOUSE        บ   A  บ     1  บ
			
			(Log Txt {VIDEO-DRV} @videoDrvEntry)
			(Log Txt {SOUND-DRV} @soundDrvEntry)
			(Log Txt {AUDIO-DRV} @audioDrvEntry)
			(Log Txt {KEYBOARD-DRV} @kbdDrvEntry)
			(Log Txt {JOY-DRV} @joyDrvEntry)
			(Log Txt {MOUSE} @mouseDrvEntry)
			
			
			;;บ  59  บ LARGEST-HEAP บ   A  บ     5  บ
			;;บ  60  บ FREE-HEAP    บ   A  บ     5  บ
			;;บ  61  บ TOTAL-HUNK   บ   A  บ     3  บ
			;;บ  62  บ LARGEST-HUNK บ   A  บ     5  บ
			;;บ  63  บ FREE-HUNK    บ   A  บ     3  บ
			
			(Log Uns {LARGEST-HEAP} (MemoryInfo LargestPtr))
			(Log Uns {FREE-HEAP} (MemoryInfo FreeHeap))
			(Log Uns {TOTAL-HUNK} (>> (MemoryInfo TotalHunk) 6))
			(Log Uns {LARGEST-HUNK} (MemoryInfo LargestHandle))
			(Log Uns {FREE-HUNK} (>> (MemoryInfo FreeHunk) 6))
			
			;;ศออออออสออออออออออออออสออออออสออออออออผ
			
			(FileIO fileFPuts logHandle {**********************************\r})
			(FileIO fileClose logHandle)
		);if opened log file
		
		(Format @thePath LOGGER 6 @sysLogPath)
		(if (and
				(== -1 (= logHandle (FileIO fileOpen @thePath fTrunc))) ;existing file
				(== -1 (= logHandle (FileIO fileOpen @thePath fAppend))) ;new file
			)
			(Printf LOGGER 14 @thePath)
		else
			(FileIO fileFPuts logHandle @QAinitials)
			(FileIO fileFPuts logHandle {\n})
			(FileIO fileFPuts logHandle @cfgPath)
			(FileIO fileFPuts logHandle {\n})
			(FileIO fileClose logHandle)
		)
		
		(= inputFont saveInfont)
		
		;; unload me
		(DisposeScript LOGGER)
	)
)




