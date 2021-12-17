;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64000)
(include sci.sh)
(use Main)
(use DialogPlane)
(use n64866)
(use NewUser)
(use MenuItem)
(use GenDialog)
(use Plane)
(use String)
(use CueObj)
(use File)

(public
	oMainMenu 0
	oMenuPopupPlane 1
	moHelp 2
	WritePrefs 3
	ReadPrefs 4
	moGetHelp 5
)

(local
	local0
)
(procedure (WritePrefs &tmp temp0)
	(= temp0 (Str with: gNewStr_2))
	(temp0 cat: {larry7.prf})
	(if (!= (temp0 weigh: (oFile name?)) 0)
		(oFile name: (Array 8 (temp0 data?)))
	)
	(temp0 dispose:)
	(if (not (oFile open: 2))
		(PrintDebug {Unable to create prefs file.})
		(return)
	)
	(oFile writeWord: 18247)
	(oFile writeWord: 6)
	(oFile writeWord: gOFileReadWord)
	(oFile writeWord: gOFileReadWord_2)
	(oFile writeWord: gOFileReadWord_3)
	(oFile writeWord: gOFileReadWord_4)
	(oFile writeWord: gOFileReadWord_5)
	(oFile writeWord: gOFileReadWord_6)
	(oFile writeWord: gOFileReadWord_7)
	(oFile writeWord: (theGame nGameSpeed?))
	(oFile writeWord: msgType)
	(oFile writeWord: gOFileReadWord_9)
	(oFile writeWord: gOFileReadWord_10)
	(oFile close:)
)

(procedure (ReadPrefs &tmp temp0 temp1 oFileReadWord_3 oFileReadWord oFileReadWord_2)
	(= temp0 (Str with: gNewStr_2))
	(temp0 cat: {larry7.prf})
	(if (!= (temp0 weigh: (oFile name?)) 0)
		(oFile name: (Array 8 (temp0 data?)))
	)
	(temp0 dispose:)
	(if
		(or
			(not (= temp1 (oFile open: 1)))
			(!= 18247 (= oFileReadWord (oFile readWord:)))
			(!= 6 (= oFileReadWord_2 (oFile readWord:)))
		)
		(if temp1 (oFile close:))
		(= gOFileReadWord 1)
		(= gOFileReadWord_2 1)
		(= gOFileReadWord_3 65)
		(= gOFileReadWord_4 60)
		(= gOFileReadWord_5 100)
		(= gOFileReadWord_6 0)
		(= gOFileReadWord_7 1)
		(theGame nGameSpeed: 6)
		(ego setSpeed: 6)
		(= msgType 2)
		(= gOFileReadWord_10 0)
		(WritePrefs)
	else
		(= gOFileReadWord (oFile readWord:))
		(= gOFileReadWord_2 (oFile readWord:))
		(= gOFileReadWord_3 (oFile readWord:))
		(= gOFileReadWord_4 (oFile readWord:))
		(= gOFileReadWord_5 (oFile readWord:))
		(= gOFileReadWord_6 (oFile readWord:))
		(= gOFileReadWord_7 (oFile readWord:))
		(= oFileReadWord_3 (oFile readWord:))
		(theGame nGameSpeed: oFileReadWord_3)
		(ego setSpeed: oFileReadWord_3)
		(= msgType (oFile readWord:))
		(= gOFileReadWord_9 (oFile readWord:))
		(= gOFileReadWord_10 (oFile readWord:))
		(oFile close:)
	)
)

(instance oFile of File
	(properties)
)

(instance oMenuPopupPlane of Plane
	(properties
		picture -2
		priority 600
	)
)

(instance oMenuPopupFeat of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= nsTop (= nsLeft 0))
		(= nsRight ((ScriptID 64000 0) getWidth:))
		(= nsBottom ((ScriptID 64000 0) getHeight:))
	)
	
	(method (handleEvent event)
		(if (self onMe: event) ((ScriptID 64000 0) show:))
		(return 0)
	)
)

(instance oKillMenuCheck of EventCode
	(properties)
	
	(method (handleEvent event &tmp temp0 temp1)
		(if (not (ScriptID 64000 0)) (return 0))
		(= temp1 0)
		(= temp0 (Clone event))
		(temp0 localize: (oMenuPopupFeat plane?))
		(if
			(and
				(user canControl:)
				(not (oMenuPopupFeat onMe: temp0))
				((ScriptID 64000 0) isVisible:)
				(not ((ScriptID 64000 0) isHilited:))
			)
			((ScriptID 64000 0) hide:)
			(= temp1 1)
		)
		(temp0 dispose:)
		(return temp1)
	)
)

(instance oMainMenu of MenuHandler
	(properties
		nStyle 1
		nUnhilitedBack 237
		nHilitedBack 238
		nHilitedFore 79
		nUnhilitedFore 87
		nFont 2510
		nLeading 1
		nHBorder 7
		vCheck -5534
	)
	
	(method (init)
		(super init: &rest)
		(gOEventHandler registerGlobalHandler: oKillMenuCheck)
		(oMenuPopupPlane
			init:
				0
				0
				((ScriptID 64000 0) getWidth:)
				((ScriptID 64000 0) getHeight:)
		)
		(oMenuPopupFeat init: oMenuPopupPlane)
	)
	
	(method (addItems)
		(oChildren addToEnd: moFile moGame moHelp)
	)
	
	(method (hide &tmp temp0)
		(super hide: &rest)
		(if ((ScriptID 64000 1) oMyFeatures?)
			(= temp0 ((ScriptID 64000 1) priority?))
			((ScriptID 64000 1) setPri: 1)
			(UpdatePlane (ScriptID 64000 1))
			((ScriptID 64000 1) setPri: temp0)
			(UpdatePlane (ScriptID 64000 1))
		)
	)
)

(instance moFile of MenuItem
	(properties
		nModule 14
		nCase 12
	)
	
	(method (addItems)
		(oChildren addToEnd: moNew moOpen moSave moExit)
	)
)

(instance moGame of MenuItem
	(properties
		nModule 14
		nCase 12
		nSeq 2
	)
	
	(method (addItems)
		(oChildren
			addToEnd: moVolume moSpeed moInventory moText moFilthUp moFilthDown moBoss
		)
	)
)

(instance moHelp of MenuItem
	(properties
		nModule 14
		nCase 12
		nSeq 3
	)
	
	(method (addItems)
		(oChildren addToEnd: moGetHelp moHints moSupport moAbout)
	)
)

(instance moNew of MenuItem
	(properties
		chShortcut 14
		nModule 14
		nCase 13
	)
	
	(method (doSelect)
		(proc64033_0
			(MakeMessageText 0 0 56 1 14)
			(Str with: global288)
		)
	)
)

(instance moOpen of MenuItem
	(properties
		chShortcut 15
		nModule 14
		nCase 13
		nSeq 2
	)
	
	(method (doSelect)
		(proc64866_1)
	)
)

(instance moSave of MenuItem
	(properties
		chShortcut 19
		nModule 14
		nCase 13
		nSeq 3
	)
	
	(method (doSelect)
		(theGame panelObj: theGame panelSelector: 82)
	)
)

(instance moExit of MenuItem
	(properties
		chShortcut 17
		nModule 14
		nCase 13
		nSeq 4
	)
	
	(method (doSelect)
		(if
			(not
				(proc64033_5
					(MakeMessageText 0 0 22 1 14)
					(MakeMessageText 0 0 21 1 14)
					(MakeMessageText 0 0 18 1 14)
				)
			)
			(return)
		)
		((ScriptID 64017 0) set: 5 6 7)
		((ScriptID 64017 0) clear: 8)
		(= global329 0)
		(curRoom newRoom: 200)
	)
)

(instance moVolume of MenuItem
	(properties
		chShortcut 13
		nModule 14
		nCase 14
	)
	
	(method (doSelect)
		((ScriptID 64028 0) init:)
	)
)

(instance moText of MenuItem
	(properties
		nModule 14
		nCase 14
		nSeq 5
	)
	
	(method (doSelect)
		(if (not (& msgType $0001))
			(= msgType 3)
			(proc64033_0
				(MakeMessageText 0 0 30 1 14)
				(Str with: global288)
			)
		else
			(= msgType 2)
			(proc64033_0
				(MakeMessageText 0 0 29 1 14)
				(Str with: global288)
			)
		)
		(WritePrefs)
	)
	
	(method (checkStatus)
		(if (& msgType $0001)
			(self check:)
		else
			(self uncheck:)
		)
	)
)

(instance moSpeed of MenuItem
	(properties
		nModule 14
		nCase 14
		nSeq 2
	)
	
	(method (doSelect)
		((ScriptID 64015 0) init:)
	)
)

(instance moFilthUp of MenuItem
	(properties
		chShortcut 6
		nModule 14
		nCase 14
		nSeq 6
	)
	
	(method (init)
		(= gNewStr_3 (Str new:))
		(super init: &rest)
	)
	
	(method (doSelect &tmp temp0 temp1 temp2 temp3 temp4)
		(= temp0 (MakeMessageText 0 0 39 1 14))
		(= temp1 (MakeMessageText 0 0 39 2 14))
		(= temp2 (GetNumMessages 14 0 0 48))
		(= temp3 (proc64896_11 1 temp2))
		(= temp4 (MakeMessageText 0 0 48 temp3 14))
		(gNewStr_3 cat: temp4)
		(gNewStr_3 cat: {_})
		(temp0 cat: gNewStr_3)
		(temp0 cat: temp1)
		(proc64033_0 temp0 (Str with: global288))
		(proc64896_7 temp1)
		(proc64896_7 temp4)
	)
)

(instance moFilthDown of MenuItem
	(properties
		chShortcut 4
		nModule 14
		nCase 14
		nSeq 7
	)
	
	(method (doSelect)
		(proc64033_0
			(MakeMessageText 0 0 55 1 14)
			(Str with: global288)
		)
	)
)

(instance moBoss of MenuItem
	(properties
		chShortcut 2
		nModule 14
		nCase 14
		nSeq 8
	)
	
	(method (doSelect)
		(proc64033_0
			(MakeMessageText 0 0 58 1 14)
			(Str with: global288)
		)
	)
)

(instance moInventory of MenuItem
	(properties
		chShortcut 9
		nModule 14
		nCase 14
		nSeq 3
	)
	
	(method (doSelect)
		(proc64033_0
			(MakeMessageText 0 0 57 1 14)
			(Str with: global288)
		)
	)
)

(instance moTitle of MenuItem
	(properties
		chShortcut 20
		nModule 14
		nCase 14
		nSeq 4
	)
	
	(method (doSelect &tmp [temp0 4])
		(proc64033_0
			(Str with: {Title Bar control not implemented.})
			(Str with: global288)
		)
	)
)

(instance moGetHelp of MenuItem
	(properties
		chShortcut 15104
		nModifiers 0
		nModule 14
		nCase 15
	)
	
	(method (doSelect &tmp temp0 temp1 temp2)
		(asm
			_line_   651
			_file_   {filename}
			_line_   652
			ldi      1
			sat      temp1
			_line_   653
code_0c1c:
			ldi      1
			bnt      code_0c94
			_line_   654
			pushi    6
			pushi    0
			pushi    0
			pushi    9
			lst      temp1
			pushi    14
			pushi    1
			calle    MakeMessageText,  12
			sat      temp2
			_line_   655
			bnt      code_0c85
			_line_   656
			_line_   657
			pushi    3
			_line_   658
			push    
			_line_   659
			pushi    5
			pushi    0
			pushi    0
			pushi    3
			pushi    1
			pushi    14
			calle    MakeMessageText,  10
			push    
			_line_   660
			pushi    5
			pushi    0
			pushi    0
			pushi    21
			pushi    1
			pushi    14
			calle    MakeMessageText,  10
			push    
			calle    proc64033_5,  6
			not     
			bnt      code_0c8d
			_line_   662
			_line_   663
			jmp      code_0c94
			jmp      code_0c8d
code_0c85:
			_line_   665
			_line_   666
			jmp      code_0c94
code_0c8d:
			_line_   668
			+at      temp1
			jmp      code_0c1c
code_0c94:
			_line_   670
			ret     
		)
	)
)

(instance moHints of MenuItem
	(properties
		chShortcut 15104
		nModifiers 0
		nModule 14
		nCase 15
		nSeq 3
	)
	
	(method (doSelect &tmp temp0 temp1 temp2)
		(asm
			_line_   682
			_file_   {filename}
			_line_   683
			ldi      1
			sat      temp1
			_line_   684
code_0cb2:
			ldi      1
			bnt      code_0d2a
			_line_   685
			pushi    6
			pushi    0
			pushi    0
			pushi    41
			lst      temp1
			pushi    14
			pushi    1
			calle    MakeMessageText,  12
			sat      temp2
			_line_   686
			bnt      code_0d1b
			_line_   687
			_line_   688
			pushi    3
			_line_   689
			push    
			_line_   690
			pushi    5
			pushi    0
			pushi    0
			pushi    3
			pushi    1
			pushi    14
			calle    MakeMessageText,  10
			push    
			_line_   691
			pushi    5
			pushi    0
			pushi    0
			pushi    21
			pushi    1
			pushi    14
			calle    MakeMessageText,  10
			push    
			calle    proc64033_5,  6
			not     
			bnt      code_0d23
			_line_   693
			_line_   694
			jmp      code_0d2a
			jmp      code_0d23
code_0d1b:
			_line_   696
			_line_   697
			jmp      code_0d2a
code_0d23:
			_line_   699
			+at      temp1
			jmp      code_0cb2
code_0d2a:
			_line_   701
			ret     
		)
	)
)

(instance moSupport of MenuItem
	(properties
		nModule 14
		nCase 15
		nSeq 4
	)
	
	(method (doSelect &tmp [temp0 4])
		(proc64033_0
			(MakeMessageText 0 0 4 1 14)
			(Str with: global288)
		)
	)
)

(instance moAbout of MenuItem
	(properties
		nModule 14
		nCase 15
		nSeq 5
	)
	
	(method (doSelect &tmp temp0 [temp1 3])
		(= temp0 (MakeMessageText 0 0 1 1 14))
		(proc64033_0 temp0 (Str with: global288))
	)
)
