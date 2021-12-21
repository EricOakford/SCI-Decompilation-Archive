;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64879)
(include sci.sh)
(use Main)
(use aeAnimPlayerClass)
(use String)
(use Print)
(use System)


(class aeMessageSeqClass of aeCommand
	(properties
		scratch 0
		type $0012
		size 6
		vTiles 0
		whoToCue 0
		seq 0
		bmpOff -1
	)
	
	(method (init theVTiles theSeq theBmpOff)
		(= vTiles theVTiles)
		(= seq theSeq)
		(if (== 3 argc) (= bmpOff theBmpOff))
	)
	
	(method (save param1)
		(super save: param1)
		(param1 writeWord: seq)
	)
	
	(method (bImAHog param1 param2 &tmp temp0)
		(if (== bmpOff -1)
			(= bmpOff
				(DoAudio
					1
					(whoToCue testHotspotVerb?)
					(whoToCue noun?)
					(whoToCue verb?)
					(whoToCue case?)
					seq
				)
			)
			(DoAudio
				3
				(whoToCue testHotspotVerb?)
				(whoToCue noun?)
				(whoToCue verb?)
				(whoToCue case?)
				seq
			)
			(MonoOut LOOKUP_ERROR seq temp0)
		)
		(param1 isButtonDown: param2 vTiles (+ vTiles bmpOff))
	)
)

(class msg of aeCastMember
	(properties
		scratch 0
		whoToCue 0
		getBitmap 0
		voBG -1
		oSpecialSync -1
		nSpecialSelector 0
		nCutoff 0
		bSpecial 0
		type $0008
		back 234
		noun 0
		verb 0
		case 0
		addHotspotVerb 0
		deleteHotspotVerb 0
		testHotspotVerb 0
	)
	
	(method (edit &tmp newStr_2 newStr_3 newStr_4 newStr_5 newStr_6 newStr_7 newStr temp7 temp8 temp9 temp10 temp11 printInit theAddHotspotVerb temp14 newStrAsInteger)
		(= temp8 (+ (= temp7 5) 100))
		(= temp9 5)
		(= temp10 (+ 7 (Font 0 inputFont)))
		(= temp11 0)
		(= newStr (Str new:))
		(= newStr_2 (Str new:))
		(= newStr_3 (Str new:))
		(= newStr_4 (Str new:))
		(= newStr_5 (Str new:))
		(= newStr_6 (Str new:))
		(= newStr_7 (Str new:))
		(newStr
			format: LOOKUP_ERROR ((whoToCue bTileBorder?) normalize?)
		)
		(Print
			x: 20
			y: 20
			width: 200
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: LOOKUP_ERROR
			addText: LOOKUP_ERROR temp7 temp9
			addEdit: newStr 5 temp8 temp9
			first: 3
			addText: LOOKUP_ERROR temp7 (= temp9 (+ temp9 temp10))
			addEdit:
				newStr_2
				5
				temp8
				(newStr_7 format: LOOKUP_ERROR (curRoom number?))
			addText: LOOKUP_ERROR temp7 (= temp9 (+ temp9 temp10))
			addEdit: newStr_3 5 temp8 (newStr_6 format: LOOKUP_ERROR 1)
			addText: LOOKUP_ERROR temp7 (= temp9 (+ temp9 temp10))
			addEdit: newStr_4 5 temp8 (newStr_5 format: LOOKUP_ERROR 1)
			addText: LOOKUP_ERROR temp7 (= temp9 (+ temp9 temp10))
			addEdit: newStr_5 5 temp8 (newStr_4 format: LOOKUP_ERROR 0)
			addText: LOOKUP_ERROR temp7 (= temp9 (+ temp9 temp10))
			addEdit: newStr_6 5 temp8 (newStr_3 format: LOOKUP_ERROR 1)
			addText: LOOKUP_ERROR temp7 (= temp9 (+ temp9 temp10))
			addEdit: newStr_7 5 temp8 (newStr_2 format: LOOKUP_ERROR 1)
			addButton: -1 LOOKUP_ERROR temp7 (= temp9 (+ temp9 temp10))
		)
		(if (> (= printInit (Print init:)) 0)
			(if (>= (= newStrAsInteger (newStr asInteger:)) 0)
				(= noun (newStr_2 asInteger:))
				(= verb (newStr_3 asInteger:))
				(= case (newStr_4 asInteger:))
				(= addHotspotVerb (newStr_5 asInteger:))
				(= deleteHotspotVerb (newStr_6 asInteger:))
				(= testHotspotVerb (newStr_7 asInteger:))
				(if
				(Message 0 testHotspotVerb noun verb case addHotspotVerb)
					(nCutoff
						format:
							LOOKUP_ERROR
							getBitmap
							name
							nSpecialSelector
							testHotspotVerb
							noun
							verb
							case
							addHotspotVerb
							deleteHotspotVerb
					)
					(= temp11 1)
					(= theAddHotspotVerb addHotspotVerb)
					(while (<= theAddHotspotVerb deleteHotspotVerb)
						(= temp14
							(DoAudio
								1
								testHotspotVerb
								noun
								verb
								case
								theAddHotspotVerb
							)
						)
						(DoAudio
							3
							testHotspotVerb
							noun
							verb
							case
							theAddHotspotVerb
						)
						(self
							doHeld: aeMessageSeqClass newStrAsInteger theAddHotspotVerb temp14
						)
						(= newStrAsInteger (+ newStrAsInteger temp14 21))
						(newStr format: LOOKUP_ERROR newStrAsInteger)
						(nCutoff cat: newStr)
						(++ theAddHotspotVerb)
					)
				else
					(MonoOut
						LOOKUP_ERROR
						testHotspotVerb
						noun
						verb
						case
						addHotspotVerb
					)
				)
			else
				(MonoOut LOOKUP_ERROR)
			)
		)
		(newStr dispose:)
		(newStr_2 dispose:)
		(newStr_3 dispose:)
		(newStr_4 dispose:)
		(newStr_5 dispose:)
		(newStr_6 dispose:)
		(newStr_7 dispose:)
		(return temp11)
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
			addTitle: LOOKUP_ERROR
			addText: LOOKUP_ERROR temp0 temp2
			addText: LOOKUP_ERROR temp0 (= temp2 (+ temp2 temp3))
			addText: 'LOOKUP_ERROR' temp0 (= temp2 (+ temp2 temp3))
			addText: 'LOOKUP_ERROR' temp0 (= temp2 (+ temp2 temp3))
			addText: 'LOOKUP_ERROR' temp0 (= temp2 (+ temp2 temp3))
			addButton: -1 LOOKUP_ERROR temp0 (= temp2 (+ temp2 temp3))
			init:
		)
	)
	
	(method (save param1)
		(param1 writeWord: type)
		(param1 writeWord: 14)
		(param1 writeWord: getBitmap)
		(param1 writeWord: noun)
		(param1 writeWord: verb)
		(param1 writeWord: case)
		(param1 writeWord: addHotspotVerb)
		(param1 writeWord: deleteHotspotVerb)
		(param1 writeWord: testHotspotVerb)
	)
	
	(method (bImAHog param1 &tmp whoToCueNTopBorderHeight [temp1 5])
		((= whoToCueNTopBorderHeight (whoToCue nTopBorderHeight?))
			oMyGroup: 0 param1 back nSpecialSelector
		)
		(bSpecial
			eachElementDo: #bImAHog whoToCueNTopBorderHeight param1
		)
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

(class aeMessInterceptClass of Obj
	(properties
		scratch 0
		oPrivateText 0
		whoToCue 0
		noun 0
		verb 0
		case 0
		addHotspotVerb 0
		deleteHotspotVerb 0
		testHotspotVerb 0
	)
	
	(method (init)
		(= oPrivateText (msg new:))
		(oPrivateText
			getBitmap: ((whoToCue bTileBorder?) stopwalk?)
			whoToCue: whoToCue
			noun: noun
			verb: verb
			case: case
			addHotspotVerb: addHotspotVerb
			deleteHotspotVerb: deleteHotspotVerb
			testHotspotVerb: testHotspotVerb
			init:
		)
		(whoToCue add: oPrivateText)
	)
	
	(method (say param1)
		(oPrivateText
			doHeld: aeMessageSeqClass ((whoToCue bTileBorder?) normalize?) &rest
		)
	)
)
