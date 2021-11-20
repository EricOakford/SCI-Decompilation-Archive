;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16)
(include sci.sh)
(use Main)
(use GloryIconItem)
(use String)
(use Print)
(use Cursor)
(use Actor)
(use System)

(public
	gloryInv 0
)

(local
	newCast
	newCast_2
	local2
)
(procedure (localproc_00cc &tmp temp0)
	(theIconBar curInvIcon: 0 advanceCurIcon:)
	(theIconBar disableIcon: (theIconBar at: 6))
	(= temp0 (ScriptID 36 1))
	(temp0 signal: (| (temp0 signal?) $0008))
	(DeleteScreenItem temp0)
)

(instance gloryInv of QGInv
	(properties
		planeTop 29
		planeLeft 65
		numRow 3
		numCol 6
		rowMargin 1
		itemWide 27
		itemHigh 24
		iconMargin 5
		invLeft 16
	)
	
	(method (init)
		(= inventory self)
		(self
			helpIconItem: invHelp
			selectIcon: invSelect
			theSlider: invSlider
			okButton: ok
		)
		(self
			add:
				thePurse
				theManas
				theCures
				theHeals
				theRations
				theThrowdagger
				theRocks
				theCandle
				theCandy
				theFlask
				theWater
				theGruegoo
				theBonemeal
				theLockpick
				theKeyRing
				theOil
				theGrapnel
				theArmor
				theShield
				theSword
				theDagger
				theFlint
				theGarlic
				theGuildcard
				theToolkit
				theDarksign
				theHat
				theCorn
				thePiepan
				theBones
				theBerries
				theHumorbar
				theRehydrator
				theDoll
				theBlackbird
				theCloth
				theChicken
				(ScriptID 35 17)
				(ScriptID 35 18)
				(ScriptID 35 19)
				(ScriptID 35 12)
				(ScriptID 35 13)
				(ScriptID 35 14)
				(ScriptID 35 15)
				(ScriptID 35 16)
				(ScriptID 35 0)
				(ScriptID 35 1)
				(ScriptID 35 2)
				(ScriptID 35 3)
				(ScriptID 35 4)
				(ScriptID 35 5)
				(ScriptID 35 6)
				(ScriptID 35 7)
				(ScriptID 35 8)
				(ScriptID 35 9)
				(ScriptID 35 10)
				(ScriptID 35 11)
				invDummy1
				invLook
				invSelect
				invHelp
				ok
				invDummy2
				invSlider
				invUpArrow
				invDownArrow
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			eachElementDo: #modNum 16
			state: 2048
		)
		(super init:)
		(ego get: 0)
		(thePurse amount: 0)
	)
	
	(method (hide)
		(thePlane deleteCast: newCast)
		(super hide: &rest)
		(newCast dispose:)
	)
	
	(method (showSelf theOwner &tmp temp0 temp1 temp2 temp3 temp4 temp5)
		(= local2 (= owner theOwner))
		(= temp1 (CelWide 932 8 0))
		(= temp0 (CelHigh 932 8 0))
		(= temp2 (/ (- 320 temp1) 2))
		(= temp4 (/ (- 153 temp0) 2))
		(= temp3 (+ temp2 temp1))
		(= temp5 (+ temp4 temp0))
		(plane back: 108)
		(thePlane addCast: (= newCast (Cast new:)))
		((View new:)
			view: 932
			loop: 8
			cel: 0
			setPri: 254
			posn: temp2 temp4
			init: newCast
		)
		((View new:)
			view: 932
			loop: 9
			cel: 0
			setPri: 254
			posn: 53 143
			init: newCast
		)
		(self getInvNum:)
		(= currentRow (= curIndex 0))
		((invSlider theSlider?) y: 41)
		(UpdateScreenItem (invSlider theSlider?))
		(super showSelf: theOwner)
	)
	
	(method (drawIcons)
		(super drawIcons: &rest)
		(plane addCast: (= newCast_2 (Cast new:)))
		((View new:)
			view: 932
			loop: 10
			cel: 2
			posn: 0 26
			init: newCast_2
		)
		((View new:)
			view: 932
			loop: 10
			cel: 0
			posn: 3 28
			init: newCast_2
		)
		((View new:)
			view: 932
			loop: 10
			cel: 1
			posn: 170 28
			init: newCast_2
		)
		(invSlider
			theSlider:
				((View new:)
					x: 180
					y: 41
					view: 932
					loop: 12
					cel: 0
					init: newCast_2
					yourself:
				)
		)
	)
	
	(method (drawInvItems &tmp temp0)
		(= temp0 0)
		(if (< (thePurse amount?) 1)
			(thePurse amount: 1)
			(= temp0 1)
		)
		(super drawInvItems:)
		(if temp0 (thePurse amount: 0))
	)
)

(instance invSlider of SliderIcon
	(properties
		noun 53
		nsLeft 180
		nsTop 39
		nsRight 191
		nsBottom 98
		x 180
		y 39
		signal $0083
		message 4
		mainView 932
		mainLoop 15
		helpVerb 9
	)
	
	(method (select &tmp temp0 temp1 [temp2 4] temp6)
		(while
			(not
				(OneOf
					((= temp0 ((user curEvent?) new:)) type?)
					2
					8
					64
				)
			)
			(temp0 localize: (gloryInv plane?))
			(cond 
				((< (temp0 y?) 41) (theSlider y: 41))
				((> (temp0 y?) 92) (theSlider y: 92))
				(else (theSlider y: (temp0 y?)))
			)
			(UpdateScreenItem theSlider)
			(FrameOut)
		)
		(temp0 localize: (gloryInv plane?))
		(cond 
			(
				(or
					(not
						(= temp1
							(/ (+ (- (temp0 y?) 41) 8) (gloryInv interval?))
						)
					)
					(< (temp0 y?) 41)
				)
				(self updateSlider: 0 0)
				(while (> (gloryInv currentRow?) 0)
					(gloryInv currentRow: (- (gloryInv currentRow?) 1))
					(gloryInv setCurIndex: -6)
				)
			)
			(
			(or (== temp1 (gloryInv totalRow?)) (> (temp0 y?) 92))
				(self updateSlider: 0 1)
				(while
				(< (gloryInv currentRow?) (gloryInv totalRow?))
					(gloryInv currentRow: (+ (gloryInv currentRow?) 1))
					(gloryInv setCurIndex: 6)
				)
			)
			(else
				(theSlider y: (+ 41 (* temp1 (gloryInv interval?))))
				(UpdateScreenItem theSlider)
				(cond 
					(
					(> (= temp6 (- temp1 (gloryInv currentRow?))) 0)
						(while temp6
							(gloryInv currentRow: (+ (gloryInv currentRow?) 1))
							(gloryInv setCurIndex: 6)
							(-- temp6)
						)
					)
					((< temp6 0)
						(while temp6
							(gloryInv currentRow: (- (gloryInv currentRow?) 1))
							(gloryInv setCurIndex: -6)
							(++ temp6)
						)
					)
				)
			)
		)
		(gloryInv drawInvItems:)
		(return 0)
	)
	
	(method (updateSlider param1 param2 &tmp temp0)
		(if (> argc 1)
			(if param2 (theSlider y: 92) else (theSlider y: 41))
		else
			(theSlider y: (+ (theSlider y?) param1))
		)
		(UpdateScreenItem theSlider)
	)
)

(instance invUpArrow of GloryIconItem
	(properties
		noun 2
		nsLeft 180
		nsTop 30
		nsRight 191
		nsBottom 38
		x 180
		y 30
		signal $0083
		message 4
		mainView 932
		mainLoop 14
		helpVerb 9
	)
	
	(method (select)
		(if (>= (- (gloryInv currentRow?) 1) 0)
			(gloryInv currentRow: (- (gloryInv currentRow?) 1))
			(if (== (gloryInv currentRow?) 0)
				(invSlider updateSlider: 0 0)
			else
				(invSlider updateSlider: (- 0 (gloryInv interval?)))
			)
			(gloryInv setCurIndex: -6 drawInvItems:)
		)
		(return 0)
	)
)

(instance invDownArrow of GloryIconItem
	(properties
		noun 1
		nsLeft 180
		nsTop 100
		nsRight 191
		nsBottom 108
		x 180
		y 100
		signal $0083
		message 4
		mainView 932
		mainLoop 13
		helpVerb 9
	)
	
	(method (select)
		(if
		(<= (+ (gloryInv currentRow?) 1) (gloryInv totalRow?))
			(gloryInv currentRow: (+ (gloryInv currentRow?) 1))
			(if
			(== (gloryInv currentRow?) (gloryInv totalRow?))
				(invSlider updateSlider: 0 1)
			else
				(invSlider updateSlider: (gloryInv interval?))
			)
			(gloryInv setCurIndex: 6 drawInvItems:)
		)
		(return 0)
	)
)

(instance invDummy1 of GloryIconItem
	(properties
		signal $0083
		mainView 932
		mainLoop 7
	)
	
	(method (onMe)
		(return 0)
	)
	
	(method (select)
	)
	
	(method (highlight)
	)
)

(instance invDummy2 of GloryIconItem
	(properties
		signal $0043
		mainView 932
		mainLoop 7
		mainCel 1
	)
	
	(method (onMe)
		(return 0)
	)
	
	(method (select)
	)
	
	(method (highlight)
	)
)

(instance invLook of GloryIconItem
	(properties
		noun 3
		modNum 16
		message 1
		mainView 932
		mainLoop 2
		cursorView 941
		cursorLoop 0
		cursorCel 0
		helpVerb 9
	)
)

(instance invSelect of GloryIconItem
	(properties
		noun 4
		modNum 16
		mainView 932
		cursorView 942
		cursorLoop 0
		cursorCel 0
		helpVerb 9
	)
)

(instance invHelp of GloryIconItem
	(properties
		noun 6
		modNum 16
		signal $0003
		message 9
		mainView 932
		mainLoop 1
		cursorView 949
		cursorLoop 0
		cursorCel 0
		helpVerb 9
	)
)

(instance ok of GloryIconItem
	(properties
		noun 7
		modNum 16
		signal $0003
		mainView 932
		mainLoop 3
		helpVerb 9
	)
)

(instance thePurse of NumInvItem
	(properties
		noun 8
		message 15
		mainView 905
		mainCel 5
		value 2
	)
	
	(method (doVerb theVerb &tmp newStr newStr_2 newStr_3 newStr_4)
		(= newStr (Str new:))
		(= newStr_2 (Str new:))
		(= newStr_3 (Str new:))
		(= newStr_4 (Str new:))
		(switch theVerb
			(1
				(if (or amount kopeks)
					(Print y: 165 addText: 0 theVerb 34 1 0 0 16 init:)
				)
				(if amount
					(Message msgGET 16 0 0 33 1 (newStr_2 data?))
					(newStr format: (newStr_2 data?) amount)
					(Print addText: (newStr data?) 0 0)
				)
				(if kopeks
					(Message msgGET 16 0 0 13 1 (newStr_3 data?))
					(newStr format: (newStr_3 data?) kopeks)
					(Print addText: (newStr data?) 0 (if amount 9 else 0))
				)
				(if (or amount kopeks)
					(Message msgGET 16 0 0 17 1 (newStr_4 data?))
					(newStr format: (newStr_4 data?) (+ amount kopeks))
					(Print
						addText: (newStr data?) 0 (if (and amount kopeks) 18 else 9)
					)
				else
					(Print addText: 0 15 12 1 0 0 16)
				)
				(Print y: 165 init:)
			)
			(101
				(Print y: 165 addText: 0 101 16 1 0 0 16 init:)
			)
			(else  (super doVerb: theVerb))
		)
		(newStr dispose:)
		(newStr_2 dispose:)
		(newStr_3 dispose:)
		(newStr_4 dispose:)
	)
)

(instance theManas of NumInvItem
	(properties
		noun 63
		message 16
		mainView 905
		mainLoop 6
		mainCel 9
		value 5
	)
)

(instance theCures of NumInvItem
	(properties
		noun 59
		message 17
		mainView 905
		mainLoop 2
		mainCel 8
		value 5
	)
)

(instance theHeals of NumInvItem
	(properties
		noun 62
		message 18
		mainView 905
		mainLoop 2
		mainCel 6
		value 5
	)
)

(instance theRations of NumInvItem
	(properties
		noun 29
		message 19
		mainView 905
		mainCel 8
		value 20
	)
)

(instance theThrowdagger of NumInvItem
	(properties
		noun 61
		message 37
		mainView 905
		mainLoop 2
		mainCel 2
		value 90
	)
)

(instance theRocks of NumInvItem
	(properties
		noun 38
		message 21
		mainView 905
		mainLoop 2
		mainCel 9
		value 30
	)
)

(instance theCandle of NumInvItem
	(properties
		noun 9
		message 22
		mainView 905
		mainLoop 6
		value 2
	)
)

(instance theCandy of NumInvItem
	(properties
		noun 10
		message 23
		mainView 905
		mainLoop 8
		mainCel 4
		value 2
	)
)

(instance theFlask of NumInvItem
	(properties
		noun 13
		message 24
		mainView 905
		mainLoop 2
		mainCel 5
		value 2
	)
)

(instance theWater of NumInvItem
	(properties
		noun 64
		message 25
		mainView 905
		mainCel 11
		value 20
	)
)

(instance theGruegoo of NumInvItem
	(properties
		noun 14
		message 26
		mainView 905
		mainLoop 6
		mainCel 10
		value 2
	)
)

(instance theBonemeal of NumInvItem
	(properties
		noun 15
		message 27
		mainView 905
		mainLoop 6
		mainCel 8
		value 2
	)
)

(instance theLockpick of NumInvItem
	(properties
		noun 68
		message 28
		mainView 905
		mainLoop 10
		mainCel 9
		value 2
	)
)

(instance theKeyRing of NumInvItem
	(properties
		noun 16
		message 29
		mainView 905
		mainLoop 2
		mainCel 15
		value 1
	)
	
	(method (doVerb theVerb &tmp newStr)
		(= newStr (Str new:))
		(switch theVerb
			(1
				(Print y: 165 addText: 16 1 0 1 0 0 16 init:)
				(if (not maskCel)
					(Print y: 165 addText: 0 29 12 1 0 0 16 init:)
				else
					(Print y: 165 addText: 16 1 0 2 0 0 16 init:)
					(if (& maskCel $0001)
						(Print y: 165 addText: 16 1 21 1 0 0 16 init:)
					)
					(if (& maskCel $0002)
						(Print y: 165 addText: 16 1 38 1 0 0 16 init:)
					)
					(if (& maskCel $0004)
						(Print y: 165 addText: 16 1 27 1 0 0 16 init:)
					)
					(if (& maskCel $0008)
						(Print y: 165 addText: 16 1 28 1 0 0 16 init:)
					)
					(if (& maskCel $0010)
						(Print y: 165 addText: 16 1 38 1 0 0 16 init:)
					)
					(if (& maskCel $0020)
						(Print y: 165 addText: 16 1 54 1 0 0 16 init:)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
		(newStr dispose:)
	)
)

(instance theOil of NumInvItem
	(properties
		noun 40
		message 32
		mainView 905
		mainCel 9
		value 30
	)
)

(instance theGrapnel of NumInvItem
	(properties
		noun 12
		message 33
		mainView 905
		mainLoop 2
		mainCel 11
		value 80
	)
)

(instance theArmor of NumInvItem
	(properties
		noun 57
		message 34
		mainView 905
		mainCel 2
		value 2100
	)
	
	(method (show)
		(if state (= cel 13) else (= cel 2))
		(super show: &rest)
	)
	
	(method (doVerb theVerb &tmp newStr newStr_2)
		(= newStr (Str new:))
		(= newStr_2 (Str new:))
		(switch theVerb
			(1
				(if (== local2 ego)
					(Message msgGET 16 0 theVerb 2 1 (newStr_2 data?))
					(if state
						(Message msgGET 16 57 1 37 1 (newStr data?))
					else
						(Message msgGET 16 57 1 36 1 (newStr data?))
					)
					(if (> amount 1)
						(Message msgGET 16 0 theVerb 1 1 (newStr_2 data?))
						(newStr_2
							format: (newStr_2 data?) (newStr data?) amount value
						)
					else
						(Message msgGET 16 0 theVerb 2 1 (newStr_2 data?))
						(newStr_2 format: (newStr_2 data?) (newStr data?) value)
					)
					(Print addText: (newStr_2 data?) y: 165 init:)
					(if state
						(Print addText: 57 theVerb 37 2 0 0 16)
					else
						(Print addText: 57 theVerb 36 2 0 0 16)
					)
					(Print y: 165 init:)
				else
					(if state
						(Message msgGET 16 57 1 37 1 (newStr data?))
					else
						(Message msgGET 16 57 1 36 1 (newStr data?))
					)
					(Message msgGET 16 0 theVerb 4 1 (newStr_2 data?))
					(newStr_2
						format: (newStr_2 data?) (newStr data?) chestAmout
					)
					(Print addText: (newStr_2 data?) y: 165 init:)
				)
			)
			(else  (super doVerb: theVerb))
		)
		(newStr dispose:)
		(newStr_2 dispose:)
	)
)

(instance theShield of NumInvItem
	(properties
		noun 11
		message 35
		mainView 905
		mainCel 3
		value 720
	)
	
	(method (show)
		(switch state
			(0 (= loop 0) (= cel 3))
			(1 (= loop 8) (= cel 13))
		)
		(super show: &rest)
	)
	
	(method (doVerb theVerb &tmp newStr newStr_2)
		(= newStr (Str new:))
		(= newStr_2 (Str new:))
		(switch theVerb
			(1
				(Message msgGET 16 0 theVerb 2 1 (newStr_2 data?))
				(switch state
					(0
						(Message msgGET 16 11 theVerb 0 1 (newStr data?))
					)
					(1
						(Message msgGET 16 11 theVerb 50 1 (newStr data?))
					)
				)
				(if (== local2 ego)
					(newStr_2 format: (newStr_2 data?) (newStr data?) value)
					(Print addText: (newStr_2 data?) y: 165 init:)
					(switch state
						(0
							(Print addText: 11 theVerb 0 2 0 0 16 y: 165 init:)
						)
						(1
							(Print addText: 11 theVerb 50 2 0 0 16 y: 165 init:)
						)
					)
				else
					(Message msgGET 16 0 theVerb 4 1 (newStr_2 data?))
					(newStr_2
						format: (newStr_2 data?) (newStr data?) chestAmout
					)
					(Print addText: (newStr_2 data?) y: 165 init:)
				)
			)
			(else  (super doVerb: theVerb))
		)
		(newStr dispose:)
		(newStr_2 dispose:)
	)
)

(instance theSword of NumInvItem
	(properties
		noun 54
		message 36
		mainView 905
		mainCel 12
		value 420
	)
	
	(method (show)
		(switch state
			(0 (= loop 0) (= cel 12))
			(1 (= cel (= loop 0)))
			(2 (= loop 0) (= cel 15))
			(3 (= loop 0) (= cel 14))
		)
		(super show: &rest)
	)
	
	(method (doVerb theVerb &tmp newStr newStr_2)
		(= newStr (Str new:))
		(= newStr_2 (Str new:))
		(switch theVerb
			(1
				(Message msgGET 16 0 theVerb 2 1 (newStr_2 data?))
				(switch state
					(0
						(Message msgGET 16 0 1 19 1 (newStr data?))
					)
					(1
						(Message msgGET 16 0 1 18 1 (newStr data?))
					)
					(2
						(Message msgGET 16 0 1 35 1 (newStr data?))
					)
					(3
						(Message msgGET 16 0 1 49 1 (newStr data?))
					)
				)
				(if (== local2 ego)
					(newStr_2 format: (newStr_2 data?) (newStr data?) value)
					(Print addText: (newStr_2 data?) y: 165 init:)
					(switch state
						(0
							(Print addText: 0 theVerb 19 2 0 0 16 y: 165 init:)
						)
						(1
							(Print addText: 0 theVerb 18 2 0 0 16 y: 165 init:)
						)
						(2
							(Print addText: 0 theVerb 35 2 0 0 16 y: 165 init:)
						)
						(3
							(Print addText: 0 theVerb 49 2 0 0 16 y: 165 init:)
						)
					)
				else
					(Message msgGET 16 0 theVerb 4 1 (newStr_2 data?))
					(newStr_2
						format: (newStr_2 data?) (newStr data?) chestAmout
					)
					(Print addText: (newStr_2 data?) y: 165 init:)
				)
			)
			(else  (super doVerb: theVerb))
		)
		(newStr dispose:)
		(newStr_2 dispose:)
	)
)

(instance theDagger of NumInvItem
	(properties
		noun 61
		message 37
		mainView 905
		mainCel 1
		value 2
	)
)

(instance theFlint of NumInvItem
	(properties
		noun 20
		message 39
		mainView 905
		mainCel 4
		value 2
	)
	
	(method (doVerb theVerb &tmp newStr newStr_2 temp2)
		(= newStr (Str new:))
		(= newStr_2 (Str new:))
		(if (== theVerb 76)
			(if (ego has: 5)
				((= temp2 (ScriptID 35 16)) cel: 10 state: 1)
				(Print addText: 69 39 0 1 0 0 16 y: 165 init:)
				(Cursor loop: 8 cel: 10)
				(SetCursor 905 8 10)
				(UpdateScreenItem temp2)
				((theIconBar at: 6) cursorCel: 10)
				((= temp2 (ScriptID 36 1)) cel: 10 show:)
				(UpdateScreenItem temp2)
				(ego solvePuzzle: 401 6)
				(curRoom doTorch: 1)
			else
				(Print addText: 69 39 55 1 0 0 16 y: 165 init:)
			)
		else
			(super doVerb: theVerb)
		)
		(newStr dispose:)
		(newStr_2 dispose:)
	)
)

(instance theGarlic of NumInvItem
	(properties
		noun 21
		message 40
		mainView 905
		mainLoop 2
		mainCel 1
		value 2
	)
)

(instance theGuildcard of NumInvItem
	(properties
		noun 23
		message 41
		mainView 905
		mainLoop 2
		mainCel 12
		value 2
	)
)

(instance theToolkit of NumInvItem
	(properties
		noun 22
		message 42
		mainView 905
		mainLoop 4
		mainCel 13
		value 30
	)
)

(instance theDarksign of NumInvItem
	(properties
		noun 24
		message 43
		mainView 905
		mainLoop 6
		mainCel 5
		value 2
	)
)

(instance theHat of NumInvItem
	(properties
		noun 25
		message 44
		mainView 905
		mainLoop 6
		mainCel 7
		value 2
	)
)

(instance theCorn of NumInvItem
	(properties
		noun 26
		message 45
		mainView 905
		mainLoop 6
		mainCel 4
		value 2
	)
)

(instance thePiepan of NumInvItem
	(properties
		noun 27
		message 46
		mainView 905
		mainLoop 2
		mainCel 10
		value 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(super doVerb: 1)
				(if (& maskCel $0001)
					(Print addText: 27 1 15 0 0 0 16 y: 165 init:)
				)
				(if (& maskCel $0002)
					(Print addText: 27 1 14 0 0 0 16 y: 165 init:)
				)
				(if (& maskCel $0004)
					(Print addText: 27 1 20 0 0 0 16 y: 165 init:)
				)
			)
			(48
				(if (== (theIconBar curInvIcon?) theBerries)
					(localproc_00cc)
				)
				(theBerries amount: 0)
				(= loop (= cel 6))
				(= maskCel (| maskCel $0001))
				(= value (+ value (theBerries value?)))
				(theBerries signal: 4)
				(DeleteScreenItem theBerries)
				(inventory drawInvItems:)
				(inventory advanceCurIcon:)
				(FrameOut)
				(Print y: 165 addText: noun 48 0 0 0 0 16 init:)
			)
			(27
				(if
					(and
						(== (theIconBar curInvIcon?) theBonemeal)
						(== (theBonemeal amount?) 1)
					)
					(localproc_00cc)
				)
				(theBonemeal amount: (- (theBonemeal amount?) 1))
				(= value (+ value (theBonemeal value?)))
				(if (< (theBonemeal amount?) 1)
					(theBonemeal signal: 4)
					(DeleteScreenItem theBonemeal)
				)
				(theFlask
					amount: (+ (theFlask amount?) 1)
					owner: (inventory owner?)
					signal: (& (theFlask signal?) $fffb)
				)
				(AddScreenItem theFlask)
				(inventory drawInvItems:)
				(inventory advanceCurIcon:)
				(FrameOut)
				(= maskCel (| maskCel $0004))
				(Print addText: noun 27 0 0 0 0 16 y: 165 init:)
			)
			(26
				(if
					(and
						(== (theIconBar curInvIcon?) theGruegoo)
						(== (theGruegoo amount?) 1)
					)
					(localproc_00cc)
				)
				(theGruegoo amount: (- (theGruegoo amount?) 1))
				(= loop (= cel 6))
				(= value (+ value (theGruegoo value?)))
				(if (< (theGruegoo amount?) 1)
					(theGruegoo signal: 4)
					(DeleteScreenItem theGruegoo)
				)
				(if (== (inventory owner?) ego)
					(theFlask amount: (+ (theFlask amount?) 1))
				else
					(theFlask chestAmout: (+ (theFlask chestAmout?) 1))
				)
				(theFlask
					owner: (inventory owner?)
					signal: (& (theFlask signal?) $fffb)
				)
				(AddScreenItem theFlask)
				(inventory drawInvItems:)
				(inventory advanceCurIcon:)
				(FrameOut)
				(= maskCel (| maskCel $0002))
				(Print addText: noun 26 0 0 0 0 16 y: 165 init:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theBones of NumInvItem
	(properties
		noun 28
		message 47
		mainView 905
		mainLoop 6
		mainCel 15
		value 2
	)
)

(instance theBerries of NumInvItem
	(properties
		noun 30
		message 48
		mainView 905
		mainLoop 6
		mainCel 14
		value 2
	)
)

(instance theHumorbar of NumInvItem
	(properties
		noun 31
		message 49
		mainView 905
		mainLoop 4
		value 2
	)
)

(instance theRehydrator of NumInvItem
	(properties
		noun 33
		message 51
		mainView 905
		mainCel 11
		value 2
	)
)

(instance theDoll of NumInvItem
	(properties
		noun 34
		message 52
		mainView 905
		mainCel 7
		value 2
	)
)

(instance theBlackbird of NumInvItem
	(properties
		noun 58
		message 53
		mainView 905
		mainLoop 4
		mainCel 11
		value 30
	)
)

(instance theCloth of NumInvItem
	(properties
		noun 35
		message 54
		mainView 905
		mainLoop 6
		mainCel 11
		value 2
	)
)

(instance theChicken of NumInvItem
	(properties
		noun 36
		message 55
		mainView 905
		mainLoop 4
		mainCel 2
		value 2
	)
)
