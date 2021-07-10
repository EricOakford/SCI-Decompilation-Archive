;;; Sierra Script 1.0 - (do not remove this comment)
;NOTE: This debug script was removed from the game.
; However, the beta still has this script, and it
; was taken from that.
(script# rgDebug)
(include game.sh)
(use Main)
(use Intrface)
(use Save)
(use User)
(use Actor)
(use System)

(public
	debugHandler 0
)

(procedure (PrintVanishingXY &tmp n)
	(Printf 307 0
		(curRoom vanishingX?)
		(curRoom vanishingY?)
	)
	(= n (GetNumber {vanishingX:}))
	(if (OneOf n -1 0) else (curRoom vanishingX: n))
	(= n (GetNumber {vanishingY:}))
	(if (OneOf n -1 0) else (curRoom vanishingY: n))
	(Printf 307 0
		(curRoom vanishingX?)
		(curRoom vanishingY?)
	)
)

(instance debugHandler of Feature
	
	(method (init)
		(super init:)
		(features addToFront: self)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (dispose)
		(features delete: self)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [str 101]
			temp101 temp102 saveBits evt saveWin temp106
			i [temp108 2] roomNum)
		(switch (event type?)
			(saidEvent
				(if (Said 'give/*/ego>')
					(if (= i (inventory saidMe:))
						(Print 307 1)
						(i moveTo: ego)
					else
						(Print 307 2)
					)
					(event claimed: TRUE)
				)
			)
			(keyDown
				(= saveWin systemWindow)
				(= systemWindow iceDebugWindow)
				(event claimed: TRUE)
				(switch (event message?)
					(`@f (theGame showMem:))
					(`@g
						(= str 0)
						(GetInput @str 4 {Variable No.})
						(= temp102 (ReadNumber @str))
						(= str 0)
						(GetInput @str 4 {Value})
						(= [temp101 (- temp102 100)] (ReadNumber @str))
						(= str 0)
					)
					(`@v
						(Show VMAP)
					)
					(`@p
						(Show PMAP)
					)
					(`@c
						(Show CMAP)
						(Animate (cast elements?))
						(while (== nullEvt ((= event (Event new: (- allEvents mouseUp))) type?))
							(event dispose:)
						)
						(event dispose:)
						(Show VMAP)
					)
					(`@a
						(cast showSelf:)
					)
					(`@t
						(= roomNum (GetNumber {Teleport to:}))
						(= systemWindow saveWin)
						(curRoom newRoom: roomNum)
					)
					(`@d
						(if (= debugOn (not debugOn))
							(Print 307 3)
						else
							(Print 307 4)
						)
					)
					(`@r
						(Printf 307 5 curRoomNum)
					)
					(`@y
						(PrintVanishingXY)
					)
					(`@u
						(UnLoad SCRIPT (GetNumber {Unload Script#:}))
					)
					(else
						(event claimed: FALSE)
					)
				)
				(= systemWindow saveWin)
			)
			(mouseDown
				(cond 
					((& (event modifiers?) altDown)
						(event claimed: TRUE)
						(= saveBits
							(Print
								(Format @str 307 6 (event x?) (event y?))
								#at
								(cond 
									((< (event x?) 20) (event x?))
									((< 300 (event x?)) (- (event x?) 40))
									(else (- (event x?) 20))
								)
								(if (< (event y?) 16)
									(event y?)
								else
									(- (event y?) 6)
								)
								#font 999
								#dispose
							)
						)
						(while (!= mouseUp ((= evt (Event new:)) type?))
							(evt dispose:)
						)
						(evt dispose:)
						(saveBits dispose:)
					)
					((& (event modifiers?) ctrlDown)
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

(instance iceDebugWindow of SysWindow
	(properties
		back vLRED
	)
)
