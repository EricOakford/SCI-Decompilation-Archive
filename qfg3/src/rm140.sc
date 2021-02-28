;;; Sierra Script 1.0 - (do not remove this comment)
(script# 140)
(include sci.sh)
(use Main)
(use GloryWindow)
(use PMouse)
(use IconBar)
(use GControl)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm140 0
)

(local
	local0
	local1
	local2 =  71
	[local3 11]
	[local14 19]
	[local33 15] = [28 34 50 66 82 98 35 47 59 71 83 95 107 114 119]
	[local48 15] = [100 11 12 12 13 13 207 207 207 207 207 207 209 12 209]
	[local63 15] = [0 1 2 3 4 5 7 9 10 1 9 11 12 6 12]
	[local78 14] = [1 2 3 4 5 7 8 9 10 11 12 13 6 14]
	[local92 15] = [0 1 2 3 4 5 13 6 7 8 9 10 11 12 14]
	[theEgoStats 51] = [0 150 80 130 150 100 150 130 100 0 0 100 0 0 90 0 80 150 100 100 100 80 0 100 0 0 0 0 150 120 0 100 100 150 100 100 100 0 100 100 100 100 100 0 100 67 78 68 20 23 27]
	[theEgoStats_3 11] = [0 50 0 0 50 0 50 0 50]
	[theEgoStats_4 11] = [100 100 50 100 100 100 100 100 100 100 50]
	theHeroType =  1
	[local181 39]
	local220
	local221 =  50
	[theEgoStats_2 14]
	[theTheEgoStats 14]
	local250 =  1
	local251
	[local252 6] = [71 159 245 71 159 245]
	[local258 6] = [42 44 43 44 46 45]
	local264
	[local265 10]
	[local275 10]
	[local285 5]
)
(procedure (localproc_1008 &tmp temp0)
	(cond 
		((or (== heroType 3) (== heroType 0))
			(= temp0 1)
			(while (< temp0 15)
				(= [theEgoStats temp0] [egoStats (- temp0 1)])
				(++ temp0)
			)
			(if (not [egoStats 10]) (= [theEgoStats 11] 50))
			(= theHeroType 0)
		)
		((== heroType 1)
			(== temp0 16)
			(while (< temp0 30)
				(= [theEgoStats temp0] [egoStats (- temp0 16)])
				(++ temp0)
			)
			(= theHeroType 1)
		)
		((== heroType 2)
			(== temp0 31)
			(while (< temp0 45)
				(= [theEgoStats temp0] [egoStats (- temp0 31)])
				(++ temp0)
			)
			(= theHeroType 2)
		)
	)
	(StrCpy @local181 @userName)
	(= local220 (StrLen @local181))
)

(procedure (localproc_10b2 param1 param2)
	(Display
		&rest
		dsCOORD
		(+ param2 5)
		(+ param1 1)
		dsCOLOR
		58
		dsWIDTH
		30
		dsALIGN
		1
		dsFONT
		123
	)
	(Display
		&rest
		dsCOORD
		(+ param2 4)
		(+ param1 1)
		dsCOLOR
		82
		dsWIDTH
		30
		dsALIGN
		1
		dsFONT
		123
	)
)

(procedure (localproc_10fb &tmp [temp0 4] temp4)
	(= temp4 1)
	(while (< temp4 15)
		(= [egoStats (- temp4 1)] [theEgoStats_2 temp4])
		(++ temp4)
	)
	(if (not [egoStats 14]) (= [egoStats 14] 50))
	(= [oldStats 14] [egoStats 14])
	(if (== heroType 3) (= paladinStat [egoStats 14]))
	(DrawCel 145 6 0 92 134 15)
	(Message msgGET 140 8 0 5 1 @local285)
	(Format @temp0 @local285 local221)
	(Display
		@temp0
		dsCOORD
		97
		133
		dsCOLOR
		58
		dsWIDTH
		30
		dsALIGN
		1
		dsFONT
		123
	)
	(Display
		@temp0
		dsCOORD
		96
		133
		dsCOLOR
		43
		dsWIDTH
		30
		dsALIGN
		1
		dsFONT
		123
	)
	(DrawCel 145 6 0 89 172 15)
	(Message msgGET 140 8 0 5 1 @local285)
	(localproc_10b2
		170
		86
		(Format
			@temp0
			@local285
			(= [egoStats 16] (ego maxHealth:))
		)
	)
	(DrawCel 145 6 0 287 159 15)
	(localproc_10b2
		157
		284
		(Format
			@temp0
			@local285
			(= [egoStats 17] (ego maxStamina:))
		)
	)
	(DrawCel 145 6 0 287 172 15)
	(localproc_10b2
		170
		284
		(Format
			@temp0
			@local285
			(= [egoStats 18] (ego maxMana:))
		)
	)
	(DrawCel 145 6 0 285 132 15)
	(localproc_10b2
		130
		283
		(Format @temp0 @local285 [egoStats 14])
	)
)

(procedure (localproc_12ac param1 &tmp temp0 temp1 temp2)
	(= temp0 (FirstNode (param1 elements?)))
	(while temp0
		(= temp1 (NextNode temp0))
		(if (not (IsObject (= temp2 (NodeValue temp0))))
			(return)
		)
		(if (temp2 respondsTo: #owner) (temp2 owner: 0))
		(= temp0 temp1)
	)
)

(procedure (localproc_12f6 &tmp temp0)
	(StrCpy @userName @local181)
	(if (!= prevRoomNum 54) (localproc_12ac inventory))
	(ego get: 43)
	(ego get: 15)
	(ego get: 14 20)
	((inventory at: 14) amount: 20)
	(= numDinars 200)
	(switch theHeroType
		(3
			((inventory at: 1) state: 1)
			(ego get: 1)
			(ego get: 4)
			(ego get: 5)
			(if [egoStats 12]
				(= temp0 19)
				(while (< temp0 30)
					(if (not [egoStats temp0])
						(= [egoStats temp0] [theEgoStats_3 (- temp0 19)])
					)
					(++ temp0)
				)
			)
		)
		(0
			(ego get: 1)
			(ego get: 4)
			(ego get: 5)
			(if [egoStats 12]
				(= temp0 19)
				(while (< temp0 30)
					(if (not [egoStats temp0])
						(= [egoStats temp0] [theEgoStats_3 (- temp0 19)])
					)
					(++ temp0)
				)
			)
		)
		(1
			(ego get: 27)
			(ego get: 2)
			(if [egoStats 12]
				(= temp0 19)
				(while (< temp0 30)
					(if (not [egoStats temp0])
						(= [egoStats temp0] [theEgoStats_4 (- temp0 19)])
					)
					(++ temp0)
				)
			)
		)
		(else 
			(ego get: 7)
			(ego get: 6)
			(ego get: 2)
			(cond 
				((not (ego has: 10)) (ego get: 10 5))
				((< ((inventory at: 10) amount?) 5) ((inventory at: 10) amount: 5))
			)
			(if [egoStats 12]
				(= temp0 19)
				(while (< temp0 30)
					(if (not [egoStats temp0])
						(= [egoStats temp0] [theEgoStats_3 (- temp0 19)])
					)
					(++ temp0)
				)
			)
		)
	)
)

(procedure (localproc_1a39 param1 &tmp newEvent temp1)
	(fightChar setCycle: 0 stopUpd:)
	(mageChar setCycle: 0 stopUpd:)
	(thiefChar setCycle: 0 stopUpd:)
	(theTitle
		setLoop: 0
		cel: temp1
		x: [local252 param1]
		y:
			[local258
			(switch param1
				(0 (= temp1 0))
				(1 (= temp1 1))
				(2 (= temp1 2))
			)]
		priority: 0
		signal: 21
	)
	(if
		(or
			(theTitle onMe: (= newEvent (Event new:)))
			(and (not (HaveMouse)) (not local251))
		)
		(theGame
			setCursor: 999 1
			(switch param1
				(0 50)
				(1 140)
				(else  235)
			) 150
		)
	)
	(newEvent dispose:)
)

(procedure (localproc_1b0c &tmp [temp0 4])
	(Message msgGET 140 1 6 2 1 @local265)
	(Message msgGET 140 1 6 3 1 @local275)
	(quest init: show: dispose:)
	(Message msgGET 140 8 0 5 1 @temp0)
	(Format @temp0 @local285 local221)
	(Display
		@temp0
		dsCOORD
		97
		133
		dsCOLOR
		58
		dsWIDTH
		30
		dsALIGN
		1
		dsFONT
		123
	)
	(Display
		@temp0
		dsCOORD
		96
		133
		dsCOLOR
		43
		dsWIDTH
		30
		dsALIGN
		1
		dsFONT
		123
	)
	(return local0)
)

(instance rm140 of Rm
	(properties
		picture 140
		style $000a
		vanishingY 40
	)
	
	(method (init)
		(cSound number: 3 play: hold:)
		(if (== prevRoomNum 54)
			(curRoom picture: 145)
			(super init: &rest)
			(localproc_1008)
			(selectChar start: 1)
			(curRoom setScript: selectChar)
		else
			(super init: &rest)
			(Load rsVIEW 145)
			(super init:)
			(HandsOff)
			(theIconBar disable:)
			(theGame setCursor: 999 1 153 155)
			(= useSortedFeatures 0)
			(= pMouse 0)
			(mouseDownHandler
				add: theTitle fightChar mageChar thiefChar self
			)
			(keyDownHandler
				add: self theTitle fightChar mageChar thiefChar
			)
			(directionHandler add: self)
			(theTitle loop: 3 init:)
			(fightChar init:)
			(mageChar init:)
			(thiefChar init:)
			(roundRobin start: 0)
			(self setScript: roundRobin)
		)
	)
	
	(method (dispose)
		(DisposeScript 934)
		(UnLoad 128 145)
		(UnLoad 128 142)
		(= useSortedFeatures 1)
		(= pMouse PseudoMouse)
		(theIconBar enable:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if local250
			(if (== (event type?) evKEYBOARD)
				(switch (event message?)
					(KEY_TAB
						(event type: 64)
						(event message: 3)
					)
					(KEY_SHIFTTAB
						(event type: 64)
						(event message: 7)
					)
					(KEY_RETURN
						(= local250 0)
						(HandsOff)
						(switch (theTitle cel?)
							(0 (= theHeroType 0))
							(1 (= theHeroType 1))
							(2 (= theHeroType 2))
						)
						(roundRobin state: 11 cue:)
					)
					(else 
						(super handleEvent: event &rest)
					)
				)
			)
			(cond 
				((or (event claimed?) (not local251)) 0)
				((& (event type?) evJOYSTICK)
					(switch (event message?)
						(JOY_LEFT
							(event claimed: 1)
							(script
								state:
								(switch (theTitle cel?)
									(0 8)
									(1 1)
									(2 3)
									(else  7)
								)
								cue:
							)
						)
						(JOY_RIGHT
							(event claimed: 1)
							(script
								state:
								(switch (theTitle cel?)
									(0 3)
									(1 8)
									(2 1)
									(else  1)
								)
								cue:
							)
						)
					)
				)
				(else (super handleEvent: event &rest))
			)
		else
			(super handleEvent: event &rest)
		)
	)
)

(instance selectChar of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= origHeroType (= heroType theHeroType))
				(curRoom picture: 145)
				(DrawPic 145)
				(= userName 0)
				(= cycles 2)
			)
			(1
				(pointsAvail init:)
				(switch theHeroType
					(2 (= temp0 2))
					(0 (= temp0 1))
					(1 (= temp0 0))
				)
				(theChar setLoop: temp0 init:)
				(DrawPic 145 dpOPEN_PIXELATION)
				(= ticks 60)
			)
			(2
				(switch heroType
					(0
						(Message msgGET 140 2 0 0 1 @local3)
						(Display @local3 dsCOORD 140 31 dsFONT 123 dsCOLOR 0)
						(Display @local3 dsFONT 123 dsCOORD 139 30 dsCOLOR 30)
					)
					(1
						(Message msgGET 140 3 0 0 1 @local3)
						(Display @local3 dsCOORD 140 31 dsFONT 123 dsCOLOR 0)
						(Display @local3 dsFONT 123 dsCOORD 139 30 dsCOLOR 30)
					)
					(2
						(Message msgGET 140 4 0 0 1 @local3)
						(Display @local3 dsCOORD 145 31 dsFONT 123 dsCOLOR 0)
						(Display @local3 dsFONT 123 dsCOORD 144 30 dsCOLOR 30)
					)
					(3
						(Message msgGET 140 5 0 0 1 @local3)
						(Display @local3 dsCOORD 137 31 dsFONT 123 dsCOLOR 0)
						(Display @local3 dsFONT 123 dsCOORD 136 30 dsCOLOR 30)
					)
				)
				(startControls init: show: dispose:)
				(cSound fade: 0 2 5 1)
				(if local264
					(curRoom newRoom: 130)
				else
					(curRoom newRoom: 110)
				)
			)
		)
	)
)

(instance startControls of GameControls
	(properties)
	
	(method (init &tmp temp0)
		(if (!= prevRoomNum 54) (= local181 0))
		(self add: namePlate)
		(= temp0 1)
		(while (< temp0 6)
			(self
				add:
					((selectionIcon new:)
						nsLeft: [local48 temp0]
						nsTop: [local33 temp0]
						cel: [local63 temp0]
						maskCel: [local63 temp0]
						state: [local78 (- temp0 1)]
						yourself:
					)
			)
			(= [theTheEgoStats temp0]
				[theEgoStats (+ temp0 (* theHeroType 15))]
			)
			(= [theEgoStats_2 temp0]
				[theEgoStats (+ temp0 (* theHeroType 15))]
			)
			(++ temp0)
		)
		(self
			add:
				((selectionIcon new:)
					nsLeft: [local48 13]
					nsTop: [local33 temp0]
					cel: [local63 temp0]
					maskCel: [local63 temp0]
					state: [local78 12]
					yourself:
				)
		)
		(= [theTheEgoStats 13]
			[theEgoStats (+ 13 (* theHeroType 15))]
		)
		(= [theEgoStats_2 13]
			[theEgoStats (+ 13 (* theHeroType 15))]
		)
		(= temp0 6)
		(while (< temp0 13)
			(self
				add:
					((selectionIcon new:)
						nsLeft: [local48 temp0]
						nsTop: [local33 temp0]
						cel: [local63 temp0]
						maskCel: [local63 temp0]
						state: [local78 (- temp0 1)]
						yourself:
					)
			)
			(= [theTheEgoStats temp0]
				[theEgoStats (+ temp0 (* theHeroType 15))]
			)
			(= [theEgoStats_2 temp0]
				[theEgoStats (+ temp0 (* theHeroType 15))]
			)
			(++ temp0)
		)
		(self
			add:
				((selectionIcon new:)
					nsLeft: [local48 14]
					nsTop: [local33 temp0]
					cel: [local63 temp0]
					maskCel: [local63 temp0]
					state: [local78 13]
					yourself:
				)
		)
		(= [theTheEgoStats 14]
			[theEgoStats (+ 14 (* theHeroType 15))]
		)
		(= [theEgoStats_2 14]
			[theEgoStats (+ 14 (* theHeroType 15))]
		)
		(self add: startIcon cancelIcon dummyIcon)
		(super init: &rest)
	)
	
	(method (show)
		(= state (| state $0020))
		(DrawCel 145 5 0 118 154 15)
		(DrawCel 145 0 0 4 128 15)
		(self eachElementDo: #show)
		((= curIcon (= highlightedIcon (self at: 0)))
			highlight: 1
		)
		(localproc_10fb)
		(theGame
			setCursor: 999 1 (+ (curIcon nsLeft?) 5) (- (curIcon nsBottom?) 2)
		)
		(self doit: hide:)
	)
	
	(method (advance &tmp temp0 temp1)
		(asm
			ldi      1
			sat      temp1
code_0755:
			lst      temp1
			pToa     size
			le?     
			bnt      code_07aa
			pushi    64
			pushi    1
			lst      temp1
			pushi    #indexOf
			pushi    1
			pTos     highlightedIcon
			self     6
			add     
			push    
			pToa     size
			mod     
			push    
			self     6
			sat      temp0
			pushi    1
			push    
			callk    IsObject,  2
			not     
			bnt      code_0787
			pushi    1
			pushi    #first
			pushi    0
			self     4
			push    
			callk    NodeValue,  2
			sat      temp0
code_0787:
			lst      temp0
			lofsa    dummyIcon
			ne?     
			bnt      code_079d
			pushi    #signal
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      4
			and     
			not     
			bnt      code_079d
code_079d:
			lst      temp1
			ldi      1
			add     
			push    
			pToa     size
			mod     
			sat      temp1
			jmp      code_0755
code_07aa:
			pushi    218
			pushi    #view
			lst      temp0
			pTos     state
			ldi      32
			and     
			push    
			self     8
			ret     
		)
	)
	
	(method (dispatchEvent event &tmp eventY eventX eventType eventMessage eventModifiers temp5 temp6 highlightedIconState)
		(cond 
			(local220
				(if (& (startIcon signal?) $0004)
					(self enable: startIcon)
					(startIcon show:)
				)
			)
			((not (& (startIcon signal?) $0004)) (self disable: startIcon) (startIcon show:))
		)
		(= eventX (event x?))
		(= eventY (event y?))
		(= eventType (event type?))
		(= temp6 0)
		(= eventMessage (event message?))
		(= eventModifiers (event modifiers?))
		(= temp5 (self firstTrue: #onMe event))
		(if (IsObject event) (event dispose:))
		(if (IsObject temp5)
			(cond 
				((& eventType evJOYSTICK)
					(switch eventMessage
						(JOY_RIGHT
							(if (and highlightedIcon (highlightedIcon state?))
								(self select: temp5 1)
							)
						)
						(JOY_LEFT
							(if (and highlightedIcon (highlightedIcon state?))
								(self select: temp5 0)
							)
						)
						(JOY_UP
							(if (not highlightedIcon)
								(= highlightedIcon (self at: (- size 1)))
							)
							(self retreat:)
						)
						(JOY_DOWN
							(if (not highlightedIcon)
								(= highlightedIcon (self at: 0))
							)
							(self advance:)
						)
						(JOY_UPRIGHT
							(if (temp5 state?) (self select: temp5 3))
						)
						(JOY_DOWNRIGHT
							(if (temp5 state?) (self select: temp5 2))
						)
					)
				)
				((== eventType evNULL)
					(cond 
						((not (IsObject temp5))
							(if (IsObject highlightedIcon)
								(highlightedIcon highlight: 0)
								(= highlightedIcon 0)
							)
						)
						((and temp5 (!= temp5 highlightedIcon)) (= oldMouseY 0) (self highlight: temp5))
					)
				)
				((not (IsObject highlightedIcon)) 0)
				((== eventType evMOUSEBUTTON)
					(cond 
						((== temp5 namePlate) 0)
						((not (temp5 state?)) (self select: temp5 1))
						((== eventModifiers emSHIFT) (self select: temp5 3))
						(else (self select: temp5 2))
					)
				)
				((== eventType evKEYBOARD)
					(switch eventMessage
						(KEY_ESCAPE
							(= temp6 1)
							(return 0)
						)
						(KEY_RETURN
							(if
								(or
									(== highlightedIcon startIcon)
									(== highlightedIcon cancelIcon)
								)
								(self select: highlightedIcon 1)
							)
						)
						(KEY_TAB
							(cond 
								(
								(< (= highlightedIconState (highlightedIcon state?)) 1) 0)
								(
								(< (= highlightedIconState (highlightedIcon state?)) 7)
									(highlightedIcon highlight: 0)
									(= highlightedIcon
										(self at: (+ highlightedIconState 5))
									)
									(self advance:)
								)
								((< highlightedIconState 13)
									(highlightedIcon highlight: 0)
									(= highlightedIcon
										(self at: (- highlightedIconState 5))
									)
									(self retreat:)
								)
							)
						)
						(else 
							(cond 
								((!= highlightedIcon namePlate) 0)
								(
									(or
										(and (<= KEY_a eventMessage) (<= eventMessage KEY_z))
										(and (<= KEY_A eventMessage) (<= eventMessage KEY_Z))
										(and (<= KEY_0 eventMessage) (<= eventMessage KEY_9))
									)
									(self select: namePlate eventMessage)
								)
								((== eventMessage KEY_SPACE) (self select: namePlate eventMessage))
								((== eventMessage JOY_UPLEFT) (self select: namePlate eventMessage))
							)
						)
					)
				)
			)
		)
		(return temp6)
	)
)

(instance selectionIcon of IconI
	(properties
		view 145
		loop 1
		maskView 145
		maskLoop 2
		highlightColor 9
		lowlightColor 91
	)
	
	(method (show)
		(self highlight: 0)
		(= nsRight (+ nsLeft (if (< state 7) 102 else 111)))
		(= nsBottom (+ nsTop 12))
	)
	
	(method (select param1)
		(switch param1
			(1
				(cond 
					((not local221) 0)
					(
					(and (< local221 5) [theEgoStats_2 [local92 state]])
						(= [theEgoStats_2 [local92 state]]
							(+ [theEgoStats_2 [local92 state]] local221)
						)
						(= local221 0)
					)
					((not [theEgoStats_2 [local92 state]])
						(if (>= local221 30)
							(= [theEgoStats_2 [local92 state]]
								(+ [theEgoStats_2 [local92 state]] 10)
							)
							(= local221 (- local221 30))
						)
					)
					(else
						(= [theEgoStats_2 [local92 state]]
							(+ [theEgoStats_2 [local92 state]] 5)
						)
						(= local221 (- local221 5))
					)
				)
			)
			(0
				(cond 
					(
						(==
							[theEgoStats_2 [local92 state]]
							[theTheEgoStats [local92 state]]
						)
						0
					)
					(
						(and
							(== [theEgoStats_2 [local92 state]] 10)
							(not [theTheEgoStats [local92 state]])
						)
						(= [theEgoStats_2 [local92 state]] 0)
						(= local221 (+ local221 30))
					)
					(
						(and
							(< [theEgoStats_2 [local92 state]] 10)
							(not [theTheEgoStats [local92 state]])
						)
						(= local221
							(+ local221 20 [theEgoStats_2 [local92 state]])
						)
						(= [theEgoStats_2 [local92 state]] 0)
					)
					(
						(<
							(-
								[theEgoStats_2 [local92 state]]
								[theTheEgoStats [local92 state]]
							)
							5
						)
						(= local221
							(+
								local221
								(-
									[theEgoStats_2 [local92 state]]
									[theTheEgoStats [local92 state]]
								)
							)
						)
						(= [theEgoStats_2 [local92 state]]
							(-
								[theEgoStats_2 [local92 state]]
								(-
									[theEgoStats_2 [local92 state]]
									[theTheEgoStats [local92 state]]
								)
							)
						)
					)
					(else
						(= [theEgoStats_2 [local92 state]]
							(- [theEgoStats_2 [local92 state]] 5)
						)
						(= local221 (+ local221 5))
					)
				)
			)
			(3
				(cond 
					((not local221) 0)
					((not [theEgoStats_2 [local92 state]])
						(if (>= local221 30)
							(= [theEgoStats_2 [local92 state]]
								(+ [theEgoStats_2 [local92 state]] 10)
							)
							(= local221 (- local221 30))
						else
							0
						)
					)
					(else
						(= [theEgoStats_2 [local92 state]]
							(+ [theEgoStats_2 [local92 state]] 1)
						)
						(= local221 (- local221 1))
					)
				)
			)
			(2
				(cond 
					(
						(==
							[theEgoStats_2 [local92 state]]
							[theTheEgoStats [local92 state]]
						)
						0
					)
					(
						(and
							(== [theEgoStats_2 [local92 state]] 10)
							(not [theTheEgoStats [local92 state]])
						)
						(= [theEgoStats_2 [local92 state]] 0)
						(= local221 (+ local221 30))
					)
					(else
						(= [theEgoStats_2 [local92 state]]
							(- [theEgoStats_2 [local92 state]] 1)
						)
						(= local221 (+ local221 1))
					)
				)
			)
		)
		(self highlight: 1)
		(localproc_10fb)
	)
	
	(method (highlight param1 &tmp temp0 [temp1 4] temp5 temp6)
		(= temp0 (if (< state 7) 92 else 285))
		(DrawCel
			view
			6
			(if (< state 7) 0 else 1)
			temp0
			(+ nsTop 1)
			15
		)
		(if param1
			(DrawCel view loop cel nsLeft nsTop 15)
			(= temp5 43)
			(= temp6 58)
		else
			(DrawCel maskView maskLoop maskCel nsLeft nsTop 15)
			(= temp5 82)
			(= temp6 58)
		)
		(Message msgGET 140 8 0 5 1 @local285)
		(Display
			(Format @temp1 @local285 [theEgoStats_2 [local92 state]])
			dsCOORD
			(+ temp0 1)
			nsTop
			dsCOLOR
			temp6
			dsWIDTH
			25
			dsALIGN
			-1
			dsFONT
			123
		)
		(Message msgGET 140 8 0 5 1 @local285)
		(Display
			(Format @temp1 @local285 [theEgoStats_2 [local92 state]])
			dsCOORD
			temp0
			nsTop
			dsCOLOR
			temp5
			dsWIDTH
			25
			dsALIGN
			-1
			dsFONT
			123
		)
	)
	
	(method (onMe)
		(return
			(if (super onMe: &rest)
				(return (= helpVerb 1))
			else
				(if helpVerb (self highlight: 0) (= helpVerb 0))
				(return 0)
			)
		)
	)
)

(instance dummyIcon of IconI
	(properties
		view 145
		loop 0
		cel 0
		nsTop 0
		nsRight 319
		nsBottom 189
	)
	
	(method (show)
	)
	
	(method (select)
	)
	
	(method (highlight)
	)
)

(instance startIcon of IconI
	(properties
		view 145
		loop 3
		cel 0
		nsLeft 127
		nsTop 158
		maskView 145
		maskLoop 3
		maskCel 3
	)
	
	(method (select &tmp temp0 temp1)
		(return
			(if (& (startIcon signal?) $0004)
				(return 0)
			else
				(= local1 0)
				(if (and local221 (localproc_1b0c))
					(return 0)
				else
					(HandsOff)
					(localproc_12f6)
					(= temp0 1)
					(while (< temp0 15)
						(= [oldStats (- temp0 1)] [theEgoStats_2 temp0])
						(++ temp0)
					)
					(startControls state: (& (startControls state?) $ffdf))
				)
			)
		)
	)
	
	(method (highlight param1)
		(if param1
			(DrawCel view loop 2 nsLeft nsTop 15)
		else
			(DrawCel view loop 0 nsLeft nsTop 15)
		)
	)
)

(instance cancelIcon of IconI
	(properties
		view 145
		loop 4
		cel 0
		nsLeft 127
		nsTop 173
	)
	
	(method (select)
		(= local1 1)
		(if (localproc_1b0c)
			(HandsOff)
			(Bclr 144)
			(= local264 1)
			(startControls state: (& (startControls state?) $ffdf))
		)
	)
	
	(method (highlight param1)
		(if param1
			(DrawCel view loop 2 nsLeft nsTop 15)
		else
			(DrawCel view loop 0 nsLeft nsTop 15)
		)
	)
)

(instance namePlate of IconI
	(properties
		view 145
		loop 1
		cel 0
		nsLeft 63
		nsTop 3
		nsRight 263
		nsBottom 15
		maskView 145
		maskLoop 7
	)
	
	(method (show)
	)
	
	(method (select param1 &tmp [temp0 4])
		(TextSize @[temp0 0] @local181 3 0)
		(return
			(cond 
				((== param1 8)
					(if local220
						(StrAt @local181 (-- local220) 0)
						(DrawCel
							maskView
							maskLoop
							maskCel
							(+ nsLeft 39)
							(- nsTop 1)
							15
						)
						(self highlight: 1)
					)
				)
				((<= [temp0 3] 150)
					(StrAt @local181 local220 param1)
					(StrAt @local181 (++ local220) 0)
					(self highlight: 1)
				)
				(else (return 1))
			)
		)
	)
	
	(method (highlight param1 &tmp temp0 temp1 theLoop)
		(if param1
			(= theLoop loop)
			(= temp0 43)
			(= temp1 58)
		else
			(= theLoop 2)
			(= temp0 82)
			(= temp1 58)
		)
		(DrawCel view theLoop cel nsLeft nsTop 15)
		(Display
			@local181
			dsCOORD
			(+ nsLeft 41)
			(+ nsTop 4)
			dsCOLOR
			temp1
			dsWIDTH
			172
			dsALIGN
			0
			dsFONT
			3
		)
		(Display
			@local181
			dsCOORD
			(+ nsLeft 40)
			(+ nsTop 4)
			dsCOLOR
			temp0
			dsWIDTH
			172
			dsALIGN
			0
			dsFONT
			3
		)
	)
)

(instance theChar of View
	(properties
		x 162
		y 154
		view 140
		priority 14
		signal $0010
	)
)

(instance pointsAvail of View
	(properties
		x 92
		y 130
		view 145
		loop 6
		priority 15
		signal $0010
	)
)

(instance fightChar of Prop
	(properties
		x 78
		y 139
		view 140
		loop 1
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((not local251) (event claimed: 1) (return))
			((self onMe: event)
				(= local250 0)
				(HandsOff)
				(= theHeroType 0)
				(theTitle cel: 0)
				(roundRobin state: 11 cue:)
				(event claimed: 1)
			)
			(else (super handleEvent: event))
		)
	)
)

(instance mageChar of Prop
	(properties
		x 158
		y 139
		view 140
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((not local251) (event claimed: 1) (return))
			((self onMe: event)
				(= local250 0)
				(HandsOff)
				(= theHeroType 1)
				(theTitle cel: 1)
				(roundRobin state: 11 cue:)
				(event claimed: 1)
			)
			(else (super handleEvent: event))
		)
	)
)

(instance thiefChar of Prop
	(properties
		x 238
		y 140
		view 140
		loop 2
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((not local251) (event claimed: 1) (return))
			((self onMe: event)
				(= local250 0)
				(HandsOff)
				(= theHeroType 2)
				(theTitle cel: 2)
				(roundRobin state: 11 cue:)
				(event claimed: 1)
			)
			(else (super handleEvent: event))
		)
	)
)

(instance chooseTitle of View
	(properties
		x 159
		y 167
		view 141
		cel 3
	)
	
	(method (handleEvent)
	)
)

(instance theTitle of View
	(properties
		x -10
		y -20
		view 141
		cel 2
		signal $0015
	)
	
	(method (handleEvent event &tmp theTheHeroType)
		(cond 
			((not local251) (event claimed: 1) (roundRobin cue:) (return))
			((self onMe: (event x?) (event y?))
				(roundRobin state: 11 cue:)
				(= local250 0)
				(HandsOff)
				(switch (self x?)
					(71 (= theTheHeroType 0))
					(159 (= theTheHeroType 1))
					(245 (= theTheHeroType 2))
				)
				(= theHeroType theTheHeroType)
				(event claimed: 1)
			)
			(else (super handleEvent: event))
		)
	)
)

(instance roundRobin of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(User canControl: 1)
				(User canInput: 1)
				(chooseTitle init: addToPic:)
				(= seconds 2)
			)
			(1
				(Message msgGET 140 7 0 0 1 @local14)
				(Display @local14 dsFONT 123 dsCOORD 101 153 dsCOLOR 0)
				(Display @local14 dsFONT 123 dsCOORD 100 152 dsCOLOR 30)
				(= ticks 1)
			)
			(2
				(= seconds 0)
				(if (cast contains: fireBall) (fireBall dispose:))
				(localproc_1a39 0)
				(globalSound number: 946 play:)
				(fightChar setCycle: End self)
				(= local251 1)
			)
			(3
				(if (== (theTitle x?) 71)
					(Message msgGET 140 2 0 0 1 @local3)
					(Display @local3 dsFONT 123 dsCOORD 50 29 dsCOLOR 0)
					(Display @local3 dsFONT 123 dsCOORD 49 28 dsCOLOR 30)
				)
				(= seconds 3)
			)
			(4
				(fightChar setCycle: Beg self)
			)
			(5
				(Load rsVIEW 142)
				(= seconds 0)
				(if (cast contains: fireBall) (fireBall dispose:))
				(localproc_1a39 1)
				(globalSound number: 948 play:)
				(mageChar setCycle: CT 4 1 self)
			)
			(6
				(if (== (theTitle x?) 159)
					(Message msgGET 140 6 0 0 1 @local3)
					(Display @local3 dsFONT 123 dsCOORD 124 29 dsCOLOR 0)
					(Display @local3 dsFONT 123 dsCOORD 123 28 dsCOLOR 30)
				)
				(globalSound number: 947 play:)
				(fireBall loop: 0 x: 157 y: 94 init: setCycle: End self)
				(mageChar setCycle: End self)
			)
			(7)
			(8
				(mageChar setCycle: Beg self)
			)
			(9
				(if (cast contains: fireBall) (fireBall dispose:))
				(= seconds 0)
				(localproc_1a39 2)
				(thiefChar setCycle: End self)
			)
			(10
				(if (== (theTitle x?) 245)
					(Message msgGET 140 4 0 0 1 @local3)
					(Display @local3 dsFONT 123 dsCOORD 226 30 dsCOLOR 0)
					(Display @local3 dsFONT 123 dsCOORD 225 29 dsCOLOR 30)
				)
				(globalSound number: 949 play:)
				(thiefChar setCycle: Beg self)
			)
			(11
				(self changeState: (= state 1))
			)
			(12
				(mouseDownHandler
					delete: fightChar mageChar thiefChar theTitle self
				)
				(keyDownHandler
					delete: self fightChar mageChar thiefChar theTitle
				)
				(switch (theTitle cel?)
					(0 (fightChar hide:))
					(1
						(mageChar hide:)
						(fireBall hide:)
					)
					(2 (thiefChar hide:))
				)
				(theGame setCursor: theCursor 0)
				(curRoom drawPic: (curRoom picture?) 9)
				(= cycles 5)
			)
			(14
				(directionHandler delete: self)
				(cast eachElementDo: #dispose)
				(Animate 0)
				(User canControl: 0)
				(User canInput: 0)
				(curRoom setScript: selectChar)
			)
		)
	)
)

(instance fireBall of Actor
	(properties
		y 139
		view 142
		priority 14
		signal $4810
	)
)

(instance quest of GameControls
	(properties)
	
	(method (init)
		(theGame setCursor: 999)
		((= window (GloryWindow new:))
			top: 60
			left: 77
			bottom: 135
			right: 243
			priority: 15
			yourself:
		)
		(self add: titleIcon yesIcon noIcon)
		(super init: &rest)
	)
)

(instance titleIcon of IconI
	(properties
		view 935
		loop 2
		cel 0
		nsTop 0
		signal $0004
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 50])
		(if local1
			(Message msgGET 140 1 6 4 1 @temp0)
		else
			(Message msgGET 140 1 6 1 1 @temp0)
		)
		(Display @temp0 dsWIDTH 155 dsCOORD 5 3 dsCOLOR 17)
	)
)

(instance yesIcon of IconI
	(properties
		view 935
		loop 2
		cel 0
		nsTop 40
		signal $0101
		maskView 361
		maskLoop 3
	)
	
	(method (show)
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @local265 dsCOORD 20 (+ nsTop 3) dsCOLOR 17)
		(if (& signal $0004) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= local0 1)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight param1 &tmp temp0)
		(if param1
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= temp0 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= temp0 17)
		)
		(Display @local265 dsCOORD 20 (+ nsTop 3) dsCOLOR temp0)
	)
)

(instance noIcon of IconI
	(properties
		view 935
		loop 2
		cel 0
		nsTop 55
		signal $0101
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @local275 dsCOORD 20 (+ nsTop 3) dsCOLOR 17)
		(if (& signal $0004) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= local0 0)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight param1 &tmp temp0)
		(if param1
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= temp0 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= temp0 17)
		)
		(Display @local275 dsCOORD 20 (+ nsTop 3) dsCOLOR temp0)
	)
)
