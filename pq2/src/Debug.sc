;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Actor)
(use System)

(public
	debugRm 0
)

(instance debugRm of Locale
	(method (handleEvent event &tmp evt obj [temp2 2] node [str 40])
		(if (or (not debugging) (event claimed?)) (return))
		(switch (event type?)
			(mouseDown
				(cond 
					((& (event modifiers?) ctrlDown)
						(event claimed: TRUE)
						(User canControl: TRUE)
						(while (!= mouseUp ((= evt (Event new:)) type?))
							(GlobalToLocal evt)
							(ego posn: (evt x?) (evt y?) setMotion: 0)
							(RedrawCast)
							(evt dispose:)
						)
						(evt dispose:)
					)
					((& (event modifiers?) shiftDown)
						(event claimed: TRUE)
						(= obj
							(Print
								(Format @str 801 0 (event x?) (event y?))
								#at 150 100
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
					(`?
						(Print 801 1)
					)
					(`@s
						(= node (cast first:))
						(while node
							(= obj (NodeValue node))
							(Print
								(Format @str 801 2
									(obj view?)
									(obj x?)
									(obj y?)
									(if (& (obj signal?) notUpd) {stopUpd:\n} else {})
									(if (& (obj signal?) ignrAct) {ignoreActors:\n} else {})
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
								#title (obj name?)
								#icon (obj view?) (obj loop?) (obj cel?)
							)
							(= node (cast next: node))
						)
					)
					(`@e
						(Format @str 801 3
							(ego x?)
							(ego y?)
							(ego loop?)
							(ego cel?)
						)
						(Print @str
							#icon (ego view?) #loop 0 #cel 0
						)
					)
					(`@i
						(= node (GetNumber {ID number of the object?}))
						((inventory at: node) moveTo: ego)
						(if (== node 1)
							(= [numAmmoClips 1] 7)
							(= [numAmmoClips 2] 7)
						)
					)
					(`@t
						(curRoom newRoom: (GetNumber {Which room number?}))
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
					(`@m
						(theGame showMem:)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
		)
	)
)
