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
	yesI
)
(procedure (GetNum n default &tmp [str 40])
	(= str 0)
	(if (> argc 1)
		(Format @str 899 0 default)
	)
	(return
		(if (GetInput @str 10 n)
			(ReadNumber @str)
		else
			-1
		)
	)
)

(procedure (CheckScroll)
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
							(Graph GSaveBits t l b r VMAP)
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
						(CheckScroll)
						(Show PMAP)
					)
					(`@c
						(CheckScroll)
						(Show CMAP)
						(Animate (cast elements?))
						(while (== 0 ((= event (Event new: (- allEvents mouseUp))) type?))
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
						(= i (GetNum {vanishingX:}))
						(if (OneOf i -1 0)
						else
							(curRoom vanishingX: i)
						)
						(= i (GetNum {vanishingY:}))
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
	(method (init &tmp lastX lastY widest num el node obj)
		(= widest (= lastX (= lastY 4)))
		(= num 0)
		(for ((= node (inventory first?))) node ((= node (inventory next: node)))
			(= obj (NodeValue node))
			(++ num)
			(if (obj isKindOf: InvItem)
				(self
					add:
						((= el (DText new:))
							value: obj
							text: (obj name?)
							nsLeft: lastX
							nsTop: lastY
							state: 3
							font: smallFont
							setSize:
							yourself:
						)
				)
			)
			(if (< widest (- (el nsRight?) (el nsLeft?)))
				(= widest (- (el nsRight?) (el nsLeft?)))
			)
			(if
				(>
					(= lastY
						(+ lastY (- (el nsBottom?) (el nsTop?)) 1)
					)
					140
				)
				(= lastY 4)
				(= lastX (+ lastX widest 10))
				(= widest 0)
			)
		)
		(= window systemWindow)
		(self setSize:)
		(= yesI (DButton new:))
		(yesI
			text: {Outta here!}
			setSize:
			moveTo: (- nsRight (+ 4 (yesI nsRight?))) nsBottom
		)
		(yesI
			move: (- (yesI nsLeft?) (yesI nsRight?)) 0
		)
		(self add: yesI setSize: center:)
		(return num)
	)
	
	(method (doit &tmp el)
		(self init:)
		(self open: 4 15)
		(= el yesI)
		(repeat
			(if
				(or
					(not
						(= el (super doit: el))
					)
					(== el -1)
					(== el yesI)
				)
				(break)
			)
			((el value?) owner: ego)
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
