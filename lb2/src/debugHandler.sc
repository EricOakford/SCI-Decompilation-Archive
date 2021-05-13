;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include sci.sh)
(use Main)
(use NameFeatureCode)
(use Intrface)
(use Print)
(use PolyEdit)
(use WriteFtr)
(use Feature)
(use Window)
(use User)
(use Actor)
(use System)

(public
	debugHandler 0
)

(local
	[local0 27]
)
(procedure (localproc_0052)
	(if (OneOf (curRoom style?) 11 12 13 14)
		(curRoom drawPic: (curRoom picture?) 100 style: 100)
	)
)

(instance debugHandler of Feature
	(properties)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
		(DisposeScript 974)
		(DisposeScript 10)
		(DisposeScript 25)
	)
	
	(method (handleEvent event &tmp [temp0 150] [temp150 10] temp160 newEvent castFirst theUserFont temp164 temp165 temp166 temp167 temp168 temp169 temp170 temp171 temp172 temp173 temp174 temp175 temp176 temp177 temp178)
		(switch (event type?)
			(evKEYBOARD
				(event claimed: 1)
				(switch (event message?)
					(KEY_ALT_a
						(= castFirst (cast first:))
						(while castFirst
							(= temp164 (NodeValue castFirst))
							(Format
								@temp0
								10
								1
								((temp164 -super-?) name?)
								(temp164 view?)
								(temp164 loop?)
								(temp164 cel?)
								(temp164 x?)
								(temp164 y?)
								(temp164 z?)
								(temp164 heading?)
								(temp164 priority?)
								(temp164 signal?)
								(if (temp164 isKindOf: Actor)
									(temp164 illegalBits?)
								else
									-1
								)
							)
							(if
								(not
									(Print
										addText: @temp0
										window: SysWindow
										title: (temp164 name?)
										addIcon: (temp164 view?) (temp164 loop?) (temp164 cel?)
										init:
									)
								)
								(break)
							)
							(= castFirst (cast next: castFirst))
						)
					)
					(KEY_ALT_b (PolyEdit doit:))
					(KEY_ALT_c
						(localproc_0052)
						(Show 4)
					)
					(KEY_ALT_d
						(Print
							addText: {ADD to to total TriggerEvent}
							addEdit: @castFirst 5 100 20 {$}
							init:
						)
						(= triggeredEvents
							(| triggeredEvents (ReadNumber @castFirst))
						)
					)
					(KEY_ALT_e
						(Print
							addText: {SUBTRACT to to total TriggerEvent}
							addEdit: @castFirst 5 100 20 {$}
							init:
						)
						(= triggeredEvents
							(& triggeredEvents (~ (ReadNumber @castFirst)))
						)
					)
					(KEY_ALT_f
						(features eachElementDo: #perform (ScriptID 25 2) 0)
						(cast eachElementDo: #perform (ScriptID 25 2) 1)
						(NameFeatureCode init:)
					)
					(KEY_ALT_g
						(= temp0 0)
						(GetInput @temp0 6 {Variable No.})
						(= castFirst (ReadNumber @temp0))
						(= temp0 0)
						(GetInput @temp0 6 {Value})
						(= [ego castFirst] (ReadNumber @temp0))
						(= temp0 0)
					)
					(KEY_ALT_h
						(= temp0 0)
						(Print
							addText: {Global number:}
							addEdit: @temp0 6 0 12
							init:
						)
						(= castFirst (ReadNumber @temp0))
						(if (IsObject [ego castFirst])
							(Format
								@temp0
								{ Global %d: %s_}
								castFirst
								([ego castFirst] name?)
							)
						else
							(Format
								@temp0
								{ Global %d: %d_}
								castFirst
								[ego castFirst]
							)
						)
						(Prints @temp0)
					)
					(KEY_ALT_i
						((ScriptID 25 0) doit:)
					)
					(KEY_ALT_j (JustifyText init:))
					(KEY_ALT_k
						(= temp160 (GetPort))
						(SetPort 0)
						(= temp171 5)
						(= temp172 16)
						(= temp167 15)
						(= temp168 80)
						(= temp170 (+ temp167 (* 34 temp171)))
						(= temp169 (+ temp168 (* 10 temp172)))
						(= temp165
							(Graph grSAVE_BOX temp167 temp168 temp170 temp169 1)
						)
						(Graph grFILL_BOX temp167 temp168 temp170 temp169 1 255)
						(= temp166 0)
						(while (< temp166 256)
							(Graph
								grFILL_BOX
								(+ temp167 temp171 (* temp171 (/ temp166 8)))
								(+ temp168 temp172 (* 16 (mod temp166 8)))
								(+ temp167 temp171 temp171 (* temp171 (/ temp166 8)))
								(+ temp168 temp172 temp172 (* temp172 (mod temp166 8)))
								1
								temp166
							)
							(++ temp166)
						)
						(Graph grUPDATE_BOX temp167 temp168 temp170 temp169 1)
						(repeat
							(if
								(or
									(== ((= newEvent (Event new:)) type?) 1)
									(== (newEvent type?) 4)
								)
								(break)
							)
							(newEvent dispose:)
						)
						(newEvent dispose:)
						(Graph grRESTORE_BOX temp165)
						(Graph grUPDATE_BOX temp167 temp168 temp170 temp169 1)
						(SetPort temp160)
					)
					(KEY_ALT_l
						(= temp0 0)
						(= castFirst (GetNumber {Flag No.}))
						(Bset castFirst)
					)
					(KEY_ALT_m
						(= temp0 0)
						(= castFirst (GetNumber {Flag No.}))
						(Bclr castFirst)
					)
					(KEY_ALT_n
						(= temp0 0)
						(= castFirst (GetNumber {Flag No.}))
						(Format @temp0 {%d} (Btst castFirst))
						(Prints @temp0)
					)
					(KEY_ALT_o
						((ScriptID 952) doit: @sysLogPath 0)
					)
					(KEY_ALT_p
						(localproc_0052)
						(Show 2)
					)
					(KEY_ALT_q
						(theGame detailLevel: 1)
					)
					(KEY_ALT_r
						(Format
							@temp0
							10
							2
							(curRoom name?)
							curRoomNum
							(curRoom curPic?)
							(curRoom style?)
							(curRoom horizon?)
							(curRoom north?)
							(curRoom south?)
							(curRoom east?)
							(curRoom west?)
							(if (IsObject (curRoom script?))
								((curRoom script?) name?)
							else
								{..none..}
							)
						)
						(Print width: 120 addText: @temp0 init:)
						(theGame showMem:)
					)
					(KEY_ALT_s
						(= temp0 0)
						(if
							(Print
								addText: {Which Format?}
								addButton: 0 {String} 0 12
								addButton: 1 {Message} 50 12
								init:
							)
							(= temp174 (GetNumber {Noun?} 0))
							(= temp175 (GetNumber {Verb?} 0))
							(= temp176 (GetNumber {Case?} 0))
							(= temp177 (GetNumber {Sequence?} 0))
							(Message msgGET temp174 temp175 temp176 temp177 @temp0)
						else
							(GetInput @temp0 50 {String to display?})
						)
						(= temp167 (GetNumber {Y Parameter?} 0))
						(= temp168 (GetNumber {X Parameter?} 0))
						(= castFirst (GetNumber {Box Width?} 0))
						(if
						(not (= theUserFont (GetNumber {Font Number?} 0)))
							(= theUserFont userFont)
						)
						(Print
							posn: temp168 temp167
							width: castFirst
							font: theUserFont
							addText: @temp0
							init:
						)
					)
					(KEY_ALT_t
						(if modelessDialog (modelessDialog dispose:))
						(if (> (= castFirst (GetNumber {Teleport to})) 0)
							(curRoom newRoom: castFirst)
						)
					)
					(KEY_ALT_u
						(User canInput: 1 canControl: 1)
						(theIconBar enable: 0 1 2 3 4 5 6)
					)
					(KEY_ALT_v
						(Show 1)
						(addToPics doit:)
					)
					(KEY_ALT_w (Class_948_0 doit:))
					(KEY_ALT_x (= quit 1))
					(KEY_ALT_z (= quit 1))
					(KEY_ALT_y
						(if
							(Print
								addText: {Which subject?}
								addEdit: @temp150 7 100 0 {$}
								addText: {Whatcha wanna do with it?} 0 12
								addButton: 0 { Stuff it_} 0 26
								addButton: 1 {Unstuff it} 75 26
								init:
							)
							((ScriptID 21 1) doit: (ReadNumber @temp150))
						else
							((ScriptID 21 0) doit: (ReadNumber @temp150))
						)
					)
					(KEY_QUESTION
						(Prints
							{Debug options:______(Page 1 of 5)\n\n___A - Show cast\n___B - Polygon editor\n___C - Show control map\n___D - Place an actor\n___E - Show ego info\n___F - Show feature outlines\n___G - Set global\n}
						)
						(Prints
							{Debug options:______(Page 2 of 5)\n\n___H - Show global\n___I - Get inventory item\n___J - Justify text on screen\n___K - Show palette\n___L - Set flag\n___M - Clear flag\n___N - Show flag\n}
						)
						(Prints
							{Debug options:______(Page 3 of 5)\n\n___O - QA Note Logger\n___P - Show priority map\n___Q - Set Detail to 1\n___R - Show room info/free memory\n___S - Show a string or message\n___T - Teleport\n___U - Give HandsOn\n}
						)
						(Prints
							{Debug options:______(Page 4 of 5)\n\n___V - Show visual map\n___W - Feature writer\n___Y - Stuff or Unstuff a clue\n___X,Z - Quick quit\n}
						)
						(Prints
							{Debug options:______(Page 5 of 5)\n\n__A=Alt, C=Ctrl, L=Left shift, R=Right shift\n\n__Left click:\n____A_______Move ego\n____CL______Show ego\n____CR______Show room\n____CA______Show position\n}
						)
					)
					(else  (event claimed: 0))
				)
			)
			(evMOUSEBUTTON
				(switch (event modifiers?)
					(13 0)
					(14 0)
					(12
						(event claimed: 1)
						(Format @temp0 10 3 (event x?) (event y?))
						(= temp160
							(Print
								posn: 160 10
								font: 999
								modeless: 1
								addText: @temp0
								init:
							)
						)
						(while (!= 2 ((= newEvent (Event new:)) type?))
							(newEvent dispose:)
						)
						(newEvent dispose:)
						(temp160 dispose:)
					)
					(5
						(event type: 4 message: 4864)
						(self handleEvent: event)
					)
					(6
						(event type: 4 message: 4608)
						(self handleEvent: event)
					)
					(9 0)
					(10 0)
					(emRIGHT_SHIFT 0)
					(emLEFT_SHIFT 0)
					(emCTRL 0)
					(emALT
						(event claimed: 1)
						(= temp178 (theGame setCursor: 996))
						(= castFirst
							((= temp173
								(if gTheNewDButtonValue else (User alterEgo?))
							)
								signal?
							)
						)
						(temp173 startUpd:)
						(while (!= 2 ((= newEvent (Event new:)) type?))
							(temp173 x: (newEvent x?) y: (- (newEvent y?) 10))
							(Animate (cast elements?) 0)
							(newEvent dispose:)
						)
						(newEvent dispose:)
						(theGame setCursor: temp178)
						(temp173 signal: castFirst)
					)
				)
			)
		)
	)
)
