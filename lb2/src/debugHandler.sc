;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include game.sh)
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
(procedure (noScrolling)
	(if (OneOf (curRoom style?) SCROLLRIGHT SCROLLLEFT SCROLLUP SCROLLDOWN)
		(curRoom drawPic: (curRoom picture?) 100 style: PLAIN)
	)
)

(instance debugHandler of Feature

	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
		(DisposeScript NAMEFIND)
		(DisposeScript DEBUG)
		(DisposeScript 25)
	)
	
	(method (handleEvent event &tmp
				[str 150] [num 10] oldPort evt node theFont obj
				saveBits theColor t l r b temp171 temp172
				saveAlterEgo n v c s oldCur)
		(switch (event type?)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(`@a
						(= node (cast first:))
						(while node
							(= obj (NodeValue node))
							(Format @str 10 1
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
								(if (obj isKindOf: Actor)
									(obj illegalBits?)
								else
									-1
								)
							)
							(if
								(not
									(Print
										addText: @str
										window: SysWindow
										title: (obj name?)
										addIcon: (obj view?) (obj loop?) (obj cel?)
										init:
									)
								)
								(break)
							)
							(= node (cast next: node))
						)
					)
					(`@b
						(PolygonEditor doit:)
					)
					(`@c
						(noScrolling)
						(Show CMAP)
					)
					(`@d
						(Print
							addText: {ADD to to total TriggerEvent}
							addEdit: @node 5 100 20 {$}
							init:
						)
						(|= triggeredEvents (ReadNumber @node))
					)
					(`@e
						(Print
							addText: {SUBTRACT to to total TriggerEvent}
							addEdit: @node 5 100 20 {$}
							init:
						)
						(&= triggeredEvents (~ (ReadNumber @node)))
					)
					(`@f
						(features eachElementDo: #perform (ScriptID 25 2) 0)
						(cast eachElementDo: #perform (ScriptID 25 2) 1)
						(NameFeatureCode init:)
					)
					(`@g
						(= str 0)
						(GetInput @str 6 {Variable No.})
						(= node (ReadNumber @str))
						(= str 0)
						(GetInput @str 6 {Value})
						(= [ego node] (ReadNumber @str))
						(= str 0)
					)
					(`@h
						(= str 0)
						(Print
							addText: {Global number:}
							addEdit: @str 6 0 12
							init:
						)
						(= node (ReadNumber @str))
						(if (IsObject [ego node])
							(Format
								@str
								{ Global %d: %s_}
								node
								([ego node] name?)
							)
						else
							(Format
								@str
								{ Global %d: %d_}
								node
								[ego node]
							)
						)
						(Prints @str)
					)
					(`@i
						((ScriptID 25 0) doit:)
					)
					(`@j
						(JustifyText init:)
					)
					(`@k
						(= oldPort (GetPort))
						(SetPort 0)
						(= temp171 5)
						(= temp172 16)
						(= t 15)
						(= l 80)
						(= b (+ t (* 34 temp171)))
						(= r (+ l (* 10 temp172)))
						(= saveBits
							(Graph GSaveBits t l b r VMAP)
						)
						(Graph GFillRect t l b r 1 255)
						(for ((= theColor 0)) (< theColor 256) ((++ theColor))
							(Graph
								GFillRect
								(+ t temp171 (* temp171 (/ theColor 8)))
								(+ l temp172 (* 16 (mod theColor 8)))
								(+ t temp171 temp171 (* temp171 (/ theColor 8)))
								(+ l temp172 temp172 (* temp172 (mod theColor 8)))
								1
								theColor
							)
						)
						(Graph GShowBits t l b r VMAP)
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
						(Graph GShowBits t l b r 1)
						(SetPort oldPort)
					)
					(`@l
						(= str 0)
						(= node (GetNumber {Flag No.}))
						(Bset node)
					)
					(`@m
						(= str 0)
						(= node (GetNumber {Flag No.}))
						(Bclr node)
					)
					(`@n
						(= str 0)
						(= node (GetNumber {Flag No.}))
						(Format @str {%d} (Btst node))
						(Prints @str)
					)
					(`@o
						((ScriptID LOGGER) doit: @sysLogPath 0)
					)
					(`@p
						(noScrolling)
						(Show PMAP)
					)
					(`@q
						(theGame detailLevel: 1)
					)
					(`@r
						(Format @str 10 2
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
						(Print width: 120 addText: @str init:)
						(theGame showMem:)
					)
					(`@s
						(= str 0)
						(if
							(Print
								addText: {Which Format?}
								addButton: 0 {String} 0 12
								addButton: 1 {Message} 50 12
								init:
							)
							(= n (GetNumber {Noun?} 0))
							(= v (GetNumber {Verb?} 0))
							(= c (GetNumber {Case?} 0))
							(= s (GetNumber {Sequence?} 0))
							(Message MsgGet n v c s @str)
						else
							(GetInput @str 50 {String to display?})
						)
						(= t (GetNumber {Y Parameter?} 0))
						(= l (GetNumber {X Parameter?} 0))
						(= node (GetNumber {Box Width?} 0))
						(if
						(not (= theFont (GetNumber {Font Number?} 0)))
							(= theFont userFont)
						)
						(Print
							posn: l t
							width: node
							font: theFont
							addText: @str
							init:
						)
					)
					(`@t
						(if modelessDialog
							(modelessDialog dispose:)
						)
						(if (> (= node (GetNumber {Teleport to})) 0)
							(curRoom newRoom: node)
						)
					)
					(`@u
						(User canInput: TRUE canControl: TRUE)
						(theIconBar enable:
							ICON_WALK
							ICON_LOOK
							ICON_DO
							ICON_TALK
							ICON_ASK
							ICON_ITEM
							ICON_INVENTORY
						)
					)
					(`@v
						(Show VMAP)
						(addToPics doit:)
					)
					(`@w
						(CreateObject doit:)
					)
					(`@x
						(= quit TRUE)
					)
					(`@z
						(= quit TRUE)
					)
					(`@y
						(if
							(Print
								addText: {Which subject?}
								addEdit: @num 7 100 0 {$}
								addText: {Whatcha wanna do with it?} 0 12
								addButton: 0 { Stuff it_} 0 26
								addButton: 1 {Unstuff it} 75 26
								init:
							)
							((ScriptID 21 1) doit: (ReadNumber @num))
						else
							((ScriptID 21 0) doit: (ReadNumber @num))
						)
					)
					(`?
						(Prints
							{Debug options:______(Page 1 of 5)\n\n
							___A - Show cast\n
							___B - Polygon editor\n
							___C - Show control map\n
							___D - Place an actor\n
							___E - Show ego info\n
							___F - Show feature outlines\n
							___G - Set global\n}
						)
						(Prints
							{Debug options:______(Page 2 of 5)\n\n
							___H - Show global\n
							___I - Get inventory item\n
							___J - Justify text on screen\n
							___K - Show palette\n
							___L - Set flag\n
							___M - Clear flag\n
							___N - Show flag\n}
						)
						(Prints
							{Debug options:______(Page 3 of 5)\n\n
							___O - QA Note Logger\n
							___P - Show priority map\n
							___Q - Set Detail to 1\n
							___R - Show room info/free memory\n
							___S - Show a string or message\n
							___T - Teleport\n
							___U - Give HandsOn\n}
						)
						(Prints
							{Debug options:______(Page 4 of 5)\n\n
							___V - Show visual map\n
							___W - Feature writer\n
							___Y - Stuff or Unstuff a clue\n
							___X,Z - Quick quit\n}
						)
						(Prints
							{Debug options:______(Page 5 of 5)\n\n
							__A=Alt, C=Ctrl, L=Left shift, R=Right shift\n\n
							__Left click:\n
							____A_______Move ego\n
							____CL______Show ego\n
							____CR______Show room\n
							____CA______Show position\n}
						)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			(mouseDown
				(switch (event modifiers?)
					((| ctrlDown altDown shiftRight) 0)
					((| ctrlDown altDown shiftLeft) 0)
					((| ctrlDown altDown)
						(event claimed: TRUE)
						(Format @str 10 3 (event x?) (event y?))
						(= oldPort
							(Print
								posn: 160 10
								font: 999
								modeless: TRUE
								addText: @str
								init:
							)
						)
						(while (!= mouseUp ((= evt (Event new:)) type?))
							(evt dispose:)
						)
						(evt dispose:)
						(oldPort dispose:)
					)
					((| ctrlDown shiftRight)
						(event type: keyDown message: `@r)
						(self handleEvent: event)
					)
					((| ctrlDown shiftLeft)
						(event type: keyDown message: `@e)
						(self handleEvent: event)
					)
					((| altDown shiftRight) 0)
					((| altDown shiftLeft) 0)
					(shiftLeft 0)
					(shiftRight 0)
					(ctrlDown 0)
					(altDown
						(event claimed: TRUE)
						(= oldCur (theGame setCursor: INVIS_CURSOR))
						(= node
							((= saveAlterEgo
								(if gTheNewDButtonValue else (User alterEgo?))
							)
								signal?
							)
						)
						(saveAlterEgo startUpd:)
						(while (!= mouseUp ((= evt (Event new:)) type?))
							(saveAlterEgo x: (evt x?) y: (- (evt y?) 10))
							(Animate (cast elements?) FALSE)
							(evt dispose:)
						)
						(evt dispose:)
						(theGame setCursor: oldCur)
						(saveAlterEgo signal: node)
					)
				)
			)
		)
	)
)
