;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use Intrface)
(use Save)
(use User)
(use Actor)
(use System)

;EO: This script is referenced in the main script, but has
; been removed. Here is a recreation based on the QFG2 demo's debug script.

(public
	debugRm 0
)

(instance debugRm of Script
	
	(method (handleEvent event &tmp evt obj nX nY i [str 80] nextRoom temp86)
		(switch (event type?)
			(mouseDown
				(cond 
					((& (event modifiers?) ctrlDown)
						(event claimed: TRUE)
						(User canControl: TRUE)
						(while (!= mouseUp ((= evt (Event new:)) type?))
							(GlobalToLocal evt)
							(Animate (cast elements?) FALSE)
							(ego posn: (evt x?) (evt y?) setMotion: 0)
							(evt dispose:)
						)
						(evt dispose:)
					)
					((& (event modifiers?) shiftDown)
						(event claimed: TRUE)
						(= obj
							(Print
								(Format @str DEBUG 0 (event x?) (event y?))
								#at
								(- (event x?) 21)
								(- (event y?) 17)
								#font 999
								#dispose
							)
						)
						(while (!= mouseUp ((= evt (Event new:)) type?))
							(evt dispose:)
						)
						(obj dispose:)
						(evt dispose:)
					)
				)
			)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(`@t
						(if
							(and
								(> 105 (= nextRoom (GetNumber {Which room number?})))
								(> nextRoom 0)
							)
							(curRoom newRoom: nextRoom)
						)
					)
					(`?
						(Print DEBUG 1
							#window SysWindow
						)
					)
					(`@s
						(= i (cast first:))
						(while i
							(= obj (NodeValue i))
							(Print
								(Format @str DEBUG 2
									(obj view?)
									(obj x?)
									(obj y?)
									(/ (& (obj signal?) notUpd) notUpd)
									(/ (& (obj signal?) ignrAct) ignrAct)
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
								#window SysWindow
								#title (obj name?)
								#icon (obj view?) (obj loop?) (obj cel?)
							)
							(= i (cast next: i))
						)
					)
					(`@m
						(theGame showMem:)
					)
					(`@e
						(Format @str DEBUG 3
							(ego x?)
							(ego y?)
							(ego loop?)
							(ego cel?)
						)
						(Print @str
							#icon (ego view?) 0 0
						)
					)
					(`@v
						(Show VMAP)
					)
					(`@p
						(Show PMAP)
					)
					(`@c
						(Show CMAP)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
		)
		(DisposeScript DEBUG)
	)
)