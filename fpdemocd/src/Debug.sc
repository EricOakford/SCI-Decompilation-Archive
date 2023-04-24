;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use DebugDlg)
(use Intrface)
(use Print)
(use PolyEdit)
(use DlgEdit)
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
	 yesI
	 [theText 25]
	 oldCursor
)

;
;(define 	shiftRight			1)	 \
;(define 	shiftLeft			2)	  \ these are defined in SYSTEM.SH
;(define 	shiftDown			3)	  /
;(define 	ctrlDown				4)	 /
;(define 	altDown				8)
(define	ctrlRShiftDown		5)
(define	ctrlLShiftDown		6)
(define	altRShiftDown		9)
(define	altLShiftDown		10)
(define	ctrlAltDown			12)
(define	ctrlAltRShiftDown	13)
(define	ctrlAltLShiftDown	14)

(procedure (CheckScroll)
	(if (OneOf (curRoom style?) SCROLLRIGHT SCROLLLEFT SCROLLUP SCROLLDOWN)
		(curRoom
			drawPic: (curRoom picture?) PLAIN
			style: PLAIN
		)
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
		(DisposeScript 974)
		(DisposeScript 10)
		(DisposeScript 25)
	)
	
	(method (handleEvent event &tmp [str 160] wind evt num theFont
			obj underbits palNum t l r b marginHigh marginWide
			temp173 n v c s oldCur
			)
		(switch (event type?)
			(keyDown
				(event claimed: 1)
				(switch (event message?)
					(`@a
						(for ((= num (cast first:))) num ((= num (cast next: num)))
							(= obj (NodeValue num))
							(Format @str
								{class: %s\n
								view: %d\n
								loop: %d\n
								cel: %d\n
								posn: %d %d %d\n
								heading: %d\n
								pri: %d\n
								signal: $%x\n
								illBits: $%x\n}
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
						)
					)
					(`@b
						(PolygonEditor doit:)
					)
					(`@c
						(CheckScroll)
						(Show CMAP)
					)
					(`@d
						(DialogEditor doit:)
					)
					(`@e
						;EO: "Every puzzle can be found", but there's no code
						; here for that
					)
					(`@f
						(features
							eachElementDo: #perform (ScriptID DEBUGDLG 2) 0
						)
						(cast
							eachElementDo: #perform (ScriptID DEBUGDLG 2) 1
						)
						(NameFeatureCode init:)
					)
					(`@g
						(= str 0)
						(GetInput @str 6 {Variable No.})
						(= num (ReadNumber @str))
						(= str 0)
						(GetInput @str 6 {Value})
						(= [ego num] (ReadNumber @str))
						(= str 0)
					)
					(`@h
						(= str 0)
						(Print
							addText: {Global number:}
							addEdit: @str 6 0 12
							init:
						)
						(= num (ReadNumber @str))
						(if (IsObject [ego num])
							(Format @str { Global %d: %s_} num ([ego num] name?))
						else
							(Format @str { Global %d: %d_} num [ego num])
						)
						(Prints @str)
					)
					(`@i
						((ScriptID DEBUGDLG 0) doit:)
					)
					(`@j
						(JustifyText init:)
					)
					(`@k
						(= wind (GetPort))
						(SetPort 0)
						(= marginHigh 5)
						(= marginWide 16)
						(= t 15)
						(= l 80)
						(= b (+ t (* 34 marginHigh)))
						(= r (+ l (* 10 marginWide)))
						(= underbits (Graph GSaveBits t l b r VMAP))
						(Graph GFillRect t l b r VMAP 255)
						(for ((= palNum 0)) (< palNum 256) ((++ palNum))
							(Graph
								GFillRect
								(+ t marginHigh (* marginHigh (/ palNum 8)))
								(+ l marginWide (* 16 (mod palNum 8)))
								(+ t marginHigh marginHigh (* marginHigh (/ palNum 8)))
								(+ l marginWide marginWide (* marginWide (mod palNum 8)))
								VMAP
								palNum
							)
						)
						(Graph GShowBits t l b r VMAP)
						(repeat
							(= evt (Event new:))
							(if (or (== (evt type?) mouseDown) (== (evt type?) keyDown))
								(break)
							)
							(evt dispose:)
						)
						(evt dispose:)
						(Graph GRestoreBits underbits)
						(Graph GShowBits t l b r VMAP)
						(SetPort wind)
					)
					(`@l
						(= str 0)
						(= num (GetNumber {Flag No.}))
						(Bset num)
					)
					(`@m
						(= str 0)
						(= num (GetNumber {Flag No.}))
						(Bclr num)
					)
					(`@n
						(= str 0)
						(= num (GetNumber {Flag No.}))
						(Format @str {%d} (Btst num))
						(Prints @str)
					)
					(`@o
						((ScriptID LOGGER) doit: @sysLogPath 0)
					)
					(`@p
						(CheckScroll)
						(Show PMAP)
					)
					(`@q
						(theGame detailLevel: 1)
					)
					(`@r
						(Format @str
							"name: %s\n
							number: %d\n
							current pic: %d\n
							style: %d\n
							horizon: %d\n
							north: %d\n
							south: %d\n
							east: %d\n
							west: %d\n
							script: %s"
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
						(Print
							width: 120
							addText: @str
							init:
						)
						(theGame showMem:)
					)
					(`@s
						(= str 0)
						(if
							(Print
								addText: { Which Format?}
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
						(= num (GetNumber {Box Width?} 0))
						(if (not (= theFont (GetNumber {Font Number?} 0)))
							(= theFont userFont)
						)
						(Print
							posn: l t
							width: num
							font: theFont
							addText: @str
							init:
						)
					)
					(`@t
						(if modelessDialog (modelessDialog dispose:))
						(if (> (= num (GetNumber {Teleport to})) 0)
							(curRoom newRoom: num)
						)
					)
					(`@u
						(User
							canInput: TRUE
							canControl: TRUE
						)
						(theIconBar enable: 
							ICON_WALK
							ICON_LOOK
							ICON_DO
							ICON_TALK
							ICON_USEIT
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
					(`?
						(Prints
							{Debug options:______(Page 1 of 5)\n\n
							___A - Show cast\n
							___B - Polygon editor\n
							___C - Show control map\n
							___D - Dialog Editor\n
							___E - Every puzzle can be found\n
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
					(ctrlAltRShiftDown
						NULL
					)
					(ctrlAltLShiftDown
						NULL
					)
					(ctrlAltDown
						(event claimed: TRUE)
						(Format @str "%d/%d" (event x?) (event y?))
						(= wind
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
						(wind dispose:)
					)
					(ctrlRShiftDown
						(event
							type: keyDown
							message: `@r
						)
						(self handleEvent: event)
					)
;;;					(ctrlLShiftDown
;;;						(event
;;;							type: keyDown
;;;							message `@E
;;;						)
;;;						(self handleEvent: event)
;;;					)
					(altRShiftDown
						NULL
					)
					(altLShiftDown
						NULL
					)
					(shiftRight
						; the iconBar uses this to advance the current icon
						NULL
					)
					(shiftLeft
						; the iconBar uses this to advance the current icon
						NULL
					)
					(ctrlDown
						; the iconBar uses this to toggle icon between walk & current
						NULL
					)
					(altDown
						(event claimed: TRUE)
						(= oldCur (theGame setCursor: INVIS_CURSOR))
						(= num
							((= temp173 (if gTheNewDButtonValue else (User input?)))
								signal?
							)
						)
						(temp173 ignoreActors:)
						(while (!= mouseUp ((= evt (Event new:)) type?))
							(temp173 x: (evt x?) y: (- (evt y?) 10))
							(Animate (cast elements?) FALSE)
							(evt dispose:)
						)
						(evt dispose:)
						(theGame setCursor: oldCur)
						(temp173 signal: num)
					)
				)
			)
		)
	)
)
