;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use Intrface)
(use Save)
(use User)
(use Actor)
(use System)

(public
	debugRm 0
)

(instance debugRm of Script
	(properties)
	
	(method (handleEvent event &tmp evt obj [temp2 2] node [str 80] i temp86)
		(switch (event type?)
			(mouseDown
				(if debugging
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
									(Format @str DEBUG 0 (event x?) (event y?))
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
			)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(`@t
						(if
							(and
								(> 105 (= i (GetNumber {Which room number?})))
								(> i 0)
							)
							(curRoom newRoom: i)
						)
					)
					(`@d
						(event claimed: TRUE)
						(= debugging (^ debugging TRUE))
					)
					(`?
						(Print 801 1 #window SysWindow)
					)
					(`@s
						(if debugging
							(= node (cast first:))
							(while node
								(= obj (NodeValue node))
								(Print
									(Format @str 801 2
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
								(= node (cast next: node))
							)
						)
					)
					(`@e
						(if debugging
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
