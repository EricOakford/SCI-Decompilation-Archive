;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use Procs)
(use KQ7PolyEdit)
(use Intrface)
(use DButton)
(use DText)
(use Plane)
(use String)
(use Print)
(use Dialog)
(use Feature)
(use Ego)
(use File)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	debugHandler 0
	WhereTo 1
)

(local
	newDButton
	egoInCast
	bmpFile
)
(procedure (WhereTo &tmp temp0 printInit str [temp3 2])
	(= str (String newWith: 10 {}))
	(Print
		font: smallFont
		addText: {Where to?}
		addEdit: str 5 40 0
		addButton: 15 {<Intro >} 0 26
		addButton: 1111 {<Chap 1>} 0 40
		addButton: 2222 {<Chap 2>} 0 54
		addButton: 3333 {<Chap 3>} 0 68
		addButton: 4444 {<Chap 4>} 40 26
		addButton: 5555 {<Chap 5>} 40 40
		addButton: 6666 {<Chap 6>} 40 54
		addButton: 22 {<Restore>} 40 68
	)
	(if
		(and
			(!= (= printInit (Print init:)) 15)
			(!= printInit 22)
		)
		(Bset 21)
	)
	(if (== curRoomNum 26)
		(Bset 8)
	)
	(if (str size:)
		(= printInit (str asInteger:))
	)
	(str dispose:)
	(switch printInit
		(1111
			(= printInit 0)
			(= curChapter 1)
		)
		(2222
			(= printInit 0)
			(= curChapter 2)
		)
		(3333
			(= printInit 0)
			(= curChapter 3)
		)
		(4444
			(= printInit 0)
			(= curChapter 4)
		)
		(5555
			(= printInit 0)
			(= curChapter 5)
		)
		(6666
			(= printInit 0)
			(= curChapter 6)
		)
		(22
			(theGame restore:)
		)
		(else 
			(= curChapter
				(Print
					addText: {Which chapter?}
					addButton: 1 {chap 1} 0 12
					addButton: 2 {chap 2} 0 26
					addButton: 3 {chap 3} 0 40
					addButton: 4 {chap 4} 0 54
					addButton: 5 {chap 5} 0 68
					addButton: 6 {chap 6} 0 82
					init:
				)
			)
		)
	)
	((ScriptID 28 0) doit: curChapter printInit)
)

(procedure (localproc_0028 param1 param2 &tmp str)
	(= str (String newWith: 80 {}))
	(if (> argc 1) (str format: {%d} param2))
	(return
		(if (GetInput (str data?) 10 param1)
			(str asInteger:)
		else
			-1
		)
	)
)

(instance debugHandler of Feature
	(properties
		y -1
	)
	
	(method (dispose)
		(sysLogPath dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp
				str temp1 evt temp3 node
				obj [temp6 4] i [temp11 2] temp13
				temp14 temp15 temp16 temp17 temp18)
		(= sysLogPath (String new: 1000))
		(= str (String new:))
		(switch (event type?)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(ESC
						(= quit TRUE)
					)
					(`@a
						(= node (cast first:))
						(while node
							(= obj (NodeValue node))
							(str
								format:
									{name: %s\n
									class: %s\n
									view: %d\n
									loop: %d\n
									cel: %d\n
									posn: %d %d %d\n
									heading: %d\n
									pri: %d\n
									signal: $%x\n
									scaleSignal: $%x\n
									scaleX: %d\n
									scaleY: %d\n
									illBits: $%x\n}
									(obj name?)
									((obj -super-?) name?)
									(obj view?)
									(obj loop?)
									(obj cel?)
									(obj x?)
									(obj y?)
									(obj z?)
									(obj heading?)
									(obj priority?)
									(obj signal?)
									(obj scaleSignal?)
									(obj scaleX?)
									(obj scaleY?)
									(if
										(or
											(== (obj -super-?) Actor)
											(== (obj -super-?) Ego)
										)
										(obj illegalBits?)
									else
										-1
									)
							)
							(if (not (obj scaleSignal?))
								(Print
									addIcon: (obj view?) (obj loop?) (obj cel?) 0 0
									font: smallFont
									addText: str (CelWide (obj view?) (obj loop?) (obj cel?)) 0
									init:
								)
							else
								(Print font: smallFont addText: str 0 0 init:)
							)
							(= node (cast next: node))
						)
					)
					(2
						(= bmpFile (String format: {%03d.BMP} curRoomNum))
						(Dummy (bmpFile data?))
						(if (FileIO FileExists (bmpFile data?))
							(Printf {%03d.BMP SUCCESSFULLY CREATED} curRoomNum)
						else
							(Printf {ERROR CREATING %03d.BMP} curRoomNum)
						)
					)
					(`@b
						(if (= egoInCast (cast contains: ego))
							(ego hide:)
						)
						(PEditor init:)
						(if (curRoom obstacles?)
							(PEditor readPolygonsFromList: (curRoom obstacles?))
						)
						(PEditor doit:)
						(= obj (String new: 13))
						(Print
							addTitle: {Output File Name:}
							addEdit: obj 12 0 0 {ROOM.POL}
							init:
						)
						(poly_pol name: (obj data?) open: 0)
						(poly_pol writeString: {\t\t(curRoom addObstacle:\0D\n})
						(if (PEditor size:)
							(PEditor eachElementDo: #writeToFile poly_pol)
						)
						(poly_pol writeString: {\t\t)\0D\n})
						(poly_pol close:)
						(obj dispose:)
						(PEditor dispose:)
						(if egoInCast
							(ego show:)
						)
					)
					(`@c
						(repeat
							(if
								(or
									(== ((= event (Event new:)) type?) mouseDown)
									(== (event type?) keyDown)
								)
								(break)
							)
							(event dispose:)
						)
						(event dispose:)
					)
					(`@d
						(if (= debugOn (not debugOn))
							(Prints {On})
						else
							(Prints {Off})
						)
					)
					(`@f
						(= i 0)
						(= i (GetNumber {Flag #:}))
						(if (Btst i)
							(Prints {cleared})
							(Bclr i)
						else
							(Prints {set})
							(Bset i)
						)
					)
					(`@g
						(GetInput str 5 {Variable No.})
						(if (not (= node (str asInteger:))) (return))
						(GetInput str 5 {Value})
						(= [ego node] (str asInteger:))
					)
					(`@i
						(ego get: (GetNumber str))
					)
					(`@k
						(= temp17 5)
						(= temp18 16)
						(= temp13 15)
						(= temp14 80)
						(= temp16 (+ temp13 (* 34 temp17)))
						(= temp15 (+ temp14 (* 10 temp18)))
						(repeat
							(if
								(or
									(== ((= evt (Event new:)) type?) mouseDown)
									(== (evt type?) keyDown)
								)
								(break)
							)
							(evt dispose:)
						)
						(evt dispose:)
					)
					(`@m
						(theGame showMem:)
					)
					(`@l
						((ScriptID LOGGER) doit:)
					)
					(`@p)
					(`@q
						(Print
							font: smallFont
							addTextF: {Cur X: %d,Y: %d} (event x?) (event y?)
							init:
						)
					)
					(`@r
						(str
							format:
								{Current Room\n
								name: %s\n
								script: %s\n
								horizon: %d\n
								vanishingX: %d\n
								vanishingY: %d\n
								picAngle: %d\n
								north: %d\n
								south: %d\n
								east: %d\n
								west: %d\n
								style: %d\n
								curPic: %d}
								(curRoom name?)
								(if (curRoom script?)
									((curRoom script?) name?)
								else
									{none}
								)
								(curRoom horizon?)
								(curRoom vanishingX?)
								(curRoom vanishingY?)
								(curRoom picAngle?)
								(curRoom north?)
								(curRoom south?)
								(curRoom east?)
								(curRoom west?)
								(curRoom style?)
								(curRoom curPic?)
						)
						(Print font: smallFont addText: str 0 0 init:)
					)
					(`@t
						(WhereTo)
					)
					(`@s
						(= node (cast first:))
						(while node
							(= obj (NodeValue node))
							(str
								format:
									{Updating cast members\n
									name: %s\n
									class: %s\n
									view: %d\n
									loop: %d\n
									cel: %d\n
									posn: %d %d %d\n
									heading: %d\n
									pri: %d\n
									signal: $%x\n
									illBits: $%x\n}
									(obj name?)
									((obj -super-?) name?)
									(obj view?)
									(obj loop?)
									(obj cel?)
									(obj x?)
									(obj y?)
									(obj z?)
									(obj heading?)
									(obj priority?)
									(obj signal?)
									(if
										(or
											(== (obj -super-?) Actor)
											(== (obj -super-?) Ego)
										)
										(obj illegalBits?)
									else
										-1
									)
							)
							(if (not (obj scaleSignal?))
								(Print
									addIcon: (obj view?) (obj loop?) (obj cel?) 0 0
									font: smallFont
									addText: str (CelWide (obj view?) (obj loop?) (obj cel?)) 0
									init:
								)
							else
								(Print font: smallFont addText: str 0 0 init:)
							)
							(= node (cast next: node))
						)
					)
					(`@u
						(theGame handsOn:)
					)
					(`@v)
					(`@x
						(= quit TRUE)
					)
					(`@y
						(Print
							font: smallFont
							addTextF:
								{vanishing x: %d,y: %d}
								(curRoom vanishingX?)
								(curRoom vanishingY?)
							init:
						)
						(= node (localproc_0028 {vanishingX:}))
						(if (OneOf node -1 0)
						else
							(curRoom vanishingX: node)
						)
						(= node (localproc_0028 {vanishingY:}))
						(if (OneOf node -1 0)
						else
							(curRoom vanishingY: node)
						)
						(Print
							font: smallFont
							addTextF:
								{vanishing x: %d,y: %d}
								(curRoom vanishingX?)
								(curRoom vanishingY?)
							init:
						)
					)
					(`@z
						(= evt (Event new:))
						((User alterEgo?)
							posn: (evt x?) (- (evt y?) 10)
							setMotion: 0
						)
						(evt dispose:)
					)
					(`@h
						(Print
							font: smallFont
							addText:
								{ALT-A show Cast\n
								ALT-B Polygon Editor\n
								ALT-C Control map\n
								ALT-D DebugOn toggle\n
								ALT-F Flag set/clr\n
								ALT-G Global set\n
								ALT-I Inv items\n
								ALT-L Log file\n
								ALT-M Memory\n
								ALT-P Priority map\n
								ALT-Q show Cursor Coords\n
								ALT-R Room info\n
								ALT-S Updating cast elements\n
								ALT-T Teleport\n
								ALT-U return User control\n
								ALT-V Visual map\n
								ALT-W feature Writer\n
								ALT-Y Vanishing point adj\n
								ALT-Z position ego at cursor}
							init:
						)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			(mouseDown
				(if (== (event modifiers?) altDown)
					(event claimed: TRUE)
					(while (!= mouseUp ((= evt (Event new:)) type?))
						((User alterEgo?)
							posn: (evt x?) (- (evt y?) 10)
							setMotion: 0
						)
						(evt dispose:)
					)
					(evt dispose:)
				)
			)
		)
		(str dispose:)
	)
)

(instance dInvD of Dialog

	(method (init &tmp left top temp2 i theText node obj)
		(= temp2 (= left (= top 4)))
		(= i 0)
		(= node (inventory first:))
		(while node
			(= obj (NodeValue node))
			(++ i)
			(if (obj isKindOf: InvItem)
				(self
					add:
						((= theText (DText new:))
							value: obj
							text: (obj name?)
							nsLeft: left
							nsTop: top
							state: 3
							font: 30
							setSize:
							yourself:
						)
				)
			)
			(if (< temp2 (- (theText nsRight?) (theText nsLeft?)))
				(= temp2 (- (theText nsRight?) (theText nsLeft?)))
			)
			(if
				(>
					(= top
						(+ top (- (theText nsBottom?) (theText nsTop?)) 1)
					)
					140
				)
				(= top 4)
				(= left (+ left temp2 10))
				(= temp2 0)
			)
			(= node (inventory next: node))
		)
		(= plane ourPlane)
		(self setSize:)
		(= newDButton (DButton new:))
		(newDButton
			text: {Outta here!}
			setSize:
			moveTo: (- nsRight (+ 4 (newDButton nsRight?))) nsBottom
		)
		(newDButton
			move: (- (newDButton nsLeft?) (newDButton nsRight?)) 0
		)
		(self add: newDButton setSize: center:)
		(return i)
	)
	
	(method (doit &tmp theNewDButton)
		(self init:)
		(self open: 4 15)
		(= theNewDButton newDButton)
		(repeat
			(if
				(or
					(not (= theNewDButton (super doit: theNewDButton)))
					(== theNewDButton -1)
					(== theNewDButton newDButton)
				)
				(break)
			)
			(ego get: (inventory indexOf: (theNewDButton value?)))
		)
		(self eachElementDo: #dispose 1 dispose:)
	)
	
	(method (handleEvent event &tmp eMsg eType)
		(= eMsg (event message?))
		(switch (= eType (event type?))
			(keyDown
				(switch eMsg
					(UPARROW
						(= eMsg SHIFTTAB)
					)
					(DOWNARROW
						(= eMsg TAB)
					)
				)
			)
			(direction
				(switch eMsg
					(dirN
						(= eMsg SHIFTTAB)
						(= eType keyDown)
					)
					(dirS
						(= eMsg TAB)
						(= eType keyDown)
					)
				)
			)
		)
		(event type: eType message: eMsg)
		(super handleEvent: event)
	)
)

(instance ourPlane of Plane)

(instance poly_pol of File
	(properties
		name "poly.pol"
	)
)
