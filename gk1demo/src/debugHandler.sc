;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use Procs)
(use Intrface)
(use Print)
(use Dialog)
(use BordWind)
(use PolyEdit)
(use DlgEdit)
(use WriteFtr)
(use Feature)
(use Window)
(use Ego)
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
	hadEgo
)
(procedure (WhereTo &tmp temp0 nextRoom [str 5])
	(= str 0)
	(= nextRoom 0)
	(= nextRoom
		(Print
			font: smallFont
			addText: {Where to, Gabriel?}
			addEdit: @str 5 85
			addButton: 210 {_____Book Shop_____} 0 20
			addButton: 230 {____Police Lobby___} 101 20
			addButton: 240 {__Mosleys' Office__} 0 34
			addButton: 250 {____Voodoo Shop____} 101 34
			addButton: 430 {__Jackson Square___} 0 48
			addButton: 470 {_____Lake Pont.____} 101 48
			addButton: 380 { Grandma's Parlor_} 0 62
			addButton: 260 {__Voodoo Museum__} 101 62
			addButton: 390 {__Grandma's Attic__} 0 76
			addButton: 280 {Cazaunoux Interior} 101 76
			addButton: -100 {_____< Restore >_____} 101 90
			init:
		)
	)
	(if str
		(= nextRoom (ReadNumber @str))
	)
	(if (== nextRoom -100)
		(theGame restore:)
	)
	(if (< howFast 100)
		(+= howFast nextRoom)
	else
		(+= howFast 1000)
	)
	(curRoom newRoom: nextRoom)
)

(procedure (localproc_012e param1 param2 &tmp [str 40])
	(= str 0)
	(if (> argc 1) (Format @str DEBUG 0 param2))
	(return
		(if (GetInput @str 10 param1)
			(ReadNumber @str)
		else
			-1
		)
	)
)

(procedure (noScrolling)
	(if (OneOf (curRoom style?) SCROLLRIGHT SCROLLLEFT SCROLLUP SCROLLDOWN)
		(curRoom drawPic: (curRoom picture?) PLAIN style: PLAIN)
	)
)

(instance debugHandler of Feature
	(properties
		y -1
	)
	
	(method (dispose)
		((= systemWindow BorderWindow)
			topBordColor: 5
			lftBordColor: 5
			rgtBordColor: 4
			botBordColor: 3
			color: 26
			back: 48
		)
		(SetCursor 0 0 153 319)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp
				[str 290] savePort evt temp292 i obj [temp295 4]
				n saveBits theColor t l b
				r colHigh colWide)
		(SetCursor -2)
		(switch (event type?)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(`@a
						(= i (cast first:))
						(while i
							(= obj (NodeValue i))
							(Format
								@str
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
								illBits: $%x\n_}
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
									addText:
										@str
										(CelWide
											(obj view?)
											(obj loop?)
											(obj cel?)
										)
										0
									init:
								)
							else
								(Print font: smallFont addText: @str 0 0 init:)
							)
							(= i (cast next: i))
						)
					)
					(`@b
						(if (= hadEgo (cast contains: ego))
							(ego hide:)
						)
						(PolygonEditor doit:)
						(if hadEgo
							(ego show:)
						)
					)
					(`@c
						(noScrolling)
						(Show CMAP)
						(Animate (cast elements?))
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
						(Show VMAP)
					)
					(`@d
						(if (= debugOn (not debugOn))
							(Prints {On})
						else
							(Prints {Off})
						)
					)
					(`@e
						(DialogEditor doit:)
					)
					(`@f
						(= n 0)
						(= n (GetNumber {Flag #:}))
						(if (Btst n)
							(Prints {cleared})
							(Bclr n)
						else
							(Prints {set})
							(Bset n)
						)
					)
					(`@g
						(= str 0)
						(GetInput @str 5 {Variable No.})
						(if (not (= i (ReadNumber @str))) (return))
						(= str 0)
						(GetInput @str 5 {Value})
						(= [ego i] (ReadNumber @str))
						(= str 0)
					)
					(`@i
						(dInvD doit:)
					)
					(`@k
						(= savePort (GetPort))
						(SetPort 0)
						(= colHigh 5)
						(= colWide 16)
						(= t 15)
						(= l 80)
						(= r (+ t (* 34 colHigh)))
						(= b (+ l (* 10 colWide)))
						(= saveBits
							(Graph GSaveBits t l r b VMAP)
						)
						(Graph GFillRect t l r b VMAP 255)
						(= theColor 0)
						(while (< theColor 256)
							(Graph
								GFillRect
								(+ t colHigh (* colHigh (/ theColor 8)))
								(+ l colWide (* 16 (mod theColor 8)))
								(+ t colHigh colHigh (* colHigh (/ theColor 8)))
								(+ l colWide colWide (* colWide (mod theColor 8)))
								1
								theColor
							)
							(++ theColor)
						)
						(Graph GShowBits t l r b 1)
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
						(Graph GRestoreBits saveBits)
						(Graph GShowBits t l r b VMAP)
						(SetPort savePort)
					)
					(`@m
						(theGame showMem:)
					)
					(`@l
						(if (u> (MemoryInfo FreeHeap) 1536)
							((ScriptID LOGGER) doit:)
						else
							(Prints {No Memory!!})
						)
					)
					(`@p
						(noScrolling)
						(Show PMAP)
					)
					(`@q
						(Print
							font: smallFont
							addTextF: {Cur X: %d,Y: %d} (event x?) (event y?)
							init:
						)
					)
					(`@r
						(Format @str
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
							curPic: %d_}
							(curRoom name?)
							(if (IsObject (curRoom script?))
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
						(Print font: smallFont addText: @str 0 0 init:)
					)
					(`@t
						(WhereTo)
					)
					(`@s
						(= i (cast first:))
						(while i
							(if
								(and
									(not
										(& ((= obj (NodeValue i)) signal?) notUpd)
									)
									(not (& (obj signal?) actorHidden))
								)
								(Format
									@str
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
										addText:
											@str
											(CelWide
												(obj view?)
												(obj loop?)
												(obj cel?)
											)
											0
										window: SysWindow
										init:
									)
								else
									(Print
										font: smallFont
										addText: @str 0 0
										window: SysWindow
										init:
									)
								)
							)
							(= i (cast next: i))
						)
					)
					(`@u
						(theGame handsOn:)
					)
					(`@v
						(Show VMAP)
					)
					(`@w
						(CreateObject doit:)
					)
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
						(= i (localproc_012e {vanishingX:}))
						(if (OneOf i -1 0)
						else
							(curRoom vanishingX: i)
						)
						(= i (localproc_012e {vanishingY:}))
						(if (OneOf i -1 0)
						else
							(curRoom vanishingY: i)
						)
						(Print
							font: smallFont
							addTextF:
								{vanishing x: %d,y: %d}
								(curRoom vanishingX?)
								(curRoom vanishingY?)
							init:
						)
						(= str 0)
					)
					(`@z
						(= evt (Event new:))
						((User alterEgo?)
							posn: (evt x?) (- (evt y?) 10)
							setMotion: 0
						)
						(Animate (cast elements?) FALSE)
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
								ALT-Z position ego at cursor_}
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
					(while (!= 2 ((= evt (Event new:)) type?))
						((User alterEgo?)
							posn: (evt x?) (- (evt y?) 10)
							setMotion: 0
						)
						(Animate (cast elements?) FALSE)
						(evt dispose:)
					)
					(evt dispose:)
				)
			)
		)
	)
)

(instance dInvD of Dialog

	(method (init &tmp cWide cHigh temp2 i newDText node obj)
		(= temp2
			(= cWide
				(= cHigh MARGIN)
			)
		)
		(= i 0)
		(= node (inventory first:))
		(while node
			(= obj (NodeValue node))
			(++ i)
			(if (obj isKindOf: InvItem)
				(self
					add:
						((= newDText (DText new:))
							value: obj
							text: (obj name?)
							nsLeft: cWide
							nsTop: cHigh
							state: 3
							font: smallFont
							setSize:
							yourself:
						)
				)
			)
			(if (< temp2 (- (newDText nsRight?) (newDText nsLeft?)))
				(= temp2 (- (newDText nsRight?) (newDText nsLeft?)))
			)
			(if
				(>
					(= cHigh
						(+ cHigh (- (newDText nsBottom?) (newDText nsTop?)) 1)
					)
					140
				)
				(= cHigh 4)
				(= cWide (+ cWide temp2 10))
				(= temp2 0)
			)
			(= node (inventory next: node))
		)
		(= window systemWindow)
		(self setSize:)
		(= newDButton (DButton new:))
		(newDButton
			text: {Outta here!}
			setSize:
			moveTo: (- nsRight (+ MARGIN (newDButton nsRight?))) nsBottom
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
