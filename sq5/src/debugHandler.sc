;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include game.sh)
(use Main)
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
		(DisposeScript DEBUG)
	)
	
	(method (handleEvent event
			&tmp [str 160] savePort evt i theUserFont obj saveBits color t l b r
				temp171 temp172 userAlterEgo noun verb case seq oldCur
			)
		(return
			(switch (event type?)
				(keyDown
					(event claimed: TRUE)
					(if
						(and
							(not debugging)
							;(not (FileIO fileExists {880.scr}))
							(not
								(OneOf (event message?)
									`@c `@e `@h `@j `@k `@n
									`@o `@p `@r `@v
								)
							)
						)
						(return TRUE)
					)
					(switch (event message?)
						(`@a
							(= i (cast first:))
							(while i
								(= obj (NodeValue i))
								(Format @str DEBUG 1
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
											addText: @str (CelWide (obj view?) (obj loop?) (obj cel?)) 0
											window: SysWindow
											addTitle: (obj name?)
											addIcon: (obj view?) (obj loop?) (obj cel?) 0 0
											init:
										)
									)
									(break)
								)
								(= i (cast next: i))
							)
						)
						(`@b
							(PolygonEditor doit:)
						)
						(`@c
							(noScrolling)
							(Show CMAP)
						)
						(`@e
							(Format @str DEBUG 2
								(ego name?)
								(ego view?)
								(ego loop?)
								(ego cel?)
								(ego x?)
								(ego y?)
								(ego z?)
								(ego heading?)
								(ego priority?)
								(ego signal?)
								(ego illegalBits?)
								(ego onControl:)
								(ego onControl: origin)
							)
							(Print
								addText: @str
								addIcon: (ego view?) (ego loop?) (ego cel?)
								init:
							)
						)
						(`@g
							(= str 0)
							(GetInput @str 6 {Variable No.})
							(= i (ReadNumber @str))
							(= str 0)
							(GetInput @str 6 {Value})
							(= [ego i] (ReadNumber @str))
							(= str 0)
						)
						(`@h
							(= str 0)
							(Print
								addText: {Global number:}
								addEdit: @str 6 0 12
								init:
							)
							(= i (ReadNumber @str))
							(if (IsObject [ego i])
								(Format @str { Global %d: %s_} i ([ego i] name?))
							else
								(Format @str { Global %d: %d_} i [ego i])
							)
							(Prints @str)
						)
						(`@i
							((ScriptID DEBUG_INV 0) doit:)
						)
						(`@j
							(= i 0)
							(while (< i (cast size?))
								(if
									(not
										(& ((= obj (cast at: i)) signal?) notUpd)
									)
									(Format @str DEBUG 1
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
									(Print
										addText: @str (CelWide (obj view?) (obj loop?) (obj cel?) ) 0
										window: SysWindow
										addTitle: (obj name?)
										addIcon: (obj view?) (obj loop?) (obj cel?) 0 0
										init:
									)
								)
								(++ i)
							)
						)
						(`@k
							(= savePort (GetPort))
							(SetPort 0)
							(= temp171 5)
							(= temp172 16)
							(= t 15)
							(= l 80)
							(= r (+ t (* 34 temp171)))
							(= b (+ l (* 10 temp172)))
							(= saveBits
								(Graph GSaveBits t l r b 1)
							)
							(Graph GFillRect t l r b 1 255)
							(= color 0)
							(while (< color 256)
								(Graph GFillRect
									(+ t temp171 (* temp171 (/ color 8)))
									(+ l temp172 (* 16 (mod color 8)))
									(+ t temp171 temp171 (* temp171 (/ color 8)))
									(+ l temp172 temp172 (* temp172 (mod color 8)))
									1
									color
								)
								(++ color)
							)
							(Graph GShowBits t l r b VMAP)
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
						(`@l
							(= str 0)
							(= i (GetNumber {Flag No.}))
							(Bset i)
						)
						(`@m
							(= str 0)
							(= i (GetNumber {Flag No.}))
							(Bclr i)
						)
						(`@n
							(= str 0)
							(= i (GetNumber {Flag No.}))
							(if (Btst i)
								(Prints {TRUE})
							else
								(Prints {FALSE})
							)
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
							(Format @str DEBUG 3
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
								(= noun (GetNumber {Noun?} 0))
								(= verb (GetNumber {Verb?} 0))
								(= case (GetNumber {Case?} 0))
								(= seq (GetNumber {Sequence?} 0))
								(Message MsgGet noun verb case seq @str)
							else
								(GetInput @str 50 {String to display?})
							)
							(= t (GetNumber {Y Parameter?} 0))
							(= l (GetNumber {X Parameter?} 0))
							(= i (GetNumber {Box Width?} 0))
							(if
							(not (= theUserFont (GetNumber {Font Number?} 0)))
								(= theUserFont userFont)
							)
							(Print
								posn: l t
								width: i
								font: theUserFont
								addText: @str
								init:
							)
						)
						(`@t
							(if modelessDialog
								(modelessDialog dispose:)
							)
							(Print
								addText: {Which room do you want?}
								addEdit: @str 6 115 35
								init:
							)
							(if
							(and str (> (= i (ReadNumber @str)) 0))
								(curRoom newRoom: i)
							)
						)
						(`@u
							(User canInput: TRUE canControl: TRUE)
							(theIconBar enable:
								 ICON_WALK
								 ICON_LOOK
								 ICON_DO
								 ICON_TALK
								 ICON_ITEM
								 ICON_INVENTORY
							)
						)
;;;						(`@v
;;;							(Show VMAP)
;;;						)
						(`@w
							(CreateObject doit:)
						)
						(`@x
							(= quit TRUE)
						)
						(`@v
							(Print
								addText: {Version number:} 0 0
								addText: version 0 14
								init:
							)
						)
						(`@y
							(= t 0)
							(while (< t (cast size?))
								(Graph GFillRect
									((cast at: t) brTop?)
									((cast at: t) brLeft?)
									((cast at: t) brBottom?)
									((cast at: t) brRight?)
									1
									colBlack
									-1
									-1
								)
								(++ t)
							)
						)
						(`@z
							(= quit TRUE)
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
						(13 0)
						(14 0)
						(12
							(event claimed: TRUE)
							(Format @str DEBUG 4 (event x?) (event y?))
							(= savePort
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
							(= i
								((= userAlterEgo (User alterEgo?)) signal?)
							)
							(userAlterEgo startUpd:)
							(while (!= 2 ((= evt (Event new:)) type?))
								(userAlterEgo x: (evt x?) y: (- (evt y?) 10))
								(Animate (cast elements?) FALSE)
								(evt dispose:)
							)
							(evt dispose:)
							(theGame setCursor: oldCur)
							(userAlterEgo signal: i)
						)
					)
				)
			)
		)
	)
)
