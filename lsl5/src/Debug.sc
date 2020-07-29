;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use Intrface)
(use PrintD)
(use Feature)
(use Window)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	debugHandler 0
)

(local
	newDButton
	[local1 26]
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
	)
	
	(method (handleEvent event &tmp [str 150] [str2 10] temp160 evt i obj [temp164 8])
		(switch (event type?)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(`@a
						(= i (cast first:))
						(while i
							(= obj (NodeValue i))
							(Format @str 10 1
								((obj superClass?) name?)
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
										(== (obj superClass?) Actor)
										(== (obj superClass?) Ego)
									)
									(obj illegalBits?)
								else
									-1
								)
							)
							(Print @str
								#window SysWindow
								#title (obj name?)
								#icon (obj view?) (obj loop?) (obj cel?)
							)
							(= i (cast next: i))
						)
					)
					(`@c
						(noScrolling)
						(Show CMAP)
						(Animate (cast elements?))
						(while (== 0 ((= event (Event new: 32765)) type?))
							(event dispose:)
						)
						(event dispose:)
						(Show VMAP)
					)
					(`@e
						(Format @str 10 2
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
							(ego onControl: 1)
						)
						(Print @str
							#icon (ego view?) (ego loop?) (ego cel?)
						)
					)
					(`@f
						(features eachElementDo: #perform showFeatureCode)
						(NameFeatureCode init:)
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
							(Format @str 10 3 i ([ego i] name?))
							(Print @str)
						else
							(Format @str 10 4 i [ego i])
							(Print @str)
						)
						(= str 0)
					)
					(`@i
						(dInvD doit:)
					)
					(`@m
						(Format @str 10 0 bucks)
						(Format @str2 10 0 silverDollars)
						(PrintD
							{Dollars:_}
							#edit @str 6
							#new
							{Silver:__} #edit @str2 6
						)
						(ego put: iMoney 0)
						(ego put: iSilverDollar 0)
						(if (= bucks (ReadNumber @str))
							(ego get: iMoney)
						)
						(if (= silverDollars (ReadNumber @str2))
							(ego get: iSilverDollar)
						)
					)
					(`@p
						(noScrolling)
						(Show 2)
					)
					(`@q
						(theGame detailLevel: 1)
					)
					(`@r
						(Format @str 10 5
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
						(Print @str #width 120)
						(theGame showMem:)
					)
					(`@t
						(if modelessDialog
							(modelessDialog dispose:)
						)
						(if (> (= i (GetNumber {Teleport to})) 0)
							(curRoom newRoom: i)
							(= gGCastFirst i)
						)
					)
					(`@u
						(User canInput: TRUE canControl: TRUE)
						(theIconBar enable:
							ICON_WALK
							ICON_LOOK
							ICON_DO
							ICON_TALK
							ICON_ZIPPER
							ICON_SKIP
							ICON_ITEM
							ICON_INVENTORY
						)
					)
					(`@v
						(Show VMAP)
					)
					(`@x
						(= quit TRUE)
					)
					(`@z
						(= quit TRUE)
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
						(Format @str 10 6 (event x?) (event y?))
						(= temp160 (Print @str #at 160 10 #font 999 #dispose))
						(while (!= 2 ((= evt (Event new:)) type?))
							(evt dispose:)
						)
						(evt dispose:)
						(temp160 dispose:)
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
						(while (!= 2 ((= evt (Event new:)) type?))
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
	
	(method (init &tmp l t temp2 i newDText inventoryFirst obj)
		(= temp2 (= l (= t 4)))
		(= i 0)
		(= inventoryFirst (inventory first:))
		(while inventoryFirst
			(= obj (NodeValue inventoryFirst))
			(++ i)
			(if (obj isKindOf: InvItem)
				(self
					add:
						((= newDText (DText new:))
							value: obj
							text: (obj name?)
							nsLeft: l
							nsTop: t
							state: 3
							font: smallFont
							setSize:
							yourself:
						)
				)
			)
			(if
			(< temp2 (- (newDText nsRight?) (newDText nsLeft?)))
				(= temp2 (- (newDText nsRight?) (newDText nsLeft?)))
			)
			(if
				(>
					(= t
						(+ t (- (newDText nsBottom?) (newDText nsTop?)) 1)
					)
					140
				)
				(= t 4)
				(= l (+ l temp2 10))
				(= temp2 0)
			)
			(= inventoryFirst (inventory next: inventoryFirst))
		)
		(= window systemWindow)
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
			(ego get: (Inventory indexOf: (theNewDButton value?)))
		)
		(self dispose:)
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

(instance showFeatureCode of Code
	(properties)
	
	(method (doit aObj &tmp t l r b)
		(= t (aObj nsTop?))
		(= l (aObj nsLeft?))
		(= b (aObj nsBottom?))
		(= r (aObj nsRight?))
		(Graph GDrawLine t l t r VMAP 15)
		(Graph GDrawLine b l b r VMAP 15)
		(Graph GDrawLine t l b l VMAP 15)
		(Graph GDrawLine t r b r VMAP 15)
		(Graph GShowBits t l (+ b 1) (+ r 1) VMAP)
	)
)

(class NameFeatureCode of Code
	
	(method (init)
		(keyDownHandler addToFront: self)
		(theDoits add: self)
	)
	
	(method (doit &tmp aObj [str 40])
		(OnMeAndLowY init:)
		(features
			eachElementDo: #perform OnMeAndLowY (User curEvent?)
		)
		(if (= aObj (OnMeAndLowY theObj?))
			(DrawStatus
				(Format @str 10 7 ((User curEvent?) x?) ((User curEvent?) y?) (aObj name?))
			)
		)
	)
	
	(method (dispose)
		(DrawStatus 0)
		(DrawStatus {_} myShadowColor 0)
		(keyDownHandler delete: self)
		(theDoits delete: self)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) keyDown)
				(== (event message?) ESC)
			)
			(event claimed: TRUE)
			(self dispose:)
		)
	)
)
