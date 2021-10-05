;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use Intrface)
(use PolyEdit)
(use WriteFtr)
(use Feature)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	dbg 0
)

(local
	outtaHereButton
)
(procedure (localproc_000e param1 param2 &tmp [str 40])
	(= str 0)
	(if (> argc 1) (Format @str 899 0 param2))
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

(instance dbg of Feature
	(method (handleEvent event &tmp
			[str 75] obj evt i node saveBits theColor t l r b numCols numRows)
		(switch (event type?)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(`@k
						(= obj (GetPort))
						(SetPort 0)
						(= numCols 5)
						(= numRows 16)
						(= t 15)
						(= l 80)
						(= b (+ t (* 34 numCols)))
						(= r (+ l (* 10 numRows)))
						(= saveBits
							(Graph GSaveBits t l b r 1)
						)
						(Graph GFillRect t l b r 1 255)
						(for ((= theColor 0)) (< theColor 256) ((++ theColor))
							(Graph
								GFillRect
								(+ t numCols (* numCols (/ theColor 8)))
								(+ l numRows (* 16 (mod theColor 8)))
								(+ t numCols numCols (* numCols (/ theColor 8)))
								(+ l numRows numRows (* numRows (mod theColor 8)))
								VMAP
								theColor
							)
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
						(Graph GRestoreBits saveBits)
						(Graph GShowBits t l b r VMAP)
						(SetPort obj)
					)
					(`@f
						(theGame showMem:)
					)
					(`@g
						(= str 0)
						(GetInput @str 4 {Variable No.})
						(= i (ReadNumber @str))
						(= str 0)
						(GetInput @str 4 {Value})
						(= [ego i] (ReadNumber @str))
						(= str 0)
					)
					(`@h
						(= str 0)
						(GetInput @str 4 {Variable No.})
						(= i (ReadNumber @str))
						(if (IsObject [ego i])
							(Printf 899 1 i ([ego i] name?))
						else
							(Printf 899 2 i [ego i])
						)
						(= str 0)
					)
					(`@v
						(Show VMAP)
					)
					(`@p
						(noScrolling)
						(Show PMAP)
					)
					(`@c
						(noScrolling)
						(Show CMAP)
						(Animate (cast elements?))
						(while (== nullEvt ((= event (Event new: $7ffd)) type?))
							(event dispose:)
						)
						(event dispose:)
						(Show VMAP)
					)
					(`@e
						(if (cast contains: ego)
							(Print
								(Format @str 899 3
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
									(ego normal?)
									(ego onControl:)
									(ego onControl: origin)
								)
								#title (ego name?)
								#icon (ego view?) (ego loop?) (ego cel?)
							)
						else
							(Print 899 4)
						)
					)
					(`@a
						(= i (cast first:))
						(while i
							(= node (NodeValue i))
							(Print
								(Format @str 899 5
									(node name?)
									((node superClass?) name?)
									(node view?)
									(node loop?)
									(node cel?)
									(node x?)
									(node y?)
									(node z?)
									(node heading?)
									(node priority?)
									(node signal?)
									(if
										(or
											(== (node superClass?) Actor)
											(== (node superClass?) Ego)
										)
										(node illegalBits?)
									else
										-1
									)
								)
								#icon
								(node view?)
								(node loop?)
								(node cel?)
							)
							(= i (cast next: i))
						)
					)
					(`@t
						(if modelessDialog
							(modelessDialog dispose:)
						)
						(if (> (= i (GetNumber {Teleport to:})) 0)
							(theMusic stop:)
							(curRoom newRoom: i)
						)
					)
					(`@d
						(if (= debugOn (not debugOn))
							(Print 899 6)
						else
							(Print 899 7)
						)
					)
					(`@r
						(Printf 899 8
							curRoomNum
							(curRoom name?)
							(if (IsObject (curRoom script?))
								((curRoom script?) name?)
							else
								{..none..}
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
					)
					(`@y
						(Printf 899 9
							(curRoom vanishingX?)
							(curRoom vanishingY?)
						)
						(= i (localproc_000e {vanishingX:}))
						(if (OneOf i -1 0)
						else
							(curRoom vanishingX: i)
						)
						(= i (localproc_000e {vanishingY:}))
						(if (OneOf i -1 0)
						else
							(curRoom vanishingY: i)
						)
						(Printf 899 9
							(curRoom vanishingX?)
							(curRoom vanishingY?)
						)
					)
					(`@w
						(Class_948_2 doit:)
					)
					(`@u
						(User canInput: TRUE canControl: TRUE)
						(theIconBar enable:
							ICON_WALK
							ICON_LOOK
							ICON_DO
							ICON_TALK
							ICON_HELP
							ICON_NOTEBOOK
							ICON_ITEM
							ICON_INVENTORY
						)
					)
					(`@z
						(= quit TRUE)
					)
					(`@i
						(dInvD doit:)
					)
					(`@b
						(PolygonEditor doit:)
					)
					(3
						(while (not (!= (= i (GetNumber {Clear Flag#:_})) -1)))
						(Bclr i)
					)
					($0013
						(while (not (!= (= i (GetNumber {Set Flag#:_})) -1)))
						(Bset i)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			(mouseDown
				(cond 
					((== (event modifiers?) altDown)
						(event claimed: TRUE)
						(= obj
							(Print
								(Format @str 899 10 (event x?) (event y?))
								#at
								(cond 
									((< (event x?) 23) 3)
									((> (event x?) 292) 273)
									(else (- (event x?) 20))
								)
								(cond 
									((< (event y?) 9) 3)
									((> (event y?) 175) 170)
									(else (- (event y?) 6))
								)
								#font 999
								#dispose
							)
						)
						(while (!= mouseUp ((= evt (Event new:)) type?))
							(evt dispose:)
						)
						(evt dispose:)
						(obj dispose:)
					)
					((== (event modifiers?) (| ctrlDown altDown))
						(event claimed: TRUE)
						(while (!= mouseUp ((= evt (Event new:)) type?))
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
)

(instance dInvD of Dialog
	(method (init &tmp temp0 temp1 temp2 temp3 newDText node obj)
		(= temp2 (= temp0 (= temp1 4)))
		(= temp3 0)
		(= node (inventory first:))
		(while node
			(= obj (NodeValue node))
			(++ temp3)
			(if (obj isKindOf: InvItem)
				(self
					add:
						((= newDText (DText new:))
							value: obj
							text: (obj name?)
							nsLeft: temp0
							nsTop: temp1
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
					(= temp1
						(+ temp1 (- (newDText nsBottom?) (newDText nsTop?)) 1)
					)
					140
				)
				(= temp1 4)
				(= temp0 (+ temp0 temp2 10))
				(= temp2 0)
			)
			(= node (inventory next: node))
		)
		(= window systemWindow)
		(self setSize:)
		(= outtaHereButton (DButton new:))
		(outtaHereButton
			text: {Outta here!}
			setSize:
			moveTo: (- nsRight (+ 4 (outtaHereButton nsRight?))) nsBottom
		)
		(outtaHereButton
			move: (- (outtaHereButton nsLeft?) (outtaHereButton nsRight?)) 0
		)
		(self add: outtaHereButton setSize: center:)
		(return temp3)
	)
	
	(method (doit &tmp theOuttaHereButton)
		(self init:)
		(self open: 4 15)
		(= theOuttaHereButton outtaHereButton)
		(repeat
			(if
				(or
					(not
						(= theOuttaHereButton (super doit: theOuttaHereButton))
					)
					(== theOuttaHereButton -1)
					(== theOuttaHereButton outtaHereButton)
				)
				(break)
			)
			((theOuttaHereButton value?) owner: ego)
		)
		(self dispose:)
	)
	
	(method (handleEvent event &tmp evMsg evType)
		(= evMsg (event message?))
		(switch (= evType (event type?))
			(keyDown
				(switch evMsg
					(UPARROW
						(= evMsg SHIFTTAB)
					)
					(DOWNARROW
						(= evMsg TAB)
					)
				)
			)
			(direction
				(switch evMsg
					(dirN
						(= evMsg SHIFTTAB)
						(= evType keyDown)
					)
					(dirS
						(= evMsg TAB)
						(= evType keyDown)
					)
				)
			)
		)
		(event type: evType message: evMsg)
		(super handleEvent: event)
	)
)
