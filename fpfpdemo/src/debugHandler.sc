;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include game.sh) (include "10.shm")
(use Main)
(use DebugDlg)
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
		(curRoom drawPic: (curRoom picture?) PLAIN style: PLAIN)
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
		(DisposeScript DEBUGDLG)
	)
	
	(method (handleEvent event &tmp
			[str 160] oldPort evt i temp163 obj
			underbits theColor t l r b colorX colorY
			aObj [temp174 4] oldCur
			)
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
								{class: %s\nview: %d\nloop: %d\ncel: %d\nposn: %d %d %d\nheading: %d\npri: %d\nsignal: $%x\nillBits: $%x\n}
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
										addTitle: (obj name?)
										addIcon:
											(obj view?)
											(obj loop?)
											(obj cel?)
											(+ (Print x?) 80)
											(Print y?)
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
					(`@f
						(features eachElementDo: #perform (ScriptID DEBUGDLG 2) 0)
						(cast eachElementDo: #perform (ScriptID DEBUGDLG 2) 1)
						(NameFeatureCode init:)
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
						((ScriptID DEBUGDLG 0) doit:)
					)
					(`@k
						(= oldPort (GetPort))
						(SetPort 0)
						(= colorX 5)
						(= colorY 16)
						(= t 15)
						(= l 80)
						(= b (+ t (* 34 colorX)))
						(= r (+ l (* 10 colorY)))
						(= underbits
							(Graph GSaveBits t l b r VMAP)
						)
						(Graph GFillRect t l b r 1 255)
						(= theColor 0)
						(while (< theColor 256)
							(Graph
								GFillRect
								(+ t colorX (* colorX (/ theColor 8)))
								(+ l colorY (* 16 (mod theColor 8)))
								(+ t colorX colorX (* colorX (/ theColor 8)))
								(+ l colorY colorY (* colorY (mod theColor 8)))
								1
								theColor
							)
							(++ theColor)
						)
						(Graph GShowBits t l b r 1)
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
						(Graph GRestoreBits underbits)
						(Graph GShowBits t l b r 1)
						(SetPort oldPort)
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
						(Format @str {%d} (Btst i))
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
						(Format @str 10 1
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
					(`@t
						(if modelessDialog (modelessDialog dispose:))
						(if (> (= i (GetNumber {Teleport to})) 0)
							(curRoom newRoom: i)
						)
					)
					(`@u
						(User canInput: TRUE canControl: TRUE)
						(theIconBar enable: ICON_WALK ICON_LOOK ICON_DO ICON_TALK ICON_USEIT ICON_INVENTORY)
					)
					(`@v
						(Show 1)
						(addToPics doit:)
					)
					(`@w (CreateObject doit:))
					(`@x (= quit TRUE))
					(`@z (= quit TRUE))
					(`?
						(Prints
							{Debug options:______(Page 1 of 5)\n\n___A - Show cast\n___B - Polygon editor\n___C - Show control map\n___D - Dialog Editor\n___E - Every puzzle can be found\n___F - Show feature outlines\n___G - Set global\n}
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
					(else  (event claimed: FALSE))
				)
			)
			(mouseDown
				(switch (event modifiers?)
					(13 0)
					(14 0)
					(12
						(event claimed: TRUE)
						(Format @str 10 2 (event x?) (event y?))
						(= oldPort
							(Print
								posn: 160 10
								font: 999
								modeless: TRUE
								addText: @str
								init:
							)
						)
						(while (!= 2 ((= evt (Event new:)) type?))
							(evt dispose:)
						)
						(evt dispose:)
						(oldPort dispose:)
					)
					((| keyDown mouseDown)
						(event type: keyDown message: `@r)
						(self handleEvent: event)
					)
					(TAB 0)
					((| keyUp mouseUp) 0)
					(shiftRight 0)
					(shiftLeft 0)
					(ctrlDown 0)
					(altDown
						(event claimed: TRUE)
						(= oldCur (theGame setCursor: INVIS_CURSOR))
						(= i
							((= aObj
								(if gTheNewDButtonValue else (User alterEgo?))
							)
								signal?
							)
						)
						(aObj startUpd:)
						(while (!= 2 ((= evt (Event new:)) type?))
							(aObj x: (evt x?) y: (- (evt y?) 10))
							(Animate (cast elements?) 0)
							(evt dispose:)
						)
						(evt dispose:)
						(theGame setCursor: oldCur)
						(aObj signal: i)
					)
				)
			)
		)
	)
)
