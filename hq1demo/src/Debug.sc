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
	(properties)
	
	(method (handleEvent event &tmp newEvent theAct [temp2 2] i [str 80] nextRoom temp86)
		(switch (event type?)
			(mouseDown
				(cond 
					((& (event modifiers?) ctrlDown)
						(event claimed: TRUE)
						(User canControl: TRUE)
						(while (!= 2 ((= newEvent (Event new:)) type?))
							(GlobalToLocal newEvent)
							(Animate (cast elements?) FALSE)
							(ego posn: (newEvent x?) (newEvent y?) setMotion: 0)
							(newEvent dispose:)
						)
						(newEvent dispose:)
					)
					((& (event modifiers?) shiftDown)
						(event claimed: TRUE)
						(= theAct
							(Print
								(Format @str "%d/%d" (event x?) (event y?))
								#at
								(- (event x?) 21)
								(- (event y?) 17)
								#font 999
								#dispose
							)
						)
						(while (!= 2 ((= newEvent (Event new:)) type?))
							(newEvent dispose:)
						)
						(theAct dispose:)
						(newEvent dispose:)
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
						(Print "Debug Key commands:\n
							ALT-S___Show cast\n
							ALT-M___Show memory\n
							ALT-T___Teleport\n
							ALT-V___Visual\n
							ALT-P___Priority\n
							ALT-C___Control\n
							ALT-E___Show ego\n"
							#window SysWindow
						)
					)
					(`@s
						(= i (cast first:))
						(while i
							(= theAct (NodeValue i))
							(Print
								(Format @str
									"view: %d\n
									(x,y):%d,%d\n
									STOPUPD=%d\n
									IGNRACT=%d\n
									ILLBITS=$%x"
									(theAct view?)
									(theAct x?)
									(theAct y?)
									(/ (& (theAct signal?) notUpd) notUpd)
									(/ (& (theAct signal?) ignrAct) ignrAct)
									(if
										(or
											(== (theAct superClass?) Actor)
											(== (theAct superClass?) Ego)
										)
										(theAct illegalBits?)
									else
										-1
									)
								)
								#window SysWindow
								#title (theAct name?)
								#icon (theAct view?) (theAct loop?) (theAct cel?)
							)
							(= i (cast next: i))
						)
					)
					(`@m (theGame showMem:))
					(`@e
						(Format @str
							"ego\n
							x:%d y:%d\n
							loop:%d\n
							cel:%d"
							(ego x?) (ego y?) (ego loop?) (ego cel?)
						)
						(Print @str #icon (ego view?) 0 0)
					)
					(`@v (Show VMAP))
					(`@p (Show PMAP))
					(`@c (Show CMAP))
					(else  (event claimed: FALSE))
				)
			)
		)
		(DisposeScript DEBUG)
	)
)
