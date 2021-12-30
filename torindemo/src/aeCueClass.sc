;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64880)
(include sci.sh)
(use Main)
(use aeMessageSeqClass)
(use aeAnimPlayerClass)
(use String)
(use Print)
(use System)


(class aeCueClass of aeCommand
	(properties
		scratch 0
		type $0010
		size 6
		vTiles 0
		whoToCue 0
		value 0
	)
	
	(method (init theVTiles theValue)
		(= vTiles theVTiles)
		(= value theValue)
	)
	
	(method (save param1)
		(super save: param1)
		(param1 writeWord: value)
	)
)

(class ext of aeCastMember
	(properties
		scratch 0
		whoToCue 0
		getBitmap 0
		voBG -1
		oSpecialSync -1
		nSpecialSelector 0
		nCutoff 0
		bSpecial 0
		type $0009
		back 234
	)
	
	(method (edit &tmp temp0 temp1 temp2 temp3 temp4 printInit)
		(= temp4 0)
		(= temp1 (+ (= temp0 5) 100))
		(= temp2 5)
		(= temp3 (+ 7 (Font 0 inputFont)))
		(Print
			x: 20
			y: 20
			width: 200
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: {External Edit}
			addText: {Ext object's name} temp0 temp2
			addEdit: nSpecialSelector 32 temp0 (= temp2 (+ temp2 temp3))
		)
		(if (> (= printInit (Print init:)) 0)
			(nCutoff
				format: {%d/%s = %s} getBitmap getBitmap name nSpecialSelector
			)
			(= temp4 1)
			(self doSelect:)
		)
		(return temp4)
	)
	
	(method (doSelect &tmp temp0 temp1 temp2 temp3 temp4 temp5 printInit newStr_2 theOSpecialSync temp9 temp10 newStr)
		(= temp2 (+ (= temp1 5) 100))
		(= temp3 5)
		(= temp4 (+ 7 (Font 0 inputFont)))
		(= newStr (Str new:))
		(= newStr_2 (Str new:))
		(= temp9 (Str with: {0}))
		(newStr format: {Cue '%s'} nSpecialSelector)
		(Print
			x: 20
			y: 20
			width: 200
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: newStr
			addText: {Tick} temp1 temp3
			addEdit: newStr_2 5 temp2 temp3
			addText: {value} temp1 (= temp3 (+ temp3 temp4))
			addEdit:
				temp9
				5
				temp2
				(newStr_2
					format: {%d} ((whoToCue bTileBorder?) normalize?)
				)
			addButton: -1 {CANCEL} temp1 (= temp3 (+ temp3 temp4))
		)
		(if (> (= printInit (Print init:)) 0)
			(if
			(>= (= theOSpecialSync (newStr_2 asInteger:)) 0)
				(= temp10 (temp9 asInteger:))
				(self doHeld: aeCueClass theOSpecialSync temp10)
				(= voBG (= oSpecialSync theOSpecialSync))
			else
				(MonoOut {Ticks must >= 0})
			)
		)
		(newStr_2 dispose:)
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
			addTitle: {External Help}
			addText: {An external allows the animation editor to connect} temp0 temp2
			addText:
				{objects ouside of the animation player. It's current}
				temp0
				(= temp2 (+ temp2 temp3))
			addText:
				{most useful feature is to allow the animation player to}
				temp0
				(= temp2 (+ temp2 temp3))
			addText:
				{cue an object outside of the animation.}
				temp0
				(= temp2 (+ temp2 temp3))
			addText: {} temp0 (= temp2 (+ temp2 temp3))
			addText: {It supports these commands:} temp0 (= temp2 (+ temp2 temp3))
			addText:
				{ALT-E_____cue____cue the external object}
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
	
	(method (save param1 &tmp temp0)
		(if
		(& (= temp0 (+ 1 (nSpecialSelector size:))) $0001)
			(nSpecialSelector at: temp0 0)
			(++ temp0)
		)
		(param1 writeWord: type)
		(param1 writeWord: (+ 4 temp0))
		(param1 writeWord: getBitmap)
		(param1 writeWord: temp0)
		(param1 write: (nSpecialSelector data?) temp0)
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

(class aeExternalInterceptClass of Obj
	(properties
		scratch 0
		oPrivateText 0
		whoToCue 0
		hotspotVerbList 0
		value 0
	)
	
	(method (init)
		(= oPrivateText (msg new:))
		(oPrivateText
			getBitmap: ((whoToCue bTileBorder?) stopwalk?)
			whoToCue: whoToCue
			init:
		)
		(whoToCue add: oPrivateText)
	)
	
	(method (cue)
		(oPrivateText
			doHeld: aeCueClass ((whoToCue bTileBorder?) normalize?) &rest
		)
	)
)
