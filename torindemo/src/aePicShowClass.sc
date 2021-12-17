;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64878)
(include sci.sh)
(use Main)
(use aeAnimPlayerClass)
(use String)
(use Print)
(use System)


(class aePicShowClass of aeCommand
	(properties
		scratch 0
		type $000e
		size 4
		vTiles 0
		whoToCue 0
	)
)

(class pic of aeCastMember
	(properties
		scratch 0
		whoToCue 0
		getBitmap 0
		voBG -1
		oSpecialSync -1
		nSpecialSelector 0
		nCutoff 0
		bSpecial 0
		type $000a
		back 234
		picture 0
	)
	
	(method (edit &tmp newStr temp1 temp2 temp3 temp4 temp5 printInit)
		(= temp5 0)
		(= newStr (Str new:))
		(= temp2 (+ (= temp1 5) 100))
		(= temp3 5)
		(= temp4 (+ 7 (Font 0 inputFont)))
		(Print
			x: 20
			y: 20
			width: 200
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: {Picture Edit}
			addText: {Number} temp1 temp3
			addEdit: newStr 5 temp2 temp3
			addButton: -1 {CANCEL} temp1 (= temp3 (+ temp3 temp4))
		)
		(if (> (= printInit (Print init:)) 0)
			(= picture (newStr asInteger:))
			(if (ResCheck 129 picture)
				(nCutoff
					format: {%d/%s = %s: %hu} getBitmap name nSpecialSelector picture
				)
				(= temp5 1)
				(self doSelect:)
			else
				(MonoOut {Pic %s not found} newStr)
			)
		)
		(newStr dispose:)
		(return temp5)
	)
	
	(method (doSelect &tmp temp0 temp1 temp2 temp3 temp4 temp5 printInit newStr newStrAsInteger)
		(= temp2 (+ (= temp1 5) 100))
		(= temp3 5)
		(= temp4 (+ 7 (Font 0 inputFont)))
		(= newStr (Str new:))
		(if (= temp5 (self nSelectValue: aePicShowClass))
			(newStr format: {%d} (temp5 vTiles?))
		else
			(newStr
				format: {%d} ((whoToCue bTileBorder?) normalize?)
			)
		)
		(Print
			x: 20
			y: 20
			width: 200
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: {Pic Edit}
			addText: {Tick} temp1 temp3
			addEdit: newStr 5 temp2 temp3
			addButton: -1 {CANCEL} temp1 (= temp3 (+ temp3 temp4))
		)
		(if (> (= printInit (Print init:)) 0)
			(if (>= (= newStrAsInteger (newStr asInteger:)) 0)
				(self doHeld: aePicShowClass newStrAsInteger)
			else
				(MonoOut {Ticks must >= 0})
			)
		)
		(newStr dispose:)
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
			addTitle: {Picture Help}
			addText: {A picture covers the background of the screen} temp0 temp2
			addText: {} temp0 (= temp2 (+ temp2 temp3))
			addText: {It supports these commands:} temp0 (= temp2 (+ temp2 temp3))
			addText:
				{ALT-E_____show____draw the picture}
				temp0
				(= temp2 (+ temp2 temp3))
			addButton: -1 {OK} temp0 (= temp2 (+ temp2 temp3))
			init:
		)
	)
	
	(method (save param1)
		(param1 writeWord: type)
		(param1 writeWord: 4)
		(param1 writeWord: getBitmap)
		(param1 writeWord: picture)
	)
	
	(method (handleEvent event)
		(if (== (event type?) evKEYBOARD)
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
	)
)

(class aePicInterceptClass of Obj
	(properties
		scratch 0
		oPrivateText 0
		whoToCue 0
		just -1
	)
	
	(method (init)
		(= oPrivateText (pic new:))
		(oPrivateText
			getBitmap: ((whoToCue bTileBorder?) stopwalk?)
			whoToCue: whoToCue
			just: just
			init:
		)
		(whoToCue add: oPrivateText)
	)
	
	(method (show)
		(oPrivateText
			doHeld: aePicShowClass ((whoToCue bTileBorder?) normalize?) &rest
		)
	)
)
