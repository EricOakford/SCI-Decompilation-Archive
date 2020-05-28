;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG) ;800
(include game.sh)
(use Main)
(use Intrface)
(use Sq4Dialog)
(use Sq4Feature)
(use Print)
(use Dialog)
(use PolyEdit)
(use WriteFtr)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	debugHandler 0
)

(local
	invExitButton
)
(procedure (noScrolling)
	(if (OneOf (curRoom style?) SCROLLRIGHT SCROLLLEFT SCROLLUP SCROLLDOWN)
		(curRoom drawPic: (curRoom picture?) 100 style: PLAIN)
	)
)

(instance debugHandler of Sq4Feature
	(properties
		x -1
		y -1
	)
	
	(method (handleEvent event &tmp [str 301] port evt obj node temp305 i temp307 temp308 temp309 temp310 temp311 temp312)
		(switch (event type?)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(`@k
						(= port (GetPort))
						(SetPort 0)
						(= temp311 5)
						(= temp312 16)
						(= temp307 15)
						(= temp308 80)
						(= temp310 (+ temp307 (* 34 temp311)))
						(= temp309 (+ temp308 (* 10 temp312)))
						(= temp305
							(Graph GSaveBits temp307 temp308 temp310 temp309 1)
						)
						(Graph GFillRect temp307 temp308 temp310 temp309 1 255)
						(= i 0)
						(while (< i 256)
							(Graph
								GFillRect
								(+ temp307 temp311 (* temp311 (/ i 8)))
								(+ temp308 temp312 (* 16 (mod i 8)))
								(+ temp307 temp311 temp311 (* temp311 (/ i 8)))
								(+ temp308 temp312 temp312 (* temp312 (mod i 8)))
								1
								i
							)
							(++ i)
						)
						(Graph GShowBits temp307 temp308 temp310 temp309 1)
						(repeat
							(if
								(or
									(== ((= evt (Event new:)) type?) 1)
									(== (evt type?) 4)
								)
								(break)
							)
							(evt dispose:)
						)
						(evt dispose:)
						(Graph GRestoreBits temp305)
						(Graph GShowBits temp307 temp308 temp310 temp309 1)
						(SetPort port)
					)
					(`@f (theGame showMem:))
					(`@g
						(= str 0)
						(SQ4GetInput @str 6 {Variable No.})
						(= obj (ReadNumber @str))
						(= str 0)
						(SQ4GetInput @str 6 {Value})
						(= [ego obj] (ReadNumber @str))
						(= str 0)
					)
					(`@h
						(= str 0)
						(SQ4GetInput @str 6 {Variable No.})
						(= obj (ReadNumber @str))
						(if (IsObject [ego obj])
							(SQ4Printf 800 0 obj ([ego obj] name?))
						else
							(SQ4Printf 800 1 obj [ego obj])
						)
						(= str 0)
					)
					(`@v (Show VMAP))
					(`@m
						(SQ4Printf 800 2 (= buckazoids (+ buckazoids 20)))
					)
					(`@p
						(noScrolling)
						(Show PMAP)
					)
					(`@c
						(noScrolling)
						(Show CMAP)
						(Animate (cast elements?))
						(while (== 0 ((= event (Event new: (- allEvents mouseDown))) type?))
							(event dispose:)
						)
						(event dispose:)
						(Show VMAP)
					)
					(`@e
						(SQ4Printf 800 3
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
							((ego _head?) view?)
							(ego onControl:)
							(ego onControl: origin)
						)
					)
					(`@a
						(= obj (cast first:))
						(while obj
							(= node (NodeValue obj))
							(SQ4Printf 800 4
								((node -super-?) name?)
								(node view?)
								(node loop?)
								(node cel?)
								(node x?)
								(node y?)
								(node z?)
								(node heading?)
								(node priority?)
								(node signal?)
								(if (node isKindOf: Actor)
									(node illegalBits?)
								else
									-1
								)
							)
							(= obj (cast next: obj))
						)
					)
					(`@t
						(if modelessDialog (modelessDialog dispose:))
						(if (> (= obj (GetNumber {Teleport to:})) 0)
							(music stop:)
							(++ roomTeleports)
							(curRoom newRoom: obj)
						)
					)
					(`@d
						(if (= debugOn (not debugOn))
							(Prints 800 5)
						else
							(Prints 800 6)
						)
					)
					(`@r
						(SQ4Printf
							800
							7
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
						(SQ4Printf 800 8
							(curRoom vanishingX?)
							(curRoom vanishingY?)
						)
						(= obj (GetNumber {vanishingX:}))
						(if (OneOf obj -1 0)
						else
							(curRoom vanishingX: obj)
						)
						(= obj (GetNumber {vanishingY:}))
						(if (OneOf obj -1 0)
						else
							(curRoom vanishingY: obj)
						)
						(SQ4Printf 800 8
							(curRoom vanishingX?)
							(curRoom vanishingY?)
						)
					)
					(`@w (CreateObject doit:))
					(`@u
						(User canInput: TRUE canControl: TRUE)
						(theIconBar enable:
							ICON_WALK
							ICON_LOOK
							ICON_DO
							ICON_TALK
							ICON_SMELL
							ICON_TASTE
							ICON_ITEM
							ICON_INVENTORY
						)
					)
					(`@z (= quit TRUE))
					(`@i (dInvD doit:))
					(`@b (PolygonEditor doit:))
					(else  (event claimed: FALSE))
				)
			)
			(mouseDown
				(cond 
					((== (event modifiers?) altDown)
						(event claimed: TRUE)
						(= port
							(SQ4Printf @str 800 9
								(event x?)
								(event y?)
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
						(port dispose:)
					)
					((== (event modifiers?) (| altDown ctrlDown))
						(event claimed: TRUE)
						(while (!= mouseUp ((= evt (Event new:)) type?))
							((User alterEgo?)
								posn: (evt x?) (- (evt y?) 10)
								setMotion: 0
							)
							(Animate (cast elements?) 0)
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
	(properties)
	
	(method (init &tmp temp0 temp1 temp2 i itemName inventoryFirst index)
		(= temp2 (= temp0 (= temp1 4)))
		(= i 0)
		(= inventoryFirst (inventory first:))
		(while inventoryFirst
			(= index (NodeValue inventoryFirst))
			(++ i)
			(if (index isKindOf: InvItem)
				(self
					add:
						((= itemName (DText new:))
							value: index
							text: (index name?)
							nsLeft: temp0
							nsTop: temp1
							state: 3
							font: smallFont
							setSize:
							yourself:
						)
				)
			)
			(if
			(< temp2 (- (itemName nsRight?) (itemName nsLeft?)))
				(= temp2 (- (itemName nsRight?) (itemName nsLeft?)))
			)
			(if
				(>
					(= temp1
						(+ temp1 (- (itemName nsBottom?) (itemName nsTop?)) 1)
					)
					140
				)
				(= temp1 4)
				(= temp0 (+ temp0 temp2 10))
				(= temp2 0)
			)
			(= inventoryFirst (inventory next: inventoryFirst))
		)
		(= window systemWindow)
		(self setSize:)
		(= invExitButton (DButton new:))
		(invExitButton
			text: {Outta here!}
			setSize:
			moveTo: (- nsRight (+ 4 (invExitButton nsRight?))) nsBottom
		)
		(invExitButton
			move: (- (invExitButton nsLeft?) (invExitButton nsRight?)) 0
		)
		(self add: invExitButton setSize: center:)
		(return i)
	)
	
	(method (doit &tmp theNewDButton)
		(self init:)
		(self open: 4 15)
		(= theNewDButton invExitButton)
		(repeat
			(if
				(or
					(not (= theNewDButton (super doit: theNewDButton)))
					(== theNewDButton -1)
					(== theNewDButton invExitButton)
				)
				(break)
			)
			((theNewDButton value?) owner: ego)
		)
		(self eachElementDo: #dispose 1 dispose:)
	)
	
	(method (handleEvent event &tmp eventMessage eventType)
		(= eventMessage (event message?))
		(switch (= eventType (event type?))
			(keyDown
				(switch eventMessage
					(UPARROW (= eventMessage SHIFTTAB))
					(DOWNARROW (= eventMessage TAB))
				)
			)
			(direction
				(switch eventMessage
					(dirN
						(= eventMessage SHIFTTAB)
						(= eventType keyDown)
					)
					(dirS
						(= eventMessage TAB)
						(= eventType keyDown)
					)
				)
			)
		)
		(event type: eventType message: eventMessage)
		(super handleEvent: event)
	)
)
