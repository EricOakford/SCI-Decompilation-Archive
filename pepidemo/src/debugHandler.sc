;;; Sierra Script 1.0 - (do not remove this comment)
(script# 880)
(include game.sh)
(use Main)
(use Intrface)
(use DebugDlg)
(use Print)
(use PolyEdit)
(use WriteFtr)
(use Feature)
(use Window)
(use Ego)
(use User)
(use Actor)
(use System)

(public
	debugHandler 0
)

(local
	[local0 27]
	saveWindow
)
(procedure (noScrolling)
	(if (OneOf (curRoom style?) SCROLLRIGHT SCROLLLEFT SCROLLUP SCROLLDOWN)
		(curRoom drawPic: (curRoom picture?) 100 style: PLAIN)
	)
)

(instance debugHandler of Feature
	
	(method (init)
		(super init:)
		(Format @sysLogPath 880 1)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
		(DisposeScript 974)
		(DisposeScript 880)
		(DisposeScript 881)
	)
	
	(method (handleEvent event
				&tmp [str 160] savePort evt node theFont obj
				saveBits theColor t l r b temp171 temp172 temp173
				n v c s oldCur userAlterEgo)
		(switch (event type?)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(`@a
						(= node (cast first:))
						(while node
							(= obj (NodeValue node))
							(Format
								@str
								{name: %s\nclass: %s\nview: %d\nloop: %d\ncel: %d\nposn: %d %d %d\nheading: %d\npri: %d\nsignal: $%x\nillBits: $%x\n}
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
							(Print
								font: smallFont
								addText: @str
								addIcon: (obj view?) (obj loop?) (obj cel?) 120 0
								init:
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
						((ScriptID 881 1) doit:)
					)
					(`@e
						(= userAlterEgo (User alterEgo?))
						(Format @str
							{name: %s\n
							view: %d\n
							loop: %d\n
							cel: %d\n
							posn: %d %d %d\n
							heading: %d\n
							pri: %d\n
							signal: $%x\n
							cycleSpeed: %d\n
							moveSpeed: %d\n
							illBits: $%x\n
							onControl: $%x\n
							origin on: $%x\n
							script: %s_}
							(userAlterEgo name?)
							(userAlterEgo view?)
							(userAlterEgo loop?)
							(userAlterEgo cel?)
							(userAlterEgo x?)
							(userAlterEgo y?)
							(userAlterEgo z?)
							(userAlterEgo heading?)
							(userAlterEgo priority?)
							(userAlterEgo signal?)
							(userAlterEgo cycleSpeed?)
							(userAlterEgo moveSpeed?)
							(if (userAlterEgo respondsTo: #onControl)
								(userAlterEgo illegalBits?)
							else
								-1
							)
							(if (userAlterEgo respondsTo: #onControl)
								(userAlterEgo onControl:)
							else
								-1
							)
							(if (userAlterEgo respondsTo: #onControl)
								(userAlterEgo onControl: 1)
							else
								-1
							)
							(if (IsObject (userAlterEgo script?))
								((userAlterEgo script?) name?)
							else
								{..none..}
							)
						)
						(Print
							font: smallFont
							addText: @str
							addIcon:
								(userAlterEgo view?)
								(userAlterEgo loop?)
								(userAlterEgo cel?)
								120
								0
							init:
						)
					)
					(`@f
						(features eachElementDo: #perform (ScriptID 881 2) 0)
						(cast eachElementDo: #perform (ScriptID 881 2) 1)
						(NameFeatureCode init:)
					)
					(`@g
						(= str 0)
						(GetInput @str 6 {Variable No.})
						(= node (ReadNumber @str))
						(= str 0)
						(GetInput @str 6 {Value})
						(if (ReadNumber @str)
							(= [ego node] (ReadNumber @str))
						else
							(Prints {*** Dont try to blow it up, home-skillet!!})
						)
						(= str 0)
					)
					(`@h
						(= str 0)
						(Print
							font: smallFont
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
						(= oldCur (theGame setCursor: ARROW_CURSOR))
						(= saveWindow systemWindow)
						(SysWindow back: 41 color: 15)
						(= systemWindow SysWindow)
						((ScriptID 881 0) doit:)
						(= systemWindow saveWindow)
						(theGame setCursor: oldCur)
					)
					(`@j
						(cond 
							((not debugging)
								(event claimed: FALSE)
							)
							((HaveMem 1536)
								(Format @sysLogPath 880 1)
								((ScriptID LOGGER) doit: @sysLogPath 0)
								(event claimed: TRUE)
							)
							(else (Prints {Not Enough Memory!!})
								(event claimed: TRUE)
							)
						)
					)
					(`@k
						(= savePort (GetPort))
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
						(= theColor 0)
						(while (< theColor 256)
							(Graph GFillRect
								(+ t temp171 (* temp171 (/ theColor 8)))
								(+ l temp172 (* 16 (mod theColor 8)))
								(+ t temp171 temp171 (* temp171 (/ theColor 8)))
								(+ l temp172 temp172 (* temp172 (mod theColor 8)))
								VMAP
								theColor
							)
							(++ theColor)
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
						(SetPort savePort)
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
					(`@o)
					(`@p
						(noScrolling)
						(Show 2)
					)
					(`@q
						(theGame detailLevel: 1)
					)
					(`@r
						(Format @str 880 2
							(curRoom name?)
							curRoomNum
							(curRoom curPic?)
							(curRoom style?)
							(curRoom horizon?)
							(curRoom north?)
							(curRoom south?)
							(curRoom east?)
							(curRoom west?)
							gameAct
							(if (IsObject (curRoom script?))
								((curRoom script?) name?)
							else
								{..none..}
							)
						)
						(Print font: smallFont width: 120 addText: @str init:)
						(theGame showMem:)
					)
					(`@s
						(= str 0)
						(if
							(Print
								font: smallFont
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
						(if (not (= theFont (GetNumber {Font Number?} 0)))
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
					(`@z
						(= quit TRUE)
					)
					(`@y
						(theGame
							setEgo:
								(Print
									font: smallFont
									addText: {Which Ego?}
									addButton: (ScriptID TWEGO 0) {Pepper} 0 12
									addButton: (ScriptID TWEGO 1) {LockJaw} 0 26
									init:
								)
						)
						((ScriptID TWEGO 1) normalize: normal: 0)
						((ScriptID TWEGO 0) normalize: normal: 0)
					)
					(`?
						(theGame showMem:)
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
					(13 0)
					(14 0)
					(12
						(event claimed: 1)
						(Format @str 880 3 (event x?) (event y?))
						(= savePort
							(Print
								posn: 160 10
								font: smallFont
								modeless: TRUE
								addText: @str
								init:
							)
						)
						(while (!= 2 ((= evt (Event new:)) type?))
							(evt dispose:)
						)
						(evt dispose:)
						(savePort dispose:)
					)
					(5
						(event type: keyDown message: 4864)
						(self handleEvent: event)
					)
					(6
						(event type: keyDown message: 4608)
						(self handleEvent: event)
					)
					(9 0)
					(10 0)
					(shiftRight 0)
					(shiftLeft 0)
					(ctrlDown 0)
					(altDown
						(event claimed: TRUE)
						(= oldCur (theGame setCursor: INVIS_CURSOR))
						(= node
							((= temp173
								(if gTheNewDButtonValue else (User alterEgo?))
							)
								signal?
							)
						)
						(temp173 startUpd:)
						(while (!= 2 ((= evt (Event new:)) type?))
							(temp173 x: (evt x?) y: (- (evt y?) 10))
							(Animate (cast elements?) 0)
							(evt dispose:)
						)
						(evt dispose:)
						(theGame setCursor: oldCur)
						(temp173 signal: node)
					)
				)
			)
		)
	)
)
