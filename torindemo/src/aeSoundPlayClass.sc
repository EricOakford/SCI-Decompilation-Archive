;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64877)
(include sci.sh)
(use Main)
(use aeAnimPlayerClass)
(use String)
(use Print)
(use System)


(class aeSoundPlayClass of aeCommand
	(properties
		scratch 0
		type $000c
		size 4
		vTiles 0
		whoToCue 0
	)
)

(class aeSoundStopClass of aeCommand
	(properties
		scratch 0
		type $000d
		size 4
		vTiles 0
		whoToCue 0
	)
)

(class sfx of aeCastMember
	(properties
		scratch 0
		whoToCue 0
		getBitmap 0
		voBG -1
		oSpecialSync -1
		nSpecialSelector 0
		nCutoff 0
		bSpecial 0
		type $000b
		back 234
		number 0
		loop 1
		bmpOn 0
	)
	
	(method (edit &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 printInit)
		(= temp6 0)
		(= temp0 (Str with: {0}))
		(= temp1 (Str with: {1}))
		(= temp3 (+ (= temp2 5) 100))
		(= temp4 5)
		(= temp5 (+ 7 (Font 0 inputFont)))
		(Print
			x: 20
			y: 20
			width: 200
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: {Sound Edit}
			addText: {Name} temp2 temp4
			addEdit: nSpecialSelector 32 temp3 temp4
			first: 3
			addText: {Number} temp2 (= temp4 (+ temp4 temp5))
			addEdit: temp0 5 temp3 temp5
			addText: {Loop} temp2 (= temp4 (+ temp4 temp5))
			addEdit: temp1 5 temp3 temp5
			addButton: -1 {CANCEL} temp2 (= temp4 (+ temp4 temp5))
		)
		(if (> (= printInit (Print init:)) 0)
			(= number (temp0 asInteger:))
			(if
			(or (ResCheck 141 number) (ResCheck 140 number))
				(= loop (temp1 asInteger:))
				(nCutoff
					format:
						{%d/%s = %s: %hu, loop %d times}
						getBitmap
						name
						nSpecialSelector
						number
						loop
				)
				(= temp6 1)
				(= bmpOn (DoAudio 1 number 1 0))
				(self doSelect:)
			else
				(MonoOut {sound %s not found} temp0)
			)
		)
		(temp0 dispose:)
		(temp1 dispose:)
		(return temp6)
	)
	
	(method (doSelect &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 printInit newStr_3 theVoBG temp10 newStr_4 theOSpecialSync temp13 newStr newStr_2)
		(= temp2 (+ (= temp1 5) 100))
		(= temp3 5)
		(= temp4 (+ 7 (Font 0 inputFont)))
		(= newStr (Str new:))
		(= newStr_2 (Str new:))
		(= newStr_3 (Str new:))
		(= newStr_4 (Str new:))
		(if (= temp5 (self nSelectValue: aeSoundPlayClass))
			(= temp10 (temp5 vTiles?))
		else
			(= temp10 ((whoToCue bTileBorder?) normalize?))
		)
		(if (= temp5 (self nSelectValue: aeSoundStopClass))
			(newStr_4 format: {%d} (temp5 vTiles?))
		)
		(newStr_3 format: {%d} temp10)
		(Print
			x: 20
			y: 20
			width: 200
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: newStr
			addText: newStr_2 temp1 temp3
			addText: {Start} temp1 (= temp3 (+ temp3 temp4))
			addEdit: newStr_3 5 temp2 (newStr_2 format: {len = %d} bmpOn)
			addText: {Stop} temp1 (= temp3 (+ temp3 temp4))
			addEdit:
				newStr_4
				5
				temp2
				(newStr format: {Sound %s Edit} nSpecialSelector)
			addButton: -1 {CANCEL} temp1 (= temp3 (+ temp3 temp4))
		)
		(if (> (= printInit (Print init:)) 0)
			(if (>= (= theVoBG (newStr_3 asInteger:)) 0)
				(= voBG theVoBG)
				(self oSelectNotify: aeSoundPlayClass)
				(self doHeld: aeSoundPlayClass theVoBG)
			else
				(MonoOut {Ticks must >= 0})
			)
			(if (== loop 0)
				(= oSpecialSync 32767)
			else
				(= oSpecialSync (+ voBG (* loop bmpOn)))
			)
			(if (newStr_4 size:)
				(if
				(>= (= theOSpecialSync (newStr_4 asInteger:)) 0)
					(if (<= theOSpecialSync (+ theVoBG bmpOn))
						(if (> theOSpecialSync theVoBG)
							(self oSelectNotify: aeSoundStopClass)
							(= oSpecialSync theOSpecialSync)
							(self doHeld: aeSoundStopClass theOSpecialSync)
						else
							(MonoOut {Stop must be > start})
						)
					else
						(MonoOut {Sound naturally ends at %d} (+ theVoBG bmpOn))
					)
				else
					(MonoOut {Ticks must >= 0})
				)
			)
		)
		(newStr dispose:)
		(newStr_3 dispose:)
		(newStr_4 dispose:)
	)
	
	(method (doClick &tmp temp0 temp1 temp2 temp3)
		(= temp1 (+ (= temp0 5) 100))
		(= temp2 5)
		(= temp3 (+ 7 (Font 0 inputFont)))
		(Print
			x: 20
			y: 20
			width: 400
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: {Sound Help}
			addText: {A sound makes a noise} temp0 temp2
			addText: {} temp0 (= temp2 (+ temp2 temp3))
			addText: {It supports these commands:} temp0 (= temp2 (+ temp2 temp3))
			addText:
				{ALT-E____start_______Start playing the sound}
				temp0
				(= temp2 (+ temp2 temp3))
			addText:
				{ALT-E____stop________Stop playing the sound}
				temp0
				(= temp2 (+ temp2 temp3))
			addButton: -1 {OK} temp0 (= temp2 (+ temp2 temp3))
			init:
		)
	)
	
	(method (oClickNotify)
	)
	
	(method (nClickMethod)
	)
	
	(method (save param1)
		(param1 writeWord: type)
		(param1 writeWord: 6)
		(param1 writeWord: getBitmap)
		(param1 writeWord: number)
		(param1 writeWord: loop)
	)
	
	(method (handleEvent event)
		(cond 
			((== (event type?) evKEYBOARD)
				(switch (event message?)
					(KEY_h
						(self doClick:)
						(event claimed: 1)
					)
					(KEY_ALT_e
						(self doSelect:)
						(event claimed: 1)
					)
				)
			)
			((== (event type?) evMOUSEBUTTON)
				(switch (event modifiers?)
					(emALT
						(if (self onMe: event)
							(self bEnabled:)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(class aeSoundInterceptClass of Obj
	(properties
		scratch 0
		oPrivateText 0
		whoToCue 0
		number 0
		loop 0
	)
	
	(method (init)
		(= oPrivateText (sfx new:))
		(oPrivateText
			getBitmap: ((whoToCue bTileBorder?) stopwalk?)
			whoToCue: whoToCue
			number: number
			loop: loop
			init:
		)
		(whoToCue add: oPrivateText)
	)
	
	(method (play)
		(oPrivateText
			doHeld: aeSoundPlayClass ((whoToCue bTileBorder?) normalize?) &rest
		)
	)
	
	(method (stop)
		(oPrivateText
			doHeld: aeSoundStopClass ((whoToCue bTileBorder?) normalize?) &rest
		)
	)
)
